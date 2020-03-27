package com.dangerousarea.gollum.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Identify implements Serializable {
    /**
     * 用户ID
     */
    private int id;

    private String account;

    private String name;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登录token
     */
    private String token;


}
