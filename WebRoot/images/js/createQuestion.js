//获取指标点表头信息专业
var ajax;
function getMajorList(){
    if(window.XMLHttpRequest){
      ajax=new XMLHttpRequest();
    }else if(window.ActiveXObject){
      ajax=new ActiveXObject();
    }
  //  var url="http://jieshubao.xin:8080/test/GetMajorList";
   // var url="http://192.168.137.1:8080/%E5%B7%A5%E7%A8%8B%E5%88%86/GetMajorListServlet";
    var url="../GetMajorListServlet.servlet";
    ajax.onreadystatechange=doGetMajorList;
    ajax.open("post",url,true);
    ajax.send();
  }
function doGetMajorList(){
    if(ajax.readyState==4 && ajax.status==200){
      var str=ajax.responseText;
      //alert(str);
     var obj = eval('(' + str + ')'); //由JSON字符串转换为JSON对象
     var option_catchValue = document.getElementById("option_catchValue");
     // alert(obj.length);
     for (var i = 0; i < obj.length; i++) {
          //result[i]表示获得第i个json对象即JSONObject
          //result[i]通过.字段名称即可获得指定字段的值
          var t = document.createElement("option");
          t.appendChild(document.createTextNode(obj[i].专业名称));
          t.setAttribute("value",obj[i].专业代码);
          option_catchValue.appendChild(t);
    }
  }
}
getMajorList();

//获取指标点表头信息-能力
function changeMajer(){
  var ajax_ability;
  function getMajorListMajer(){
      if(window.XMLHttpRequest){
        ajax_ability=new XMLHttpRequest();
      }else if(window.ActiveXObject){
        ajax_ability=new ActiveXObject();
      }
    var id = document.getElementById("option_catchValue"); //定位id
  var index = id.selectedIndex; // 选中索引
  var value = id.options[index].value; // 选中值

      var url ="../GetAbilityListServlet.servlet?对应系="+value;
  	  ajax_ability.onreadystatechange=doGetMajorListMajer;
      ajax_ability.open("post",url,true);
      ajax_ability.send();
    }
  function doGetMajorListMajer(){
      if(ajax_ability.readyState==4 && ajax_ability.status==200){
        var str=ajax_ability.responseText;
       var obj = eval('(' + str + ')'); //由JSON字符串转换为JSON对象
       //清除上一次的选项
       var option_catchValue_ability = document.getElementById("option_catchValue_ability");
        for (var i = 1; i < option_catchValue_ability.options.length; i++) {
          option_catchValue_ability.removeChild(option_catchValue_ability.options[i]);
        }


       for (var i = 0; i < obj.length; i++) {
            //result[i]表示获得第i个json对象即JSONObject
            //result[i]通过.字段名称即可获得指定字段的值
            // <option value ="yes" class="catch_value1"></option>
            var t = document.createElement("option");
            t.appendChild(document.createTextNode(obj[i].毕业生应获得的知识和能力名));
            t.setAttribute("value",obj[i].毕业生应获得的知识和能力ID);
            option_catchValue_ability.appendChild(t);
            // alert(obj[i].专业名称);
            // alert(obj[i].专业代码);
      }
    }
  }
  getMajorListMajer();

}

