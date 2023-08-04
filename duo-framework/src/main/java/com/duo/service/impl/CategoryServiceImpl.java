package com.duo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duo.constants.SystemConstants;
import com.duo.domain.ResponseResult;
import com.duo.domain.entity.Article;
import com.duo.domain.entity.Category;
import com.duo.domain.entity.Role;
import com.duo.domain.vo.CategoryVo;
import com.duo.domain.vo.PageVo;
import com.duo.mapper.CategoryMapper;
import com.duo.service.ArticleService;
import com.duo.service.CategoryService;
import com.duo.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-07-26 18:32:27
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ResponseResult getCategoryList() {
        //查询状态是已经发布的文章表
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);
        //获取文章的id
        Set<Long> categoryIds = articleList.stream()
                .map(Article::getCategoryId)
                .collect(Collectors.toSet());
        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        categories.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //返回vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories,CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public List<CategoryVo> listAllCategory() {
        //查询所有分类，判断是不是正常状态
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, SystemConstants.NORMAL);
        //封装数据
        List<Category> list = list(wrapper);
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(list, CategoryVo.class);
        //返回数据
        return categoryVos;
    }

    @Override
    public ResponseResult<PageVo> listCategory(Integer pageNum, Integer pageSize, String name, String status) {
        // 构建查询条件(模糊查询)
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(name)) {
            wrapper.like(Category::getName, name);
        }

        // 分页查询
        Page<Category> page = new Page<>(pageNum, pageSize);
        IPage<Category> rolePage = categoryMapper.selectPage(page, wrapper);

        // 组装响应数据
        PageVo pageVo = new PageVo(rolePage.getRecords(), rolePage.getTotal());
        return ResponseResult.okResult(pageVo);
    }
}
