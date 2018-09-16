package com.yy.EmployeeManagement.Tests;

import java.util.List;

import org.junit.Test;

import com.yy.EmployeeManagement.Domain.Employee;
import com.yy.EmployeeManagement.Model.EmployeeDAO;
import com.yy.EmployeeManagement.Model.EmployeeDAOImpl;
import com.yy.EmployeeManagement.Service.EmployeeService;

public class Tests {
	
	
	@Test
	public void test()
	{
		EmployeeService service = new EmployeeService();
		List<Employee>employees = service.ListEmployees();
		
		EmployeeDAO dao = new EmployeeDAOImpl();
		
		List<Employee>e = dao.getEmployees(2, 3);
		
		var p = service.paginate(1);
		System.out.println(dao.getEmployeesTotal());
	}

}