//获取指标点表头信息-指标点
function changeAbility(){
  var ajax_indexPoint;
  function getMajorListAbility(){
      if(window.XMLHttpRequest){
        ajax_indexPoint=new XMLHttpRequest();
      }else if(window.ActiveXObject){
        ajax_indexPoint=new ActiveXObject();
      }
    //  var url="http://jieshubao.xin:8080/test/GetMajorList";

    var id = document.getElementById("option_catchValue_ability"); //定位id
  var index = id.selectedIndex; // 选中索引
  // var text = id.options[index].text; // 选中文本
  var value = id.options[index].value; // 选中值
  //alert(value);
      //var url="http://192.168.137.1:8080/%E5%B7%A5%E7%A8%8B%E5%88%86/GetZhibiaoServlet?%E6%AF%95%E4%B8%9A%E7%94%9F%E5%BA%94%E8%8E%B7%E5%BE%97%E7%9A%84%E7%9F%A5%E8%AF%86%E5%92%8C%E8%83%BD%E5%8A%9BID="+value;
      var url="../GetZhibiaoServlet.servlet?ID="+value;
  	  ajax_indexPoint.onreadystatechange=doGetMajorListAbility;
      ajax_indexPoint.open("post",url,true);
      ajax_indexPoint.send();
    }
  function doGetMajorListAbility(){
      if(ajax_indexPoint.readyState==4 && ajax_indexPoint.status==200){
        var str=ajax_indexPoint.responseText;
        //alert(str);
       var obj = eval('(' + str + ')'); //由JSON字符串转换为JSON对象

        //test
        // var fa = document.getElementById("selectBoard");
        // fa.removeChild(document.getElementById("option_catchValue_indexPoint"));
        // test
       //清除上一次的选项
       var option_catchValue_indexPoint = document.getElementById("option_catchValue_indexPoint");
       //alert(obj.length);
       // option_catchValue_ability.options.length=0;
        for (var i = 1; i < option_catchValue_indexPoint.options.length; i++) {
          option_catchValue_indexPoint.removeChild(option_catchValue_indexPoint.options[i]);
        }

        option_catchValue_indexPoint.size = 3;
       for (var i = 0; i < obj.length; i++) {
            //result[i]表示获得第i个json对象即JSONObject
            //result[i]通过.字段名称即可获得指定字段的值
            // <option value ="yes" class="catch_value1"></option>
            var t = document.createElement("option");
            t.appendChild(document.createTextNode(obj[i].指标分解内容));
            t.setAttribute("value",obj[i].指标分解ID);
            option_catchValue_indexPoint.appendChild(t);
            // alert(obj[i].专业名称);
            // alert(obj[i].专业代码);
      }
            // test
            // for (var i = 0; i < obj.length; i++) {
              // var wa = document.createElement(ul);
              // var t = document.createElement("input");
              // t.appendChild(document.createTextNode(obj[i].指标分解内容));
              // t.setAttribute("value",obj[i].指标分解ID);
              // ul.appendChild(t);
              // option_catchValue_indexPoint.appendChild(wa);}
            //test


        for(var i = 0;i<$("#option_catchValue_indexPoint option").length;i++){

                    var content = $("#option_catchValue_indexPoint option").eq(i).html();
                    var value = content.substring(0,60);
                    $("#option_catchValue_indexPoint  option").eq(i).html(value);
                    $("#option_catchValue_indexPoint option").eq(i).attr('title',content);
                }  
    }
  }
  getMajorListAbility();

}

function textnull(i){
    if($("#chose_text"+i).text() == "选项A"||
        $("#chose_text"+i).text() == "选项B"||
        $("#chose_text"+i).text() == "选项C"||
        $("#chose_text"+i).text() == "选项D"||
        $("#chose_text"+i).text() == "选项" )
        {$("#chose_text"+i).text("");}
      }
