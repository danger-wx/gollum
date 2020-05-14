package com.dangerousarea.gollum.domain.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("gollum_game")
public class Game extends BaseEntity {
    private Long brandId;
    private Long storeId;
    private Long themeId;
    private Date startTime;
    private Date endTime;
    private Integer playersNo;
    private Double income;
    private String remarks;
}
