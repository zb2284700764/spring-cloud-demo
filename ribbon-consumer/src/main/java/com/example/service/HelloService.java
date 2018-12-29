package com.example.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

	@Autowired
	RestTemplate restTemplate;

	/**
	 * hiService 方法
	 * 使用 HystrixCommand 增加断路器功能，当这个方法出错的时候调用熔断方法 hiError
	 * @param name
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "hiError")
	public String hiService(String name){
		// 此处使用服务名调用 而不是具体的地址
		return restTemplate.getForEntity("http://eureka-client-hi/hi?name=" + name, String.class).getBody();
	}

	/**
	 * 断路器方法
	 * @param name
	 * @return
	 */
	public String hiError(String name) {
		return "hi,"+name+",sorry,error!";
	}

}
