package cn.wys.live.controller;

import cn.wys.live.beans.Page;
import cn.wys.live.beans.RetCode;
import cn.wys.live.beans.RetResponse;
import cn.wys.live.beans.RetResult;
import cn.wys.live.service.CategoryService;
import cn.wys.live.utils.hy.HyLiveLink;
import cn.wys.live.utils.hy.HyLiveUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;

import javax.xml.ws.Response;
import java.io.IOException;

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
    @RequestMapping(value = "/categories/{category}", method = RequestMethod.GET)
    public String categories(@PathVariable(value = "category") String myCategories, Model model) throws Exception{
        Integer id = categoryService.selectIdByName(myCategories);

        try{
            model.addAttribute("myCategory", myCategories);
            model.addAttribute("page", HyLiveUtils.getAllAnchor(id, 1));
        }catch (IOException e){
            e.printStackTrace();
        }

        return "categories";
    }

    /**
     * 这个就是使用ajax处理分页信息的类
     * @param myCategories
     * @param pageId
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/categories/{category}", method = RequestMethod.POST)
    public RetResult<Page> listcategoriesPage(@PathVariable(value = "category") String myCategories, @RequestParam(value = "pageid") Integer pageId, Model model) {
        Integer id = categoryService.selectIdByName(myCategories);

        try{
            return RetResponse.makeOKRsp(HyLiveUtils.getAllAnchor(id, pageId));
        }catch (IOException e){
            return RetResponse.makeRsp(RetCode.FAIL.code, "FAIL");
        }

    }

    @RequestMapping(value = "/live/{roomid}", method = RequestMethod.GET)
    public String playLive(@PathVariable(value = "roomid")String roomId, Model model) throws Exception{


        String[] ss = HyLiveLink.getLink(roomId);
        model.addAttribute("title", ss[0]);
        model.addAttribute("liveLink", ss[1]);

        return "playlive";
    }
}
