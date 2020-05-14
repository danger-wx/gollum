package com.dangerousarea.gollum.domain.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_role_account")
public class RoleAccount extends BaseEntity{
    private String applicationCode;
    private Long roleId;
    private Long accountId;
}
