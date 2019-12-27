package cn.wys.live.mapper;

import cn.wys.live.beans.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

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



}
