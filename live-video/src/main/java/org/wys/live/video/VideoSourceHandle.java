package org.wys.live.video;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wys
 * @date 2021/2/4 10:17
 * 抽象一个视频工具类
 */
public abstract class VideoSourceHandle {

    protected final Map<String, String> headers = new HashMap<String, String>();

    public abstract String getSearchTitleLink(String message) throws Exception;

    public abstract Map<String, String> getVideoMessage(String link) throws Exception;

    public abstract List<String> getVideoLinks(String link) throws Exception;

    public abstract void DownLoadVideoImageByLink(String link, String message) throws Exception;

}
