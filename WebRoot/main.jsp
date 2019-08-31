<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
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
.rlist {
	padding-top: 35px
}

.news div {
	display: inline-block;
}

.news-user {
	margin-left: 300px;
	font-size: 13px;
}

.news-date {
	margin-left: 15px;
	font-size: 13px;
}

.news:hover {
	background-color: #FFE7a2;
	color: #B94A48;
	width: 633PX;
}

span {
	font-size: 18px;
	color: #0087E8;
	font-weight: bold;
}

#left a {
	text-decoration: none;
	font-size: 13px;
	color: #0087E8;
}

#left a:hover {
	background-color: #FFE7a2;
	color: #B94A48;
}

ul {
	list-style-image: url(images/img/xw.png);
	margin: 4px;
}

#hh {
	padding-top: 5px;
}

#ll {
	padding-top: 5px;
}

#title a {
	text-decoration: none;
	font-size: 13px;
	padding-left: 553px;
	padding-bottom: 1px;
	color: #CCCCCC;
}

#title a:hover {
	text-decoration: underline;
	font-size: 13px;
}

#title1 a {
	text-decoration: none;
	font-size: 13px;
	padding-left: 553px;
	padding-bottom: 1px;
	color: #CCCCCC;
}

#title1 a:hover {
	text-decoration: underline;
	font-size: 13px;
}

#title {
	width: 670px;
	border-bottom: 1px solid #dedede;
	height: 26px;
}

#title1 {
	height: 30px;
	width: 670px;
	border-bottom: 1px solid #dedede;
	height: 26px;
}
</style>
</head>

<body>
	<div class="right_cont">
		<div style="width: 900px; margin: auto;">
			<div class="main">
				<div class="rmain">

					<div class="rlist">
						<div id="title">
							<span>新闻消息</span> <a
								href="${pageContext.request.contextPath}/notice_tochecktzfbPage.action">更多></a>
						</div>

						<div id="hh">
							<c:forEach items="${requestScope.pageBean.noticelist}"
								var="notice">
								<ul class="news">
									<li>
										<div id="left">
											<a
												href="${pageContext.request.contextPath}/notice_shownotice.action?noticeid=${notice.noticeid}">${notice.noticetitle}</a>
										</div>
										<div class="news-date">[${notice.noticetime}]</div>
									</li>
								</ul>
							</c:forEach>

						</div>
					</div>
					<div class="rlist">
						<div id="title1">
							<span>资源下载</span> <a
								href="${pageContext.request.contextPath}/noticeFile_tocheckzyxzPage.action">更多></a>
						</div>

						<div id="ll">
							<c:forEach items="${requestScope.pageBean.noticeFilelist}"
								var="noticeFile">
								<ul class="news">
									<li>
										<div id="left">
											<a
												href="${pageContext.request.contextPath}/noticeFile_downLoadNoticeFile.action?noticefileid=${noticeFile.noticefileid}">${noticeFile.noticefilename}</a>
										</div>
										<div class="news-date">[${noticeFile.noticefiletime}]</div>
									</li>
								</ul>
							</c:forEach>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>