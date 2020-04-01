package com.dangerousarea.gollum.dao;

import com.dangerousarea.gollum.domain.entities.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BrandMapper {
    int create(Brand record);

    Brand selectByPrimaryKey(@Param("brandId") Long brandId);

    /**
     * 审核品牌
     * @param brandId
     * @param status
     * @return
     */
    int audit(@Param("brandId") Long brandId, @Param("status") Integer status);
}
