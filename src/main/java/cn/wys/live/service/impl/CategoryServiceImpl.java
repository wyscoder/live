package cn.wys.live.service.impl;

import cn.wys.live.mapper.CategoryMapper;
import cn.wys.live.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wys
 * @date 2019/10/31
 * 业务层实现类
 */
@Service("categorityService")
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
}
