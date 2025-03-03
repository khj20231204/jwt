package com.cos.jwt.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

   /* 구버전
   @Bean
   public CorsFilter corsFilter(){
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true);
      config.addAllowedOrigin("*");
      config.addAllowedHeader("*");
      config.addAllowedMethod("*");
      source.registerCorsConfiguration("/api/**", config);

      return new CorsFilter(source);
   } */

   /*  Spring Boot 3+ 및 Spring Security 6 */
   @Bean
   public CorsConfigurationSource corsConfigurationSource() {
       CorsConfiguration config = new CorsConfiguration();
       config.setAllowCredentials(true); //서버에 쿠키, 인증 토큰 등의 정보가 포함된 요청을 보낼 수 있음.
       config.setAllowedOrigins(List.of("*")); // 모든 ip에 응답을 허용
       config.setAllowedHeaders(List.of("*")); //모든 header에 응답을 허용
       config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       source.registerCorsConfiguration("/api/**", config);

       return source;
   }
   
}
