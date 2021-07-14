package org.wys.live.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.wys.live.domain.po.Categories;

import java.util.List;

/**
 * @author wys
 * @date 2019/10/31
 * 分类的映射接口 相当于持久层
 */
@Mapper
@Repository
public interface CategoryMapper extends BaseMapper<Categories> {

}
