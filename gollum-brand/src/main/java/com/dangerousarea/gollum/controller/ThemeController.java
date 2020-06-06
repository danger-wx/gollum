package com.dangerousarea.gollum.controller;

import com.dangerousarea.gollum.common.define.ThemeDefine;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.Theme;
import com.dangerousarea.gollum.service.ThemeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "主题服务")
@RestController
@RequestMapping("/theme")
@Slf4j
public class ThemeController extends BaseController {

    @Autowired
    ThemeService themeService;

    @Autowired
    HttpServletRequest request;

    @ApiOperation("创建主题")
    @PostMapping
    public CommonResult<Theme> create(@RequestBody Theme theme){
        theme.setBrandId(getLoginBrandId());
        if(theme.getStatus() == null) {
            //设置默认状态为下架
            theme.setStatus(ThemeDefine.Status.LOWER);
        }
        return themeService.create(theme, request);
    }

    @ApiOperation("编辑主题")
    @PutMapping
    public CommonResult<Theme> update(@RequestBody Theme theme){
        theme.setBrandId(getLoginBrandId());
        return themeService.edit(theme, request);
    }

    @ApiOperation("删除主题")
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable Long id){
        return themeService.delete(id, getLoginBrandId(), request);
    }

    @ApiOperation("主题上架")
    @PutMapping("/{id}/upper")
    public CommonResult upper(@PathVariable Long id){

        return themeService.upperOrLower(id, getLoginBrandId(), true, request);
    }

    @ApiOperation("下架")
    @PutMapping("/{id}/lower")
    public CommonResult lower(@PathVariable Long id){
        return themeService.upperOrLower(id, getLoginBrandId(), false, request);
    }

    @ApiOperation("获取品牌所有主题")
    @GetMapping("/all")
    public CommonResult<List<Theme>> getAll(){
        return themeService.getThemesByBrandId(getLoginBrandId(), request);
    }

    @ApiOperation("获取品牌门店下所有主题")
    @GetMapping("/{storeId}/all")
    public CommonResult<List<Theme>> getStoreTheme(@PathVariable Long storeId){
        return themeService.getThemesByStoreId(getLoginBrandId(), storeId, request);
    }
}
