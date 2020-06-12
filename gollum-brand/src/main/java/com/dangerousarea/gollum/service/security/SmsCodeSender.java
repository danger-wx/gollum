package com.dangerousarea.gollum.service.security;

/**
 * @Description: 短信验证码的发送接口
 */
public interface SmsCodeSender {
    void send(String mobile, String code);
}
