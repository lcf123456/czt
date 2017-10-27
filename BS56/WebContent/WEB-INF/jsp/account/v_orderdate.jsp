<%@page import="com.ztel.framework.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/account/orderdate.js"/>"></script>
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
		 <a href="#"id="editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openEdit()">修改</a>
			订单日期:  <input id=begdate name=begdate class="easyui-datebox" style="width:100px;" value="<%=DateUtil.getyyyy_mm()+"-01"%>" > 到    <input id=enddate name=enddate class="easyui-datebox" style="width:100px;" value="<%=DateUtil.getyyyy_mm_dd()%>">
			</input>
			<a href="#" onclick="searchOrderdate();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">查询</a>
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
					<!-- 修改对话框 -->
	<div id="edit-dlg" class="easyui-dialog" style="width:600px;height:200px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#edit-dlg-buttons">
		<div class="ftitle"></div>

		<form  id="edit-fm" method="post" action="" novalidate  >
			<div class="fitem"><input type="hidden" name="id" id="id">
				<table width="100%" border="0" cellpadding="0" cellspacing="6">
                 <div class="fitem">
				<label>订单日期：</label>
				<input name="orderdate"id="orderdate" class="easyui-datebox" style="width:200px;" readonly>
			</div>
			<br>
			<div class="fitem">
				<label>配送日期：</label>
				<input name="deliverydate"id="deliverydate" class="easyui-datebox" style="width:200px;">
			</div>
  </table>
			</div>	
		</form>
	</div>
	<div id="edit-dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveEdit()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#edit-dlg').dialog('close')">取消</a>
	</div>
	</div>
	
<c:import url="/WEB-INF/jsp/pub/sessionPage.jsp?paramPage=Timebydm"></c:import>	
  </body>
</html>
