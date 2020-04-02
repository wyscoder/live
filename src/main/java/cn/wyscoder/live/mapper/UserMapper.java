package cn.wyscoder.live.mapper;

import cn.wyscoder.live.beans.Collection;
import cn.wyscoder.live.beans.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户的数据访问层
 */
@Mapper
@Component
public interface UserMapper {


    /**
     * 查询用户的帐号密码是否正确
     * @param username 账号
     * @return 返回的是帐号密码
     */
    @Select("select * from user where username = #{username}")
    User selectUserByUserName(String username);

    /**
     * 向数据库插入用户
     * @param user 用户
     */
    @Insert("insert into user(username,password,name,mail) values(#{username},#{password},#{name},#{mail})")
    void insertUser(User user);


    /**
     * 插入收藏信息
     * @param collection 收藏的信息
     */
    @Insert("insert into collection(video_id,pid) value(#{videoId},#{pid})")
    void insertCollection(Collection collection);


    /**
     * 查询用户收藏的视频
     * @param id 用户id
     * @return 查找到所有的视频
     */
    @Select("select * from collection where pid = #{id}")
    List<Collection> selectCollectionByUserId(Integer id);

    /**
     * 删除收藏视频
     * @param id 视频id
     * @param pid 用户id
     */
    @Delete("delete from collection where video_id = #{id} and pid = #{pid}")
    void deleteCollectionByIdAndPid(Integer id, Integer pid);

    /**
     * 查找指定的视频
     * @param id 视频id
     * @param pid 用户id
     * @return 查询结果
     */
    @Select("select * from collection where video_id = #{id} and pid = #{pid}")
    Collection selectCollectionByVideoIdAndPid(Integer id, Integer pid);

    /**
     * 删除跟这个视频有关的收藏记录
     * @param id 视频id
     */
    @Delete("delete from collection where video_id = #{id}")
    void deleteCollectionByVideoId(Integer id);

}
