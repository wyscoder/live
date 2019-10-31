package cn.wys.live;

import cn.wys.live.utils.hy.HyLiveSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class LiveApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void testHy() throws IOException {
        HyLiveSource.getAllAnchor(2336);
    }
}
