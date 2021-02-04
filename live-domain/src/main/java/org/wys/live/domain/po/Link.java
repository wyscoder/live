package org.wys.live.domain.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wys
 * @date 2019/12/25
 */
@Data
@TableName("link")
public class Link {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("seq")
    private Integer seq;
    @TableField("name")
    private String name;
    @TableField("link")
    private String link;
    @TableField("video_id")
    private Integer videoId;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

}
