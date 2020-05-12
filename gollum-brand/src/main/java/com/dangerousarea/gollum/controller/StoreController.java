package com.dangerousarea.gollum.controller;

import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.Store;
import com.dangerousarea.gollum.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "Gollum门店服务")
@Slf4j
@RestController
@RequestMapping("/store")
public class StoreController extends BaseController{

    @Autowired
    private StoreService storeService;

    @Autowired
    private HttpServletRequest request;

    @ApiOperation("创建门店")
    @PostMapping("")
    public CommonResult create(@RequestBody Store store){
        store.setBrandId(getLoginBrandId());
        return storeService.createStore(store, request);
    }

    @ApiOperation(value = "删除门店")
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") Long id){
        return storeService.deleteStore(id, getLoginBrandId(), request);
    }

    @ApiOperation(value = "获取品牌所有门店")
    @GetMapping("/brand")
    public CommonResult<List<Store>> getBrandStores(){
        return storeService.getBrandStores(getLoginBrandId(), request);
    }
}
