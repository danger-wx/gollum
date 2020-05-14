package com.dangerousarea.gollum.domain.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("gollum_brand_account")
public class BrandAccount extends BaseEntity {
    private Long brandId;
    private String account;
    private String email;
    private String name;
    private String password;
    private String passwordSalt;
    private Integer status;
    private Integer dataStatus;
    private Date lastPasswordResetDate;
}
