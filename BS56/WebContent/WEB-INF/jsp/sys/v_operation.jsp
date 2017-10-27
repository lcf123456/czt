<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>

<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/sys/operation.js"/>"></script>
  <head>
  </head>
  
  <body>

	
	
	<!-- 系统下拉框-->
	<div style="padding:5px;height:auto;border-top:1px solid #95B8E7" id="combox_id">
		  
	</div>
	
	    <body class="easyui-layout">
        <div data-options="region:'north'" style="height:auto;padding:5px;">所属系统：<input id="cmbox" name="onelevel" ></div>
        <div data-options="region:'west',title:'菜单'" style="width:210px;">
        	    <ul id="menu_tree" >
    			</ul>
        </div>
        <div data-options="region:'center'">
            <div class="datagrid-toolbar" id="toolbar" style="padding:5px;height:auto;border-top:1px solid #95B8E7">
				<form id="queryForm" style="margin:10;">
					<div style="margin-bottom:5px">
						<a href="#"id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newOp()">增加</a>
						<a href="#" id="updateBtn"class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editOp()">修改</a>
						<a href="#" id="delBtn"class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteOp()">删除</a>
					</div>
				</form>
			</div>
	
		<div style="padding:10" id="tabdiv">
			<table id="dataTabel"></table>
		</div>
        </div>
        
        <!-- 新增对话框 -->
	<div id="add-dlg" class="easyui-dialog" style="width:550px;height:300px;padding:10px 20px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<div class="ftitle"></div>
		<form  id="add-fm" method="post" action="" novalidate  >
		<input type="hidden" name="mid" id="mid">
		<input type="hidden" name="menucode" id="menucode">
			<div class="fitem">
				<label>功能点code:</label>
				<input name="code" class="easyui-validatebox" style="width:360px;"  required="true">
			</div>
			<br>
			<div class="fitem">
				<label>功能点名称:&nbsp;</label>
				<input name="name" class="easyui-validatebox" style="width:360px;"  required="true">
			</div>
			<br>
			<div id="text2" class="fitem">
				<label >备注信息:&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<textarea id="remarks"  rows="2" cols="42" name="remarks"></textarea>
			</div>
			<br>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveOp()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#add-dlg').dialog('close')">取消</a>
	</div>
	
	<!-- 修改对话框 -->
	<div id="edit-dlg" class="easyui-dialog" style="width:550px;height:300px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#edit-dlg-buttons">
		<div class="ftitle"></div>
		<form id="edit-fm" method="post" novalidate action="${contextPath}/sys/operation/operationUpdate.json" >
			<input type="hidden" id="id" name="id"/>
			<div class="fitem">
				<label>功能点code:</label>
				<input name="code" class="easyui-validatebox" style="width:360px;"  required="true">
			</div>
			<br>
			<div class="fitem">
				<label>功能点名称:&nbsp;</label>
				<input name="name" class="easyui-validatebox" style="width:360px;"  required="true">
			</div>
			<br>
			<div id="text2" class="fitem">
				<label >备注信息:&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<textarea id="remarks"  rows="2" cols="42" name="remarks"></textarea>
			</div>
			<br>
		</form>
	</div>
	<div id="edit-dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveEdit()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#edit-dlg').dialog('close')">取消</a>
	</div>
    </body>

	  
  </body>
</html>
