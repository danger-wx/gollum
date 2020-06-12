package com.dangerousarea.gollum.controller.authentication;


import com.dangerousarea.gollum.common.define.ContentDefine;
import com.dangerousarea.gollum.domain.entities.validate.ValidateCode;
import com.dangerousarea.gollum.domian.JwtUser;
import com.dangerousarea.gollum.service.security.SmsCodeSender;
import com.dangerousarea.gollum.service.security.ValidateCodeGenerator;
import com.dangerousarea.gollum.service.impl.UserDetailsServiceImpl;
import com.dangerousarea.gollum.tool.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "Gollum品牌登陆服务")
@RestController
@RequestMapping("/api")
public class AuthenticationRestController {
    private Logger log= LoggerFactory.getLogger(AuthenticationRestController.class);
    private RequestCache requestCache = new HttpSessionRequestCache();// 会将http请求缓存起来
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Autowired
    private ObjectMapper objectMapper;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @ApiOperation(value = "登陆")
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Map<String, String> authenticationRequest,
                                                       HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.get(ContentDefine.ACCOUNT),
                authenticationRequest.get(ContentDefine.PASSWORD)
        );
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.get(ContentDefine.ACCOUNT));

        final String token = jwtTokenUtil.generateToken(userDetails);
        Map resultMap = new HashMap();
        resultMap.put("userInfo", userDetails);
        resultMap.put("authenticate", objectMapper.writeValueAsString(authentication));
        resultMap.put("token", token);

       /* SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("【引发跳转的请求的是:{}】",targetUrl);
            // 引发跳转的请求是已Html结尾的,直接跳转到登录页面
            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                try{
                    redirectStrategy.sendRedirect(request,response,"login.html");// 跳转用户自定义页面
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }*/

        // Return the token
        return ResponseEntity.ok(resultMap);
    }

    @ApiOperation(value = "刷新TOKEN")
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(refreshedToken);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
        ValidateCode smsCode = smsCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,smsCode);
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        smsCodeSender.send(mobile, smsCode.getCode());
    }
}
