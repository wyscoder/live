package cn.wys.live.service;

import cn.wys.live.beans.Link;
import cn.wys.live.beans.Video;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 向数据库插入链接信息
     * @param link 链接
     */
    void insertLink(Link link);

    /**
     * 根据链接查询播放链接
     * @param pid 视频链接
     * @return 返回的是所有播放链接
     */
    List<Link> selectAllLinksByVideo(Integer pid);

    Link selectLinkByVideoAndSeq(Integer pid,Integer seq);

    Integer selectLinkByVideoCount(Integer pid);

    void deleteAllLinksByPid(Integer pid);

    void deleteVideoById(Integer id);

    Video selectVideoById(Integer id);
}
