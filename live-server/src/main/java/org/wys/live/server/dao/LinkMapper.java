package org.wys.live.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.wys.live.domain.po.Link;

/**
 * @author wys
 * @date 2021/2/3 22:30
 */
@Mapper
@Repository
public interface LinkMapper extends BaseMapper<Link> {
}
