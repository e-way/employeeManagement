<div class="easyui-panel" data-options="region:'east',split:true" title="Find an Employee By ID" style="width: 100%; padding: 30px 60px;" data-options="footer:'#footer'">
	<form action="/EmployeeManagement/EmployeeServlet/action.do?method=findEmployee" method="post">
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="id" style="width: 100%" data-options="label:'ID:',required:true">
		</div>
		<div>
			<input type="submit" value="Search" />
		</div>
	</form>
	<div id="footer" style="padding:5px;">
	   Footer Content.
	</div>
</div>
