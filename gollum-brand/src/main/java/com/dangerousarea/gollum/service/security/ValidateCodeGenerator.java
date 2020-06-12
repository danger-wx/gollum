package com.dangerousarea.gollum.service.security;

import com.dangerousarea.gollum.domain.entities.validate.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest request);
}
