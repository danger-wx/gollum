package com.dangerousarea.gollum.domain.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("gollum_theme")
public class Theme extends BaseEntity {
    @ApiModelProperty(value = "品牌ID")
    private Long brandId;
    @ApiModelProperty(value = "门店ID")
    private Long storeId;
    @ApiModelProperty(value = "主题名称")
    private String name;
    @ApiModelProperty(value = "主题单价")
    private Double unitPrice;
    @ApiModelProperty(value = "主题简介")
    private String introduction;
    @ApiModelProperty(value = "封面图")
    private String cover;
    @TableField(exist = false)
    private String fullCover;
    private Integer status;
}
