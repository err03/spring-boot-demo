package com.error.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class JDBCController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	//查询数据库的所有信息
	//没有实体类，数据库中的，怎么获取
	@GetMapping("/userList")
	public List<Map<String,Object>> userList(){
		String sql = "select * from user";
		List<Map<String, Object>> list_maps = jdbcTemplate.queryForList(sql);
		return list_maps;
	}

	@GetMapping("/addUser")
	public String addUser(){
		String sql = "insert into user values(2,'a')";
		jdbcTemplate.update(sql);
		return "add okk";
	}

	@GetMapping("/updateUser/{id}")
	public String updateUser(@PathVariable("id") int id){
		String sql = "update user set name = ? where id =" +id;

		//封装
		Object[] objects = new Object[1];
		objects[0] = "q";

		jdbcTemplate.update(sql,objects);
		return "update okk";
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") int id){
		String sql = "delete from user where id = ?";
		jdbcTemplate.update(sql,id);
		return "delete okk";
	}

}
