package org.wys.live.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.wys.live.domain.po.Collection;
import org.wys.live.domain.po.User;

import java.util.List;

/**
 * 用户的数据访问层
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
