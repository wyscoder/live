package cn.wys.live.utils.video;

import cn.wys.live.constant.HeaderPiaohua;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wys
 * @date 2019/12/25
 * VideoUtils工具类
 * 基于飘花网站实现的
 */
@Configuration
public class VideoUtils {

    private final static String BASIC = "http://piaohua520.com";
    private final static String SEACH_PIAOHUA = "http://www.piaohua520.com/?c=search&wd=";

    private static Map<String,String> piaohuaHeaders = new HashMap<String,String>();
    /**
     * 获取飘花视频的检索信息
     * 检索方式为 从左到右第一个匹配到相同名称的视频的链接
     * @param message 你想检索的信息
     * @return 返回匹配到的链接
     */
    public String getSeachTitleLink(String message) throws Exception{

        if(piaohuaHeaders.isEmpty()) {
            addPiaoHuapiaohuaHeadersers();
        }
        Document document = Jsoup.connect(SEACH_PIAOHUA+message).ignoreContentType(true).ignoreHttpErrors(true).headers(piaohuaHeaders).get();
        Elements elements = document.getElementsByClass("movie-item");
        for(Element element:elements){
            if(element.child(0).attr("title").equals(message)){
                return element.child(0).attr("href");
            }
        }
        return null;
    }


    /**
     * 根据传进来的飘花链接返回视频所有信息
     * @param link 传进来的链接
     * @return 返回的信息
     */
    public Map<String,String> getVideoMessage(String link) throws Exception{
        if(piaohuaHeaders.isEmpty()) {
            addPiaoHuapiaohuaHeadersers();
        }
        Map<String,String> msg = new HashMap<>();
        Document document = Jsoup.connect(link).headers(piaohuaHeaders).ignoreHttpErrors(true).ignoreContentType(true).get();
        Elements elements = document.getElementsByTag("tr");
        //System.out.println(document);
        for(int i=0;i<elements.size();i++) {
            String left = elements.get(i).child(0).text();
            String right = "";
            right = elements.get(i).child(1).text();
            msg.put(left,right);

        }
        msg.put("剧情",document.getElementsByClass("summary").text());
        return msg;
    }

    /**
     * 获取当前影视的播放链接
     * @param link 影视详细页面的链接
     * @return 返回的是影视链接
     * @throws Exception 抛出的异常
     */
    public List<String> getVideoLinks(String link) throws Exception {
        if(piaohuaHeaders.isEmpty()) {
            addPiaoHuapiaohuaHeadersers();
        }

        Document document = Jsoup.connect(link).ignoreContentType(true).ignoreHttpErrors(true).headers(piaohuaHeaders).get();
        Element element = document.getElementsByClass("dslist-group").get(1);
        List<String> links = new ArrayList<>();
        System.out.println(document.getElementsByClass("dslist-group").size());
        for(Element e:element.getElementsByTag("a")){
            String url = BASIC + e.attr("href");
            document = Jsoup.connect(url).ignoreHttpErrors(true).ignoreContentType(true).headers(piaohuaHeaders).get();
            String l = document.getElementsByTag("iframe").attr("src");
            links.add(l.substring(l.indexOf("=")+1,l.length()));
        }

        return links;
    }

    public void DownLoadVideoImageByLink(String link,String message) throws Exception {
        if(piaohuaHeaders.isEmpty()) {
            addPiaoHuapiaohuaHeadersers();
        }
        Document document = Jsoup.connect(link).ignoreContentType(true).ignoreHttpErrors(true).headers(piaohuaHeaders).get();
        Element element = document.getElementsByClass("img-thumbnail").get(0);
        String uurl = element.attr("src");

        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\video\\"+message+".jpg";

        Connection.Response response = Jsoup.connect(uurl).headers(piaohuaHeaders).ignoreContentType(true).ignoreHttpErrors(true).execute();

        byte[] img = response.bodyAsBytes();


        File file = new File(path);

        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fos);
        bufferedOutputStream.write(img);

        System.out.println("图片下载完成！");


        fos.flush();
        fos.close();
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    /**
     * 给头文件添加信息
     */
    private void addPiaoHuapiaohuaHeadersers() {
        piaohuaHeaders.put("accept", HeaderPiaohua.ACCEPT);
        piaohuaHeaders.put("accept-encoding", HeaderPiaohua.ACCEPT_ENCODING);
        piaohuaHeaders.put("accept-language", HeaderPiaohua.ACCEPT_LANGUAGE);
        piaohuaHeaders.put("cookie", HeaderPiaohua.COOKIE);
        piaohuaHeaders.put("user-agent",HeaderPiaohua.USER_AGENT);
    }


}
