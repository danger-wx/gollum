package com.dangerousarea.gollum.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.common.result.ErrorCodes;
import com.dangerousarea.gollum.dao.BrandMapper;
import com.dangerousarea.gollum.dao.StoreMapper;
import com.dangerousarea.gollum.domain.entities.Brand;
import com.dangerousarea.gollum.domain.entities.Store;
import com.dangerousarea.gollum.service.StoreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Resource
    BrandMapper brandMapper;

    @Resource
    StoreMapper storeMapper;

    @Override
    public CommonResult<Store> createStore(Store store, HttpServletRequest request) {
        if(ObjectUtil.isEmpty(store.getBrandId())){
            return CommonResult.error(ErrorCodes.PARAMETER_ERROR);
        }

        Brand brand = brandMapper.selectByPrimaryKey(store.getBrandId());
        if(ObjectUtil.isEmpty(brand)){
            return CommonResult.error(ErrorCodes.BRAND_NOT_EXISTS);
        }

        int result = storeMapper.create(store);

        if (result > 0) {
            return CommonResult.success(store);
        }
        return CommonResult.error(ErrorCodes.UNKNOWN_ERROR);
    }

    @Override
    public CommonResult deleteStore(Long id, HttpServletRequest request) {
        Store store = storeMapper.selectByPrimaryKey(id);
        if(ObjectUtil.isEmpty(store)){
            return CommonResult.error(ErrorCodes.STORE_NOT_EXISTS);
        }

        int result = storeMapper.delete(id);
        if(result > 0){
            return CommonResult.success(store);
        }
        return CommonResult.error(ErrorCodes.UNKNOWN_ERROR);
    }

    @Override
    public CommonResult<List<Store>> getBrandStores(Long brandId, HttpServletRequest request) {
        List<Store> storeList = storeMapper.selectByBrandId(brandId);
        return CommonResult.success(storeList);
    }
}
