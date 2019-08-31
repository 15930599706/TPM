<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/TPM/images/css/denglu.css">
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="/TPM/images/js/register.js"></script>
</head>
<body>
	<div class="nav" style="width: 100%; height: 90px; background: url(../images/img/bgg.png); border-bottom: 1px solid #ccc">
		<div style="width: 272px; height: 80px; margin-top: 10px; background: url(../images/img/ysulogo.png); float: left; margin-left: 5%;"></div>
		<div style="width: 300px; height: 80px; margin-top: 10px; background: url(../images/img/logo.png); float: left;"></div>
		<div></div>
		<ul>

			<li><a class="za" href="register_stu.jsp"> <span>注册</span>
			</a></li>
			<li><a class="fh" href="../login.jsp"> <span>管理员登陆</span>
			</a></li>
		</ul>
	</div>

	<div class="zhuti">
		<span id="leftBlue"
			style="width: 35%; height: 20%; background: url(../images/img/bg1.gif); padding-right: 0; float: left; margin-top: 16%;"></span>
		<span id="rightBlue"
			style="width: 34.5%; height: 20%; background: url(../images/img/bg1.gif); margin-left: 0; float: right; margin-top: 16%;"></span>
		<form class="denglukuang" id="f"
			style="top: 35%; display: inline-block;">
			<div class="dl">
				<h2 style="text-align:center;">登录</h2>
				<input class="zh" type="text" name="user_name" id="phoneNum"
					placeholder="请输入账号" maxlength="11" onblur="checkUserName()"
					required /> <span id="_tishi"
					style="display: block; margin-top: -30px;">&nbsp</span> <input
					class="mm" type="password" name="pwd" placeholder="请输入密码" required />

				<div class="yhlb">
					学生用户<input type="radio" name="dengluming" value="stu" checked />
					企业用户<input type="radio" name="dengluming" value="com" />
				</div>

			</div>
			<div class="anniu">
				<input type="button" id="login_submit" value="登陆" class="an" />
			</div>
		</form>
	</div>
</body>
</html>