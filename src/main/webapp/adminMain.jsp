<%@page language="java" %>

<!DOCTYPE html>
<html>
<head>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/themes/icon.css">
	<script type="text/javascript" src="jquery-easyui-1.6.6/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.6.6/jquery.easyui.min.js"></script>
</head>
</head>
<body>
  <table id="tt" class="easyui-datagrid" style="width:700px;height:250px"
			url="/EmployeeManagement/EmployeeServlet/action.do?method=paginate"
			title="Employees" iconCls="icon-save"
			rownumbers="true" pagination="true" data-options="pageSize:5,pageList:[5]">
		<thead>
			<tr>
				<th field="id" width="80">ID</th>
				<th field="firstName" width="120">FirstName</th>
				<th field="lastName" width="80" align="right">LastName</th>
				<th field="dob" width="80" align="right">DOB</th>
			</tr>
		</thead>
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