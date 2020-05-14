package com.dangerousarea.gollum.service;

import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.BrandAccount;
import javax.servlet.http.HttpServletRequest;

public interface BrandAccountService {
    /**
     * 创建品牌用户
     * @param account
     * @param request
     * @return
     */
    CommonResult<BrandAccount> create(BrandAccount account, HttpServletRequest request);

}
