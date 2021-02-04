package org.wys.live.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wys.live.server.service.CollectionService;
import org.wys.live.server.service.LinkService;
import org.wys.live.web.response.RetResponse;
import org.wys.live.web.response.RetResult;
import org.wys.live.domain.po.Video;
import org.wys.live.server.service.UserService;
import org.wys.live.server.service.VideoService;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
@Slf4j
public class AdminController {

    private final VideoService videoService;
    private final UserService userService;
    private final LinkService linkService;
    private final CollectionService collectionService;


    @RequestMapping("/upload")
    public String upload_video(){
        return "admin/upload_video";
    }
    @RequestMapping("/manage")
    public String manage(){
        return "admin/manage_video";
    }
    @RequestMapping("/delete")
    public String delete_video(Model model) {

        List<Video> videos = videoService.selectAllVideo();
        model.addAttribute("videos",videos);
        return "admin/delete_video";
    }
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("/delete_video")
    public RetResult<Boolean> delete(Integer videoId){
        try{
            String name = videoService.selectVideoById(videoId).getTitle();
            linkService.deleteAllLinksByVideoId(videoId);
            videoService.deleteVideoById(videoId);
            collectionService.deleteCollectionByVideoId(videoId);

            String path = "";
            String os = System.getProperty("os.name");
            if(os.toLowerCase().startsWith("win")){
                path = System.getProperty("user.dir")+"/src/main/resources/static/images/video/"+name+".jpg";
            }else{
                path = System.getProperty("user.dir")+"/video/"+name+".jpg";
            }
            File file = new File(path);
            return RetResponse.makeOKRsp();
        }catch (Exception e){
            return RetResponse.makeErrRsp(e.getMessage());
        }
    }


}
