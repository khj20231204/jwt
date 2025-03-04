package com.cos.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

import lombok.RequiredArgsConstructor;

/* 현재 사용하지 않는 버전 => 최근 버전은 SecurityFilterChain으로 사용
public class SecurityConfig extends WebSecurityConfigurationAdapter{ 
   @Override
   protected void configure(HttpSecurity http) throws Exception{
   }
}*/

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

   private final CorsConfigurationSource corsConfigurationSource;

   /* 구버전
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.csrf().disable();
       http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
       .and()
       .formLogin().disable()
       .httpBasic().disable()
       .authorizeRequests()
       .antMatchers("/api/v1/user/**")
       .access("hasRole('ROLE_USER') or hasRole(ROLE_MANAGER) or hasRole(ROLE_ADMIN)");
       .antMatchers("/api/v1/manager/**")
       .access("hasRole(ROLE_MANAGER) or hasRole(ROLE_ADMIN)");
       .antMatchers("/api/v1/admin/**")
       .access("hasRole(ROLE_ADMIN)");
       .anyRequest().permitAll();
   } */
   
   /* Spring Security 6 이상 버전 */
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            //.addFilterBefore(new MyFilter1(), BasicAuthenticationFilter.class)  
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            /* STATELESS(무상태) 정책은 Spring Security가 세션을 생성하거나 사용하지 않도록 설정하는 것입니다.
                즉, 클라이언트의 인증 정보를 서버의 세션에 저장하지 않음을 의미합니다.
                주로 JWT 기반 인증을 사용할 때 적용됩니다. */
            //.addFilter(corsConfigurationSource)
            .cors(cors -> cors.configurationSource(corsConfigurationSource))
            .formLogin(form -> form.disable()) //Spring Security의 기본 로그인 페이지(Form Login)를 비활성화
            .httpBasic(basic -> basic.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/user/**").hasAnyRole("USER", "MANAGER", "ADMIN")
                .requestMatchers("/api/v1/manager/**").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
            );

        return http.build();
    }
}


