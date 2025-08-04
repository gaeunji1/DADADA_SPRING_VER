package com.ktdsuniversity.proj.dadada.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("다다다 회원가입 이메일 인증");
        message.setText("인증코드는 [ " + code + " ] 입니다.");
        mailSender.send(message);
    }
}