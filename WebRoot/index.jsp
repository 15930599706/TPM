<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<HTML>
<HEAD>

<META http-equiv=Content-Type content="text/html; charset=gb2312">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</HEAD>

<frameset rows="56, *" border="0" framespacing="0" frameborder="0">
	<frame src="top.jsp" id="top" scrolling="NO">
	<frameset id="topwin" framespacing="0" border="0" cols="258,*"
		frameborder="0">
		<frame id="left" src="left.jsp" target="MainFrame">
		<FRAME name=MainFrame
			src="${pageContext.request.contextPath}/user_tomainPage.action"
			frameBorder="0" ;scrolling="auto">
	</frameset>

	<noframes>
	</noframes>
</frameset>