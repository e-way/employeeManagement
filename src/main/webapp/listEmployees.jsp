<%@page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <script type="text/javascript">
   		function pagenateHere(i){
   			var uri = "/EmployeeManagement/EmployeeServlet?method=paginate&currPageNO="+i;
   			window.location.href = uri;
   		}
    </script>
  	<table border="1" align="center">
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
						href="/EmployeeManagement/EmployeeServlet?method=paginate&currPageNO=${i}" 
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
  </body>
</html>