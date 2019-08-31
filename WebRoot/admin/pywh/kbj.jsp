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
<script type="text/javascript" src="<%=basePath%>images/js/bui-min.js"></script>
<script type="text/javascript" src="<%=basePath%>images/js/sdmenu.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/laydate/laydate.js"></script>
<style type="text/css">
#box3 a {
	display: block;
	height: 20px;
	width: 97px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000000;
	background: url(<%=basePath%>images/img/import1-16.png) no-repeat 25px
		3px;
	text-indent: 40px;
	text-align: center;
}

#box3 {
	height: 35px;
	float: left;
	width: 97px;
	padding-top: 10px;
}

#box4 {
	font-family: "microsoft yahei";
	height: 50px;
	padding-left: 25px;
	padding-top: 25px;
}

#box3 a:hover {
	font-family: "microsoft yahei";
	color: #0000c6;
	width: 97px;
	height: 20px;
}

#box1 a:hover {
	font-family: "microsoft yahei";
	color: #00006c;
	width: 97px;
	height: 20px;
}

#box1 a {
	display: block;
	height: 20px;
	width: 97px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath%>images/img/add-16.png) no-repeat 25px 3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box10 {
	height: 40px;
	float: left;
	width: 97px;
	padding-top: 10px;
}

#box10 a:hover {
	font-family: "microsoft yahei";
	color: #00006c;
	width: 97px;
	height: 20px;
}

#box10 a {
	display: block;
	height: 20px;
	width: 97px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath%>images/img/28.png) no-repeat 25px 3px;
	text-indent: 40px;
	padding: 2px 0;
	text-align: center;
}

#box1 {
	height: 40px;
	float: left;
	width: 97px;
	padding-top: 10px;
}

#box2 {
	font-family: "microsoft yahei";
	height: 50px;
	padding-left: 25px;
	padding-top: 50px;
	width: 1003px;
}
</style>
</head>

