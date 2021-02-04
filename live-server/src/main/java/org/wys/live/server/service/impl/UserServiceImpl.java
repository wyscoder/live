package org.wys.live.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.wys.live.domain.po.Collection;
import org.wys.live.domain.po.User;
import org.wys.live.domain.po.Video;
import org.wys.live.server.dao.UserMapper;
import org.wys.live.server.dao.VideoMapper;
import org.wys.live.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final VideoMapper videoMapper;

    @Override
    public User selectUserByUserName(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",username);
        return userMapper.selectOne(userQueryWrapper);
    }

    @Override
    @Transactional
    public void insertUser(User user) {
        userMapper.insert(user);
    }
}
