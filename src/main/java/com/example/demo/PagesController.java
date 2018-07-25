package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagesController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello from CNE training class!!!";
    }

    @GetMapping("/math/pi")
    public String renderPi(){
        return "3.141592653589793";
    }
}
