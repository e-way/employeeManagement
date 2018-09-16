package com.yy.EmployeeManagement.Model;

import java.util.List;

public interface EmployeeDAO {

	public List<Employee> getAllEmployees();

	public void addEmployee(Employee employee);

	public Employee findEmployeeByID(String id) throws Exception;

	public void deleteEmployee(String id);
}
