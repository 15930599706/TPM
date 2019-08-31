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
<script type="text/javascript" src=<%=basePath%>images/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>images/js/sdmenu.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/laydate/laydate.js"></script>
<script src="<%=basePath%>images/js/jquery-1.7.2.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>images/js/tc.min.js"></script>
<style type="text/css">
#box5 {
	font-family: "microsoft yahei";
	height: 50px;
	padding-left: 25px;
	padding-top: 25px;
}

#box1 a:hover {
	font-family: "microsoft yahei";
	color: #000;
	background-color: #D2E7F5;
	border: 1px solid #B1CCEB;
	width: 88px;
	height: 20px;
}

#box1 a {
	display: block;
	height: 20px;
	width: 88px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #0000C6;
	background: url(<%=basePath%>images/img/add-16.png) no-repeat 25px 3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box1 {
	height: 60px;
	float: left;
	width: 100px;
}

#box2 {
	font-family: "microsoft yahei";
	height: 50px;
	float: left;
	width: 1000px;
	padding-left: 24px;
}

#sl {
	width: 160px;
}

#lb #xq {
	width: 100px;
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

#box7 a:hover {
	font-family: "microsoft yahei";
	color: #0000c6;
	width: 142px;
	height: 20px;
}

#box7 a {
	display: block;
	height: 20px;
	width: 142px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath%>images/img/export-16.png) no-repeat 25px
		2px;
	text-indent: 40px;
	text-align: center;
}

#box7 {
	height: 50px;
	float: left;
	width: 142px;
}
</style>
</head>
<script language="javascript">
	function selectSubmit()
		{			
			var form=document.getElementById("PreCreErr");
			form.action="${pageContext.request.contextPath}/errorDisc_topreCreErrPage.action?tnum=${user.tnum}";
			form.submit();  
		}
	</script>

<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>学时学分对应错误</strong>
		</div>
		<div>
			<div id="box7">
				<a
					href="${pageContext.request.contextPath}/errorDisc_exportPreCreErrExcel.action?tnum=${user.tnum}&collegeid=${xueyuan}"
					target="MainFrame">导出Excel文件</a>
			</div>
		</div>
		<span style="color: red; padding-left: 24px;"><strong>说明：</strong>此处修改的课程信息将联动更新至计划进程表及课程大纲。因此，请谨慎修改课程信息。</span>
		<div id="box2">
			<form method="post" id="PreCreErr">
				<td>学院：</td>
				<c:if test="${user.adminlevel == 5 }">
					<td><select size="1" id="sl" name="collegeid"
						onChange="selectSubmit()">
							<option value="-1" selected="selected">全部</option>
							<c:forEach items="${collegelist}" var="college">
								<option value="${college.collegeid }"
									<c:if test="${college.collegeid eq xueyuan }">selected="selected"</c:if>>${college.collegeCname}</option>
							</c:forEach>
					</select></td>
				</c:if>
				<c:if test="${user.adminlevel == 3 }">${user.college.collegeCname }</c:if>
			</form>
		</div>

		<div style="width: 80%; margin-left: 24px;">
			<form action="" method="post" name="form3">
				<table class="table table-bordered table-striped table-hover">

					<tbody>
					<thead>
						<tr align="center">
							<th nowrap="nowrap"><small>开课学院</small></th>
							<th nowrap="nowrap"><small>课程编号</small></th>
							<th nowrap="nowrap"><small>课程名称</small></th>
							<th nowrap="nowrap"><small>学分</small></th>
							<th nowrap="nowrap"><small>总学时</small></th>
							<th nowrap="nowrap"><small>讲课学时</small></th>
							<th nowrap="nowrap"><small>上机学时</small></th>
							<th nowrap="nowrap"><small>实验学时</small></th>
							<th nowrap="nowrap"><small>实践学时</small></th>
							<th nowrap="nowrap"><small>问题说明</small></th>
							<th width="80" nowrap="nowrap"><small>编辑</small></th>
							<th width="80" nowrap="nowrap"><small>删除</small></th>
						</tr>
					</thead>
					<c:if test="${preCreErrListCount > 0 }">
						<c:forEach items="${preCreErrList }" var="preCreErr">
							<tr align="center">
								<td nowrap="nowrap"><small>${preCreErr.college.collegeCname }</small></td>
								<td nowrap="nowrap"><small>${preCreErr.curriculumid }</small></td>
								<td nowrap="nowrap"><small>${preCreErr.curriculumCname }</small></td>
								<td nowrap="nowrap"><small>${preCreErr.credit }</small></td>
								<td nowrap="nowrap"><small>${preCreErr.hoursOfALL }</small></td>
								<td nowrap="nowrap"><small>${preCreErr.hoursOfClass }</small></td>
								<td nowrap="nowrap"><small>${preCreErr.hoursOfCom }</small></td>
								<td nowrap="nowrap"><small>${preCreErr.hoursOfExp }</small></td>
								<td nowrap="nowrap"><small>${preCreErr.hoursOfPractice }</small></td>
								<td nowrap="nowrap"><small style="color: red;">学时学分对应错误。</small></td>
								<c:if
									test="${user.adminlevel == 5 || (user.adminlevel == 3 && preCreErr.newcurriculum == '1' && user.college.collegeid eq preCreErr.college.collegeid)}">
									<td nowrap="nowrap"><a
										href="${pageContext.request.contextPath}/curriculum_tokcupdatePage.action?tnum=${user.tnum}&curriculumid=${preCreErr.curriculumid }&perCreErr=1"><small>编辑</small></a>
									</td>
									<td nowrap="nowrap"><a
										href="${pageContext.request.contextPath}/curriculum_tokcdelPage.action?curriculumid=${preCreErr.curriculumid }&perCreErr=1"
										onclick="if (confirm('确定要删除该课程吗？')) return true; else return false;"><small>删除</small></a>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${preCreErrListCount == 0 }">
						<tr align="center">
							<th nowrap="nowrap" colspan="9"><small>没有学时学分对应错误的课程！</small>
							</th>
						</tr>
					</c:if>

					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div id="detail">
		<div class="tit">
			<input type="button" class="close" value="X" />
		</div>
		<div id="box5">

			<form>
				<input type="file" name="exc" />

			</form>
			<form>

				<td><a href="#" style="text-decoration: none;">&nbsp;&nbsp;&nbsp;&nbsp;导入</a></td>
			</form>

		</div>
		<script type="text/javascript">
	$("#t1").click(function(){
			popWin("detail");
	});
</script>
</body>
</html>