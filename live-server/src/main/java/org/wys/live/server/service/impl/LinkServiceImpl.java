package org.wys.live.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wys.live.domain.po.Link;
import org.wys.live.server.dao.LinkMapper;
import org.wys.live.server.service.LinkService;

import java.util.List;

/**
 * @author wys
 * @date 2021/2/3 22:36
 */
@Service
@Slf4j
@AllArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final LinkMapper linkMapper;

    @Override
    @Transactional
    public void insertLink(Link link) {
        linkMapper.insert(link);
    }

    @Override
    public List<Link> selectAllLinksByVideoId(Integer videoId) {
        QueryWrapper<Link> linkQueryWrapper = new QueryWrapper<>();
        linkQueryWrapper.eq("video_id",videoId);
        return linkMapper.selectList(linkQueryWrapper);
    }

    @Override
    public Link selectLinkByVideoAndSeq(Integer videoId, Integer seq) {
        QueryWrapper<Link> linkQueryWrapper = new QueryWrapper<>();
        linkQueryWrapper.eq("video_id",videoId);
        linkQueryWrapper.eq("seq",seq);
        return linkMapper.selectOne(linkQueryWrapper);
    }

    @Override
    public Integer selectLinkByVideoCount(Integer videoId) {
        QueryWrapper<Link> linkQueryWrapper = new QueryWrapper<>();
        linkQueryWrapper.eq("video_id",videoId);
        return linkMapper.selectCount(linkQueryWrapper);
    }

    @Transactional
    @Override
    public void deleteAllLinksByVideoId(Integer videoId) {
        QueryWrapper<Link> linkQueryWrapper = new QueryWrapper<>();
        linkQueryWrapper.eq("video_id",videoId);
        linkMapper.delete(linkQueryWrapper);
    }

}
