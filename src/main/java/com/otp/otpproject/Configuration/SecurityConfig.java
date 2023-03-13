package com.otp.otpproject.Configuration;

import com.otp.otpproject.Security.AuthenticationSuccessHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public BCryptPasswordEncoder cryptPW(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        http
                .authorizeHttpRequests()
                .antMatchers("/signin", "/signup", "/css/**", "/item/**", "/js/**", "/fragments/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .successHandler(authenticationSuccessHandler);
        return http.build();
    }
}
