package org.wys.live.server.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.wys.live.domain.po.Link;
import org.wys.live.domain.po.Video;

import java.util.List;

/**
 * @author wys
 * @date 2019/11/9
 * 影视的持久层
 */

@Component
@Mapper
public interface VideoMapper {

    /**
     * 查询所有影视
     * @return 返回的是查找的所有影视
     */
    @Select("select * from video")
    List<Video> selectAllVideo();

    /**
     * 向数据库插入影视信息
     * @param video 影视
     */
    @Insert("insert into video(title,director,stars,categories,country,status,year,content) values(#{title},#{director},#{stars},#{categories},#{country},#{status},#{year},#{content})")
    void insertVideo(Video video);

    /**
     * 根据姓名查询影视
     * @param title 姓名
     * @return 查询所有匹配的姓名
     */
    @Select("select * from video where title = #{title}")
    List<Video> selectVideoByName(String title);

    /**
     * 向数据库插入链接信息
     * @param link 链接
     */
    @Insert("insert into link(seq,name,link,pid) values(#{seq},#{name},#{link},#{pid})")
    void insertLink(Link link);

    /**
     * 根据链接查询播放链接
     * @param pid 视频链接
     * @return 返回的是所有播放链接
     */
    @Select("select * from link where pid = #{pid}")
    List<Link> selectAllLinksByVideo(Integer pid);

    /**
     * 根据视频id号和视频的序列号决定一个播放链接
     * @param pid 视频id
     * @param seq 视频序列号
     * @return 返回的是唯一播放链接
     */
    @Select("select * from link where pid = #{pid} and seq = #{seq}")
    Link selectLinkByVideoAndSeq(Integer pid,Integer seq);

    /**
     * 获取此视频的集数
     * @param pid 视频id
     * @return 返回的是集数
     */
    @Select("select count(*) from link where pid = #{pid}")
    Integer selectLinkByVideoCount(Integer pid);

    /**
     * 删除此视频的所有链接
     * @param pid 视频id
     */
    @Delete("delete from link where pid = #{pid}")
    void deleteAllLinksByPid(Integer pid);

    /**
     * 根据id删除视频信息
     * @param id 视频id
     */
    @Delete("delete from video where id = #{id}")
    void deleteVideoById(Integer id);

    /**
     * 根据id查找视频
     * @param id 视频id
     * @return 返回的查找到的视频
     */
    @Select("select * from video where id = #{id}")
    Video selectVideoById(Integer id);
}
