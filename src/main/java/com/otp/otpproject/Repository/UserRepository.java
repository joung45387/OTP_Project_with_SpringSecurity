package com.otp.otpproject.Repository;

import com.otp.otpproject.Domain.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);
}
