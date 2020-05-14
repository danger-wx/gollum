package com.dangerousarea.gollum.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dangerousarea.gollum.domain.entities.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
