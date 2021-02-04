package org.wys.live.web.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wys.live.domain.po.Collection;
import org.wys.live.domain.po.Link;
import org.wys.live.domain.po.Video;
import org.wys.live.server.service.CollectionService;
import org.wys.live.server.service.LinkService;
import org.wys.live.server.service.UserService;
import org.wys.live.server.service.VideoService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wys
 * @date 2019/11/9
 * 这个就是视图映射器
 */
@Controller
@AllArgsConstructor
@Slf4j
public class VideoController {

    private final VideoService videoService;
    private final UserService userService;
    private final LinkService linkService;
    private final CollectionService collectionService;


    @RequestMapping("/movie")
    public String movie(Model model) {

        List<Video> videos = videoService.selectAllVideo();
        model.addAttribute("videos",videos);
        String path = System.getProperty("user.dir")+"\\video\\";
        System.out.println(videos);
        model.addAttribute("path",path);

        return "movie";
    }

    @RequestMapping("/detail")
    public String detail(String title, Model model, HttpSession session) {

        Video video = videoService.selectVideoByName(title).get(0);
        Integer count = linkService.selectLinkByVideoCount(video.getId());
        List list = new ArrayList();
        for(int i=1;i<=count;i++){
            list.add(i);
        }
        Integer id = (Integer)session.getAttribute("id");
        Collection collection = collectionService.selectCollectionByVideoIdAndPid(video.getId(),id);
        System.out.println(collection);
        if(collection != null){
            model.addAttribute("collection",1);
        }else{
            model.addAttribute("collection",0);
        }
        model.addAttribute("video_id",video.getId());
        model.addAttribute("title",video.getTitle());
        model.addAttribute("director",video.getDirector());
        model.addAttribute("stars",video.getStars());
        model.addAttribute("categories",video.getCategories());
        model.addAttribute("country",video.getCountry());
        model.addAttribute("status",video.getStatus());
        model.addAttribute("year",video.getYear());
        model.addAttribute("content",video.getContent());
        model.addAttribute("list",list);
        return "detail";
    }

    /**
     * 请求到集数和视频名称
     * @param title 视频名称
     * @param seq 序列id
     * @return 返回的跳转的链接
     */
    @RequestMapping("/play_video")
    public String playVideo(String title,Integer seq,Model model) {
        seq--;
        Integer pid = videoService.selectVideoByName(title).get(0).getId();
        Link link = linkService.selectLinkByVideoAndSeq(pid,seq);
        model.addAttribute("link",link.getLink());
        model.addAttribute("title",title+"第"+(seq+1)+"集");
        return "play_hls";
    }

    /**
     * 播放链接
     * @param link 链接
     * @return 跳转的链接
     */
    @RequestMapping("/playLink")
    public String playLink(String link,Model model) {
        model.addAttribute("link",link);
        model.addAttribute("title","正在播放");
        System.out.println(link);
        String suffix = "";
        //判断一下有没有问号
        if(link.contains("?")){
            suffix = link.substring(link.indexOf(".")+1,link.indexOf("?")-1);
        }else{
            suffix = link.substring(link.indexOf(".")+1,link.length());
        }
        if(suffix.equals("flv")){
            return "play_flv";
        }else if(suffix.equals("m3u8")){
            return "play_hls";
        }else{
            return "play_all";
        }
    }
}
