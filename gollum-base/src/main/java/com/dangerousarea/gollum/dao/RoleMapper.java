package com.dangerousarea.gollum.dao;

import com.dangerousarea.gollum.domain.entities.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    int create(Role role);
}
