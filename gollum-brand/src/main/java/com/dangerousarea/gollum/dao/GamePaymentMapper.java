package com.dangerousarea.gollum.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dangerousarea.gollum.domain.entities.GamePayment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GamePaymentMapper extends BaseMapper<GamePayment> {
}
