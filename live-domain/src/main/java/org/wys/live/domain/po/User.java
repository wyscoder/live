package org.wys.live.domain.po;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 账号类
 */
@Data
@TableName(value = "user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "username")
    private String username;
    @TableField(value = "password")
    private String password;
    @TableField(value = "name")
    private String name;
    @TableField(value = "mail")
    private String mail;
    @TableField(fill = FieldFill.INSERT, value = "createTime")
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE, value = "updateTime")
    private LocalDateTime updateTime;
}
