package com.dangerousarea.gollum.controller.authentication;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dangerousarea.gollum.common.define.ContentDefine;
import com.dangerousarea.gollum.common.result.CommonResult;
import com.dangerousarea.gollum.common.result.ErrorCodes;
import com.dangerousarea.gollum.domain.entities.BrandAccount;
import com.dangerousarea.gollum.domain.entities.Validate;
import com.dangerousarea.gollum.domain.entities.validate.ValidateCode;
import com.dangerousarea.gollum.domian.JwtUser;
import com.dangerousarea.gollum.service.BrandAccountService;
import com.dangerousarea.gollum.service.ValidateService;
import com.dangerousarea.gollum.service.security.SmsCodeSender;
import com.dangerousarea.gollum.service.security.ValidateCodeGenerator;
import com.dangerousarea.gollum.service.impl.UserDetailsServiceImpl;
import com.dangerousarea.gollum.tool.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Autowired
    private BrandAccountService brandAccountService;

    @Autowired
    private ValidateService validateService;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String from;

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
        resultMap.put("authenticated", authentication.isAuthenticated());
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


    /**
     * 发送忘记密码邮件请求，每日申请次数不超过5次，每次申请间隔不低于1分钟
     * @param email
     * @param request
     * @return
     */
    @ApiOperation(value = "发送忘记密码邮件", notes = "发送忘记密码邮件")
    @RequestMapping(value = "/sendValidationEmail", method = {RequestMethod.POST})
    public CommonResult<String> sendValidationEmail(@ApiParam("邮箱地址") @RequestParam("email") String email,
                                                    HttpServletRequest request){
        BrandAccount account = brandAccountService.getOne(new QueryWrapper<BrandAccount>().eq("email", email));
        if (account == null){
            return CommonResult.error(ErrorCodes.PARAMETER_ERROR, "该邮箱所属用户不存在");
        }else {
            if (validateService.sendValidateLimitation(email, 5,1)){
                // 若允许重置密码，则在gollum_validate表中插入一行数据，带有token
                Validate validate = new Validate();
                validateService.insertNewResetRecord(validate, account, UUID.randomUUID().toString());
                // 设置邮件内容
                String appUrl = request.getScheme() + "://" + request.getServerName();
                SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
                passwordResetEmail.setFrom(from);
                passwordResetEmail.setTo(email);
                passwordResetEmail.setSubject("忘记密码");
                passwordResetEmail.setText("您正在申请重置密码，请点击此链接重置密码: \n" + appUrl + "/validate/reset?token=" + validate.getResetToken());
                validateService.sendPasswordResetEmail(passwordResetEmail);
                return CommonResult.success("发送成功");
            }else {
                return CommonResult.error(ErrorCodes.UNKNOWN_ERROR, "操作过于频繁，请稍后再试！");
            }
        }
    }

    /**
     * 将url的token和数据库里的token匹配，成功后便可修改密码，token有效期为60分钟
     * @param token
     * @param password
     * @param confirmPassword
     * @return
     */
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public CommonResult<String> resetPassword(@ApiParam("token") @RequestParam("token") String token,
                                              @ApiParam("密码") @RequestParam("password") String password,
                                              @ApiParam("密码确认") @RequestParam("confirmPassword") String confirmPassword){
        // 通过token找到validate记录
        List<Validate> validates = validateService.findUserByResetToken(token);
        if (validates == null){
            return CommonResult.error(ErrorCodes.DATA_NOT_FOUND, "该重置请求不存在");
        }else {
            Validate validate = validates.get(0);
            if (validateService.validateLimitation(validate.getEmail(), Long.MAX_VALUE, 60, token)){
                Long accountId = validate.getAccountId();
                if (password.equals(confirmPassword)) {
                    BrandAccount account = new BrandAccount();
                    account.setId(accountId);
                    account.setPassword(passwordEncoder.encode(password));
                    brandAccountService.updateById(account);
                    return CommonResult.success("修改成功");
                }else {
                    return CommonResult.error(ErrorCodes.PARAMETER_ERROR, "确认密码和密码不一致，请重新输入");
                }
            }else {
                return CommonResult.error(ErrorCodes.FAIL_TO_UPDATE, "该链接失效");
            }
        }
    }
}
