<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<title>注册页面</title>
<link rel="stylesheet" href="/TPM/images/css/register.css">
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="/TPM/images/js/register.js"></script>
</head>
<body>
	<!-- <div class="title" >注册
							<a  class="btn" href="denglu.html">
								<span>登陆</span>
							</a>
						</div>
						<div class="top-wapper">
							<ul class="top-wapper-ul">
								<li class= "stu-li" ><a href="register_stu.html"/;>学生用户</a></li>
								<li class="comp-li" style="background-color:green;"><a href="register_comp.html" style="color:#fff">企业用户</a></li>
								<li class= "admin-li"><a href="register_admin.html">管理员用户</a></li>
							</ul>
						</div> -->
	<div class="nav"
		style="width: 100%; height: 90px; background: url(../../images/img/bgg.png); border-bottom: 1px solid #ccc">
		<div
			style="width: 272px; height: 80px; margin-top: 10px; background: url(../../images/img/ysulogo.png); float: left; margin-left: 5%;"></div>
		<div
			style="width: 300px; height: 80px; margin-top: 10px; background: url(../../images/img/logo.png); float: left;"></div>
		<div></div>
		<ul class="navUl">
			<li><a class="za" href="denglu.jsp"> <span>登录</span>
			</a></li>
			<li><a class="fh" href="index.jsp"> <span>返回首页</span>
			</a></li>
			<li><a class="student" href="register_stu.jsp"> <span>学生用户注册</span>
			</a></li>
			<li><a class="company" href="register_comp.jsp"
				style="background-color: #fa911e; color: #fff; border: 3px solid #fa911e;">
					<span>企业用户注册</span>
			</a></li>
		</ul>
	</div>
	<span id="leftBlue"
		style="width: 33%; height: 20%; background: url(../../images/img/bg1.gif); padding-right: 0; float: left; margin-top: 25%;"></span>
	<span id="rightBlue"
		style="width: 33%; height: 20%; background: url(../../images/img/bg1.gif); margin-left: 0; float: right; margin-top: 25%;"></span>
	<div class="registerBox">
		<div class="form" style="margin-bottom: 50px;">
			<form id="f" action="${pageContext.request.contextPath}/user.do"
				method="get">
				<h2>企业用户注册</h2>
				<ul>
					<li
						style='display: none !important;&gt;
														&lt;input type="hidden"  id="flag"  name="flag"  value="student" ' />
					</li>
					<li>用户名: <input class="inp" type="text" id="phoneNum"
						name="phoneNum" placeholder="请输入用户名(手机号)" onblur="ajxTest()"
						onkeyup="" ="checkUserName()" required /> <span id="_tishi"></span>
					</li>

					<li>姓名： <input class="inp" id="Name" name="Name"
						placeholder="请输入你的姓名" required />
					</li>

					<li>企业名称： <input class="inp" type="text" name="CompanyName"
						placeholder="请输入你的企业名称" />
					</li>
					<li>用户密码： <input class="inp" type="password" name="pwd"
						id="pwd" placeholder="请输入密码" onblur="judgepwd()" required /><span
						id="__tishi"></span>
					</li>
					<li>确认密码： <input class="inp" type="password" name="_pwd"
						id="_pwd" placeholder="确认密码" onkeyup="judge_pwd()"
						onfocus="onfFuc()" required /><span id="tishi"></span>

					</li>
				</ul>

				<input type="button" class="regiest" id="submit" value="注册" /> <br />

			</form>
		</div>
	</div>
	<!-- <div class="reg">
					<span>已有账号？</span>
					<a href="login.html">登录</a>
					</div> -->
	<script type="text/javascript">
					$("#submit").click(function(){
							if($("#Name").val()=="" ||$("input[name='CompanyName']").val()==""){
								alert("输入信息不全！");
							}
							else{
								$.ajax({
																		type:"POST",
																		url: '/PublishQnaireServlet.servlet',
																		data:{"phoneNum":$("#phoneNum").val(),
																				 "pwd":$("#pwd").val(),
																				 "Name":$("#Name").val(),
																					"CompanyName":$("input[name='CompanyName']").val()
																			 },
																		dataType:'json',
																		cache:false,
																		async:true,
																		success: function(data) {
																					alert(data);
																		},
																		error: function() {
																				alert("error！")
																		}
																});
							}
	})
					</script>
</body>
</html>
