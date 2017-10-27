<%@page import="com.ztel.framework.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/cost/splimp.js"/>"></script>
  </head>
  <script type="text/javascript">
	var showFlag="${showFlag}";
	</script>
  <body>
	
	
	<!-- datagrid页面列表数据 -->
	<div style="padding:10" id="tabdiv">
		<table id="dataTable"></table>
	</div>
	<form id="excelForm" style="margin:10;">
		<input type=hidden name=ids id="ids">
		<input type=hidden name=settlementflag id="settlementflag1">
	</form>
	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;border-top:1px solid #95B8E7">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">
			<a href="#" id= "settleBtn"  class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="SPLSettle();">结算</a>
			<a href="#"  id="excelBtn" class="easyui-linkbutton" iconCls="icon-myto_excel" plain="true" onclick="toExcel();">导出Excel</a>
			结算状态：<select name=settlementflag id=settlementflag class="easyui-combobox">
			<option value='0' selected>未结算</option>
			<option value='10'>已结算</option>
			</select>
			时间：<input id=begdate name=begdate class="easyui-datebox" style="width:100px;" >到<input id=enddate name=enddate class="easyui-datebox" style="width:100px;" >
			<input class="easyui-textbox"  name="param"  id=param data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchSPL();},prompt:'请输入物资/类型/供应商/记录人...'" style="width:250px;height:24px;">
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
	<c:import url="/WEB-INF/jsp/pub/sessionPage.jsp?paramPage=SPLSettle"></c:import>	
  </body>
</html>
