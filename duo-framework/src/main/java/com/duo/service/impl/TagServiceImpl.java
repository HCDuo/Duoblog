package com.duo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duo.domain.entity.Tag;
import com.duo.mapper.TagMapper;
import com.duo.service.TagService;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-07-31 16:37:15
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
