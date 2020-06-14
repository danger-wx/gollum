package com.dangerousarea.gollum.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dangerousarea.gollum.dao.ValidateMapper;
import com.dangerousarea.gollum.domain.entities.BrandAccount;
import com.dangerousarea.gollum.domain.entities.Validate;
import com.dangerousarea.gollum.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ValidateServiceImpl implements ValidateService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ValidateMapper validateMapper;

    /**
     * 发送邮件：@Async进行异步调用发送邮件接口
     * @param email
     */
    @Override
    @Async
    public void sendPasswordResetEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    /**
     * 在gollum_validate表中插入一条validate记录，accountId，email属性来自gollum_brand_account表，token由UUID生成
     * @param validate
     * @param account
     * @param token
     * @return
     */
    @Override
    public int insertNewResetRecord(Validate validate, BrandAccount account, String token) {
        validate.setAccountId(account.getId());
        validate.setEmail(account.getEmail());
        validate.setResetToken(token);
        validate.setType("passwordReset");
        validate.setCreateTime(new Date());
//        validate.setGmtModified(new Date());
        return validateMapper.insert(validate);
    }

    /**
     * gollum_validate表中，通过token查找重置申请记录
     * @param resetToken
     * @return
     */
    @Override
    public List<Validate> findUserByResetToken(String resetToken) {

        return validateMapper.selectList(new QueryWrapper<Validate>().eq("reset_token", resetToken));
    }

    /**
     * 验证连接是否失效：链接有两种情况失效 1.超时 2.最近请求的一次链接自动覆盖之前的链接（待看代码）
     * @param email
     * @param requestPerDay
     * @param interval
     * @return
     */
    @Override
    public boolean validateLimitation(String email, long requestPerDay, long interval, String token) {
        List<Validate> validates = validateMapper.selectList(new QueryWrapper<Validate>().eq("email", email));
        // 有记录才会调用该函数，只需判断是否超时
        Optional validate = validates.stream().map(Validate::getModifyTime).max(Date::compareTo);
        Date dateOfLastRequest = new Date();
        if (validate.isPresent()) dateOfLastRequest = (Date) validate.get();
        long intervalForLastRequest = new Date().getTime() - dateOfLastRequest.getTime();

        Optional lastRequestToken = validates.stream().filter(x-> x.getResetToken().equals(token)).map(Validate::getModifyTime).findAny();
        Date dateOfLastRequestToken = new Date();
        if (lastRequestToken.isPresent()) {
            dateOfLastRequestToken = (Date) lastRequestToken.get();
        }
        return intervalForLastRequest <= interval * 60 * 1000 && dateOfLastRequest == dateOfLastRequestToken;
    }


    /**
     * 验证是否发送重置邮件：每个email的重置密码每日请求上限为requestPerDay次，与上一次的请求时间间隔为interval分钟。
     * @param email
     * @param requestPerDay
     * @param interval
     * @return
     */
    @Override
    public boolean sendValidateLimitation(String email, long requestPerDay, long interval) {
        List<Validate> validates = validateMapper.selectList(new QueryWrapper<Validate>().eq("email", email));
        // 若查无记录，意味着第一次申请，直接放行
        if (validates.isEmpty()) {
            return true;
        }
        // 有记录，则判定是否频繁申请以及是否达到日均请求上线
        long countTodayValidation = validates.stream().filter(x-> DateUtil.isSameDay(x.getModifyTime(), new Date())).count();
        Optional validate = validates.stream().map(Validate::getModifyTime).max(Date::compareTo);
        Date dateOfLastRequest = new Date();
        if (validate.isPresent()) dateOfLastRequest = (Date) validate.get();
        long intervalForLastRequest = new Date().getTime() - dateOfLastRequest.getTime();

        return countTodayValidation <= requestPerDay && intervalForLastRequest >= interval * 60 * 1000;
    }
}
