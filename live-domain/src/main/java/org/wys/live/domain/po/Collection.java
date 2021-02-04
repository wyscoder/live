package org.wys.live.domain.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *
 */
@Data
@TableName(value = "collection")
public class Collection {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "video_id")
    private Integer videoId;
    @TableField(value = "pid")
    private Integer pid;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
