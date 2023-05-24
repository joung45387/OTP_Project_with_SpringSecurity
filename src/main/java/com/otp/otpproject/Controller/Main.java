package com.otp.otpproject.Controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.otp.otpproject.Domain.Model.AppInfo;
import com.otp.otpproject.Repository.AppInfoRepository;
import com.otp.otpproject.Security.PrincipalDetails;
import com.otp.otpproject.Service.Firebase.FcmService;
import com.otp.otpproject.Service.TEST;
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
public class Main {
    private final FcmService fcmService;
    private final AppInfoRepository appInfoRepository;
    private final TEST test;
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

    @GetMapping("/test2")
    public String test2(){
        AppInfo appInfo = appInfoRepository.findById(111111).orElse(null);
        if(appInfo!=null){
            try {
                String s = fcmService.sendNotification("님 해킹당함", "ㅅㄱ", appInfo.getAppToken());
            } catch (FirebaseMessagingException e) {
                throw new RuntimeException(e);
            }
        }
        return "main";
    }
    @PostMapping("/test3")
    public String test3(String token, String serialNum){
        System.out.println(token+"-"+serialNum);
        return "main";

    }
    @GetMapping("/test3")
    public String test3(){
        System.out.println(test.getA());
        test.up();
        return "main";
    }

    public int aaa(){
        return 2;
    }
}
