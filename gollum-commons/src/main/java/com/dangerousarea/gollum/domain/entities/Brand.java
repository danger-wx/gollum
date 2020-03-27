package com.dangerousarea.gollum.domain.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Brand extends BaseEntity {
    private String name;
    private String logo;
    private String mobilePhone;
    private String countryCode;
    private Integer status;
    private String introduction;
    private Date activeTime;
    private Date expireTime;
}
