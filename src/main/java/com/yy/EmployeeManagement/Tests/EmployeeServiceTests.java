package com.yy.EmployeeManagement.Tests;

import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.yy.EmployeeManagement.Domain.Employee;
import com.yy.EmployeeManagement.Domain.Pagination;
import com.yy.EmployeeManagement.Factory.DaoFactory;
import com.yy.EmployeeManagement.Model.EmployeeCRUDException;
import com.yy.EmployeeManagement.Model.EmployeeDAO;
import com.yy.EmployeeManagement.Service.EmployeeService;

public class EmployeeServiceTests {
	
	private EmployeeService _service;
	private String _employeeID1 = "A99999998";
	private String _employeeID2 = "A99999999";
	
	@Before
	public void setUp()
	{
		_service = new EmployeeService();
	}
	
	@Test
	public void testListEmployees()
	{
		List<Employee> employees = _service.ListEmployees();
		Assert.assertTrue(employees.size()>1);
	}
	
	@Test
	public void testAddEmployeeAndFindEmployee() throws Exception
	{
		String dateStr = "2009-03-03";
		Employee employee = new Employee(_employeeID1,"firstTestName","lastTestName",Date.valueOf(dateStr));
		_service.AddEmployee(employee);
		
		Employee employeeFound = _service.FindEmployee(_employeeID1);
		Assert.assertEquals(employee, employeeFound);
	}
	
	@Test(expected = EmployeeCRUDException.class)
	public void testDeleteEmployee() throws Exception
	{
		String dateStr = "2009-03-04";
		Employee employee = new Employee(_employeeID2,"firstTestName2","lastTestName2",Date.valueOf(dateStr));
		_service.AddEmployee(employee);
		
		_service.DeleteEmployee(_employeeID2);
		_service.FindEmployee(_employeeID2);
	}
	
	@Test
	public void testPaginate()
	{
		EmployeeDAO employeeDAO = DaoFactory.getDaoFactory().getEmployeeDao();
		int total = employeeDAO.getEmployeesTotal();
		int rowsPerPage = 5;
	
		int totalPages = ((total % rowsPerPage)==0?(total/rowsPerPage):((total/rowsPerPage) + 1));
		
		int currentPageNumber = 1;
		Pagination page = _service.paginate(currentPageNumber);
		
		Assert.assertEquals(totalPages, page.getTotalPages());
		Assert.assertEquals(currentPageNumber, page.getCurrentPageNumber());
		Assert.assertEquals(total, page.getTotalRecords());
		Assert.assertEquals(rowsPerPage, page.getEmployeeList().size());
	}
	
	@After
	public void teardown()
	{
		_service.DeleteEmployee(_employeeID1);
	}

}
