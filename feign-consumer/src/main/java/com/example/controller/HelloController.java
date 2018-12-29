package com.example.controller;

import com.example.service.IHello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @Autowired
    IHello hello;

    @RequestMapping("/hi")
    public String sayHi(@RequestParam String name) {

        return hello.sayHiFromClientOne(name+"(Feign)");
    }

}
