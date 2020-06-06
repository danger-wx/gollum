package com.dangerousarea.gollum.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangerousarea.gollum.common.define.DataStatusDefine;
import com.dangerousarea.gollum.common.define.ThemeDefine;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.common.result.ErrorCodes;
import com.dangerousarea.gollum.dao.ThemeMapper;
import com.dangerousarea.gollum.domain.entities.Theme;
import com.dangerousarea.gollum.service.ThemeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ThemeServiceImpl extends ServiceImpl<ThemeMapper, Theme> implements ThemeService {

    @Resource
    private ThemeMapper themeMapper;

    @Override
    public CommonResult<Theme> create(Theme theme, HttpServletRequest request) {
        if(ObjectUtil.isAllEmpty(theme.getBrandId(), theme.getStoreId(), theme.getName())){
            return CommonResult.error(ErrorCodes.PARAMETER_ERROR);
        }

        int result = themeMapper.insert(theme);
        if(result > 0) {
            return CommonResult.success(theme);
        }
        return CommonResult.error(ErrorCodes.FAIL_TO_INSERT);
    }

    @Override
    public CommonResult delete(Long themeId,Long brandId, HttpServletRequest request) {
        UpdateWrapper<Theme> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("data_status", DataStatusDefine.DELETE)
                .eq("id", themeId)
                .eq("brand_id", brandId);

        int result = themeMapper.update(null, updateWrapper);
        if(result > 0){
            return CommonResult.success(result);
        }
        return CommonResult.error(ErrorCodes.FAIL_TO_DELETE);
    }

    @Override
    public CommonResult<Theme> edit(Theme theme, HttpServletRequest request) {
        int result = themeMapper.updateById(theme);
        if(result > 0){
            return CommonResult.success(theme);
        }
        return CommonResult.error(ErrorCodes.FAIL_TO_UPDATE);
    }

    @Override
    public CommonResult upperOrLower(Long themeId, Long brandId,Boolean upper, HttpServletRequest request) {
        Theme theme = themeMapper.selectById(themeId);
        if(theme == null){
            return CommonResult.error(ErrorCodes.DATA_NOT_FOUND);
        }

        if(upper && ThemeDefine.Status.UPPER == theme.getStatus()){
            return CommonResult.success(1);
        }
        if(!upper && ThemeDefine.Status.LOWER == theme.getStatus()){
            return CommonResult.success(1);
        }


        UpdateWrapper<Theme> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", upper ? ThemeDefine.Status.UPPER : ThemeDefine.Status.LOWER)
                .eq("id", themeId)
                .eq("brand_id", brandId);

        int result = themeMapper.update(null, updateWrapper);
        if(result > 0){
            return CommonResult.success(result);
        }
        return CommonResult.error(ErrorCodes.FAIL_TO_UPDATE);
    }

    @Override
    public CommonResult<List<Theme>> getThemesByBrandId(Long brandId, HttpServletRequest request) {
        QueryWrapper<Theme> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("data_status", DataStatusDefine.NORMAL)
                .eq("brand_id", brandId);
        List<Theme> list = themeMapper.selectList(queryWrapper);
        if(list != null) {
            return CommonResult.success(list);
        }
        return CommonResult.error(ErrorCodes.UNKNOWN_ERROR);
    }

    @Override
    public CommonResult<List<Theme>> getThemesByStoreId(Long brandId, Long storeId, HttpServletRequest request) {
        QueryWrapper<Theme> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("data_status", DataStatusDefine.NORMAL)
                .eq("brand_id", brandId)
                .eq("store_id", storeId);
        List<Theme> list = themeMapper.selectList(queryWrapper);
        if(list != null) {
            return CommonResult.success(list);
        }
        return CommonResult.error(ErrorCodes.UNKNOWN_ERROR);
    }
}
