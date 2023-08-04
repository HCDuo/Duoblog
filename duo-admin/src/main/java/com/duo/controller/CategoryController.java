package com.duo.controller;

import com.duo.domain.ResponseResult;
import com.duo.domain.dto.CategoryDto;
import com.duo.domain.vo.CategoryVo;
import com.duo.domain.vo.PageVo;
import com.duo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/8/2 17:18
 */
@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory(){
        List<CategoryVo> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }
    @GetMapping("/list")
    public ResponseResult<PageVo> listCategory(Integer pageNum, Integer pageSize, String name, String status){
        return categoryService.listCategory(pageNum,pageSize,name,status);
    }

    @PostMapping
    public ResponseResult<?> addCategory(@RequestBody @Validated CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

}
