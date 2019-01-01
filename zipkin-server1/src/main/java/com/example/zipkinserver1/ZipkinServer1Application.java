package com.example.zipkinserver1;

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
public class ZipkinServer1Application {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServer1Application.class, args);
    }

    private static final Logger logger = LoggerFactory.getLogger(ZipkinServer1Application.class);

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


    @RequestMapping("/hi")
    public String hi() {
        logger.info("calling trace zipkin-server1 [hi] method");
        return restTemplate.getForObject("http://localhost:9083/miya", String.class);
    }

    @RequestMapping("/info")
    public String info() {
        logger.info("calling trace zipkin-server1 [info] method ");
        return "i'm zipkin-server1 [info] method message";
    }
}

