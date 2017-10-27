<%@page import="com.ztel.framework.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/account/vehicleamount.js"/>"></script>
  </head>
  <body>
	
	
	<!-- datagrid页面列表数据 -->
	<form id="refundForm" style="margin:10;" method="post">
	<div style="padding:10" id="tabdiv">
		<table id="dataTable"></table>
	</div>
	</form>
	
	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">
			<a href="#"  id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openNew()">录入</a>
			时间：<input id=orderdate name=orderdate class="easyui-datebox" style="width:100px;" >
			<a href="#" onclick="searchData();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">查询</a>
		</div>
		</form>
	</div>
	<!-- 进度条，建议对于耗时长的操作才启用
	<div id="loading">
		<div class="inputdiv" >
			<img  src="/BS56/img/loading.gif" style="padding-top: 20px; padding-left:72px;"/><br>
			<h3 style="padding-left: 35px;">&nbsp;&nbsp;&nbsp;&nbsp;数据上传中,请稍候......</h3>
		</div>
	 </div>-->
  </body>
</html>
