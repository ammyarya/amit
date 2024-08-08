package com.amit.otpverification.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;

@Service
public class SendOtpToMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sentOtpService(String email){
        String otp = generateOtp();
        try {
            sentOtpToMail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send OTP to " + email, e);
        }
    }

    private String generateOtp() {
//        generate 6 digit otp
        SecureRandom random = new SecureRandom();
        int otp = random.nextInt(900000) + 100000;
        return String.valueOf(otp);

    }

    private void sentOtpToMail(String email, String otp) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Your OTP IS");
        mimeMessageHelper.setText(otp);
        javaMailSender.send(mimeMessage);
    }
}
