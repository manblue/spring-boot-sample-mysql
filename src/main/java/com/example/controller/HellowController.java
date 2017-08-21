package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hellow")
public class HellowController {

	private static Logger log = LoggerFactory.getLogger(HellowController.class);
	
	@RequestMapping("/hello")
	public String helloworld() {
		log.debug("Visit Hellow！");
		return "Hellow World!";
	}
	
	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		log.debug("Visit hello!");
		return "Hello " + name;
	}
	
	@RequestMapping("/{name}/hi")
	public String hi(@PathVariable  String name) {
		log.debug("Visit hi!");
		return "Hi " + name;
	}
}
