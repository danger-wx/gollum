package com.dangerousarea.gollum.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dangerousarea.gollum.domain.entities.Game;
import com.dangerousarea.gollum.domain.vo.GameVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameMapper extends BaseMapper<Game> {
    IPage<GameVo> selectPageVo(Page<Game> page, QueryWrapper<Game> queryWrapper);
}
