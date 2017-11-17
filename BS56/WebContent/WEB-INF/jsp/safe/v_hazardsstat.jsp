<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>

<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/safe/hazardsstat.js"/>"></script>
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
		风险等级：<input  name="levelid" id="levelid" class="easyui-combobox" style="width:100px;" data-option='' >
		    相关部门：<input  name="deptid" id="deptid" class="easyui-combobox" style="width:100px;" data-option='' >
			危险源类别：<input class="easyui-combobox" name="hazardstype" id="hazardstype" style="width:150px;" data-options="">
			<input class="easyui-textbox"  name="hazards"id="keyword"  data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchHazardsStat();},prompt:'请输入危险源...'" style="width:350px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>
	
	
	


  </body>
</html>
