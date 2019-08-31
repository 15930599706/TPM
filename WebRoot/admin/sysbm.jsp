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
#box1 a:hover {
	font-family: "microsoft yahei";
	color: #0000c6;
	width: 73px;
	height: 20px;
}

#box1 a {
	display: block;
	height: 20px;
	width: 73px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath%>images/img/export-16.png) no-repeat 25px
		2px;
	text-indent: 40px;
	text-align: center;
}

#box1 {
	height: 40px;
	float: left;
	width: 73px;
}

#box2 {
	font-family: "microsoft yahei";
	height: 50px;
	width: 1000px;
	padding-left: 24px;
	padding-top: 40px;
}
</style>
</head>

<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>查看实验室编码信息</strong>
		</div>

		<%-- 			<div id="box1">
				<a href="${pageContext.request.contextPath}/user_exportDepartmentExcel.action" target="MainFrame">导出</a>

			</div>  --%>
		<div id="box2">
			<form
				action="${pageContext.request.contextPath}/user_tosysbmPage.action"
				method="post" id="findcurriculum">
				<td>部门(学院)：</td>
				<td><select size="1" name="college.collegeid">
						<!-- onchange="selectsubmit()" -->
						<option value="-1">---所有学院---</option>
						<c:forEach items="${pageBean.collegelist }" var="college">
							<option value="${college.collegeid}"
								<c:if test="${college.collegeid eq newcollege }">selected="selected"</c:if>>${college.collegeCname}</option>
						</c:forEach>
				</select></td>
				<td class="text-center" colspan="0"><input type="submit"
					value="检索" class="btn btn-info  " style="width: 50px;" /></td>
			</form>

		</div>


		<div style="width: 50%; margin-left: 24px;">
			<form
				action="${pageContext.request.contextPath}/user_tosysbmPage.action?college.collegeid=${newcollege}"
				method="post" name="form3">
				<table class="table table-bordered table-striped table-hover">

					<tbody>
					<thead>
						<tr align="center">
							<th nowrap="nowrap"><small>实验室编码</small></th>
							<th nowrap="nowrap"><small>名称</small></th>
							<th nowrap="nowrap"><small>所属学院</small></th>

						</tr>
					</thead>

					<c:forEach items="${pageBean.experimentlist }" var="experiment">
						<tr align="center">
							<td nowrap="nowrap"><small>${experiment.experimentid }</small></td>
							<td nowrap="nowrap"><small>${experiment.experimentname }</small></td>
							<td nowrap="nowrap"><small>${experiment.college.collegeCname }</small></td>
						</tr>
					</c:forEach>
					<c:if test="${pageBean.totalCount == 0 }">
						<tr align="center">
							<th nowrap="nowrap" colspan="11">此学院尚未有所属实验室</th>
						</tr>
					</c:if>
					<tfoot>
						<tr align="center">
							<th nowrap="nowrap" colspan="9"><c:if
									test="${pageBean.currentpage != 1 }">
									<a
										href="${pageContext.request.contextPath}/user_tosysbmPage.action?college.collegeid=${newcollege}"><small>首页</small></a>&nbsp;&nbsp;&nbsp;
									<a
										href="${pageContext.request.contextPath}/user_tosysbmPage.action?currentpage=${currentpage-1 }&college.collegeid=${newcollege}"><small>上一页</small></a>&nbsp;&nbsp;&nbsp;
									</c:if> <small>共${pageBean.totalCount }条记录,共计${pageBean.totalPage }页,当前第${pageBean.currentpage }页</small>&nbsp;&nbsp;&nbsp;
								<c:if test="${pageBean.currentpage != pageBean.totalPage }">
									<a
										href="${pageContext.request.contextPath}/user_tosysbmPage.action?currentpage=${currentpage+1 }&college.collegeid=${newcollege}"><small>下一页</small></a>&nbsp;&nbsp;&nbsp;
									<a
										href="${pageContext.request.contextPath}/user_tosysbmPage.action?currentpage=${pageBean.totalPage }&college.collegeid=${newcollege}"><small>尾页</small></a>
								</c:if> <small> 第</small><input type="number" name="currentpage"
								onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
								class="span1" /><small>页 </small> <input type="submit"
								value="跳转">&nbsp;&nbsp;&nbsp;</th>
						</tr>
					</tfoot>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>