package com.dangerousarea.gollum.filter;

import cn.hutool.core.util.StrUtil;
import com.dangerousarea.gollum.service.impl.UserDetailsServiceImpl;
import com.dangerousarea.gollum.tool.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private String tokenHeader = "Authorization";

    private String tokenHead = "Bearer";

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestHeader  =  request.getHeader(this.tokenHeader);
        String authToken = null;
        String username = null;

        log.info(requestHeader);

        if(StrUtil.isNotEmpty(requestHeader) && requestHeader.startsWith(this.tokenHead)){
            authToken = requestHeader.substring(tokenHead.length());
            try {
                //根据令牌信息获取用户名
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            }catch (IllegalArgumentException e){
                logger.error("an error occured during getting username from the token ",e);
            }catch (ExpiredJwtException e){
                logger.error("the token is Expried and not invalid anymore",e);
            }
        } else {
            log.error("couldn't find Bearer String,will ignore the request");
        }

        log.info("checking Authentication with username : " + username);
        if(StrUtil.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails =  userDetailsService.loadUserByUsername(username);

            if(jwtTokenUtil.validateToken(authToken,userDetails)){
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info("authorication user: "+username+", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
