<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
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
.bottom {
	padding-top: 10px;
	padding-left: 350px;
}

table {
	font-family: "微软雅黑";
	font-size: 13px;
	color: #000000;
}

.item-name {
	height: 50px;
	text-align: right;
}

.input {
	height: 50px;
	text-align: left;
}

#box9 a:hover {
	font-family: "microsoft yahei";
	color: #0000c6;
	width: 160px;
	height: 20px;
}

#box9 {
	height: 35px;
	float: left;
	width: 160px;
}

#box9 a {
	display: block;
	height: 20px;
	width: 160px;
	text-decoration: none;
	font-family: "microsoft yahei";
	color: #000;
	background: url(<%=basePath%>images/img/back-16.png) no-repeat 25px 2px;
	text-indent: 40px;
	text-align: center;
}
</style>
<script type="text/javascript">
			function addTr(tab, row, trHtml) {

				var $tr = $("#" + tab + " tr").eq(row);
				if($tr.size() == 0) {
					alert("指定的table id或行数不存在！");
					return;
				}
				$tr.after(trHtml);
			}

			function delTr(ckb) {

				var ckbs = $("input[name=" + ckb + "]:checked");
				if(ckbs.size() == 0) {
					alert("要删除指定行，需选中要删除的行！");
					return;
				}
				ckbs.each(function() {
					$(this).parent().parent().remove();
				});
			}

			function allCheck(allCkb, items) {
				$("#" + allCkb).click(function() {
					$('[name=' + items + ']:checkbox').attr("checked", this.checked);
				});
			}

			$(function() {

				allCheck("allCkb", "ckb");
			});

			function addTr2(tab, row) {
				var trHtml = "<tr class='distributeHour' align='center'>" +
				"<td width='30%'><input type='checkbox' name='ckb'/></td>" +
				"<td width='30%'><input class='namecourse' style='width: 215px;' type='text'/></td>" +
				"<td width='30%'><input class='classhour' style='width: 50px;'type='number'/></td>" +
				"<td width='30%'><input class='exphour' style='width: 50px;'type='number'/></td>" +
				"<td width='30%'><input class='exp' style='width:120px;' type='text'/></td></tr>";
			
					addTr(tab, row, trHtml);
			}

			function delTr2() {
				delTr('ckb');
			}
		</script>
</head>


