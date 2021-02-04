package org.wys.live.domain.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wys
 * @date 2019/10/31
 * 房间信息
 */
@Data
public class Room implements Serializable {

    /**
     * 房间名称
     */
    private String roomName;
    /**
     * 主播名称
     */
    private String anchorName;
    /**
     * 房间id号
     */
    private String roomId;
    
}
