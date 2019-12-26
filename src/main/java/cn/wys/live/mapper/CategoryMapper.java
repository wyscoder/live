package cn.wys.live.mapper;

import cn.wys.live.beans.Categories;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

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

    /**
     * 查询所有名称的直播类型按照点击量从大到小排序
     * @return 所有名称的直播类型
     */
    @Select("select * from categories order by count desc")
    List<Categories> selectAllCategory();

    @Update("update categories set count = count+1 where name = #{name}")
    void updateCategoriesCount(@Param("name") String name);
}
