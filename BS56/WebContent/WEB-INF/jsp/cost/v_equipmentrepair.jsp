<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/cost/equipmentrepair.js"/>"></script>
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
			<input class="easyui-textbox" id=equipname name=equipname  data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchEquipmentRepair();},prompt:'请输入设备名称/维修内容...'" style="width:300px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
			<!-- 新增对话框 -->
				 <div id="add-dlg" class="easyui-dialog" style="width:600px;height:380px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#dlg-buttons">
		<div class="ftitle"></div>

		<form  id="add-fm" method="post" action="" novalidate  >
			<div class="fitem"><!-- <input type="hidden" name="id" id="id"> -->
				<table width="100%" border="0" cellpadding="0" cellspacing="6">	
				 <tr>
           <td height="120px">
           <div style="WIDTH: 100%; HEIGHT: 120px; BACKGROUND-COLOR: transparent; OVERFLOW: scroll; scrollbar-face-color:#c1d9f5; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff">
          <table width="100%" class="gridtable">
          <tr>
			  <td colspan="5">
                     <input class="easyui-textbox"  name="condname" id="condname" data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchEquipname1();},prompt:'请输入id或设备名称...'" style="width:450px;height:24px;">
                     </td>
					                      
            </tr>
          </table>
           </div>
           </td>
           </tr>			                  
     <tr>
    <td >
				 <table width="90%" border="0" cellpadding="2" cellspacing="2" class="">
              <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>设备名称：</td>
            <td width="14%" height="20" align="left" nowrap>
                   <%-- <input name="equipid" id="equipid" class="easyui-combobox" style="width:150px" data-options=" 
                    valueField: 'id',  
                  textField: 'equipname',  
                   value:'222', 
                url: '<%=basePath %>/cost/equipment/getEquipmentCombobox.json'"><strong><font color="red" >*</font></strong> 
               --%> 
               <input type="hidden" id="equipid2" name="equipid"/> 
               <input name="equipname" id="equipname2" class="easyui-validatebox tb" style="width:150px"data-options="required:true"> <strong><font color="red" >*</font></strong>
            </td>
   
           <td width="5%" height="20" align="left" nowrap>维修原因：</td>
         <td  width="14%"height="20" align="left" nowrap>
            <input name="reason" id="reason"   class="easyui-validatebox tb" style="width:150px"data-options=""  >
           </td>
           </tr> 
          <tr align="center" >
           <td width="5%"  height="20" align="left" nowrap>维修内容：</td>
         <td width="14%"  height="20" align="left" nowrap>
               <input name="contentlist" id="contentlist"   class="easyui-validatebox tb"style="width:150px" data-options=""  >
           </td>  
    
            <td width="5%" height="20" align="left" nowrap>维修时间：</td>
            <td width="14%" height="20" align="left" nowrap>
                  <input name="repairtime" id="repairtime" class="easyui-datebox"style="width:auto;"data-option="">
            </td>
            </tr>
            <tr align="center" >
              <td width="5%" height="20" align="left" nowrap>消耗配件：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="spareparts" id="spareparts" class="easyui-validatebox tb" style="width:150px" data-options="" >
            </td>
           
           <td width="5%" height="20" align="left" nowrap>维修金额：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="amount" id="amount" class="easyui-validatebox tb" style="width:150px" data-options="" >
               </tr> 
       <tr align="center" >
       <td width="5%" height="20" align="left" nowrap>维修结果：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="repairresult" id="repairresult" class="easyui-validatebox tb" style="width:150px" data-options="" >
           </td> 
            
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
	<div id="edit-dlg" class="easyui-dialog" style="width:600px;height:300px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
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
   
           <td width="5%" height="20" align="left" nowrap>维修原因：</td>
         <td  width="14%"height="20" align="left" nowrap>
            <input name="reason" id="reason"   class="easyui-validatebox tb" style="width:150px"data-options=""  >
           </td>
           </tr> 
          <tr align="center" >
           <td width="5%"  height="20" align="left" nowrap>维修内容：</td>
         <td width="14%"  height="20" align="left" nowrap>
               <input name="contentlist" id="contentlist"   class="easyui-validatebox tb"style="width:150px" data-options=""  >
           </td>  
    
            <td width="5%" height="20" align="left" nowrap>维修时间：</td>
            <td width="14%" height="20" align="left" nowrap>
                  <input name="repairtime" id="repairtime" class="easyui-datebox"style="width:auto;"data-option="">
            </td>
            </tr>
            <tr align="center" >
              <td width="5%" height="20" align="left" nowrap>消耗配件：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="spareparts" id="spareparts" class="easyui-validatebox tb" style="width:150px" data-options="" >
            </td>
           
           <td width="5%" height="20" align="left" nowrap>维修金额：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="amount" id="amount" class="easyui-validatebox tb" style="width:150px" data-options="" >
               </tr> 
       <tr align="center" >
       <td width="5%" height="20" align="left" nowrap>维修结果：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="repairresult" id="repairresult" class="easyui-validatebox tb" style="width:150px" data-options="" >
           </td> 
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
<c:import url="/WEB-INF/jsp/pub/sessionPage.jsp?paramPage=Equipmentrepair"></c:import>
  </body>
</html>
