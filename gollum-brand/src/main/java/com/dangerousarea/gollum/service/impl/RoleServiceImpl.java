package com.dangerousarea.gollum.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangerousarea.gollum.common.exceptions.BusinessException;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.common.result.ErrorCodes;
import com.dangerousarea.gollum.dao.RoleMapper;
import com.dangerousarea.gollum.domain.entities.Role;
import com.dangerousarea.gollum.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public CommonResult<Role> create(Role role, HttpServletRequest request) {
        int roleResult = roleMapper.insert(role);
        if(roleResult == 0){
            throw new BusinessException(ErrorCodes.SYSTME_ERROR, "创建默认角色失败");
        }
        return CommonResult.success(role);
    }
}
