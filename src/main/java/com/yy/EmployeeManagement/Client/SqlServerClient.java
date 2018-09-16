package com.yy.EmployeeManagement.Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlServerClient {
	private static String connectionUrl = "jdbc:sqlserver://Beangrinder.bcit.ca:1433;databaseName=jspweb;user=javastudent;password=compjava";

	public static void SqlServerOp() {
		Connection connection = null;
		try {
			System.out.println("Connecting to db...");
			connection = DriverManager.getConnection(connectionUrl);
			System.out.println("Done.");
			
			String getAllEmployee = "SELECT id, firstName, lastName, dob FROM [DBO].[A00911103_EMPLOYEES]";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(getAllEmployee);
			while(resultSet.next())
			{
				System.out.println("ID:"+resultSet.getString(1)
				     +" FirstName:"+resultSet.getString(2)
				     +" LastName:"+resultSet.getString(3)+" DOB:"+resultSet.getDate(4));
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