<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>课程概况</strong>
		</div>
		<div id="box9">
			<a
				href="${pageContext.request.contextPath}/theoreticalPlan_toupdateTheoLesPageInnerExperiment.action?syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.theoreticalLessonid}">返回录入其他内容</a>
		</div>
		<table style="margin-left: 130px; margin-top: 55px;">

			<tr>
				<td class="item-name">课程名称：</td>
				<td>${curriculum.curriculumCname }</td>
				<td class="item-name">课程编码：</td>
				<td>${curriculum.curriculumid }</td>
			</tr>
			<tr>
				<td class="item-name">英文名称：</td>
				<td>${curriculum.curriculumEname }</td>
				<td class="item-name">课程性质：</td>
				<td>${curriculum.natureOfCourse.natureOfCoursename }</td>
			</tr>
			<tr>
				<td class="item-name">课程类别：</td>
				<td>${curriculum.natureOfCourse.natureOfCoursename }</td>
				<td class="item-name">学时：</td>
				<td>${curriculum.hoursOfALL }</td>
			</tr>
			<tr>
				<td class="item-name">学分：</td>
				<td>${curriculum.credit }</td>

			</tr>
			<tr>
				<td class="item-name">适用专业：</td>
				<c:if test="${flage == 1}">
					<td class="input"><c:forEach items="${applicationSyllabus}"
							var="applicationSyllabus">
						${applicationSyllabus.professional.professionalname} </br>
						</c:forEach></td>
				</c:if>

				<c:if test="${flage == -1}">
					<td class="input"><input type="text" name="fordepartment"
						disabled
						value="${applicationSyllabus.department.departmentCname }" /></td>
				</c:if>

			</tr>
			<input type="hidden" name="baseExperimentid" id="baseExperimentid"
				value="${baseExperiment.baseExperimentid}" />
		</table>
		<table style="margin-left: 130px;">
			<tr>

				<div class="title_right">
					<strong>进度安排</strong>
				</div>
			</tr>
			<tr>

				<td class="item-name">教学安排：</td>
				<td><textarea name="schedule" id="schedule"
						style="width: 450px; height: 100px;">${BaseExperiment_TheoInnerExperiment.schedule}</textarea></td>
			</tr>
			<tr>
				<td class="item-name">学时分配：</td>
				<td class="input" colspan="3" style="height: 128px;">
					<table border="1px #ooo" id="tab" cellpadding="0" cellspacing="0"
						width="30%">
						<tr align="center">
							<td width="30%"><input type="button"
								onclick="addTr2('tab', -1)" value="添加"> <input
								type="button" onclick="delTr2()" value="删除"></td>
							<td width="30%" rowspan="2">项目名称</td>
							<td width="30%" colspan="2">学时</td>
							<td width="30%" rowspan="2">实验类型</td>
						</tr>
						<tr align="center">
							<td width="30%"><input id="allCkb" type="checkbox" /></td>
							<td width="30%">实验</td>
							<td width="30%">上机</td>
						</tr>
						<c:forEach items="${distributeHour_TheoInnerExperimentlist}"
							var="distributeHour_TheoInnerExperiment">
							<tr class="distributeHour" align="center">
								<td width="30%"><input name='ckb' type="checkbox" /></td>
								<td width="30%"><input class="namecourse" type="text"
									style="width: 215px;"
									value="${distributeHour_TheoInnerExperiment.name }" /></td>
								<td width="30%"><input class="classhour" type="number"
									style="width: 50px;"
									value="${distributeHour_TheoInnerExperiment.hoursOfClass }" /></td>
								<td width="30%"><input class="exphour" type="number"
									style="width: 50px;"
									value="${distributeHour_TheoInnerExperiment.hoursOfExp }" /></td>
								<td width="30%"><input class="exp" type="text"
									style="width: 120px;"
									value="${distributeHour_TheoInnerExperiment.exp }" /></td>
								<td width="30%"><input class="disid" type="hidden"
									style="width: 120px;"
									value="${distributeHour_TheoInnerExperiment.distributeHour_TheoInnerExperimentid }" /></td>
							</tr>
						</c:forEach>

						<input type="hidden" name="baseExperiment_TheoInnerExperimentid"
							id="baseExperiment_TheoInnerExperimentid"
							value="${BaseExperiment_TheoInnerExperiment.baseExperiment_TheoInnerExperimentid}" />
					</table>
				</td>
			</tr>
		</table>

		<table style="margin-left: 130px;">
			<tr>

				<div class="title_right">
					<strong>成绩考核与评定</strong>
				</div>
			</tr>
			<tr>
				<td><textarea name="assess" id="assess"
						style="width: 460px; height: 150px;">${BaseExperiment_TheoInnerExperiment.assess}</textarea></td>
				</td>

			</tr>
		</table>
		<div class="bottom">

			<button onclick="submit()">提交</button>
		</div>
	</div>


</body>
<script type="text/javascript">
	// 得到元素数组的子元素属性
    function getChildValues($parents, selectors) {
    	var datas = [];
    	for(var key in selectors) {
    		selectors[key].getValue = selectors[key].getValue || function() { return this[selectors[key].prop]; };
    	}
    	$parents.each(function() {
    		var data = {};
    		for(var key in selectors) {
    			var item = $(this).find(selectors[key].selector)[0];
    			var value = selectors[key].getValue.call(item);
    			data[key] = value;
    		}
    		datas.push(data);
    	});
    	return datas;
    }
	function submit() {
		var result = getChildValues($(".distributeHour"), {
			namecourse: { selector: ".namecourse", prop: "value" },
			classhour: { selector: ".classhour", prop: "value" },
			exphour: { selector: ".exphour", prop: "value" },
			conhour: { selector: ".exp", prop: "value" },
			id:{selector:".disid",prop:"value"}
		});		
		console.info(result);
		schedule = document.getElementById("schedule").value;
		assess = document.getElementById("assess").value;
		baseExperiment_TheoInnerExperimentid = document.getElementById("baseExperiment_TheoInnerExperimentid").value;
 		var url = '${pageContext.request.contextPath}/BaseExperimentTheoInnerExperimentAction_updateBaseTheoExperiment.action?syllabusId=${syllabusId}&theoreticalLessonId=${newtheoreticalLesson.theoreticalLessonid}';
		var s={schedule:schedule,assess:assess,baseExperiment_TheoInnerExperimentid:baseExperiment_TheoInnerExperimentid,data: JSON.stringify(result)};
 		$.post(url,s, function(res) {
			if(res.error) 
	 		{
	 			alert("保存失败！");
	 		}
	 		else
	 		{ 
	 			alert(res.message);
				window.location.href = "${pageContext.request.contextPath}/theoreticalPlan_toupdateTheoLesPageInnerExperiment.action?tnum=${user.tnum}&syllabusId=${syllabusId}&theoreticalLessonid=${newtheoreticalLesson.theoreticalLessonid }&curriculum.curriculumid=${newtheoreticalLesson.curriculum.curriculumid }&department.departmentid=${newtheoreticalLesson.department.departmentid}";
	 		}
		}); 
	}
</script>
</html>