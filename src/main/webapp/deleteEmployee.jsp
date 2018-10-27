<div class="easyui-panel" data-options="region:'east',split:true" title="Remove an Employee" style="width: 100%; padding: 30px 60px;">
	<form action="/EmployeeManagement/EmployeeServlet/action.do?method=removeEmployee" method="post">
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="id" style="width: 100%" data-options="label:'ID:',required:true">
		</div>
		<div>
			<input type="submit" value="Remove Employee" />
		</div>
	</form>
</div>
