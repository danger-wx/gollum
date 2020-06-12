package com.dangerousarea.gollum.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dangerousarea.gollum.dao.GamePaymentMapper;
import com.dangerousarea.gollum.domain.entities.GamePayment;
import com.dangerousarea.gollum.service.GamePaymentService;
import org.springframework.stereotype.Service;

@Service
public class GamePaymentServiceImpl extends ServiceImpl<GamePaymentMapper, GamePayment> implements GamePaymentService {
}
