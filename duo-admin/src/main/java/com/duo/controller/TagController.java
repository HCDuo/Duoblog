package com.duo.controller;

import com.duo.domain.ResponseResult;
import com.duo.domain.dto.TagDto;
import com.duo.domain.dto.TagListDto;
import com.duo.domain.entity.Tag;
import com.duo.domain.vo.PageVo;
import com.duo.domain.vo.TagVo;
import com.duo.service.TagService;
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
 * @date:2023/7/31 16:44
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    @PostMapping
    public ResponseResult<?> addTag(@Validated @RequestBody TagDto tagDtO) {
        return tagService.addTag(tagDtO);
    }

    @DeleteMapping("/{id}")
    public ResponseResult<?> deleteTag(@PathVariable("id") Long id) {
        return tagService.deleteTag(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getTagDetail(@PathVariable("id") Long id){
        return tagService.getTagDetail(id);
    }
    @PutMapping()
    public ResponseResult updateTag(@RequestBody Tag tag){
        return tagService.updateTag(tag);
    }
    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
}
