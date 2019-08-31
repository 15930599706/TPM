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
<script src="<%=basePath %>images/js/jquery-1.7.2.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>images/js/tc.min.js"></script>
<style type="text/css">
#box1 a:hover {
	font-family: "microsoft yahei";
	color: #0000c6;
	cursor: pointer;
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
	background: url(<%=basePath%>images/img/add-16.png) no-repeat 25px 2px;
	text-indent: 40px;
	text-align: center;
}

#box3 a {
	display: block;
	height: 20px;
	width: 90px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath %>images/img/171.png) no-repeat 25px 3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box3 a:hover {
	font-family: "microsoft yahei";
	color: #000;
	background-color: #D2E7F5;
	border: 1px solid #B1CCEB;
	width: 120px;
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
	width: 120px;
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
			<strong>下载信息发布</strong>
		</div>

		<div id="box1">
			<a id="t1" target="MainFrame">添加下载资源</a>
		</div>

		<div style="width: 50%; margin-left: 24px;">
			<form
				action="${pageContext.request.contextPath}/noticeFile_tozyxzPage.action"
				method="post" name="form3">
				<table class="table table-bordered table-striped table-hover">

					<tbody>
					<thead>
						<tr align="center">
							<th nowrap="nowrap"><small>标题</small></th>
							<th nowrap="nowrap"><small>作者</small></th>
							<th nowrap="nowrap"><small>日期</small></th>
							<th nowrap="nowrap"><small>操作</small></th>
						</tr>
					</thead>

					<c:forEach items="${pageBean.noticeFilelist}" var="noticeFile">
						<tr align="center">
							<td nowrap="nowrap"><small><a
									href="${pageContext.request.contextPath}/noticeFile_downLoadNoticeFile.action?noticefileid=${noticeFile.noticefileid}">${noticeFile.noticefilename}</a></small></td>
							<td nowrap="nowrap"><small>${noticeFile.user.username}</small></td>
							<td nowrap="nowrap"><small>[${noticeFile.noticefiletime}]</small></td>

							<td nowrap="nowrap"><a
								href="${pageContext.request.contextPath}/noticeFile_delnoticeFile.action?noticefileid=${noticeFile.noticefileid}"
								onclick="if (confirm('确定要删除该文件吗？')) return true; else return false;"><small>删除</small></a>
							</td>
						</tr>
					</c:forEach>
					<tfoot>
						<tr align="center">
							<th nowrap="nowrap" colspan="11"><c:if
									test="${pageBean.currentpage != 1 }">
									<a
										href="${pageContext.request.contextPath}/noticeFile_tozyxzPage.action"><small>首页</small></a>&nbsp;&nbsp;&nbsp;
									<a
										href="${pageContext.request.contextPath}/noticeFile_tozyxzPage.action?currentpage=${currentpage-1 }"><small>上一页</small></a>
								</c:if>&nbsp;&nbsp;&nbsp; <small>共${pageBean.totalCount }条记录,共计${pageBean.totalPage }页,当前第${pageBean.currentpage }页</small>&nbsp;&nbsp;&nbsp;
								<c:if test="${pageBean.currentpage != pageBean.totalPage }">
									<a
										href="${pageContext.request.contextPath}/noticeFile_tozyxzPage.action?currentpage=${currentpage+1 }"><small>下一页</small></a>&nbsp;&nbsp;&nbsp;
									<a
										href="${pageContext.request.contextPath}/noticeFile_tozyxzPage.action?currentpage=${pageBean.totalPage }"><small>尾页</small></a>
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
	<div id="detail">
		<div class="tit">
			<input type="button" class="close" value="X" />
		</div>

		<div id="box4">

			<form name="upfile"
				action="${pageContext.request.contextPath}/noticeFile_addnoticeFile.action"
				method="post" enctype="multipart/form-data">
				<input type="hidden" name="user.tnum" value="${user.tnum }"></input>
				<span style="color: red;">上传文件大小需小于50M！</span> <input type="file"
					name="upload" />
				<td><input id="upfile" type="submit" value="上传"></td>
			</form>

		</div>
	</div>
	<script type="text/javascript">
	$("#t1").click(function(){
			popWin("detail");
	});
	$("#upfile").click(function(){
            var s=document.upfile.upload.value; 
            if(s==""){
                alert("请选择需要上传的文件！");
                document.upfile.upload.focus();
                return false;
            }
            
            showLoadingWnd('文件正在上传请稍候...');
            document.upfile.submit();
        });
</script>
</body>