package com.dangerousarea.gollum.domian.factory;

import com.dangerousarea.gollum.domain.dto.BrandAccountDto;
import com.dangerousarea.gollum.domain.entities.Role;
import com.dangerousarea.gollum.domian.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory(){}

    public static JwtUser create(BrandAccountDto account){
        return new JwtUser(
                account.getId(),
                account.getName(),
                account.getAccount(),
                account.getPassword(),
                account.getEmail(),
                mapToGrandAuthorties(account.getRoles()),
                account.getStatus().equals(1) ? true : false,           //status表示状态，冻结或锁定， 0表示锁定，1表示正常
                account.getDataStatus().equals(1) ? true : false,       //data_status表示物理删除标记 为1时表示正常，0表示删除
                true,true,
                account.getLastPasswordResetDate()
        );

    }

    private static List<GrantedAuthority> mapToGrandAuthorties(List<Role> roles) {
        return roles.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());


    }
}
