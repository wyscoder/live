package org.wys.live.test.hy;

import lombok.AllArgsConstructor;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wys.live.domain.po.Categories;
import org.wys.live.hy.utils.HyLiveUtils;

import java.util.List;

/**
 * @author wys
 * @date 2021/2/2 23:43
 */
@AllArgsConstructor
public class TestHy {


    @Test
    public void getLink() {
        List<Categories> hyLiveCategories = HyLiveUtils.getHyLiveCategories();
        System.out.println(hyLiveCategories);
    }

    void getCategories() {

    }
}
