<%@page import="com.ztel.framework.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/cost/splstock.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/jquery.jqprint-0.3.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/jquery-migrate-1.2.1.min.js"/>"></script>
<head>
<script type="text/javascript">
	var showFlag="${showFlag}";
	</script>
  </head>
  <body>
	
	
	<!-- datagrid页面列表数据 -->
	<div style="padding:10" id="tabdiv">
		<table id="dataTable">
		<thead>
            <tr>
                <th data-options="field:'typename',width:30,align:'center'" rowspan="2">物资类型</th>
                <th data-options="field:'maintypename',width:30,align:'center'" rowspan="2">物资归属</th>
                <th data-options="field:'unit',width:20,align:'center'" rowspan="2">单位</th>
                <th colspan="2">上月库存</th>
                <th colspan="2">本月收料</th>
                <th colspan="2">本月发料</th>
                <th colspan="2">本月库存</th>
            </tr>
            <tr>
                <th data-options="field:'lastqty',width:30,align:'center'">数量</th>
                <th data-options="field:'lastamount',width:30,align:'center'">金额</th>
                <th data-options="field:'impqty',width:30,align:'center'">数量</th>
                <th data-options="field:'impamount',width:30,align:'center'">金额</th>
                <th data-options="field:'consumeqty',width:30,align:'center'">数量</th>
                <th data-options="field:'consumeamount',width:30,align:'center'">金额</th>
                <th data-options="field:'currqty',width:30,align:'center'">数量</th>
                <th data-options="field:'curramount',width:30,align:'center'">金额</th>
            </tr>
        </thead>
		</table>
	</div>
	
<form  id="excelForm" method="post" action=""   >
<input type=hidden name="param" id="param1">
<input type=hidden name="maintypeid" id="maintypeid1">
<input type=hidden name="begdate" id="begdate1">
<input type=hidden name="enddate" id="enddate1">
	</form>
	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;border-top:1px solid #95B8E7">
	<form id="queryForm" style="margin:10;" method='post'>
		<div style="margin-bottom:5px">
			时间:  <input id=begdate name=begdate class="easyui-datebox" style="width:100px;" > 到    <input id=enddate name=enddate class="easyui-datebox" style="width:100px;">
			<input name="maintypeid" id="maintypeid" class="easyui-combobox"style="width:auto;"  >
			<input class="easyui-textbox"  name="param"  id=param data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchSPL();},prompt:'请输入物资类型...'" style="width:250px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
			<a href="#" id="printBtn" onclick="openStockPagePrint();" class="easyui-linkbutton" iconCls="icon-myprinter" style="height:24px;">打印</a>
			<!--  <a href="#" id="toexcelBtn" onclick="toExcel();" class="easyui-linkbutton" iconCls="icon-myto_excel" style="width:84px;height:24px;">导出excel</a> -->
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
  </body>
</html>
