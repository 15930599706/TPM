<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=basePath %>images/css/bootstrap.css" />
<link rel="stylesheet" href="<%=basePath %>images/css/css.css" />
<script type="text/javascript"
	src="<%=basePath %>images/js/jquery1.9.0.min.js"></script>
<script type="text/javascript"
	src="<%=basePath %>images/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath %>images/js/sdmenu.js"></script>
<script type="text/javascript"
	src="<%=basePath %>images/js/laydate/laydate.js"></script>
<style type="text/css">
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
	width: 120px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath %>images/img/add-16.png) no-repeat 25px 3px;
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
	background: url(<%=basePath %>images/img/add-16.png) no-repeat 25px 3px;
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
	width: 130px;
}

#box3 {
	height: 35px;
	float: left;
	width: 120px;
}
</style>
</head>

<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>通知信息</strong>
		</div>

		<div style="width: 50%; margin-left: 24px;">
			<form
				action="${pageContext.request.contextPath}/notice_tochecktzfbPage.action"
				method="post" name="form3">
				<table class="table table-bordered table-striped table-hover">

					<tbody>
					<thead>
						<tr align="center">
							<th nowrap="nowrap"><small>标题</small></th>
							<th nowrap="nowrap"><small>发布人</small></th>
							<th nowrap="nowrap"><small>日期</small></th>
						</tr>
					</thead>
					<c:forEach items="${pageBean.noticelist}" var="notice">
						<tr align="center">
							<td nowrap="nowrap"><small><a
									href="${pageContext.request.contextPath}/notice_shownotice.action?noticeid=${notice.noticeid}">${notice.noticetitle}</a></small></td>
							<td nowrap="nowrap"><small>${notice.user.username}</small></td>
							<td nowrap="nowrap"><small>[${notice.noticetime}]</small></td>
						</tr>
					</c:forEach>
					<tfoot>
						<tr align="center">
							<th nowrap="nowrap" colspan="11"><c:if
									test="${pageBean.currentpage != 1 }">
									<a
										href="${pageContext.request.contextPath}/notice_tochecktzfbPage.action"><small>首页</small></a>&nbsp;&nbsp;&nbsp;
									<a
										href="${pageContext.request.contextPath}/notice_tochecktzfbPage.action?currentpage=${currentpage-1 }"><small>上一页</small></a>
								</c:if>&nbsp;&nbsp;&nbsp; <small>共${pageBean.totalCount }条记录,共计${pageBean.totalPage }页,当前第${pageBean.currentpage }页</small>&nbsp;&nbsp;&nbsp;
								<c:if test="${pageBean.currentpage != pageBean.totalPage }">
									<a
										href="${pageContext.request.contextPath}/notice_tochecktzfbPage.action?currentpage=${currentpage+1 }"><small>下一页</small></a>&nbsp;&nbsp;&nbsp;
									<a
										href="${pageContext.request.contextPath}/notice_tochecktzfbPage.action?currentpage=${pageBean.totalPage }"><small>尾页</small></a>
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