package cn.wys.live;

import cn.wys.live.beans.Video;
import cn.wys.live.service.VideoService;
import cn.wys.live.utils.hy.HyLiveLink;
import cn.wys.live.utils.hy.HyLiveUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class LiveApplicationTests {

    @Autowired
    private VideoService videoService;
    @Test
    void contextLoads() {
    }

    @Test
    void testvideo() throws Exception {
        String url = "https://51kdy.org/voddetail/liangjian/index.html";
        Video video = new Video();
        Document document = Jsoup.connect(url).validateTLSCertificates(false).get();

        System.out.println(document.title().substring(0,document.title().indexOf("_")));

        Elements elements = document.getElementsByClass("fed-col-xs6 fed-part-eone");
        List<Object> list = new ArrayList<>();
        for(Element e : elements) {
            list.add(e.text().substring(e.text().indexOf("：")+1,e.text().length()));
        }
        //名称
        video.setName(document.title().substring(0,document.title().indexOf("_")));
        //分类
        video.setCategory((String)list.get(0));
        //所属类别
        video.setP(1);
        //地区
        video.setAddress((String)list.get(1));
        //年份
        video.setYear(Integer.valueOf((String)list.get(2)));
        video.setLink("null");
        videoService.insertVideo(video);
    }


}
