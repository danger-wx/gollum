package com.dangerousarea.gollum.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    @ApiModelProperty(hidden = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty(hidden = true)
    private Integer dataStatus;
    @ApiModelProperty(hidden = true)
    private Date createTime;
}
