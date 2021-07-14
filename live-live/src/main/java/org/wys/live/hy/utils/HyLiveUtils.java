package org.wys.live.hy.utils;


import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.checkerframework.checker.units.qual.C;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.wys.live.commons.constant.HeaderConstant;
import org.wys.live.domain.po.Categories;
import org.wys.live.domain.po.Page;
import org.wys.live.domain.po.Room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author wys
 * @date 2019/10/31
 * 虎牙工具类
 */
@Slf4j
public class HyLiveUtils {

    private final static String HYHEAD = "http://www.huya.com/cache.php?m=LiveList&do=getLiveListByPage&gameId=";
    private final static String HYTAIL = "&tagAll=0&page=";
    private final static String LIVE_HEAD = "https://www.huya.com/";
    private final static String HY_LIVE_CATEGORIES = "https://www.huya.com/g";
    public final static Map<String, String> head = new HashMap<>();
    private final static String configPattern = "hyPlayerConfig=(\\{.*?\\})";
    private final static String time = "&t=103";

    static {
        head.put("accept", HeaderConstant.ACCEPT);
        head.put("accept-encoding", HeaderConstant.ACCEPT_ENCODING);
        head.put("accept-language", HeaderConstant.ACCEPT_LANGUAGE);
        head.put("cache-control", HeaderConstant.CACHE_CONTROL);
        head.put("cookie", HeaderConstant.COOKIE);
        head.put("sec-fetch-mode", HeaderConstant.SEC_FETCH_MODE);
        head.put("sec-fetch-site", HeaderConstant.SEC_FETCH_SITE);
        head.put("sec-fetch-user", HeaderConstant.SEC_FETCH_USER);
        head.put("upgrade-insecure-requests", HeaderConstant.UPGRADE_INSECURE_REQUESTS);
        head.put("user-agent", HeaderConstant.USER_AGENT);
    }

    /**
     * 获取当前直播分类所有主播房间信息
     *
     * @param id 这个就是直播的类别
     */
    public static Page getAllAnchor(Integer id, Integer pageId) throws IOException {
        Page page = new Page();
        Document doc = Jsoup.connect(HYHEAD + id + HYTAIL).headers(head).ignoreHttpErrors(true).ignoreContentType(true).timeout(5000).get();
        Integer totalPage = Integer.valueOf(JSONObject.parseObject(JSONObject.parseObject(doc.text()).getString("data")).getString("totalPage"));
        Integer totalCount = Integer.valueOf(JSONObject.parseObject(JSONObject.parseObject(doc.text()).getString("data")).getString("totalCount"));
        List<Room> list = new ArrayList<>();
        /*
         * introduction  房间名称
         * nick   主播名称
         * profileRoom 房间id
         */
        Document document = Jsoup.connect(HYHEAD + String.valueOf(id) + HYTAIL + String.valueOf(pageId)).headers(head).ignoreHttpErrors(true).ignoreContentType(true).timeout(5000).get();
        JSONArray array = JSONArray.parseArray(JSON.parseObject(JSON.parseObject(document.text()).getString("data")).getString("datas"));
        for (int j = 0; j < array.size(); j++) {
            JSONObject jsonObject = array.getJSONObject(j);
            Room room = new Room();
            room.setRoomName(jsonObject.getString("introduction"));
            room.setAnchorName(jsonObject.getString("nick"));
            room.setRoomId(jsonObject.getString("profileRoom"));
            list.add(room);
        }
        page.setThePage(pageId);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);
        page.setRooms(list);
        return page;
    }

    /**
     * 获取播放链接
     */
    public static Object[] getLink(String roomId) throws Exception {
        String title = "";
        Document document = Jsoup.connect(LIVE_HEAD + roomId).headers(HyLiveUtils.head).ignoreContentType(true).ignoreHttpErrors(true).timeout(5000).get();
        title = document.title();
        Elements elements = document.getElementsByAttribute("data-fixed");;
        for(Element element : elements) {
//            log.info("element====>{}", element);
            String replace = element.html().replaceAll("\n","").replaceAll(" ","").replaceAll("\r","");
//            log.info("html====>{}", element.html());
//            log.info("replace str ====> {}", replace);
            Matcher matcher = Pattern.compile(configPattern).matcher(replace);
            int count = 0;
            if(matcher.find()){
                String s = matcher.group(1);
                JSONObject decodeJson = JSONObject.parseObject(Base64.decodeStr(JSONObject.parseObject(s).getString("stream")));
                JSONArray jsonArray = decodeJson.getJSONArray("data").getJSONObject(0).getJSONArray("gameStreamInfoList");
                log.info("object ====> {}",jsonArray);
                String[] links = new String[jsonArray.size()];
                for (int i = 0; i < jsonArray.size(); i++) {
                    String sHlsUrl = jsonArray.getJSONObject(i).getString("sHlsUrl") + "/";
                    String sStreamName = jsonArray.getJSONObject(i).getString("sStreamName") + ".m3u8";
                    String sFlvAntiCode = jsonArray.getJSONObject(i).getString("sHlsAntiCode").replaceAll("&amp;","&");
                    String finalUrl = sHlsUrl+sStreamName+"?"+sFlvAntiCode+time;
                    links[i] = (finalUrl);
                    log.info("links ======> {}", links[i]);
                }
                return new Object[]{title, links};
            }
        }
        return new Object[]{};
    }

    /**
     * 获取虎牙所有分类
     *
     * @return
     */
    public static List<Categories> getHyLiveCategories() {
        Document document = null;
        try {
            document = Jsoup.connect(HY_LIVE_CATEGORIES).ignoreHttpErrors(true).ignoreContentType(true).headers(head).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Objects.nonNull(document)) {
            return document.getElementsByClass("g-gameCard-item").stream().map(item -> {
                Categories categories = new Categories();
                categories.setCount(0);
                categories.setUid(Integer.valueOf(item.attr("data-gid")));
                categories.setName(item.attr("title"));
                return categories;
            }).collect(Collectors.toList());
        }
        return null;
    }
}
