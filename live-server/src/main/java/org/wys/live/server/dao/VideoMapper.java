package org.wys.live.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.wys.live.domain.po.Link;
import org.wys.live.domain.po.Video;

import java.util.List;

/**
 * @author wys
 * @date 2019/11/9
 * 影视的持久层
 */
@Mapper
@Repository
public interface VideoMapper extends BaseMapper<Video> {

}
