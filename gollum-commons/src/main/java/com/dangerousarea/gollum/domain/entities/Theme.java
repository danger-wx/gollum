package com.dangerousarea.gollum.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Theme extends BaseEntity {
    private Long brandId;
    private Long storeId;
    private String name;
    private String introduction;
    private String cover;
    private String fullCover;
    private Integer status;
}
