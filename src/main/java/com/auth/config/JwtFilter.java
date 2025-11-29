package com.auth.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth.service.JwtService;
import com.auth.service.MyUserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    ApplicationContext context;

    @Autowired
    JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String token = null;
        String username = null;
        String role=null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            username = jwtService.extractUserName(token);
            
            role=jwtService.extractRoles(token);
        }

        // If username is found & not authenticated yet
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =
                    context.getBean(MyUserDetailService.class).loadUserByUsername(username);

            // Validate token
            if (jwtService.validateToken(token, userDetails)) {

                // Create Authentication object
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,   // IMPORTANT: userDetails (not username)
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // **IMPORTANT: Set authentication in context**
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
