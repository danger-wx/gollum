package com.dangerousarea.gollum.service;

import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.Brand;
import com.dangerousarea.gollum.domain.vo.BrandVo;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BrandService {

    @Transactional
    CommonResult<Brand> register(BrandVo brandVo, HttpServletRequest request);

    @Transactional
    CommonResult audit(Long brandId, HttpServletRequest request);

    Brand getBrandById(Long brandId, HttpServletRequest request);
}
