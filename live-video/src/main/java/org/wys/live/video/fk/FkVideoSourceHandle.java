package org.wys.live.video.fk;

import org.wys.live.video.VideoSourceHandle;

import java.util.List;
import java.util.Map;

/**
 * @author wys
 * @date 2021/2/4 10:32
 */
public class FkVideoSourceHandle extends VideoSourceHandle {

    @Override
    public String getSearchTitleLink(String message) throws Exception {
        return null;
    }

    @Override
    public Map<String, String> getVideoMessage(String link) throws Exception {
        return null;
    }

    @Override
    public List<String> getVideoLinks(String link) throws Exception {
        return null;
    }

    @Override
    public void DownLoadVideoImageByLink(String link, String message) throws Exception {

    }
}
