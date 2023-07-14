package com.board;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	//스프링시큐리티 암호화 스프링빈 등록 
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//로그인 처리
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
        
        //CSRF, CORS 공격 보안 비활성화
        http
           .csrf((csrf) -> csrf.disable())
           .cors((cors) -> cors.disable())
           .formLogin((formLogin) -> formLogin.disable());
		
		System.out.println("************* 스프링 시큐리티 설정 완료 !!! *************");
		
		return http.build();
	}
}
