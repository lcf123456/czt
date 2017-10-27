<%@page import="com.ztel.framework.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/account/prepaycustomer.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/datagrid-groupview.js"/>"></script>
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
			<a href="#" id= "newBtn"  class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openNew()">新增</a>
			<a href="#" id= "newBtn"  class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openPLNew()">添加</a>
			<a href="#"  id="refundBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delPrepayCustomer()">删除</a>
			<input class="easyui-textbox"  name="param"  id=param data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){search();},prompt:'请输入新老专卖证号/店名...'" style="width:240px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>
	
	<!-- 1、新增对话框 --------------------------------------------------------------------------------------->
	<div id="add-dlg" class="easyui-dialog" style="width:550px;height:250px;padding:10px 20px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<div class="ftitle"></div>
		<form  id="add-fm" method="post" action=""   >
		<input type=hidden name='customertype' id='customertype'>
		<input type=hidden name='prepayflag' id='prepayflag'>
		<input type=hidden name='delstatus' id='delstatus'>
		<div class="fitem">
				<label>客户名称</label>
				<input name="name" id="name" value="" class="easyui-validatebox tb" style="width:373px;" data-options="required:true,validType:'length[0,100]'" />
			</div>
			<br>
			<div class="fitem">
				<label>联系人</label>
				<input name="contact" id="contact" value="" class="easyui-validatebox tb"  data-options="required:true,validType:'length[0,10]'" />
			</div>
			<br>
			<div class="fitem">
				<label>联系电话</label>
				<input name="contactphone" id="contactphone" class="easyui-numberbox" data-options="required:true,min:0" >
			</div>
			<br>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveAdd()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#add-dlg').dialog('close');">取消</a>
	</div>

    <!-- end 1、新增对话框 -->
	
	
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
			<input class="easyui-textbox"  name="param"  id=param data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchCustomer();},prompt:'请输入新老专卖证号/店名...'" style="width:240px;height:24px;">
		</div>
		</form>
	</div>
	</div>
	<div id="customerdlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="savePLAdd()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#pladd-dlg').dialog('close');">取消</a>
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
