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
<script type="text/javascript" src="<%=basePath %>/images/js/animate.js"></script>
<script type="text/javascript"
	src="<%=basePath %>/images/js/loadMask.js"></script>
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
</style>
</head>

<body>

	<div class="right_cont">
		<div class="title_right">
			<strong>实验室人员修改日志</strong>
		</div>

		<div style="width: 70%; margin-left: 25px;">
			<form
				action="${pageContext.request.contextPath}/user_tosysrzAdmin.action"
				method="post" name="form3">
				<table class="table table-bordered table-striped table-hover">
					<tbody>
					<thead>
						<tr align="center">
							<th nowrap="nowrap"><small>修改人</small></th>
							<th nowrap="nowrap"><small>被修改人</small></th>
							<th nowrap="nowrap"><small>修改前所在实验室</small></th>
							<th nowrap="nowrap"><small>修改前职责</small></th>
							<th nowrap="nowrap"><small>操作</small></th>
							<th nowrap="nowrap"><small>修改后所在实验室</small></th>
							<th nowrap="nowrap"><small>修改后职责</small></th>
							<th nowrap="nowrap"><small>修改时间</small></th>
						</tr>
					</thead>
					<c:forEach items="${pageBean.experimentLogList }"
						var="experimentLogs">
						<tr align="center" class="even">
							<td nowrap="nowrap"><small>${experimentLogs.user_modify.username }</small></td>
							<td nowrap="nowrap"><small>${experimentLogs.user_modified.username }</small></td>
							<td nowrap="nowrap"><small>${experimentLogs.experiment_before.experimentname }</small></td>
							<td nowrap="nowrap"><small><c:if
										test="${experimentLogs.experimenterlevel_before == 1 }">实验员</c:if>
									<c:if test="${experimentLogs.experimenterlevel_before == 3 }">实验室主任</c:if></small></td>
							<td nowrap="nowrap"><small>${experimentLogs.operate }</small></td>
							<td nowrap="nowrap"><small>${experimentLogs.experiment_after.experimentname }</small></td>
							<td nowrap="nowrap"><small><c:if
										test="${experimentLogs.experimenterlevel_after == 1 }">实验员</c:if>
									<c:if test="${experimentLogs.experimenterlevel_after == 3 }">实验室主任</c:if></small></td>
							<%-- <td nowrap="nowrap"><small>${experimentLogs.user_modified.experiment.experimentname }</small></td>
								<td nowrap="nowrap"><small><c:if test="${experimentLogs.user_modified.experimenterlevel == 1 }">实验员</c:if><c:if test="${experimentLogs.user_modified.experimenterlevel == 3 }">实验室主任</c:if></small></td>
								 --%>

							<td nowrap="nowrap"><small>${experimentLogs.time }</small></td>
						</tr>
					</c:forEach>
					<c:if test="${pageBean.totalCount ==0}">
						<tr align="center">
							<th nowrap="nowrap" colspan="9"><small>没有修改日志！</small></th>
						</tr>
					</c:if>
					<c:if test="${pageBean.totalPage != 1}">
						<tfoot>
							<tr align="center">
								<th nowrap="nowrap" colspan="9"><c:if
										test="${pageBean.currentpage != 1 }">
										<a
											href="${pageContext.request.contextPath}/user_tosysrzAdmin.action"><small>首页</small></a>&nbsp;&nbsp;&nbsp;
									<a
											href="${pageContext.request.contextPath}/user_tosysrzAdmin.action?currentpage=${currentpage-1 }"><small>上一页</small></a>
									</c:if>&nbsp;&nbsp;&nbsp; <small>共${pageBean.totalCount }条记录,共计${pageBean.totalPage }页,当前第${pageBean.currentpage }页</small>&nbsp;&nbsp;&nbsp;
									<c:if test="${pageBean.currentpage != pageBean.totalPage }">
										<a
											href="${pageContext.request.contextPath}/user_tosysrzAdmin.action?currentpage=${currentpage+1 }"><small>下一页</small></a>&nbsp;&nbsp;&nbsp;
									<a
											href="${pageContext.request.contextPath}/user_tosysrzAdmin.action?currentpage=${pageBean.totalPage }"><small>尾页</small></a>
									</c:if> <small> 第</small><input type="number" name="currentpage"
									onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"
									class="span1" /><small>页 </small> <input type="submit"
									value="跳转">&nbsp;&nbsp;&nbsp;</th>
							</tr>
						</tfoot>
					</c:if>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
