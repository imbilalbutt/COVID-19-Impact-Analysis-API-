package com.imbilalbutt.bilalButtarbisoftv14.responses;


import java.io.Serializable;


// This output structure.
// actuall jwt request.
public class JwtResponse implements Serializable {
    // "/models/Authentication Response.java"

    //    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;

    public JwtResponse(String jwttoken) {
        this.jwtToken = jwttoken;
    }

    public String getToken() {
        return this.jwtToken;
    }
}