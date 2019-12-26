package cn.wys.live.service;


import cn.wys.live.beans.Categories;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wys
 * @date 2019/10/31
 * 分类的业务层接口
 */
public interface CategoryService {

    /**
     * 根据姓名查找id
     * @param name
     * @return
     */
    Integer selectIdByName(String name);

    List<Categories> selectAllCategory();

    void updateCategoriesCount(String name);
}
