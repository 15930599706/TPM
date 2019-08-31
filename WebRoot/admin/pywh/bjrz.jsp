<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
.bottom {
	padding-left: 50%;
}

#box5 {
	font-family: "microsoft yahei";
	height: 50px;
	padding-left: 25px;
	padding-top: 25px;
}

#box2 {
	font-family: "microsoft yahei";
	height: 22px;
	float: left;
	width: 1000px;
}

#sl {
	width: 150px;
}

#s2 {
	width: 100px;
}

#lb #xq {
	width: 100px;
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
</style>
</head>

<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>修改专业认证课程</strong>
		</div>
		<div style="text-align: center;">
			<h3>
				修改专业认证课程
				</h1>
		</div>
		<div id="box2">
			<span
				style="color: red; font-size: 14px; font-family: '微软雅黑'; font-weight: bold;">注：专业认证为“否”的课程将不在专业课程矩阵中显示。</span>


		</div>
		<form
			action="${pageContext.request.contextPath}/curriculumMatrix_modifyDetermine.action"
			method="post">
			<div style="width: 90%;">
				<form action="" method="post" name="form3">
					<table class="table table-bordered table-striped table-hover">

						<tbody>
						<thead>
							<tr align="center">
								<th nowrap="nowrap"><small>专业</small></th>
								<th nowrap="nowrap"><small>专业方向</small></th>
								<th nowrap="nowrap"><small>课程编码</small></th>
								<th nowrap="nowrap"><small>课程名称</small>
								</td>
								<th nowrap="nowrap"><small>是否认证</small>
								</td>
							</tr>
						</thead>

						<!-- <tr align="center"> -->


						<c:forEach items="${curriculumlist_pro}" var="curriculum_pro">
							<tr align="center">
								<td nowrap="nowrap"><small>${curriculum_pro.department.departmentCname}</small><input
									type="hidden" name="departmentCnameID"
									value="${curriculum_pro.department.departmentid}" /></td>
								<td nowrap="nowrap"><small>${curriculum_pro.professional.professionalname}</small><input
									type="hidden" name="professionalnameID"
									value="${curriculum_pro.professional.professionalid}" /></td>
								<td nowrap="nowrap"><small>${curriculum_pro.curriculum.curriculumid }</small><input
									type="hidden" name="HavecurriculumId"
									value="${curriculum_pro.curriculum.curriculumid}" /></td>
								<td nowrap="nowrap"><small>${curriculum_pro.curriculum.curriculumCname }</small></td>
								<td><select name="renzheng" style="width: 50px;">
										<option value="是"
											<c:if test="${curriculum_pro.tag eq '是' || curriculum_pro.tag == null }">selected="selected"</c:if>>是</option>
										<option value="否"
											<c:if test="${curriculum_pro.tag eq '否' }">selected="selected"</c:if>>否</option>
								</select></td>
							</tr>
						</c:forEach>


						</tbody>
					</table>
			</div>
			<div style="width: 100px; margin: 0 auto;">
				<button type="submit" value="确认修改">确认修改</button>
			</div>
		</form>

		<script type="text/javascript" src="js/jquery.min.js"></script>
</body>