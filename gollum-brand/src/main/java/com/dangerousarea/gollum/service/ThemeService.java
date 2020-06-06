package com.dangerousarea.gollum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.Theme;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ThemeService extends IService<Theme> {

    CommonResult<Theme> create(Theme theme, HttpServletRequest request);

    CommonResult delete(Long themeId, Long brandId, HttpServletRequest request);

    CommonResult<Theme> edit(Theme theme, HttpServletRequest request);

    /**
     * 主题上架
     * @param themeId
     * @param brandId
     * @param request
     * @return
     */
    CommonResult upperOrLower(Long themeId, Long brandId, Boolean upper, HttpServletRequest request);

    CommonResult<List<Theme>> getThemesByBrandId(Long brandId, HttpServletRequest request);

    CommonResult<List<Theme>> getThemesByStoreId(Long brandId, Long storeId, HttpServletRequest request);
}
