package cn.wys.live.service.impl;

import cn.wys.live.beans.Collection;
import cn.wys.live.beans.User;
import cn.wys.live.beans.Video;
import cn.wys.live.mapper.UserMapper;
import cn.wys.live.mapper.VideoMapper;
import cn.wys.live.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public User selectUserByUserName(String username) {
        return userMapper.selectUserByUserName(username);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public List<Collection> selectCollectionByUserId(Integer id) {
        return userMapper.selectCollectionByUserId(id);
    }

    @Override
    public void insertCollection(Collection collection) {
        userMapper.insertCollection(collection);
    }

    @Override
    public List<Video> selectCollectionVideoById(Integer id) {
        List<Collection> collections = selectCollectionByUserId(id);
        List<Video> videoList = new ArrayList<>();
        for(Collection c : collections){
            videoList.add(videoMapper.selectVideoById(c.getVideoId()));
        }
        return videoList;
    }

    @Override
    public void deleteCollectionByIdAndPid(Integer id, Integer pid) {
        userMapper.deleteCollectionByIdAndPid(id,pid);
    }

    @Override
    public Collection selectCollectionByVideoIdAndPid(Integer id, Integer pid) {
        return userMapper.selectCollectionByVideoIdAndPid(id,pid);
    }

    @Override
    public void deleteCollectionByVideoId(Integer id) {
        userMapper.deleteCollectionByVideoId(id);
    }
}
