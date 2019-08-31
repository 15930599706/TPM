<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
#box9 a:hover {
	font-family: "microsoft yahei";
	color: #0000c6;
	width: 80px;
	height: 20px;
}

#box9 {
	height: 35px;
	float: left;
	width: 80px;
}

#box9 a {
	display: block;
	height: 20px;
	width: 80px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath%>images/img/back-16.png) no-repeat 25px 2px;
	text-indent: 40px;
	text-align: center;
}

#box5 {
	font-family: "microsoft yahei";
	height: 50px;
	padding-left: 25px;
	padding-top: 25px;
}

#box2 {
	font-family: "microsoft yahei";
	height: 40px;
	float: left;
	width: 1000px;
	padding-left: 35px;
}

#box {
	font-family: "microsoft yahei";
	height: 50px;
	float: left;
	width: 1000px;
}

#sl {
	width: 160px;
}

#lb #xq {
	width: 100px;
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

body {
	margin: 0;
	padding: 0;
	font-size: 12px;
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

#box3 a:hover {
	font-family: "microsoft yahei";
	color: #0000C6;
	width: 188px;
	height: 20px;
}

#box3 a {
	display: block;
	height: 20px;
	width: 188px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath%>images/img/disk-16.png) no-repeat 25px 3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box3 {
	height: 35px;
	float: left;
	width: 168px;
	padding-left: 10px;
}
</style>
</head>



<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>实践课课程设计（学年论文）大纲录入</strong>
		</div>
		<div id="box9">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckPracLesCourseDesignPage.action?user.tnum=${user.tnum}">返回</a>

		</div>
		<!-------------------------------课程设计（学年论文）------------------------------------->
		<div style="width: 90%; margin-left: 24px;">
			<table class="table table-bordered table-striped table-hover">

				<tbody>
				<thead>
					<tr align="center">

						<th nowrap="nowrap"><small>课程编号</small></th>
						<th nowrap="nowrap"><small>课程名称</small></th>
						<th nowrap="nowrap"><small>开课学院</small></th>
						<th nowrap="nowrap"><small>学分</small></th>
						<th nowrap="nowrap"><small>总学时</small></th>
						<th nowrap="nowrap"><small>课程性质</small></th>
						<th nowrap="nowrap"><small>应用专业</small></th>
						<!-- <th nowrap="nowrap"><small>应用方向</small></th> -->
						<th width="80" nowrap="nowrap"><small>基本信息</small></th>
						<th width="80" nowrap="nowrap"><small>教学目标及对应关系</small></th>
						<th width="80" nowrap="nowrap"><small>课设内容</small></th>
						<th width="80" nowrap="nowrap"><small>教学评价及评定</small></th>
						<th width="80" nowrap="nowrap"><small>教材信息</small></th>
					</tr>
				</thead>
				<tr align="center">
					<td nowrap="nowrap"><small>${newPracticeLesson.curriculum.curriculumid }</small></td>
					<td nowrap="nowrap"><small>${newPracticeLesson.curriculum.curriculumCname }</small></td>
					<td nowrap="nowrap"><small>${newPracticeLesson.curriculum.college.collegeCname }</small></td>
					<td nowrap="nowrap"><small>${newPracticeLesson.curriculum.credit }</small></td>
					<td nowrap="nowrap"><small>${newPracticeLesson.curriculum.hoursOfALL }</small></td>
					<td nowrap="nowrap"><small>${newPracticeLesson.curriculum.natureOfCourse.natureOfCoursename }</small></td>
					<td nowrap="nowrap"><small>${newPracticeLesson.department.departmentCname}</small></td>
					<%-- 	<td nowrap="nowrap">
								<c:if test="${count > 0}">
									<c:forEach items="${professionalList }" var="professional">
									<small>${professional.professionalname}</small>
									</c:forEach>
								</c:if>
								<c:if test="${count == 0}">
									<small>不区分专业方向</small>
								</c:if>
								</td> --%>

					<td nowrap="nowrap"><a
						href="${pageContext.request.contextPath}/BaseCourseDesign_toBaseCouDesPage.action?syllabusId=${syllabusId}&practiceLessonid=${newPracticeLesson.practiceLessonid}"><small>编辑</small></a>
					</td>
					<td nowrap="nowrap"><a
						href="${pageContext.request.contextPath}/abilityAndTeachObj_toBefourAimCourseDesignPage.action?syllabusId=${syllabusId}&practiceLessonid=${newPracticeLesson.practiceLessonid}"><small>编辑</small></a>
					</td>
					<td nowrap="nowrap"><a
						href="${pageContext.request.contextPath}/ConCourseDesign_toContentCouDesPage.action?syllabusId=${syllabusId}&practiceLessonid=${newPracticeLesson.practiceLessonid}"><small>编辑</small></a>
					</td>
					<td nowrap="nowrap"><a
						href="${pageContext.request.contextPath}/WayCourseDesign_toTeaMethodCouDesPage.action?syllabusId=${syllabusId}&practiceLessonid=${newPracticeLesson.practiceLessonid}"><small>编辑</small></a>
					</td>
					<td nowrap="nowrap"><a
						href="${pageContext.request.contextPath}/practiceBookCourseDesign_toMateriaCouDesPage.action?syllabusId=${syllabusId}&practiceLessonid=${newPracticeLesson.practiceLessonid}"><small>编辑</small></a>
					</td>


				</tr>
				</tbody>
			</table>
		</div>

	</div>

</body>