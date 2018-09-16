package com.yy.EmployeeManagement.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static String connectionUrl = "jdbc:sqlserver://Beangrinder.bcit.ca:1433;databaseName=jspweb;user=javastudent;password=compjava";

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> allEmployees = new ArrayList<Employee>();
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			System.out.println("Connecting to db...");
			connection = DriverManager.getConnection(connectionUrl);
			System.out.println("Done.");

			String getAllEmployee = "SELECT id, firstName, lastName, dob FROM [DBO].[A00911103_EMPLOYEES]";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(getAllEmployee);
			while (resultSet.next()) {
				allEmployees.add(new Employee(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getDate(4)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return allEmployees;
	}

	@Override
	public void addEmployee(Employee employee) {
		Connection connection = null;
		try {
			System.out.println("Connecting to db...");
			connection = DriverManager.getConnection(connectionUrl);
			System.out.println("Done.");

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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	@Override
	public Employee findEmployeeByID(String id) throws Exception {
		List<Employee> employees = new ArrayList<Employee>();
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			System.out.println("Connecting to db...");
			connection = DriverManager.getConnection(connectionUrl);
			System.out.println("Done.");

			String sql = new StringBuilder()
					.append("SELECT id, firstName, lastName, dob FROM [DBO].[A00911103_EMPLOYEES] WHERE")
					.append(" ID = ?").toString();
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, id);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					employees.add(new Employee(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
							resultSet.getDate(4)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		if (employees.size() != 1) {
			throw new Exception("Found unexpected results!");
		}
		return employees.get(0);
	}

	@Override
	public void deleteEmployee(String id) {
		Connection connection = null;
		try {
			System.out.println("Connecting to db...");
			connection = DriverManager.getConnection(connectionUrl);
			System.out.println("Done.");

			String sql = new StringBuilder().append("DELETE FROM [DBO].[A00911103_EMPLOYEES] WHERE").append(" ID = ?")
					.toString();
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setString(1, id);
				int rowsAffected = preparedStatement.executeUpdate();
				System.out.println(rowsAffected + " row(s) deleted");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}
