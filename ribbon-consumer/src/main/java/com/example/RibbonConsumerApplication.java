package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient  // 除了发现 Eureka 注册中心的服务之外，还可以发现其他注册中心的服务
//@EnableEurekaClient  // 发现 Eureka 注册中心的服务
@EnableHystrix  // 开启 Hystrix 断路器功能
public class RibbonConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonConsumerApplication.class, args);
	}


	/**
	 * LoadBalanced 表明 restTemplate 开启负载均衡功能，默认轮训
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
