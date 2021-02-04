package org.wys.live.server.service;

import org.wys.live.domain.po.Collection;
import org.wys.live.domain.po.Video;

import java.util.List;

/**
 * @author wys
 * @date 2021/2/3 17:32
 */
public interface CollectionService {

    List<Collection> selectCollectionByUserId(Integer id);

    void insertCollection(Collection collection);

    List<Video> selectCollectionVideoById(Integer id);

    void deleteCollectionById(Integer id);

    Collection selectCollectionByVideoIdAndPid(Integer id, Integer pid);

    void deleteCollectionByVideoId(Integer id);

}
