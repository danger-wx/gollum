package com.dangerousarea.gollum.domian;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;


@Data
public class JwtUser implements UserDetails {
    private final Long id;
    private final String name;
    private final String username;
    private final String password;
    private final String email;
    private final Long brandId;
    private final  Collection<? extends GrantedAuthority> authorities;
    private final boolean accountNonLocked;
    private final boolean enabled;
    private final boolean accountNonExpired;
    private final boolean credentialsNonExpired;
    private final Date lastPasswordResetDate;

    public JwtUser(Long id, String name, String username, String password, Long brandId, String email, Collection<? extends GrantedAuthority> authorities
            , boolean accountNonLocked, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, Date lastPasswordResetDate) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.brandId = brandId;
        this.email = email;
        this.authorities = authorities;
        this.accountNonLocked = accountNonLocked;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 账号是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    /**
     * 账号是否被冻结
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    /**
     * 账号密码是否过期（因为存在密码的有效期为30天的那种高级防护系统）
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    /**
     * 是否可用（自定义实现）
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
