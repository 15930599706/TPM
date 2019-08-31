<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

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
#box2 {
	font-family: "microsoft yahei";
	height: 30px;
	padding-left: 25px;
	padding-top: 5px;
}

#box3 {
	font-family: "microsoft yahei";
	height: 30px;
	padding-left: 160px;
}

dt {
	padding: 10px;
}

p {
	height: 100px;
	line-height: 100px;
	border: 1px solid #000000;
	margin: 10px;
}

i {
	font-style: normal;
	color: #000000;
}

#detail {
	position: absolute;
	width: 350px;
	height: 120px;
	border: 1px solid #ccc;
	background: #efefef;
	display: none;
	color: #000000;
}

#detail .tit {
	background: #B1CCEB;
	display: block;
	height: 22px;
	cursor: move;
	color: #000000;
}

#detail .tit i {
	float: right;
	padding-right: 9px;
	padding-top: 2px;
	cursor: default;
	font-size: 12px;
	color: #000000;
}

#detail2 {
	position: absolute;
	width: 300px;
	height: 100px;
	border: 1px solid #555;
	background: #555;
	display: none;
}

#detail2 .tit {
	background: #333;
	display: block;
	height: 33px;
	cursor: move;
}

#detail2 .tit i {
	float: right;
	line-height: 33px;
	padding: 0 8px;
	color: #000000;
	cursor: default;
}

#box4 {
	font-family: "microsoft yahei";
	height: 50px;
	padding-left: 25px;
	padding-top: 25px;
}
</style>
</head>

<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>更改适用专业方向</strong>
		</div>
		<form method="post" id="change">
			<div
				style="_height: 10px; min-height: 10px; padding-left: 25px; padding-top: 5px;">
				<table>
					<td style="vertical-align: top; font-size: 13px;">当前适用的专业方向：</td>
					<td style="vertical-align: top; font-size: 13px;"><c:forEach
							items="${haveProfessionalList }" var="haveProfessional">
							<li style="list-style-type: none;">${haveProfessional.professionalname }</li>
							<input type="hidden" name="professionalList"
								value="${haveProfessional.professionalid}">
						</c:forEach></td>
				</table>
			</div>
			<div
				style="_height: 10px; min-height: 10px; padding-left: 25px; padding-top: 5px;">
				<table>
					<td style="vertical-align: top; font-size: 13px;">更改适用专业方向为：</td>
					<td style="vertical-align: top; font-size: 13px;"><c:forEach
							items="${allProfessionallist }" var="allProfessional">
							<li style="list-style-type: none;"><input name="changePro"
								type="checkbox" value="${allProfessional.professionalid }"
								style="align: center; font-size: 13px;"
								<c:forEach items="${haveProfessionalList }" var="haveProfessional"><c:if test="${haveProfessional.professionalid eq allProfessional.professionalid}">checked="checked"</c:if>
							</c:forEach>>
								${allProfessional.professionalname }</li>
						</c:forEach></td>
				</table>
			</div>
		</form>
		<button onclick="return changePro()" style="width: 170px;">确认修改</button>
	</div>
	<script language="javascript">
	function changePro() { 	
		if ($("input[type='checkbox']").is(':checked')) {
			var form = document.getElementById("change");
			form.action = "${pageContext.request.contextPath}/mainTainOfPT_bjapChangePro.action?departmentid=${department.departmentid}";
			form.submit();
		}
		else {
			alert("请选择要适用的专业方向！");
			return false;
		}
	}
	</script>
</body>
</html>
