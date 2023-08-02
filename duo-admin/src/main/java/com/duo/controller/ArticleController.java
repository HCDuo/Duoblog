package com.duo.controller;

import com.duo.domain.ResponseResult;
import com.duo.domain.dto.AddArticleDto;
import com.duo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/8/2 17:30
 */
@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }

    @GetMapping("/list")
    public ResponseResult adminArticleList(Integer pageNum,Integer pageSize,String title, String summary){
        return articleService.adminArticleList(pageNum,pageSize,title,summary);
    }


}
