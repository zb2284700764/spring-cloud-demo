package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.Map;

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


    /**
     * 使用此方法刷新 config 配置会导致 config-client 服务关闭
     * @return
     */
    @RequestMapping("/refresh")
    public String refresh() {
        System.out.println("refresh");

        String url = "http://localhost:8881/actuator/bus-refresh";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            MultiValueMap<String, String> requestBody  = new LinkedMultiValueMap<>();
            HttpEntity<MultiValueMap> request = new HttpEntity<MultiValueMap>(requestBody, headers);

            Object obj = restTemplate.postForEntity(url, request, Object.class);
        } catch (RestClientException e) {
            e.printStackTrace();
            return "refresh error";
        }
        System.out.println("refresh success");
        return "refresh success";
    }
}
