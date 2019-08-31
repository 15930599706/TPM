
function submit_fills(){				//文件上传题
	  /* var qusType = '文件上传题'; */
	  var  option_catchValue = $("#option_catchValue option:selected").val();//专业
	  option_catchValue =1;
	  alert(option_catchValue);
	  var  option_catchValue_ability = $("#option_catchValue_ability option:selected").val();//能力
	  option_catchValue_ability=2;
	  alert(option_catchValue_ability);
	  var  option_catchValue_indexPoint = $("#option_catchValue_indexPoint option:selected").val();//指标点
	  option_catchValue_indexPoint = 357;
	  alert(option_catchValue_indexPoint);

	  /* var  caption = $("#queName1").text();//标题
	  //alert(caption);
	  var  tipText= $("#queName2").text();//提示内容
	  //alert(tipText);
	  var  ansType = $("#ansType option:selected").val();//是否必答
	  //alert(ansType); */
	      $.ajax({
	     type: 'get',
	     url: '../GetSuoyinServlet.servlet',
	     contentType: 'application/json;charset=utf-8',
	     data:{
	              /* 'queType':qusType, */
	             'option_catchValue':option_catchValue,
	             'option_catchValue_ability':option_catchValue_ability,
	             'option_catchValue_indexPoint':option_catchValue_indexPoint,
	             /* 'caption':caption,
	             'tipText':tipText,
	             'ansType':ansType */

	         },
	     success: function (data) { //返回json结果
	         alert(data);
	     }
	     });
	}

function f(){
	var ajax;
 function getMajorLists(){
    if(window.XMLHttpRequest){
      ajax=new XMLHttpRequest();
    }else if(window.ActiveXObject){
      ajax=new ActiveXObject();
    }
   //  var url="http://jieshubao.xin:8080/test/GetMajorList";
   // var url="http://192.168.137.1:8080/%E5%B7%A5%E7%A8%8B%E5%88%86/GetMajorListServlet.servlet";
    var url="../GetSuoyinServlet.servlet";
    //var url="http://192.168.137.1:8080/%E5%B7%A5%E7%A8%8B%E5%88%86/GetSuoyinServlet.servlet";
    ajax.onreadystatechange=doGetMajorLists;
    ajax.open("post",url,true);
    ajax.send();
  }
 function doGetMajorLists(){
    if(ajax.readyState==4 && ajax.status==200){
      var str=ajax.responseText;
      //alert(str);
     var obj = eval('(' + str + ')'); //由JSON字符串转换为JSON对象
     var ul = document.getElementById("question.show");
     // alert(obj.length);
     for (var i = 0; i < obj.length; i++) {
    	 
    	     var tr=document.createElement("tr");
    		 var td1=document.createElement("td");
    		 var td2=document.createElement("td");
    		 var td3=document.createElement("td");
    		 var td4=document.createElement("td");
    		 var td5=document.createElement("td");
    		 
    		  td1.innerHTML=obj[i].题号;
    		  td2.innerHTML=obj[i].标题;
    		  td3.innerHTML=obj[i].提示内容;
    		  td4.innerHTML=obj[i].类型;
    		  td5.innerHTML=obj[i].是否必答;
    		  
    			  
    			  tr.appendChild(td1);
    			  tr.appendChild(td2);
    			  tr.appendChild(td3);
    			  tr.appendChild(td4);
    			  tr.appendChild(td5);
    			  
    		  
    		  var table=document.getElementById("table");
    		  table.appendChild(tr);
    		  if(obj[i].类型=="单选题"){
    			 var td=document.createElement("td");
    			 td.innerHTML=obj[i].选项内容;
    			 var td2=document.createElement("td");
    			 td2.innerHTML=obj[i].分值;
    			 tr.appendChild(td);
    			 tr.appendChild(td2);
    			 
    		  }
    		  if(obj[i].类型=="多选题"){
     			 var td=document.createElement("td");
     			 td.innerHTML=obj[i].选项内容;
     			 var td2=document.createElement("td");
     			 td2.innerHTML=obj[i].分值;
     			 tr.appendChild(td);
     			 tr.appendChild(td2);
     			 
     		  }
    	/*var div=document.createElement("div").innerHTML="<p>"+obj[i].题号+"$$$$$"+obj[i].标题+"$$$$$"+obj[i].提示内容+"</p>";
    	 ul.appendChild(div);*/
         /* //result[i]表示获得第i个json对象即JSONObject
          //result[i]通过.字段名称即可获得指定字段的值
          // <option value ="yes" class="catch_value1"></option>
          var t = document.createElement("li");
          t.innerHTML=("<p>"+obj[i].类型+"</p>");
          t.appendChild(obj[i].标题);
          
          t.setAttribute("value",obj[i].专业代码);
          
          ul.appendChild(t);
          alert(obj[i].题号);
          alert(obj[i].标题);*/
    }
  }
}
getMajorLists();
}
