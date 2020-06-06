package com.dangerousarea.gollum.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

@Mapper
public interface StatisticsMapper {
    /**
     * 接待人次统计
     * @param brandId
     * @param startTime
     * @param endTime
     * @return
     */
    Integer receptionNumber(@Param("brandId") Long brandId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * 收入统计
     * @param brandId
     * @param startTime
     * @param endTime
     * @return
     */
    Double income(@Param("brandId") Long brandId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
