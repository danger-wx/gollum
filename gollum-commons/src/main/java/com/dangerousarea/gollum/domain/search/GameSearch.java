package com.dangerousarea.gollum.domain.search;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class GameSearch extends Page {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long themeId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long storeId;
    private Date startTime;
    private Date endTime;
    private Integer players;
    private Double income;
}
