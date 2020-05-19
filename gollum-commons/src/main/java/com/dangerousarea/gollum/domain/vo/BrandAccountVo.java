package com.dangerousarea.gollum.domain.vo;

import com.dangerousarea.gollum.domain.entities.BrandAccount;
import lombok.Data;

@Data
public class BrandAccountVo extends BrandAccount {
    private Long roleId;
}
