package org.wys.live.server.service;

import org.wys.live.domain.po.Collection;
import org.wys.live.domain.po.User;
import org.wys.live.domain.po.Video;

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
