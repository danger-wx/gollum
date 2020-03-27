package com.dangerousarea.gollum.dao;

import com.dangerousarea.gollum.domain.entities.BrandAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BrandAccountMapper {

    int create(BrandAccount account);
}
