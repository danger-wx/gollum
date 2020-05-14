package com.dangerousarea.gollum.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dangerousarea.gollum.domain.dto.BrandAccountDto;
import com.dangerousarea.gollum.domain.entities.BrandAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BrandAccountMapper extends BaseMapper<BrandAccount> {

}
