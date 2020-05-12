package com.dangerousarea.gollum.controller;

import com.dangerousarea.gollum.domian.JwtUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {
    /**
     * 日志记录
     */
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 当前请求
     */
    @Autowired
    HttpServletRequest request;

    public JwtUser getLoginAccount() {
        JwtUser account = (JwtUser) ((Authentication) request.getUserPrincipal()).getPrincipal();
        return account;
    }

    public Long getLoginUserId(){
        JwtUser account = (JwtUser) ((Authentication) request.getUserPrincipal()).getPrincipal();
        return account.getId();
    }

    public Long getLoginBrandId(){
        JwtUser account = (JwtUser) ((Authentication) request.getUserPrincipal()).getPrincipal();
        return account.getBrandId();
    }
}
