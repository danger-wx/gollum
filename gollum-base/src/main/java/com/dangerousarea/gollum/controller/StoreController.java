package com.dangerousarea.gollum.controller;

import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.Store;
import com.dangerousarea.gollum.service.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("")
    public CommonResult create(Store store){
        return storeService.createStore(store, request);
    }

    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id){
        return storeService.deleteStore(id, request);
    }

    @GetMapping("/brand/{brandId}")
    public CommonResult<List<Store>> getBrandStores(@PathVariable("brandId") Long brandId){
        return storeService.getBrandStores(brandId, request);
    }
}
