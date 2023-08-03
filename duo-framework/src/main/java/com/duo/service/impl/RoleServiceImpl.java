package com.duo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duo.domain.ResponseResult;
import com.duo.domain.dto.RoleAddDTO;
import com.duo.domain.dto.RoleStatusDto;
import com.duo.domain.entity.Article;
import com.duo.domain.entity.Role;
import com.duo.domain.vo.PageVo;
import com.duo.enums.AppHttpCodeEnum;
import com.duo.exception.SystemException;
import com.duo.mapper.RoleMapper;
import com.duo.mapper.RoleMenuMapper;
import com.duo.service.RoleService;
import com.duo.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2023-07-31 19:09:55
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否是管理员 如果是返回集合中只需要有admin
        if(id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户所具有的角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }

    @Override
    public ResponseResult<PageVo> pageRoleList(Integer pageNum, Integer pageSize, String roleName, String status) {
        // 构建查询条件(模糊查询)
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(roleName)) {
            wrapper.like(Role::getRoleName, roleName);
        }
        if (StringUtils.hasText(status)) {
            wrapper.like(Role::getStatus, status);
        }

        // 分页查询
        Page<Role> page = new Page<>(pageNum, pageSize);
        IPage<Role> rolePage = roleMapper.selectPage(page, wrapper);

        // 组装响应数据
        PageVo pageVo = new PageVo(rolePage.getRecords(), rolePage.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult<?> changeStatus(RoleStatusDto roleStatus) {
        // 检查角色是否存在
        if(roleStatus.getRoleId() == 1){
            throw new SystemException(AppHttpCodeEnum.ADMIN_ERROR);
        }
        //检查是不是超级管理员
        Role role = roleMapper.selectById(roleStatus.getRoleId());
        if (role == null) {
            return ResponseResult.errorResult(500, "角色不存在");
        }
        //更新到数据库
        LambdaUpdateWrapper<Role> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Role::getId, roleStatus.getRoleId())
                .set(Role::getStatus, roleStatus.getStatus());
        int success = roleMapper.update(role, updateWrapper);
        if (success > 0) {
            // 状态更新成功
            return ResponseResult.okResult();
        } else {
            // 状态更新失败
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ResponseResult<?> addRole(RoleAddDTO roleAddDTO) {
        // 角色名是否存在
        String roleName = roleAddDTO.getRoleName();
        Role existingRole = roleMapper.selectOne(new QueryWrapper<Role>().eq("role_name", roleName));
        if (existingRole != null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.ROLE_EXIST);
        }
        // 添加新角色
        Role newRole = BeanCopyUtils.copyBean(roleAddDTO, Role.class);
        newRole.setCreateTime(new Date());
        roleMapper.insert(newRole);
        // 给权限
        roleMenuMapper.insertRoleMenu(newRole.getId(), roleAddDTO.getMenuIds());
        return ResponseResult.okResult();
    }
}
