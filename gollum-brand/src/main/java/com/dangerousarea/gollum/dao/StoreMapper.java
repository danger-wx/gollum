package com.dangerousarea.gollum.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dangerousarea.gollum.domain.entities.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreMapper extends BaseMapper<Store> {
    int create(Store store);

    List<Store> selectByBrandId(@Param("brandId") Long brandId);
}
