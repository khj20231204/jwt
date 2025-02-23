package com.cos.jwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/* 현재 사용하지 않는 버전 => 최근 버전은 SecurityFilterChain으로 사용
public class SecurityConfig extends WebSecurityConfigurationAdapter{ 
   @Override
   protected void configure(HttpSecurity http) throws Exception{
   }
}*/

@Configuration
public class SecurityConfig{


   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
         .csrf(csrf -> csrf.disable())

   }
}