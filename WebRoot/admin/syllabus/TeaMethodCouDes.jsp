<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
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
.bottom {
	padding-top: 10px;
	padding-left: 350px;
}

table {
	font-family: "微软雅黑";
	font-size: 13px;
	color: #000000;
}

.item-name {
	height: 50px;
	text-align: right;
}

.input {
	height: 50px;
	text-align: left;
}
</style>
</head>

<body>
	<form
		action="${pageContext.request.contextPath}/WayCourseDesign_updateWayCourseDesign.action?syllabusId=${syllabusId}&practiceLessonid=${newpracticeLesson.practiceLessonid}"
		method="post">
		<div class="right_cont">

			<table style="margin-left: 120px;">
				<tr>

					<div class="title_right">
						<strong>教学方法与评定</strong>
					</div>
				</tr>


				<tr>
					<td class="item-name">教学目标达成度评价 ：</td>
					<td class="input" colspan="3" style="height: 128px;"><textarea
							name="aim" style="height: 100px; width: 453px;">${wayCourseDesign.aim}</textarea></td>
					</td>

				</tr>
				<tr>
					<td class="item-name">成绩考核与评定：</td>
					<td class="input" colspan="3" style="height: 128px;"><textarea
							name="assess" style="height: 100px; width: 453px;">${wayCourseDesign.assess}</textarea></td>
					</td>

				</tr>
				<input type="hidden" name="wayCourseDesignid"
					value="${wayCourseDesign.wayCourseDesignid}" />
			</table>
			<div class="bottom">
				<button type="submit" value="提交">提交</button>
			</div>
		</div>
</body>

</html>