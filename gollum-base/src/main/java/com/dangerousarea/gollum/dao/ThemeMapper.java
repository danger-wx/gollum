package com.dangerousarea.gollum.dao;

import com.dangerousarea.gollum.domain.entities.Theme;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ThemeMapper {
    int create(Theme theme);

    int delete(@Param("themeId") Long themeId);

    int modify(Theme theme);

    Theme selectByPrimaryKey(@Param("id") Long id);

    List<Theme> selectByBrandId(@Param("brandId") Long brandId);

    List<Theme> selectByStoreId(@Param("storeId") Long storeId);
}
