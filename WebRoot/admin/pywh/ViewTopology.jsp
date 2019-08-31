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
<script src="<%=basePath%>images/jquery-1.7.1.min.js"
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
	color: #0000c6;
	cursor: pointer;
	width: 158px;
	height: 20px;
}

#box1 a {
	display: block;
	height: 20px;
	width: 158px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath%>images/img/import1-16.png) no-repeat 25px
		2px;
	text-indent: 40px;
	text-indent: 40px;
	text-align: center;
}

#box1 {
	height: 40px;
	float: left;
	width: 158px;
	padding-left: 5px;
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
			<strong>主干课程拓扑图</strong>
		</div>
		<c:if test="${user.adminlevel == 1 || tag == 'revise'}">
			<div id="box1">
				<c:if test="${not empty msg }">
					<a class="t1" target="MainFrame">上传主干课程拓扑图</a>
				</c:if>
				<c:if test="${ empty msg }">
					<a class="t1" target="MainFrame">更改主干课程拓扑图</a>
				</c:if>

			</div>
		</c:if>
		<div style="margin-top: 60px; margin-left: 26px;">
			<tr>
				<td><font size="2px" color="#000000">专业名称：</font></td>
				<td><font size="2px" color="red">${department.departmentCname }</font></td>
			</tr>
		</div>

		<div style="width: 90%; margin-left: 30px;">
			<c:if test="${not empty msg }">
				<p style="color: red;">${msg }</p>
			</c:if>
			<c:if test="${ empty msg }">
				<img
					src="${pageContext.request.contextPath}/topology_showImage.action?department.departmentid=${department.departmentid }" />
			</c:if>
		</div>

	</div>
	<div id="detail">
		<div class="tit">
			<input type="button" class="close" value="X" />
		</div>
		<div id="box4">

			<form name="upfile"
				<c:if test="${not empty msg }">
				action="${pageContext.request.contextPath}/topology_addtopology.action"
				</c:if>
				<c:if test="${ empty msg }">
               action="${pageContext.request.contextPath}/topology_updatetopology.action"
               </c:if>
				method="post" enctype="multipart/form-data">
				<input type="hidden" name="department.departmentid"
					value="${department.departmentid }"></input> <span
					style="color: red;">上传图片大小需小于1M!格式为JPG、PNG、BMP!</span> <input
					type="file" name="image" />
				<td><input id="upfile" type="submit" value="上传"></td>
			</form>

		</div>
	</div>
	<script type="text/javascript">
	$(".t1").click(function(){
			popWin("detail");
	});
	$("#upfile").click(function(){
            var s=document.upfile.image.value; 
            if(s==""){
                alert("请选择需要上传的图片！");
                document.upfile.image.focus();
                return false;
            }
            
            showLoadingWnd('文件正在上传请稍候...');
            document.upfile.submit();
        });
</script>

</body>
</html>