//填空属性
var lastCount = 3;//上一次的表项数
function showMiniMeu(){
  var countFill =getFillProperty();//当前填空数目
    //alert(countFill);
  document.getElementById('setFillPropertyLight').style.display='block';
  document.getElementById('setFillPropertyBcg').style.display='block';
  var multFill_wap_qtest_id =  document.getElementById("multFill_wap_qtest_id").innerText;
  var multFill_que_menu_top = document.getElementById("multFill_que_menu_top");
  multFill_que_menu_top.innerHTML="";
  multFill_que_menu_top.appendChild(document.createTextNode("当前题目"+multFill_wap_qtest_id));



  var multFill_que_menu_center = document.getElementById("multFill_que_menu_center");
  //销毁旧的表格

    for (var i = 1; i <= lastCount; i++) {
      var multFill_que_menu_center_ul = document.getElementById("multFill_que_menu_center_ul_"+i);
      multFill_que_menu_center.removeChild(multFill_que_menu_center_ul);
    }
//动态生成表格
    lastCount = countFill;
    for (var i = 1; i <= countFill; i++) {
      var ul = document.createElement("ul");
      ul.setAttribute("id","multFill_que_menu_center_ul_"+i);
    multFill_que_menu_center.appendChild(ul);

    var li1 = document.createElement("ul");
    li1.setAttribute("class","cap1");
    li1.appendChild (document.createTextNode("填空"+i) );
    ul.appendChild(li1);

    var li2 = document.createElement("ul");
    li2.setAttribute("class","cap2");

      var span1 = document.createElement("span");
      var text1 = document.createTextNode("最小字数：")
      span1.style.float="left";
      span1.appendChild(text1);
      li2.appendChild (span1);

      var p1 = document.createElement("p");
      p1.style.width="40px";
      // p1.style.height="30px";
      p1.style.border="black solid 1px";
      p1.style.float="left";
      p1.setAttribute("contenteditable","true");

      p1.setAttribute("id","min"+i);

      p1.appendChild(document.createTextNode("0"));
      li2.appendChild(p1);

      var span2 = document.createElement("span");
      var text2 = document.createTextNode("最大字数：");
      span2.style.float="left";
      span2.style.marginLeft= "5px";
      span2.appendChild(text2);
      li2.appendChild (span2);

      var p2 = document.createElement("p");
        p2.style.width="auto";
        p2.style.border="black solid 1px";
        p2.style.float="left";
      p2.setAttribute("contenteditable","true");

        p2.setAttribute("id","max"+i);

      p2.appendChild(document.createTextNode("100"));
      li2.appendChild(p2);
    ul.appendChild(li2);

  }
}
  function hideMiniMeu(){
  document.getElementById('setFillPropertyLight').style.display='none';
 document.getElementById('setFillPropertyBcg').style.display='none';

}
//删除事件
$(function(){
    $('#chose_wap').on('click','.del_chose_btn',function(){
        $(this).closest('li').remove();
        return false;
    });
});
function addChosesSingle(){
    var ul_id = document.getElementById('chose_wap');
    var ul_li = document.createElement("li");
    ul_id.appendChild(ul_li);

    var left_div = document.createElement("div");
    left_div.setAttribute("class","chose_left_wap");
    ul_li.appendChild(left_div);

    left_div.appendChild(document.createTextNode("分值"));

    // var score_text = document.createElement("p");
    // score_text.setAttribute("class","chose_score");
    // score_text.setAttribute("contenteditable","true");
    // score_text.appendChild(document.createTextNode("百分比"));

    var score_select = document.createElement("select");
    score_select.setAttribute("class","chose_score");
    var option1 = document.createElement("option");
    option1.setAttribute("value","0");
    option1.appendChild(document.createTextNode("0"));
    var option2 = document.createElement("option");
    option2.setAttribute("value","20");
    option2.appendChild(document.createTextNode("20"));
    var option3 = document.createElement("option");
    option3.setAttribute("value","30");
    option3.appendChild(document.createTextNode("30"));
    var option4 = document.createElement("option");
    option4.setAttribute("value","50");
    option4.appendChild(document.createTextNode("50"));
    var option5 = document.createElement("option");
    option5.setAttribute("value","100");
    option5.appendChild(document.createTextNode("100"));
    score_select.appendChild(option1);
    score_select.appendChild(option2);
    score_select.appendChild(option3);
    score_select.appendChild(option4);
    score_select.appendChild(option5);
    left_div.appendChild(score_select);

    left_div.appendChild(document.createTextNode("%"));
    var chose_radio = document.createElement("input");
    chose_radio.setAttribute("name","#");
    chose_radio.setAttribute("type","radio");
    chose_radio.setAttribute("name","#");
    left_div.appendChild(chose_radio);
    //ul_li.innerHTML='<input name="#" type="radio"/>';

    var right_div = document.createElement("div");
    right_div.setAttribute("class","chose_right_wap");
    ul_li.appendChild(right_div);

    var chose_text_text = document.createElement("p");
    chose_text_text.setAttribute("class","chose_text");


    chose_text_text.setAttribute("contenteditable","true");
    right_div.appendChild(chose_text_text);
    chose_text_text.appendChild(document.createTextNode("选项"));

    var del_btn = document.createElement("input");
    del_btn.setAttribute("class","del_chose_btn");
    del_btn.setAttribute("type","button");
    del_btn.setAttribute("value","-");
    right_div.appendChild(del_btn);
}
function addChoseMul(){
    var ul_id = document.getElementById('chose_wap');
    var ul_li = document.createElement("li");
    ul_id.appendChild(ul_li);

    var left_div = document.createElement("div");
    left_div.setAttribute("class","chose_left_wap");
    ul_li.appendChild(left_div);

    left_div.appendChild(document.createTextNode("分值"));

    // var score_text = document.createElement("p");
    // score_text.setAttribute("class","chose_score");
    // score_text.setAttribute("contenteditable","true");
    // score_text.appendChild(document.createTextNode("百分比"));
    // left_div.appendChild(score_text);
    var score_select = document.createElement("select");
    score_select.setAttribute("class","chose_score");
    var option1 = document.createElement("option");
    option1.setAttribute("value","0");
    option1.appendChild(document.createTextNode("0"));
    var option2 = document.createElement("option");
    option2.setAttribute("value","20");
    option2.appendChild(document.createTextNode("20"));
    var option3 = document.createElement("option");
    option3.setAttribute("value","30");
    option3.appendChild(document.createTextNode("30"));
    var option4 = document.createElement("option");
    option4.setAttribute("value","50");
    option4.appendChild(document.createTextNode("50"));
    var option5 = document.createElement("option");
    option5.setAttribute("value","100");
    option5.appendChild(document.createTextNode("100"));
    score_select.appendChild(option1);
    score_select.appendChild(option2);
    score_select.appendChild(option3);
    score_select.appendChild(option4);
    score_select.appendChild(option5);
    left_div.appendChild(score_select);

    left_div.appendChild(document.createTextNode("%"));
    var chose_radio = document.createElement("input");
    chose_radio.setAttribute("name","#");
    chose_radio.setAttribute("type","checkbox");
    chose_radio.setAttribute("name","#");
    left_div.appendChild(chose_radio);
    //ul_li.innerHTML='<input name="#" type="radio"/>';

    var right_div = document.createElement("div");
    right_div.setAttribute("class","chose_right_wap");
    ul_li.appendChild(right_div);

    var chose_text_text = document.createElement("p");
    chose_text_text.setAttribute("class","chose_text");
    chose_text_text.setAttribute("contenteditable","true");
    right_div.appendChild(chose_text_text);
    chose_text_text.appendChild(document.createTextNode("选项"));

    var del_btn = document.createElement("input");
    del_btn.setAttribute("class","del_chose_btn");
    del_btn.setAttribute("type","button");
    del_btn.setAttribute("value","-");
    right_div.appendChild(del_btn);
}

