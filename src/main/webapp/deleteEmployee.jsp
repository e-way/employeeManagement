<div class="easyui-panel" data-options="region:'east',split:true,footer:'#footerRemoveEmployee'" title="Remove an Employee" style="width: 100%; padding: 30px 60px;">
	<form action="/EmployeeManagement/EmployeeServlet/action.do?method=removeEmployee" method="post">
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="id" style="width: 100%" data-options="label:'ID:',required:true">
		</div>
		<div align="right">
			<input type="submit" value="Remove Employee" />
		</div>
	</form>
	<% 
	    String code = (String)request.getSession().getAttribute("deleteResponseCode"); 
	    String description = (String)request.getSession().getAttribute("deleteResponseDescription");
	%>
	<div id="footerRemoveEmployee" style="padding:5px;">
	      <p>Result Code:<%=code%> Description: <%=description%></p>
	   </div>
</div>
