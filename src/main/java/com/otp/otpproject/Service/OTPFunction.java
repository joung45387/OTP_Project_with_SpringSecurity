package com.otp.otpproject.Service;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
public class OTPFunction {
    public List<Integer> getOTPNum(int serialNum){
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        int year = now.getYear();
        int monthValue = now.getMonthValue();
        int dayOfMonth = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        List<Integer> li = new ArrayList<>();
        li.add(serialNum*year*monthValue*dayOfMonth*hour*minute%1000000);
        li.add(serialNum*year*monthValue*dayOfMonth*hour*(minute-1)%1000000);
        li.add(serialNum*year*monthValue*dayOfMonth*hour*(minute+1)%1000000);
        return li;
    }
}
