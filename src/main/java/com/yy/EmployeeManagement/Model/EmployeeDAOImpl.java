package com.yy.EmployeeManagement.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yy.EmployeeManagement.Domain.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//private static String connectionUrl = "jdbc:sqlserver://Beangrinder.bcit.ca:1433;databaseName=jspweb;user=javastudent;password=compjava";
	private static String connectionUrl = "jdbc:sqlserver://DESKTOP-V4UNJHJ:1433;databaseName=jspweb;user=sa;password=sa";
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> allEmployees = new ArrayList<Employee>();
		try {
			try (Connection connection = DriverManager.getConnection(connectionUrl)) {
				String getAllEmployee = "SELECT id, firstName, lastName, dob FROM [DBO].[A00911103_EMPLOYEES]";
				try (Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery(getAllEmployee)) {
					while (resultSet.next()) {
						allEmployees.add(new Employee(resultSet.getString(1), resultSet.getString(2),
								resultSet.getString(3), resultSet.getDate(4)));
					}
				}
			}
			System.out.println("Done.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allEmployees;
	}

	@Override
	public void addEmployee(Employee employee) {
		try {
			try (Connection connection = DriverManager.getConnection(connectionUrl)) {
				String sql = new StringBuilder()
						.append("INSERT INTO [DBO].[A00911103_EMPLOYEES]([ID],[firstName],[lastName],[dob])")
						.append("VALUES(?, ?, ?, ?);").toString();

				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					preparedStatement.setString(1, employee.getID());
					preparedStatement.setString(2, employee.getFirstName());
					preparedStatement.setString(3, employee.getLastName());
					preparedStatement.setDate(4, employee.getDOB());
					int rowsAffected = preparedStatement.executeUpdate();
					System.out.println(rowsAffected + " row(s) inserted");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Employee findEmployeeByID(String id) throws EmployeeCRUDException {
		List<Employee> employees = new ArrayList<Employee>();
		try {
			try (Connection connection = DriverManager.getConnection(connectionUrl)) {
				String sql = new StringBuilder()
						.append("SELECT id, firstName, lastName, dob FROM [DBO].[A00911103_EMPLOYEES] WHERE")
						.append(" ID = ?").toString();
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					preparedStatement.setString(1, id);
					try (ResultSet resultSet = preparedStatement.executeQuery()) {
						while (resultSet.next()) {
							employees.add(new Employee(resultSet.getString(1), resultSet.getString(2),
									resultSet.getString(3), resultSet.getDate(4)));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        //not found employee.
		if (employees.isEmpty())
		{
			return null;
		}
		//found more than one records.
		if (employees.size() != 1) {
			throw new EmployeeCRUDException("Found unexpected results!");
		}
		return employees.get(0);
	}

	@Override
	public void deleteEmployee(String id) {
		try {
			try (Connection connection = DriverManager.getConnection(connectionUrl)) {
				String sql = new StringBuilder().append("DELETE FROM [DBO].[A00911103_EMPLOYEES] WHERE")
						.append(" ID = ?").toString();
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					preparedStatement.setString(1, id);
					int rowsAffected = preparedStatement.executeUpdate();
					System.out.println(rowsAffected + " row(s) deleted");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getEmployeesTotal() {
		int total = 0;
		try {
			try (Connection connection = DriverManager.getConnection(connectionUrl)) {
				String sql = "SELECT COUNT(*) FROM [DBO].[A00911103_EMPLOYEES]";
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
						ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.next()) {
						total = resultSet.getInt(1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	/*
	 * 
	 * select * from (select ROW_NUMBER() over (order by id desc) as rn, * from
	 * dbo.A00911103_Employees) as x where rn between 3 and 6
	 * 
	 */
	@Override
	public List<Employee> getPagedEmployees(int startRows, int recordPerPage) {
		List<Employee> allEmployees = new ArrayList<Employee>();
		try {
			try (Connection connection = DriverManager.getConnection(connectionUrl)) {
				String sql = new StringBuilder().append(
						"SELECT id, firstName, lastName, dob FROM (SELECT ROW_NUMBER() OVER (ORDER BY id DESC) as rn, * from dbo.A00911103_Employees) as x ")
						.append("WHERE rn BETWEEN ? and ?").toString();
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					preparedStatement.setInt(1, startRows + 1);
					preparedStatement.setInt(2, startRows + recordPerPage);
					try (ResultSet resultSet = preparedStatement.executeQuery()) {
						while (resultSet.next()) {
							allEmployees.add(new Employee(resultSet.getString(1), resultSet.getString(2),
									resultSet.getString(3), resultSet.getDate(4)));
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allEmployees;

	}

	@Override
	public List<Employee> getPagedEmployees(int startRows, int recordsPerPage, String sortItemName, String order) {
		
		List<Employee> allEmployees = new ArrayList<Employee>();
		//set default order to desc.
		String orderBy = sortItemName == null ? "" : "ORDER BY " + sortItemName + " ";
		String orderDescription = order == null ? "" : order;
		try {
			try (Connection connection = DriverManager.getConnection(connectionUrl)) {
				String sql = new StringBuilder().append(
						"SELECT id, firstName, lastName, dob FROM (SELECT ROW_NUMBER() OVER (ORDER BY id DESC) as rn, * from dbo.A00911103_Employees) as x ")
						.append("WHERE rn BETWEEN ? and ? ")
						.append(orderBy)
						.append(orderDescription)
						.toString();
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					preparedStatement.setInt(1, startRows + 1);
					preparedStatement.setInt(2, startRows + recordsPerPage);
					
					try (ResultSet resultSet = preparedStatement.executeQuery()) {
						while (resultSet.next()) {
							allEmployees.add(new Employee(resultSet.getString(1), resultSet.getString(2),
									resultSet.getString(3), resultSet.getDate(4)));
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allEmployees;
	}
}
