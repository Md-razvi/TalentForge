package com.hireAI.TalentForge.security;

import com.hireAI.TalentForge.service.Interface.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    private final CustomUserDetailService userDetailService;
    public JwtAuthenticationFilter(JwtService jwtServiceImpl,CustomUserDetailService userDetailService){
        this.jwtService=jwtServiceImpl;
        this.userDetailService=userDetailService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        if(authHeader== null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        String jwtToken=authHeader.substring(7);
        String username=jwtService.extractUsername(jwtToken);
        UserDetails userDetails= userDetailService.loadUserByUsername(username);
        if(username.equals(userDetails.getUsername()) && !jwtService.isTokenExpired(jwtToken)){
            UsernamePasswordAuthenticationToken authenticationToken=new
                    UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);




    }
}
