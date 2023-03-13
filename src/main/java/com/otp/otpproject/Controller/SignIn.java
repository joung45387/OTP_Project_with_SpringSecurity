package com.otp.otpproject.Controller;

import com.otp.otpproject.Service.UpgradeAuthority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignIn {
    private final UpgradeAuthority upgradeAuthority;
    @GetMapping("/signin")
    public String singIn(){
        return "signin";
    }
    @GetMapping("/OTPAuth")
    public String singInOTP(){
        return "OTPAuth";
    }
    @PostMapping("/OTPAuth")
    public String singInOTPPost(){
        upgradeAuthority.upgrade2FAuthority();
        return "redirect:/test";
    }
}
