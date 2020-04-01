package com.dangerousarea.gollum.service.impl;

import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.Theme;
import com.dangerousarea.gollum.service.ThemeService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ThemeServiceImpl implements ThemeService {



    @Override
    public CommonResult<Theme> create(Theme theme, HttpServletRequest request) {
        return null;
    }

    @Override
    public CommonResult delete(Long themeId, HttpServletRequest request) {
        return null;
    }

    @Override
    public CommonResult<Theme> edit(Theme theme, HttpServletRequest request) {
        return null;
    }

    @Override
    public CommonResult<List<Theme>> getThemesByBrandId(Long brandId, HttpServletRequest request) {
        return null;
    }

    @Override
    public CommonResult<List<Theme>> getThemesByStoreId(Long storeId, HttpServletRequest request) {
        return null;
    }
}
