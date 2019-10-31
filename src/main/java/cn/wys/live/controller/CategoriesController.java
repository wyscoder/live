package cn.wys.live.controller;

import cn.wys.live.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wys
 * @date 2019/10/31
 * 分类控制器
 */
@Controller
public class CategoriesController {

    private CategoryService categoryService;

    /**
     * 给CategoryService注入值
     * @param categoryService
     */
    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 解析收到的图片类别，然后检索数据库
     * @param myCategories
     * @return
     */
    @RequestMapping("/categories/{category}")
    public String categories(@PathVariable(value = "category") String myCategories, Model model) {
        String id = String.valueOf(categoryService.selectIdByName(myCategories));

        model.addAttribute("myCategoty",myCategories);


        System.out.println(id);
        return "categories";
    }
}
