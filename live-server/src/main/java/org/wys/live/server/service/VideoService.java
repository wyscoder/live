package org.wys.live.server.service;

import org.wys.live.domain.po.Link;
import org.wys.live.domain.po.Video;

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
     * 向数据库插入影视信息
     * @param video 影视
     */
    void insertVideo(Video video);

    /**
     * 根据姓名查询影视
     * @param title 姓名
     * @return 查询所有匹配的姓名
     */
    List<Video> selectVideoByName(String title);

    void deleteVideoById(Integer id);

    Video selectVideoById(Integer id);
}
