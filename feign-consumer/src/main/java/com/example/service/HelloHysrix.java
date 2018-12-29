package com.example.service;

import org.springframework.stereotype.Component;

/**
 * Feign 断路器实现
 */
@Component
public class HelloHysrix implements IHello {

    @Override
    public String sayHiFromClientOne(String name) {
        return "Feign Hysrix : " + name;
    }
}
