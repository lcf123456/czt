<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/sq/industrialdriver.js"/>"></script>
  <head>
  </head>

  <body>
  <!-- 查询
  <div id="searchDiv">
    <form id="queryForm" style="margin:10;text-align: center;">
		<table width="100%">
			<tr>
			<td>角色名称：<input class="easyui-textbox"  name="rolename"  data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchUser();},prompt:'请输入角色名称...'" style="width:450px;height:26px;">
            &nbsp;&nbsp;
            <a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search">清空</a></td>
			<td align="center"><a href="#" onclick="searchUser();" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
			</tr>
		</table>
	</form>
	</div>
   -->
	
	
	<!-- datagrid页面列表数据 -->
	<div style="padding:10" id="tabdiv">
		<table id="dataTabel"></table>
	</div>
	
	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;border-top:1px solid #95B8E7">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">
			<a href="#" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newIndustrialdriver()">增加</a>
			<a href="#" id="editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openEdit()">修改</a>
			<a href="#" id="delBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="industrialdriverDel()">删除</a>
			<input class="easyui-textbox"  name="drivername" id="drivernamex" data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchIndustrialdriver();},prompt:'请输入司机姓名...'" style="width:450px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>
	
	<!-- 新增对话框 -->
	<div id="add-dlg" class="easyui-dialog" style="width:450px;height:300px;padding:10px 20px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<div class="ftitle"></div>
		<form  id="add-fm" method="post" action="" novalidate  >
			<div class="fitem">
				<table width="100%" border="0" cellpadding="0" cellspacing="6">
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
               <tr><td colspan="4"  class="style2"><font color="blue">新增工业司机信息</font></td></tr>
               </table></td>
           </tr>          
     <tr>
    <td >
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="">     
        <tr align="center" >
           <td width="5%" height="20" align="left" nowrap>司机姓名：</td>
           <td width="14%" height="20" align="left" nowrap>
                <input name="drivername" id="drivername" class="easyui-validatebox tb" data-options="required:true,validType:'length[1,5]'"><strong><font color="red" >*</font></strong>
           </td>
           <tr align="center" >
		   <td width="5%"  height="20" align="left" nowrap>电话号码:</td>
           <td width="13%" height="20" align="left" nowrap>
          <input class="easyui-textbox" id="phonenum" name="phonenum" data-options="validType:'phonenum'" /></td>
       </tr>
			
			<tr align="center">
            <td width="5%"  height="20" align="left" nowrap>厂家名称:</td>
           <td width="13%" height="20" align="left" nowrap>
           <input name="factoryid" id="factoryid" class="easyui-combobox"style="width:auto;" data-option='' >
           <input type="hidden" name="factoryname" id="factoryname" />
           </td>
            </tr>
        </table>
	</td>
  </tr>
  </table>
			<br>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveAdd()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#add-dlg').dialog('close')">取消</a>
	</div>
	
	<!-- 修改对话框 -->
	<div id="edit-dlg" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#edit-dlg-buttons">
		<div class="ftitle"></div>
		<form id="edit-fm" method="post" action="${contextPath}/sq/industrialdriverUpdate.json" novalidate  >
			<div class="fitem"><input type=hidden name=id id=id>
				<table width="100%" border="0" cellpadding="0" cellspacing="6">
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
              <tr><td colspan="4"  class="style2"><font color="blue">修改工业司机信息</font></td></tr>
               </table></td>
                       
     <tr>
    <td >
	 <table width="90%" border="0" cellpadding="2" cellspacing="2" class="">
              <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>司机姓名：</td>
            <td width="14%" height="20" align="left" nowrap>
                   <input name="drivername" id="drivername" class="easyui-validatebox tb" style="width:auto" data-options="required:true,validType:'length[1,5]'" ><strong><font color="red" >*</font></strong>
            </td> 
           </tr>
             <tr align="center" >
		   <td width="5%"  height="20" align="left" nowrap>电话号码：</td>
           <td width="13%" height="20" align="left" nowrap>
          <input class="easyui-textbox" id="phonenum" name="phonenum" data-options="validType:'phonenum'" /></td>
       </tr>
		<br>
		<br>
			 <tr align="center">
            <td width="5%"  height="20" align="left" nowrap>厂家名称：</td>
           <td width="13%" height="20" align="left" nowrap>
           <input name="factoryid1" id="factoryid1" class="easyui-combobox"style="width:auto;" data-option=''>
           <input type="hidden" name="factoryname" id="factoryname1" />
           </td></tr>
         
  </table>
			</div>
			<br>
		</form>
	</div>
	<div id="edit-dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveEdit()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#edit-dlg').dialog('close')">取消</a>
	</div>
	
	<!-- 进度条，建议对于耗时长的操作才启用

	<div id="loading">
		<div class="inputdiv" >
			<img  src="/BS56/img/loading.gif" style="padding-top: 20px; padding-left:72px;"/><br>
			<h3 style="padding-left: 35px;">&nbsp;&nbsp;&nbsp;&nbsp;数据上传中,请稍候......</h3>
		</div>
	 </div>
	  -->
	  
	  	   <c:import url="/WEB-INF/jsp/pub/sessionPage.jsp?paramPage=industrialdriver"></c:import>
  </body>
</html>
