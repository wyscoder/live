package org.wys.live.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.wys.live.domain.po.Categories;
import org.wys.live.hy.utils.HyLiveUtils;
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
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    @Override
    public Integer selectIdByName(String name) {
        QueryWrapper<Categories> categoriesQueryWrapper = new QueryWrapper<>();
        categoriesQueryWrapper.eq("name",name);
        return categoryMapper.selectOne(categoriesQueryWrapper).getUid();
    }

    @Override
    public List<Categories> selectAllCategory() {
        return categoryMapper.selectList(null);
    }

    @Override
    @Transactional
    public void updateCategoriesCount(String name) {
        QueryWrapper<Categories> categoriesQueryWrapper = new QueryWrapper<>();
        categoriesQueryWrapper.eq("name",name);
        Categories categories = categoryMapper.selectOne(categoriesQueryWrapper);
        categories.setCount(categories.getCount()+1);
        categoryMapper.updateById(categories);
    }

    @Override
    @Transactional
    public void insertAllCategories() {
        List<Categories> hyLiveCategories = HyLiveUtils.getHyLiveCategories();
        if(!CollectionUtils.isEmpty(hyLiveCategories)) {
            hyLiveCategories.forEach(item->{
                categoryMapper.insert(item);
                log.info("[categories] insert categories success =========> {}", item);
            });
        }
    }
}
