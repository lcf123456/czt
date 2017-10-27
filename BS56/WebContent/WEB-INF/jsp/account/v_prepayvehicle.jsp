<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 预付款客户：预付车组 -->
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/account/prepayvehicle.js"/>"></script>
  <head>
  <script type="text/javascript">
  var tsid;//id主键
  var groupcode;//装卸组code
</script>
  </head>
  
  <body>
	
	<!-- datagrid页面列表数据 -->
	<div style="padding:10" id="tabdiv">
		<table id="dataTabel"></table>
	</div>
	
<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;border-top:1px solid #95B8E7">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">
		所属部门：<input name="deptid" id="deptid" class="easyui-textbox" style="width:160px" >
		审核人员：<input name="calcid" id="calcid" class="easyui-textbox" style="width:160px" >
		订单日期：<input name="orderdate" id="orderdate" class="easyui-datebox" style="width:120px" >&nbsp;
		<a href="#" onclick="searchData();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">查询</a>
		<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		<input class="easyui-textbox"  id=excelBtn name="toexcel" id="toexcel"  data-options="buttonText:'导出excel',buttonIcon:'icon-myto_excel',onClickButton:function(){exportxls();}," style="width:80px;height:24px;">
		</div>
		</form>
	</div>

  </body>
</html>
