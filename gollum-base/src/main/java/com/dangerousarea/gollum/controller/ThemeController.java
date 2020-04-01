package com.dangerousarea.gollum.controller;

import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.domain.entities.Theme;
import com.dangerousarea.gollum.service.ThemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theme")
@Slf4j
public class ThemeController {

    @Autowired
    ThemeService themeService;

    @PostMapping
    public CommonResult<Theme> create(Theme theme){
        return null;
    }
}
