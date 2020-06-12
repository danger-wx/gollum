package com.dangerousarea.gollum.service.security;

import cn.hutool.core.util.RandomUtil;
import com.dangerousarea.gollum.domain.entities.validate.ValidateCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ValidateCode generate(ServletWebRequest request) {
//        String code = RandomStringUtils.randomNumeric(SecurityConstants.SMS_RANDOM_SIZE);
//        return new ValidateCode(code, SecurityConstants.SMS_EXPIRE_SECOND);
        String code = RandomUtil.randomString(4);
        return new ValidateCode(code, 3000);
    }
}
