package com.dangerousarea.gollum.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangerousarea.gollum.common.define.DataStatusDefine;
import com.dangerousarea.gollum.common.define.StoreDefine;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.common.result.ErrorCodes;
import com.dangerousarea.gollum.dao.BrandMapper;
import com.dangerousarea.gollum.dao.StoreMapper;
import com.dangerousarea.gollum.domain.entities.Brand;
import com.dangerousarea.gollum.domain.entities.Store;
import com.dangerousarea.gollum.service.BrandService;
import com.dangerousarea.gollum.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

    @Autowired
    private BrandService brandService;

    @Resource
    StoreMapper storeMapper;

    @Override
    public CommonResult<Store> createStore(Store store, HttpServletRequest request) {
        if(ObjectUtil.isEmpty(store.getBrandId())){
            return CommonResult.error(ErrorCodes.PARAMETER_ERROR);
        }

        Brand brand = brandService.getBrandById(store.getBrandId(), request);
        if(ObjectUtil.isEmpty(brand)){
            return CommonResult.error(ErrorCodes.BRAND_NOT_EXISTS);
        }

        int result = storeMapper.insert(store);

        if (result > 0) {
            return CommonResult.success(store);
        }
        return CommonResult.error(ErrorCodes.UNKNOWN_ERROR);
    }

    @Override
    public CommonResult deleteStore(Long id, Long brandId, HttpServletRequest request) {
        Store store = storeMapper.selectById(id);
        if(ObjectUtil.isEmpty(store)){
            return CommonResult.error(ErrorCodes.STORE_NOT_EXISTS);
        }
        if (!store.getBrandId().equals(brandId)){
            return CommonResult.error(ErrorCodes.STORE_NOT_EXISTS);
        }

        store.setDataStatus(DataStatusDefine.DELETE);
        UpdateWrapper<Store> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id).eq("brand_id", brandId);
        int result = storeMapper.update(store, updateWrapper);

        if(result > 0){
            return CommonResult.success(store);
        }
        return CommonResult.error(ErrorCodes.UNKNOWN_ERROR);
    }

    @Override
    public CommonResult<Store> updateStore(Store store, HttpServletRequest request) {
        int result = storeMapper.updateById(store);

        if(result > 0){
            return CommonResult.success(store);
        }
        return CommonResult.error(ErrorCodes.UNKNOWN_ERROR);
    }

    @Override
    public CommonResult<List<Store>> getBrandStores(Long brandId, HttpServletRequest request) {
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("brand_id", brandId)
                .eq("data_status", DataStatusDefine.NORMAL);
        List<Store> storeList = storeMapper.selectList(queryWrapper);
        return CommonResult.success(storeList);
    }
}
