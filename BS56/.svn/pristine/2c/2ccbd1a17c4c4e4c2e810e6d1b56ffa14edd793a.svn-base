<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 预付款客户：预付车组 -->
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/account/prepayreturn.js"/>"></script>
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
		订单日期从：<input name="orderdateStart" id="orderdateStart" class="easyui-datebox" style="width:100px" >&nbsp;
		到<input name="orderdateEnd" id="orderdateEnd" class="easyui-datebox" style="width:100px" >&nbsp;
		<input class="easyui-textbox"  name="keywd" id="keywd" data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchData();},prompt:'请输入店名、专卖证、送货车组...'" style="width:450px;height:24px;">
		<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>


 <!-- 1、明细查看--------------------------------------------------------------------------------------->
	<div id="add-dlg" class="easyui-dialog" style="width:800px;height:420px;padding:5px 10px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<form  id="add-fm" method="post" action="" novalidate  >
			<div >
			<tr>
			<td>
			<table>
			<tr>
		  		 	<td width="5%" height="20" align="left" nowrap>专卖证：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="customerId" id="customerId" class="easyui-textbox" style="width:120px"  readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>客户名称：</td>
		           	<td width="23%" height="20" align="left" nowrap>
		               <input name="customername" id="customername" class="easyui-textbox" style="width:180px" readonly/>
		           </td>  
		            <td width="5%" height="20" align="left" nowrap>联系电话：</td>
		           	<td width="5%" height="20" align="left" nowrap colspan="3">
		                <input name="realshipaddressphone" id="realshipaddressphone" class="easyui-textbox" style="width:120px" readonly>
		           	</td>
	           </tr>
	           <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>车组：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="routecode" id="routecode" class="easyui-textbox" style="width:120px"  readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>金额：</td>
		           	<td width="23%" height="20" align="left" nowrap>
		               <input name="totalamount" id="totalamount" class="easyui-textbox" style="width:180px" readonly/>
		           </td>  
		            <td width="5%" height="20" align="left" nowrap>条数：</td>
		           	<td width="5%" height="20" align="left" nowrap colspan="3">
		                <input name="totalqty" id="totalqty" class="easyui-textbox" style="width:120px" readonly>
		           	</td>
	           </tr>
	            <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>订单日期：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="5">
		                <input name="orderdatestr" id="orderdatestr" class="easyui-textbox" style="width:120px"  readonly>
		           	</td>
	           </tr>
			</table>
			</td>
			</tr>
			</div>
		</form>
	 <div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-ok" onclick="javascript:$('#add-dlg').dialog('close')">关闭</a>
	</div>
	
	<div id="listtabdiv" style="border-top:1px solid #95B8E7">
		<table id="listdataTabel"></table>
	</div>
	</div>
	
  </body>
</html>
