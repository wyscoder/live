package cn.wys.live.controller;

import cn.wys.live.beans.Collection;
import cn.wys.live.beans.RetResponse;
import cn.wys.live.beans.RetResult;
import cn.wys.live.beans.Video;
import cn.wys.live.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户的控制器
 * 负责跳转和显示信息以及与后台交互
 */
@Controller
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * 收藏
     * @return 返回的是是否收藏成功的信息
     */
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @PostMapping("/collection")
    public RetResult setCollection(Integer videoId, HttpSession session){

        try{
            Integer id = (Integer)session.getAttribute("id");
            Collection collection = new Collection();
            collection.setPid(id);
            collection.setVideoId(videoId);
            userService.insertCollection(collection);
            return RetResponse.makeOKRsp();
        }catch (Exception e){
            return RetResponse.makeErrRsp(e.getMessage());
        }

    }


    /**
     * 展示所有的收藏信息
     * @param model 传值
     * @return 返回的是跳转的页面
     */
    @RequestMapping("/user")
    public String userMsg(Model model,HttpSession session){

        Integer id = (Integer)session.getAttribute("id");

        List<Video> videoList = userService.selectCollectionVideoById(id);

        if(videoList == null){
            model.addAttribute("videoList","null");
        }else{
            model.addAttribute("videoList",videoList);
        }

        return "user_msg";
    }


    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/delete_collection")
    @ResponseBody
    public RetResult deleteCollection(Integer videoId,HttpSession session) {
        try{
            Integer id = (Integer)session.getAttribute("id");
            userService.deleteCollectionByIdAndPid(videoId,id);
            return RetResponse.makeOKRsp();
        }catch (Exception e){
            return RetResponse.makeErrRsp(e.getMessage());
        }
    }
}
