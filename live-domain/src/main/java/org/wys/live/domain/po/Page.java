package org.wys.live.domain.po;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 分页属性类
 *
 * @author wys
 * @date 2019/11/1
 */
@Data
public class Page implements Serializable {

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 直播总数量
     */
    private Integer totalCount;

    /**
     * 当前直播页数
     */
    private Integer thePage;

    /**
     * 当前页直播所有房间
     */
    private List<Room> rooms;

}
