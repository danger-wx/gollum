package com.dangerousarea.gollum.controller;

import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.vo.BrandVo;
import com.dangerousarea.gollum.service.BrandService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api(tags = "Gollum品牌服务")
@RestController
@Slf4j
@RequestMapping("/brand")
public class BrandController extends BaseController{

    @Autowired
    HttpServletRequest request;

    @Autowired
    private BrandService brandService;

    @ApiOperation(value = "创建品牌")
    @PostMapping("/register")
    public CommonResult register(@RequestBody BrandVo brandVo){
        return brandService.register(brandVo, request);
    }

    @ApiOperation(value = "品牌审核")
    @PutMapping("/audit")
    public CommonResult audit(@RequestBody Map<String, Object> requestBody){
        Long brandId = (Long) requestBody.get("brandId");

        return brandService.audit(brandId, request);
    }
}
