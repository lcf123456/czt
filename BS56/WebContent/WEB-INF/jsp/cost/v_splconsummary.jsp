<%@page import="com.ztel.framework.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/cost/splconsummary.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/datagrid-groupview.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/jquery.jqprint-0.3.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/jquery-migrate-1.2.1.min.js"/>"></script>
 <head>
  </head>
  <body>
	
	
	<!-- datagrid页面列表数据 -->
	<div style="padding:10" id="tabdiv">
		<table id="dataTable"></table>
	</div>
	
<form  id="qform" method="post" action=""   >
<input type=hidden name="param" id="param">
	</form>
	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;border-top:1px solid #95B8E7">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">
			时间:  <input id=begdate name=begdate class="easyui-datebox" style="width:100px;" value="<%=DateUtil.getyyyy_mm()+"-01"%>" > 到    <input id=enddate name=enddate class="easyui-datebox" style="width:100px;" value="<%=DateUtil.getyyyy_mm_dd()%>">
			部门名称：<input class="easyui-combobox" name="deptname" id="deptname" style="width:110px;" data-options="">	
			</input>
			<input class="easyui-textbox"  name="typename"  id="typename" data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchSPLConsummarylist();},prompt:'请输入物品名称/物资类别'" style="width:250px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
			<a href="#" id="printBtn" onclick="printpage();" class="easyui-linkbutton" iconCls="icon-myprinter" style="height:24px;">打印</a>
		</div>
		</form>
	</div>
	<!-- 打印窗口 -->
	<!--startprint-->
	<div id="print-dlg" class="easyui-dialog" style="width:800px;height:400px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#print-dlg-buttons">
		<div class="ftitle"></div>
		<form id="print-fm" method="post" action="" novalidate  >
		 <div class="fitem" id="printTable">
		<div id="print-dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-myprinter" onclick="$('#printTable').jqprint();$('#print-dlg').dialog('close'); ">打印</a>
	</div>
	</div>
<c:import url="/WEB-INF/jsp/pub/sessionPage.jsp?paramPage=SPLConsummary"></c:import>	
  </body>
</html>
