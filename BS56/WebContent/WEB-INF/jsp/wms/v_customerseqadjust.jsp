<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>

<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/wms/customerseqadjust.js"/>"></script>
  </head>
  
  <body>
	
	
	<!-- datagrid页面列表数据 -->
	<div style="padding:10" id="tabdiv">
		<table id="dataTable"></table>
	</div>

	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;border-top:1px solid #95B8E7">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">
			<a href="#" id="lineBtn" class="easyui-linkbutton" iconCls="icon-mylorry" plain="true" onclick="editseq()">顺序调整</a>
		<a href="#" id="linesaveBtn" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveseq()">顺序保存</a>
		<a href="#" id="linesaveBtn" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="cancelseq()">取消调整</a>
			车组：<input name="routecode" id="routecode" class="easyui-combobox" style="width:90px;" data-options="">
			</input>
			批次：<input class="easyui-combobox" name="orderbatch" id="orderbatch" style="width:90px;" data-option='selected:true;'>
				</input>
			<input class="easyui-textbox"  name="code" id="code" data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchCustomer();},prompt:'请输入专卖证、零售户信息。。。。'" style="width:300px;height:24px;">
			<!-- <a href="#" onclick="searchCustomer();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">查询</a> -->
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
	<c:import url="/WEB-INF/jsp/pub/sessionPage.jsp?paramPage=Customer"></c:import>	

  </body>
</html>
