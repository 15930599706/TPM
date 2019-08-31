<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<style type="text/css">
.bottom {
	padding-top: 10px;
	padding-left: 222px;
}

.bj {
	padding-left: 10px;
}
</style>

</head>

<body>

	<div class="right_cont">
		<div class="title_right">
			<strong>编辑专业人才培养理念</strong>
		</div>
		<div class="bj">
			<form
				action="${pageContext.request.contextPath}/ppTrainingConcept_updateppTrainingConcept.action"
				method="post">
				<div>
					<tr>
						<td><font size="2px" color="#000000">专业名称：</td>
						<td><font size="2px" color="red">${department.departmentCname }</td>
					</tr>
				</div>
				<c:if test="${user.adminlevel == 1 || tag == 'revise'}">
					<div>
						<tr>
							<h5>
								<font size="2px" color="#000000">专业发展规划:<span
									style="color: red;">(500字以内)</span>
							</h5>
							<td><textarea style="width: 555px; height: 99px"
									name="ProfessionalDevelopmentPlanning" maxlength="500" required>${ppTrainingConcept.professionalDevelopmentPlanning }</textarea></td>

						</tr>
					</div>
					<div>
						<tr>
							<h5>
								<font size="2px" color="#000000">专业特色:<span
									style="color: red;">(300字以内)</span>
							</h5>
							<td><textarea style="width: 555px; height: 99px"
									name="ProfessionalFeatures" maxlength="300" required>${ppTrainingConcept.professionalFeatures }</textarea></td>

						</tr>
					</div>
				</c:if>

				<c:if test="${user.adminlevel != 1 && tag != 'revise'}">
					<c:if test="${not empty ppTrainingConcept.professionalFeatures}">
						<div>
							<tr>
								<h5>
									<font size="2px" color="#000000">专业发展规划:<span
										style="color: red;">
								</h5>
								<td><p>${ppTrainingConcept.professionalDevelopmentPlanning }</p></td>

							</tr>
						</div>
						<div>
							<tr>
								<h5>
									<font size="2px" color="#000000">专业特色:<span
										style="color: red;">
								</h5>
								<td><p>${ppTrainingConcept.professionalFeatures }</p></td>

							</tr>
						</div>
					</c:if>
					<c:if test="${empty ppTrainingConcept.professionalFeatures}">
						<font size="2px" color="red">${department.departmentCname }还未填写专业人才培养理念！</font>
					</c:if>
				</c:if>



				<input type="hidden" name="department.departmentid"
					value="${department.departmentid }">
				<c:if
					test="${ppTrainingConcept.PPTrainingConceptid ne '' && not empty ppTrainingConcept.PPTrainingConceptid}">
					<input type="hidden" name="PPTrainingConceptid"
						value="${ppTrainingConcept.PPTrainingConceptid }">
				</c:if>
				<div class="bottom">
					<c:if test="${user.adminlevel == 1 || tag == 'revise'}">
						<button type="submit" value="保存">保存</button>
					</c:if>
				</div>
			</form>
		</div>
</body>