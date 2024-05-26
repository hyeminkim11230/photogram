package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http.csrf().disable(); // csrf토큰 비활성화
        http.authorizeRequests()
                .antMatchers("/","/user/**","/subscribe/**","/comment/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/signin") //Get
                .loginProcessingUrl("/auth/signin")//POST ->스프링 시큐리티가 로그인 프로세스진행
                .defaultSuccessUrl("/");
            return http.build();
    }
}
