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
<link rel="stylesheet" href="<%=basePath%>images/css/bootstrap.css" />
<link rel="stylesheet"
	href="<%=basePath%>images/css/bootstrap-multiselect.css"
	type="text/css">
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
<script type="text/javascript"
	src="<%=basePath%>images/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>images/css/prettify.js"></script>
<script type="text/javascript"
	src="<%=basePath%>images/js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="<%=basePath %>/images/js/animate.js"></script>
<script type="text/javascript"
	src="<%=basePath %>/images/js/loadMask.js"></script>
<style type="text/css">
.bottom {
	padding-top: 10px;
	padding-left: 500px;
}

#box5 {
	font-family: "microsoft yahei";
	height: 50px;
	padding-left: 25px;
	padding-top: 25px;
}

#box2 {
	font-family: "microsoft yahei";
	height: 50px;
	float: left;
	width: 1000px;
	padding-left: 24px;
}

#sl {
	width: 150px;
}

#s2 {
	width: 100px;
}

#lb #xq {
	width: 100px;
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
</style>
</head>
<script type="text/javascript">
		showMask({ info: "正在加载，请稍后……" });
		window.onload = function() {
			hiddenMask();
		}
	</script>
<script language="javascript">
		function selectSubmit()
		{
			var form=document.getElementById("tjsjk");
			form.action="${pageContext.request.contextPath}/practicePlan_tjsjkResult.action?collegeid=${college.collegeid}&departmentid=${department.departmentid}";
			form.submit();  			
		}
	</script>

