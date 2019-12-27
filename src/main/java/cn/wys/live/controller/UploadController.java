package cn.wys.live.controller;

import cn.wys.live.beans.Link;
import cn.wys.live.beans.RetResponse;
import cn.wys.live.beans.RetResult;
import cn.wys.live.beans.Video;
import cn.wys.live.mapper.VideoMapper;
import cn.wys.live.service.VideoService;
import cn.wys.live.utils.video.VideoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 视频上传的控制器
 */
@RestController
public class UploadController {

    @Autowired
    private VideoUtils videoUtils;

    @Autowired
    private VideoService videoService;

    @Transactional
    @GetMapping("/upload_video")
    public RetResult uploadVideo(String name,HttpSession session){
        try {

            Video video = new Video();

            session.setAttribute("process",0);

            String link = videoUtils.getSeachTitleLink(name);
            session.setAttribute("process",(int)session.getAttribute("process")+15);

            videoUtils.DownLoadVideoImageByLink(link,name);
            session.setAttribute("process",(int)session.getAttribute("process")+15);

            Map<String,String> map = videoUtils.getVideoMessage(link);
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


            List<String> links = videoUtils.getVideoLinks(link);
            session.setAttribute("process",(int)session.getAttribute("process")+15);

            for(int i=0;i<links.size();i++){
                Link l = new Link();
                l.setLink(links.get(i));
                l.setPid(id);
                l.setSeq(i);
                l.setName("第"+i+"集");
                videoService.insertLink(l);
            }

            session.setAttribute("process",(int)session.getAttribute("process")+25);

        } catch (Exception e) {
            session.setAttribute("process","0");
            return RetResponse.makeErrRsp(e.getMessage());
        }
        return RetResponse.makeOKRsp();
    }
    @PostMapping("/getProcess")
    public RetResult getProcess(HttpSession session){
        return RetResponse.makeOKRsp(session.getAttribute("process"));
    }
}
