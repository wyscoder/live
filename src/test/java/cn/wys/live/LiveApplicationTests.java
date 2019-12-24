package cn.wys.live;

import cn.wys.live.beans.Video;
import cn.wys.live.constant.HeaderPiaohua;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class LiveApplicationTests {

    @Autowired
    private VideoService videoService;
    @Test
    void contextLoads() {
    }

    @Test
    void testvideo() throws Exception {
        Map<String,String> head = new HashMap<>();
        head.put("accept", HeaderPiaohua.ACCEPT);
        head.put("accept-encoding", HeaderPiaohua.ACCEPT_ENCODING);
        head.put("accept-language", HeaderPiaohua.ACCEPT_LANGUAGE);
        head.put("cookie", HeaderPiaohua.COOKIE);
        head.put("user-agent",HeaderPiaohua.USER_AGENT);
        String url = "http://www.piaohua520.com/?c=search&wd=琅琊榜";
        String u_url = "http://www.piaohua520.com";
        Video video = new Video();
        Document document = Jsoup.connect(url).headers(head).get();

        Element element = document.getElementsByClass("movie-item").get(1).getElementsByTag("a").get(0);
        String name = element.attr("title");
        String link = element.attr("href");

        //System.out.println(name+" "+link);

        document = Jsoup.connect(link).headers(head).get();
        //System.out.println(document.getElementsByClass("dslist-group").get(1));
        element = document.getElementsByClass("dslist-group").get(1);
        //System.out.println(element);
        Elements elements = element.getElementsByTag("li");
        int len = elements.size();
        //System.out.println(elements);
        for(int i=0;i<len;i++){
            String js = elements.get(i).getElementsByTag("a").attr("href");
            document = Jsoup.connect(u_url+js).headers(head).get();
            String play_0 = document.getElementsByClass("player").get(0).getElementsByTag("iframe").attr("src");
            play_0 = play_0.substring(play_0.indexOf("=")+1,play_0.length());
            System.out.println(play_0);
        }
    }


}
