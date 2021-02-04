package org.wys.live.domain.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wys
 * @date 2019/12/26
 */
@Data
@TableName(value = "categories")
public class Categories {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "uid")
    private Integer uid;
    @TableField(value = "count")
    private Integer count;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

}
