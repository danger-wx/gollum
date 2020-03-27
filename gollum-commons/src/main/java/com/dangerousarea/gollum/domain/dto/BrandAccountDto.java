package com.dangerousarea.gollum.domain.dto;

import com.dangerousarea.gollum.domain.entities.BrandAccount;
import com.dangerousarea.gollum.domain.entities.Permission;
import com.dangerousarea.gollum.domain.entities.Role;
import lombok.Data;

import java.util.List;

@Data
public class BrandAccountDto extends BrandAccount {
    private Integer brandStatus;
    List<Permission> permissions;
    List<Role> roles;
}
