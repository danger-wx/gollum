package com.dangerousarea.gollum.dao;

import com.dangerousarea.gollum.domain.dto.BrandAccountDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthMapper {

    BrandAccountDto selectByAccount(@Param("account") String account);
}
