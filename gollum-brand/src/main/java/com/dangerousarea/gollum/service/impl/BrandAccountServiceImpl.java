package com.dangerousarea.gollum.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangerousarea.gollum.common.define.ContentDefine;
import com.dangerousarea.gollum.common.exceptions.BusinessException;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.common.result.ErrorCodes;
import com.dangerousarea.gollum.dao.BrandAccountMapper;
import com.dangerousarea.gollum.domain.entities.BrandAccount;
import com.dangerousarea.gollum.domain.entities.RoleAccount;
import com.dangerousarea.gollum.domain.vo.BrandAccountVo;
import com.dangerousarea.gollum.service.BrandAccountService;
import com.dangerousarea.gollum.service.RoleAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class BrandAccountServiceImpl extends ServiceImpl<BrandAccountMapper, BrandAccount> implements BrandAccountService {

    @Resource
    private BrandAccountMapper brandAccountMapper;

    @Autowired
    private RoleAccountService roleAccountService;

    @Override
    public CommonResult<BrandAccount> create(BrandAccount account, HttpServletRequest request) {
        int accountResult = brandAccountMapper.insert(account);
        if(accountResult == 0){
            throw new BusinessException(ErrorCodes.SYSTME_ERROR, "创建登陆账号失败");
        }
        return CommonResult.success(account);
    }

    @Override
    public CommonResult<BrandAccount> create(BrandAccountVo accountVo, HttpServletRequest request) {
        int accountResult = brandAccountMapper.insert(accountVo);
        if(accountResult == 0){
            throw new BusinessException(ErrorCodes.SYSTME_ERROR, "创建登陆账号失败");
        }

        RoleAccount roleAccount = new RoleAccount();
        roleAccount.setApplicationCode(ContentDefine.BRAND);
        roleAccount.setRoleId(accountVo.getRoleId());
        roleAccount.setAccountId(accountVo.getId());
        CommonResult<RoleAccount> roleAccountResult = roleAccountService.create(roleAccount, request);
        if(roleAccountResult.getData() == null){
            throw new BusinessException(ErrorCodes.SYSTME_ERROR,"创建角色账号关联表数据失败");
        }

        return CommonResult.success(accountVo);
    }
}
