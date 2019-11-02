package cn.wys.live.utils.hy;


import cn.wys.live.beans.Page;
import cn.wys.live.beans.Room;
import cn.wys.live.constant.HeaderConstant;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wys
 * @date 2019/10/31
 * 抓取虎牙直播源
 */
public class HyLiveUtils {

    private final static String HYHEAD = "http://www.huya.com/cache.php?m=LiveList&do=getLiveListByPage&gameId=";
    private final static String HYTAIL = "&tagAll=0&page=";

    public final static Map<String,String> head = new HashMap<>();

    /**
     * 获取当前直播分类所有主播房间信息
     * @param id 这个就是直播的类别
     */
    public static Page getAllAnchor(Integer id, Integer pageId) throws IOException {
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
        Page page = new Page();
        Document doc = Jsoup.connect(HYHEAD+id+HYTAIL).headers(head).ignoreHttpErrors(true).ignoreContentType(true).timeout(5000).get();


        Integer totalPage = Integer.valueOf(JSONObject.parseObject(JSONObject.parseObject(doc.text()).getString("data")).getString("totalPage"));
        Integer totalCount = Integer.valueOf(JSONObject.parseObject(JSONObject.parseObject(doc.text()).getString("data")).getString("totalCount"));


        List<Room> list = new ArrayList<>();


        /**
         * introduction  房间名称
         * nick   主播名称
         * profileRoom 房间id
         */
        Document document = Jsoup.connect(HYHEAD+String.valueOf(id)+HYTAIL+String.valueOf(pageId)).headers(head).ignoreHttpErrors(true).ignoreContentType(true).timeout(5000).get();
        JSONArray array = JSONArray.parseArray(JSON.parseObject(JSON.parseObject(document.text()).getString("data")).getString("datas"));
        for(int j=0;j<array.size();j++) {
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


}
