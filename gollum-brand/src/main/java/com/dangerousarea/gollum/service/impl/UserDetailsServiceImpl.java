package com.dangerousarea.gollum.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.dangerousarea.gollum.common.define.BrandDefine;
import com.dangerousarea.gollum.dao.AuthMapper;
import com.dangerousarea.gollum.domain.dto.BrandAccountDto;
import com.dangerousarea.gollum.domian.factory.JwtUserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        BrandAccountDto brandAccount = authMapper.selectByAccount(account);
        if(!brandAccount.getBrandStatus().equals(BrandDefine.Status.PASSED)){
            throw new UsernameNotFoundException("该品牌尚未通过审核");
        }
        if(ObjectUtil.isNull(brandAccount)){
            throw new UsernameNotFoundException("该用户不存在");
        }else{
            return JwtUserFactory.create(brandAccount);
        }

    }
}
