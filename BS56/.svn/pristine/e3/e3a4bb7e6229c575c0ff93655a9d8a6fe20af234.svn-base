<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Xgxt web demo</title>
  <!-- Set render engine for 360 browser -->
  <meta name="renderer" content="webkit">
  <!-- No Baidu Siteapp-->
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <!-- <link rel="icon" type="image/png" href="assets/i/favicon.png"> -->
  <!-- Add to homescreen for Chrome on Android -->
  <meta name="mobile-web-app-capable" content="yes">
  <!-- <link rel="icon" sizes="192x192" href="assets/i/app-icon72x72@2x.png"> -->
  <!-- Add to homescreen for Safari on iOS -->
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
  <!-- <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png"> -->
  <!-- Tile icon for Win8 (144x144 + tile color) -->
  <!-- <meta name="msapplication-TileImage" content="assets/i/app-icon72x72@2x.png"> -->
  <meta name="msapplication-TileColor" content="#0e90d2">
  <link rel="stylesheet" href="<spring:url value="/css/bootstrap.min.css" />">
  <link rel="stylesheet" href="<spring:url value="/css/demo.css" />">
</head>
<body>
<div class="container">

  <!-- Search form -->
  <div class="am-padding-sm">
    <form class="form-inline" id="orderForm" action="<spring:url value="/queryOrders" />" method="post" >
      <div class="form-group">
        <label for="exampleInputName2">用户名</label>
        <input type="text" class="form-control" id="userName" name="userName" placeholder="">
      </div>
      <div class="form-group">
        <label for="exampleInputName2">手机号</label>
        <input type="text" class="form-control" id="userMobile" name="userMobile" placeholder="">
      </div>   
      <button type="submit" class="btn btn-default" id="queryBtn">订单查询</button>
      <button type="submit" class="btn btn-default" id="queryJsonBtn" data-url="<spring:url value="/queryJsonOrders.json"/>">FORM->JSON</button>
      <button type="submit" class="btn btn-default" id="jsonRequestBtn" data-url="<spring:url value="/jsonRequest.json"/>">JSON->JSON</button>      
    </form>
  </div>
  
  <!-- Result table -->
  <div id="orderCtn" class="ajax-link-ctn">
  <c:import url="orderList.jsp"></c:import>
  </div>
  
  <hr size="1" />
  <input type="file" id="uploadFileBtn" name="myFile" class="btn btn-default" value="上传文件" data-url="<spring:url value="/uploadFile.json"/>">
  <hr size="1" />
  <a class="btn btn-default" id="excelBtn" href="<spring:url value="/excel"/>">Excel导出</button>

</div>

<script type="text/javascript" src="<spring:url value="/js/jquery-1.12.3.min.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/fileupload/jquery.ui.widget.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/fileupload/jquery.iframe-transport.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/fileupload/jquery.fileupload.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/demo.js"/>"></script>
<script type="text/javascript">

$("#queryBtn").click(function() {
	var form = $("#orderForm");
	var url = form.attr("action");
	var params = form.serialize();
	$.post(url, params).success(function(html) {
		var orderCtn = $("#orderCtn");
		orderCtn.html(html);
		registerEvent(orderCtn);
	}).fail(function() {
		alert("查询订单失败");
	});
	return false;
});

$("#queryJsonBtn").click(function() {
    var form = $("#orderForm");
    var url = $(this).data("url");
    var params = form.serialize();
    $.post(url, params).success(function(json) {
        $("#orderCtn").html(JSON.stringify(json));
    }).fail(function() {
        alert("查询JSON失败");
    });
    return false;	
});

$("#jsonRequestBtn").click(function() {
    var form = $("#orderForm");
    var url = $(this).data("url");
    var param = {};
    param.userName = $("#userName").val();
    param.userMobile = $("#userMobile").val();
    var jsonRequest = JSON.stringify(param);
    
    $.ajax({
        url: url,
        data: jsonRequest,
        type: "POST",
        dataType: "JSON",
        contentType: "application/json;charset=UTF-8"
    }).done(function (json) {
    	$("#orderCtn").html(JSON.stringify(json));
    }).fail(function (e) {
    	 alert("查询JSON失败");
    });
    return false;   
});

$("#uploadFileBtn").fileupload({
	//url in data-url or set it manually
	dataType: 'json',
	maxFileSize: 1024000*5, //5M
    done: function (e, data) {
    	alert(data.result.resultMessage);
    }
});


</script>
</body>
</html>


