package com.dangerousarea.gollum.domain.entities.validate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateCode {

    private String code;

    private LocalDateTime expireTime;


}
