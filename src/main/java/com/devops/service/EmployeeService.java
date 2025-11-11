package com.devops.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devops.model.Employee;
import com.devops.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee getEmployeeById(Long employeeId){
		return employeeRepository.findById(employeeId).get();	
	}
	
	public Employee updateEmployee(Long employeeId, Employee employeeDetails){
		Employee employee = employeeRepository.findById(employeeId).get();
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		return employeeRepository.save(employee);
	}

	
	public void deleteEmployee(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).get();
		employeeRepository.delete(employee);
	}
}