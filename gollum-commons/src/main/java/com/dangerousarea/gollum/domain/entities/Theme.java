package com.dangerousarea.gollum.domain.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("gollum_theme")
public class Theme extends BaseEntity {
    private Long brandId;
    private Long storeId;
    private String name;
    private String introduction;
    private String cover;
    @TableField(exist = false)
    private String fullCover;
    private Integer status;
}
