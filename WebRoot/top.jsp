<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="images/css/bootstrap.css" />
<link rel="stylesheet" href="images/css/css.css" />
<script type="text/javascript" src="images/js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="images/js/bootstrap.min.js"></script>
<script type="text/javascript" src="images/js/sdmenu.js"></script>
<script type="text/javascript" src="images/js/laydate/laydate.js"></script>
<style type="text/css">
.header {
	position: absolute !important;
	position: relative;
	width: 100%;
	height: 55px;
	background-color: #1F547E;
	no-repeat
	right
	center;
}

#header-left a {
	color: #FFFFFF;
	font-size: 25px;
	margin-top: 17px;
	margin-left: 44px;
	float: left;
	text-decoration: none;
	font-family: "microsoft yahei";
}

#header-right a {
	color: #fff;
	text-decoration: none;
	float: right;
}
</style>
</head>

<body>
	<div class="header">
		<div id="header-left">

			<a>培养计划管理系统</a>
		</div>
		<div class="header-right">		    
			<span style="color: white; margin-right: 20px;">
				<c:if test="${empty StuID}">欢迎您,${user.username }老师!</c:if>
				<c:if test="${not empty StuID}">你好,${StuID}同学!</c:if>
			</span>
			
			<a href="index.jsp" target="_top">返回首页</a> <i
				class="icon-off icon-white"></i> <a
				onclick="if (confirm('确定要退出吗？')) return true; else return false;"
				href="login_outlogin.action" target="_top">注销退出</a>

		</div>
	</div>
</body>

</html>