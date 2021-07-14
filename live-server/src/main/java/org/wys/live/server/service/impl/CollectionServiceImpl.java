package org.wys.live.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wys.live.domain.po.Collection;
import org.wys.live.domain.po.Video;
import org.wys.live.server.dao.CollectionMapper;
import org.wys.live.server.dao.VideoMapper;
import org.wys.live.server.service.CollectionService;
import org.wys.live.server.service.VideoService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wys
 * @date 2021/2/3 17:32
 */
@Service
@AllArgsConstructor
@Slf4j
public class CollectionServiceImpl implements CollectionService {

    private final CollectionMapper collectionMapper;
    private final VideoMapper videoMapper;

    @Override
    @Transactional
    public void deleteCollectionById(Integer id) {
        collectionMapper.deleteById(id);
    }

    @Override
    public List<Collection> selectCollectionByUserId(Integer id) {
        QueryWrapper<Collection> collectionQueryWrapper = new QueryWrapper<>();
        collectionQueryWrapper.eq("pid", id);
        return collectionMapper.selectList(collectionQueryWrapper);
    }

    @Override
    @Transactional
    public void insertCollection(Collection collection) {
        collectionMapper.insert(collection);
    }

    @Override
    public List<Video> selectCollectionVideoById(Integer id) {
        List<Collection> collections = selectCollectionByUserId(id);
        List<Video> videoList = new ArrayList<>();
        for (Collection c : collections) {
            QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
            videoQueryWrapper.eq("id", c.getVideoId());
            videoList.add(videoMapper.selectOne(videoQueryWrapper));
        }
        return videoList;
    }

    @Override
    public Collection selectCollectionByVideoIdAndPid(Integer videoId, Integer pid) {
        QueryWrapper<Collection> collectionQueryWrapper = new QueryWrapper<>();
        collectionQueryWrapper.eq("video_id", videoId);
        collectionQueryWrapper.eq("pid", pid);
        return collectionMapper.selectOne(collectionQueryWrapper);
    }

    @Override
    @Transactional
    public void deleteCollectionByVideoId(Integer videoId) {
        QueryWrapper<Collection> collectionQueryWrapper = new QueryWrapper<>();
        collectionQueryWrapper.eq("video_id", videoId);
        collectionMapper.delete(collectionQueryWrapper);
    }
}
