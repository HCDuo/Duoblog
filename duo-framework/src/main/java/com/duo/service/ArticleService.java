package com.duo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.duo.domain.ResponseResult;
import com.duo.domain.entity.Article;

/**
 * 文章表(Article)表服务接口
 *
 * @author makejava
 * @since 2023-07-26 14:30:50
 */
public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList();
}

