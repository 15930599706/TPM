<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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

#box1 a:hover {
	font-family: "microsoft yahei";
	color: #000;
	background-color: #D2E7F5;
	border: 1px solid #B1CCEB;
	width: 118px;
	height: 20px;
}

#box1 a {
	display: block;
	height: 20px;
	width: 90px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath%>images/img/add-16.png) no-repeat 25px 3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box3 a {
	display: block;
	height: 20px;
	width: 90px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath%>images/img/import1-16.png) no-repeat 25px
		3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box3 a:hover {
	font-family: "microsoft yahei";
	color: #000;
	background-color: #D2E7F5;
	border: 1px solid #B1CCEB;
	width: 118px;
	height: 20px;
}

#box1 {
	height: 40px;
	float: left;
	width: 120px;
}

#box3 {
	height: 35px;
	float: left;
	width: 120px;
}

#box2 {
	font-family: "microsoft yahei";
	height: 35px;
	padding-left: 25px;
	padding-top: 1px;
}

#box4 {
	font-family: "microsoft yahei";
	height: 50px;
	padding-left: 25px;
	padding-top: 25px;
}
</style>
</head>

<body>

	<div class="right_cont">
		<div class="title_right">
			<strong>添加实验员</strong>
		</div>

		<form method="post"
			action="${pageContext.request.contextPath}/user_totjsyyPage.action?tnum=${user.tnum}&experiment.experimentid=${experiment.experimentid}"
			id="dlzh">
			<div id="box2">
				实验室：<font style="color: red">${experiment.experimentname}</font>
			</div>
			<div id="box2">
				<td>学院：</td>
				<td><select size="1" id="xueyuan" name="college.collegeid">
						<c:forEach items="${pageBean.collegelist}" var="college">
							<option value="${college.collegeid }"
								<c:if test="${newuser.college.collegeid == college.collegeid }">selected="selected"</c:if>>${college.collegeCname}</option>
						</c:forEach>
				</select></td>

				<td width="40%" align="right" nowrap="nowrap">教师姓名：</td>
				<td><input name="username" size="1"
					value="${newuser.username }" /></td>
				<tr>

					<td class="text-center" colspan="0"><input type="submit"
						value="检索" class="btn btn-info  " style="width: 50px;" /></td>
			</div>
		</form>
		<span Style="font-size: 15px; color: red; text-align: center;">${msg }</span>
		<div style="width: 70%; margin-left: 24px;">
			<form
				action="${pageContext.request.contextPath}/user_totjsyyPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&experiment.experimentid=${experiment.experimentid}"
				method="post" name="form3">
				<table class="table table-bordered table-striped table-hover">
					<tbody>
					<thead>
						<tr align="center">
							<th nowrap="nowrap"><small>教工号</small></th>
							<th nowrap="nowrap"><small>姓名</small></th>
							<th nowrap="nowrap"><small>部门（学院）</small></th>
							<th nowrap="nowrap"><small>实验室</small></th>
							<th nowrap="nowrap"><small>职责</small></th>
							<th width="80" nowrap="nowrap"><small>操作</small></th>
						</tr>
					</thead>
					<c:forEach items="${pageBean.userlist }" var="users">
						<tr align="center" class="even">
							<td nowrap="nowrap"><small>${users.tnum }</small></td>
							<td nowrap="nowrap"><small>${users.username }</small></td>
							<td nowrap="nowrap"><small>${users.college.collegeCname }</small></td>
							<td nowrap="nowrap"><small>${users.experiment.experimentname }</small></td>
							<td nowrap="nowrap"><small><c:if
										test="${users.experimenterlevel == 1 }">实验员</c:if>
									<c:if test="${users.experimenterlevel == 3 }">实验室主任</c:if></small></td>
							<td nowrap="nowrap"><a
								href="${pageContext.request.contextPath}/user_toexperimenter.action?modifyid=${user.tnum}&tnum=${users.tnum }&experiment.experimentid=${experiment.experimentid}"
								onclick="if (confirm('您确定要将该教师设置为所选实验室的实验员吗？')) return true; else return false;"><small>设置为实验员</small></a>

							</td>
						</tr>
					</c:forEach>
					<c:if test="${pageBean.totalCount ==0}">
						<tr align="center">
							<th nowrap="nowrap" colspan="9"><small>没有符合要求的用户！</small></th>
						</tr>
					</c:if>
					<c:if test="${pageBean.totalPage != 1}">
						<tfoot>
							<tr align="center">
								<th nowrap="nowrap" colspan="9"><c:if
										test="${pageBean.currentpage != 1 }">
										<a
											href="${pageContext.request.contextPath}/user_totjsyyPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&experiment.experimentid=${experiment.experimentid}"><small>首页</small></a>&nbsp;&nbsp;&nbsp;
									<a
											href="${pageContext.request.contextPath}/user_totjsyyPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&experiment.experimentid=${experiment.experimentid}&currentpage=${currentpage-1 }"><small>上一页</small></a>
									</c:if>&nbsp;&nbsp;&nbsp; <small>共${pageBean.totalCount }条记录,共计${pageBean.totalPage }页,当前第${pageBean.currentpage }页</small>&nbsp;&nbsp;&nbsp;
									<c:if test="${pageBean.currentpage != pageBean.totalPage }">
										<a
											href="${pageContext.request.contextPath}/user_totjsyyPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&experiment.experimentid=${experiment.experimentid}&currentpage=${currentpage+1 }"><small>下一页</small></a>&nbsp;&nbsp;&nbsp;
									<a
											href="${pageContext.request.contextPath}/user_totjsyyPage.action?tnum=${user.tnum}&username=${newuser.username}&college.collegeid=${newuser.college.collegeid}&experiment.experimentid=${experiment.experimentid}&currentpage=${pageBean.totalPage }"><small>尾页</small></a>
									</c:if> <small> 第</small><input type="number" name="currentpage"
									onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
									class="span1" /><small>页 </small> <input type="submit"
									value="跳转">&nbsp;&nbsp;&nbsp;</th>
							</tr>
						</tfoot>
					</c:if>
					</tr>
					</tfoot>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div id="detail">
		<div class="tit">
			<input type="button" class="close" value="X" />
		</div>
		<div id="box4"></div>
	</div>
</body>
</html>
