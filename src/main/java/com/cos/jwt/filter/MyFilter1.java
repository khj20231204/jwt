package com.cos.jwt.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyFilter1 implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         System.out.println("====== 필터1 시작 ======");

         HttpServletRequest req = (HttpServletRequest)request;
         HttpServletResponse res = (HttpServletResponse)response;

         String headerAuth = req.getHeader("Authorization");
         System.out.println("headerAuth:"+headerAuth);

         System.out.println("====== 필터1 끝 ======");

         chain.doFilter(req, res); //다음 필터를 실행하면서 req, res를 넘겨준다
         
    }

   
}
