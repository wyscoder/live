package cn.wyscoder.live.service.impl;

import cn.wyscoder.live.beans.Collection;
import cn.wyscoder.live.beans.User;
import cn.wyscoder.live.beans.Video;
import cn.wyscoder.live.mapper.UserMapper;
import cn.wyscoder.live.mapper.VideoMapper;
import cn.wyscoder.live.service.UserService;
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
