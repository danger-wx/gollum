package com.dangerousarea.gollum.domain.vo;

import com.dangerousarea.gollum.domain.entities.BrandAccount;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class BrandAccountVo extends BrandAccount {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long roleId;
}
