package com.devops;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.devops.model.Employee;
import com.devops.repository.EmployeeRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	

    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
        		.id(1L)
        		.firstName("Rh")
                .lastName("Fae")
                .emailId("ram@gmail.com")
                .build();
    }
	
	@Test 
	public void testCreateEmployee() {
		Employee savedEmployee = employeeRepository.save(employee);
		assertThat(savedEmployee.getId()).isEqualTo(1L);	
	    }
	
	@Test
	public void testGetAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		assertThat(employees.size()).isGreaterThan(0);
	}
	
	@Test
	public void testGeEmployeeById() {
		Employee emp = employeeRepository.findById(1L).get();
		assertThat(emp.getId()).isEqualTo(1L);
	}

	@Test
	public void testUpdateEmployeeById() {
		employee.setEmailId("rouis.09@gamil.com");
		Employee updatedEmployee = employeeRepository.save(employee);
		assertThat(updatedEmployee.getEmailId()).isEqualTo("rouis.09@gamil.com");
	}
	
	@Test
	public void testDeleteEmployee() {
		employeeRepository.delete(employee);
		assertThat(employeeRepository.findById(1L)).isEmpty();
	}
	
	
	
}