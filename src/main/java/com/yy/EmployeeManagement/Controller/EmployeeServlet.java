package com.yy.EmployeeManagement.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runner.Request;

import com.yy.EmployeeManagement.Domain.Pagination;
import com.yy.EmployeeManagement.Service.EmployeeService;

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method != null) {
			if (method.equals("paginate")) {
				paginate(request, response);
			}
		} else {
			paginate(request, response);
		}
	}

	private void paginate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentPageNumber = request.getParameter("currentPageNumber");
		if (currentPageNumber == null) {
			currentPageNumber = "1";
		}
		EmployeeService service = new EmployeeService();
		Pagination pagenation = service.paginate(Integer.valueOf(currentPageNumber));
		request.setAttribute("pagination", pagenation);
		request.getRequestDispatcher("/listEmployees.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if (method != null) {
			if (method.equals("login")) {
				this.login(request, response);
			}
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeService service = new EmployeeService();
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");

		String role = service.LoginAsRole(userName, passWord);
		if (role.equals("role_user")) {
			request.setAttribute("message", "login as user.");
		}
		if (role.equals("role_admin")) {
			request.setAttribute("message", "login as admin");
		}
		if (role.equals("not_allowed")) {
			request.setAttribute("message", "not allowed!");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

}