<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>可编辑课程信息</strong>
		</div>
		<div style="width: 1000px;">
			<span style="color: red; padding-left: 24px;"><strong>说明：</strong>此处修改的课程信息将联动更新至计划进程表及课程大纲。因此，请谨慎修改课程信息。</span>
		</div>


		<div id="box10">
			<a
				href="${pageContext.request.contextPath}/curriculum_tokcxxPage.action?college.collegeid=-1"
				target="MainFrame">所有课程</a>

		</div>
		<div id="box1">
			<a
				href="${pageContext.request.contextPath}/curriculum_tokcaddPage.action?tnum=${user.tnum}"
				target="MainFrame">添加课程</a>

		</div>
		<div id="box2">

			<form
				action="${pageContext.request.contextPath}/curriculum_tokbjPage.action?tnum=${user.tnum}"
				method="post" id="findcurriculum">
				<td>开课部门(学院)：</td>
				<td><select size="1" name="college.collegeid"
					onchange="selectSubmit()">
						<c:if test="${sessionScope.user.adminlevel eq 5}">
							<option value="-1">---所有学院---</option>
						</c:if>
						<c:forEach items="${pageBean.collegelist }" var="college">
							<option value="${college.collegeid}"
								<c:if test="${college.collegeid eq curriculum.college.collegeid }">selected="selected"</c:if>>${college.collegeCname}</option>
						</c:forEach>

				</select></td>

				<td width="40%" align="right" nowrap="nowrap">课程编号：</td>
				<td><input size="1" name="curriculumid" class="span3"
					value="${curriculum.curriculumid }" style="width: 110px;" /></td>
				<td width="40%" align="right" nowrap="nowrap">课程名称：</td>
				<td><input size="1" name="curriculumCname" class="span3"
					value="${curriculum.curriculumCname }" /></td>
				<tr>
					<td class="text-center" colspan="0"><input type="submit"
						value="检索" class="btn btn-info  " style="width: 50px;" /></td>
			</form>
		</div>

		<div style="width: 90%; margin-left: 24px;">
			<form
				action="${pageContext.request.contextPath}/curriculum_tokbjPage.action?college.collegeid=${curriculum.college.collegeid}&curriculumid=${curriculum.curriculumid}&curriculumCname=${curriculum.curriculumCname}&tnum=${user.tnum}"
				method="post" name="form3">
				<table class="table table-bordered table-striped table-hover">

					<tbody>
					<thead>
						<tr align="center">
							<th nowrap="nowrap"><small>课程代码</small></th>
							<th nowrap="nowrap"><small>课程中文名称</small>
							</td>
							<th nowrap="nowrap"><small>开课部门</small></th>
							<th nowrap="nowrap"><small>课程性质</small></th>
							<th nowrap="nowrap"><small>课程类别</small></th>
							<th nowrap="nowrap"><small>学分</small></th>
							<th nowrap="nowrap"><small>总学时</small></th>
							<th nowrap="nowrap"><small>讲课学时</small></th>
							<th nowrap="nowrap"><small>实验学时</small></th>
							<th nowrap="nowrap"><small>上机学时</small></th>
							<th nowrap="nowrap"><small>课程实践学时</small></th>
							<th width="80" nowrap="nowrap"><small>编辑</small></th>
							<th width="80" nowrap="nowrap"><small>删除</small></th>
							<th width="80" nowrap="nowrap"><small>生成新课</small></th>

						</tr>
					</thead>

					<c:forEach items="${pageBean.curriculumlist }" var="curriculum">
						<tr align="center">
							<td nowrap="nowrap"><small>${curriculum.curriculumid }</small></td>
							<td nowrap="nowrap"><small>${curriculum.curriculumCname }</small></td>
							<td nowrap="nowrap"><small>${curriculum.college.collegeCname }</small></td>
							<td nowrap="nowrap"><small>${curriculum.natureOfCourse.natureOfCoursename }</small></td>
							<td nowrap="nowrap"><small>${curriculum.courseLei }</small></td>
							<td nowrap="nowrap"><small>${curriculum.credit }</small></td>
							<td nowrap="nowrap"><small>${curriculum.hoursOfALL }</small></td>
							<td nowrap="nowrap"><small>${curriculum.hoursOfClass }</small></td>
							<td nowrap="nowrap"><small>${curriculum.hoursOfExp }</small></td>
							<td nowrap="nowrap"><small>${curriculum.hoursOfCom }</small></td>
							<td nowrap="nowrap"><small>${curriculum.hoursOfPractice }</small></td>
							<td nowrap="nowrap"><a
								href="${pageContext.request.contextPath}/curriculum_tokcupdatePage.action?tnum=${user.tnum}&curriculumid=${curriculum.curriculumid }"><small>编辑</small></a>
							</td>
							<td nowrap="nowrap"><a
								href="${pageContext.request.contextPath}/curriculum_tokcdelPage.action?curriculumid=${curriculum.curriculumid }"
								onclick="if (confirm('确定要删除该课程吗？')) return true; else return false;"><small>删除</small></a>
							</td>
							<td nowrap="nowrap"><a
								href="${pageContext.request.contextPath}/curriculum_tokcupdateaddPage.action?tnum=${user.tnum}&curriculumid=${curriculum.curriculumid }"><small>生成新课</small></a>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${pageBean.totalCount == 0 }">
						<tr align="center">
							<th nowrap="nowrap" colspan="14">没有符合您搜索要求的课程</th>
						</tr>
					</c:if>
					<tfoot>
						<tr align="center">
							<th nowrap="nowrap" colspan="14"><c:if
									test="${pageBean.currentpage != 1 }">
									<a
										href="${pageContext.request.contextPath}/curriculum_tokbjPage.action?college.collegeid=${curriculum.college.collegeid}&curriculumid=${curriculum.curriculumid}&curriculumCname=${curriculum.curriculumCname}&tnum=${user.tnum}"><small>首页</small></a>&nbsp;&nbsp;&nbsp;
									<a
										href="${pageContext.request.contextPath}/curriculum_tokbjPage.action?currentpage=${currentpage-1 }&college.collegeid=${curriculum.college.collegeid}&curriculumid=${curriculum.curriculumid}&curriculumCname=${curriculum.curriculumCname}&tnum=${user.tnum}"><small>上一页</small></a>
								</c:if>&nbsp;&nbsp;&nbsp; <small>共${pageBean.totalCount }条记录,共计${pageBean.totalPage }页,当前第${pageBean.currentpage }页</small>&nbsp;&nbsp;&nbsp;
								<c:if test="${pageBean.currentpage != pageBean.totalPage }">
									<a
										href="${pageContext.request.contextPath}/curriculum_tokbjPage.action?currentpage=${currentpage+1 }&college.collegeid=${curriculum.college.collegeid}&curriculumid=${curriculum.curriculumid}&curriculumCname=${curriculum.curriculumCname}&tnum=${user.tnum}"><small>下一页</small></a>&nbsp;&nbsp;&nbsp;
									<a
										href="${pageContext.request.contextPath}/curriculum_tokbjPage.action?currentpage=${pageBean.totalPage }&college.collegeid=${curriculum.college.collegeid}&curriculumid=${curriculum.curriculumid}&curriculumCname=${curriculum.curriculumCname}&tnum=${user.tnum}"><small>尾页</small></a>
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
	<script type="text/javascript">
	
	function selectSubmit()
		{
			var form=document.getElementById("findcurriculum");
			form.action="${pageContext.request.contextPath}/curriculum_tokbjPage.action?tnum=${user.tnum}";
			form.submit();  
		}
	
	
</script>
</body>