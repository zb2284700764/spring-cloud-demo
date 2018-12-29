package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

	private Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Value("${server.port}")
	String port;

	@RequestMapping("/hi")
	public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {

		return "hi " + name + " ,i am from port:" + port;
	}
}
