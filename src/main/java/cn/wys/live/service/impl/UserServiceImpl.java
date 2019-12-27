package cn.wys.live.service.impl;

import cn.wys.live.beans.User;
import cn.wys.live.mapper.UserMapper;
import cn.wys.live.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByUserName(String username) {
        return userMapper.selectUserByUserName(username);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

}
