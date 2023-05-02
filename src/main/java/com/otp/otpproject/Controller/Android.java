package com.otp.otpproject.Controller;

import com.otp.otpproject.Domain.Model.AppInfo;
import com.otp.otpproject.Repository.AppInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class Android {
    private final AppInfoRepository appInfoRepository;
    @PostMapping("/api/registration")
    public ResponseEntity test3(String token, String serialNum){
        System.out.println(token+"-"+serialNum);
        AppInfo appInfo = AppInfo.builder().serialNumber(Integer.parseInt(serialNum)).appToken(token).build();
        AppInfo dbAppInfo = appInfoRepository.findById(Integer.parseInt(serialNum)).orElse(null);
        if(dbAppInfo == null){
            appInfoRepository.saveAndFlush(appInfo);
        }
        else {
            dbAppInfo.setAppToken(token);
            appInfoRepository.flush();
        }

        return new ResponseEntity("success",HttpStatus.OK);
    }
}
