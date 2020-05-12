package com.dangerousarea.gollum.domain.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("gollum_brand")
public class Brand extends BaseEntity {
    @ApiModelProperty(value = "品牌名称")
    private String name;
    @ApiModelProperty(value = "品牌logo")
    private String logo;
    @ApiModelProperty(value = "注册手机号",required=true)
    private String mobilePhone;
    @ApiModelProperty(value = "手机号国家码",required=true)
    private String countryCode;
    private Integer status;
    @ApiModelProperty(value = "简介")
    private String introduction;
    private Date activeTime;
    private Date expireTime;
}
