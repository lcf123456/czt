<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title><c:out value="${titleInfo}"/></title>
<link  rel="stylesheet" href="<spring:url value="/css/index/style.css"/>" type="text/css" />
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>

<script type="text/javascript" language="javascript">
//按回车键登录
document.onkeydown = function(e){
	var usercodeVal = $("#usercode").textbox('getValue');
	var passwordVal = $("#password").textbox('getValue');
	if((usercodeVal!=null&&usercodeVal.length>0))
		{
		 document.getElementById('showMsg').innerHTML="";
		}
	
    var event = e || window.event;  
    var code = event.keyCode || event.which || event.charCode;
    if (code == 13) {
        login();
    }
}

	$(function(){
		$('#loading').window({
			title:'系统提示',
			closable:false,
			collapsible:false,
			minimizable:false,
			maximizable:false,
			resizable:false,
			width:355,
			height:120,
			closed:true,
		    modal:true   
		});
		checkcode();
		//document.getElementById("imageCode").src="/img/index/yaoshi.png";
		// $('#username').textbox('textbox').focus();//加载时默认聚焦用户名输入框
		 $('#usercode').focus();//加载时默认聚焦用户名输入框
		 document.getElementById('showMsg').innerHTML="";
		 
		 $("input",$("#usercode").next("div")).blur(function(){  
			    alert("登录名已存在");  
			})
			
		 $("#username").textbox({
			 onChange: function (n,o) {
				 document.getElementById('showMsg').innerHTML="";
			 }

			 });
	});
	
	function checkcode(){  
	    var XMLHttp = null;  
	    if (window.XMLHttpRequest) {  
	        XMLHttp = new XMLHttpRequest()  
	    } else if (window.ActiveXObject) {  
	        XMLHttp = new ActiveXObject("Microsoft.XMLHTTP");  
	    }  
	    XMLHttp.onreadystatechange = function() {  
	        if (XMLHttp.readyState == 4) {  
	            document.getElementById("imageCode").src =  "${contextPath}/getImage?" + new Date();//改变验证码图片    
	        }  
	    }  
	    //将请求发送出去    
	    //加上new Date()防止浏览器缓存，不重新发送请求  
	    XMLHttp.open("GET", "${contextPath}/getImage?" + new Date(), true);  
	    XMLHttp.send(null);  
	    $("#checkcode").textbox('setValue', "");
	}  
	
	function verificationcode(usercode,password){
		 var text=$.trim($("#checkcode").val());
		 $.post("${contextPath}/imgCodeVerify.json",{op:text},function(data){
			 //console.log("data="+data);
			 data=parseInt($.trim(data));
			 if(data>0){
				//验证码成功继续检查用户名及密码
				//ajax异步提交  
		           $.ajax({            
		                  url:"${contextPath}/doLogin.json", 
		                  data:{
		                	  usercode:usercode,userpsw:password
		                  },
		                  beforeSend : function () {
		                	  $('#loading').window('open');
		          		},
		                  error:function(request) {      // 设置表单提交出错                 
		                      $("#showMsg").html("登录异常，请联系管理员");  //登录错误提示信息
		                      $('#loading').window('close');
		                  },
		                  success:function(data) {
		                	  $('#loading').window('close');
		                    if(data.resultCode=="0" || data.resultCode=="2"){
		                    	$("#showMsg").html(data.msg);  //登录错误提示信息
		                    	 checkcode();  //验证失败后需要更换验证码
		                    }
		                    else
		                    	{
		                    	//window.opList = data.opList;
		                    	//alert(window.opList);
		                    	//console.log(checkOp("209")+","+checkOp("208"));
		                    	window.location="${contextPath}/main/toMain";
		                    	}
		                    
		                  }
		            });  
			 }else{
				 $("#showMsg").html("请输入正确的验证码");  //验证失败
				 checkcode();  //验证失败后需要更换验证码
			 }
		 });
		// document.getElementById('showMsg').value=""; // 将验证码清空
		$("#checkcode").textbox('setValue', "");
	 }
	
	/**
	 * 登录
	 */
	function login(){
		
		var usercode = $("#usercode").val();
		var password= $("#password").val();
		 if(usercode =="" || password==""){
	         document.getElementById('showMsg').innerHTML="用户名或密码为空，请输入";
	     }
		 else
			 {
			 verificationcode(usercode,password);//验证码校验
			 }
	}
	
	</script>


	
</head>


<body>
<div id="box">
		<div id="logo"><img src="<spring:url value="/img/index/CZT_logo.png"/>" width="716" height="39" /></div>
        <div id="block">
            <div id="userLogin"><img src="<spring:url value="/img/index/user-login.png"/>" width="194" height="36" /></div>
            <div id="login">
              <div id="left"><img src="<spring:url value="/img/index/yaoshi.png"/>" width="115" height="128"/></div>
              <div id="right">
                <div class="field ph-hide">
		            <label for="TPL_username_1">用户名 :&nbsp;</label>
		            <input id="usercode" name="usercode" type="text" class="easyui-textbox" data-options="iconCls:'icon-man'" style="width:240PX;height:30px;" tabindex="5" >           	
        		</div>
				<div class="field">
				  <label id="password-label">密&nbsp;&nbsp;  码 :&nbsp;</label>
				   <input id="password" name="password" type="password" class="easyui-textbox" data-options="iconCls:'icon-lock'"  style="width:240PX;height:30px" tabindex="5">    
				</div>
				<div class="verifyImg">
				  <li>
					  <label id="validate-label">验证码 :&nbsp;</label>
					  <input id="checkcode" name="checkcode" type="text" class="easyui-textbox"   style="width:140PX;height:30px;" tabindex="5">    
				  </li>
				  <li>
				       <img  id="imageCode" onclick="checkcode()" style="margin-left:15px;vertical-align:bottom;" title="点击更换验证码" />
				  </li>
				</div>
				<div >
				  <p id="showMsg" style="padding:5px 0;text-align:center;color:red;height:4px;font-weight:200;font-size:10" ></p>
				</div>
             </div>
             
            </div>
           <div id="bottom"> <button type="submit" class="J_Submit" tabindex="5" onclick="login()">登&nbsp;录</button></div>
 </div>
 </div>
 <!-- 进度条，建议对于耗时长的操作才启用 -->
	<div id="loading">
		<div class="inputdiv" >
			<img  src="/BS56/img/loading.gif" style="padding-top: 20px; padding-left:72px;"/><br>
			<h3 style="padding-left: 35px;">&nbsp;&nbsp;&nbsp;&nbsp;用户登录中,请稍候......</h3>
		</div>
	 </div>
	 
</body>
</html>
