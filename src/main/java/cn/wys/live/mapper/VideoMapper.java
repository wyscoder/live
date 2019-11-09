package cn.wys.live.mapper;

import cn.wys.live.beans.Video;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

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
     * 向数据库中插入影视信息
     */
    @Insert("insert into video(name,category,year,link,content,address,p) values(#{name},#{category},#{year},#{link},#{content},#{address},#{p})")
    void insertVideo(Video video);

}
