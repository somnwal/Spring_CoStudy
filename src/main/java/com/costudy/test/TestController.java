package com.costudy.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
	
	public TestController() {
		System.out.println("TestController Created!");
	}
	
	@GetMapping("/hello")
	public String hello() {
		System.out.println("Test Get Mapping");
		return "test";
	}
}
