package org.wys.live.web.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wys.live.domain.po.*;
import org.wys.live.hy.utils.HyLiveUtils;
import org.wys.live.server.service.CategoryService;
import org.wys.live.web.response.RetCode;
import org.wys.live.web.response.RetResponse;
import org.wys.live.web.response.RetResult;


import java.io.IOException;
import java.util.List;

/**
 * @author wys
 * @date 2019/10/31
 * 分类控制器
 */
@Controller
public class LiveController {

    private CategoryService categoryService;


    /**
     * 给CategoryService注入值
     *
     * @param categoryService
     */
    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/live")
    public String jumpLiveCategories(Model model) {

        List<Categories> categoriesList = categoryService.selectAllCategory();
        model.addAttribute("categoriesList", categoriesList);

        return "live";
    }


    /**
     * 解析收到的图片类别，然后检索数据库
     *
     * @param myCategories
     * @return
     */
    @RequestMapping(value = "/categories/{category}", method = RequestMethod.GET)
    public String categories(@PathVariable(value = "category") String myCategories, Model model) throws Exception {
        Integer id = categoryService.selectIdByName(myCategories);

        categoryService.updateCategoriesCount(myCategories);
        try {
            model.addAttribute("myCategory", myCategories);
            model.addAttribute("page", HyLiveUtils.getAllAnchor(id, 1));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "categories";
    }

    /**
     * 这个就是使用ajax处理分页信息的类
     *
     * @param myCategories
     * @param pageId
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/categories/{category}", method = RequestMethod.POST)
    public RetResult<Page> listcategoriesPage(@PathVariable(value = "category") String myCategories, @RequestParam(value = "pageid") Integer pageId, Model model) {
        Integer id = categoryService.selectIdByName(myCategories);

        try {
            return RetResponse.makeOKRsp(HyLiveUtils.getAllAnchor(id, pageId));
        } catch (IOException e) {
            return RetResponse.makeRsp(RetCode.FAIL.code, "FAIL");
        }

    }

    @RequestMapping(value = "/live/{roomid}", method = RequestMethod.GET)
    public String playLive(@PathVariable(value = "roomid") String roomId, Model model) throws Exception {


        Object[] ss = HyLiveUtils.getLink(roomId);
        model.addAttribute("title", (String) ss[0]);
        String[] links = (String[]) ss[1];
        JSONObject[] array = new JSONObject[links.length];
        for (int i = 0; i < links.length; i++) {
            JSONObject obj = new JSONObject();
            obj.put("url", links[i]);
            obj.put("name", "路线" + i);
            //System.out.println(obj);
            array[i] = obj;
        }
        model.addAttribute("liveLink", array);

        return "playlive";
    }
}
