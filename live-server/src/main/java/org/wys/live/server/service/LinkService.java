package org.wys.live.server.service;

import org.springframework.stereotype.Service;
import org.wys.live.domain.po.Link;

import java.util.List;

/**
 * @author wys
 * @date 2021/2/3 22:35
 */
public interface LinkService {

    /**
     * 向数据库插入链接信息
     * @param link 链接
     */
    void insertLink(Link link);

    /**
     * 根据链接查询播放链接
     * @param pid 视频链接
     * @return 返回的是所有播放链接
     */
    List<Link> selectAllLinksByVideoId(Integer pid);

    Link selectLinkByVideoAndSeq(Integer pid,Integer seq);

    Integer selectLinkByVideoCount(Integer pid);

    void deleteAllLinksByVideoId(Integer pid);
}
