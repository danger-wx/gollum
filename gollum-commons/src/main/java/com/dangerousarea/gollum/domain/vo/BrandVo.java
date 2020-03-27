package com.dangerousarea.gollum.domain.vo;

import com.dangerousarea.gollum.domain.entities.Brand;
import lombok.Data;

@Data
public class BrandVo extends Brand {
    private String password;
}
