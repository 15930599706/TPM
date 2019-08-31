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
	<div class="right_cont">

		<form
			action="${pageContext.request.contextPath}/teaMethodTheo_updateTeaMethodTheo.action?syllabusId=${syllabusId}&curriculumId=${newtheoreticalLesson.curriculum.curriculumid}&theoreticalLessonId=<c:if test="${newtheoreticalLesson.curriculum.courseLei eq '理论课'}">${newtheoreticalLesson.theoreticalLessonid}</c:if><c:if test="${newtheoreticalLesson.curriculum.courseLei eq '实验课'}">${newtheoreticalLesson.theoreticalLessonid}</c:if><c:if test="${newtheoreticalLesson.curriculum.courseLei eq '实践课'}">${newtheoreticalLesson.practiceLessonid}</c:if>"
			method="post">
			<%-- 	<c:if test="${newtheoreticalLesson.curriculum.courseLei eq '理论课'}">
		</c:if>
		<c:if test="${newtheoreticalLesson.curriculum.courseLei eq '实践课'}">
			<form action="${pageContext.request.contextPath}/teaMethodTheo_updateTeaMethodTheo.action?syllabusId=${syllabusId}&theoreticalLessonId=${newtheoreticalLesson.practiceLessonid}" method="post">
		</c:if> --%>
			<table style="margin-left: 120px;">
				<tr>

					<div class="title_right">
						<strong>教学方法与评定</strong>
					</div>
				</tr>

				<tr>
					<td class="item-name">教学方法 ：</td>
					<td class="input" colspan="3" style="height: 128px;"><textarea
							style="height: 100px; width: 453px;" name="teaMethod">${TeaMethodTheo.teaMethod }</textarea></td>
					</td>

				</tr>
				<tr>
					<td class="item-name">教学目标达成度评价 ：</td>
					<td class="input" colspan="3" style="height: 128px;"><textarea
							style="height: 100px; width: 453px;" name="teaok">${TeaMethodTheo.teaok }</textarea></td>
					</td>

				</tr>
				<tr>
					<td class="item-name">成绩评定 ：</td>
					<td class="input" colspan="3" style="height: 128px;"><textarea
							style="height: 100px; width: 453px;" name="scoreok">${TeaMethodTheo.scoreok }</textarea></td>
					</td>
				</tr>
				<input type="hidden" name="teaMethodTheoid"
					value="${TeaMethodTheo.teaMethodTheoid }" />
			</table>
			<div class="bottom">
				<button type="submit" value="提交">提交</button>
			</div>
		</form>
	</div>
</body>

</html>