package com.dangerousarea.gollum.service.impl;

import com.dangerousarea.gollum.common.define.ContentDefine;
import com.dangerousarea.gollum.common.exceptions.BusinessException;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.common.result.ErrorCodes;
import com.dangerousarea.gollum.dao.RoleAccountMapper;
import com.dangerousarea.gollum.domain.entities.RoleAccount;
import com.dangerousarea.gollum.service.RoleAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class RoleAccountServiceImpl implements RoleAccountService {
    @Resource
    private RoleAccountMapper roleAccountMapper;

    @Override
    public CommonResult<RoleAccount> create(RoleAccount roleAccount, HttpServletRequest request) {
        int roleAccountResult = roleAccountMapper.insert(roleAccount);
        if(roleAccountResult == 0){
            throw new BusinessException(ErrorCodes.SYSTME_ERROR,"创建角色账号关联表数据失败");
        }

        return CommonResult.success(roleAccount);
    }
}
