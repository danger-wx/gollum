package com.dangerousarea.gollum.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dangerousarea.gollum.domain.dto.GameDto;
import com.dangerousarea.gollum.domain.entities.Game;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GameMapper extends BaseMapper<Game> {

    IPage<GameDto> selectPageVo(Page<Game> page, @Param("ew") QueryWrapper<Game> queryWrapper);
}
