package com.duo.controller;

import com.duo.domain.ResponseResult;
import com.duo.domain.dto.AddUserDto;
import com.duo.domain.dto.RoleAddDTO;
import com.duo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    public ResponseResult adminArticleList(Integer pageNum, Integer pageSize, String userName, String phonenumber,String status){
        return userService.adminArticleList(pageNum,pageSize,userName,phonenumber,status);
    }

    @PostMapping()
    public ResponseResult<?> addUser(@RequestBody AddUserDto addUserDto) {
        return userService.addUser(addUserDto);
    }
}
