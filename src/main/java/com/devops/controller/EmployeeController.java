package com.devops.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devops.model.Employee;
import com.devops.service.EmployeeService;


@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
		
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}


	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}
	
	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}


	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable(value = "id") Long employeeId, @RequestBody Employee employeeDetails) {
		return employeeService.updateEmployee(employeeId, employeeDetails);
	}

	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable(value = "id") Long employeeId) {
		employeeService.deleteEmployee(employeeId);
	
	}

}
