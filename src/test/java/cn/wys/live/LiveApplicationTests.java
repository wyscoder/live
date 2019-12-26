package cn.wys.live;

import cn.wys.live.beans.Link;
import cn.wys.live.beans.Video;
import cn.wys.live.constant.HeaderPiaohua;
import cn.wys.live.service.VideoService;
import cn.wys.live.utils.hy.HyLiveLink;
import cn.wys.live.utils.hy.HyLiveUtils;
import cn.wys.live.utils.video.VideoUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class LiveApplicationTests {

    @Autowired
    private VideoService videoService;
    void contextLoads() {
    }

    void testImage() throws Exception{

       VideoUtils v = new VideoUtils();
        v.DownLoadVideoImageByLink(v.getSeachTitleLink("琅琊榜"),"琅琊榜");
    }
    void testDel() throws Exception{
        videoService.deleteAllLinksByPid(videoService.selectVideoByName("琅琊榜").get(0).getId());
    }
    void testvideo() throws Exception {
        VideoUtils v = new VideoUtils();
        Video video = new Video();

        Map<String,String> map = v.getVideoMessage(v.getSeachTitleLink("琅琊榜"));
        List<String> links = v.getVideoLinks(v.getSeachTitleLink("琅琊榜"));
        /*System.out.println(map);
        video.setTitle("琅琊榜");
        video.setDirector(map.get("导演"));
        video.setStars(map.get("主演"));
        video.setCategories(map.get("类型"));
        video.setContent(map.get("剧情"));
        video.setStatus(map.get("更新状态"));
        video.setCountry(map.get("制片国家"));
        video.setYear(map.get("上映日期"));
        videoService.insertVideo(video);*/

        Integer id = videoService.selectVideoByName("琅琊榜").get(0).getId();
        for(int i=0;i<links.size();i++){
            Link link = new Link();
            link.setLink(links.get(i));
            link.setPid(id);
            link.setSeq(i);
            link.setName("第"+i+"集");
            videoService.insertLink(link);
        }
    }
}
