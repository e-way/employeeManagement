<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/demo.css">
	<script type="text/javascript" src="jquery-easyui-1.6.6/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.6.6/jquery.easyui.min.js"></script>
</head>

<body>
	<div id="win" class="easyui-window" title="Login" style="width: 400px; height: 280px;">
		<form id="loginForm" action="/EmployeeManagement/EmployeeServlet?method=login" method="post" style="padding: 10px 20px 10px 40px;">
			<p>
				UserName: <input type="text" name="username">
			</p>
			<p>
				Password: <input type="password" name="password">
			</p>
			<p>
			    <input type="submit" value="Submit">
			</p>
		</form>
	</div>
</body>
</html>
