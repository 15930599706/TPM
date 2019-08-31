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
#error {
	position: absolute;
	top: 25%;
	left: 25%;
}
</style>
</head>

<body>

	<div id="error">
		<img src="<%=basePath %>images/img/exception.jpg"
			style="width: 200px; height: 180px;" /> <strong>服务器异常，请及时与我们联系！</strong>
	</div>

</body>
</html>