package com.otp.otpproject.Controller;

import com.otp.otpproject.Service.UpgradeAuthority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> collect = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        if(collect.contains("2")){
            return "redirect:/";
        }
        return "OTPAuth";
    }
    @PostMapping("/OTPAuth")
    public String singInOTPPost(){
        upgradeAuthority.upgrade2FAuthority();
        return "redirect:/test";
    }
}
