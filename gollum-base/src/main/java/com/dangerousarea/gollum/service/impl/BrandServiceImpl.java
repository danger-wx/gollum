package com.dangerousarea.gollum.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.dangerousarea.gollum.common.define.ContentDefine;
import com.dangerousarea.gollum.common.exceptions.BusinessException;
import com.dangerousarea.gollum.dao.BrandAccountMapper;
import com.dangerousarea.gollum.dao.BrandMapper;
import com.dangerousarea.gollum.common.define.BrandDefine;
import com.dangerousarea.gollum.dao.RoleAccountMapper;
import com.dangerousarea.gollum.dao.RoleMapper;
import com.dangerousarea.gollum.domain.entities.Brand;
import com.dangerousarea.gollum.domain.entities.BrandAccount;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.common.result.ErrorCodes;
import com.dangerousarea.gollum.domain.entities.Role;
import com.dangerousarea.gollum.service.BrandService;
import com.dangerousarea.gollum.domain.vo.BrandVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private BrandMapper brandMapper;

    @Resource
    private BrandAccountMapper brandAccountMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleAccountMapper roleAccountMapper;


    @Override
    public CommonResult<Brand> register(BrandVo brandVo, HttpServletRequest request) {
        Brand brand = brandVo;
        if(StrUtil.isAllEmpty(brandVo.getPassword(), brandVo.getCountryCode(), brandVo.getName())){
            return CommonResult.error(ErrorCodes.PARAMETER_ERROR);
        }

        brandVo.setStatus(BrandDefine.Status.REVIEW);
        int brandResult = brandMapper.create(brand);
        if(brandResult == 0){
            throw new BusinessException(ErrorCodes.SYSTME_ERROR, "创建品牌失败");
        }

        BrandAccount account = new BrandAccount();
        account.setAccount(brandVo.getMobilePhone());
        account.setPassword(passwordEncoder.encode(brandVo.getPassword()));
        account.setName(brand.getMobilePhone());
        account.setBrandId(brandVo.getId());
        int accountResult = brandAccountMapper.create(account);
        if(accountResult == 0){
            throw new BusinessException(ErrorCodes.SYSTME_ERROR, "创建登陆账号失败");
        }

        /*
            新品牌创建角色
         */
        Role role = new Role(brand.getId(), ContentDefine.BRAND, ContentDefine.ROLE_ADMIN_UPPER, "System admin", 0, ContentDefine.ROLE_ADMIN_UPPER, 1);
        int roleResult = roleMapper.create(role);
        if(roleResult == 0){
            throw new BusinessException(ErrorCodes.SYSTME_ERROR, "创建默认角色失败");
        }

        int roleAccountResult = roleAccountMapper.create(ContentDefine.BRAND, role.getId(), account.getId());
        if(roleAccountResult == 0){
            throw new BusinessException(ErrorCodes.SYSTME_ERROR,"创建角色账号关联表数据失败");
        }

        return CommonResult.success(brand);
    }

    @Override
    public CommonResult audit(Long brandId, Integer status, HttpServletRequest request) {
        Brand brand = brandMapper.selectByPrimaryKey(brandId);
        if(ObjectUtil.isNull(brand)){
            return CommonResult.error(ErrorCodes.DATA_NOT_FOUND, "该品牌不存在");
        }

        if (status.equals(brand.getStatus())) {
            return CommonResult.success(true);
        }

        int audit = brandMapper.audit(brandId, status);
        if (audit > 0 && BrandDefine.Status.PASSED == status){
            return CommonResult.success(true);
        }

        return CommonResult.error(ErrorCodes.UNKNOWN_ERROR);
    }
}
