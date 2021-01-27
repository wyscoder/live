package org.wys.live.server.service.impl;

import org.wys.live.domain.po.Categories;
import org.wys.live.server.dao.CategoryMapper;
import org.wys.live.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wys
 * @date 2019/10/31
 * 业务层实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper categoryMapper;

    /**
     * set注入值
     * @param categoryMapper
     */
    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper){
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Integer selectIdByName(String name) {
        return categoryMapper.selectIdByName(name);
    }

    @Override
    public List<Categories> selectAllCategory() {
        return categoryMapper.selectAllCategory();
    }

    @Override
    public void updateCategoriesCount(String name) {
        categoryMapper.updateCategoriesCount(name);
    }
}