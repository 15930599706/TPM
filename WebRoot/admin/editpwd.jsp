<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=basePath %>images/css/bootstrap.css" />
<link rel="stylesheet" href="<%=basePath %>images/css/css.css" />
<script type="text/javascript"
	src="<%=basePath%>images/js/jquery1.9.0.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>images/js/sdmenu.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/laydate/laydate.js"></script>
</head>
<script type="text/javascript">
function check()
{
	if(document.form1.oldpwd.value=="")
	{
		alert("请输入原密码！");
		document.form1.oldpwd.focus();
		return false;
	}
	if(document.form1.newpwd.value=="")
	{
		alert("请输入新密码！");
		document.form1.newpwd.focus();
		return false;
	} 
	if(document.form1.repwd.value=="")
	{
		alert("请确认密码！");
		document.form1.repwd.focus();
		return false;
	}
	if(document.form1.repwd.value!=document.form1.newpwd.value)
	{
		alert("对不起，两次输入的密码不相同，请确认密码！");
		document.form1.repwd.focus();
		return false;
	}
}
function checkPasswords() {
        var pass1 = document.getElementById("newpwd");
        var pass2 = document.getElementById("repwd");
 
        if (pass2.value != pass1.value)
            pass2.setCustomValidity("两次输入的密码不匹配");
        else
            pass2.setCustomValidity("");
}
</script>

<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>修改登录密码</strong>
		</div>
		<div style="width: 900px; margin: auto;">
			<p Style="font-size: 15px; color: red; text-align: center;">${msg }</p>
			<form action="${pageContext.request.contextPath}/user_editpwd.action"
				method="post" name="form1">
				<input type="hidden" name="tnum" value="${user.tnum }" />
				<table class="table table-bordered">
					<tr>
						<td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">原&nbsp;&nbsp;密&nbsp;&nbsp;码：</td>
						<td><input type="password" name="oldpwd" class="span3"
							required /></td>
					</tr>
					<tr>
						<td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">新&nbsp;&nbsp;密&nbsp;&nbsp;码：</td>
						<td><input type="password" name="newpwd" id="newpwd"
							onchange="checkPasswords()" class="span3" required /></td>
					</tr>
					<tr>
						<td width="40%" align="right" nowrap="nowrap" bgcolor="#f1f1f1">确认密码：</td>
						<td><input type="password" name="repwd" id="repwd"
							onchange="checkPasswords()" class="span3" required /></td>
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