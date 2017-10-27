<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>

<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/produce/sorttroughinfo.js"/>"></script>
  </head>
  
  <body>
	
	
	<!-- datagrid页面列表数据 -->
	<div style="padding:10" id="tabdiv">
		<table id="dataTable"></table>
	</div>

	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;border-top:1px solid #95B8E7">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">通道类型
			<select name="troughtype" id="troughtype" class="easyui-combobox" style="width:auto;" data-option='selected:true;'>
			<option value="10" selected>分拣通道</option>
			<option value="20">重力式货架</option> 
			<option value="30">皮带机</option>
			<option value="40">分拣出口</option>
			</select>卷烟类型
			<select name="cigarettetype" id="cigarettetype" class="easyui-combobox" style="width:auto;" data-option='selected:true;'>
			<option value="20" selected>标准</option>
			<option value="30">异形</option>
			<option value="40">异形混合</option>
			</select>
			<input class="easyui-textbox"  name="param"  data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchSorttrough();},prompt:'请输入品牌名称/品牌代码...'" style="width:350px;height:24px;">
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

  </body>
</html>
