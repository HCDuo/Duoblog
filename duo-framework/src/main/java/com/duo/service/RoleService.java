package com.duo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.duo.domain.entity.Role;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2023-07-31 19:09:55
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);

}
