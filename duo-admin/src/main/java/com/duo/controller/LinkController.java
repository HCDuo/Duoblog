package com.duo.controller;

import com.duo.domain.ResponseResult;
import com.duo.domain.vo.PageVo;
import com.duo.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
