package com.duo.controller;

import com.duo.domain.ResponseResult;
import com.duo.domain.dto.CategoryDto;
import com.duo.domain.dto.LinkDto;
import com.duo.domain.vo.PageVo;
import com.duo.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/8/4 16:27
 */
@RestController
@RequestMapping("/content/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/list")
    public ResponseResult<PageVo> Linklist(Integer pageNum, Integer pageSize, String name, String status){
        return linkService.Linklist(pageNum,pageSize,name,status);
    }

    @PostMapping
    public ResponseResult<?> addLink(@RequestBody @Validated LinkDto linkDto) {
        return linkService.addLink(linkDto);
    }

}
