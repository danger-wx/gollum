package com.dangerousarea.gollum.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity{
    private Integer brandId;
    private String applicationCode;
    private String name;
    private String description;
    private Integer inlay;
    private String code;
    private Integer dataStatus;
}
