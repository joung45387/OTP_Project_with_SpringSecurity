package com.otp.otpproject.Domain.Model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppInfo {
    @Id
    private int serialNumber;
    @Setter
    private String appToken;
}
