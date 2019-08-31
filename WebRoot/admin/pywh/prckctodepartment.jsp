<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="textml; charset=utf-8" />

<link rel="stylesheet" href="<%=basePath%>images/css/bootstrap.css" />
<link rel="stylesheet" href="<%=basePath%>images/css/css.css" />
<script type="text/javascript"
	src="<%=basePath%>images/js/jquery1.9.0.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>images/js/sdmenu.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/laydate/laydate.js"></script>

<style type="text/css">
.bottom {
	padding-top: 10px;
	padding-left: 222px;
}

.bj {
	padding-left: 250px;
	padding-top: 20px;
}
</style>

</head>

<body>

	<div class="right_cont">
		<div class="title_right">
			<strong>添加实践课程开课专业
		</div>
		<div class="bj">
			<form
				action="${pageContext.request.contextPath}/practicePlan_goupdatedepartment.action?newcurrentpage=${newcurrentpage }&newdepartmenttag1=${newdepartmenttag1 }"
				method="post">
				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;中文名称：${Curriculum.curriculumCname}
				</div>
				<div>
					<h5>课程英文名：${Curriculum.curriculumEname }
				</div>
				<div id="right">
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学分：${Curriculum.credit }
					
				</div>
				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;周学时：${Curriculum.hoursOfWeek }
					
				</div>
				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总学时：${Curriculum.hoursOfALL }
					
				</div>
				<div>
					<h5>&nbsp;&nbsp;&nbsp;&nbsp;讲课学时：${Curriculum.hoursOfClass }
				</div>
				<div>
					<h5>&nbsp;&nbsp;&nbsp;&nbsp;实验学时：${Curriculum.hoursOfExp }
				</div>
				<div>
					<h5>&nbsp;&nbsp;&nbsp;&nbsp;上机学时：${Curriculum.hoursOfCom }
				</div>

				<div>
					<h5>&nbsp;&nbsp;&nbsp;&nbsp;课程类别：${Curriculum.courseLei }
				</div>
				<div>
					<h5>&nbsp;&nbsp;&nbsp;&nbsp;课程归属：${Curriculum.courseCategory }
					
				</div>
				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;课程性质：${Curriculum.natureOfCourse.natureOfCoursename }
					
				</div>
				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;课程平台：${Curriculum.curriculumpingtai }
				</div>

				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;开课学院：${Curriculum.college.collegeCname }
				</div>
				<div style="_height: 10px; min-height: 10px;">
					<table>
						<td style="vertical-align: top">
							<h5>&nbsp;&nbsp;&nbsp;&nbsp;选课专业：
						</td>
						<td>
							<h5>
								<c:forEach items="${choosedepartlist}" var="professional">
									<c:if test="${professional.professionalname ne null}">
										<li style="list-style-type: none;">${professional.department.departmentCname}——${professional.professionalname}</li>
										<input type="hidden" name="newchoosedepartlist"
											value="${professional.professionalid }">
									</c:if>
									<c:if test="${professional.professionalname eq null}">
										<li style="list-style-type: none;">${professional.department.departmentCname}</li>
										<input type="hidden" name="newchoosedepartlist"
											value="${professional.department.departmentid }">
									</c:if>
								</c:forEach>
						</td>
					</table>
				</div>
				<div>
					<tr>
						<h5>&nbsp;&nbsp;&nbsp;&nbsp;课程简介：</h5>
						<p>${Curriculum.courseIntroduction }</p>
					</tr>
				</div>
				<div>
					<tr>
						<h5>
							&nbsp;&nbsp;&nbsp;&nbsp;开课专业： <select size="1"
								name="teachDepartment.departmentid">
								<c:forEach items="${departmentlist }" var="department">
									<option value="${department.departmentid }">${department.departmentCname }</option>
								</c:forEach>
								<lect>
						</h5>
					</tr>
				</div>

				<input type="hidden" name="curriculum.curriculumid"
					value="${Curriculum.curriculumid }">
				<c:if test="${not empty departmentlist}">
					<div class="bottom">
						<button type="submit" value="保存">
							保存
							<tton>
					</div>
				</c:if>
			</form>
		</div>
</body>