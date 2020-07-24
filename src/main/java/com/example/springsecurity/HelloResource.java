package com.example.springsecurity;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {
    @RequestMapping({"/"})
    public String hello(){
        return "Hello world";
    }
}
