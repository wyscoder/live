package cn.wys.live.service;


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
}
