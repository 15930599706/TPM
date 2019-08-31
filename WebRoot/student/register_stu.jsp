<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<title>注册页面</title>
<link rel="stylesheet" href="/TPM/images/css/register.css">
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="/TPM/images/js/register.js"></script>
</head>
<body>

	<div class="nav"
		style="width: 100%; height: 90px; background: url(../images/img/bgg.png); border-bottom: 1px solid #ccc">
		<div
			style="width: 272px; height: 80px; margin-top: 10px; background: url(../images/img/ysulogo.png); float: left; margin-left: 5%;"></div>
		<div
			style="width: 300px; height: 80px; margin-top: 10px; background: url(../images/img/logo.png); float: left;"></div>
		<div></div>
		<ul class="navUl">
			<li><a class="za" href="denglu.jsp"> <span>登录</span>
			</a></li>

			<li><a class="student" href="register_stu.jsp"
				style="background-color: #fa911e; color: #fff; border: 3px solid #fa911e;">
					<span>学生用户注册</span>
			</a></li>
			<li><a class="company" href="register_comp.jsp"> <span>企业用户注册</span>
			</a></li>
		</ul>
	</div>
	<span id="leftBlue"
		style="width: 33%; height: 20%; background: url(../../images/img/bg1.gif); padding-right: 0; float: left; margin-top: 26%;"></span>
	<span id="rightBlue"
		style="width: 33%; height: 20%; background: url(../../images/img/bg1.gif); margin-left: 0; float: right; margin-top: 26%;"></span>
	<div class="registerBox">

		<div class="form" style="margin-bottom: 50px;">

			<form action="${pageContext.request.contextPath}/user.do"
				method="get">
				<h2>学生用户注册</h2>
				<ul>

					<li>用户名: <input class="inp" type="text" id="phoneNum"
						name="phoneNum" placeholder="请输入用户名(手机号)"
						onkeyup="checkUserName()" onblur="ajxTest()" required /> <span
						id="_tishi"></span>
					</li>
					<li>用户密码： <input class="inp" type="password" name="pwd"
						id="pwd" placeholder="请输入密码" onblur="judgepwd()" required /><span
						id="__tishi"></span>
					</li>
					<li>确认密码： <input class="inp" type="password" name="_pwd"
						id="_pwd" placeholder="确认密码" onkeyup="judge_pwd()"
						onfocus="onfFuc()" required /><span id="tishi"></span>

					</li>
					<li>姓名： <input class="inp" id="Name" name="Name"
						placeholder="请输入你的姓名" required />
					</li>
					<li>性别： <input type="radio" name="Sex" value="male" checked />
						男 <input type="radio" name="Sex" value="female" />女</br>
					</li>
					<li>入学年份： <input type="date" name="Brithday"
						value="2000-01-01" />
					</li>
					<li>专业： <!-- <input class="inp" type="text" name="Major" placeholder="请输入你的专业"/> -->
						<select class="inp" name="Major" id="Major">
							<option value="0101">机械设计制造及其自动化</option>
							<option value="0102">材料成型及控制工程</option>
							<option value="0104">机械设计制造及其自动化(卓越试点)</option>
							<option value="0201">金属材料工程</option>
							<option value="0202">无机非金属材料工程</option>
							<option value="0203">材料物理</option>
							<option value="0204">高分子材料与工程</option>
							<option value="0301">自动化</option>
							<option value="0302">测控技术与仪器</option>
							<option value="0303">电气工程及其自动化</option>
							<option value="0304">生物医学工程</option>
							<option value="0305">自动化(卓越试点)</option>
							<option value="0401">计算机科学与技术</option>
							<option value="0402">电子信息工程</option>
							<option value="0403">通信工程</option>
							<option value="0404">电子科学与技术</option>
							<option value="0409">电子信息工程(卓越试点)</option>
							<option value="0410">光电信息科学与工程</option>
							<option value="0411">计算机教学实验中心</option>
							<option value="0501">工商管理</option>
							<option value="0502">会计学</option>
							<option value="0503">国际经济与贸易</option>
							<option value="0504">旅游管理</option>
							<option value="0506">工业工程</option>
							<option value="0507">电子商务</option>
							<option value="0508">经济学</option>
							<option value="0509">工业工程（中外合作培养）</option>
							<option value="0601">英语</option>
							<option value="0602">日语</option>
							<option value="0603">俄语</option>
							<option value="0604">德语</option>
							<option value="0605">法语</option>
							<option value="0606">翻译</option>
							<option value="0607">大学英语一系</option>
							<option value="0608">大学英语二系</option>
							<option value="0609">综合英语系</option>
							<option value="0701">土木工程</option>
							<option value="0702">工程力学</option>
							<option value="0704">建筑学</option>
							<option value="0705">建筑环境与能源应用工程</option>
							<option value="0801">信息与计算科学</option>
							<option value="0802">统计学</option>
							<option value="0803">应用物理学</option>
							<option value="0804">电子信息科学与技术</option>
							<option value="0901">法学</option>
							<option value="0902">政治学与行政学</option>
							<option value="0903">汉语言文学</option>
							<option value="0904">国际政治</option>
							<option value="0907">行政管理</option>
							<option value="0908">广播电视学</option>
							<option value="0909">公共事业管理</option>
							<option value="1001">化学工程与工艺</option>
							<option value="1002">应用化学</option>
							<option value="1003">环境工程</option>
							<option value="1004">过程装备与控制工程</option>
							<option value="1005">生物工程</option>
							<option value="1006">生物工程(卓越试点)</option>
							<option value="1007">能源化学工程</option>
							<option value="1104">音乐表演</option>
							<option value="1105">雕塑</option>
							<option value="1106">工业设计</option>
							<option value="1107">产品设计</option>
							<option value="1108">视觉传达设计</option>
							<option value="1109">环境设计</option>
							<option value="1110">公共艺术</option>
							<option value="1111">舞蹈表演</option>
							<option value="1201">社会体育</option>
							<option value="1202">社会体育指导与管理</option>
							<option value="1303">车辆工程</option>
							<option value="1304">石油工程</option>
							<option value="1305">车辆工程(卓越试点)</option>
							<option value="1306">能源与动力工程</option>
							<option value="2001">软件工程</option>

					</select>
					</li>
					<li>学号： <input class="inp" type="text" name="StuNum"
						placeholder="请输入你的学号" />
					</li>
					<li>学生状态： <input type="radio" name="Status" value="0" checked />在校
						<input type="radio" name="Status" value="1" />工作</br>
					</li>
					<li>所在企业<span style="color: red;">*</span>： <input
						class="inpx" type="text" name="Company"
						placeholder="请输入你所在的企业(在校生可以不填)" />
					</li>
				</ul>

				<input type="button" class="regiest" id="submit" value="注册" /> <br />

			</form>
		</div>
	</div>
	<!--<div class="reg">
					<span>已有账号？</span>
					<a href="login.html">登录</a>
					</div>
					-->
	<script type="text/javascript">
					$("#submit").click(function(){
							if($("#Name").val()=="" || $("input[name='Major']").val()=="" || $("input[name='StuNum']").val()==""){
								alert("输入信息不全！");
							}
							else{
								$.ajax({
																		type:"POST",
																		url: '/TPM/StuLoginServlet',
																		data:{
																			"status":1,
																			"phoneNum":$("#phoneNum").val(),
																				 "pwd":$("#pwd").val(),
																				 "Name":$("#Name").val(),
																				 "Sex": $("input[name='Sex']:checked").val(),
																					"grade":$("input[type='date']").val(),
																					"Major":$("#Major").val(),
																					"StuNum":$("input[name='StuNum']").val(),
																					"Status":$("input[name='Status']:checked").val(),
																					"Company":$("input[name='Company']").val()
																			 },
																		dataType:'json',
																		cache:false,
																		async:true,
																		success: function(data) {
																			if(data[0].status==0){
																					window.location.href="/TPM/admin/regiester(dyh)&login（gh/html/denglu.html";
																							alert("注册成功");
																				}
																				else{
																					alert("error！")
																				}
																		},
																		error: function() {
																				alert("error！")
																		}
																});
							}
	})
					</script>
</body>
</html>
