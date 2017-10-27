<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title></title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->
       <script type="text/javascript" src="<spring:url value="/easyui/jquery.min.js"/>"></script>
       <script type="text/javascript" src="<spring:url value="/easyui/jquery.easyui.min.js"/>"></script>
       <script type="text/javascript" src="<spring:url value="/easyui/locale/easyui-lang-zh_CN.js"/>"></script>
       <link rel="stylesheet" type="text/css" href="<spring:url value="/easyui/themes/default/easyui.css"/>">
       <link rel="stylesheet" type="text/css" href="<spring:url value="/easyui/themes/icon.css"/>">


<link rel="stylesheet" type="text/css" href="<spring:url value="/css/common.css"/>">
       
		<link rel="stylesheet" type="text/css" href="<spring:url value="/easyui/assets/css/bootstrap.min.css"/>">
		<link rel="stylesheet" type="text/css" href="<spring:url value="/easyui/assets/css/font-awesome.min.css"/>">
		<link rel="stylesheet" type="text/css" href="<spring:url value="/easyui/assets/css/css.css"/>">


<link rel="stylesheet" type="text/css" href="<spring:url value="/easyui/assets/css/ace.min.css"/>">
<link rel="stylesheet" type="text/css" href="<spring:url value="/easyui/assets/css/ace-rtl.min.css"/>">
<link rel="stylesheet" type="text/css" href="<spring:url value="/easyui/assets/css/ace-skins.min.css"/>">	


	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head>
	
	<script>
	var roleid = '${roleid}';
	var optype = '${optype}';
	var userlevel = '${userlevel}';//绩效考核中的考核授权传递用户级次参数
	$(function(){
		/*
		$("#btn").click(function(){
			$("#TB_overlayBG").css({
				//display:"block",height:$(document).height()
			});
			$(".eoffice").css({
				//left:($("body").width()-$(".eoffice").width())/2-20+"px",
				//top:($(window).height()-$(".eoffice").height())/2+$(window).scrollTop()+"px",
				display:"block"
			});
		});
		*/
		
	})
	
	</script>
	<script type="text/javascript" src="<spring:url value="/js/jsp/sys/chooseUser.js"/>"></script>
	
	<style>
	.eoffice{width:865px;left:1%;height:auto;z-index:10;background-color:#fff;border:1px #8FA4F5 solid;padding:1px;}

	</style>
<body id="chooseUser">
									<div class="eoffice" >
										<!---eoffice左侧选择部门--->
										<div class="tabs-below office_tab" >
											<div class="tab-content office_tabcontent" id="dept">
											</div>
										</div>
										
										<!---eoffice右侧选择姓名---->
										<div class="eoffice_right">
											<div class="eoffice_right_bg">
											<a href="javascript:void(0)" class="op_button close_dialog" data-placement="left" title="关闭窗口">
											<i class="fa fa-times" style="line-height:35px;"></i></a>											</div>
											
											
											<div class="row"></div>
											<div class="row">
												<div class="col-xs-12">
													<div class="allclear fr"><i class="icon-trash">清空</i></div>
													<div class="allselect fr"><i class="icon-check">全选</i></div>
												</div>
											</div>
											<div class="row">
												<div class="col-xs-12 user_list_main">
													<ul id="userUL">
													</ul>
												</div>
											</div>
											<div class="row"><div class="col-xs-12 eoffice_right_bg">已选择</div></div>
											<div class="row"><div class="col-xs-12"><div class="selected_user" id="userULSelected"></div></div></div>
										</div>
										
										<div id="cleaner"></div>
										<!---eoffice右侧选择姓名---->
										<div class="eoffice_buttonpane">
										  <div class="button_position fr">
											   <!-- <a href="#" class="btn-gray fr" id="button_cancel">取消</a> -->
											  <a href="#" class="btn-blue fr" id="button_ok">确定</a>
											  <div id="cleaner"></div>
										  </div>
										  
										</div>
									</div>
									
									
									<script type="text/javascript" src="<spring:url value="/easyui/assets/js/jquery.nicescroll.js"/>"></script>

												<script>
												var hasGetDept="";
													//隐藏div中的滚动条
													$(".user_list_main").niceScroll({   
													cursoropacitymax:0,  
													}); 
													$(".selected_user").niceScroll({   
													cursoropacitymax:0,  
													}); 
												</script>
											
												
												<script>
												function initUserList()
												{
														  //传输文字并添加<a>标签
														  $("li#user_list").click(function(){
															  var word = $(this).text();
															  word = $.trim(word);
															  var has = false;
															 // alert(word);
																  $(".selected_user li").each(function(){
																  //alert($(this).text());
																	  if($(this).text() == word){
																	  has=true;
																	  $(this).remove();}
																  });
															  //alert(has);
															  if(!has){
																  $(".selected_user").append ("<li>"+word+"<span class='shanchu'><i class='icon-close'></i></span></li>");
																  $(this).prepend("<a class='selected_tip hover' href='javascript:void(0)'><i class='icon-check-circle'></i></a> ");
																  }
																  else{$(this).children("a").remove();}
														  });
														  
														  
														  //取消选中、remove <a>标签
															  $(".selected_user").delegate("li", "click", function(){
																$(this).remove();
																var name = $(this).text();
																//alert(name);
																		$("li#user_list").each(function(){
																		if($.trim($(this).text()) == name){
																		$(this).children("a").remove();
																		}
																	});
															  });
															  
														  //全选、清空
														  $(".allselect").click(function(){
														  $(".selected_user").children("li").remove();
														  $("li#user_list").children("a").remove();
															$("li#user_list").each(function(){
															$(".selected_user").append ("<li>"+$.trim($(this).text())+"<span class='shanchu'><i class='icon-close'></i></span></li>");
															$(this).prepend("<a class='selected_tip' href='javascript:void(0)'><i class='icon-check-circle'></i></a> ");
															});
														  });
														  $(".allclear").click(function(){
														  $(".selected_user").children("li").remove();
														  $("li#user_list").children("a").remove();
														  });
														  
														  
														  $("li#user_list").mouseover(function(){
																$(this).find(".selected_tip").addClass("hover");
															});
															$("li#user_list").mouseout(function(){
																$(this).find(".selected_tip").removeClass("hover");
															});
												}
													$(document).ready(initUserList);
												</script>

</body>
</html>
