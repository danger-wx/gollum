package com.dangerousarea.gollum.dao;

import com.dangerousarea.gollum.domain.entities.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreMapper {
    Store selectByPrimaryKey(@Param("id") Long id);

    int create(Store store);

    int delete(@Param("id") Long id);

    List<Store> selectByBrandId(@Param("brandId") Long brandId);
}
