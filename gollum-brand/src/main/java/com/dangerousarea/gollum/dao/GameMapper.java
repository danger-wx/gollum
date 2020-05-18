package com.dangerousarea.gollum.dao;

import cn.hutool.db.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dangerousarea.gollum.domain.entities.Game;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameMapper extends BaseMapper<Game> {
    IPage<Game> selectPageVo(Page page, QueryWrapper<Game> queryWrapper);
}
