package com.yy.EmployeeManagement.Model;

import java.util.List;

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
}
