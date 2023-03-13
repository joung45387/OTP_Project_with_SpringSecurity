package com.otp.otpproject.Service;

import com.otp.otpproject.Domain.Model.User;
import com.otp.otpproject.Security.PrincipalDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UpgradeAuthority {
    public void upgrade2FAuthority(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(authentication.getAuthorities());
        updatedAuthorities.add(new SimpleGrantedAuthority("2"));
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        user.setRole(user.getRole()+1);
        new PrincipalDetails(user);
        Authentication newAuth = new UsernamePasswordAuthenticationToken(new PrincipalDetails(user), authentication.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
