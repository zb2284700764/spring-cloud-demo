package com.example.zipkinserver2;

import brave.sampler.Sampler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class ZipkinServer2Application {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServer2Application.class, args);
    }

    private static final Logger logger = LoggerFactory.getLogger(ZipkinServer2Application.class);

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }


    @RequestMapping("/miya")
    public String miya() {
        logger.info("calling trace zipkin-server2 [miya] method");
        return restTemplate.getForObject("http://localhost:9082/info", String.class);
    }

}

