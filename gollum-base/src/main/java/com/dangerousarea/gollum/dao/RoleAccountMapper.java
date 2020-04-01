package com.dangerousarea.gollum.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleAccountMapper {
    int create(@Param("applicationCode") String applicationCode,@Param("roleId") Long roleId,@Param("accountId") Long accountId);
}
