package com.dangerousarea.gollum.service.impl;

import cn.hutool.core.date.DateUtil;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.dao.StatisticsMapper;
import com.dangerousarea.gollum.service.StatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    private StatisticsMapper statisticsMapper;

    @Override
    public CommonResult<Map> receptionNumber(Long brandId, HttpServletRequest request) {
        Integer all = statisticsMapper.receptionNumber(brandId, null, null);
        Date now = new Date();
        Integer year = statisticsMapper.receptionNumber(brandId, DateUtil.beginOfYear(now), DateUtil.endOfYear(now));
        Integer month = statisticsMapper.receptionNumber(brandId, DateUtil.beginOfMonth(now), DateUtil.endOfMonth(now));
        Integer week = statisticsMapper.receptionNumber(brandId, DateUtil.beginOfWeek(now), DateUtil.endOfWeek(now));
        Integer day = statisticsMapper.receptionNumber(brandId, DateUtil.beginOfDay(now), DateUtil.endOfDay(now));
        Map<String, Integer> result = new HashMap<>();
        result.put("allReception", all != null ? all : 0);
        result.put("yearReception", year != null ? year : 0);
        result.put("monthReception", month != null ? month : 0);
        result.put("weekReception", week != null ? week : 0);
        result.put("dayReception", day != null ? day : 0);
        return CommonResult.success(result);
    }

    @Override
    public CommonResult<Map> income(Long brandId, HttpServletRequest request) {
        Double all = statisticsMapper.income(brandId, null, null);
        Date now = new Date();
        Double year = statisticsMapper.income(brandId, DateUtil.beginOfYear(now), DateUtil.endOfYear(now));
        Double month = statisticsMapper.income(brandId, DateUtil.beginOfMonth(now), DateUtil.endOfMonth(now));
        Double week = statisticsMapper.income(brandId, DateUtil.beginOfWeek(now), DateUtil.endOfWeek(now));
        Double day = statisticsMapper.income(brandId, DateUtil.beginOfDay(now), DateUtil.endOfDay(now));
        Map<String, Double> result = new HashMap<>();
        result.put("allIncome", all != null ? all : 0);
        result.put("yearIncome", year != null ? year : 0);
        result.put("monthIncome", month != null ? month : 0);
        result.put("weekIncome", week != null ? week : 0);
        result.put("dayIncome", day != null ? day : 0);
        return CommonResult.success(result);
    }

    @Override
    public CommonResult<Map> games(Long brandId, HttpServletRequest request) {
        Integer all = statisticsMapper.games(brandId, null, null);
        Date now = new Date();
        Integer year = statisticsMapper.games(brandId, DateUtil.beginOfYear(now), DateUtil.endOfYear(now));
        Integer month = statisticsMapper.games(brandId, DateUtil.beginOfMonth(now), DateUtil.endOfMonth(now));
        Integer week = statisticsMapper.games(brandId, DateUtil.beginOfWeek(now), DateUtil.endOfWeek(now));
        Integer day = statisticsMapper.games(brandId, DateUtil.beginOfDay(now), DateUtil.endOfDay(now));
        Map<String, Integer> result = new HashMap<>();
        result.put("allGames", all != null ? all : 0);
        result.put("yearGames", year != null ? year : 0);
        result.put("monthGames", month != null ? month : 0);
        result.put("weekGames", week != null ? week : 0);
        result.put("dayGames", day != null ? day : 0);
        return CommonResult.success(result);
    }
}
