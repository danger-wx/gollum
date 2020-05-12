package com.dangerousarea.gollum.domain.vo;

import com.dangerousarea.gollum.domain.entities.Brand;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BrandVo extends Brand {
    @ApiModelProperty(value = "密码",required=true)
    private String password;
}
