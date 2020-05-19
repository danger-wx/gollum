package com.dangerousarea.gollum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.BrandAccount;
import com.dangerousarea.gollum.domain.vo.BrandAccountVo;

import javax.servlet.http.HttpServletRequest;

public interface BrandAccountService extends IService<BrandAccount> {
    /**
     * 创建品牌用户
     * @param account
     * @param request
     * @return
     */
    CommonResult<BrandAccount> create(BrandAccount account, HttpServletRequest request);

    /**
     * 创建品牌用户
     * @param accountVo
     * @param request
     * @return
     */
    CommonResult<BrandAccount> create(BrandAccountVo accountVo, HttpServletRequest request);

}
