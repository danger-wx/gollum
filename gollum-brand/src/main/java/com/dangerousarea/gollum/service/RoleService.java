package com.dangerousarea.gollum.service;

import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.Role;

import javax.servlet.http.HttpServletRequest;

public interface RoleService {
    /**
     * 创建品牌角色
     * @param role
     * @param request
     * @return
     */
    CommonResult<Role> create(Role role, HttpServletRequest request);
}
