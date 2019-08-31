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
<style type="text/css">
#success {
	position: absolute;
	top: 40%;
	left: 50%;
}
</style>
</head>

<body>

	<div id="success">
		<img src="<%=basePath %>images/img/Success-16.png" /> <strong>培养计划信息尚未填写完整，无法下载！</strong>
</body>
</html>