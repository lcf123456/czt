<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 调拨出库界面 -->
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/wms/shiporderanalyze.js"/>"></script>
  <head>
  <script type="text/javascript">
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
	<input type="hidden" name="datetype" id="datetype" value="10"></input>
		<div style="margin-bottom:5px">
		<a href="#" id="viewBtn" class="easyui-linkbutton" iconCls="icon-view" plain="true" onclick="viewD()">查看</a>
		
		订单日期从：<input name="searchtime" id="searchtime" class="easyui-datebox" style="width:90px" >&nbsp;
		到：<input name="searchtime2" id="searchtime2" class="easyui-datebox" style="width:90px" >&nbsp;&nbsp;&nbsp;
			<a href="#" onclick="searchData();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">查询</a>
		</div>
		</form>
	</div>
	
	    <!-- 1、查看--------------------------------------------------------------------------------------->
	<div id="view-dlg" class="easyui-dialog" style="width:960px;height:420px;padding:5px 10px;align:center;"
			 closed="true" buttons="#view-buttons"  data-options="modal:true,draggable:false">
		<form  id="view-fm" method="post" action="" novalidate  >
			<div >
			<tr>
			<td>
			<table>
			<tr>
		  		 	<td width="5%" height="20" align="left" nowrap>专卖证号：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="customerId" id="customerId" class="easyui-textbox"  style="width:120px"  readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>零售户：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="customername" id="customername" class="easyui-textbox" style="width:160px" readonly/>
		           </td>
		            <td width="5%" height="20" align="left" nowrap>订单日期：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="orderdatestr" id="orderdatestr" class="easyui-textbox" style="width:120px" readonly>
		           	</td>
		           	 <td width="5%"  height="20" align="left" nowrap>送货车组：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="routecode" id="routecode" class="easyui-textbox" style="width:120px" readonly/>
		           </td> 
	         </tr>
			</table>
			</td>
			</tr>
			</div>
			
			<div >
			<tr>
			<td>
			<table width="100%" id="linelist">
			</table>
			</td>
			</tr>
			</div>
		</form>
	 <div id="view-buttons">
		<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-cancel" onclick="javascript:$('#view-dlg').dialog('close')">关闭</a>
	</div>
	</div>
	
  </body>
</html>