<body>
	<div class="right_cont">
		<div class="title_right">
			<strong>添加集中实践课</strong>
		</div>
		<form method="post" id="tjsjk" onsubmit="selectSubmit()">
			<input type="hidden" name="tnum" value="${user.tnum}"></input> <input
				type="hidden" name="department.departmentid"
				value="${department.departmentid}"></input> <input type="hidden"
				name="department.college.collegeid" value="${college.collegeid}"></input>

			<div id="box2">

				<td>开课学院：</td>
				<td><select size="1" id="xueyuan"
					name="curriculum.college.collegeid" onChange="selectSubmit()">
						<!-- <option  value="-1" selected="selected">全部</option> -->
						<c:forEach items="${collegelist}" var="college">
							<option value="${college.collegeid }"
								<c:if test="${(user.adminlevel != 5) && (college.collegeid eq user.college.collegeid) && (first eq 'yes')}">selected="selected"</c:if>
								<c:if test="${college.collegeid eq xueyuan }">selected="selected"</c:if>>${college.collegeCname}</option>
						</c:forEach>
				</select></td>

				<td>课程平台：</td>
				<td><select size="1" id="curriculum.curriculumpingtai"
					name="curriculum.curriculumpingtai" onChange="selectSubmit()"
					style="width: 100px;">
						<option value="">【全部】</option>
						<option value="公共教育平台"
							<c:if test="${pingtai == '公共教育平台' }">selected="selected"</c:if>>公共教育平台</option>
						<option value="专业教育平台"
							<c:if test="${pingtai == '专业教育平台' }">selected="selected"</c:if>>专业教育平台</option>

				</select></td>
				<td>课程性质：</td>
				<td><select size="1"
					name="curriculum.natureOfCourse.natureOfCourseid"
					onChange="selectSubmit()" style="width: 100px;">
						<option value="">【全部】</option>
						<option value="06"
							<c:if test="${xingzhi == '06' }">selected="selected"</c:if>>公共必选课</option>
						<option value="09"
							<c:if test="${xingzhi == '09' }">selected="selected"</c:if>>公共选修课</option>
						<option value="10"
							<c:if test="${xingzhi == '10' }">selected="selected"</c:if>>专业必选课</option>
						<option value="12"
							<c:if test="${xingzhi == '12' }">selected="selected"</c:if>>专业选修课</option>
				</select></td>

				<td>课程类别：</td>
				<td><select size="1" name="curriculum.courseLei"
					onChange="selectSubmit()" style="width: 90px;">
						<option value="实践课"
							<c:if test="${leibie == '实践课' }">selected="selected"</c:if>>实践课</option>
				</select></td>
				<td>课程归属：</td>
				<td><select size="1" name="curriculum.courseCategory"
					onChange="selectSubmit()" style="width: 150px;">
						<option value="">【全部】</option>
						<option value="社会科学类"
							<c:if test="${guishu == '社会科学类' }">selected="selected"</c:if>>社会科学类</option>
						<option value="文化与艺术类"
							<c:if test="${guishu == '文化与艺术类' }">selected="selected"</c:if>>文化与艺术类</option>
						<option value="数学与逻辑类"
							<c:if test="${guishu == '数学与逻辑类' }">selected="selected"</c:if>>数学与逻辑类</option>
						<option value="科学与技术类"
							<c:if test="${guishu == '科学与技术类' }">selected="selected"</c:if>>科学与技术类</option>
						<option value="创新创业与发展类"
							<c:if test="${guishu == '创新创业与发展类' }">selected="selected"</c:if>>创新创业与发展类</option>
				</select></td>
			</div>
			<div id="box2">
				<td width="40%" align="right" nowrap="nowrap">课程代码：</td>
				<td><input size="1" name="curriculum.curriculumid"
					class="span3" value="${daima }" /></td>
				<td width="40%" align="right" nowrap="nowrap">课程名称：</td>
				<td><input size="1" name="curriculum.curriculumCname"
					class="span3" value="${mingcheng }" /></td>
				<tr>
					<td class="text-center" colspan="0"><input type="submit"
						value="检索" class="btn btn-info  "
						style="width: 50px; height: 28px;" /></td>
			</div>
		</form>

		<div style="width: 90%; margin-left: 24px;">
			<form method="post" name="form3">
				<table class="table table-bordered table-striped table-hover">
					<thead>
						<tr align="center">
							<th><input type="checkbox" onclick="swapCheck(this)" /></th>
							<th nowrap="nowrap"><small>课程代码</small></th>
							<th nowrap="nowrap"><small>课程名称</small></th>
							<th nowrap="nowrap"><small>开课部门</small></th>
							<th nowrap="nowrap"><small>课程平台</small></th>
							<th nowrap="nowrap"><small>课程性质</small></th>
							<th nowrap="nowrap"><small>课程类别</small></th>
							<th nowrap="nowrap"><small>课程归属</small></th>
							<th nowrap="nowrap"><small>学分</small></th>
							<th nowrap="nowrap"><small>课外实验学时</small></th>
							<th nowrap="nowrap"><small>课外上机学时</small></th>
							<th nowrap="nowrap"><small>开课学期</small></th>
							<th nowrap="nowrap"><small>起止周</small></th>
							<th width="80" nowrap="nowrap"><small>组织形式</small></th>
							<th width="80" nowrap="nowrap"><small>是否学位课</small></th>
							<th width="90" nowrap="nowrap"><small>是否集中实践</small></th>
							<th width="80" nowrap="nowrap"><small>专业方向</small></th>
							<th width="80" nowrap="nowrap"><small>备注</small></th>

						</tr>
					</thead>
					<tbody>

						<c:forEach items="${curriculumList }" var="curriculum"
							varStatus="xh">
							<tr align="center">
								<td><input class="item_checkbox" type="checkbox" /></td>
								<td nowrap="nowrap"><small class="curriculumid">${curriculum.curriculumid }</small></td>
								<td nowrap="nowrap" style="width: 6em;"><small
									class="curriculumCname">${curriculum.curriculumCname }</small></td>
								<td nowrap="nowrap"><small>${curriculum.college.collegeid }</small></td>
								<td nowrap="nowrap"><small>${curriculum.curriculumpingtai }</small></td>
								<td nowrap="nowrap"><small>${curriculum.natureOfCourse.natureOfCoursename }</small></td>
								<td nowrap="nowrap"><small>${curriculum.courseLei }</small></td>
								<td nowrap="nowrap" style="width: 9em;"><small>${curriculum.courseCategory }</small></td>
								<td nowrap="nowrap"><small>${curriculum.credit }</small></td>

								<td nowrap="nowrap"><input type="text" style="width: 60px;"
									class="kewaishiyan" /></td>

								<td nowrap="nowrap"><input type="text" style="width: 60px;"
									class="kewaishangji" /></td>

								<td nowrap="nowrap"><select class="xueqi"
									style="width: 60px;">
										<option>1</option>
										<option>2</option>
										<option>3</option>
										<option>4</option>
										<option>5</option>
										<option>6</option>
										<option>7</option>
										<option>8</option>
										<option>7、8</option>
										<option>9</option>
										<option>10</option>
										<option>9、10</option>
								</select></td>

								<td nowrap="nowrap"><input type="text" style="width: 60px;"
									class="qizhizhou" value="1~17" /></td>

								<td nowrap="nowrap"><select class="zuzhixingshi" size="1"
									style="width: 130px;">
										<option>实习</option>
										<option>课程设计（学年论文）</option>
										<option>实验</option>
										<option>毕业设计</option>
										<option>其他</option>
								</select></td>

								<td nowrap="nowrap"><select class="isxueweike"
									style="width: 50px;">
										<option value="是">是</option>
										<option value="否" selected>否</option>
								</select></td>

								<td nowrap="nowrap"><select class="jizhongshijian"
									style="width: 50px;">
										<option value="是">是</option>
										<option value="否">否</option>
								</select></td>

								<c:if test="${professionalListCount > 0}">
									<td nowrap="nowrap"><select size="1"
										class="example-selectAllNumber" style="width: 120px;"
										multiple="multiple"
										<c:if test="${curriculum.curriculumpingtai == '公共教育平台'}">disabled</c:if>>
											<c:forEach items="${unselectedProfessionalList[xh.count-1]}"
												var="professional">
												<option class="professional_item"
													value="${professional.professionalid }" selected="selected">${professional.professionalname}</option>
											</c:forEach>
									</select></td>
								</c:if>

								<c:if test="${professionalListCount == 0}">
									<td nowrap="nowrap"><select size="1" style="width: 120px;">
											<option selected="selected">不区分方向</option>
									</select></td>
								</c:if>

								<td nowrap="nowrap"><textarea class="beizhu"></textarea></td>

							</tr>
						</c:forEach>
						<c:if test="${curriculumListCount == 0}">
							<tr align="center">
								<th nowrap="nowrap" colspan="20"><small>没有查询到相应课程！</small>
								</th>
							</tr>
						</c:if>

					</tbody>
				</table>
				<c:if test="${curriculumListCount > 0}">
					<div class="bottom">
						<button type="button" value="保存" onClick="a()">保存到培养计划</button>
					</div>
				</c:if>


			</form>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery.min.js"></script>

	<script type="text/javascript">  
       // checkbox 全选/取消全选  
        function swapCheck(control) {
        	$("input[type='checkbox']").each(function() {
            	this.checked = control.checked;
            });
        } 
        
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
        
        function a() {
       		var nullArr = [];
        	var noProfessional = ${professionalListCount == 0};
	        var $parents = $("tbody .item_checkbox[type=checkbox]:checked").parent().parent();
	        var result = getChildValues($parents, {
	        	curriculumid: { selector: ".curriculumid", prop: "innerText",getValue: function() {
	        	return this.innerText;
	        	} },
	        	kewaishiyan: { selector: ".kewaishiyan", prop: "innerText" },
	        	kewaishangji: { selector: ".kewaishangji", prop: "innerText" },
	        	xueqi: { selector: ".xueqi", prop: "value" },
	        	qizhizhou: { selector: ".qizhizhou", prop: "innerText" },
	        	zuzhixingshi: { selector: ".zuzhixingshi", prop: "value" },
	        	isxueweike: { selector: ".isxueweike", prop: "value" },
	        	jizhongshijian: { selector: ".jizhongshijian", prop: "value" },
	        	zhuanyefangxiang: { 
		        	selector: ".example-selectAllNumber", 
		        	getValue: noProfessional ? function() {
		        		return -1;
		        	} : function() {
		        		var selected = [];
		        		var selectedItem = $(this).find(".professional_item:selected");
		        		if(selectedItem.length == 0) {
		        			var tr = $(this).parents("tr");
		        			nullArr.push({
		        				classCode: tr.find(".curriculumid").text(),
		        				className: tr.find(".curriculumCname").text()
		        			});
		        		}
		        		selectedItem.each(function() {
		        			selected.push(this.value);
		        		});
		        		return selected;
		        	}
	        	},
	        	beizhu: { selector: ".beizhu", prop: "value" } 
	        });	   
	        if(nullArr.length > 0) {
				var str = nullArr.map(function(ele) {
					return "\"" + ele.classCode +"--"+ ele.className + "\"未选择专业方向！";
				}).join("\n");
				alert(str);
				return;
			}    
			showMask({ info: "正在添加到培养计划，请稍后……" });
	        var url = "${pageContext.request.contextPath}/practicePlan_sjkAddResult.action?departmentid=${department.departmentid}"; //?tnum=${user.tnum}&data="+ encodeURI(encodeURI(JSON.stringify(result)));
			$.post(url, {
	//			tnum: ${user.tnum},
				data: JSON.stringify(result)
			}, function(response) {
				// 如果成功执行
			//	if(response.err) {
			//		alert("失败:" + response.message);
			//		windows.href = "action" + response.param;
			//	}else{
					hiddenMask();
					alert("添加成功，点击确定查看");
					window.location.href = "${pageContext.request.contextPath}/practicePlan_tojzsjkPage.action?tnum=${user.tnum}&collegeid=${college.collegeid}&departmentid=${department.departmentid}";
			//	}
			});
        }
        $(document).ready(function() {
			$('.example-selectAllNumber').multiselect({
				includeSelectAllOption: true,
				selectAllNumber: false
			});
		});   
    </script>

</body>
</html>