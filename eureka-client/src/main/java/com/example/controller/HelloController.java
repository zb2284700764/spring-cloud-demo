package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class HelloController {

	private Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Value("${server.port}")
	String port;

	@RequestMapping("/hi/{name}")
//	public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
	public String home(@PathVariable("name") String name) {

		return "hi " + name + " ,i am from port:" + port;
	}



	@RequestMapping("/list")
	public ModelAndView list(ModelAndView modelAndView) {

		modelAndView.setViewName("modules/demo/demo");
		return modelAndView;
	}
}
