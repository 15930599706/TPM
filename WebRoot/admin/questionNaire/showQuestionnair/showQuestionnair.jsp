<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.tpm.entity.Question"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<script type="text/javascript" src="/TPM/images/js/inputQust.js"></script>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
<script type="text/javascript" src="/TPM/images/js/kindeditor-min.js"></script>

<script type="text/javascript"
	src="/TPM/images/js/showAndDoQustionnair.js"></script>
<link rel="stylesheet" type="text/css"
	href="/TPM/images/css/inputQust.css">
<!--     <link rel="stylesheet" type="text/css" href="/TPM/images/css/default/default.css"> -->
<title>问卷预览</title>
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
	left: 390px;
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

.title_showQus span:nth-child(1) {
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
</style>
</head>
<body>
	<div class="main">
		<div class="creatpaper_topbar">
			<h2 id="setQusInfo_html" class="creatpaper_topbar_title">问卷预览</h2>
		</div>
		<p id="questionnairTitle"
			style="display: block; margin-top: 20px;; margin-left: auto; border: 1px, solid, blue; width: 100%; height: 40px; font-size: 30px; text-align: center; line-height: 40px;">${questionnaireName } </p>

		<div class="centerwapper_for_position">
			<div class="centerwapper_creatpaper" style="left: 300px;">
				<%-- 题号 --%>
				<c:set var="que_num_i" value="0" />
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
									<span style="color: red">*</span>
								</c:if>
								<span>${que_num_i}、</span> <span>${quetionList.biaoti}</span>
							</p>
							<p class="tipmsg_showQus">
								<span style="color: gray;">提示:${quetionList.tishineirong}</span>
							</p>
						</div>

						<%-- <p>${quetionList.tihao}</p>
	  				<p>${quetionList.biaoti}</p>
	  				<p>${quetionList.tishineirong}</p>
	  				<p>${quetionList.leixing}</p>
	  				<p>${quetionList.shifoubida}</p> --%>
						<div class="selectcontent_showQus">
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
														<input class="" name="my" type="radio"
															value="${iuse_choose_cards.neirong}" /> <span
															class="chose_text">${iuse_choose_cards.neirong}</span> <span
															class="">(分值${iuse_choose_cards.fenzhi}%)</span>
													</div>
												</li>
											</ul>

										</c:if>
									</c:forEach>
								</c:forEach>
							</c:if>
						</div>
						<c:if test="${quetionList.leixing =='多选题'}">
							<c:forEach var="my_choose_cards" items="${choose_cards}">
								<c:forEach var="iuse_choose_cards" items="${my_choose_cards}">
									<c:if test="${quetionList.tihao ==iuse_choose_cards.bianhao}">
										<%-- <p>${iuse_choose_cards.bianhao}</p> --%>
										<%-- <p>${iuse_choose_cards.neirong}</p>
	  					 			<p>${iuse_choose_cards.fenzhi}</p> --%>
										<ul class="chosewap_showQus">
											<li>
												<div class="eachchose_showQus">
													<input class="" name="myname" type="checkbox"
														value="${iuse_choose_cards.neirong}" disabled /> <span
														class="chose_text">${iuse_choose_cards.neirong}</span> <span
														class="">(分值${iuse_choose_cards.fenzhi}%)</span>
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
													<span id="errotip"></span> <span style="float: right">请输入${iuse_fill_cards.minlength}~${iuse_fill_cards.maxlength}个字符</span>
												</div>
											</li>
										</ul>


									</c:if>
								</c:forEach>
							</c:forEach>
						</c:if>
						<c:if test="${quetionList.leixing =='文件上传题'}">
							<c:forEach var="my_file_questions" items="${file_questions}">
								<c:if test="${quetionList.tihao ==my_file_questions.tihao}">

									<!--  <input class="ke-input-text" type="text" id="url" value="" readonly="readonly" />  -->
									<input type="button" id="uploadButton" value="上传" />

									<%-- 	  					 			        <p>${my_file_questions.tihao}</p> --%>
									<span>(文件最大上传空间：${my_file_questions.maxspace}MB)</span>
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
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>
