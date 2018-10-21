package com.yy.EmployeeManagement.Controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.junit.runner.Request;

import com.yy.EmployeeManagement.Domain.Employee;
import com.yy.EmployeeManagement.Domain.PagedEmployees;
import com.yy.EmployeeManagement.Domain.Pagination;
import com.yy.EmployeeManagement.Service.EmployeeService;
import com.yy.EmployeeManagement.Service.EmployeeServiceException;

/**
 * Servlet implementation class EmployeeServlet
 */
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method != null) {
			if (method.equals("login")) {
				login(request, response);
			}
			if (method.equals("paginate")) {
				paginate(request, response);
			}
			if (method.equals("logout")) {
				logout(request, response);
			}
			if (method.equals("addEmployee")) {
				try {
					addEmployee(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (method.equals("removeEmployee")){
				try {
					removeEmployee(request, response);
				} catch (EmployeeServiceException e) {
					e.printStackTrace();
				}
			} 
		}
	}

	private void removeEmployee(HttpServletRequest request, HttpServletResponse response) throws EmployeeServiceException {
		EmployeeService service = new EmployeeService();
		String id = request.getParameter("id");
		
		service.DeleteEmployee(id);
		
	}

	private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		EmployeeService service = new EmployeeService();
		String id = request.getParameter("id");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String dob =  request.getParameter("dob");
		
		Employee employee = new Employee(id, firstName, lastName, Date.valueOf(dob));
		service.AddEmployee(employee);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
	}

	private void paginate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		
		
		String page = request.getParameter("page");
	
		EmployeeService service = new EmployeeService();
		Pagination pagination = service.paginate(Integer.valueOf(page), sort, order);
		
		response.getWriter().write(getJsonEmployeeString(pagination));
	}
	
	private String getJsonEmployeeString(Pagination pagination) throws JsonProcessingException
	{
		PagedEmployees employees = new PagedEmployees(pagination.getTotalRecords(), pagination.getEmployeeList());
		ObjectMapper mapper = new ObjectMapper();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		mapper.setDateFormat(dateFormat);
		String employeeJson = mapper.writeValueAsString(employees);
		
		return employeeJson;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeService service = new EmployeeService();
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");

		String role = service.LoginAsRole(userName, passWord);
		if (role.equals("role_user")) {
			request.setAttribute("message", "login as user.");
			request.getSession().setAttribute("loginUser", "user");
			response.sendRedirect(request.getContextPath() + "/userMain.jsp");
			return;
		}
		if (role.equals("role_admin")) {
			request.getSession().setAttribute("loginUser", "admin");
			response.sendRedirect(request.getContextPath() + "/adminMain.jsp");
			return;
		}
		if (role.equals("not_allowed")) {
			request.setAttribute("message", "not allowed!");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}

	}

}
