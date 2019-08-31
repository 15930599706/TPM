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
		<img src="<%=basePath %>images/img/error.jpg" /> <strong>请遵照上传要求进行上传！</strong>
		<a
			href="${pageContext.request.contextPath}/noticeFile_tozyxzPage.action">确定</a>
	</div>
</body>

</html>