package com.dangerousarea.gollum.domain.entities;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("gollum_game_payment")
public class GamePayment extends BaseEntity implements Serializable {
    @ApiModelProperty(value = "场次ID")
    private Long gameId;
    @ApiModelProperty(value = "支付类型(微信、支付宝、现金...)")
    private Integer type;
    @ApiModelProperty(value = "支付金额")
    private Double amount;
}
