package com.dangerousarea.gollum.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends BaseEntity{
    private String applicationCode;
    private String code;
    private String parentCode;
    private String link;
    private String linkType;
    private String languageKey;
    private String description;
    private Integer zIndex;
    private Integer dataStatus;
}
