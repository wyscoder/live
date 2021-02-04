package org.wys.live.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.wys.live.domain.po.Link;
import org.wys.live.server.service.LinkService;
import org.wys.live.video.piaohua.PiaoHuaVideoSourceHandle;
import org.wys.live.web.response.RetResponse;
import org.wys.live.web.response.RetResult;
import org.wys.live.domain.po.Video;
import org.wys.live.server.service.VideoService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 视频上传的控制器
 */

@RestController
@AllArgsConstructor
@Slf4j
public class UploadController {

    private final PiaoHuaVideoSourceHandle piaoHuaVideoSourceHandle;
    private final VideoService videoService;
    private final LinkService linkService;


    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/upload_video")
    public RetResult<Integer> uploadVideo(String name, HttpSession session){
        try {
            Video video = new Video();
            List<Video> v = videoService.selectVideoByName(name);
            //如果视频有问题就把视频删了
            if(v!=null&&v.size()>0&&linkService.selectAllLinksByVideoId(v.get(0).getId()).size()<=0){
                videoService.deleteVideoById(v.get(0).getId());
            }
            session.setAttribute("process",0);
            String link = piaoHuaVideoSourceHandle.getSearchTitleLink(name);
            session.setAttribute("process",(int)session.getAttribute("process")+15);
            piaoHuaVideoSourceHandle.DownLoadVideoImageByLink(link,name);
            session.setAttribute("process",(int)session.getAttribute("process")+15);
            Map<String,String> map = piaoHuaVideoSourceHandle.getVideoMessage(link);
            session.setAttribute("process",(int)session.getAttribute("process")+15);
            video.setTitle(name);
            video.setDirector(map.get("导演"));
            video.setStars(map.get("主演"));
            video.setCategories(map.get("类型"));
            video.setContent(map.get("剧情"));
            video.setStatus(map.get("更新状态"));
            video.setCountry(map.get("制片国家"));
            video.setYear(map.get("上映日期"));
            videoService.insertVideo(video);
            int id = videoService.selectVideoByName(name).get(0).getId();
            session.setAttribute("process",(int)session.getAttribute("process")+15);
            List<String> links = piaoHuaVideoSourceHandle.getVideoLinks(link);
            session.setAttribute("process",(int)session.getAttribute("process")+15);
            for(int i=0;i<links.size();i++){
                Link l = new Link();
                l.setLink(links.get(i));
                l.setVideoId(id);
                l.setSeq(i);
                l.setName("第"+i+"集");
                linkService.insertLink(l);
            }
            session.setAttribute("process",(int)session.getAttribute("process")+25);
        } catch (Exception e) {
            session.setAttribute("process","0");
            log.error("[upload exception] upload exception ======> " ,e);
            return RetResponse.makeErrRsp(e.getMessage());
        }
        return RetResponse.makeOKRsp();
    }
    @PostMapping("/getProcess")
    public RetResult<Object> getProcess(HttpSession session){
        return RetResponse.makeOKRsp(session.getAttribute("process"));
    }
}
