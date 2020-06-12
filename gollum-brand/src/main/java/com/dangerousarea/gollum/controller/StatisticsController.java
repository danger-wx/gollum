package com.dangerousarea.gollum.controller;

import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@Api(tags = "统计服务")
@RestController
@RequestMapping("/statistics")
@Slf4j
public class StatisticsController extends BaseController {

    @Autowired
    private StatisticsService statisticsService;

    @ApiOperation("接待人次统计")
    @GetMapping("/receptionNumber")
    public CommonResult<Map> receptionNumber(){
        return statisticsService.receptionNumber(getLoginBrandId(), getRequest());
    }

    @ApiOperation("收入统计")
    @GetMapping("/income")
    public CommonResult<Map> income(){
        return statisticsService.income(getLoginBrandId(), getRequest());
    }

    @ApiOperation("场次统计")
    @GetMapping("/games")
    public CommonResult<Map> games(){
        return statisticsService.games(getLoginBrandId(), getRequest());
    }
}
