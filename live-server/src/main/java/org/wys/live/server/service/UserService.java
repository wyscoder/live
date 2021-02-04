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
}
