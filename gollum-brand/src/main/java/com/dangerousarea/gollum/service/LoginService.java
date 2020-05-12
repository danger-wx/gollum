package com.dangerousarea.gollum.service;

import com.dangerousarea.gollum.domain.dto.BrandAccountDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    BrandAccountDto login(String account, String password, HttpServletRequest request) throws UsernameNotFoundException;
}
