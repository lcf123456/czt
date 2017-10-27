<%@page import="com.ztel.framework.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/account/invoiceexport.js"/>"></script>
  </head>
  <body>
	
	
	<!-- datagrid页面列表数据 -->
	<div style="padding:10" id="tabdiv">
		<table id="dataTable"></table>
	</div>
	
	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">
			<a href="#"  id= "viewBtn" class="easyui-linkbutton" iconCls="icon-view" plain="true" onclick="openView()">查看</a>
			时间:<input id=begdate name=begdate class="easyui-datebox" style="width:100px;" >到<input id=enddate name=enddate class="easyui-datebox" style="width:100px;" >
			发票类型：
			<select id="billtype1" class="easyui-combobox" name="billtype" style="width:auto;">
			    <option value="10" selected>普通发票</option>
			    <option value="20">增值税发票</option>
			</select>
			<input class="easyui-textbox"  name="param"  id=param data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchCust();},prompt:'请输入专卖证/零售户名称...'" style="width:240px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
			<a href="#" id="exportBtn" onclick="exportFormToTxt();" class="easyui-linkbutton" iconCls="icon-toexcel" style="height:24px;">导出</a>
		</div>
		</form>
	</div>
	
	
	<!-- 导出对话框 -->
	<form id="exportForm">
		<input type=hidden name="ids" id="ids">
		<input type=hidden name="billtype" id="billtype">
		<input type=hidden name="begdate" id="exportBegdate">
		<input type=hidden name="enddate" id="exportEnddate">
	<div id="view-dlg" class="easyui-dialog" style="width:750px;height:450px;padding:10px 20px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<div class="ftitle"></div>
		<table id="viewDataTable"></table>
	</div>
	</form>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#view-dlg').dialog('close')">关闭</a>
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
