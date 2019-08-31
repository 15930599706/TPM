<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE >
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
<link rel="stylesheet" href="<%=basePath%>images/css/style.css" />

</head>

<body>
	<div class="rmain" style="width: 100%;">
		<div class="rdetail" style="width: 100%;">
			<div class="rdtitle" style="width: 100%">
				<span style="color: #0044CC;">${notice.noticetitle }</span>
			</div>
			<div class="rdmark" style="width: 100%">来源：${notice.user.username }
				发表时间：${notice.noticetime }</div>

			<div class="rlist"
				style="width: 666px; margin: 0 auto; text-indent: 2em;">
				<table width="100%" border="0">
					<tbody>${notice.noticecontent }

					</tbody>
				</table>

			</div>
		</div>
	</div>
	</div>
	</div>
</body>

</html>