<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 调拨出库界面 -->
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/perform/deptmonthsum.js"/>"></script>
  <head>
  <script type="text/javascript">
  var deptid = "${userVo.deptid}";////出库类型(10订单出库 20 调拨出库)
  var username = "${userVo.username}";
  var deptname = "${userVo.deptname}";
  
  var obid=0;//出库单主表id
  var tmpscore = 0;
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
		<a href="#" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openCreate()">新增</a>
		<a href="#" id="editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">修改</a>
		<a href="#" id="postBtn" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="dopost()">提交</a>
		<a href="#" id="postBtn" class="easyui-linkbutton" iconCls="icon-myuser" plain="true" onclick="doAuditOne()">它评</a>
		<a href="#" id="postBtn" class="easyui-linkbutton" iconCls="icon-man" plain="true" onclick="doAuditTwo()">总评</a>
		<a href="#" id="delBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleterow()">删除</a>
		
		记录日期从：<input name="searchtime" id="searchtime" class="easyui-datebox" style="width:120px" >&nbsp;
		到：<input name="searchtime2" id="searchtime2" class="easyui-datebox" style="width:120px" >&nbsp;&nbsp;&nbsp;
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
		  		 	<td width="5%" height="20" align="left" nowrap>记录人：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="selfuser" id="selfuser" class="easyui-textbox"  style="width:130px" readonly >
		           	</td>
		            <td width="5%" height="20" align="left" nowrap>部门：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="belongdeptname" id="belongdeptname" class="easyui-textbox" style="width:120px" readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>考核日期：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="scoremonth" id="scoremonth" class="easyui-textbox" style="width:160px" readonly/>
		           </td>
	         </tr>
	         <tr>
	         <td width="5%" height="20" align="left" nowrap>自评分：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="selfscore" id="selfscore" class="easyui-textbox"  style="width:130px" readonly >
		           	</td>
		            <td width="5%" height="20" align="left" nowrap>部长姓名：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="deptmnguser" id="deptmnguser" class="easyui-textbox" style="width:120px" readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>部长评分：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="deptmngscore" id="deptmngscore" class="easyui-textbox" style="width:160px" readonly/>
		           </td>
	         </tr>
	         <tr>
		            <td width="5%" height="20" align="left" nowrap>总经理姓名：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="genmnguser" id="genmnguser" class="easyui-textbox" style="width:130px" readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>总经理评级：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="genmngrating" id="genmngrating" class="easyui-textbox" style="width:120px" readonly/>
		           </td>
		           
		  		 	<td width="5%" height="20" align="left" nowrap>状态：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="statusname" id="statusname" class="easyui-textbox"  style="width:160px" readonly >
		           	</td>
	         </tr>
	         <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>需注意工作事项：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="5">
		                <input name="matter" id="matter" class="easyui-textbox"  style="width:630px"  readonly>
		           	</td>
	         </tr>
	         <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>未完成事项说明：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="5">
		                <input name="unfinished" id="unfinished" class="easyui-textbox"  style="width:630px"  readonly>
		           	</td>
	         </tr>
	         <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>总经理总结：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="5">
		                <input name="summary" id="summary" class="easyui-textbox"  style="width:630px"  readonly>
		           	</td>
	         </tr>
			</table>
			</td>
			</tr>
			</div>
			
			<div >
			<tr>
			<td>
			<table width="100%" id="worklist">
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
