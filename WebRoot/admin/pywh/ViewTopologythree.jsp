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
	width: 148px;
}

#box1 a {
	display: block;
	height: 20px;
	width: 148px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath%>images/img/import1-16.png) no-repeat 25px
		2px;
	text-indent: 40px;
	text-align: center;
}

#box1 {
	height: 40px;
	float: left;
	width: 148px;
	padding-left: 5px;
}

#box2 {
	font-family: "microsoft yahei";
	height: 50px;
	padding-left: 26px;
	padding-top: 50px;
}

#box4 {
	font-family: "microsoft yahei";
	height: 50px;
	padding-left: 26px;
	padding-top: 50px;
}
</style>
</head>

<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>主干课程拓扑图</strong>
		</div>

		<c:if test="${user.adminlevel == 1}">
			<div id="box1">
				<a
					href="topology_changetag.action?department.departmentid=${department.departmentid }">改为整个专业上传</a>
			</div>
		</c:if>

		<c:if test="${user.adminlevel == 5}">
			<div id="box1">
				<a
					href="topology_revisechangetag.action?department.departmentid=${department.departmentid }">改为整个专业上传</a>
			</div>
		</c:if>

		<div id="box1">
			<a
				href="topology_ontopology.action?department.departmentid=${department.departmentid }">上传或修改拓扑图</a>
		</div>

		<div id="box4">
			<form action="" id="findprofessional">
				专业方向：<select name="professional.professionalid" id="professional"
					onchange="selectSubmit()" size="1">
					<option value="-1">请选择专业方向</option>
					<c:forEach items="${professionallist }" var="professional">
						<option value="${professional.professionalid }"
							<c:if test="${pid.professionalid == professional.professionalid }">selected="selected"</c:if>>${professional.professionalname }</option>
					</c:forEach>
				</select>
			</form>
		</div>
		<c:if test="${not empty pid }">
			<div style="width: 90%; margin-left: 30px;">
				<c:if test="${not empty hf }">
					<img
						src="${pageContext.request.contextPath}/topology_showImagepp.action?professional.professionalid=${pid.professionalid }" />

				</c:if>
				<c:if test="${ empty hf }">
					<p style="color: red;">该专业方向还未上传拓扑图！</p>
				</c:if>
			</div>
		</c:if>

	</div>

	<script type="text/javascript">
	function selectSubmit()
		{	
			var professionalid = document.getElementById("professional").value;
			if(professionalid != "-1"){
				var form=document.getElementById("findprofessional");
				form.action="${pageContext.request.contextPath}/topology_findtopo.action";
				form.submit();
			}
		}
</script>

</body>
</html>
