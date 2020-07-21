package com.imbilalbutt.bilalButtarbisoftv14.requests;

import java.io.Serializable;


// Input sructure to authenticate method, which take in username
// and password and post request.
public class JwtRequest implements Serializable {
    // "/models/AuthenticationRequest.java"


    // It is going to define the input arguments for my
    // authenticate method.
    // This is what user's gonna send in the post request.
    // And this class will define it's structure.

//    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;

    //need default constructor for JSON Parsing
    public JwtRequest()
    {

    }

    public JwtRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}