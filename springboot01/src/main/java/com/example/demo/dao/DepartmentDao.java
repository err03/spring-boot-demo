package com.example.demo.dao;

import com.example.demo.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.*;

//部门Dao （dao - data access object） 数据访问对象
@Repository
public class DepartmentDao {
	//模拟数据库中的数据

	private static Map<Integer, Department> departments = null;
	static {
		departments = new HashMap<Integer,Department>();	//创建一个部门表

		departments.put(101,new Department(101,"教学部"));
		departments.put(102,new Department(102,"市场部"));
		departments.put(103,new Department(103,"教研部"));
		departments.put(104,new Department(104,"运营部"));
		departments.put(105,new Department(105,"后勤部"));
	}//staic

	//获得所有部门信息
	public Collection<Department> getDepartments(){
		return departments.values();
	}//Collection

	//通过Id得到部门
	public Department getDepartment(Integer id){
		return departments.get(id);
	}//getDepartment

}//class
