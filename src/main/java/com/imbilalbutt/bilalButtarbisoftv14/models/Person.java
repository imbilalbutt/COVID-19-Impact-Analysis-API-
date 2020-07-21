package com.imbilalbutt.bilalButtarbisoftv14.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Repository;

@Repository
public class Person {

    private String name;
    private String password;
    private String role;

    public Person() {
        this.name = "name";
        this.password = "password";
        this.role = "role";
    }

    public Person(@JsonProperty("username") String name, @JsonProperty("password") String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
