package com.yy.EmployeeManagement.Controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
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
import com.yy.EmployeeManagement.Service.IdAlreadyExistException;
import com.yy.EmployeeManagement.Service.InvalidEmployeeDataException;
import com.yy.EmployeeManagement.Service.ResultResponse;

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

		boolean isUserRole = request.isUserInRole("user");
		boolean isAdminRole = request.isUserInRole("admin");

		String method = request.getParameter("method");
		if (method != null) {
//			if (method.equals("login")) {
//				login(request, response);
//			}
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
			if (method.equals("removeEmployee")) {
				try {
					removeEmployee(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (method.equals("findEmployee")) {
				try {
					findEmployeeBy(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void findEmployeeBy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		EmployeeService service = new EmployeeService();
		String id = request.getParameter("id");
		try
		{
			Employee employee = service.FindEmployee(id);
			if (employee != null)
			{
				request.getSession().setAttribute("findName", employee.getLastName() +" "+ employee.getFirstName());
				findEmployeeResponse(request, "000", "Success.");
			}
			else
			{
				findEmployeeResponse(request, "801", "No match found");
			}
		}catch(Exception e)
		{
			findEmployeeResponse(request, "801", "No match found");
		}
		
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	private void removeEmployee(HttpServletRequest request, HttpServletResponse response)
			throws EmployeeServiceException, Exception {
		EmployeeService service = new EmployeeService();
		String id = request.getParameter("id");

		try
		{
			service.DeleteEmployee(id);
			removeEmployeeResponse(request, "001", "Deleted Successfully");
		}catch(Exception e)
		{
			removeEmployeeResponse(request, "902", "Deleted UnSuccessful");
		}
		
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
	
	private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		EmployeeService service = new EmployeeService();
		String id = request.getParameter("id");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String dob = request.getParameter("dob");
		
		try {
			Employee employee = new Employee(id, firstName, lastName, Date.valueOf(dob));
			service.AddEmployee(employee);
			AddEmployeeResponse(request, "200", "Success");
			
		} catch (IdAlreadyExistException e1) {
			request.getSession().setAttribute("addInfo", e1.getMessage());
			AddEmployeeResponse(request, "502", "ID already exists for another employee.");
			
		} catch (InvalidEmployeeDataException e2) {
			AddEmployeeResponse(request, "901", "Description: invalid employee data!");
		} catch (Exception e3) {
			AddEmployeeResponse(request, "500", e3.toString());
		}

		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
	private void findEmployeeResponse(HttpServletRequest request, String code, String description)
	{
		addInfoToResponse(request, "find", code, description);
	}
	
	private void removeEmployeeResponse(HttpServletRequest request, String code, String description)
	{
		addInfoToResponse(request, "delete", code, description);
	}
	
	private void AddEmployeeResponse(HttpServletRequest request, String code, String description)
	{
		addInfoToResponse(request, "add", code, description);
	}
	
	private void addInfoToResponse(HttpServletRequest request, String prefix,  String code, String description)
	{
		request.getSession().setAttribute(prefix+"ResponseCode", code);
		request.getSession().setAttribute(prefix+"ResponseDescription", description);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/index.jsp");
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

	private String getJsonEmployeeString(Pagination pagination) throws JsonProcessingException {
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

//	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		EmployeeService service = new EmployeeService();
//		String userName = request.getParameter("username");
//		String passWord = request.getParameter("password");
//
//		String role = service.LoginAsRole(userName, passWord);
//		if (role.equals("role_user")) {
//			request.setAttribute("message", "login as user.");
//			request.getSession().setAttribute("loginUser", "user");
//
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/Employees.jsp");
//			dispatcher.include(request, response);
//
//			dispatcher = request.getRequestDispatcher("/Logout.jsp");
//			dispatcher.include(request, response);
//
//			return;
//		}
//		if (role.equals("role_admin")) {
//			request.getSession().setAttribute("loginUser", "admin");
//
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/Employees.jsp");
//			dispatcher.forward(request, response);
//		
//			return;
//		}
//		if (role.equals("not_allowed")) {
//			request.setAttribute("message", "not allowed!");
//			request.getRequestDispatcher("/message.jsp").forward(request, response);
//			return;
//		}
//	}
}
