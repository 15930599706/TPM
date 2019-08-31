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
<script src="<%=basePath%>images/js/jquery-1.7.2.min.js"
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
	cursor: pointer;
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
			<strong>课程信息维护</strong>
		</div>
		<div style="width: 1000px;">
			<span style="color: red; padding-left: 24px;"><strong>说明：</strong>此处修改的课程信息将联动更新至计划进程表及课程大纲。因此，请谨慎修改课程信息。</span>
		</div>



		<div id="box1">
			<a
				href="${pageContext.request.contextPath}/curriculum_tokbjPage.action?college.collegeid=${curriculum.college.collegeid}&curriculumid=${curriculum.curriculumid}&curriculumCname=${curriculum.curriculumCname}&tnum=${user.tnum}"
				target="MainFrame">编辑课程</a>
		</div>
		<div id="box3">
			<c:if test="${user.adminlevel == 5 }">
				<a id="t1" target="MainFrame"
					hidden="user_importCurriculumExcel.action">批量导入</a>
			</c:if>
		</div>
		<div id="box2">
			<form
				action="${pageContext.request.contextPath}/curriculum_tokcxxPage.action"
				method="post" id="findcurriculum">
				<td>开课部门(学院)：</td>
				<td><select size="1" name="college.collegeid"
					onchange="selectSubmit()">
						<option value="-1">---所有学院---</option>
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
				action="${pageContext.request.contextPath}/curriculum_tokcxxPage.action?college.collegeid=${curriculum.college.collegeid}&curriculumid=${curriculum.curriculumid}&curriculumCname=${curriculum.curriculumCname}"
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
						</tr>
					</c:forEach>
					<c:if test="${pageBean.totalCount == 0 }">
						<tr align="center">
							<th nowrap="nowrap" colspan="11">没有符合您搜索要求的课程</th>
						</tr>
					</c:if>

					<tfoot>
						<tr align="center">
							<th nowrap="nowrap" colspan="11"><c:if
									test="${pageBean.currentpage != 1 }">
									<a
										href="${pageContext.request.contextPath}/curriculum_tokcxxPage.action?college.collegeid=${curriculum.college.collegeid}&curriculumid=${curriculum.curriculumid}&curriculumCname=${curriculum.curriculumCname}"><small>首页</small></a>&nbsp;&nbsp;&nbsp;
									<a
										href="${pageContext.request.contextPath}/curriculum_tokcxxPage.action?currentpage=${currentpage-1 }&college.collegeid=${curriculum.college.collegeid}&curriculumid=${curriculum.curriculumid}&curriculumCname=${curriculum.curriculumCname}"><small>上一页</small></a>
								</c:if>&nbsp;&nbsp;&nbsp; <small>共${pageBean.totalCount }条记录,共计${pageBean.totalPage }页,当前第${pageBean.currentpage }页</small>&nbsp;&nbsp;&nbsp;
								<c:if test="${pageBean.currentpage != pageBean.totalPage }">
									<a
										href="${pageContext.request.contextPath}/curriculum_tokcxxPage.action?currentpage=${currentpage+1 }&college.collegeid=${curriculum.college.collegeid}&curriculumid=${curriculum.curriculumid}&curriculumCname=${curriculum.curriculumCname}"><small>下一页</small></a>&nbsp;&nbsp;&nbsp;
									<a
										href="${pageContext.request.contextPath}/curriculum_tokcxxPage.action?currentpage=${pageBean.totalPage }&college.collegeid=${curriculum.college.collegeid}&curriculumid=${curriculum.curriculumid}&curriculumCname=${curriculum.curriculumCname}"><small>尾页</small></a>
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

			<form name="upfile" action="user_importCurriculumExcel.action"
				enctype="multipart/form-data" method="post">
				<input type="file" name="excelFile" id="excelFile" /> <input
					type="button" id="queryBtn" value="导入">
			</form>


		</div>
	</div>

	<script type="text/javascript">
	$("#t1").click(function(){
			popWin("detail");
	});
	function selectSubmit()
		{
			var form=document.getElementById("findcurriculum");
			form.action="${pageContext.request.contextPath}/curriculum_tokcxxPage.action";
			form.submit();  
		}
	
	$("#queryBtn").click(function(){
            
            var s=document.upfile.excelFile.value; 
            if(s==""){
                alert("请选择需要上传的文件！");
                document.upfile.upload.focus();
                return false;
            }
            document.upfile.submit();
        });
</script>
</body>
</html>
