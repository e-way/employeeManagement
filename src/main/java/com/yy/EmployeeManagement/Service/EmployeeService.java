package com.yy.EmployeeManagement.Service;

import java.util.List;

import com.yy.EmployeeManagement.Domain.Employee;
import com.yy.EmployeeManagement.Domain.Pagination;
import com.yy.EmployeeManagement.Factory.DaoFactory;
import com.yy.EmployeeManagement.Model.EmployeeDAO;

public class EmployeeService {
	private final EmployeeDAO employeeDAO = DaoFactory.getDaoFactory().getEmployeeDao();

	public String LoginAsRole(String userName, String password) {
		if (Validator.isUserRole(userName, password)) {
			return "role_user";
		} else if (Validator.isAdminRole(userName, password)) {
			return "role_admin";
		} else {
			return "not_allowed";
		}
	}

	public List<Employee> ListEmployees() {
		return employeeDAO.getAllEmployees();
	}

	public void AddEmployee(Employee employee) throws Exception {
		if (Validator.isValidEmployee(employee)) {
			if (employeeDAO.findEmployeeByID(employee.getID()) != null) {
				throw new EmployeeServiceException(
						"Result Code:502 Description: ID already exists for another employee.");
			} else {
				employeeDAO.addEmployee(employee);
			}
		} else {
			throw new EmployeeServiceException("Result Code:901 Description: invalid employee data!");
		}
	}

	public Employee FindEmployee(String employeeId) throws Exception {
		if (!Validator.isValidID(employeeId)) {
			throw new EmployeeServiceException("The employee Id is empty, please check.");
		}
		return employeeDAO.findEmployeeByID(employeeId);
	}

	public void DeleteEmployee(String employeeId) throws EmployeeServiceException {
		if (!Validator.isValidID(employeeId)) {
			throw new EmployeeServiceException("The employee Id is empty, please check.");
		}
		employeeDAO.deleteEmployee(employeeId);
	}

	public Pagination paginate(int currentPageNumber) {
		Pagination page = new Pagination();

		page.setCurrentPageNumber(currentPageNumber);

		int totalRecords = employeeDAO.getEmployeesTotal();
		page.setTotalRecords(totalRecords);

		int recordsPerPage = page.getRecordsPerPage();
		int startRowNumber = (page.getCurrentPageNumber() - 1) * recordsPerPage;

		List<Employee> employeeList = employeeDAO.getPagedEmployees(startRowNumber, recordsPerPage);
		page.setEmployeeList(employeeList);

		return page;
	}
}
