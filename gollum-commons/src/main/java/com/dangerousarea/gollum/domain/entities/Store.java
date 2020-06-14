package com.dangerousarea.gollum.domain.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("gollum_store")
public class Store extends BaseEntity {
    @ApiModelProperty(value = "类型(暂时无用传1)")
    private Integer type;
    @ApiModelProperty(value = "品牌ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long brandId;
    @ApiModelProperty(value = "门店名", required = true)
    private String name;
    @ApiModelProperty(value = "门店坐标")
    private String locationCode;
    @ApiModelProperty(value = "门店地址")
    private String address;
    @ApiModelProperty(value = "负责人手机号")
    private String mobilePhone;
    @ApiModelProperty(value = "店长姓名")
    private String manager;
    @ApiModelProperty(value = "经度")
    private Double lng;
    @ApiModelProperty(value = "纬度")
    private Double lat;
}
