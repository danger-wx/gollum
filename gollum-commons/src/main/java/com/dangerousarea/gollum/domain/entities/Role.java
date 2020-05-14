package com.dangerousarea.gollum.domain.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("pms_role")
public class Role extends BaseEntity{
    private Long brandId;
    private String applicationCode;
    private String name;
    private String description;
    private Integer inlay;
    private String code;
    private Integer dataStatus;
}
