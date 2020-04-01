package com.dangerousarea.gollum.service;

import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.Theme;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ThemeService {
    CommonResult<Theme> create(Theme theme, HttpServletRequest request);

    CommonResult delete(Long themeId, HttpServletRequest request);

    CommonResult<Theme> edit(Theme theme, HttpServletRequest request);

    CommonResult<List<Theme>> getThemesByBrandId(Long brandId, HttpServletRequest request);

    CommonResult<List<Theme>> getThemesByStoreId(Long storeId, HttpServletRequest request);
}
