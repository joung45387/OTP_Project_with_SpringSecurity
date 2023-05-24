package com.otp.otpproject.Repository;

import com.otp.otpproject.Domain.Model.Image;
import com.otp.otpproject.Domain.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository  extends JpaRepository<Image, Long> {
    List<Image> findBySerialNum(String serialNum);
}
