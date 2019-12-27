package cn.wys.live.service;

import cn.wys.live.beans.User;

/**
 * 用户的业务层
 */
public interface UserService {


    User selectUserByUserName(String username);

    void insertUser(User user);


}
