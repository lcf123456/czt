<%@page import="com.ztel.framework.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/account/billcustomer.js"/>"></script>
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
			<a href="#" id= "newBtn"  class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openPLNew()">新增</a>
			<a href="#"  id="refundBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delBillCustomer()">删除</a>
			车组：<input name="routecode" id="routecode" class="easyui-combobox" style="width:90px;" data-options="" >
			发票类型：
			<select id="billtype1" class="easyui-combobox" name="billtype" style="width:auto;">
			    <option value="" selected>--请选择--</option>
			    <option value="10" >普通发票</option>
			    <option value="20">增值税发票</option>
			</select>
			<input class="easyui-textbox"  name="param"  id=param data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){search();},prompt:'请输入新老专卖证号/联系人/店名...'" style="width:240px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>
	
	<!-- 添加零售户到一级客户下 -->
	<!-- datagrid页面列表数据 -->
	<div id="pladd-dlg" class="easyui-dialog" style="width:850px;height:450px;padding:10px 20px;align:center;"
			 closed="true" buttons="#customerdlg-buttons"  data-options="modal:true,draggable:false">
	<form  id="pladd-fm" method="post" action=""   >
	<div style="padding:10" id="tabdiv">
		<table id="customerTable"></table>
	</div>
	<input type=hidden name="prepayparentid" id="prepayparentid">
	</form>
	<!-- 查询-->
	<div id="customerToolbar" style="padding:5px;height:auto;">
	<form id="searchForm" style="margin:10;">
		<div style="margin-bottom:5px">
			车组：<input name="routecode" id="routecode1" class="easyui-combobox" style="width:90px;" data-options="" >
			<input class="easyui-textbox"  name="param"  id=param data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchCustomer();},prompt:'请输入新老专卖证号/店名...'" style="width:240px;height:24px;">
			
			发票类型：
			<select id="billtype" class="easyui-combobox" name="billtype" style="width:auto;">
			    <option value="10" selected>普通发票</option>
			    <option value="20">增值税发票</option>
			</select>
			<font color=red><B>提交前请选择发票客户的发票类型</B></font>
		</div>
		</form>
	</div>
	</div>
	<div id="customerdlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="savePLAdd()">提交</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#pladd-dlg').dialog('close');">关闭</a>
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
