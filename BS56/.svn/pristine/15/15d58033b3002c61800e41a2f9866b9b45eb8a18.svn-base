<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 装卸效率 -->
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/gis/truckseqgroupefficiency.js"/>"></script>
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
		装卸时间从：<input name="searchtime" id="searchtime" class="easyui-datebox" style="width:120px" >&nbsp;
		到：<input name="searchtime2" id="searchtime2" class="easyui-datebox" style="width:120px" >&nbsp;&nbsp;&nbsp;
		<a href="#" onclick="searchData();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">查询</a>
		</div>
		</form>
	</div>
	
    <!-- 1、明细查看--------------------------------------------------------------------------------------->
	<div id="add-dlg" class="easyui-dialog" style="width:960px;height:420px;padding:5px 10px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
	
	<div style="padding:10" id="tabdiv2">
		<table id="detaildataTabel"></table>
	</div>
			
	 <div id="dlg-buttons" style="padding:5px;height:auto;border-top:1px solid #95B8E7">
		<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-ok" onclick="javascript:$('#add-dlg').dialog('close')">关闭</a>
	</div>
	</div>
	

  </body>
</html>
