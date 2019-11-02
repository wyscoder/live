package cn.wys.live.utils.hy;

import cn.wys.live.constant.HeaderConstant;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wys
 * @date 2019/11/2
 * 获取播放链接的类
 */
public class HyLiveLink {

    private final static String LIVE_HEAD = "https://www.huya.com/";
    /**
     * 获取播放链接
     */
    public static String[] getLink(String roomId) throws Exception {
        if(HyLiveUtils.head.isEmpty()){
            HyLiveUtils.head.put("accept", HeaderConstant.ACCEPT);
            HyLiveUtils.head.put("accept-encoding", HeaderConstant.ACCEPT_ENCODING);
            HyLiveUtils.head.put("accept-language", HeaderConstant.ACCEPT_LANGUAGE);
            HyLiveUtils.head.put("cache-control", HeaderConstant.CACHE_CONTROL);
            HyLiveUtils.head.put("cookie", HeaderConstant.COOKIE);
            HyLiveUtils.head.put("sec-fetch-mode", HeaderConstant.SEC_FETCH_MODE);
            HyLiveUtils.head.put("sec-fetch-site", HeaderConstant.SEC_FETCH_SITE);
            HyLiveUtils.head.put("sec-fetch-user", HeaderConstant.SEC_FETCH_USER);
            HyLiveUtils.head.put("upgrade-insecure-requests",HeaderConstant.UPGRADE_INSECURE_REQUESTS);
            HyLiveUtils.head.put("user-agent",HeaderConstant.USER_AGENT);
        }
        String title = "";
        Document document = Jsoup.connect(LIVE_HEAD+roomId).headers(HyLiveUtils.head).ignoreContentType(true).ignoreHttpErrors(true).timeout(5000).get();
        title = document.title();
        Elements elements = document.getElementsByAttribute("data-fixed");

        String p = "\"data\":\\[\\{(.*)\\]\\}";
        Pattern pattern = Pattern.compile(p);
        String s1 = "";
        for(Element element:elements) {
            String str = element.toString();
            Matcher matcher = pattern.matcher(str);
            while(matcher.find()) {
                s1 = matcher.group();
                //System.out.println(matcher.group());
            }
        }
        JSONObject jsonObject = JSONObject.parseObject(s1.substring(8,s1.length()));
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("gameStreamInfoList"));

        JSONObject jSONObjectFirst = jsonArray.getJSONObject(jsonArray.size()-1);
        String sHlsUrl = jSONObjectFirst.getString("sHlsUrl")+"/";
        String sStreamName = jSONObjectFirst.getString("sStreamName")+".m3u8";
        //System.out.println(title);

        return new String[]{title,sHlsUrl+sStreamName};
    }

}
