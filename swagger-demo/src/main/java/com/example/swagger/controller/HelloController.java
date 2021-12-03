package com.example.swagger.controller;

import com.example.swagger.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping(value = "/hello")
	public String hello(){
		return "hello world";
	}

	//只要我们的接口中，返回值中存在实体类，他就会被扫描到Swagger中
	@PostMapping(value = "/user")
	public User user(){
		return new User();
	}
}
