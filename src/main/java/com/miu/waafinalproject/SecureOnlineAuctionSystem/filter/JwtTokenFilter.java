package com.miu.waafinalproject.SecureOnlineAuctionSystem.filter;


import com.miu.waafinalproject.SecureOnlineAuctionSystem.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.split(" ")[1].trim();
        boolean isTokenValid = jwtTokenUtil.validateToken(token);
        if (!isTokenValid) {
            filterChain.doFilter(request, response);
            return;
        }

        Claims claims = jwtTokenUtil.parseClaims(token);
        String email = claims.getSubject();

        List<Map<String, Object>> mapObj = (ArrayList<Map<String, Object>>) claims.get("ROLE");

        Set<GrantedAuthority> authorities = mapObj == null ? Collections.emptySet() :
                mapObj.stream()
                        .map(obj -> new SimpleGrantedAuthority(obj.get("title").toString()))
                        .collect(Collectors.toSet());

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        filterChain.doFilter(request, response);

    }






//    private final JwtTokenUtil tokenUtil;


//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//        if(authHeader == null) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        //Split the authorization by using space
//        //which will give you token
//        String token = authHeader.split(" ")[1];
//
//        //token validation
//        boolean result = tokenUtil.validateToken(token);
//        if(!result){
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        //get Authentication, save SecurityContext
////        1. parseClaim
//        Claims claims = tokenUtil.parseClaims(token);
//        String subject = claims.getSubject(); //1234,test@miu.edu
//        String[] info = subject.split(",");
//        String email = info[1];
//        ArrayList<String> roles = (ArrayList<String>) claims.get("roles");
//        Set<GrantedAuthority> authorities = roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role))
//                .collect(Collectors.toSet());
//
//        System.out.println("Role=========="+roles);
//        //we should not write password in place of credentials
//        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
////        SecurityContextHolder.getContext().setAuthentication(authentication);
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        context.setAuthentication(authentication);
//        SecurityContextHolder.setContext(context);
//
//        filterChain.doFilter(request, response);
//    }

}

