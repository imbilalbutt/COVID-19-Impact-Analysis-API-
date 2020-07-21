package com.imbilalbutt.bilalButtarbisoftv14.controllers;

import com.imbilalbutt.bilalButtarbisoftv14.requests.JwtRequest;
import com.imbilalbutt.bilalButtarbisoftv14.responses.JwtResponse;
import com.imbilalbutt.bilalButtarbisoftv14.service.MyUserDetailsService;
import com.imbilalbutt.bilalButtarbisoftv14.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController  // oreo and tuto
@CrossOrigin
public class JwtAuthenticationController {
    // "./HelloResource.java"

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    //      MyUserDetailsService
    @Autowired
    private MyUserDetailsService userDetailsService;

    // This is an end point which does the authentication.
    // Api
    // Take in user name and password as input argument
    // and will return jwt
    // users will be sending in the username and passwords, that's why it .POST type
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    //@Request body because : its parsing request body to get user name and
    // and password from request body.
    // returns jwt
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        // If reach at this line. meaning the authentication is sueccesfull
        // so now this method "createAuthenticationToken" need to return
        // jwt. In order to return jwt, I need to create jwt.
        // And for this purpose we created a JwtTokenUtil, which takes im
        // the userDetails in order to create jwt. pout of it.
        // I need to fetch details of user by using
        // "loadUserByUsername"

        // yeh ese hi rahe ga
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        // this "token" contains jwt. (i.e. gerateTojken)
        final String token = jwtTokenUtil.generateToken(userDetails);

        // After above line we have now jwt, now create authentication response
        // instance and pass it back by creating ResponseEntity out of it.
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {                                       // yeh ese hi rahe ga
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
