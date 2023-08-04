package com.duo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duo.constants.SystemConstants;
import com.duo.domain.ResponseResult;
import com.duo.domain.entity.Category;
import com.duo.domain.entity.Link;
import com.duo.domain.vo.LinkVo;
import com.duo.domain.vo.PageVo;
import com.duo.mapper.LinkMapper;
import com.duo.service.LinkService;
import com.duo.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2023-07-27 16:48:31
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        //转换成vo
        List<Link> links = list(queryWrapper);
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links,LinkVo.class);
        //封装返回
        return ResponseResult.okResult(linkVos);
    }

    @Override
    public ResponseResult<PageVo> Linklist(Integer pageNum, Integer pageSize, String name, String status) {
        // 构建查询条件(模糊查询)
        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(name)) {
            wrapper.like(Link::getName, name);
        }

        // 分页查询
        Page<Link> page = new Page<>(pageNum, pageSize);
        IPage<Link> rolePage = linkMapper.selectPage(page, wrapper);

        // 组装响应数据
        PageVo pageVo = new PageVo(rolePage.getRecords(), rolePage.getTotal());
        return ResponseResult.okResult(pageVo);
    }
}
