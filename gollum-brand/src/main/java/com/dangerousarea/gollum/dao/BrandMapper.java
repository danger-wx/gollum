package com.dangerousarea.gollum.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dangerousarea.gollum.domain.entities.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BrandMapper extends BaseMapper<Brand> {

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