//多项填空题插入填空符号
$(function(){
  $("#multFill_wap_qtest_id").focus(function(){
      $("#multFill_wap_qtest_id").removeClass("multFill_wap_qtest");
    });
});

  function Insertsign(){
   insertHTML("___");
  }

   //再加入一个全屏事件
       $(window).click(function(e)
            {
              if (window.getSelection)
              {
                   var getevent=e.srcElement?e.srcElement:e.target;
                   //不要告诉我不知道这句的意思
                //console.log(getevent.id,getevent.tagName);
                  if(getevent.id!=null && getevent.id=="multFill_Insertsign"||getevent.id=="multFill_wap_qtest_id")
                  {
                    //alert(0);
                    //代表 点了插入html的按钮
                      //则不执行getFocus方法
                   }
                  else
                    $("#multFill_wap_qtest_id").addClass("multFill_wap_qtest");//除非点了那个插入html的按钮 其他时候必须要执行getFocus来更新最后失去焦点的div
              }

            })


  function insertHTML(html)
    {
        var dthis=$("#multFill_wap_qtest_id")[0];//要插入内容的某个div,在标准浏览器中 无需这句话
        //dthis.focus();
         var sel, range;
         console.log($(dthis).hasClass("multFill_wap_qtest"));
         if($(dthis).hasClass("multFill_wap_qtest")){
           $(dthis).html(dthis.innerHTML+html);
           return;
         }
         if (window.getSelection)
          {
                 // IE9 and non-IE
                 sel = window.getSelection();
                 if (sel.getRangeAt && sel.rangeCount) {
                 range = sel.getRangeAt(0);
                 range.deleteContents();
                 var el = document.createElement('div');
                 el.innerHTML = html;
                 var frag = document.createDocumentFragment(), node, lastNode;
                 while ( (node = el.firstChild) )
                  {
                     lastNode = frag.appendChild(node);
                  }

             range.insertNode(frag);
                 if (lastNode) {
                 range = range.cloneRange();
                 range.setStartAfter(lastNode);
                 range.collapse(true);
                 sel.removeAllRanges();
                 sel.addRange(range);
                 }
                }
         }
         else if (document.selection && document.selection.type !='Control')
         {
             $(dthis).focus(); //在非标准浏览器中 要先让你需要插入html的div 获得焦点
             ierange= document.selection.createRange();//获取光标位置
             ierange.pasteHTML(html);    //在光标位置插入html 如果只是插入text 则就是fus.text="..."
             $(dthis).focus();

         }
    }

    //文本插入后可把光标放在文本最后
    function po_Last_Div(obj) {
      if (window.getSelection) {//ie11 10 9 ff safari
          obj.focus(); //解决ff不获取焦点无法定位问题
          var range = window.getSelection();//创建range
          range.selectAllChildren(obj);//range 选择obj下所有子内容
          range.collapseToEnd();//光标移至最后
      }
      else if (document.selection) {//ie10 9 8 7 6 5
          var range = document.selection.createRange();//创建选择对象
          //var range = document.body.createTextRange();
          range.moveToElementText(obj);//range定位到obj
          range.collapse(false);//光标移至最后
          range.select();
      }
       }

