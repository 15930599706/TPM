<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.tpm.entity.Question"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<script type="text/javascript" src="/TPM/images/js/inputQust.js"></script>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<%-- <script type="text/javascript" src="/TPM/images/js/kindeditor-min.js"></script> --%>
<script type="text/javascript"
	src="/TPM/images/js/showAndDoQustionnair.js"></script>
<link rel="stylesheet" type="text/css"
	href="/TPM/images/css/inputQust.css">
<!--     <link rel="stylesheet" type="text/css" href="/TPM/images/css/default/default.css"> -->
<title>学生答题界面</title>
<script type="text/javascript">

		</script>
<style>
.creatpaper_topbar {
	border: 1px solid blue;
	height: 64px;
	width: 100%;
	background-color: #0078c8;
}

.creatpaper_topbar .creatpaper_topbar_title {
	float: left;
	color: white;
	line-height: 64px;
	margin-left: 60px;
}

.centerwapper_for_position {
	position: absolute;
	width: 100%;
	height: 100%;
}

.centerwapper_creatpaper {
	position: relative;
	left: 270px;
}

.centerwapper {
	margin-top: 20px;
	margin-bottom: 20px;
}

.publicwap_showQus {
	margin-top: 15px;
	margin-bottom: 15px;
}

.quetype_showQus {
	height: 25px;
}

.quetype_showQus span {
	float: left;
	margin-left: 10px;
}

.title_showQus {
	height: 30px;
	line-height: 30px;
	font-size: 20px;
	font-weight: bold;
	color: rgb(68, 68, 68);
	margin-top: 5px;
	margin-bottom: 5px;
}

.title_showQus span {
	float: left;
}

.title_showQus span:nth-child(2) {
	margin-left: 20px;
}

.chosewap_showQus:hover {
	cursor: pointer;
	background-color: rgb(239, 239, 239);
}

.eachchose_showQus {
	display: inline-block;
	height: auto;
	margin-top: 5px;
	margin-bottom: 5px;
}

.eachchose_showQus input {
	float: left;
	margin-left: 65px;
	width: 20px;
	height: 20px;
}

.eachchose_showQus :nth-child(2) {
	float: left;
	font-size: 18px;
	height: auto;
	width: 500px;
	line-height: 25px;
	text-align: left;
}

.eachchose_showQus :nth-child(3) {
	width: 100px;
	float: right;
	margin-right: 10px;
	text-align: left;
}

form {
	width: 400px !important;
	margin-left: 100 !important;
	margin-top: 15px !important;
}

.ke-inline-block {
	margin: 10px, auto;
}

.ke-button-common input[type='button'] {
	float: right;
	height: 25px;
	line-height: 18px !important;
	padding: 3px 18px;
	display: inline-block;
	vertical-align: middle;
	font-weight: normal;
	border-radius: 2px;
	margin: 0 8px 0 3px;
	border: 1px solid #3383da;
}

.ke-button-common input[type='button']:hover {
	color: #ffffff;
	background-color: #3383da;
}

.ke-upload-file {
	vertical-align: middle;
	font-weight: normal;
	border-radius: 2px;
	margin: 0 8px 0 3px;
	border: 1px solid #3383da;
}

/* 该页面私有css */
.stuSubmitQustionnair input[type='button'] {
	width: 100px;
	height: 30px;
	text-align: center;
	padding: 3px 18px;
	display: inline-block;
	vertical-align: middle;
	font-weight: normal;
	border-radius: 2px;
	margin: 0 8px 0 3px;
	border: 1px solid #3383da;
}

.stuSubmitQustionnair input[type='button']:hover {
	color: #ffffff;
	background-color: #3383da;
}

.hidefordata {
	display: inline-block;
	height: 1px;
	overflow: hidden;
}

.eachchose_showQus_checkbox {
	display: inline-block;
	height: auto;
	margin-top: 5px;
	margin-bottom: 5px;
}

.eachchose_showQus_checkbox input {
	float: left;
	margin-left: 65px;
	width: 20px;
	height: 20px;
}

.eachchose_showQus_checkbox :nth-child(2) {
	float: left;
	font-size: 18px;
	height: auto;
	width: 500px;
	line-height: 25px;
	text-align: left;
}

