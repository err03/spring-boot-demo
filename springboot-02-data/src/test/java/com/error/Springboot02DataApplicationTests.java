package com.error;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Springboot02DataApplicationTests {

	@Autowired
	DataSource dataSource;

	@Test
	void contextLoads() throws SQLException {
		System.out.println("database:"+dataSource);
		Connection connection = dataSource.getConnection();

		System.out.println(connection);

		//xxx template :sprringboot�Ѿ����úõ�ģ��bean���������� CRUD
		//jdbc
		//redis

		connection.close();
	}

}