function getFillProperty(){
  var multFill_wap_qtest_id =  document.getElementById("multFill_wap_qtest_id").innerText;
  var countFill=0;//保存获取的填空数量
  for (var i = 1; i < multFill_wap_qtest_id.length; i++) {
      if(multFill_wap_qtest_id[i-1] == "_" && multFill_wap_qtest_id[i] != "_")
      {
        countFill++;
      }
      if(i ==  multFill_wap_qtest_id.length-1 && multFill_wap_qtest_id[i] == "_")
      {
        countFill++;
      }
  }

    return countFill;
}


function submit_singleChose(){		//单选题
    var qusType = '单选题';
    var  option_catchValue = $("#option_catchValue option:selected").val();//专业
    //option_catchValue =1;
   //alert(option_catchValue);
    var  option_catchValue_ability = $("#option_catchValue_ability option:selected").val();//能力
    //option_catchValue_ability=2;
    //alert(option_catchValue_ability);
    var  option_catchValue_indexPoint = $("#option_catchValue_indexPoint option:selected").val();//指标点
    //option_catchValue_indexPoint = 3;
    //alert(option_catchValue_indexPoint);

    var  caption = $("#queName1").text();//标题
   //alert(caption);
    var  tipText= $("#queName2").text();//提示内容
    //alert(tipText);
    var  ansType = $("#ansType option:selected").val();//是否必答
   // alert(ansType);

    var chose_Num=getNum();//获取选项数目
    //alert(chose_Num);
    var chose_score_array = new Array();
    var chose_text_array = new Array();
    for (var i = 1; i <= chose_Num; i++) //获取每个选项的分数 和 内容
    {
      chose_score_array.push($("#"+"chose_score"+i+" option:selected").val());
    // alert(chose_score_array[i]);
     chose_text_array.push($("#"+"chose_text"+i).text());
      //alert(chose_text_array[i]);
    }

    //前端向后台发送数据
    //获取指标点表头信息专业

   //  var formData = new FormData();
   //   formData.append('option_catchValue' , option_catchValue);
   //   formData.append('option_catchValue_ability',option_catchValue_ability);
    $.ajax({
   type: 'get',
   url: '../SetTiServlet.servlet',
   contentType: 'application/json;charset=utf-8',
   traditional: true,
    dataType: 'json',
    data:{
            'queType':qusType,
            'option_catchValue':option_catchValue,
            'option_catchValue_ability':option_catchValue_ability,
            'option_catchValue_indexPoint':option_catchValue_indexPoint,
            'caption':caption,
            'tipText':tipText,
            'ansType':ansType,
               "chose_Num":chose_Num,
               chose_score_array,
               chose_text_array
       },
   success: function (data) { //返回json结果
       alert();
   }
   });

}
function submit_multChose(){		//多选题
   var  qusType = '多选题';
   var  option_catchValue = $("#option_catchValue option:selected").val();//专业
  // option_catchValue =1;
  //alert(option_catchValue);
   var  option_catchValue_ability = $("#option_catchValue_ability option:selected").val();//能力
  // option_catchValue_ability=2;
   //alert(option_catchValue_ability);
   var  option_catchValue_indexPoint = $("#option_catchValue_indexPoint option:selected").val();//指标点
  // option_catchValue_indexPoint = 3;
   //alert(option_catchValue_indexPoint);

   var  caption = $("#queName1").text();//标题
  //alert(caption);
   var  tipText= $("#queName2").text();//提示内容
   //alert(tipText);
   var  ansType = $("#ansType option:selected").val();//是否必答
  // alert(ansType);

   var chose_Num=getNum();//获取选项数目
   //alert(chose_Num);
   var chose_score_array = new Array();
   var chose_text_array = new Array();
   for (var i = 1; i <= chose_Num; i++) //获取每个选项的分数 和 内容
   {
     chose_score_array[i] = $("#"+"chose_score"+i+" option:selected").val();
    //alert(chose_score_array[i]);
    chose_text_array[i] = $("#"+"chose_text"+i).text();
     //alert(chose_text_array[i]);
   }

   //前端向后台发送数据
   //获取指标点表头信息专业

  //  var formData = new FormData();
  //   formData.append('option_catchValue' , option_catchValue);
  //   formData.append('option_catchValue_ability',option_catchValue_ability);
   $.ajax({
  type: 'get',
  url: '../SetTiServlet.servlet',
  contentType: 'application/json;charset=utf-8',
  traditional: true,
   dataType: 'json',
   data:{
           'queType':qusType,
           'option_catchValue':option_catchValue,
           'option_catchValue_ability':option_catchValue_ability,
           'option_catchValue_indexPoint':option_catchValue_indexPoint,
           'caption':caption,
           'tipText':tipText,
           'ansType':ansType,
              "chose_Num":chose_Num,
              chose_score_array,
              chose_text_array
      },
  success: function (data) { //返回json结果
      alert(data);
  }
  });


}
function submit_singleFill(){		//单项填空题
var qusType = '单项填空题';
var  option_catchValue = $("#option_catchValue option:selected").val();//专业
//option_catchValue =1;
//alert(option_catchValue);
var  option_catchValue_ability = $("#option_catchValue_ability option:selected").val();//能力
//option_catchValue_ability=2;
//alert(option_catchValue_ability);
var  option_catchValue_indexPoint = $("#option_catchValue_indexPoint option:selected").val();//指标点
//option_catchValue_indexPoint = 3;
//alert(option_catchValue_indexPoint);

var  caption = $("#queName1").text();//标题
//alert(caption);
var  tipText= $("#queName2").text();//提示内容
//alert(tipText);
var  ansType = $("#ansType option:selected").val();//是否必答
//alert(ansType);
    $.ajax({
   type: 'get',
   url: '../SetTiServlet.servlet',
   contentType: 'application/json;charset=utf-8',
   data:{
            'queType':qusType,
           'option_catchValue':option_catchValue,
           'option_catchValue_ability':option_catchValue_ability,
           'option_catchValue_indexPoint':option_catchValue_indexPoint,
           'caption':caption,
           'tipText':tipText,
           'ansType':ansType

       },
   success: function (data) { //返回json结果
       alert(data);
   }
   });
}
function getNum(){           //获取选项数目并为选项设置id
var chose_score = document.getElementsByClassName("chose_score");
var chose_text = document.getElementsByClassName("chose_text");
var chose_Num = chose_score.length;//选项数量
//alert(chose_Num);
for (var i = 0; i < chose_Num; i++) {
  chose_score[i].setAttribute("id","chose_score"+(i+1));
  //alert(chose_score[i].id);
  chose_text[i].setAttribute("id","chose_text"+(i+1));
}
return chose_Num;
}

