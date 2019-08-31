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
				"<td width='30%'><input class='classhour' style='width: 40px;'type='number'/></td>" +
				"<td width='30%'><input class='exphour' style='width: 40px;'type='number'/></td>" +
				"<td width='30%'><input class='conhour' style='width: 40px;' type='number'/></td></tr>";
				
				addTr(tab, row, trHtml);
			}

			function delTr2() {
				delTr('ckb');
			}
		</script>
</head>

<body>
	<div class="right_cont">

		<table style="margin-left: 120px;">
			<tr>

				<div class="title_right">
					<strong>课程概况</strong>
				</div>
			</tr>
			<tr>
				<td class="item-name">课程名称：</td>
				<td>${curriculum.curriculumCname }</td>
				<td class="item-name">课程编号：</td>
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
				<td>${curriculum.courseLei }</td>
				<td class="item-name">学时：</td>
				<td>${curriculum.hoursOfALL }</td>
			</tr>
			<tr>
				<td class="item-name">学分：</td>
				<td>${curriculum.credit }</td>

			</tr>
			<tr>
				<td class="item-name">先修课程：</td>
				<td class="input"><input type="text" id="firstcurriculum"
					name="firstcurriculum" value="${BaseTheo.firstcurriculum }" /></td>

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

		</table>
		<table style="margin-left: 120px;">
			<tr>

				<div class="title_right">
					<strong>教学安排</strong>
				</div>
			</tr>

			<tr>
				<td class="item-name">教学安排：</td>
				<td class="input" colspan="3" style="height: 128px;"><textarea
						style="height: 100px; width: 453px;" id="plan" name="plan">${BaseTheo.plan }</textarea></td>
				</td>

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
							<td width="30%" rowspan="2">讲课内容</td>
							<td width="30%" colspan="3">学时（时）</td>
						</tr>
						<tr align="center">
							<td width="30%"><input id="allCkb" type="checkbox" /></td>

							<td width="30%">讲课</td>
							<td width="30%">实验(践)</td>
							<td width="30%">上机</td>

						</tr>
						<c:forEach items="${distributeHour_Theolist}"
							var="distributeHour_Theo" varStatus="xh">
							<tr class="distributeHour" align="center">
								<td width="30%"><input name='ckb' type="checkbox" /></td>
								<td width="30%"><input class="namecourse" type="text"
									style="width: 215px;" value="${distributeHour_Theo.name }" /></td>
								<td width="30%"><input class="classhour" type="number"
									style="width: 40px;"
									value="${distributeHour_Theo.hoursOfClass }" /></td>
								<td width="30%"><input class="exphour" type="number"
									style="width: 40px;" value="${distributeHour_Theo.hoursOfExp }" /></td>
								<td width="30%"><input class="conhour" type="number"
									style="width: 40px;" value="${distributeHour_Theo.hoursOfCom }" /></td>

							</tr>
						</c:forEach>
						<input type="hidden" name="baseTheoid" id="baseTheoid"
							value="${BaseTheo.baseTheoid }" />
					</table>
					<div class="bottom">
						<c:if test="${newtheoreticalLesson.curriculum.courseLei eq '理论课'}">
							<button
								onclick="submit(${newtheoreticalLesson.theoreticalLessonid})">提交</button>
						</c:if>
						<c:if test="${newtheoreticalLesson.curriculum.courseLei eq '实验课'}">
							<button
								onclick="submit(${newtheoreticalLesson.theoreticalLessonid})">提交</button>
						</c:if>
						<c:if test="${newtheoreticalLesson.curriculum.courseLei eq '实践课'}">
							<button
								onclick="submit(${newtheoreticalLesson.practiceLessonid})">提交</button>
						</c:if>
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
	
	function submit(id) {
		var result = getChildValues($(".distributeHour"), {
			namecourse: { selector: ".namecourse", prop: "value" },
			classhour: { selector: ".classhour", prop: "value" },
			exphour: { selector: ".exphour", prop: "value" },
			conhour: { selector: ".conhour", prop: "value" },
		});		
		firstcurriculum = document.getElementById("firstcurriculum").value;
		plan = document.getElementById("plan").value;
		baseTheoid = document.getElementById("baseTheoid").value;
		
	 		var url = "${pageContext.request.contextPath}/baseTheo_updateBaseTheo.action?syllabusId=${syllabusId}&theoreticalLessonId="+id+"&curriculumId=${newtheoreticalLesson.curriculum.curriculumid}";

			var s={firstcurriculum:firstcurriculum,plan:plan,baseTheoid:baseTheoid,data: JSON.stringify(result)};
 	 		$.post(url,s, function(res) {
 			if(res.error) 
 	 		{
 	 			alert("保存失败！");
 	 		}
 	 		else
 	 		{ 
 	 			alert(res.message);
 	 			if('${newtheoreticalLesson.curriculum.courseLei}' == '理论课'||'${newtheoreticalLesson.curriculum.courseLei}' == '实验课')
 					window.location.href = "${pageContext.request.contextPath}/theoreticalPlan_toupdateTheoLesPage.action?tnum=${user.tnum}&syllabusId=${syllabusId}&theoreticalLessonid="+id+"&curriculum.curriculumid=${newtheoreticalLesson.curriculum.curriculumid }&department.departmentid=${newtheoreticalLesson.department.departmentid}";
 				else
 					window.location.href = "${pageContext.request.contextPath}/practicePlan_toHavePracLesOtherPage.action?tnum=${user.tnum}&syllabusId=${syllabusId}&practiceLessonid="+id+"&curriculum.curriculumid=${newtheoreticalLesson.curriculum.curriculumid }&department.departmentid=${newtheoreticalLesson.department.departmentid}"; 
 	 		}			
 			});
 
	}
</script>

</html>