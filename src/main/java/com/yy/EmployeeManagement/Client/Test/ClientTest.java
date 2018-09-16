package com.yy.EmployeeManagement.Client.Test;

import java.sql.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.yy.EmployeeManagement.Client.SqlServerClient;
import com.yy.EmployeeManagement.Model.Employee;
import com.yy.EmployeeManagement.Model.EmployeeDAO;
import com.yy.EmployeeManagement.Model.EmployeeDAOImpl;

public class ClientTest {

	SqlServerClient _client;
	
	@Before
	public void Setup()
	{
		
	}
	
	@Test
	public void TestGetAllEmployees() throws Exception
	{
		EmployeeDAO dao = new EmployeeDAOImpl();
		List<Employee>employees = dao.getAllEmployees();
		
		String dateStr = "1912-10-10";
		Date date = Date.valueOf(dateStr);
		//dao.addEmployee(new Employee("A01234577","Jack","London",date));
		
		Employee e = dao.findEmployeeByID("A01234570");
		dao.deleteEmployee("A01234570");
		
		Assert.assertNull(dao.findEmployeeByID("A01234570"));
		System.out.println("");
		//SqlServerClient.SqlServerOp();
	}
}
