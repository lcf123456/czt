<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 调拨出库界面 -->
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/wms/outbounddb.js"/>"></script>
  <head>
  <script type="text/javascript">
  var outboundtype = "${outboundtype}";////出库类型(10订单出库 20 调拨出库)
  var username = "${userVo.username}";
  var obid=0;//出库单主表id
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
		<a href="#" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openCreate()">录入</a>
		<a href="#" id="delBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleterow()">删除</a>
		
		出库日期从：<input name="searchtime" id="searchtime" class="easyui-datebox" style="width:120px" >&nbsp;
		到：<input name="searchtime2" id="searchtime2" class="easyui-datebox" style="width:120px" >&nbsp;&nbsp;&nbsp;
		货主：<input name="consignsorsearch" id="consignsorsearch" class="easyui-textbox" style="width:120px" >
			<a href="#" onclick="searchData();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">查询</a>
		</div>
		</form>
	</div>
	
    <!-- 1、新增--------------------------------------------------------------------------------------->
	<div id="add-dlg" class="easyui-dialog" style="width:960px;height:420px;padding:5px 10px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<form  id="add-fm" method="post" action="" novalidate  >
			<div >
			<tr>
			<td>
			<table>
			<tr>
		  		 	<td width="5%" height="20" align="left" nowrap>记录人：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="username_new" id="username_new" class="easyui-textbox"  style="width:120px"  >
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>出库日期：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="outtime_new" id="outtime_new" class="easyui-datetimebox" style="width:160px" />
		           </td>  
		            <td width="5%" height="20" align="left" nowrap>货主：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="consignsor_new" id="consignsor_new" class="easyui-textbox" style="width:120px" >
		           	</td>
	         </tr>
	          
			</table>
			</td>
			</tr>
			</div>
			
			<div >
			<tr >
            <td >
                  请输入查询： <input class="easyui-textbox"  name="keyword" id="keyword" data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchType();},prompt:'请输入卷烟名称或编码'" style="width:300px;height:24px;">
                  <a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
           </td>
           </tr>
			<tr>
			<td>
			<table width="100%" id="itemlist"><!-- 卷烟列表 -->
			</table>
			</td>
			</tr>
			<tr>
	    	<td >
		  	 <table width="100%" border="0" cellpadding="2" cellspacing="2" >
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>卷烟编码：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="cigarettecode" id="cigarettecode" class="easyui-textbox" style="width:160px" readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>卷烟名称：</td>
		           	<td width="23%" height="20" align="left" nowrap>
		               <input name="cigarettename" id="cigarettename" value="" class="easyui-textbox" style="width:80px" readonly/>
		           </td>  
		           <td width="5%"  height="20" align="left" nowrap>件条比率：</td>
		           	<td width="23%" height="20" align="left" nowrap>
		               <input name="jtsize" id="jtsize" value="" class="easyui-textbox" style="width:80px" readonly/>
		           </td>
		           	<td width="5%" height="20" align="left" nowrap>出库数量：</td>
		           	<td width="5%" height="20" align="left" nowrap>
		                <input name="qtynew" id="qtynew" class="easyui-numberbox" style="width:80px" data-options="validType:'length[1,10]'"><strong><font color="red" >*</font></strong>(件)
		           	</td>
	           </tr>
		 	 </table>
		     </td>
	  	   </tr>
	  	   
	  	   <tr>
			<td>
			<table width="100%" id="outboundlinelist">
			</table>
			</td>
			</tr>
			</div>
		</form>
	 <div id="dlg-buttons">
	    <a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-ok" onclick="toCKD()">生成出库单</a>
		<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-cancel" onclick="javascript:$('#add-dlg').dialog('close')">关闭</a>
	</div>
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
		  		 	<td width="5%" height="20" align="left" nowrap>记录人：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="createusername" id="createusername" class="easyui-textbox"  style="width:120px"  readonly>
		           	</td>
		            <td width="5%" height="20" align="left" nowrap>数量(件)：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="qty" id="qty" class="easyui-textbox" style="width:120px" readonly>
		           	</td>
	         </tr>
			<tr>
		           	<td width="5%"  height="20" align="left" nowrap>出库日期：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="outtime" id="outtime" class="easyui-textbox" style="width:120px" readonly/>
		           </td>  
		            <td width="5%" height="20" align="left" nowrap>货主：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="consignsor" id="consignsor" class="easyui-textbox" style="width:120px" readonly>
		           	</td>
	         </tr>
	          
			</table>
			</td>
			</tr>
			</div>
			
			<div >
			<tr>
			<td>
			<table width="100%" id="outboundlinelist1">
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
