<%@page import="com.ztel.framework.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/account/operate_refund.js"/>"></script>
  </head>
  <body>
	
	
	<!-- datagrid页面列表数据 -->
	<form id="refundForm" style="margin:10;" method="post">
	<div style="padding:10" id="tabdiv">
		<table id="dataTable"></table>
	</div>
	</form>
	
	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">
			<a href="#"  id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openNew()">新增</a>
			<a href="#"  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delOperate()">删除</a>
			车组选择：<input name="routecode" id="routecode" class="easyui-combobox"  >&nbsp;
			时间：<input id=begdate name=begdate class="easyui-datebox" style="width:100px;" >到<input id=enddate name=enddate class="easyui-datebox" style="width:100px;" >
			<input class="easyui-textbox"  name="keywd"  id=keywd data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){search();},prompt:'请输入专卖证/客户名称...'" style="width:200px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>
	
	<!-- 订单查询toolbar  -->
	<form id="searchForm" style="margin:10;">
			<input type=hidden name="routecode"id="routecode2">
			<input type=hidden name="orderdatestr"id="orderdatestr">
	<div id="ordertoolbar" style="padding:5px;height:auto;">
		<div style="margin-bottom:5px">
			<input class="easyui-numberbox"  name="totalamount"  id=totalamount data-options="min:0,precision:2,prompt:'请输入订单金额...'" style="width:100px;height:24px;">
			<input class="easyui-textbox"  name="keywd"  id=keywd data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchOrder();},prompt:'请输入专卖证/客户名称...'" style="width:200px;height:24px;">
		</div>
	</div>
		</form>
	
	<div id="add-dlg" class="easyui-dialog" style="width:800px;height:420px;padding:5px 10px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<form  id="add-fm" method="post" action=""   >
		<input type=hidden name="ctype"id="ctype">
		<input type=hidden name="orderno"id="orderno">
		<input type=hidden name="status"id="status">
		<input type=hidden name="prestatus"id="prestatus">
			<div >
			<tr>
			<td>
			<table>
			<tr>
		           	<td width="5%"  height="20" align="left" nowrap>线路选择：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="routecode" id="routecode1" class="easyui-combobox" style="width:120px" required="required">
		           </td>  
		           	<td width="5%" height="20" align="left" nowrap>退货原因：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="reasonid" id="reasonid" class="easyui-combobox" style="width:220px"  required="required">
		           	</td>
	           </tr>
	           <tr>
			<tr>
		           	<td width="5%"  height="20" align="left" nowrap>订单日期：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input id=orderdate name=orderdate class="easyui-datebox" style="width:120px;" readonly>
		           </td>  
		           	<td width="5%" height="20" align="left" nowrap>核算日期：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input id=calcdate name=calcdate class="easyui-datebox" style="width:120px;" readonly>
		           	</td>
	           </tr>
	           <tr>
		            <td width="5%" height="20" align="left" nowrap>退货数量：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="quantity" id="quantity" class="easyui-numberbox" style="width:120px" value="0" readonly>
		           	</td>
		            <td width="5%" height="20" align="left" nowrap>退货金额：</td>
		           	<td width="5%" height="20" align="left" nowrap>
		                <input name="amount" id="amount" class="easyui-numberbox"data-options="min:0,precision:2" style="width:120px" value="0" readonly>
		           	</td>
	           </tr>
		 	 </table>
		     </td>
	  	   </tr>
	  	   <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
               <tr><td colspan="6"  class="style2"><b><font color="blue">订单选择</font></b></td></tr>
               </table></td>
           </tr>        
	  	   <tr>
			<td>
				  <table id="orderDataTable"></table>
			</td>
		</tr>
		<input type="hidden" name="neworderid" id="neworderid" ></input>
			</div>
		</form>
	 <div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" id="btn-ok" iconCls="icon-ok" onclick="saveAdd();">保存</a>
		<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-cancel" onclick="javascript:$('#add-dlg').dialog('close')">关闭</a>
	</div>
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
