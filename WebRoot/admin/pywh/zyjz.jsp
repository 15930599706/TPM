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
<script src="<%=basePath%>images/js/jquery-1.7.2.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>images/js/tc.min.js"></script>
<script type="text/javascript"
	src="<%=basePath %>images/js/jquery.min.js"></script>
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
	color: #0000c6;
	width: 122px;
	height: 20px;
}

#box1 a {
	display: block;
	height: 20px;
	width: 122px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath%>images/img/disk-16.png) no-repeat 25px 2px;
	text-indent: 40px;
	text-align: center;
}

#box3 a {
	display: block;
	height: 20px;
	width: 143px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath%>images/img/disk-16.png) no-repeat 25px 2px;
	text-indent: 40px;
	text-align: center;
}

#box3 a:hover {
	font-family: "microsoft yahei";
	color: #0000c6;
	width: 143px;
	height: 20px;
}

#box1 {
	height: 40px;
	float: left;
	width: 122px;
}

#box3 {
	height: 35px;
	float: left;
	width: 143px;
}

#box2 {
	font-family: "microsoft yahei";
	height: 50px;
	padding-left: 25px;
	padding-top: 50px;
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
			<strong>专业课程矩阵</strong>
		</div>

		<div id="box2">
			<c:if test="${user.adminlevel == 5 }">
				<form
					action="${pageContext.request.contextPath}/curriculumMatrix_tozyjzpage.action?tnum=${user.tnum }"
					method="post" id="finddpartment">
					<td>学院:</td>
					<td><select size="1" name="collegeid"
						onchange="selectSubmit()">
							<option value="-1">所有学院</option>
							<c:forEach items="${pageBean.collegelist }" var="college">
								<option value="${college.collegeid }"
									<c:if test="${college.collegeid eq collegeid }">selected="selected"</c:if>>${college.collegeCname }</option>
							</c:forEach></td> </select>
				</form>
			</c:if>
			<c:if test="${user.adminlevel == 3 }">
					学院：${college.collegeCname}
				</c:if>
			</td>
		</div>
		<c:if test="${pageBean.totalCount != 0 }">
			<div style="width: 70%; margin-left: 24px;">
				<form
					action="${pageContext.request.contextPath}/curriculumMatrix_tozyjzpage?tnum=${user.tnum }&collegeid=${collegeid}"
					method="post" name="form3">
					<table class="table table-bordered table-striped table-hover">
						<tbody>
						<thead>
							<tr align="center">
								<th nowrap="nowrap"><small>序号</small></th>
								<th nowrap="nowrap"><small>专业代码</small></th>
								<th nowrap="nowrap"><small>专业名称</small></th>
								<th width="80" nowrap="nowrap"><small>查看</small></th>
								<c:if test="${user.adminlevel == 5 }">
									<th width="80" nowrap="nowrap"><small>修改</small></th>
								</c:if>
							</tr>
						</thead>
						<c:forEach items="${pageBean.departmentlist }" var="department"
							varStatus="xh">
							<tr align="center" class="even">
								<td nowrap="nowrap"><small>${xh.count }</small></td>
								<td nowrap="nowrap"><small>${department.departmentid }</small></td>
								<td nowrap="nowrap"><small>${department.departmentCname }</small></td>
								<td nowrap="nowrap"><a href="#"
									onclick="chakan('${department.departmentid }')"><small>查看</small></a>
									<!-- 									<a href="${pageContext.request.contextPath}/curriculumMatrix_tobjjzpage.action?departmentid=${department.departmentid }"><small>查看</small></a> -->
									<script type="text/javascript"> 
		function chakan(id){
		showMask({info: "加载中……"});
		window.location.href="${pageContext.request.contextPath}/curriculumMatrix_tobjjzpage.action?departmentid="+id;
		}
	 </script></td>
								<c:if test="${user.adminlevel == 5 }">
									<td nowrap="nowrap"><a href="#"
										onclick="xiugai('${department.departmentid }')"><small>修改</small></a>
										<script type="text/javascript"> 
					function xiugai(id){
					showMask({info: "加载中……"});
					window.location.href="${pageContext.request.contextPath}/curriculumMatrix_revisetobjjzpage.action?departmentid="+id;
					}
	 		</script></td>

								</c:if>
							</tr>
						</c:forEach>
						<c:if test="${pageBean.totalPage != 1}">
							<tfoot>
								<tr align="center">
									<th nowrap="nowrap" colspan="9"><c:if
											test="${pageBean.currentpage != 1 }">
											<a
												href="${pageContext.request.contextPath}/curriculumMatrix_tozyjzpage.action?tnum=${user.tnum }&collegeid=${collegeid}"><small>首页</small></a>&nbsp;&nbsp;&nbsp;
									<a
												href="${pageContext.request.contextPath}/curriculumMatrix_tozyjzpage.action?currentpage=${currentpage-1 }&tnum=${user.tnum }&collegeid=${collegeid}"><small>上一页</small></a>
										</c:if>&nbsp;&nbsp;&nbsp; <small>共${pageBean.totalCount }条记录,共计${pageBean.totalPage }页,当前第${pageBean.currentpage }页</small>&nbsp;&nbsp;&nbsp;
										<c:if test="${pageBean.currentpage != pageBean.totalPage }">
											<a
												href="${pageContext.request.contextPath}/curriculumMatrix_tozyjzpage.action?currentpage=${currentpage+1 }&tnum=${user.tnum }&collegeid=${collegeid}"><small>下一页</small></a>&nbsp;&nbsp;&nbsp;
									<a
												href="${pageContext.request.contextPath}/curriculumMatrix_tozyjzpage.action?currentpage=${pageBean.totalPage }&tnum=${user.tnum }&collegeid=${collegeid}"><small>尾页</small></a>
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
		</c:if>
	</div>
	<script language="javascript">
		function selectSubmit()
		{
			var form=document.getElementById("finddpartment");
			form.action="${pageContext.request.contextPath}/curriculumMatrix_tozyjzpage.action?tnum=${user.tnum }";
			form.submit();  
		}
</script>
</body>
</html>
