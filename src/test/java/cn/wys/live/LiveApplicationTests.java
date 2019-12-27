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

    @Test
    void testImage() throws Exception {


        //v.DownLoadVideoImageByLink(v.getSeachTitleLink("仙剑奇侠传一"), "仙剑奇侠传一");

    }
}
