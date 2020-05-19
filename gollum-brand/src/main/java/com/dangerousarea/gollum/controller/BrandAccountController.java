package com.dangerousarea.gollum.controller;

import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.BrandAccount;
import com.dangerousarea.gollum.domain.vo.BrandAccountVo;
import com.dangerousarea.gollum.service.BrandAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Gollum品牌账户服务")
@RestController
@Slf4j
@RequestMapping("/account")
public class BrandAccountController extends BaseController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BrandAccountService brandAccountService;

    @ApiOperation(value = "新建员工账号")
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult register(@RequestBody BrandAccountVo brandAccountVo){
        String password = brandAccountVo.getPassword();
        brandAccountVo.setPassword(passwordEncoder.encode(password));
        brandAccountVo.setBrandId(getLoginBrandId());
        return brandAccountService.create(brandAccountVo, request);
    }
}
