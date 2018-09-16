package com.yy.EmployeeManagement.Service;

import java.util.List;

import com.yy.EmployeeManagement.Domain.Employee;
import com.yy.EmployeeManagement.Domain.Pagination;
import com.yy.EmployeeManagement.Factory.DaoFactory;
import com.yy.EmployeeManagement.Model.EmployeeDAO;

public class EmployeeService {
	private final EmployeeDAO employeeDAO = DaoFactory.getDaoFactory().getEmployeeDao();

	public List<Employee> ListEmployees() {
		return employeeDAO.getAllEmployees();
	}

	public void AddEmployee(Employee employee) {
		employeeDAO.addEmployee(employee);
	}

	public Employee FindEmployee(String employeeId) throws Exception {
		return employeeDAO.findEmployeeByID(employeeId);
	}

	public void DeleteEmployee(String employeeId) {
		employeeDAO.deleteEmployee(employeeId);
	}
	
	public Pagination paginate(int currentPageNumber){
		Pagination page = new Pagination();
		
		page.setCurrentPageNumber(currentPageNumber);
		
		int totalRecords = employeeDAO.getEmployeesTotal();
		page.setTotalPages(totalRecords);
		
		int recordsPerPage = page.getRecordsPerPage();
		int startRowNumber = (page.getCurrentPageNumber() - 1) * recordsPerPage;
		
		List<Employee> employeeList = employeeDAO.getEmployees(startRowNumber, recordsPerPage);
		page.setEmployeeList(employeeList);
		
		return page;
	}
}