.eachchose_showQus_checkbox :nth-child(3) {
	width: 100px;
	float: right;
	margin-right: 10px;
	text-align: left;
}

#submitTip {
	width: 100px;
	height: 30px;
	text-align: center;
	display: inline-block;
	color: red;
}

/* 2-25 */
.choseFileButton {
	border: solid 1px #0078c8;
}

.uploadButton {
	border: solid 1px #0078c8;
	width: 55px;
	height: 24px;
	margin-left: 20px;
}

.uploadButton:hover {
	background-color: #0078c8;
}
</style>
</head>
<body>
	<div class="main">
		<div class="creatpaper_topbar">
			<h2 id="setQusInfo_html" class="creatpaper_topbar_title">学生答题界面</h2>
		</div>
		<p id="questionnairTitle"
			style="display: block; margin-top: 20px;; margin-left: auto; border: 1px, solid, blue; width: 100%; height: 40px; font-size: 30px; text-align: center; line-height: 40px;">问卷标题</p>

		<div class="centerwapper_for_position">
			<div class="centerwapper_creatpaper">
				<%-- 题号 --%>
				<c:set var="que_num_i" value="0" />

				<span id="QnaireID" class="hidefordata">${QnaireID}</span>
				<c:forEach var="quetionList" items="${quetionList}">
					<c:set var="que_num_i" value="${que_num_i+1}" />
					<div class="centerwapper">
						<div class="publicwap_showQus" style="height: auto;">
							<div class="quetype_showQus">
								<span> <%-- 题号${quetionList.tihao} &nbsp --%>
									【${quetionList.leixing}】
								</span>
								<%-- <c:if test="${quetionList.shifoubida ==1}">
													<span style="color:red" class="mustans_showQus">(必答)</span>
												</c:if>
												<c:if test="${quetionList.shifoubida ==0}">
													<span class="mustans_showQus">(非必答)</span>
												</c:if> --%>
							</div>

							<p class="title_showQus">
								<c:if test="${quetionList.shifoubida ==1}">
									<span id="anstype${que_num_i}"
										style="color: red; width: 0px; margin-left: 5px;">*</span>
								</c:if>
								<c:if test="${quetionList.shifoubida ==0}">
									<span id="anstype${que_num_i}"></span>
								</c:if>

								<span>${que_num_i}、</span> <span>${quetionList.biaoti}</span>
							</p>
							<p class="tipmsg_showQus">
								<span style="color: gray;">提示:${quetionList.tishineirong}</span>
							</p>
						</div>

						<span id="tihao${que_num_i}" class="hidefordata">${quetionList.tihao}</span>
						<span id="leixing${que_num_i}" class="hidefordata">${quetionList.leixing}</span>

						<%--<p>${quetionList.biaoti}</p>
	  				<p>${quetionList.tishineirong}</p>
	  				<p>${quetionList.shifoubida}</p> --%>
						<div class="innercontent_showQus${que_num_i}">
							<c:if test="${quetionList.leixing =='单选题'}">
								<c:forEach var="my_choose_cards" items="${choose_cards}">
									<c:forEach var="iuse_choose_cards" items="${my_choose_cards}">
										<c:if test="${quetionList.tihao ==iuse_choose_cards.bianhao}">

											<%-- <p>${iuse_choose_cards.bianhao}</p> --%>
											<%-- <p>${iuse_choose_cards.neirong}</p>
	  					 			<p>${iuse_choose_cards.fenzhi}</p> --%>
											<ul class="chosewap_showQus">
												<li>
													<div class="eachchose_showQus">
														<input class="" name="myradio${quetionList.tihao}"
															type="radio" value="${iuse_choose_cards.neirong}" /> <span
															class="chose_text">${iuse_choose_cards.neirong}</span>
														<%-- 															<span class="">(分值${iuse_choose_cards.fenzhi}%)</span> --%>
													</div>
												</li>
											</ul>

										</c:if>
									</c:forEach>
								</c:forEach>
							</c:if>


							<c:if test="${quetionList.leixing =='多选题'}">
								<c:forEach var="my_choose_cards" items="${choose_cards}">
									<c:forEach var="iuse_choose_cards" items="${my_choose_cards}">
										<c:if test="${quetionList.tihao ==iuse_choose_cards.bianhao}">
											<%-- <p>${iuse_choose_cards.bianhao}</p> --%>
											<%-- <p>${iuse_choose_cards.neirong}</p>
	  					 			<p>${iuse_choose_cards.fenzhi}</p> --%>
											<ul class="chosewap_showQus">
												<li>
													<div class="eachchose_showQus_checkbox">
														<input class="" name="mycheckbox${quetionList.tihao}"
															type="checkbox" value="${iuse_choose_cards.neirong}" />
														<span class="chose_text">${iuse_choose_cards.neirong}</span>
														<%-- 														<span class="">(分值${iuse_choose_cards.fenzhi}%)</span> --%>
													</div>
												</li>
											</ul>

										</c:if>
									</c:forEach>
								</c:forEach>
							</c:if>
							<c:if test="${quetionList.leixing =='多项填空题'}">
								<c:forEach var="my_fill_cards" items="${fill_cards}">
									<c:forEach var="iuse_fill_cards" items="${my_fill_cards}">
										<c:if test="${quetionList.tihao ==iuse_fill_cards.dxnum}">
											<%-- <p>${iuse_fill_cards.dxnum}</p> --%>
											<ul style="height: 30px;">
												<li>
													<div class="">
														<span style="float: left; margin-left: 65px;">你的答案是：</span>
														<p
															style="width: 320px; display: inline-block; float: left; border: gray 1px solid;"
															contenteditable="true"
															onblur="testFillSize(${iuse_fill_cards.minlength},${iuse_fill_cards.maxlength})"></p>
														<span class="errotip" id="errotip"></span> <span
															style="float: right">请输入${iuse_fill_cards.minlength}~${iuse_fill_cards.maxlength}个字符</span>
													</div>
												</li>
											</ul>


										</c:if>
									</c:forEach>
								</c:forEach>
							</c:if>
			<script type="text/javascript">
              function checknull(i){
                if($('#choseFileButton'+i)[0].files[0] == undefined){
                  alert("请选择一个文件！");
                  return false;
                }
                else if ($('#choseFileButton'+i)[0].files[0].size > 1024*1024*4 ) {
                      alert("文件大小超过4M,无法上传!");
                      return false;
                }
                else {
                  alert("上传成功！");
                  $("#uploadButton"+i).css("color","gray");
                  return true;
                }

              }
          </script>
							<c:if test="${quetionList.leixing =='文件上传题'}">
								<c:forEach var="my_file_questions" items="${file_questions}">
									<c:if test="${quetionList.tihao ==my_file_questions.tihao}">
										<%-- <input id="choseFileButton" type="file" name="y"/>
                   	<input id="uploadButton" type="button"  value="上传" /> --%>


										<form action="/TPM/FilePost" method="post" name="uploadform"
											enctype="multipart/form-data" target="frameName"
											onsubmit="return checknull(${que_num_i});">
											    <input id="choseFileButton${que_num_i}"
												class="choseFileButton" name="y" type="file">      
											  <input id="uploadButton${que_num_i}" class="uploadButton"
												type="submit" value="开始上传"> <input name="QnaireID"
												value="${QnaireID}" style="display: none"></input> <input
												name="this_que_num" value="${quetionList.tihao}"
												style="display: none"></input>    
										</form>
										<iframe src="" frameborder="0" name="frameName"
											style="display: none"></iframe>
										<%--  <p>${my_file_questions.tihao}</p> --%>
										<span style="display: block; margin-left: -60;">(文件最大上传空间：${my_file_questions.maxspace}MB)</span>
									
									
									
									</c:if>
								</c:forEach>
							</c:if>

							<c:if test="${quetionList.leixing =='单项填空题'}">
								<span style="float: left; margin-left: 65px;">你的答案是：</span>
								<p
									style="width: 460px; display: inline-block; float: right; margin-right: 50px; border: gray 1px solid;"
									contenteditable="true"></p>
							</c:if>
						</div>
					</div>
				</c:forEach>
				<span id="qusNum" class="hidefordata">${que_num_i}</span>
				<div class="stuSubmitQustionnair">
					<span id="submitTip"></span> <input id="stuSubmit" type="button"
						name="" value="提交">
				</div>
			</div>
		</div>
	</div>

</body>
</html>
