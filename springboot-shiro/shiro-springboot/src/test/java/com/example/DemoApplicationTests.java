package com.example;

import com.example.service.UserService;
import com.example.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	UserServiceImpl userService;

	@Autowired
	UserService us;

	@Test
	void contextLoads() {
		System.out.println(userService.queryUserByName("abc"));
		System.out.println(us.queryUserByName("abc"));
	}

}
