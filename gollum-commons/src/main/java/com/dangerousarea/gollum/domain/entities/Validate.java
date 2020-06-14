package com.dangerousarea.gollum.domain.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("gollum_validate")
public class Validate {
    Long id;
    Long accountId;
    String email;
    String resetToken;
    String type;
    Date createTime;
    Date modifyTime;
}
