<div class="easyui-panel" data-options="region:'east',split:true,footer:'#footerFindEmployee'" title="Find an Employee By ID" style="width: 100%; padding: 30px 60px;">
	<form action="/EmployeeManagement/EmployeeServlet/action.do?method=findEmployee" method="post">
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="id" style="width: 100%" data-options="label:'ID:',required:true">
		</div>
		<div align="right">
			<input type="submit" value="Search" />
		</div>
	</form>
	<% 
		String name = (String)request.getSession().getAttribute("findName");
		String code = (String)request.getSession().getAttribute("findResponseCode");
		String description = (String)request.getSession().getAttribute("findResponseDescription");
	%>
	<div id="footerFineEmployee" style="padding:5px;">
		<p>Found <%=name%></p>
		<p>Result Code:<%=code%> Description:<%=description%></p>  
	</div>
</div>
