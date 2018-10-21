<%@page language="java" %>

<!DOCTYPE html>
<html>
<head>
<title>Admin</title>
</head>
<body>
   <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
    <script type="text/javascript">
   		function pagenateHere(i){
   			var uri = "/EmployeeManagement/EmployeeServlet?method=paginate&currentPageNumber="+i;
   			window.location.href = uri;
   		}
    </script>
  	<table border="1">
  		<caption>Employee List</caption>
  		<tr>
  			<th>
  				ID
  			</th>
  			<th>
  				firstName
  			</th>
  			<th>
  				lastName
  			</th>
  			<th>
  				DOB
  			</th>
  		</tr>
  		<c:forEach var="employee" items="${pagination.employeeList}">
  			<tr>
  				<td>${employee.ID}</td>
  				<td>${employee.firstName}</td>
  				<td>${employee.lastName}</td>
  				<td>${employee.DOB}</td>
  			</tr>
  		</c:forEach>
  		<tr>
  			<td colspan="5" align="center">
				<c:forEach var="i" begin="1" end="${pagination.totalPages}" step="1">
					<a 
						href="/EmployeeManagement/EmployeeServlet?method=paginate&currentPageNumber=${i}" 
						style="text-decoration:none">
						[${i}]&nbsp;&nbsp;&nbsp;
					</a>
				</c:forEach>
  			</td>
  		</tr>
  		<tr>
  			<td colspan="5" align="center">
  				<select onchange="pagenateHere(this.value)">
					<c:forEach var="i" begin="1" end="${pagination.totalPages}">
  						<option value="${i}" ${pagination.currentPageNumber==i?'selected':''}>${i}</option>
					</c:forEach>
  				</select>
  			</td>
  		</tr>
  		<tr>
  			<td colspan="5" align="center">
  				The${pagination.currentPageNumber}
  				/Total:${pagination.totalPages}
  				/${pagination.recordsPerPage} rows per page
  			</td>
  		</tr>
  	</table>
  	<H2><a id="logout" title="Log out" href="logout.do?method=logout">Log out</a></H2>

    <form action="/EmployeeManagement/EmployeeServlet/action.do?method=addEmployee" method="post">
  		<table border="1">
  			<caption>Add Employee</caption>
  			<tr>
  				<th>ID</th>
  				<td>
  					<input type="text" name="id"/>
  				</td>
  			</tr>
  			<tr>
  				<th>FirstName</th>
  				<td>
  					<input type="text" name="firstName"/>
  				</td>
  			</tr>
  			<tr>
  				<th>LastName</th>
  				<td>
  					<input type="text" name="lastName"/>
  				</td>
  			</tr>
  			<tr>
  				<th>DOB</th>
  				<td>
  					<input type="text" name="dob"/>
  				</td>
  			</tr>
  			<tr>
  				<td colspan="2" align="center">
  					<input type="submit" value="Add Employee"/>
  				</td>
  			</tr>
   	    </table>
  	</form>
  	
  	 <form action="/EmployeeManagement/EmployeeServlet/action.do?method=removeEmployee" method="post">
  		<table border="1">
  			<caption>Remove Employee</caption>
  			<tr>
  				<th>ID</th>
  				<td>
  					<input type="text" name="id"/>
  				</td>
  			</tr>
  			<tr>
  				<td colspan="2" align="center">
  					<input type="submit" value="Remove Employee"/>
  				</td>
  			</tr>
   	    </table>
  	</form>

  </body>
</html>