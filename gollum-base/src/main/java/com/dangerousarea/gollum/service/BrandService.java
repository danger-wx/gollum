package com.dangerousarea.gollum.service;

import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.Brand;
import com.dangerousarea.gollum.domain.vo.BrandVo;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

public interface BrandService {

    @Transactional
    CommonResult<Brand> register(BrandVo brandVo, HttpServletRequest request);

    @Transactional
    CommonResult audit(Long brandId,Integer status, HttpServletRequest request);
}
