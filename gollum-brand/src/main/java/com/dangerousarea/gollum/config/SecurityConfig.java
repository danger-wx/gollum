package com.dangerousarea.gollum.config;

import com.dangerousarea.gollum.entryPoint.JwtAuthenticationEntryPoint;
import com.dangerousarea.gollum.filter.JwtAuthenticationTokenFilter;
import com.dangerousarea.gollum.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;


/**
 * 安全配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //开启授权注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 装载 BCrypt 密码编码器
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//          .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //不创建session
            .and().authorizeRequests()
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            .antMatchers(HttpMethod.GET,"/").permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .antMatchers("/api/auth/**","/api/verifyCode/**","/brand/register","/brand/audit").permitAll()
            .antMatchers(
                    "/webjars/**",
                    "/resources/**",
                    "/swagger-ui.html",
                    "/swagger-resources/**",
                    "/v2/api-docs")
            .permitAll()
            .anyRequest().authenticated()
            .and().cors();//设置跨域


        //将自定义的security filter 验证添加再密码验证之前
        //这里就是token验证
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        //关闭page caching
        http.headers().cacheControl();
    }

    /**
     * 设置 UserDetailsService
     * 使用 BCrypt 进行密码的 hash
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
