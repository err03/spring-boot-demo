package com.example.demo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

	//在一个特定的时间执行这个方法 ~

	//cron 表达式
	//秒 分 时 日 月 周
	@Scheduled(cron = "0 54 1 * * 2")
	public void hello(){
		System.out.println("Hello, you exec");
	}
}
