	<div class="easyui-panel" data-options="region:'east',split:true,footer:'#footerAddEmployee'" title="Add Employees" style="width: 100%; padding: 30px 60px;" >
		<form action="/EmployeeManagement/EmployeeServlet/action.do?method=addEmployee" method="post">
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="id" style="width: 100%" data-options="label:'ID:',required:true">
			</div>
			
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="firstName" style="width: 100%" data-options="label:'FirstName:',required:true">
			</div>
			
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="lastName" style="width: 100%" data-options="label:'LastName:',required:true">
			</div>
			
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="dob" style="width: 100%" data-options="prompt:'YYYY/MM/DD',label:'DOB',required:true">
			</div>

			<div align="right">
				<input type="submit" value="Add Employee" />
			</div>
		</form>
		<%
	    String code = (String)request.getSession().getAttribute("addResponseCode"); 
	    String description = (String)request.getSession().getAttribute("addResponseDescription");
		%>
		
		<div id="footerAddEmployee" style="padding:5px;">
	      <p>Result Code:<%=code%> Description: <%=description%></p>
	   </div>

	</div>
