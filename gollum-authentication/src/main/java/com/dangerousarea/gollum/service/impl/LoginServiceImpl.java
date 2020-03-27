package com.dangerousarea.gollum.service.impl;

import com.dangerousarea.gollum.domain.dto.BrandAccountDto;
import com.dangerousarea.gollum.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public BrandAccountDto login(String account, String password, HttpServletRequest request) throws UsernameNotFoundException {
        userDetailsService.loadUserByUsername(account);
        return null;
    }
}
