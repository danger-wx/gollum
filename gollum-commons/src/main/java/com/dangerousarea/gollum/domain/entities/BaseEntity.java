package com.dangerousarea.gollum.domain.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    @ApiModelProperty(hidden = true)
    private Long id;
    @ApiModelProperty(hidden = true)
    private Integer dataStatus;
    @ApiModelProperty(hidden = true)
    private Date createTime;
}
