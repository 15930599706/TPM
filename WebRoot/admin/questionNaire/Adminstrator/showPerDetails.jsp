<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.tpm.entity.Question"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <%-- <script type="text/javascript" src="/TPM/images/js/inputQust.js"></script> --%>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>

     <link rel="stylesheet" type="text/css" href="/TPM/images/css/inputQust.css">
<!--     <link rel="stylesheet" type="text/css" href="/TPM/images/css/default/default.css"> -->
    <title>创建问卷</title>
<style>
    .creatpaper_topbar{
      border: 1px solid blue;
      height: 64px;
      width: 100%;
      background-color: #0078c8;
    }
      .creatpaper_topbar .creatpaper_topbar_title{
        float: left;
        color: white;
        line-height: 64px;
        margin-left: 60px;
      }
	.centerwapper_for_position{
		position:absolute;
		width:100%;
    height: 100%;
	}
	.centerwapper_creatpaper{
    /* position:relative;
    left: 390px; */
    margin: 0px;
    padding-bottom: 0px;
    border:0px;
    /* box-shadow: 0 10px 10px #d8d8d8; */
    width: 700px;
	}
  .centerwapper_creatpaper:hover{
    border:0px;
     box-shadow: 0 0px 0px ;
  }
	.centerwapper{
		margin-top: 5px;
		margin-bottom:5px;
    border:0px;
    padding-bottom: 20px;
    margin-left: 0px;
	}
  .centerwapper:hover{
    border: 0px;
    box-shadow: 0 0px 0px;
  }
	.publicwap_showQus{
		margin-top: 0px;
		margin-bottom: 5px;
	}
	.quetype_showQus{
		height: 25px;
	}
	.quetype_showQus span{
		float: left;
		margin-left: 10px;
	}
	.title_showQus{
		height: 30px;
		line-height: 30px;
		font-size: 20px;
		font-weight: bold;
		color: rgb(68,68,68);
		margin-top: 5px;
		margin-bottom: 5px;
	}
	.title_showQus span{
		float: left;
	}
	.title_showQus span:nth-child(1){
		margin-left: 20px;
	}
	.chosewap_showQus:hover{
			cursor:pointer;
			/* background-color: rgb(239, 239, 239); */
	}
	.eachchose_showQus{
		display: inline-block;
		height: auto;
		margin:0px;

	}
  .eachchose_showQus:hover{
    background-color: white;
  }
	.eachchose_showQus input{
		float: left;
		margin-left: 65px;
		width: 20px;
		height: 20px;
	}
	.eachchose_showQus :nth-child(2){
		float: left;
		font-size: 18px;
		height: auto;
		width:500px;
		line-height: 25px;
		text-align: left;
	}
	.eachchose_showQus :nth-child(3){
		width:100px;
		float:right;
 		margin-right: 10px;
 		text-align: left;
	}


    </style>
  </head>
  <body style="background-color: white;">
  	<div class="main">
      <div class="togshowcheckbox">
        <input type="checkbox" id="tangleAns" style="width: 20px;height: 18px;margin-top:10px;"/>仅显示答案
      </div>

	 <div class="centerwapper_for_position">

      <div class="centerwapper_creatpaper">
				<%-- 题号 --%>
			<c:set var="que_num_i" value="0" />
 			 <c:forEach var="quetionList" items="${questionsList}" >
				 	<c:set var="que_num_i" value="${que_num_i+1}"/>

						<div class="centerwapper" >
									<div class="publicwap_showQus"   style="height:auto;">

										<p class="title_showQus" >
											<c:if test="${quetionList.shifoubida ==1}">
												<span style="color:red" >*</span>
											</c:if>
											<span>${que_num_i}、</span>
											<span>${quetionList.biaoti}</span>
											</p>
									</div>

					<div class="selectcontent_showQus">
	  				<c:if test="${quetionList.leixing =='单选题'}">
    					<c:forEach var="my_choose_cards" items="${choose_cardArrayList}" >
	  					 		<c:forEach var="iuse_choose_cards" items="${my_choose_cards}" >
	  					 			<c:if test="${quetionList.tihao ==iuse_choose_cards.bianhao}">

											<ul class="chosewap_showQus" >
					              <li >
					                <div class="eachchose_showQus">
			                     <c:set var="switch_i" value="0" />

                            <c:forEach var="temp_answerList" items="${answerList}" >
                              <c:forEach var="my_answerList" items="${temp_answerList}" >
                                <c:if test="${my_answerList.tiID == quetionList.tihao}">
                                  <c:if test="${my_answerList.answer == iuse_choose_cards.neirong}">
                                   <c:set var="switch_i" value="1" />
                                  </c:if>
                                </c:if>
                              </c:forEach>
                            </c:forEach>

                            <c:if test="${switch_i == 0}">
                              <input  class="" name="my1" type="radio" onclick="return false" value="${iuse_choose_cards.neirong}"  />
                              <span  class="chose_text" >${iuse_choose_cards.neirong}</span>
                            </c:if>
                            <c:if test="${switch_i == 1}">
					                    <input  class="" name="my" type="radio" checked value="${iuse_choose_cards.neirong}"  />
					                    <span  class="chose_text" style="color:green;" >${iuse_choose_cards.neirong}</span>
                              <c:set var="switch_i" value="0" />
                            </c:if>

															<span class="">(分值${iuse_choose_cards.fenzhi}%)</span>
					                </div>
					              </li>
					            </ul>

	  					 			</c:if>
	  							</c:forEach>
	  					</c:forEach>
	  				</c:if>
						</div>
						<c:if test="${quetionList.leixing =='多选题'}">
    					<c:forEach var="my_choose_cards" items="${choose_cardArrayList}" >
	  					 		<c:forEach var="iuse_choose_cards" items="${my_choose_cards}" >
	  					 			<c:if test="${quetionList.tihao ==iuse_choose_cards.bianhao}">
	  					 			<%-- <p>${iuse_choose_cards.bianhao}</p> --%>
	  					 			<%-- <p>${iuse_choose_cards.neirong}</p>
	  					 			<p>${iuse_choose_cards.fenzhi}</p> --%>
										<ul class="chosewap_showQus">
				              <li >
				                <div class="eachchose_showQus">

                            <c:set var="switch_i" value="0" />
                            <c:forEach var="temp_answerList" items="${answerList}" >
                              <c:forEach var="my_answerList" items="${temp_answerList}" >
                                <c:if test="${my_answerList.tiID == quetionList.tihao}">
                                  <c:if test="${my_answerList.answer == iuse_choose_cards.neirong}">
                                      <c:set var="switch_i" value="1" />
                                  </c:if>
                                </c:if>
                              </c:forEach>
                            </c:forEach>

                              <c:if test="${switch_i == 0}">
                            <input class="" name="myname" type="checkbox" value="${iuse_choose_cards.neirong}" disabled/>
                           <span class="chose_text" >${iuse_choose_cards.neirong}</span>
                           </c:if>

                             <c:if test="${switch_i == 1}">
                           <input class="" name="myname" type="checkbox" checked value="${iuse_choose_cards.neirong}" disabled/>
                           <span class="chose_text"  style="color:green;">${iuse_choose_cards.neirong}</span>
                           <c:set var="switch_i" value="0" />
                         </c:if>
														<span class="">(分值${iuse_choose_cards.fenzhi}%)</span>
				                </div>
				              </li>
				            </ul>

	  					 			</c:if>
	  							</c:forEach>
	  					</c:forEach>
	  				</c:if>
	  				<c:if test="${quetionList.leixing =='多项填空题'}">
              <div class="c_multfill">
                <c:forEach var="temp_answerList" items="${answerList}" >
                  <c:forEach var="my_answerList" items="${temp_answerList}" >
                    <c:if test="${my_answerList.tiID == quetionList.tihao}">
                      <span>
                        【${my_answerList.answer}】</span>
                    </c:if>
                  </c:forEach>
                </c:forEach>
              </div>

 					</c:if>
	  				<c:if test="${quetionList.leixing =='文件上传题'}">
              <div class="c_file">
                <c:forEach var="my_file_questions" items="${file_questionArrayList}" >
  	  					 		 <c:if test="${quetionList.tihao ==my_file_questions.tihao}">
                       <c:forEach var="temp_answerList" items="${answerList}" >
                         <c:forEach var="my_answerList" items="${temp_answerList}" >
                           <c:if test="${my_answerList.tiID == quetionList.tihao}">
                             <span>
                               【${my_answerList.answer}】</span>
                           </c:if>
                         </c:forEach>
                       </c:forEach>
  	  					 			</c:if>
  	  					</c:forEach>
              </div>
 					</c:if>

					<c:if test="${quetionList.leixing =='单项填空题'}">
		      <div class="c_singlefill">
              <c:forEach var="temp_answerList" items="${answerList}" >
                <c:forEach var="my_answerList" items="${temp_answerList}" >
                  <c:if test="${my_answerList.tiID == quetionList.tihao}">
                    <span>
                      【${my_answerList.answer}】</span>
                  </c:if>
                </c:forEach>
              </c:forEach>
            </div>
        </c:if>

        <div class="onlyAns" style="display:none">
            <c:set var="switch_kk" value="1" />
          <c:forEach var="temp_answerList" items="${answerList}" >
            <c:forEach var="my_answerList" items="${temp_answerList}" >
                <c:if test="${my_answerList.tiID == quetionList.tihao}">


                   <c:if test="${switch_kk != que_num_i ||que_num_i==1 }">
                  <div class="tihao_num" style="display:none;float:left">第${que_num_i}题:</div>
                  <c:set var="switch_kk" value="${que_num_i}" />
                  </c:if>
                <div style="float:left">
                  【${my_answerList.answer}】</div>
              </c:if>
            </c:forEach>
          </c:forEach>
        </div>

 </div>
    		</c:forEach>
      </div>
    </div>
 	</div>
<script type="text/javascript">

    $("#tangleAns").click(function(){
      if ($("#tangleAns").prop('checked')) {
        $(".onlyAns").css("display","block");
        $(".selectcontent_showQus").css("display","none");
        $(".chosewap_showQus").css("display","none");
        $(".c_multfill").css("display","none");
        $(".c_file").css("display","none");
        $(".c_singlefill").css("display","none");
        $(".quetype_showQus").css("display","none");
        $(".title_showQus").css("display","none");
        $(".tihao_num").css("display","block");
      }
      else{
        $(".onlyAns").css("display","none");
        $(".selectcontent_showQus").css("display","block");
        $(".chosewap_showQus").css("display","block");
        $(".c_multfill").css("display","block");
        $(".c_file").css("display","block");
        $(".c_singlefill").css("display","block");
        $(".quetype_showQus").css("display","block");
        $(".title_showQus").css("display","block");
        $(".tihao_num").css("display","none");
      }
    })
</script>
  </body>
</html>