function submit_multFill(){ 		//多项填空题
var qusType = '多项填空题';
var  option_catchValue = $("#option_catchValue option:selected").val();//专业
//option_catchValue =1;
//alert(option_catchValue);
var  option_catchValue_ability = $("#option_catchValue_ability option:selected").val();//能力
//option_catchValue_ability=2;
//alert(option_catchValue_ability);
var  option_catchValue_indexPoint = $("#option_catchValue_indexPoint option:selected").val();//指标点
//option_catchValue_indexPoint = 3;
//alert(option_catchValue_indexPoint);

var  caption = $("#multFill_wap_qtest_id").text().toString();//标题
//alert(caption);
var  tipText= $("#queName2").text();//提示内容
//alert(tipText);
var  ansType = $("#ansType option:selected").val();//是否必答
//alert(ansType);


//for (var i = 1; i < count; i++) {
// var range =$(".multFill_que_menu_center .cap2 p");
// alert(range.text());
//   alert(range.text());
//}


var count = getMulFillNum();
//alert(count);;
var minSize= new Array();
var maxSize = new Array();

for (var i = 1; i <= count; i++) {
  minSize[i] = $("#min"+i).text();
//  alert(minSize[i]);
  maxSize[i] = $("#max"+i).text();
//  alert(maxSize[i]);
  // maxSize[i] = range.innerHTML;
}


  $.ajax({
  type: 'get',
  url: '../SetTiServlet.servlet',
  traditional: true,
  contentType: 'application/json;charset=utf-8',
  data:{
          'queType' : qusType,
         'option_catchValue':option_catchValue,
         'option_catchValue_ability':option_catchValue_ability,
         'option_catchValue_indexPoint':option_catchValue_indexPoint,
         'caption':caption,
         'tipText':tipText,
         'ansType':ansType,
         'count':count,
         minSize,
         maxSize
     },
  success: function (data) { //返回json结果
     alert(data);
  }
  });
}
function getMulFillNum(){           //获取选项数目并为选项设置id
var cap2 = document.getElementsByClassName("cap2");

var fNum = cap2.length - 1;//选项数量
return fNum;
}


