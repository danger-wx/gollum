package com.dangerousarea.gollum.service;

import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.Store;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StoreService {
    /**
     * 创建门店
     * @param store
     * @param request
     * @return
     */
    CommonResult<Store> createStore(Store store, HttpServletRequest request);

    CommonResult deleteStore(Long id, Long brandId, HttpServletRequest request);

    CommonResult<Store> updateStore(Store store, HttpServletRequest request);

    CommonResult<List<Store>> getBrandStores(Long brandId, HttpServletRequest request);
}
