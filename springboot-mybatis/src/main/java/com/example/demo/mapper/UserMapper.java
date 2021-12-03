package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.*;

//这个注解表示了，这是一个mybatis的mapper类
@Mapper
@Repository
public interface UserMapper {
	List<User> queryUserList();

	User queryUserById(int id);

	int addUser(User user);

	int updateUser(User user);

	int deleteUser(int id);
}
