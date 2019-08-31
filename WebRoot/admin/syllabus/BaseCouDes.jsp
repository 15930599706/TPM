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

<link rel="stylesheet" href="../editor/themes/default/default.css" />
<link rel="stylesheet" href="../editor/plugins/code/prettify.css" />
<script charset="utf-8" src="../editor/kindeditor.js"></script>
<script charset="utf-8" src="../editor/lang/zh_CN.js"></script>
<script charset="utf-8" src="../editor/plugins/code/prettify.js"></script>


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
				"<td width='30%'><input class='time' style='width: 150px;'type='text'/></td>" ;
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
				<td>${curriculum.courseLei }</td>
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

		</table>
		<table style="margin-left: 130px;">
			<tr>

				<div class="title_right">
					<strong>课程设计（论文）进度安排</strong>
				</div>
			</tr>
			<tr>

				<td class="item-name">教学安排：</td>
				<td><textarea name="schedule" id="schedule"
						style="width: 450px; height: 100px;">${baseCourseDesign.schedule}</textarea></td>
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
							<td width="30%" rowspan="2">工作内容</td>
							<td width="30%" rowspan="2">时间</td>
						</tr>
						<tr align="center">
							<td width="30%"><input id="allCkb" type="checkbox" /></td>
						</tr>
						<c:forEach items="${distributeHour_CourseDesignlist}"
							var="distributeHour_CourseDesign">
							<tr class="distributeHour" align="center">
								<td width="30%"><input name='ckb' type="checkbox" /></td>

								<td width="30%"><input class="namecourse" type="text"
									style="width: 215px;"
									value="${distributeHour_CourseDesign.name }" /></td>
								<td width="30%"><input class="time" type="text"
									style="width: 150px;"
									value="${distributeHour_CourseDesign.time }" /></td>


							</tr>
						</c:forEach>
						<input type="hidden" name="baseCourseDesignid"
							id="baseCourseDesignid"
							value="${baseCourseDesign.baseCourseDesignid}" />
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
			time: { selector: ".time", prop: "value" },

		});		
		schedule = document.getElementById("schedule").value;
		baseCourseDesignid = document.getElementById("baseCourseDesignid").value;
		//console.info(result);
		//return;
 		var url = '${pageContext.request.contextPath}/BaseCourseDesign_updateBaseCourseDesign.action?syllabusId=${syllabusId}&practiceLessonid=${newpracticeLesson.practiceLessonid}';
		var s={schedule:schedule,baseCourseDesignid:baseCourseDesignid,data: JSON.stringify(result)};
 		$.post(url,s, function(res) {
		if(res.error) 
 		{
 			alert("保存失败！");
 		}
 		else
 		{ 
 			alert(res.message);
			window.location.href = "${pageContext.request.contextPath}/practicePlan_toHavePracLesCourseDesignPage.action?tnum=${user.tnum}&syllabusId=${syllabusId}&practiceLessonid=${newpracticeLesson.practiceLessonid}&curriculum.curriculumid=${newpracticeLesson.curriculum.curriculumid }&department.departmentid=${newpracticeLesson.department.departmentid}";
 		}
		}); 
	}
</script>
</html>