package com.otp.otpproject;

import com.otp.otpproject.Service.OTPFunction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class SNTest {

    @Test
    public void aa(){
        OTPFunction otpFunction = new OTPFunction();
        List<String> otpNum = otpFunction.getOTPNum(1004);
        for(String s :otpNum){
            System.out.println(s);
        }
    }
}
