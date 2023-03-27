package com.otp.otpproject.Service.Firebase;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

@Service
public class FcmService {
    public String sendNotification(String title, String body) throws FirebaseMessagingException {
        Message message = Message.builder().setAndroidConfig(AndroidConfig.builder()
                        .setRestrictedPackageName("com.util.otpapplication")
                        .setNotification(AndroidNotification.builder()
                                .setTitle(title)
                                .setBody(body)
                                .build()
                        )
                        .build()
                ).putData("requestId", "0")
                .setToken("")
                .build();
        String response = FirebaseMessaging.getInstance().send(message);
        return response;
    }
}
