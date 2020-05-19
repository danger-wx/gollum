package com.dangerousarea.gollum.controller;

import com.dangerousarea.gollum.common.define.ContentDefine;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.common.result.ErrorCodes;
import com.dangerousarea.gollum.domain.entities.Role;
import com.dangerousarea.gollum.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "角色服务")
@RestController
@Slf4j
@RequestMapping("/role")
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;

    @ApiOperation("创建角色")
    @PostMapping
    public CommonResult<Role> create(@RequestBody Role role){
        role.setBrandId(getLoginBrandId());
        if(role.getName().equalsIgnoreCase(ContentDefine.ADMIN_UPPER) || role.getCode().equalsIgnoreCase(ContentDefine.ROLE_ADMIN_UPPER)){
            return CommonResult.error(ErrorCodes.PARAMETER_ERROR);
        }
        return roleService.create(role, getRequest());
    }
}
