package com.dangerousarea.gollum.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game extends BaseEntity {
    private Long themeId;
    private Date startTime;
    private Date endTime;
    private Double income;
    private String remarks;
}
