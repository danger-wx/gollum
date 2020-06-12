package com.dangerousarea.gollum.config;

import com.dangerousarea.gollum.service.security.DefaultSmsCodeSender;
import com.dangerousarea.gollum.service.security.SmsCodeSender;
import com.dangerousarea.gollum.service.security.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 生成验证码逻辑的相关配置
 */
@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * @ConditionalOnMissingBean
     * 指定接口,这个表示如果没有实现该接口的类，就执行下面的代码
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSender();
    }
}
