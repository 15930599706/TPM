<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="com.tpm.entity.Question"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <script src="/TPM/images/js/nprogress.js" ></script>
     <script src="/TPM/images/js/echarts.js" ></script>
 	 <script src="/TPM/images/js/echarts-wordcloud.js" ></script>
    <script type="text/javascript" src="/TPM/images/js/inputQust.js"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.js"></script>
    <script type="text/javascript" src="/TPM/images/js/kindeditor-min.js"></script>
     <script type="text/javascript" src="/TPM/images/js/showAndDoQustionnair.js"></script>
     <link rel="stylesheet" type="text/css" href="/TPM/images/css/inputQust.css">
     <link rel="stylesheet" type="text/css" href="/TPM/images/css/nprogress.css">
<!--     <link rel="stylesheet" type="text/css" href="/TPM/images/css/default/default.css"> -->
    <title>问卷评估</title>
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
    position:relative;
    left: 390px;

	}
	.centerwapper{
		margin-top: 20px;
		margin-bottom:20px;
	}
	.publicwap_showQus{
		margin-top: 15px;
		margin-bottom: 15px;
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
			background-color: rgb(239, 239, 239);
	}
	.eachchose_showQus{
		display: inline-block;
		height: auto;
		margin-top: 5px;
		margin-bottom: 5px;
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
  form{
    width: 400px!important;
    margin-left: 100!important;
    margin-top: 15px!important;
  }
  .ke-inline-block {
    margin: 10px,auto;
  }
  .ke-button-common input[type='button']{
    float: right;
	height: 25px;
	line-height: 18px!important;
	padding: 3px 18px;
	 display: inline-block;
	 vertical-align: middle;
	 font-weight: normal;
	 border-radius: 2px;
   margin: 0 8px 0 3px;
	 border: 1px solid #3383da;


  }
.ke-button-common input[type='button']:hover{
    color: #ffffff;
     background-color: #3383da;
}
.ke-upload-file{
  vertical-align: middle;
  font-weight: normal;
  border-radius: 2px;
  margin: 0 8px 0 3px;
  border: 1px solid #3383da;
}
.mult_table{
margin-left:10%;
cellspacing:10px;
width:40%;

}
.mult_table td{
    width: 20em;
    max-width:30px;  
    overflow: hidden; 
    white-space: nowrap; 
    text-overflow: ellipsis;  
}
.mult_table td:hover{
overflow: visible; 
color:red;
}

    </style>
  </head>
  <body >
  <div id="mask" style="width:100%; height:100%; background-color:rgba(255,255,255,0.8); display:none; position:fixed;z-index:1000; text-align:center;">
 <h1 style="vertical-align: middle;">玩命加载中</h1>
  </div> 
  	<div class="main">
      <div class="creatpaper_topbar">
        <h2 id="setQusInfo_html" class="creatpaper_topbar_title">问卷预览</h2>
        <a href="/TPM/GetMyExcel.servlet?id=${param.QnaireID}" target="view_window" style="float:right;margin-right:30px;margin-top:20px;color:white;">下载统计结果</a>
      </div>
		<p id="questionnairTitle"  style="display:block;margin-top:20px;;margin-left:auto;border:1px,solid,blue;width:100%;height:40px;font-size:30px; text-align:center;line-height:40px;">问卷标题</p>

	 <div class="centerwapper_for_position">
      <div class="centerwapper_creatpaper">
				<%-- 题号 --%>
				<c:set var="tibiao" value="0"/>
			<c:set var="que_num_i" value="0" />
 			 <c:forEach var="quetionList" items="${quetionList}" >
				 	<c:set var="que_num_i" value="${que_num_i+1}"/>

						<div class="centerwapper" >
									<div class="publicwap_showQus"   style="height:auto;">
										<div class="quetype_showQus">
											<span>
												<%-- 题号${quetionList.tihao} &nbsp --%>
												【${quetionList.leixing}】</span>
												<%-- <c:if test="${quetionList.shifoubida ==1}">
													<span style="color:red" class="mustans_showQus">(必答)</span>
												</c:if>
												<c:if test="${quetionList.shifoubida ==0}">
													<span class="mustans_showQus">(非必答)</span>
												</c:if> --%>
										</div>

										<p class="title_showQus" >
											<c:if test="${quetionList.shifoubida ==1}">
												<span style="color:red" >*</span>
											</c:if>
											<span>${que_num_i}、</span>
											<span>${quetionList.biaoti}</span>
											</p>
										<%-- <p class="tipmsg_showQus" >
											<span style="color:gray;">提示:${quetionList.tishineirong}</span>
											</p> --%>
									</div>

	  				<%-- <p>${quetionList.tihao}</p>
	  				<p>${quetionList.biaoti}</p>
	  				<p>${quetionList.tishineirong}</p>
	  				<p>${quetionList.leixing}</p>
	  				<p>${quetionList.shifoubida}</p> --%>
					<div class="selectcontent_showQus">
	  				<c:if test="${quetionList.leixing =='单选题'}">
    					<c:forEach var="my_choose_cards" items="${choose_cards}" >
	  					 		<c:forEach var="iuse_choose_cards" items="${my_choose_cards}" >
	  					 			<c:if test="${quetionList.tihao ==iuse_choose_cards.bianhao}">

	  					 			<%-- <p>${iuse_choose_cards.bianhao}</p> --%>
	  					 			<%-- <p>${iuse_choose_cards.neirong}</p>
	  					 			<p>${iuse_choose_cards.fenzhi}</p> --%>
											<ul class="chosewap_showQus" >
					              <li >
					                <div class="eachchose_showQus">
					                    <input  class="" name="my" type="radio" value="${iuse_choose_cards.neirong}"  />
					                    <span  class="chose_text" >${iuse_choose_cards.neirong}</span>
															<span class="">(分值${iuse_choose_cards.fenzhi}%)</span>
					                </div>
					              </li>
					            </ul>

	  					 			</c:if>
	  							</c:forEach>
	  					</c:forEach>
	  				</c:if>
						
						<c:if test="${quetionList.leixing =='多选题'}">
    					<c:forEach var="my_choose_cards" items="${choose_cards}" >
	  					 		<c:forEach var="iuse_choose_cards" items="${my_choose_cards}" >
	  					 			<c:if test="${quetionList.tihao ==iuse_choose_cards.bianhao}">
	  					 			<%-- <p>${iuse_choose_cards.bianhao}</p> --%>
	  					 			<%-- <p>${iuse_choose_cards.neirong}</p>
	  					 			<p>${iuse_choose_cards.fenzhi}</p> --%>
										<ul class="chosewap_showQus">
				              <li >
				                <div class="eachchose_showQus">
				                    <input class="" name="myname" type="checkbox" value="${iuse_choose_cards.neirong}" disabled/>
				                    <span class="chose_text" >${iuse_choose_cards.neirong}</span>
														<span class="">(分值${iuse_choose_cards.fenzhi}%)</span>
				                </div>
				              </li>
				            </ul>

	  					 			</c:if>
	  							</c:forEach>
	  					</c:forEach>
	  				</c:if>
	  				<c:if test="${quetionList.leixing =='多项填空题'}">
	  					<c:forEach var="my_fill_cards" items="${fill_cards}" >
	  					 		<c:forEach var="iuse_fill_cards" items="${my_fill_cards}" >
	  					 			<c:if test="${quetionList.tihao ==iuse_fill_cards.dxnum}">
	  					 			
										<ul style="height:30px;">		
										</ul>
	  					 			</c:if>
	  							</c:forEach>
	  					</c:forEach>
 					</c:if>
	  				<c:if test="${quetionList.leixing =='文件上传题'}">
	  					<c:forEach var="my_file_questions" items="${file_questions}" >
	  					 		 <c:if test="${quetionList.tihao ==my_file_questions.tihao}">
	  					 			</c:if>
	  					</c:forEach>
 					</c:if>

					<c:if test="${quetionList.leixing =='单项填空题'}">
						<!-- <span style=" float:left;margin-left: 65px;">你的答案是：</span> -->
						<!-- <p style="width:460px;display:inline-block;float:right;margin-right: 50px;border:gray 1px solid;"contenteditable="true"></p> -->
				</c:if>
				 </div>
				 </div>
			<!-- zhushi -->
			
			
			
			
				<c:if test="${quetionList.leixing =='单项填空题'}">
				  <div id="${ tibiao }" style="width:700px;height:400px;float:left">
				   
				   <div id='btnBox' style="margin-left:74%">
				  <input type="button" id="clound${ tibiao }" value="云图" onclick="Clound(${ tibiao })">
				  <input type="button" id="bar${ tibiao }" value="饼图" onclick="Pie(${ tibiao })">
				  <input type="button" id="bar${ tibiao }" value="柱状图" onclick="Bar(${ tibiao })">
				  <input type="button" id="bar${ tibiao }" value="情感分析" onclick="sentimentAnalysis (${ tibiao })">
				</div>
				   </div>
				 </c:if>
				 
				 
				 
				 <c:if test="${quetionList.leixing =='多项填空题'}">
				 <div id="${ tibiao }" style="width:700px;height:400px;max-height:400px;float:left;border:2px solid #CCC;margin-left:50px;overflow-y: auto;">
				 
				<table id="mult_table${ tibiao }" class="mult_table">
				</table>
				   </div>
				 </c:if>
				 
				 
				
				<c:if test="${quetionList.leixing =='单选题'||'多选题'}">
				  <div id="${ tibiao }" style="width:700px;height:400px;float:left">
				   
				   <div id='btnBox' style="margin-left:75%">				 
				  <input type="button" id="bar${ tibiao }" value="饼图" onclick="Pie(${ tibiao })">
				  <input type="button" id="bar${ tibiao }" value="柱状图" onclick="Bar(${ tibiao })">				  
				  </div>
				  </div>
				 </c:if> 
				 <c:if test="${quetionList.leixing =='文件上传题'}">				  
				 </c:if>
				 
				 <c:set var="tibiao" value="${ tibiao+1 }"/>
    		</c:forEach>
      </div>
    </div>
     </div>
     <script>
    var tixiang=[];
    var tiValue=[];
    var Json=[];
    var numOfTi=0;
    var jilu=-1;
    var json=[];
    
    $.ajax({
         type: 'get',
         url: '/TPM/Get4AnswersServlet.servlet?QnaireID='+${ QnaireID },
         async: true,       
         dataType: 'json',
         data:{		    	  
      	  //"queNum":tihao,
           },
          beforeSend:function(){
        	  NProgress.start();
        	  $("#mask").css("display","block");
          },
           complete:function(){
        	   NProgress.done();
        	   $("#mask").css("display","none");
           },
         success: function (result) { 
      if (result) {
               json=result;
               for(var i=0;i<result.length;i=i+result[i].geshu+1)
              {
                  jilu=jilu+1;
                  
                var tem=-1;//记录第几个题
              	var tiHao;
              	var tiType
              	for(var i=0;i<json.length;i++){
              		
              		if(json[i].tiID!=null){
              			tem++;
              			if(tem==jilu){
              				tiHao=json[i].Allnum;
              				tiType=json[i].type;
              				break;
              			}
              		}
              	} 
              	if(tiType!='文件上传题'){
              	if(tiType!='多项填空题'){
               	  var Names = [];    //类别数组（实际用来盛放X轴坐标值）
               	  var Nums = [];     //销量数组（实际用来盛放Y坐标值）
               	  /* document.getElementById('worldClound2').innerHTML=""; */
               	  
               	  /* document.getElementById(""+jilu) */
               	  var divBig=document.createElement("div");
               	  divBig.setAttribute("id","worldClound"+jilu);
               	  document.getElementById(""+jilu).appendChild(divBig); 
               	  
               	  divBig.style.marginLeft="50px";
               	  divBig.style.width="100%";
                  divBig.style.height="100%";
                     
                     var divobj=document.createElement("div");
                     divobj.setAttribute("id","worldClound-min"+jilu);
                     divobj.style.width="100%";
                     divobj.style.height="100%";
                var myChart=echarts.init(document.getElementById('worldClound'+jilu));
                 // 显示标题，图例和空的坐标轴
                 myChart.setOption({
                     title: {
                         text: '柱状图分析'
                     },
                     tooltip: {},
                     legend: {
                         data: ['选项']
                     },
                     toolbox: {
         		        show : true,
         		        feature : {
         		            mark : {show: true},
         		            dataView : {show: true, readOnly: false},
         		            magicType : {
         		                show: true, 
         		                type: ['pie', 'funnel'],
         		                option: {
         		                    funnel: {
         		                        x: '25%',
         		                        width: '50%',
         		                        funnelAlign: 'left',
         		                        max: 1548
         		                    } 
         		                }
         		            },
         		            restore : {show: true},
         		            saveAsImage : {show: true}
         		        }
         		    },
                     xAxis: {
                         data: []
                     },
                     yAxis: {},
                     series: [{
                         name: '这道题共有'+tiHao+'个人作答',
                         type: 'bar',
                        data: [] 
                     }]
                 });

                 myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画

              	    
              	  	for(var j=i+1;j<=i+result[i].geshu;j++)
              	  		{
              	  			Json.push(result[j]);
              	  		    Names.push(result[j].name);
              	  	        Nums.push(result[j].value);
              	  		}
                 
              	  tixiang[numOfTi]=Names;
              	  tiValue[numOfTi]=Nums;
              	  numOfTi++;
              	  myChart.hideLoading();    //隐藏加载动画
                  myChart.setOption({        //加载数据图表
                     xAxis: {
                   	
                         data: Names
                     },
                     series: [{
                         // 根据名字对应到相应的系列
                         name: '这道题共有'+tiHao+'个人作答',
                         data: Nums,
                         itemStyle: {

                             normal: {
                                 color: function(params) {

                                     // build a color map as your need.

                                     var colorList = [

                                       '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',

                                        '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',

                                        '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'

                                     ];

                                     return colorList[params.dataIndex]

                                 },

                             }

                         },
                     }]
                 });
              	  }
              	else{
              		 var Names = [];    //类别数组（实际用来盛放X轴坐标值）
                 	 var Nums = [];     //销量数组（实际用来盛放Y坐标值）
                     var TEM=result[i].geshu/result[i].Allnum;//空数
                     var datirenshu=result[i].Allnum;
                	    var geNum;
                        var AllNum;//答案数
                	  	for(var j=i+1;j<=i+result[i].geshu;j++)
                	  		{
                	  			if(result[i].geshu!=null){
                	  				geNum=result[i].geshu;//kongshu
                	  				
                	  				AllNum=result[i].Allnum*TEM; //datirenshu
                	  			}
                	  			
                	  		    Names.push(result[j].name);
                	  	        Nums.push(result[j].value);
                	  		}
                   
                	  tixiang[numOfTi]=Names;
                	  tiValue[numOfTi]=Nums;
                	  numOfTi++;
                	  var jiu=i;
                	
                	var element=$(".title_showQus").eq(jilu).find("span").eq(2);
                 	var s=($(element).html());
                 	console.log(s);
                 	ss = s.split("_");// 在每个逗号(,)处进行分解  ["abc", "abcd", "aaa"]
                  	var s2=[];
                 	for(var index=0;index<ss.length;index++){
                 		if(ss[index]!=""){
                 			s2.push(ss[index].replace(/&nbsp;/ig,'').replace(/\s*/g,""));
                 			
                 		}
                 		
                 	}
                 	console.log(s2);
                 	console.log(Names);
                 	console.log(AllNum);
                 	
                 		 var mult=document.getElementById("mult_table"+tem);
                 		
                		 var tr=document.createElement("tr");
                		 tr.setAttribute("id","tr0");
                		 mult.appendChild(tr);
                		 
                		 var td=document.createElement("td");
                		 td.setAttribute("id","td-1");
                		 td.innerHTML=("索引");
                		 tr.appendChild(td);
                		 
                		 var kkl=1;
                		 
                		 
                		 for(var nn=0;nn<TEM;nn++){
                			 var td=document.createElement("td");
                    		 td.setAttribute("id","td"+nn);
                    		 td.innerHTML=s2[nn];
                    		 tr.appendChild(td);
                		 }
                		 
                		 var kl=1
                         for(var n=1;n<=datirenshu;n++){//hang
                        	 
                        	 var trr=document.createElement("tr");
                    		 trr.setAttribute("id","tr"+n);
                    		 mult.appendChild(trr);
                    		 
                    		 
                        	 var tdd=document.createElement("td");
                			 tdd.setAttribute("id","td-1");
                    		 tdd.innerHTML=kl;
                    		 kl++;
                    		 trr.appendChild(tdd);
                    		 
                			 
                    		 for(var m=kkl;m<=AllNum;m++){
                    			 
                    			
                        		 
                    			 var td=document.createElement("td");
                        		 td.setAttribute("id","td"+m);
                        		 td.innerHTML=Names[m-1];
                        		 trr.appendChild(td);
                        		 if(m%TEM==0){
                        			 kkl+=3;
                        			 break;
                        		 }
                        		 
                    		 }
                    		 
                		 } 
                		 
                		 /* for(var d=1;d<datirenshu;d++){
                			 var trrr=document.getElementById("tr"+d);
                			 var tdd=document.createElement("td");
                			 tdd.innerHTML=d;
                			 trrr.appendChild(tdd);
                		 } */
                		 
                		 
                		
                		 
                		 
                		 
            	}
              	}
              	
             }
      	 }
       }
    });
    
    
    
  /*  var tixiang=[];
    var tiValue=[];
    var Json=[];
    var numOfTi=0;
    var jilu=-1;
    var json=[]; */
    
    
    
    function Clound(ctibiao){
    	var Ctibiao=ctibiao;
    	
    	
    	
    	var tem=-1;//记录第几个题
    	var tiHao;
    	
    	 for(var i=0;i<json.length;i++){
    		
    		if(json[i].tiID!=null){
    			tem++;
    			if(tem==ctibiao){
    				tiHao=json[i].Allnum;
    				break;
    			}
    		}
    	} 
    	 
    	 
    	 
        document.getElementById('worldClound'+Ctibiao).innerHTML="";
         var divobj=document.createElement("div");
         divobj.setAttribute("id","worldClound-min"+Ctibiao);
         divobj.style.width="100%";
         divobj.style.height="100%";
        document.getElementById('worldClound'+Ctibiao).appendChild(divobj);
         var jss=[];//每道题的选项
         var jj={};//每个选项作为一个json对象。
         for(var x=0;x<tixiang[Ctibiao].length;x++){
        	 jj.name=tixiang[Ctibiao][x];
        	 jj.value=tiValue[Ctibiao][x];
        	 jss.push(jj);
        	 
        	jj={}
         }
         
         
         var mychart=echarts.init(document.getElementById('worldClound-min'+Ctibiao));
      	 /* var JosnList = []; */
     		
     			/* $(function(){
     				 $.ajax({
     				      type: 'get',
     				      url:'/TPM/Get2AnswersServlet',
     				      async: true,       
     				      dataType: 'json',
     				      data:{		    	  
     				    	  //"queNum":tihao,
     				         },
     				     success: function (result) { 
     				    	 alert("成功");
     				    	  */
     				    	 /* for (var i = 1; i < result.length; i++) {
     	                         
     	                         JosnList.push(result[i]);
     	                      };
     	                      console.log(JosnList); */
     				    	
     		                  
     		                  var worldCloudoption = {
     		           			    title: {                    //图标题
     		           			        text: '云图分析',
     		           			        x: 'center',
     		           			      
     		           			        textStyle: {
     		           			            fontSize: 23,
     		           			            color:'#000'
     		           			        }
     		           
     		           			    },
     		           			    tooltip: {                  //提示框
     		           			        show: true,
     		           			    },
     		           			toolbox: {
     		       		        show : true,
     		       		        feature : {
     		       		            mark : {show: true},
     		       		            dataView : {show: true, readOnly: false},
     		       		            magicType : {
     		       		                show: true, 
     		       		                type: ['pie', 'funnel'],
     		       		                option: {
     		       		                    funnel: {
     		       		                        x: '25%',
     		       		                        width: '50%',
     		       		                        funnelAlign: 'left',
     		       		                        max: 1548
     		       		                    }
     		       		                }
     		       		            },
     		       		            restore : {show: true},
     		       		            saveAsImage : {show: true}
     		       		        }
     		       		    }, 
     		           			    restore : {show: true},
    	    	 		            saveAsImage : {show: true},
     		           			    series: [{
     		           			        name: '这道题共有'+tiHao+'个人作答',           //提示框的内容
     		           			        type: 'wordCloud',      //图类型
     		           			        sizeRange: [20, 100],    //词云字体大小范围
     		           			        rotationRange: [0, 45],//旋转角度范围
     		           			        textPadding: 0,
     		           			        autoSize: {
     		           			            enable: true,
     		           			            minSize: 6
     		           			        },
     		           			        textStyle: {
     		           			            normal: {
     		           			                color: function() {
     		           			                    return 'rgb(' + [
     		           			                        Math.round(Math.random() * 160),
     		           			                        Math.round(Math.random() * 160),
     		           			                        Math.round(Math.random() * 160)
     		           			                    ].join(',') + ')';
     		           			                }
     		           			            },
     		           			            emphasis: {
     		           			                shadowBlur: 10,
     		           			                shadowColor: '#333'
     		           			            }
     		           			        },
     		           			        data: [{
     		           			            name: tixiang[Ctibiao],
     		           			            value: tiValue[Ctibiao]
     		           			        }]
     		           			    }]
     		           			};
     	                      
     		                 
     		                 
     		                 
     		                 
     		                 worldCloudoption.series[0].data =jss;
     		                  
     		           		mychart.setOption(worldCloudoption);
     		           		
     		           	/*},
     				        error: function(){
     				        	alert("error");
     				        }
     				     }) 
     			}) */
     
         } 
    
    
    function Pie(ctibiao){
        var Ctibiao=ctibiao;
    	
    	
    	
    	var tem=-1;//记录第几个题
    	var tiHao;
    	
    	 for(var i=0;i<json.length;i++){
    		
    		if(json[i].tiID!=null){
    			tem++;
    			if(tem==ctibiao){
    				tiHao=json[i].Allnum;
    				break;
    			}
    		}
    	} 
        
        
    	var jss=[];
        var jj={};
        for(var x=0;x<tixiang[Ctibiao].length;x++){
       	 jj.name=tixiang[Ctibiao][x];
       	 jj.value=tiValue[Ctibiao][x];
       	 jss.push(jj);
       	 console.log(jj);
       	jj={}
        }
        
        
        document.getElementById('worldClound'+Ctibiao).innerHTML="";
         var divobj=document.createElement("div");
         divobj.setAttribute("id","worldClound-min"+Ctibiao);
         divobj.style.width="100%";
         divobj.style.height="100%";
        document.getElementById('worldClound'+Ctibiao).appendChild(divobj);
        //document.getElementById('wroldClound100').appendChild(divobj);
        
     var echartsPie;
     /* var json = [];
    $(function(){
 		 $.ajax({
 		      type: 'get',
 		       url: '/TPM/Get4AnswersServlet',
 		       async: true,       
 		      dataType: 'json',
 		      data:{		    	  
 		    	  //"queNum":tihao,
 		         },
 		     success: function (result) { 
 		    	 alert("成功");
 		    	 
 		    	 if (result) { */
                        /* for (var i = 1; i <Json.length; i++) {
                           //alert(result[i].name);
                           json.push(result[i]);    //挨个取出类别并填入类别数组
                       }  */
                        /*  for(var i=0;i<=result.length;i=i+result[i].geshu)
                     	  {
                     	  	for(var j=i+1;j<=i+result[i].geshu;j++)
                     	  		{
                     	  			json.push(result[j]);
                     	  		}
                     	  }  
                     	  for(var i=0;i<2;i=i+2)
                     	  {
                     	  	for(var j=i+1;j<=i+2;j++)
                     	  		{
                     	  			json.push(result[j]);
                     	  		}
                     	  }   */
                       /* console.log(json); */
                       var option = {
   		    	 		    title : {
   		    	 		        text: '饼图分析',
   		    	 		        subtext: '独家报道',
   		    	 		        x:'center'
   		    	 		    },
   		    	 		    tooltip : {
   		    	 		        trigger: 'item',
   		    	 		        formatter: "{a} <br/>{b} : {c} 岁"
   		    	 		    },
   		    	 		    legend: {
   		    	 		        orient : 'vertical',
   		    	 		        x : 'left',
   		    	 		        data:Json,
   		    	 		    },
   		    	 		    toolbox: {
   		    	 		        show : true,
   		    	 		        feature : {
   		    	 		            mark : {show: true},
   		    	 		            dataView : {show: true, readOnly: false},
   		    	 		            magicType : {
   		    	 		                show: true, 
   		    	 		                type: ['pie', 'funnel'],
   		    	 		                option: {
   		    	 		                    funnel: {
   		    	 		                        x: '25%',
   		    	 		                        width: '50%',
   		    	 		                        funnelAlign: 'left',
   		    	 		                        max: 1548
   		    	 		                    }
   		    	 		                }
   		    	 		            },
   		    	 		            restore : {show: true},
   		    	 		            saveAsImage : {show: true}
   		    	 		        }
   		    	 		    },
   		    	 		    calculable : true,
   		    	 		    series : [
   		    	 		        {
   		    	 		            name:'这道题共有'+tiHao+'个人作答', 
   		    	 		            type:'pie',
   		    	 		            radius : '55%',//饼图的半径大小
   		    	 		            center: ['50%', '60%'],//饼图的位置
   		    	 		            data:jss
   		    	 		        }
   		    	 		    ]
   		    	 		}; 
   		    	 	
   		    	 	echartsPie = echarts.init(document.getElementById('worldClound-min'+Ctibiao));
   		    	 	echartsPie.setOption(option);

   		    	 	/*}
 		    	
 		    	 },
 		        error: function(){
 		        	alert("error");
 		        }
 		     }) 
 	})  */
 	} 
    
    function Bar(ctibiao){
    	 
         var Ctibiao=ctibiao;
    	
         
         var tem=-1;//记录第几个题
     	var tiHao;
     	
     	 for(var i=0;i<json.length;i++){
     		
     		if(json[i].tiID!=null){
     			tem++;
     			if(tem==ctibiao){
     				tiHao=json[i].Allnum;
     				break;
     			}
     		}
     	} 
     	 
         var jss=[];
         var jj={};
         for(var x=0;x<tixiang[Ctibiao].length;x++){
        	 jj.name=tixiang[Ctibiao][x];
        	 jj.value=tiValue[Ctibiao][x];
        	 jss.push(jj);
        	 console.log(jj);
        	jj={}
         }
         
         
        document.getElementById('worldClound'+Ctibiao).innerHTML="";
         var divobj=document.createElement("div");
         divobj.setAttribute("id","worldClound-min"+Ctibiao);
         divobj.style.width="100%";
         divobj.style.height="100%";
        document.getElementById('worldClound'+Ctibiao).appendChild(divobj);
         //document.getElementById('wroldClound100').appendChild(divobj);
         
    	 var myChart=echarts.init(document.getElementById('worldClound-min'+Ctibiao));
     // 显示标题，图例和空的坐标轴
     myChart.setOption({
         title: {
             text: '柱状图分析'
         },
         tooltip: {},
         legend: {
             data: ['个数']
         },
         toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {
		                show: true, 
		                type: ['pie', 'funnel'],
		                option: {
		                    funnel: {
		                        x: '25%',
		                        width: '50%',
		                        funnelAlign: 'left',
		                        max: 1548
		                    }
		                }
		            },
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
         xAxis: {
             data: []
         },
         yAxis: {},
         series: [{
             name:'这道题共有'+tiHao+'个人作答',
             type: 'bar',
             data: []
         }]
     });

     myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画

     var names = [];    //类别数组（实际用来盛放X轴坐标值）
     var nums = [];     //销量数组（实际用来盛放Y坐标值）
             //指定图标的配置和数据
             //初始化echarts实例
              var myChart = echarts.init(document.getElementById('worldClound-min'+Ctibiao));
     			  
     		//使用制定的配置项和数据显示图表
             //myChart.setOption(option);
     		
             	
     			/* $(function(){
     				 $.ajax({
     				      type: 'get',
     				       url: '/TPM/Get2AnswersServlet',
     				       async: true,       
     				      dataType: 'json',
     				      data:{		    	  
     				    	  //"queNum":tihao,
     				         },
     				     success: function (result) { 
     				    	 alert("成功");
     				    	 
     				    	 if (result) { */
     				    		 
     				    		 
     				    		 
     				    		 
     		                        for (var i = 0; i < jss.length; i++) {
     		                          //alert(result[i].name);
     		                          names.push(jss[i].name);    //挨个取出类别并填入类别数组
     		                      }
     		                      for (var i = 0; i < jss.length; i++) {
     		                          nums.push(jss[i].value);    //挨个取出销量并填入销量数组
     		                      } 
     		                      console.log(nums);
     		                      myChart.hideLoading();    //隐藏加载动画
     		                      myChart.setOption({        //加载数据图表
     		                          xAxis: {
     		                        	
     		                              data: names
     		                          },
     		                          series: [{
     		                              // 根据名字对应到相应的系列
     		                              name: '这道题共有'+tiHao+'个人作答',
     		                              data: nums,
     		                             itemStyle: {

     		                                normal: {
     		                                    color: function(params) {

     		                                        // build a color map as your need.

     		                                        var colorList = [

     		                                          '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',

     		                                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',

     		                                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'

     		                                        ];

     		                                        return colorList[params.dataIndex]

     		                                    },

     		                                }

     		                            },
     		                          }]
     		                         }); 

     		                         
     		                         
     		                        
     		                       /*  for(var i=0;i<json.length;i=i+json[i].geshu+1)
     		                 	  {
     		                   	 
     		                          var Names = [];    //类别数组（实际用来盛放X轴坐标值）
          		                  	  var Nums = [];
     		         	  	          for(var j=i+1;j<=i+json[i].geshu;j++)
     		                 	  		{
     		                 	  			
     		                 	  		    Names.push(json[j].name);
     		                 	  	        Nums.push(json[j].value);
     		                 	  	        
     		                 	  		}
     		                     
     		                 	 
     		                 	  myChart.hideLoading();    //隐藏加载动画
     		                     myChart.setOption({        //加载数据图表
     		                        xAxis: {
     		                      	
     		                            data: Names
     		                        },
     		                        series: [{
     		                            // 根据名字对应到相应的系列
     		                            name: '销量',
     		                            data: Nums,
     		                        }]
     		                    }); 
     		                 	  
     		                } */   
     		                       
     		                         
     		                     /* }
     				    	 },
     				   error: function(){
     				        	alert("error");
     				        }
     				     })  
     			}) */
     			
     			
     }
    
    
    function sentimentAnalysis (ctibiao){
    	var tem=-1;//记录第几个题
    	var tiHao;
    	
    	 for(var i=0;i<json.length;i++){
    		
    		if(json[i].tiID!=null){
    			tem++;
    			if(tem==ctibiao){
    				tiHao=json[i].tiID;
    				break;
    			}
    		}
    	} 
    	 
    	 $.getJSON('/TPM/AnalysisEmotionServlet.servlet?QnaireID='+${ QnaireID } +"&Tihao="+tiHao,{},function(result){
    		alert("成功");
            var Ctibiao=ctibiao;
            var analy=result;
            
           document.getElementById('worldClound'+Ctibiao).innerHTML="";
            var divobj=document.createElement("div");
            divobj.setAttribute("id","worldClound-min"+Ctibiao);
            divobj.style.width="100%";
            divobj.style.height="100%";
           document.getElementById('worldClound'+Ctibiao).appendChild(divobj);
            //document.getElementById('wroldClound100').appendChild(divobj);
            
       	 var myChart=echarts.init(document.getElementById('worldClound-min'+Ctibiao));
        // 显示标题，图例和空的坐标轴
        myChart.setOption({
            title: {
                text: '情感分析'
            },
            tooltip: {},
            legend: {
                data: ['个数']
            },
            toolbox: {
   		        show : true,
   		        feature : {
   		            mark : {show: true},
   		            dataView : {show: true, readOnly: false},
   		            magicType : {
   		                show: true, 
   		                type: ['pie', 'funnel'],
   		                option: {
   		                    funnel: {
   		                        x: '25%',
   		                        width: '50%',
   		                        funnelAlign: 'left',
   		                        max: 1548
   		                    }
   		                }
   		            },
   		            restore : {show: true},
   		            saveAsImage : {show: true}
   		        }
   		    },
            xAxis: {
                data: []
            },
            yAxis: {},
            series: [{
                name: '个数',
                type: 'bar',
                data: []
            }]
        });

        myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画

       
        var nums = [];     //销量数组（实际用来盛放Y坐标值）
                //指定图标的配置和数据
                //初始化echarts实例
                 var myChart = echarts.init(document.getElementById('worldClound-min'+Ctibiao));
        			  
        		//使用制定的配置项和数据显示图表
                //myChart.setOption(option);
        		
                	
        			/* $(function(){
        				 $.ajax({
        				      type: 'get',
        				       url: '/TPM/Get2AnswersServlet',
        				       async: true,       
        				      dataType: 'json',
        				      data:{		    	  
        				    	  //"queNum":tihao,
        				         },
        				     success: function (result) { 
        				    	 alert("成功");
        				    	 
        				    	 if (result) { */
        				    		 
        				    		 
        				    		 
        				    		 
        		                        
        		                      for (var i = 0; i < analy.length; i++) {
        		                    	 
        		                          nums.push(analy[i].num);    //挨个取出销量并填入销量数组
        		                      } 
        				    	 
        				    	 
        				    	 
        				    	 
        		                      myChart.hideLoading();    //隐藏加载动画
        		                      myChart.setOption({        //加载数据图表
        		                          xAxis: {
        		                        	
        		                              data: ['超级讨厌','不喜欢','一般','喜欢','超级喜欢']
        		                          },
        		                          series: [{
        		                              // 根据名字对应到相应的系列
        		                              name: '个数',
        		                              data: nums,
        		                             itemStyle: {
        		                                normal: {　　　　　　　　　　　　　　
        		                                    color: function(params) {

        		                                        // build a color map as your need.

        		                                        var colorList = [

        		                                          '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',

        		                                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',

        		                                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'

        		                                        ];
        		                                        return colorList[params.dataIndex]
        		                                    },

        		                                }

        		                            },
        		                     }]
        		         }); 			
    	})  
    }
        				         
/* function getInnerHTML()
{
  alert(document.getElementById("tr1").innerHTML);
} */
    </script>
 	
  </body>
      
  
</html>