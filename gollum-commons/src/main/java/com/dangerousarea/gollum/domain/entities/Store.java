package com.dangerousarea.gollum.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store extends BaseEntity {
    private Integer type;
    private Long brandId;
    private String name;
    private String locationCode;
    private String address;
    private String mobilePhone;
    private String manager;
    private Double lng;
    private Double lat;
}
