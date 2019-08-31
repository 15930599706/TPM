<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<title>网页访问不鸟</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="<%=basePath %>images/css/error.css">
</head>
<body class="error-404">
	<div id="doc_main">

		<section class="bd clearfix">
			<div class="module-error">
				<div class="error-main clearfix">
					<div class="label"></div>
					<div class="info">
						<h3 class="title">啊哦，你所访问的页面不存在了，可能是炸了</h3>
						<div class="reason">
							<p>可能的原因：</p>
							<p>1.手抖点错了。</p>
							<p>2.链接过了保质期。</p>
							<p>3.服务器异常。</p>
						</div>
						<div class="oper">
							<p>
								<a href="javascript:history.go(-1);">返回上一级页面&gt;</a>
							</p>
							<p>
								<a href="/TPM/user_tomainPage.action">回到网站首页&gt;</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>

</body>
</html>
