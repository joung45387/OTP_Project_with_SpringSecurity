package com.otp.otpproject.Controller;

import com.otp.otpproject.Domain.DTO.UserDTO;
import com.otp.otpproject.Domain.Model.User;
import com.otp.otpproject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignUp {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/signup")
    public String signUp(){
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignUp(UserDTO userDTO){
        User user = userRepository.findByUsername(userDTO.getUsername());
        if(user!=null){
            System.out.println("이미 존재하는 아이디");
        }
        else {
            user = User.builder()
                    .username(userDTO.getUsername())
                    .password(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                    .serialNumber(userDTO.getSerialNumber())
                    .build();
            userRepository.saveAndFlush(user);
        }
        return "redirect:/";
    }
}
