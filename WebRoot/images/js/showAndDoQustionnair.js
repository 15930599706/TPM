/**
 * 模块说明
 * @module 问卷显示和学生答题的页面逻辑JS
 */
	$(function(){
				$(".eachchose_showQus").click(function(){
					//radio状态转换
					var radio = $(this).find(':radio').prop("checked",true);
				})
				$(".eachchose_showQus_checkbox").click(function(){
					//checkbox状态转换
					var checkbox = $(this).find(':checkbox');
					 checkbox.prop('checked', !checkbox.prop('checked'));
				})
			});
			function testFillSize(minsiz,maxsiz){

				if(! ($(event.target).text().length>=minsiz && $(event.target).text().length<=maxsiz) ){
// 					$(event.target).focus();
					$(event.target).next().css("color","red").text("长度错误!");
				}
				else{
					$(event.target).next().text("");
				}

//				console.log($(event.target).text());
//				console.log($(event.target).text().length);
//				console.log(minsiz+"-"+maxsiz);
				return;
			}



/**
 * 模块说明
 * @module  学生答题后问卷上传
 */

 	$(function(){
		$("#stuSubmit").click(function(){

			for (var i = 1; i <= $("#qusNum").text(); i++) {
			//	var mutex = 0;
				if($("#anstype"+i).text()=="*"){
			//		alert(i);
					if($("#leixing"+i).text()=='单选题' &&$(".innercontent_showQus"+i).find("input[type='radio']:checked").length == 0)
					{
							$("#submitTip").text("有必答题未填");
						//	mutex = 1;
							 return;
					}
					else if($("#leixing"+i).text()=='多选题' &&$(".innercontent_showQus"+i).find("input[type='checkbox']:checked").length == 0)
					{
							$("#submitTip").text("有必答题未填");
					//		mutex = 1;
								return;
					}
					else if($("#leixing"+i).text()=='文件上传题'&&$("#uploadButton"+i).css('color')=="rgb(0, 0, 0)")
					{
								$("#submitTip").text("有必答题未填");
								return;
					}
					else if($("#leixing"+i).text()=='单项填空题' &&$(".innercontent_showQus"+i).find("p").text()=="")
					{
							$("#submitTip").text("有必答题未填");
						//	mutex = 1;
							return;
					}
					else if($("#leixing"+i).text()=='多项填空题')
					{
						var myst = 0;
					$(".errotip").each(function(){
							if ($(this).text()=='长度错误!') {
									alert("多项填空题填空长度不正确！");
							//		mutex = 1;
									myst = 1;
										return false;
							}
						})
						if(myst == 1){
							return;
						}
						var sema =0;var semb =0;
						$(".innercontent_showQus"+i).find("p").each(function(){
						//console.log($(this).text());
							if($(this).text()==""){sema++;}
							else{sema++;semb++;}
						})
						if(semb!=sema){
							$("#submitTip").text("有必答题未填");
						//	mutex = 1;
								return;
						}
					}
					// else if(mutex == 0 &&i == $("#qusNum").text()-1){
					// 	alert("i:"+i);
					// 	// if(i == ($("#qusNum").text()-1)){
					// 	// 	mutex = 0; alert(mutex);
					// 	}
					}
			}
			$("#submitTip").text("");
			console.log($("#qusNum").text());//题目总数量
			var tihao = [];//按顺序存储题号
			var neirong = new Array();//二维数组
			for (var i = 1; i <= $("#qusNum").text(); i++) {
				//console.log($("#tihao"+i).text());
				neirong[i-1] = new Array();////按顺序存储题目内容

				tihao.push($("#tihao"+i).text());
				//console.log($("#leixing"+i).text());
				switch ($("#leixing"+i).text()) {
						case '单选题':
						//console.log($(".innercontent_showQus"+i).find("input[type='radio']:checked").val());
						if($(".innercontent_showQus"+i).find("input[type='radio']:checked").val()==undefined){
								break;
						}
						neirong[i-1].push($(".innercontent_showQus"+i).find("input[type='radio']:checked").val());
						break;
						case '多选题':
						$(".innercontent_showQus"+i).find("input[type='checkbox']:checked").each(function(){
						//console.log($(this).val());
						neirong[i-1].push($(this).val());
						})
						break;
						case '单项填空题':
						//console.log($(".innercontent_showQus"+i).find("p").text());
						neirong[i-1].push($(".innercontent_showQus"+i).find("p").text());
						break;
						case '多项填空题':
						$(".innercontent_showQus"+i).find("p").each(function(){
						//console.log($(this).text());
						neirong[i-1].push($(this).text());
						})
						break;
						case '文件上传题':
							neirong[i-1].push("文件上传题");
						break;
					default:
						alert("error");
				}
			}

			// console.log("++++题号数组++++");
			// for (var i = 0; i < tihao.length; i++) {
			// 		console.log(tihao[i]);
			// }
			// 	console.log("++++题号数组++++");

			//	console.log("++++内容数组++++");

			//数据存储到json中

			var jsonstr='[';

				for(var i=0;i<neirong.length;i++){
					 // console.log("-------------");
					// console.log(tihao[i]);
					 for(j=0;j<neirong[i].length;j++)
					 {
						 	jsonstr+='{"题号":"'+tihao[i];
							 jsonstr+='","内容":"'+neirong[i][j]+'"},';
					 }
					// console.log("-------------");

				 }

			//	console.log("++++内容数组++++");

		  jsonstr=jsonstr.substring(0,jsonstr.length-1);

		  jsonstr+=']';
		console.log(jsonstr);

			$.ajax({
			 type: 'post',
			 url: '/TPM/SetStudentQnaireAnsSevlet',
			 contentType:"application/x-www-form-urlencoded",
				     // contentType: 'application/json;charset=utf-8',
			 traditional: true,
				//dataType: 'json',
				data:{
					"QnaireID":$("#QnaireID").text(),
								jsonstr
					 },
			 success: function (data) { //返回json结果
//					 alert(data);
				 	$("#stuSubmit").attr("disabled",true);
					 custom_close();
					 reflash();
			 }
			 });

		})
	})
function custom_close(){
if
(confirm("题目结果已提交!")){
//window.opener=null;
//window.open('','_self');
//window.close();
}
else{}
}

 	function reflash(){
 		response.sendRedirect("/TPM/GetQuestionnaireServlet.servlet?QuestionnaireStatus=3");
 	}