function submit_fill(){				//文件上传题
var qusType = '文件上传题';
var  option_catchValue = $("#option_catchValue option:selected").val();//专业
//option_catchValue =1;
//alert(option_catchValue);
var  option_catchValue_ability = $("#option_catchValue_ability option:selected").val();//能力
//option_catchValue_ability=2;
//alert(option_catchValue_ability);
var  option_catchValue_indexPoint = $("#option_catchValue_indexPoint option:selected").val();//指标点
// option_catchValue_indexPoint = 3;
//alert(option_catchValue_indexPoint);

var  caption = $("#queName1").text();//标题
//alert(caption);
var  tipText= $("#queName2").text();//提示内容
//alert(tipText);
var  ansType = $("#ansType option:selected").val();//是否必答
//alert(ansType);
    $.ajax({
   type: 'get',
   url: '../SetTiServlet.servlet',
   contentType: 'application/json;charset=utf-8',
   data:{
            'queType':qusType,
           'option_catchValue':option_catchValue,
           'option_catchValue_ability':option_catchValue_ability,
           'option_catchValue_indexPoint':option_catchValue_indexPoint,
           'caption':caption,
           'tipText':tipText,
           'ansType':ansType

       },
   success: function (data) { //返回json结果
       alert(data);
   }
   });
}
