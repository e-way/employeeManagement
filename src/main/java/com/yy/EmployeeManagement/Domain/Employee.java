package com.yy.EmployeeManagement.Model;

import java.sql.Date;

public class Employee {
	private String ID;
	private String firstName;
	private String lastName;
	private Date DOB;
	
	public Employee(String iD, String firstName, String lastName, Date dOB) {
		super();
		ID = iD;
		this.firstName = firstName;
		this.lastName = lastName;
		DOB = dOB;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}

}
