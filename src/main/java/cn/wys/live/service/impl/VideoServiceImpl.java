package cn.wys.live.service.impl;

import cn.wys.live.beans.Video;
import cn.wys.live.mapper.VideoMapper;
import cn.wys.live.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wys
 * @date 2019/11/9
 * 影视的业务层实现类
 */
@Service("videoService")
public class VideoServiceImpl implements VideoService {

    private VideoMapper videoMapper;

    @Autowired
    public void getVideoMapper(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    @Override
    public List<Video> selectAllVideo() {
        return videoMapper.selectAllVideo();
    }

    @Override
    public void insertVideo(Video video) {
        videoMapper.insertVideo(video);
    }
}
