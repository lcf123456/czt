<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 调拨出库界面 -->
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/perform/workcontent.js"/>"></script>
  <head>
  <script type="text/javascript">
  var outboundtype = "${outboundtype}";////出库类型(10订单出库 20 调拨出库)
  var username = "${userVo.username}";
  var deptname = "${userVo.deptname}";
  var deptid = "${userVo.deptid}";
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
		<a href="#" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openCreate()">新增</a>
		<a href="#" id="delBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleterow()">删除</a>
		
		<a href="#" id="lineBtn" class="easyui-linkbutton" iconCls="icon-mylorry" plain="true" onclick="editseq()">修改</a>
		<a href="#" id="linesaveBtn" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveseq()">保存</a>
		<a href="#" id="linesaveBtn" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="cancelseq()">取消</a>
		时间选择：<input name="searchtime" id="searchtime"  style="width:80px" >&nbsp;
		
		部门：<input name="searchdept" id="searchdept" class="easyui-textbox" style="width:100px" >
		类型：<input name="searchproperty" id="searchproperty" class="easyui-textbox" style="width:100px" >
		<input class="easyui-textbox"  name="keyword" id="keyword" data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchData();},prompt:'请输入人员、事项、标准'" style="width:200px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>
	
    <!-- 1、新增--------------------------------------------------------------------------------------->
	<div id="add-dlg" class="easyui-dialog" style="width:800px;height:420px;padding:5px 10px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<form  id="add-fm" method="post" action="" novalidate  >
			<div >
			<tr>
			<td>
			<table>
			<tr>
		  		 	<td width="5%" height="20" align="left" nowrap>工作时间：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="workdate_new" id="workdate_new" class="easyui-datebox"  style="width:120px"  >
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>类型：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="property_new" id="property_new" style="width:120px" />
		           </td>  
		            <td width="5%" height="20" align="left" nowrap>权重：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="weight_new" id="weight_new" class="easyui-numberbox"  data-options="precision:2,max:1,min:0" style="width:120px" >
		           	</td>
	         </tr>
	          <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>工作事项：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="5">
		                <input name="matters_new" id="matters_new" class="easyui-textbox"  style="width:630px"  >
		           	</td>
	         </tr>
	         <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>考评标准：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="5">
		                <input name="standard_new" id="standard_new" class="easyui-textbox"  style="width:630px"  >
		           	</td>
	         </tr>
	         
	         <tr>
	         <td width="5%"  height="20" align="left" nowrap>部门：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="deptid_new" id="deptid_new" style="width:120px" />
		           </td>  
		  		 	<td width="5%" height="20" align="left" nowrap>人员：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="3">
		                <input name="userid_new" id="userid_new" style="width:120px"  >
		           	</td>
	         </tr>
	         
			</table>
			</td>
			</tr>
			<tr>
				<td>
				<table width="100%" id="kwlist"><!-- 本月已有列表 -->
				</table>
				</td>
				</tr>
			</div>
			<input type="hidden" name="ctype" id="ctype" class="easyui-textbox" >
		</form>
		
	 <div id="dlg-buttons">
	    <a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-ok" onclick="toSave()">保存</a>
		<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-cancel" onclick="javascript:$('#add-dlg').dialog('close')">关闭</a>
	</div>
	</div>
	
	    <!-- 1、查看--------------------------------------------------------------------------------------->
	<div id="view-dlg" class="easyui-dialog" style="width:800px;height:320px;padding:5px 10px;align:center;"
			 closed="true" buttons="#view-buttons"  data-options="modal:true,draggable:false">
		<form  id="view-fm" method="post" action="" novalidate  >
			<div >
			<tr>
			<td>
			<table>
			<tr>
		  		 	<td width="5%" height="20" align="left" nowrap>工作时间：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="workdatestr" id="workdatestr" class="easyui-textbox"  style="width:120px" readonly >
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>类型：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="propertyname" id="propertyname" class="easyui-textbox" style="width:120px" readonly/>
		           </td>  
		            <td width="5%" height="20" align="left" nowrap>权重：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="weight" id="weight" class="easyui-textbox"  style="width:120px" readonly>
		           	</td>
	         </tr>
	          <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>工作事项：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="5">
		                <input name="matters" id="matters" class="easyui-textbox"  style="width:630px"  readonly>
		           	</td>
	         </tr>
	         <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>考评标准：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="5">
		                <input name="standard" id="standard" class="easyui-textbox"  style="width:630px"  readonly>
		           	</td>
	         </tr>
	         
	         <tr>
	         <td width="5%"  height="20" align="left" nowrap>部门：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="deptname" id="deptname" class="easyui-textbox" style="width:120px" />
		           </td>  
		  		 	<td width="5%" height="20" align="left" nowrap>人员：</td>
		           	<td width="14%" height="20" align="left" nowrap >
		                <input name="username" id="username" class="easyui-textbox" style="width:120px"  >
		           	</td>
		           	<td width="5%" height="20" align="left" nowrap>创建人员：</td>
		           	<td width="14%" height="20" align="left" nowrap >
		                <input name="createusername" id="createusername" class="easyui-textbox" style="width:120px"  >
		           	</td>
	         </tr>
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
