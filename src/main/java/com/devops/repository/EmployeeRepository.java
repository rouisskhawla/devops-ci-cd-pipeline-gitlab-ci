package com.devops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devops.model.Employee;


public interface EmployeeRepository  extends JpaRepository<Employee, Long>{

}
