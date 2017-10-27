<%@page import="com.ztel.framework.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/wms/refund.js"/>"></script>
  </head>
  <body>
	
	
	<!-- datagrid页面列表数据 -->
	<form id="refundForm" style="margin:10;" method="post">
	<div style="padding:10" id="tabdiv">
		<table id="dataTable"></table>
	</div>
	</form>
	
	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;border-top:1px solid #95B8E7">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">
			<a href="#"  id="refundBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="refund()">退货</a>
			车组选择：<input name="routecode" id="routecode" class="easyui-combobox"  >&nbsp;
			时间：<input id=begdate name=begdate class="easyui-datebox" style="width:100px;" >到<input id=enddate name=enddate class="easyui-datebox" style="width:100px;" >
			<input class="easyui-textbox"  name="keywd"  id=keywd data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){search();},prompt:'请输入专卖证/客户名称...'" style="width:200px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
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
	
<c:import url="/WEB-INF/jsp/pub/sessionPage.jsp?paramPage=WmsRefund"></c:import>	
  </body>
</html>
