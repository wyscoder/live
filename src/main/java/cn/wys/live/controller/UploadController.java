package cn.wys.live.controller;

import cn.wys.live.beans.RetResponse;
import cn.wys.live.beans.RetResult;
import cn.wys.live.utils.video.VideoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 视频上传的控制器
 */
@RestController
public class UploadController {

    @Autowired
    private VideoUtils videoUtils;
    @GetMapping("/upload_video")
    public RetResult uploadVideo(String name){

        return RetResponse.makeOKRsp();
    }

}
