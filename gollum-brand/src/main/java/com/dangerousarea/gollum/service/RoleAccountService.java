package com.dangerousarea.gollum.service;

import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.RoleAccount;

import javax.servlet.http.HttpServletRequest;

public interface RoleAccountService {
    /**
     * 创建角色账户关系
     * @param roleAccount
     * @param request
     * @return
     */
    CommonResult<RoleAccount> create(RoleAccount roleAccount, HttpServletRequest request);
}
