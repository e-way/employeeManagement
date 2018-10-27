package com.yy.EmployeeManagement.Domain;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class FormatedEmployee extends Employee{

	public FormatedEmployee(String iD, String firstName, String lastName, Date dOB) {
		super(iD, firstName, lastName, dOB);
	}
	
	@Override
	public Date getDOB()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		
		return null;
	}

}
