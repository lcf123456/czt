<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/wms/brandinfo.js"/>"></script>
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
			<a href="#"id="editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openEdit()">修改</a>
			<input class="easyui-textbox"  name="itemname" id="itemname1" data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchbrandinfo();},prompt:'件码/商品名称/商品编码...'" style="width:250px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	
	
			<!-- 修改对话框 -->
	<div id="edit-dlg" class="easyui-dialog" style="width:600px;height:300px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#edit-dlg-buttons">
		<div class="ftitle"></div>
		
		<form  id="edit-fm" method="post" action="" novalidate  >
			<div class="fitem"><input type=hidden name=id id=id>
				<table width="100%" border="0" cellpadding="0" cellspacing="6" class="">
                  <tr align="center" >
            
            <td width="5%" height="20" align="left" nowrap>商品名称：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="itemname" id="itemname"  class="easyui-validatebox tb "style="width:150px" >
            </td>
           <td width="5%" height="20" align="left" nowrap>商品简称：</td>
           <td  width="14%"height="20" align="left" nowrap>
            <input name="shortname" id="shortname"   class="easyui-validatebox tb" style="width:150px"data-options="" >
           </td>
           </tr>
             <tr align="center" >
            
            <td width="5%" height="20" align="left" nowrap>商品编号：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="itemno" id="itemno"  class="easyui-validatebox tb "style="width:150px"  >
            </td>
           <td width="5%" height="20" align="left" nowrap>件码：</td>
           <td  width="14%"height="20" align="left" nowrap>
            <input name="bigboxBar" id="bigboxBar"   class="easyui-validatebox tb" style="width:150px" >
           </td>
           </tr>
            <tr align="center" >
            
            <td width="5%" height="20" align="left" nowrap>满盘数量：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="fullcount" id="fullcount"  class="easyui-validatebox tb "style="width:150px" >
            </td>
            <td width="5%" height="20" align="left" nowrap>规格：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="spec" id="spec"  class="easyui-validatebox tb "style="width:150px" >
            </td>
            </tr>
            <tr align="center" >
           <td width="5%" height="20" align="left" nowrap>拆垛类型：</td>
            <td width="14%" height="20" align="left" nowrap>
                <select name="cdtype" id="cdtype" class="easyui-combobox tb" style="width:100px" data-options="" >
            <option value="10" selected>自动拆垛</option>
				<option value="0" >人工拆垛</option>
				</select>
            </td>
           </tr>
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
