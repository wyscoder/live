package cn.wys.live.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author wys
 * @date 2019/10/31
 *  分类的映射接口 相当于持久层
 */
@Mapper
@Component
public interface CategoryMapper {

    /**
     * 根据姓名查找id
     * @param name 姓名
     * @return 返回的是id
     */
    @Select("select id from categories where name = #{name}")
    Integer selectIdByName(@Param("name") String name);
}
