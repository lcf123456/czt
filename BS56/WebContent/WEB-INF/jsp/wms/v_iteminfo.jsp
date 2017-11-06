<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/wms/iteminfo.js"/>"></script>
  <head>
  <script type="text/javascript">
</script>
  </head>
  
  <body>
	
	<!-- datagrid页面列表数据 -->
	<div style="padding:10" id="tabdiv">
		<table id="dataTable"></table>
	</div>
	
	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;border-top:1px solid #95B8E7">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">
			<a href="#"id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newadd()">新增</a>
			<a href="#"id="delBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleterow()">删除</a>
			<input class="easyui-textbox"  name="cdtype"  data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchiteminfo();},prompt:'件码...'" style="width:180px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	
	
			<!-- 修改对话框 -->
	<div id="edit-dlg" class="easyui-dialog" style="width:800px;height:400px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#edit-dlg-buttons">
		<div class="ftitle"></div>
		
		<form  id="edit-fm" method="post" action="" novalidate  >
			<div class="fitem"><input type=hidden name=id id=id>
				<table width="100%" border="0" cellpadding="0" cellspacing="6">
                 <div class="fitem">
				<label>件码：</label>
				<input name="bigboxBar"id="bigboxBar" class="easyui-validatebox" style="width:373px;" >
			</div>
			<br>
			<div class="fitem">
				<label>满盘数量：</label>
				<input name="fullcount"id="fullcount" class="easyui-validatebox" style="width:373px;">
			</div>
			<br>
			<div class="fitem">
				<label>拆垛类型：</label>
                <select name="cdtype" id="cdtype" class="easyui-combobox " style="width:100px;"data-options="" >
                <option value="0" selected>人工拆垛</option>
						<option value="10" >自动拆垛</option>
						</select>
			</div>
			<br>      
    
      
  </table>
			</div>	
		</form>
	</div>
	<div id="edit-dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveEdit()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#edit-dlg').dialog('close')">取消</a>
	</div>
	</div>
<c:import url="/WEB-INF/jsp/pub/sessionPage.jsp?paramPage=Brandinfo"></c:import>
  </body>
</html>
