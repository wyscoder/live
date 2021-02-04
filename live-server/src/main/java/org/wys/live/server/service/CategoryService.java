package org.wys.live.server.service;



import org.wys.live.domain.po.Categories;

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

    void insertAllCategories();
}
