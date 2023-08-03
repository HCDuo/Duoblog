package com.duo.controller;

import com.duo.domain.ResponseResult;
import com.duo.domain.dto.RoleStatusDto;
import com.duo.domain.dto.TagListDto;
import com.duo.domain.vo.PageVo;
import com.duo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/8/3 14:55
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public ResponseResult<PageVo> pageRoleList(Integer pageNum, Integer pageSize, String roleName,String status){
        return roleService.pageRoleList(pageNum,pageSize,roleName,status);
    }

    @PutMapping("/changeStatus")
    public ResponseResult<?> changeStatus(@Validated @RequestBody RoleStatusDto roleStatus) {
        return roleService.changeStatus(roleStatus);
    }
}
