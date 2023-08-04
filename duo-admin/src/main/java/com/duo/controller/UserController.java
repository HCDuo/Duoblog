package com.duo.controller;

import com.duo.domain.ResponseResult;
import com.duo.domain.dto.AddUserDto;
import com.duo.domain.dto.RoleAddDTO;
import com.duo.domain.dto.UserDto;
import com.duo.domain.vo.UserUpdateVo;
import com.duo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @DeleteMapping("/{id}")
    public ResponseResult<Object> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@ps.hasPermission('system:user:edit')")
    public ResponseResult<UserUpdateVo> getUserInfo(@PathVariable Long id){
        return userService.getUserInfo(id);
    }
    @PutMapping
    public ResponseResult<?> updateUserInfo(@RequestBody @Validated UserDto userDto){
        return userService.updateUser(userDto);
    }
}
