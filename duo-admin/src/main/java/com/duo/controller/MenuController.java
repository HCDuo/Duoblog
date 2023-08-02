package com.duo.controller;

import com.duo.annotation.SystemLog;
import com.duo.domain.ResponseResult;
import com.duo.domain.entity.Article;
import com.duo.domain.entity.Menu;
import com.duo.service.ArticleService;
import com.duo.service.MenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
