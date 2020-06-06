package com.dangerousarea.gollum.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dangerousarea.gollum.common.define.BrandDefine;
import com.dangerousarea.gollum.common.define.ContentDefine;
import com.dangerousarea.gollum.common.exceptions.BusinessException;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.common.result.ErrorCodes;
import com.dangerousarea.gollum.dao.BrandAccountMapper;
import com.dangerousarea.gollum.dao.BrandMapper;
import com.dangerousarea.gollum.dao.RoleAccountMapper;
import com.dangerousarea.gollum.dao.RoleMapper;
import com.dangerousarea.gollum.domain.entities.Brand;
import com.dangerousarea.gollum.domain.entities.BrandAccount;
import com.dangerousarea.gollum.domain.entities.Role;
import com.dangerousarea.gollum.domain.entities.RoleAccount;
import com.dangerousarea.gollum.domain.vo.BrandVo;
import com.dangerousarea.gollum.service.BrandAccountService;
import com.dangerousarea.gollum.service.BrandService;
import com.dangerousarea.gollum.service.RoleAccountService;
import com.dangerousarea.gollum.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private BrandMapper brandMapper;

    @Autowired
    private BrandAccountService brandAccountService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleAccountService roleAccountService;


    @Override
    public CommonResult<Brand> register(BrandVo brandVo, HttpServletRequest request) {
        Brand brand = brandVo;
        if(StrUtil.isAllEmpty(brandVo.getPassword(), brandVo.getCountryCode(), brandVo.getName(),
                brandVo.getMobilePhone())){
            return CommonResult.error(ErrorCodes.PARAMETER_ERROR);
        }

        Brand exit = brandMapper.selectOne(new QueryWrapper<Brand>().eq("mobile_phone", brandVo.getMobilePhone()));
        if(exit != null){
            return CommonResult.error(ErrorCodes.PARAMETER_ERROR, "该账户已存在");
        }

        brandVo.setStatus(BrandDefine.Status.REVIEW);
        int brandResult = brandMapper.insert(brand);
        if(brandResult == 0){
            throw new BusinessException(ErrorCodes.SYSTME_ERROR, "创建品牌失败");
        }

        BrandAccount account = new BrandAccount();
        account.setAccount(brandVo.getMobilePhone());
        account.setPassword(passwordEncoder.encode(brandVo.getPassword()));
        account.setName(brand.getMobilePhone());
        account.setBrandId(brandVo.getId());
        CommonResult<BrandAccount> accountResult = brandAccountService.create(account, request);
        if(accountResult.getData() == null){
            throw new BusinessException(ErrorCodes.SYSTME_ERROR, "创建登陆账号失败");
        }

        /*
            新品牌创建角色
         */
        Role role = new Role(brand.getId(), ContentDefine.BRAND, ContentDefine.ADMIN_UPPER, "System admin", 0, ContentDefine.ROLE_ADMIN_UPPER, 1);
        CommonResult<Role> roleResult = roleService.create(role, request);
        if(roleResult.getData() == null){
            throw new BusinessException(ErrorCodes.SYSTME_ERROR, "创建默认角色失败");
        }

        RoleAccount roleAccount = new RoleAccount();
        roleAccount.setApplicationCode(ContentDefine.BRAND);
        roleAccount.setRoleId(role.getId());
        roleAccount.setAccountId(account.getId());
        CommonResult<RoleAccount> roleAccountResult = roleAccountService.create(roleAccount, request);
        if(roleAccountResult.getData() == null){
            throw new BusinessException(ErrorCodes.SYSTME_ERROR,"创建角色账号关联表数据失败");
        }

        return CommonResult.success(brand);
    }

    @Override
    public CommonResult audit(Long brandId, HttpServletRequest request) {
        Brand brand = brandMapper.selectById(brandId);
        if(ObjectUtil.isNull(brand)){
            return CommonResult.error(ErrorCodes.DATA_NOT_FOUND, "该品牌不存在");
        }

        if (BrandDefine.Status.PASSED == brand.getStatus()) {
            return CommonResult.success(true);
        }

        UpdateWrapper<Brand> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .set("status", BrandDefine.Status.PASSED)
                .eq("id", brandId);

        int audit = brandMapper.update(null, updateWrapper);
        if (audit > 0){
            return CommonResult.success(true);
        }

        return CommonResult.error(ErrorCodes.UNKNOWN_ERROR);
    }

    @Override
    public Brand getBrandById(Long brandId, HttpServletRequest request) {
        return brandMapper.selectById(brandId);
    }
}
