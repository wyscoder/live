package org.wys.live.video.piaohua;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.wys.live.commons.constant.HeaderPiaohua;
import org.wys.live.video.VideoSourceHandle;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wys
 * @date 2021/2/4 10:33
 */
@Component
public class PiaoHuaVideoSourceHandle extends VideoSourceHandle {

    private final String BASIC = "http://piaohua520.com";
    private final String SEARCH_PIAOHUA = "http://www.piaohua520.com/?c=search&wd=";

    /**
     * 获取飘花视频的检索信息
     * 检索方式为 从左到右第一个匹配到相同名称的视频的链接
     *
     * @param message 你想检索的信息
     * @return 返回匹配到的链接
     */
    @Override
    public String getSearchTitleLink(String message) throws Exception {

        if (headers.isEmpty()) {
            addPiaoHuapiaohuaHeadersers();
        }
        Document document = Jsoup.connect(SEARCH_PIAOHUA + message).ignoreContentType(true).ignoreHttpErrors(true).headers(headers).get();
        Elements elements = document.getElementsByClass("movie-item");
        for (Element element : elements) {
            if (element.child(0).attr("title").equals(message)) {
                return element.child(0).attr("href");
            }
        }
        return null;
    }


    /**
     * 根据传进来的飘花链接返回视频所有信息
     *
     * @param link 传进来的链接
     * @return 返回的信息
     */
    @Override
    public Map<String, String> getVideoMessage(String link) throws Exception {
        if (headers.isEmpty()) {
            addPiaoHuapiaohuaHeadersers();
        }
        Map<String, String> msg = new HashMap<>();
        Document document = Jsoup.connect(link).headers(headers).ignoreHttpErrors(true).ignoreContentType(true).get();
        Elements elements = document.getElementsByTag("tr");
        //System.out.println(document);
        for (int i = 0; i < elements.size(); i++) {
            String left = elements.get(i).child(0).text();
            String right = "";
            right = elements.get(i).child(1).text();
            msg.put(left, right);

        }
        msg.put("剧情", document.getElementsByClass("summary").text());
        return msg;
    }

    /**
     * 获取当前影视的播放链接
     *
     * @param link 影视详细页面的链接
     * @return 返回的是影视链接
     * @throws Exception 抛出的异常
     */
    @Override
    public List<String> getVideoLinks(String link) throws Exception {
        if (headers.isEmpty()) {
            addPiaoHuapiaohuaHeadersers();
        }

        Document document = Jsoup.connect(link).ignoreContentType(true).ignoreHttpErrors(true).headers(headers).get();
        Element element = document.getElementsByClass("dslist-group").get(0);
        List<String> links = new ArrayList<>();
        System.out.println(document.getElementsByClass("dslist-group").size());
        for (Element e : element.getElementsByTag("a")) {
            String url = BASIC + e.attr("href");
            document = Jsoup.connect(url).ignoreHttpErrors(true).ignoreContentType(true).headers(headers).get();
            String l = document.getElementsByTag("iframe").attr("src");
            links.add(l.substring(l.indexOf("=") + 1, l.length()));
        }

        return links;
    }

    @Override
    public void DownLoadVideoImageByLink(String link, String message) throws Exception {
        if (headers.isEmpty()) {
            addPiaoHuapiaohuaHeadersers();
        }
        Document document = Jsoup.connect(link).ignoreContentType(true).ignoreHttpErrors(true).headers(headers).get();
        Element element = document.getElementsByClass("img-thumbnail").get(0);

        String uurl = element.attr("src");
        uurl = uurl.substring(uurl.indexOf(":"), uurl.length());
        uurl = "https" + uurl;

        String path = "";
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            path = System.getProperty("user.dir") + "/live-web/src/main/resources/static/images/video/" + message + ".jpg";
        } else {
            path = System.getProperty("user.dir") + "/video/" + message + ".jpg";
        }
        Connection.Response response = Jsoup.connect(uurl).headers(headers).ignoreContentType(true).ignoreHttpErrors(true).execute();

        byte[] img = response.bodyAsBytes();


        File file = new File(path);

        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fos);
        bufferedOutputStream.write(img);

        System.out.println("图片下载完成！");
        System.out.println(path);


        fos.flush();
        fos.close();
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    /**
     * 给头文件添加信息
     */
    private void addPiaoHuapiaohuaHeadersers() {
        headers.put("accept", HeaderPiaohua.ACCEPT);
        headers.put("accept-encoding", HeaderPiaohua.ACCEPT_ENCODING);
        headers.put("accept-language", HeaderPiaohua.ACCEPT_LANGUAGE);
        headers.put("cookie", HeaderPiaohua.COOKIE);
        headers.put("user-agent", HeaderPiaohua.USER_AGENT);
    }
}
