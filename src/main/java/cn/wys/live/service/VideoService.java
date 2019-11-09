package cn.wys.live.service;

import cn.wys.live.beans.Video;

import java.util.List;

/**
 * @author wys
 * @date 2019/11/9
 * 影视的业务层
 */
public interface VideoService {

    /**
     * 查询所有影视
     * @return 返回的是查找的所有影视
     */
    List<Video> selectAllVideo();

    /**
     * 向数据库中插入影视信息
     */
    void insertVideo(Video video);

}
