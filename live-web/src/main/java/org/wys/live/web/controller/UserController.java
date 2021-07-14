package org.wys.live.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wys.live.domain.po.Collection;
import org.wys.live.server.service.CollectionService;
import org.wys.live.web.response.RetResponse;
import org.wys.live.web.response.RetResult;
import org.wys.live.domain.po.Video;
import org.wys.live.server.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户的控制器
 * 负责跳转和显示信息以及与后台交互
 */
@Controller
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final CollectionService collectionService;

    /**
     * 收藏
     *
     * @return 返回的是是否收藏成功的信息
     */
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @PostMapping("/collection")
    public RetResult setCollection(Integer videoId, HttpSession session) {

        try {
            Integer id = (Integer) session.getAttribute("id");
            Collection collection = new Collection();
            collection.setPid(id);
            collection.setVideoId(videoId);
            collectionService.insertCollection(collection);
            return RetResponse.makeOKRsp();
        } catch (Exception e) {
            return RetResponse.makeErrRsp(e.getMessage());
        }

    }


    /**
     * 展示所有的收藏信息
     *
     * @param model 传值
     * @return 返回的是跳转的页面
     */
    @RequestMapping("/user")
    public String userMsg(Model model, HttpSession session) {

        Integer id = (Integer) session.getAttribute("id");

        List<Video> videoList = collectionService.selectCollectionVideoById(id);

        if (videoList == null) {
            model.addAttribute("videoList", "null");
        } else {
            model.addAttribute("videoList", videoList);
        }

        return "user_msg";
    }


    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/delete_collection")
    @ResponseBody
    public RetResult<Boolean> deleteCollection(Integer videoId, HttpSession session) {
        try {
            Integer id = (Integer) session.getAttribute("id");
            collectionService.selectCollectionByVideoIdAndPid(videoId, id);
            return RetResponse.makeOKRsp();
        } catch (Exception e) {
            return RetResponse.makeErrRsp(e.getMessage());
        }
    }
}
