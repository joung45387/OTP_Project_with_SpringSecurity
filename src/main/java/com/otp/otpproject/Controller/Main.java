package com.otp.otpproject.Controller;

import com.otp.otpproject.Security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class Main {
    @GetMapping("/")
    public String signUp(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        model.addAttribute("userName", principalDetails.getUsername());
        return "main";
    }
    @GetMapping("/test")
    public String test(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> collect = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        model.addAttribute("userName", "현재 접속아이디는 "+principalDetails.getUsername()+"이고 권한은"+collect+"입니다.");
        return "main";
    }
}
