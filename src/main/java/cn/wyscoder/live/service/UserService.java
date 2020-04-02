package cn.wyscoder.live.service;

import cn.wyscoder.live.beans.Collection;
import cn.wyscoder.live.beans.User;
import cn.wyscoder.live.beans.Video;

import java.util.List;

/**
 * 用户的业务层
 */
public interface UserService {


    User selectUserByUserName(String username);

    void insertUser(User user);

    List<Collection> selectCollectionByUserId(Integer id);

    void insertCollection(Collection collection);

    List<Video> selectCollectionVideoById(Integer id);

    void deleteCollectionByIdAndPid(Integer id, Integer pid);

    Collection selectCollectionByVideoIdAndPid(Integer id, Integer pid);

    void deleteCollectionByVideoId(Integer id);

}
