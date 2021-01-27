package org.wys.live.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wys.live.domain.po.RetResponse;
import org.wys.live.domain.po.RetResult;
import org.wys.live.domain.po.Video;
import org.wys.live.server.service.UserService;
import org.wys.live.server.service.VideoService;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private UserService userService;


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
    public RetResult delete(Integer id){
        try{
            String name = videoService.selectVideoById(id).getTitle();
            videoService.deleteAllLinksByPid(id);
            videoService.deleteVideoById(id);
            userService.deleteCollectionByVideoId(id);

            String path = "";
            String os = System.getProperty("os.name");
            if(os.toLowerCase().startsWith("win")){
                path = System.getProperty("user.dir")+"/src/main/resources/static/images/video/"+name+".jpg";
            }else{
                path = System.getProperty("user.dir")+"/video/"+name+".jpg";
            }
            File file = new File(path);
            if(file.exists()){
                file.delete();
            }
            return RetResponse.makeOKRsp();
        }catch (Exception e){
            return RetResponse.makeErrRsp(e.getMessage());
        }
    }


}
