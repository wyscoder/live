package cn.wys.live.controller;

import cn.wys.live.service.CategoryService;
import cn.wys.live.utils.hy.HyLiveUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URLEncoder;

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
    @RequestMapping("/categories/{category}/{pageid}")
    public String categories(@PathVariable(value = "category") String myCategories, @PathVariable(value = "pageid") Integer pageId, Model model) {
        Integer id = categoryService.selectIdByName(myCategories);
        System.out.println(pageId);
        try{
            model.addAttribute("myCategory", myCategories);
            model.addAttribute("page", HyLiveUtils.getAllAnchor(id, pageId));
        }catch (IOException e){
            e.printStackTrace();
        }

        return "categories";
    }

}
