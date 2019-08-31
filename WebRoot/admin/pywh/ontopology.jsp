<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
#box2 {
	font-family: "microsoft yahei";
	height: 30px;
	padding-left: 25px;
	padding-top: 5px;
}

#box3 {
	font-family: "microsoft yahei";
	height: 30px;
	padding-left: 160px;
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
			<strong>上传拓扑图</strong>
		</div>
		<div
			style="_height: 10px; min-height: 10px; padding-left: 25px; padding-top: 5px;">
			<table>
				<td style="vertical-align: top; font-size: 13px;">已上传拓扑图专业方向：</td>
				<td style="vertical-align: top; font-size: 13px;"><c:forEach
						items="${professionallistyes }" var="yes">
						<li style="list-style-type: none;">${yes.professionalname }</li>
					</c:forEach></td>
			</table>
		</div>
		<div
			style="_height: 10px; min-height: 10px; padding-left: 25px; padding-top: 5px;">
			<table>
				<td style="vertical-align: top; font-size: 13px;">未上传拓扑图专业方向：</td>
				<td style="vertical-align: top; font-size: 13px;"><c:forEach
						items="${professionallistno }" var="no">
						<li style="list-style-type: none;">${no.professionalname }</li>
					</c:forEach></td>
			</table>
		</div>
		<div
			style="_height: 10px; min-height: 10px; padding-left: 25px; padding-top: 5px;">
			<table>
				<td style="vertical-align: top; font-size: 13px;">
					请选择需要上传或更改拓扑图的专业方向：</td>
				<td style="vertical-align: top; font-size: 13px;">
					<form
						action="${pageContext.request.contextPath}/topology_addupdatetopology.action"
						name="upfile" method="post" enctype="multipart/form-data">
						<c:forEach items="${professionallistyes }" var="yes">
							<li style="list-style-type: none;"><input name="pid"
								type="checkbox" value="${yes.professionalid }"
								style="align: center; font-size: 13px;" />${yes.professionalname}</label></li>
						</c:forEach>
						<c:forEach items="${professionallistno }" var="no">
							<li style="list-style-type: none; align: center;"><input
								name="pid" type="checkbox" value="${no.professionalid }"
								style="align: center; font-size: 13px;" />${no.professionalname}</li>
						</c:forEach>
				</td>
			</table>
		</div>
		<div id="box3">
			<input type="button" id="t1" target="MainFrame" value="选择上传文件" />
		</div>

	</div>
	<div id="detail">
		<div class="tit">
			<input type="button" class="close" value="X" />
		</div>

		<div id="box4">

			<span style="color: red;">上传图片大小需小于1M!格式为JPG、PNG、BMP!</span> <input
				type="file" name="image" /> <input id="upfile" type="submit"
				value="上传">
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$("#t1").click(function() {

			if ($('input:checked').length == 0) {
				alert("请至少选择一个专业方向");
			} else {
				popWin("detail");
			}
		});

		$("#upfile").click(function() {

			var s = document.upfile.upload.value;
			if (s == "") {
				alert("请选择需要上传的文件！");
				document.upfile.upload.focus();
				return false;
			}

			showLoadingWnd('文件正在上传请稍候...');
			document.upfile.submit();

		});
	</script>
</body>
</html>
