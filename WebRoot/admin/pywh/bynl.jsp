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
	padding-left: 200px;
}
</style>

</head>

<body>

	<div class="right_cont">
		<div class="title_right">
			<strong>毕业生应获得以下几方面的知识和能力</strong>
		</div>
		<div class="bj">
			<div>
				<tr>
					<td><font size="2px" color="#000000">专业名称：</td>
					<td><font size="2px" color="red">${department.departmentCname }</font></td>
				</tr>
			</div>
			<form
				action="${pageContext.request.contextPath}/ability_updateability.action"
				method="post">

				<c:forEach items="${abilitylist }" var="ability" varStatus="xh">
					<table cellspacing="10">
						<tr>
							<td>${xh.count }、<input type="text" name="abilityname"
								value="${ability.abilityname }" required /> <input type="hidden"
								name="abilityid" value="${ability.abilityid }" />

							</td>
						</tr>
						<tr style="height: 150px;">
							<td><textarea style="width: 400px; height: 100px;"
									name="abilitycontent" maxlength="255" required>${ability.abilitycontent }</textarea>
							</td>
						</tr>
					</table>
				</c:forEach>
				<input type="hidden" name="departmentid"
					value="${department.departmentid }" required />
				<div class="bottom">
					<button type="submit" value="提交">提交</button>
				</div>
			</form>
		</div>
</body>