package org.wys.live.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.wys.live.domain.po.Link;
import org.wys.live.domain.po.Video;
import org.wys.live.server.dao.VideoMapper;
import org.wys.live.server.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wys
 * @date 2019/11/9
 * 影视的业务层实现类
 */
@Service
@AllArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService {

    private final VideoMapper videoMapper;

    @Override
    public List<Video> selectAllVideo() {
        return videoMapper.selectList(null);
    }

    @Override
    @Transactional
    public void insertVideo(Video video) {
        videoMapper.insert(video);
    }

    @Override
    public List<Video> selectVideoByName(String title) {
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("title", title);
        return videoMapper.selectList(videoQueryWrapper);
    }

    @Override
    @Transactional
    public void deleteVideoById(Integer id) {
        videoMapper.deleteById(id);
    }

    @Override
    public Video selectVideoById(Integer id) {
        return videoMapper.selectById(id);
    }
}
