<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
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
			<strong>添加课程信息</strong>
		</div>
		<div class="bj">
			<form name="curriculum"
				action="${pageContext.request.contextPath}/curriculum_addcurriculum.action?tnum=${user.tnum}&perCreErr=${perCreErr}&keyCouEmpty=${keyCouEmpty}"
				method="post">
				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;中文名称： <input
							type="text" name="curriculumCname"
							value="${curriculum.curriculumCname }" required />
				</div>
				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;课程英文名： <input type="text"
							name="curriculumEname" value="${curriculum.curriculumEname }"
							required />
				</div>
				<div id="right">
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学分：
						<input type="number" name="credit" value="${curriculum.credit }"
							required />
				</div>
				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总学时：
						<input type="number" name="hoursOfALL"
							value="${curriculum.hoursOfALL }" />
				</div>
				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;周学时：
						<input type="text" name="hoursOfWeek"
							value="${curriculum.hoursOfWeek }" />
				</div>
				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;讲课学时： <input
							type="number" name="hoursOfClass"
							value="${curriculum.hoursOfClass }" />
				</div>
				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;实验学时： <input
							type="number" name="hoursOfExp" value="${curriculum.hoursOfExp }"
							onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" />
				</div>
				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上机学时： <input
							type="number" name="hoursOfCom" value="${curriculum.hoursOfCom }" />
				</div>
				<div>
					<h5>
						&nbsp;课程实践学时： <input type="number" name="hoursOfPractice"
							value="${curriculum.hoursOfPractice }" />
				</div>
				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课程平台： <select
							size="1" name="curriculumpingtai">
							<c:if test="${sessionScope.user.adminlevel eq 5}">
								<option value="公共教育平台"
									<c:if test="${curriculum.curriculumpingtai eq '公共教育平台'}">selected="selected"</c:if>>公共教育平台</option>
							</c:if>
							<option value="专业教育平台"
								<c:if test="${curriculum.curriculumpingtai eq '专业教育平台'}">selected="selected"</c:if>>专业教育平台</option>
						</select>
				</div>
				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课程性质： <select
							size="1" name="natureOfCourse.natureOfCourseid">
							<c:forEach items="${natureOfCourselist }" var="natureOfCourse">
								<option value="${natureOfCourse.natureOfCourseid }"
									<c:if test="${curriculum.natureOfCourse.natureOfCourseid eq natureOfCourse.natureOfCourseid}">selected="selected"</c:if>>${natureOfCourse.natureOfCoursename }</option>
							</c:forEach>
						</select>
				</div>
				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课程类别： <select
							size="1" name="courseLei">
							<option value="理论课"
								<c:if test="${curriculum.courseLei eq '理论课'}">selected="selected"</c:if>>理论课</option>
							<option value="实验课"
								<c:if test="${curriculum.courseLei eq '实验课'}">selected="selected"</c:if>>实验课</option>
							<option value="实践课"
								<c:if test="${curriculum.courseLei eq '实践课'}">selected="selected"</c:if>>实践课</option>
							<option value="体育课"
								<c:if test="${curriculum.courseLei eq '体育课'}">selected="selected"</c:if>>体育课</option>

						</select>
				</div>
				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课程归属： <select
							size="1" name="courseCategory">
							<option value=""></option>
							<option value="社会科学类"
								<c:if test="${curriculum.courseCategory eq '社会科学类'}">selected="selected"</c:if>>社会科学类</option>
							<option value="文化与艺术类"
								<c:if test="${curriculum.courseCategory eq '文化与艺术类'}">selected="selected"</c:if>>文化与艺术类</option>
							<option value="数学与逻辑类"
								<c:if test="${curriculum.courseCategory eq '数学与逻辑类'}">selected="selected"</c:if>>数学与逻辑类</option>
							<option value="科学与技术类"
								<c:if test="${curriculum.courseCategory eq '科学与技术类'}">selected="selected"</c:if>>科学与技术类</option>
							<option value="创新创业与发展类"
								<c:if test="${curriculum.courseCategory eq '创新创业与发展类'}">selected="selected"</c:if>>创新创业与发展类</option>
						</select>
				</div>
				<div>
					<h5>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开课部门： <select
							size="1" name="college.collegeid">
							<c:forEach items="${collegelist }" var="college">
								<option value="${college.collegeid }"
									<c:if test="${curriculum.college.collegeid eq college.collegeid}">selected="selected"</c:if>>${college.collegeCname }</option>
							</c:forEach>
						</select>
				</div>
				<div>
					<tr>
						<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课程简介：</h5>
						<td><textarea style="width: 555px; height: 99px"
								name="courseIntroduction" maxlength="9000" required>${curriculum.courseIntroduction }</textarea></td>

					</tr>
				</div>
				<c:if test="${tag ne 'no' }">
					<c:if
						test="${curriculum.curriculumid ne '' && not empty curriculum.curriculumid}">
						<input type="hidden" name="curriculumid"
							value="${curriculum.curriculumid }">
					</c:if>
				</c:if>
				<c:if
					test="${curriculum.newcurriculum ne '' && not empty curriculum.newcurriculum}">
					<input type="hidden" name="newcurriculum"
						value="${curriculum.newcurriculum }">
				</c:if>
				<c:if
					test="${curriculum.newcurriculum eq '' || empty curriculum.newcurriculum}">
					<input type="hidden" name="newcurriculum" value="1">
				</c:if>
				<div class="bottom">
					<button type="button" value="保存" onclick="check()">保存</button>
				</div>
			</form>
		</div>
		<script type="text/javascript">
function check()
{ 	
	var hoursOfALL = 0;
	var hoursOfClass = 0;
	var hoursOfExp = 0;
	var hoursOfCom = 0;
	var hoursOfPractice = 0;
	if(document.curriculum.hoursOfALL.value.length == 0){
	}else{
		hoursOfALL = document.curriculum.hoursOfALL.value;
	}
	if(document.curriculum.hoursOfClass.value.length == 0){
	}else{
		hoursOfClass = document.curriculum.hoursOfClass.value;
	}
	if(document.curriculum.hoursOfExp.value.length == 0){
	}else{
		hoursOfExp = document.curriculum.hoursOfExp.value;
	}
	if(document.curriculum.hoursOfCom.value.length == 0){
	}else{
		hoursOfCom = document.curriculum.hoursOfCom.value;
	}
	if(document.curriculum.hoursOfPractice.value.length == 0){
	}else{
		hoursOfPractice = document.curriculum.hoursOfPractice.value;
	}
	var newhoursOfALL = parseInt(hoursOfALL);
	var newhoursOfClass = parseInt(hoursOfClass);
	var newhoursOfExp = parseInt(hoursOfExp);
	var newhoursOfCom = parseInt(hoursOfCom);
	var newhoursOfPractice = parseInt(hoursOfPractice);
	var All = newhoursOfClass+newhoursOfExp+newhoursOfCom+newhoursOfPractice;
	if(newhoursOfALL != All){
		alert("要求：总学时=讲课学时+实验学时+上机学时+课程实践学时!");
	}else{
		document.curriculum.submit();
	}
	
}
</script>
</body>