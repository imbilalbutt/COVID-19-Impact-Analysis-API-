package com.imbilalbutt.bilalButtarbisoftv14.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.imbilalbutt.bilalButtarbisoftv14.service.MyUserDetailsService;
import com.imbilalbutt.bilalButtarbisoftv14.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;


import io.jsonwebtoken.ExpiredJwtException;
//@Service
@Component // tuto and oreo
public class JwtRequestFilter extends OncePerRequestFilter {
    // "/filters/JwtRequestFilter.java"


    // This filter intercept every request once
    // and examine the header.

    // MyUserDetailsService
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    //    @Qualifier("my_bean")
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // this method actually does the job
    // To examine the incoming request for jwt in the header.
    // It's gonna look at the header and see if jwt is valid
    // if it finds valid jwt, it's gonna get the userDetails from userDetailsService
    // and save it in the secutiry context.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        //           autherizationHeader
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;
        // JWT Token is in the form "Wisdom token". Remove Wisdom word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Wisdom ")) {
            /// leaving first 7 letters, meaning leave Beaerer
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Wisdom String");
        }

        // Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Ye esa hi rahe ga UserDetails
            UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);

            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        // I need to continue to chain, so i do this: I need to say chain dot doFilter
        // asking the request and reponse, basically saying okay I have done job
        // I enetered the filter chain and I did my job and now the rest of the filters
        // whoever is doing what, you just continue doing your job.
        // It's basically handing off the control to the next filter in the filter chain.
        chain.doFilter(request, response);

    }

}
