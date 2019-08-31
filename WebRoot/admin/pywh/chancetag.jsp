<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=basePath%>images/css/bootstrap.css" />
<link rel="stylesheet" href="<%=basePath%>images/css/css.css" />
<script type="text/javascript"
	src="<%=basePath%>images/js/jquery1.9.0.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>images/js/sdmenu.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/laydate/laydate.js"></script>
<script src="<%=basePath%>images/js/jquery-1.7.2.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>images/js/tc.min.js"></script>
<style type="text/css">
html, body {
	height: 100%;
}

#box2 {
	position: fixed;
	left: 50%;
	top: 50%;
	width: 500px;
	height: 200px;
	margin: -100px 0px 0px -250px;
	vertical-align: middle;
	align: center;
	text-align: center;
	font-size: 18px;
}

a {
	text-decoration: none;
	border-style: outset;
	vertical-align: middle;
	align: center;
	text-align: center;
	color: #0087E8;
	margin-left: 20px;
	font-size: 18px;
}

a:hover {
	color: #f37028;
}

#box1 {
	position: fixed;
	left: 50%;
	top: 50%;
	width: 210px;
	height: 25px;
	margin: -150px 0px 0px -300px;
	color: red;
	vertical-align: middle;
	align: center;
	text-align: center;
	font-size: 18px;
}

#box {
	padding-left: 24px;
	font-size: 14px;
	color: red;
}
</style>

</head>

<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>选择上传拓扑图方式</strong>
		</div>
		<c:if test="${user.adminlevel == 1 || tag == 'revise'}">
			<div id="box1">
				<strong
					style="vertical-align: middle; align: center; text-align: center">*&nbsp;</strong><strong>请选择上传拓扑图方式:</strong>
			</div>
			<div style="align: center;" Id="box2">
				<c:if test="${user.adminlevel == 1}">
					<a
						href="topology_chancetag?department.departmentid=${department.departmentid }&tag=0">整个专业用一幅拓扑图</a>
					<a
						href="topology_chancetag?department.departmentid=${department.departmentid }&tag=1">每个专业方向用一幅拓扑图</a>
				</c:if>

				<c:if test="${user.adminlevel == 5}">
					<a
						href="topology_revisechancetag?department.departmentid=${department.departmentid }&tag=0">整个专业用一幅拓扑图</a>
					<a
						href="topology_revisechancetag?department.departmentid=${department.departmentid }&tag=1">每个专业方向用一幅拓扑图</a>
				</c:if>

			</div>
		</c:if>

		<c:if test="${user.adminlevel != 1 && tag != 'revise'}">
			<div id="box">该专业还未上传拓扑图</div>
		</c:if>

	</div>

</body>
</html>
