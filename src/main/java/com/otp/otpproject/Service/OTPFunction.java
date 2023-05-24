package com.otp.otpproject.Service;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
public class OTPFunction {
    public List<String> getOTPNum(int serialNum){
        List<String> otps = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        for(int i=0;i<60;i++){
            LocalDateTime time = now.plusSeconds(i);
            int year = time.getYear();
            int monthValue = time.getMonthValue();
            int dayOfMonth = time.getDayOfMonth();
            int hour = time.getHour();
            int minute = time.getMinute();
            int second = time.getSecond();
            long l = (long) serialNum * monthValue * dayOfMonth * (hour+1) * (minute+1) * (second+1)  / year * 37;
            StringBuilder sb = new StringBuilder(String.format("%06d", l%1000000));
            otps.add(sb.reverse().toString());
        }

        return otps;
    }
}
