package com.otp.otpproject.Controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.otp.otpproject.Domain.Model.AppInfo;
import com.otp.otpproject.Domain.Model.User;
import com.otp.otpproject.Repository.AppInfoRepository;
import com.otp.otpproject.Security.PrincipalDetails;
import com.otp.otpproject.Service.Firebase.FcmService;
import com.otp.otpproject.Service.OTPFunction;
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
    private final OTPFunction otpFunction;
    private final AppInfoRepository appInfoRepository;
    private final FcmService fcmService;
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
    public String singInOTPPost(@AuthenticationPrincipal PrincipalDetails principalDetails, Integer userOTP, Model model){
        User user = principalDetails.getUser();
        List<Integer> serverOTPs = otpFunction.getOTPNum(user.getSerialNumber());
        if(serverOTPs.contains(userOTP)){
            upgradeAuthority.upgrade2FAuthority();
            AppInfo appInfo = appInfoRepository.findById(user.getSerialNumber()).orElse(null);
            if(appInfo!=null){
                try {
                    String s = fcmService.sendNotification("OTP로그인", user.getUsername()+"으로 로그인 완료했습니다.", appInfo.getAppToken());
                } catch (FirebaseMessagingException e) {
                    throw new RuntimeException(e);
                }
            }
            return "redirect:/test";
        }
        model.addAttribute("OTPError", "OTP번호가 맞지 않습니다.");
        return "OTPAuth";
    }
}
