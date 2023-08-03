package com.duo.controller;

import com.duo.annotation.SystemLog;
import com.duo.domain.ResponseResult;
import com.duo.domain.entity.Menu;
import com.duo.service.MenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/8/3 0:53
 */
@RestController
@RequestMapping("/system/menu")
@Api(tags = "菜单",description = "菜单相关接口")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @GetMapping("/list")
    @SystemLog(BusinessName = "展示菜单信息")
    public ResponseResult<List<Menu>> adminMenuList(String status ,String menuName){
        return menuService.adminMenuList(status,menuName);
    }

    @PostMapping()
    @SystemLog(BusinessName = "增加菜单")
    public ResponseResult adminAddMenu(@RequestBody Menu menu){
        return menuService.adminAddMenu(menu);
    }

    @GetMapping("/{id}")
    @SystemLog(BusinessName = "显示具体菜单信息")
    public ResponseResult getMenuDetail(@PathVariable("id") Long id){
        return menuService.getMenuDetail(id);
    }

    @PutMapping()
    @SystemLog(BusinessName = "修改具体菜单信息")
    public ResponseResult updateMenu(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteMenu(@PathVariable("id") Long id) {
        return menuService.deleteMenu(id);
    }
}
