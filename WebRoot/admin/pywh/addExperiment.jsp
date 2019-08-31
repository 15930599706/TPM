<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script language="javascript">
	function check()
	{
  		var xueyuanid = document.getElementById("xueyuan").value;
		if(xueyuanid != "-1")	//限定有选择时才会提交表单
		{
			return true;  
		} 
	 	else
		{
			alert("请选择该专业的所属学院");
			return false;
		} 
	}
	</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="images/css/bootstrap.css" />
<link rel="stylesheet" href="images/css/css.css" />
</head>
<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>添加实验室</strong>
		</div>
		<div style="width: 700px; margin: auto;">
			<form action="experiment_addExp.action" method="post" name="addExp"
				onSubmit="return check();" enctype="multipart/form-data">
				<table class="table table-bordered">
					<tr>
					<tr>
						<td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">部门（学院）：</td>
						<td><select size="1" id="xueyuan" name="college.collegeid"
							style="width: 160px;">
								<option value="-1" selected="selected">请选择所属学院</option>
								<c:forEach items="${collegeList }" var="college">
									<option value="${college.collegeid }">${college.collegeCname}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td width="5%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">实验室编码：</td>
						<td><input type="text" name="experimentid" class="span4"
							value="" required style="width: 162px;" /></td>
					</tr>

					<tr>
						<td width="5%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">实验室名称：</td>
						<td><input type="text" name="experimentname" class="span4"
							value="" required style="width: 162px;" /></td>
					</tr>

					<tr>
						<td class="text-center" colspan="2"><input type="submit"
							value="确定" class="btn btn-info  " style="width: 80px;" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>