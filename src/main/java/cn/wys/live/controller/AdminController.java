package cn.wys.live.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/upload")
    public String upload_video(){
        return "admin/upload_video";
    }
    @RequestMapping("/manage")
    public String manage(){
        return "admin/manage_video";
    }
    @RequestMapping("/delete")
    public String delete_video() {
        return "admin/delete_video";
    }



}
