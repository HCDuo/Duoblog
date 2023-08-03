package com.duo.controller;

import com.duo.domain.ResponseResult;
import com.duo.service.RoleService;
import com.duo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/8/4 0:05
 */
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public ResponseResult adminArticleList(Integer pageNum, Integer pageSize, String userName, String phonenumber,String status){
        return userService.adminArticleList(pageNum,pageSize,userName,phonenumber,status);
    }

}
