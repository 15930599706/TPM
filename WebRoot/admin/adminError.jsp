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
	top: 40%;
	left: 40%;
}
</style>
</head>

<body>

	<div id="error">
		<img src="<%=basePath %>images/img/error.jpg" /> <strong>${msg }</strong>
		<c:if test="${tag == 'topyxxPage' }">
			<c:if test="${user.adminlevel != 1 }">
				<a
					href="${pageContext.request.contextPath}/ptBasicInformation_topyxxPage.action?tnum=${user.tnum }&collegeid=-1">确定</a>
			</c:if>
			<c:if test="${user.adminlevel == 1 }">
				<a
					href="${pageContext.request.contextPath}/ptBasicInformation_tobjpyPage.action?department.departmentid=${user.department.departmentid }">确定</a>
			</c:if>
		</c:if>

		<c:if test="${tag == 'tozyjzpage' }">
			<c:if test="${user.adminlevel != 1 }">
				<a
					href="${pageContext.request.contextPath}/curriculumMatrix_tozyjzpage.action?tnum=${user.tnum }&collegeid=-1"></a>
			</c:if>
			<c:if test="${user.adminlevel == 1 }">
				<a
					href="${pageContext.request.contextPath}/curriculumMatrix_tobjjzpage.action?departmentid=${user.department.departmentid}"></a>
			</c:if>
		</c:if>

		<c:if test="${tag == 'togetInPage' && user.adminlevel == 5}">
			<a
				href="${pageContext.request.contextPath}/user_togetInPage.action?tnum=${user.tnum}">确定</a>
		</c:if>

		<c:if test="${tag == 'toCheckTheoLes'}">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_toCheckTheoLes.action?user.tnum=${user.tnum}">确定</a>
		</c:if>

		<c:if test="${tag == 'toCheckTheoLesInnerExperiment'}">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_toCheckTheoLesInnerExperiment.action?user.tnum=${user.tnum}">确定</a>
		</c:if>

		<c:if test="${tag == 'toCheckPracLesFieldWorkPage'}">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckPracLesFieldWorkPage?user.tnum=${user.tnum}">确定</a>
		</c:if>
		<c:if test="${tag == 'toCheckPracLesCourseDesignPage'}">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckPracLesCourseDesignPage?user.tnum=${user.tnum}">确定</a>
		</c:if>
		<c:if test="${tag == 'toCheckPracLesInnerExperimentPage'}">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckPracLesInnerExperimentPage?user.tnum=${user.tnum}">确定</a>
		</c:if>
		<c:if test="${tag == 'toCheckPracLesGraduationProjectPage'}">
			<a
				href="${pageContext.request.contextPath}/practicePlan_toCheckPracLesGraduationProjectPage?user.tnum=${user.tnum}">确定</a>
		</c:if>
		<c:if test="${tag == 'toCheckPracLesOtherPage'}">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_toCheckPracticeOther.action?user.tnum=${user.tnum}">确定</a>
		</c:if>
	</div>

</body>
</html>