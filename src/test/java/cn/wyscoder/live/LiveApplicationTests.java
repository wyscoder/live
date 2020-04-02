package cn.wyscoder.live;

import cn.wyscoder.live.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LiveApplicationTests {

    @Autowired
    private VideoService videoService;

    void contextLoads() {
    }

    @Test
    void testImage() throws Exception {
        //System.out.println(System.getProperty("os.name").toLowerCase().startsWith("win"));
        //v.DownLoadVideoImageByLink(v.getSeachTitleLink("仙剑奇侠传一"), "仙剑奇侠传一");

    }
}
