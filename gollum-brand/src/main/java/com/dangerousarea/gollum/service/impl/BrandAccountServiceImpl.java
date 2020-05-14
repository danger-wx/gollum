package com.dangerousarea.gollum.service.impl;

import com.dangerousarea.gollum.common.exceptions.BusinessException;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.common.result.ErrorCodes;
import com.dangerousarea.gollum.dao.BrandAccountMapper;
import com.dangerousarea.gollum.domain.entities.BrandAccount;
import com.dangerousarea.gollum.service.BrandAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class BrandAccountServiceImpl implements BrandAccountService {

    @Resource
    private BrandAccountMapper brandAccountMapper;

    @Override
    public CommonResult<BrandAccount> create(BrandAccount account, HttpServletRequest request) {
        int accountResult = brandAccountMapper.insert(account);
        if(accountResult == 0){
            throw new BusinessException(ErrorCodes.SYSTME_ERROR, "创建登陆账号失败");
        }
        return CommonResult.success(account);
    }
}
