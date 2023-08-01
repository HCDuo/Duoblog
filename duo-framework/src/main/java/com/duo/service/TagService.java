package com.duo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.duo.domain.ResponseResult;
import com.duo.domain.dto.TagListDto;
import com.duo.domain.entity.Tag;
import com.duo.domain.vo.PageVo;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-07-31 16:37:15
 */
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);
}
