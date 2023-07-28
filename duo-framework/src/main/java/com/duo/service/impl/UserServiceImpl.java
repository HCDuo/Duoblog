package com.duo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duo.domain.entity.User;
import com.duo.mapper.UserMapper;
import com.duo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2023-07-28 23:24:06
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
