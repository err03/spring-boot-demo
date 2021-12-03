package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//标注这个类是一个springboot的应用
@SpringBootApplication
public class DemoApplication {
	//将springboot应用启动
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
