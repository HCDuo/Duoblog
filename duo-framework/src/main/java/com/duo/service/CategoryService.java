package com.duo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.duo.domain.ResponseResult;
import com.duo.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-07-26 18:32:26
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}
