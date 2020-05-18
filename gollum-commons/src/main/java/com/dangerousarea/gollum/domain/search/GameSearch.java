package com.dangerousarea.gollum.domain.search;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.Date;

@Data
public class GameSearch extends Page {
    private Long themeId;
    private Long storeId;
    private Date startTime;
    private Date endTime;
    private Integer players;
    private Double income;
}
