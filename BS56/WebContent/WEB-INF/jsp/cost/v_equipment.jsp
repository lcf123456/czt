<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/cost/equipment.js"/>"></script>
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
		<a href="#" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newadd()">新增</a>
		<a href="#"id="editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openEdit()">修改</a>
		<a href="#" id="delBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleterow()">删除</a>
			<input class="easyui-textbox"  name="equipname"  data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchEquipment();},prompt:'请输入设备名称/生产厂家...'" style="width:300px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
		<!-- 新增对话框 -->
				 <div id="add-dlg" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#dlg-buttons">
		<div class="ftitle"></div>

		<form  id="add-fm" method="post" action="" novalidate  >
			<div class="fitem"><!-- <input type="hidden" name="id" id="id"> -->
				<table width="100%" border="0" cellpadding="0" cellspacing="6">			                  
     <tr>
    <td >
				 <table width="90%" border="0" cellpadding="2" cellspacing="2" class="">
              <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>设备名称：</td>
            <td width="14%" height="20" align="left" nowrap>
                   <input name="equipname" id="equipname" class="easyui-validatebox tb" style="width:150px" data-options=""required="true" >
            </td>
             <td width="5%" height="20" align="left" nowrap>状态：</td>
            <td width="14%" height="20" align="left" nowrap>
                <select name="status" id="status" class="easyui-combobox " style="width:100px;"data-options="" >
                <option value="10" selected>在用</option>
						<option value="20" >置闲</option>
						<option value="30" >报废</option>
						</select>
				</td>
				</tr>		
  
          <tr align="center" >
           <td width="5%"  height="20" align="left" nowrap>品牌：</td>
         <td width="14%"  height="20" align="left" nowrap>
               <input name="brand" id="brand"   class="easyui-validatebox tb"style="width:150px" data-options=""  >
           </td>  
       
            <td width="5%" height="20" align="left" nowrap>型号：</td>
          <td width="14%" height="20" align="left" nowrap>
                <input name="model" id="model" class="easyui-validatebox tb" style="width:150px" data-options=""  >
           </td>
                </tr> 
       <tr align="center" >
           <td width="5%" height="20" align="left" nowrap>数量：</td>
          <td width="14%" height="20" align="left" nowrap>
                <input name="qty" id="qty" class="easyui-validatebox tb" style="width:150px" data-options=""  >
            </td>
    
            <td width="5%" height="20" align="left" nowrap>采购时间：</td>
            <td width="14%" height="20" align="left" nowrap>
                  <input name="purchasetime" id="purchasetime" class="easyui-datebox"style="width:auto;"data-option="">
            </td>
            </tr>
            <tr align="center" >
              <td width="5%" height="20" align="left" nowrap>生产厂家：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="manufacturer" id="manufacturer" class="easyui-validatebox tb" style="width:150px" data-options="" >
            </td>
           
           <td width="5%" height="20" align="left" nowrap>采购金额：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="purchaseamount" id="purchaseamount" class="easyui-validatebox tb" style="width:150px" data-options="" >
               </tr> 
       <tr align="center" >
       <td width="5%" height="20" align="left" nowrap>采购方式：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="purchasestyle" id="purchasestyle" class="easyui-validatebox tb" style="width:150px" data-options="" >
           </td> 
         
           <td width="5%" height="20" align="left" nowrap>设计能力：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="designability" id="designability" class="easyui-validatebox tb" style="width:150px" data-options="" >
           </td>  
             </tr>
       <tr align="center"> 
           <td width="5%" height="20" align="left" nowrap>规定使用年限：</td>
		           	<td width="14%" height="20" align="left"  nowrap>
		        <input name="servicelife" id="servicelife" class="easyui-validatebox tb" style="width:150px" data-options=""  >	           		
	           </td>
	<td width="5%" height="20" align="left" nowrap>实际能力：</td>
           <td width="14%" height="20" align="left"  nowrap>
           <input name="actualability" id="actualability" class="easyui-validatebox tb" style="width:150px" data-options="" >
           </td>  
   </tr>
  <tr align="center">  
           <td width="5%" height="20" align="left" nowrap>额定功率：</td>
		           	<td width="14%" height="20" align="left"  nowrap>
		        <input name="power" id="power" class="easyui-validatebox tb" style="width:150px" data-options=""  > 		
	</td>
	<td width="5%" height="20" align="left" nowrap>质保期：</td>
           <td width="14%" height="20" align="left"  nowrap>
           <input name="guaranteed" id="guaranteed" class="easyui-validatebox tb" style="width:150px" data-options="" >
           </td>  
            </tr>
   <tr align="center" >
           <td width="5%" height="20" align="left" nowrap>类型名称：</td>
       <td width="13%" height="20" align="left" colspan=3  nowrap>
		               <input name="lvl1" id="lvl1" class="easyui-combobox"style="width:130px;"  >
						<input name="lvl2" id="lvl2" class="easyui-combobox"style="width:130px;"  >
						<input name="typeid" id="typeid" class="easyui-combobox"style="width:130px;"  >
		           </td> 
           </tr> 		
  </table>
			</div>
			<br>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveNew()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#add-dlg').dialog('close')">取消</a>
	</div>
			<!-- 修改对话框 -->
	<div id="edit-dlg" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#edit-dlg-buttons">
		<div class="ftitle"></div>

		<form  id="edit-fm" method="post" action="" novalidate  >
			<div class="fitem"><input type="hidden" name="id" id="id">
				<table width="100%" border="0" cellpadding="0" cellspacing="6">			                  
     <tr>
    <td >
				 <table width="90%" border="0" cellpadding="2" cellspacing="2" class="">
              <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>设备名称：</td>
            <td width="14%" height="20" align="left" nowrap>
                   <input name="equipname" id="equipname" class="easyui-validatebox tb" style="width:150px" data-options="" readonly>
            </td>
   
           <td width="5%" height="20" align="left" nowrap>类型名称：</td>
         <td  width="14%"height="20" align="left" nowrap>
            <input name="typename" id="typename"   class="easyui-validatebox tb" style="width:150px"data-options="" readonly >
           </td>
           </tr> 
          <tr align="center" >
           <td width="5%"  height="20" align="left" nowrap>品牌：</td>
         <td width="14%"  height="20" align="left" nowrap>
               <input name="brand" id="brand"   class="easyui-validatebox tb"style="width:150px" data-options=""  >
           </td>  
       
            <td width="5%" height="20" align="left" nowrap>型号：</td>
          <td width="14%" height="20" align="left" nowrap>
                <input name="model" id="model" class="easyui-validatebox tb" style="width:150px" data-options="" >
           </td>
                </tr> 
       <tr align="center" >
           <td width="5%" height="20" align="left" nowrap>数量：</td>
          <td width="14%" height="20" align="left" nowrap>
                <input name="qty" id="qty" class="easyui-validatebox tb" style="width:150px" data-options=""  >
            </td>
    
            <td width="5%" height="20" align="left" nowrap>采购时间：</td>
            <td width="14%" height="20" align="left" nowrap>
                  <input name="purchasetime" id="purchasetime" class="easyui-datebox"style="width:auto;"data-option="">
            </td>
            </tr>
            <tr align="center" >
              <td width="5%" height="20" align="left" nowrap>生产厂家：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="manufacturer" id="manufacturer" class="easyui-validatebox tb" style="width:150px" data-options="" >
            </td>
           
           <td width="5%" height="20" align="left" nowrap>采购金额：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="purchaseamount" id="purchaseamount" class="easyui-validatebox tb" style="width:150px" data-options="" >
               </tr> 
       <tr align="center" >
       <td width="5%" height="20" align="left" nowrap>采购方式：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="purchasestyle" id="purchasestyle" class="easyui-validatebox tb" style="width:150px" data-options="" >
           </td> 
         
           <td width="5%" height="20" align="left" nowrap>设计能力：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="designability" id="designability" class="easyui-validatebox tb" style="width:150px" data-options="" >
           </td>  
             </tr>
       <tr align="center"> 
           <td width="5%" height="20" align="left" nowrap>规定使用年限：</td>
		           	<td width="14%" height="20" align="left"  nowrap>
		        <input name="servicelife" id="servicelife" class="easyui-validatebox tb" style="width:150px" data-options=""  >	           		
	           </td>
	<td width="5%" height="20" align="left" nowrap>实际能力：</td>
           <td width="14%" height="20" align="left"  nowrap>
           <input name="actualability" id="actualability" class="easyui-validatebox tb" style="width:150px" data-options="" >
           </td>  
   </tr>
  <tr align="center">  
           <td width="5%" height="20" align="left" nowrap>额定功率：</td>
		           	<td width="14%" height="20" align="left"  nowrap>
		        <input name="power" id="power" class="easyui-validatebox tb" style="width:150px" data-options=""  > 		
	</td>
	<td width="5%" height="20" align="left" nowrap>质保期：</td>
           <td width="14%" height="20" align="left"  nowrap>
           <input name="guaranteed" id="guaranteed" class="easyui-validatebox tb" style="width:150px" data-options="" >
           </td>  
            </tr>
  <tr align="center">  
   <td width="5%" height="20" align="left" nowrap>状态：</td>
            <td width="14%" height="20" align="left" nowrap>
                <select name="status" id="status" class="easyui-combobox " style="width:70px;"data-options="" >
                <option value="10" selected>在用</option>
						<option value="20" >置闲</option>
						<option value="30" >报废</option>
						</select>
				</td>
				</tr>		
  </table>
			</div>
			<br>
		</form>
	</div>
	<div id="edit-dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveEdit()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#edit-dlg').dialog('close')">取消</a>
	</div>
	</div>
<c:import url="/WEB-INF/jsp/pub/sessionPage.jsp?paramPage=Equipment"></c:import>
  </body>
</html>
