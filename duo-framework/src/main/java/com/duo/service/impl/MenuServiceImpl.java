package com.duo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duo.constants.SystemConstants;
import com.duo.domain.ResponseResult;
import com.duo.domain.entity.Menu;
import com.duo.domain.entity.Tag;
import com.duo.domain.vo.MenuUpdateVo;
import com.duo.enums.AppHttpCodeEnum;
import com.duo.exception.SystemException;
import com.duo.mapper.MenuMapper;
import com.duo.service.MenuService;
import com.duo.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-07-31 19:05:35
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuService menuService;

    @Override
    public List<String> selectPermsByUserId(Long id) {
        //如果是管理员，返回所有的权限
        if(id == 1L){
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType,SystemConstants.MENU,SystemConstants.BUTTON);
            wrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(wrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }
        //否则返回所具有的权限
        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        //判断是否是管理员
        if(userId == 1L){
            //如果是 获取所有符合要求的Menu
            menus = menuMapper.selectAllRouterMenu();
        }else{
            //否则  获取当前用户所具有的Menu
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }

        //构建tree
        //先找出第一层的菜单  然后去找他们的子菜单设置到children属性中
        List<Menu> menuTree = builderMenuTree(menus,0L);
        return menuTree;
    }

    @Override
    public ResponseResult<List<Menu>> adminMenuList(String status, String menuName) {
        // 构建查询条件(模糊查询)
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(status)) {
            wrapper.like(Menu::getStatus, status);
        }
        if (StringUtils.hasText(menuName)) {
            wrapper.like(Menu::getMenuName, menuName);
        }
        // 组装响应数据
        List<Menu> menuList = menuService.list(wrapper);
        // 按照父菜单id和orderNum字段进行排序
        menuList.sort(Comparator.comparing(Menu::getParentId)
                .thenComparing(Menu::getOrderNum));
        return ResponseResult.okResult(menuList);
    }

    @Override
    public ResponseResult adminAddMenu(Menu menu) {
        // 名称是否存在
        Menu existMenu = menuMapper.selectOne(new LambdaQueryWrapper<Menu>()
                .select(Menu::getId)
                .eq(Menu::getMenuName, menu.getMenuName()));
        if (existMenu != null) {
            throw new SystemException(AppHttpCodeEnum.MENU_EXIST);
        }
        // 将传入的menu对象保存到数据库中
        menu.setCreateTime(new Date());
        int rows = menuMapper.insert(menu);
        if (rows > 0) {
            // 保存成功，返回包含添加的菜单数据的ResponseResult
            return ResponseResult.okResult();
        } else {
            // 保存失败，返回响应结果表示操作失败
            throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ResponseResult getMenuDetail(Long id) {
        Menu menu = getById(id);
        MenuUpdateVo menuUpdateVo = BeanCopyUtils.copyBean(menu, MenuUpdateVo.class);
        return ResponseResult.okResult(menuUpdateVo);
    }

    @Override
    public ResponseResult updateMenu(Menu menu) {
        // 检查是否将父菜单设置为当前菜单
        if (menu.getParentId() != null && menu.getId().equals(menu.getParentId())) {
            return ResponseResult.errorResult(500, "修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        menu.setUpdateTime(new Date());
        // 将传入的menu对象更新到数据库中
        try {
            int rows = menuMapper.updateById(menu);
            if (rows > 0) {
                // 更新成功，返回成功响应
                return ResponseResult.okResult();
            } else {
                // 更新失败，返回响应结果表示操作失败
                return ResponseResult.errorResult(500, "修改菜单失败");
            }
        } catch (Exception e) {
            // 捕获异常，返回响应结果表示操作失败
            return ResponseResult.errorResult(500, "修改菜单失败：" + e.getMessage());
        }
    }

    @Override
    public ResponseResult deleteMenu(Long id) {
        // 判断是否存在这个菜单
        Menu menu = menuMapper.selectById(id);
        if (menu == null) {
            return ResponseResult.errorResult(500, "菜单不存在");
        }

        // 判断是否有子菜单
        List<Menu> children = menuMapper.selectList(new LambdaQueryWrapper<Menu>().eq(Menu::getParentId, id));
        if (!children.isEmpty()) {
            return ResponseResult.errorResult(500, "存在子菜单不允许删除");
        }

        // 进行逻辑删除
        LambdaUpdateWrapper<Menu> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Menu::getId, menu.getId())
                .set(Menu::getDelFlag, 1);
        int success = menuMapper.update(menu, updateWrapper);
        if (success > 0) {
            // 删除成功，返回成功响应
            return ResponseResult.okResult();
        } else {
            // 删除失败，返回响应结果表示操作失败
            return ResponseResult.errorResult(500, "删除菜单失败");
        }
    }

    private List<Menu> builderMenuTree(List<Menu> menus, Long parentId) {
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }
    /**
     * 获取存入参数的 子Menu集合
     * @param menu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> childrenList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m->m.setChildren(getChildren(m,menus)))
                .collect(Collectors.toList());
        return childrenList;
    }
}
