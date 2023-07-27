package com.duo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.duo.domain.entity.User;
import org.springframework.stereotype.Service;


/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-27 18:08:05
 */
@Service
public interface UserMapper extends BaseMapper<User> {

}
