package cn.wys.live.utils.hy;


import cn.wys.live.constant.HeaderConstant;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wys
 * @date 2019/10/31
 * 抓取虎牙直播源
 */
public class HyLiveSource {

    private final static String HYHEAD = "http://www.huya.com/cache.php?m=LiveList&do=getLiveListByPage&gameId=";
    private final static String HYTAIL = "&tagAll=0&page=";

    private final static Map<String,String> head = new HashMap<>();

    /**
     * 获取当前直播分类所有主播房间信息
     * @param id 这个就是直播的类别
     */
    public static void getAllAnchor(Integer id) throws IOException {
        if(head.isEmpty()){
            head.put("accept", HeaderConstant.ACCEPT);
            head.put("accept-encoding", HeaderConstant.ACCEPT_ENCODING);
            head.put("accept-language", HeaderConstant.ACCEPT_LANGUAGE);
            head.put("cache-control", HeaderConstant.CACHE_CONTROL);
            head.put("cookie", HeaderConstant.COOKIE);
            head.put("sec-fetch-mode", HeaderConstant.SEC_FETCH_MODE);
            head.put("sec-fetch-site", HeaderConstant.SEC_FETCH_SITE);
            head.put("sec-fetch-user", HeaderConstant.SEC_FETCH_USER);
            head.put("upgrade-insecure-requests",HeaderConstant.UPGRADE_INSECURE_REQUESTS);
            head.put("user-agent",HeaderConstant.USER_AGENT);
        }

        Document doc = Jsoup.connect(HYHEAD+id+HYTAIL).headers(head).ignoreHttpErrors(true).ignoreContentType(true).timeout(5000).get();


        Integer totalPage = Integer.valueOf(JSONObject.parseObject(JSONObject.parseObject(doc.text()).getString("data")).getString("totalPage"));
        System.out.println(totalPage);



        /**
         * introduction  房间名称
         * nick   主播名称
         * profileRoom 房间id
         */
        for(int i=0;i<totalPage;i++){
            Document document = Jsoup.connect(HYHEAD+String.valueOf(id)+HYTAIL+String.valueOf(i)).headers(head).ignoreHttpErrors(true).ignoreContentType(true).timeout(5000).get();
            JSONArray array = JSONArray.parseArray(JSON.parseObject(JSON.parseObject(document.text()).getString("data")).getString("datas"));
            for(int j=0;j<array.size();j++) {
                JSONObject jsonObject = array.getJSONObject(j);
                System.out.println(jsonObject);
            }
        }

    }


}
