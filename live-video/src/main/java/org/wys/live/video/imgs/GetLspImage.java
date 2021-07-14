package org.wys.live.video.imgs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author wys
 * @date 2021/2/10 18:16
 */
public class GetLspImage {

    private static final String url = "https://mf.q19g.com/6D667A796D62/tuiguang2.php?_wv=%78%77%2e%71%71%2e%63%6f%6d";

    public void downloadImages() {

    }

    public void getImageLink() {

    }

    public static void main(String[] args) throws Exception {
        Document document = Jsoup.connect(url).get();
        System.out.println(document);
    }

}
