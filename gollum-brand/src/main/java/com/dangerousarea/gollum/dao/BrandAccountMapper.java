package com.dangerousarea.gollum.dao;

import com.dangerousarea.gollum.domain.dto.BrandAccountDto;
import com.dangerousarea.gollum.domain.entities.BrandAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BrandAccountMapper {

    int create(BrandAccount account);

    BrandAccountDto selectByAccount(@Param("account") String account);
}
