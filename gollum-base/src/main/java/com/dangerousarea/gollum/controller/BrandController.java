package com.dangerousarea.gollum.controller;

import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.service.BrandService;
import com.dangerousarea.gollum.domain.vo.BrandVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    private BrandService brandService;

    @PostMapping("/register")
    public CommonResult register(@RequestBody BrandVo brandVo){
        return brandService.register(brandVo, request);
    }

    @PutMapping("/audit")
    public CommonResult audit(@RequestBody Map<String, Integer> requestBody){
        Integer status = requestBody.get("status");
        Integer brandId = requestBody.get("brandId");

        return brandService.audit(brandId, status, request);
    }
}
