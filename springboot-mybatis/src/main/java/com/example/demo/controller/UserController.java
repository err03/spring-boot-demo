package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UserController {

	@Autowired
	private UserMapper userMapper;

	@GetMapping("/queryUserList")
	public List<User> queryUserList(){
		List<User> userList = userMapper.queryUserList();
		for(User user : userList){
			System.out.println(user);
		}
		return userList;
	}

	@GetMapping("/addUser")
	public String addUser(){
		userMapper.addUser(new User(5,"jim"));
		return "Add success";
	}

	@GetMapping("/updateUser")
	public String updateUser(){
		userMapper.updateUser(new User(5,"lon"));
		return "update success";
	}

	@GetMapping("/deleteUser")
	public String deleteUser(){
		userMapper.deleteUser(5);
		return "delete success";
	}
}
