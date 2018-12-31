package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;

import java.net.URI;

@RestController
@RefreshScope
public class GetController {

    @Value("${foo}")
    String foo;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/getFoo")
    public String getFoo() {

        return foo;
    }


    @RequestMapping("/refresh")
    public String refresh() {
        String url = "http://localhost:8881/actuator/bus-refresh";
        try {
            Object obj = restTemplate.postForObject(url, null, Object.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            return "refresh error";
        }
        return "refresh success";
    }
}
