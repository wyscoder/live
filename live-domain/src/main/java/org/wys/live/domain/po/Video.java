package org.wys.live.domain.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wys
 * @date 2019/11/9
 * 影视的实体类
 */
@Data
@TableName(value = "video")
public class Video {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "title")
    private String title;
    @TableField(value = "director")
    private String director;
    @TableField(value = "stars")
    private String stars;
    @TableField(value = "categories")
    private String categories;
    @TableField(value = "country")
    private String country;
    @TableField(value = "status")
    private String status;
    @TableField(value = "year")
    private String year;
    @TableField(value = "content")
    private String content;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
