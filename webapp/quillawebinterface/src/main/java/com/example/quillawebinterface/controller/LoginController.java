package com.example.quillawebinterface.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController{

    @GetMapping("/login")
    public String login(){
        return "access/login";
    }

    @GetMapping("/test")
    public String test(){
        return "access/login";
    }


}
