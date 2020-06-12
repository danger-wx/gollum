package com.dangerousarea.gollum.service;

import com.dangerousarea.gollum.common.result.CommonResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface StatisticsService {
    /**
     * 接待人次统计
     * @param brandId
     * @param request
     * @return
     */
    CommonResult<Map> receptionNumber(Long brandId, HttpServletRequest request);

    /**
     * 收入统计
     * @param brandId
     * @param request
     * @return
     */
    CommonResult<Map> income(Long brandId, HttpServletRequest request);

    /**
     *
     * @param brandId
     * @param request
     * @return
     */
    CommonResult<Map> games(Long brandId, HttpServletRequest request);
}
