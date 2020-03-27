package com.dangerousarea.gollum.domain.entities;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    private Integer id;
    private Date createTime;
    private Date updateTime;
}
