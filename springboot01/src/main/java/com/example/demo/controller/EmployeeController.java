package com.example.demo.controller;

import com.example.demo.dao.DepartmentDao;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.pojo.Department;
import com.example.demo.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	DepartmentDao departmentDao;

	@RequestMapping("/emps")
	public String list(Model model){
		Collection<Employee> employees = employeeDao.getAll();
		model.addAttribute("emps",employees);

		return "emp/list";
	}//list

	@GetMapping("/emp")
	public String toAddPage(Model model){
		//查出所有部门的信息
		Collection<Department> departments = departmentDao.getDepartments();
		model.addAttribute("departments",departments);

		return "emp/add";
	}

	@PostMapping("/emp")
	public String addEmp(Employee employee){
		//添加的操作：forward

		employeeDao.save(employee);	//保存员工的信息，调用底层
		return "redirect:/emps";
	}

	//去到员工的修改页面
	@GetMapping("/emp/{id}")
	public String toUpdateEmp(@PathVariable("id") Integer id,Model model){

		//查出原来的数据

		Employee employee = employeeDao.getEmployeeById(id);
		model.addAttribute("emp",employee);

		Collection<Department> departments = departmentDao.getDepartments();
		model.addAttribute("departments",departments);

		return "emp/update";
	}

	@PostMapping("/updateEmp")
	public String updateEmp(Employee employee){
		employeeDao.save(employee);
		return "redirect:/emps";
	}

	//删除员工
	@GetMapping("/delemp/{id}")
	public String delteEmp(@PathVariable("id") int id){
		employeeDao.delete(id);
		return "redirect:/emps";
	}
}
