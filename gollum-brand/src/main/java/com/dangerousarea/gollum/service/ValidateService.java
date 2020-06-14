package com.dangerousarea.gollum.service;

import com.dangerousarea.gollum.domain.entities.BrandAccount;
import com.dangerousarea.gollum.domain.entities.Validate;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;

public interface ValidateService {
    void sendPasswordResetEmail(SimpleMailMessage email);
    int insertNewResetRecord(Validate validate, BrandAccount account, String token);
    List<Validate> findUserByResetToken(String resetToken);
    boolean validateLimitation(String email, long requestPerDay, long interval, String token);
    boolean sendValidateLimitation(String email, long requestPerDay, long interval);
}
