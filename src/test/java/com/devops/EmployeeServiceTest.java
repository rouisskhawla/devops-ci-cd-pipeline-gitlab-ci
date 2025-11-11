package com.devops;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.devops.model.Employee;
import com.devops.repository.EmployeeRepository;
import com.devops.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	
	@Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
	private EmployeeService employeeService;
    
    private Employee employee;

    @BeforeEach
    public void setup(){
        employee = Employee.builder()
        		.id(2L)
        		.firstName("Rh")
                .lastName("Fae")
                .emailId("ram@gmail.com")
                .build();
    }
	
	@Test 
	public void testCreateEmployeeService() {
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		Employee savedEmployee = employeeService.createEmployee(employee);
		assertThat(savedEmployee).isNotNull();
	    }
   
    @Test 
    public void testGetEmployeeByIdService() {
		when(employeeRepository.findById(2L)).thenReturn(Optional.of(employee));
    	Employee emp = employeeService.getEmployeeById(2L);
        assertEquals(emp.getId(), 2L);  
    }
 
    @Test 
    public void testGetAllEmployeesService() {
    	List<Employee> list = new ArrayList<Employee>();
    	Employee employee1 = Employee.builder()
         		.id(3L)
         		.firstName("Ruue")
                .lastName("wew")
                .emailId("rwew@gmail.com")
                .build();
    	list.add(employee);
    	list.add(employee1);
    	when(employeeRepository.findAll()).thenReturn(list);
		List<Employee> empList = employeeService.getAllEmployees();
        assertThat(empList).isNotNull();    
        assertEquals(empList.size(),2);
    }
    
	@Test
	public void testUpdateEmployeeService() {
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		when(employeeRepository.findById(2L)).thenReturn(Optional.of(employee));
        employee.setEmailId("maro@gmail.com");
        Employee updatedEmployee = employeeService.updateEmployee(2L, employee);
        assertEquals(updatedEmployee.getEmailId(),"maro@gmail.com");
	}
	
	@Test
	public void testDeleteEmployeeService() {
		when(employeeRepository.findById(2L)).thenReturn(Optional.of(employee));
		doNothing().when(employeeRepository).delete(any(Employee.class));
		employeeService.deleteEmployee(2L);
        verify(employeeRepository, times(1)).delete(employee);
        }
	

}
