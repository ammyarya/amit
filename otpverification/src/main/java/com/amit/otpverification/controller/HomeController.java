package com.amit.otpverification.controller;

import com.amit.otpverification.service.SendOtpToMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private SendOtpToMailService sendOtpToMailService;

    @GetMapping("/")
    public String home(){
        return "Welcome to OTP Verification";
    }

    @PostMapping("sendOtp/{email}")
    public String sendOtpToMail(@PathVariable("email") String email ){
        sendOtpToMailService.sentOtpService(email);
        return "Otp Sent Successfully";
    }
}
