package com.imbilalbutt.bilalButtarbisoftv14.controllers;

// This is the home, which I'm gonna get when I access the URL.

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home{

    @GetMapping("/user") public String user(){
        return "/user/index";
    }

    @GetMapping("/admin") public String admin(){
        return "admin";
    }

    @RequestMapping("/")
    public String root() {
        return "redirect:/csvData";
    }

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    @RequestMapping("/user/index")
    public String userIndex() {
        return "user/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
