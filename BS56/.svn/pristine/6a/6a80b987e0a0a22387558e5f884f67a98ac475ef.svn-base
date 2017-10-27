<%@ page language="java" contentType ="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>
<%@ page import="com.ztel.framework.util.DateUtil" %>
<html>

<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/gis/groupinfo.js"/>"></script>
  <head>
  <script type="text/javascript">
    var username = "${userVo.username}";
    var userid = "${userVo.id}";
    var deptid = "${userVo.deptid}";
   // var deptid1 = "${userVo.deptid}";
</script>
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
		<table id="dataTable"></table>
	</div>
	
	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;border-top:1px solid #95B8E7">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">
			<a href="#" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newadd()">新增</a>
			<!-- a href="#" id="editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openEdit()">修改</a -->
			<a href="#" id="delBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleterow()">删除</a>
		</div>
		</form>
	</div>
	
		<!-- 新增对话框 -->
	<div id="add-dlg" class="easyui-dialog" style="width:550px;height:550px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#add-dlg-buttons">
		<div class="ftitle"></div>
		<form id="add-fm" method="post" action="" novalidate  >
			<div class="fitem"><input type=hidden name=id id=id>
				<table width="100%" border="0" cellpadding="0" cellspacing="6">
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
              <tr><td colspan="4"  class="style2"><font color="blue">新增装卸组信息</font></td></tr>
               </table></td>
           </tr>          
     <tr>
    <td >
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="">
              <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>装卸组编号：</td>
            <td width="24%" height="20" align="left" nowrap>
                     <input name="groupcode" id="groupcode"  type="text" style="ime-mode:disabled" class="validate[required]" data-errormessage="装卸组CODE不能为空，且不能多于20字符" maxlength="25" placeholder="不能有汉字" >
                    </td> 
            
             <td width="5%"  height="20" align="left" nowrap>装卸组名称：</td>
           <td width="25%" height="20" align="left"  nowrap>
               <input type="hidden" name="membername" id="membername">
               <input name="groupname" id="groupname"   class="easyui-validatebox tb" data-options="multiline:true,validType:'length[1,50]'"  data-options="" required="true">
           </td>  </tr>   
    </table>
	</td>
  </tr>
  <div id="splchoose-div">
			<tr>
              <td> 
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
               <tr><td colspan="8"  class="style2"><font color="blue">选择人员:</font></td></tr>
               </table></td>
           </tr>  
        <tr>
           <td height="120px">
           <div style="WIDTH: 100%; HEIGHT: 200px; BACKGROUND-COLOR: transparent; OVERFLOW: scroll; scrollbar-face-color:#c1d9f5; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff">
          <table width="100%" class="gridtable">
          <tr>
			  <td colspan="3">部门：
                    <input name="deptid" id="deptid" class="easyui-combobox"style="width:auto;"  >
               <button name="groupuser" type="button"  id="grantBtn" iconCls="icon-mygroup" onClick="saveNew()" >添加人员</button> </td> 
                     </td>
            </tr>
            <tr>
            <th>id</th>
            <th>姓名</th>
             <th>选择</th>
            </tr>
          </table>
           </div>
           </td>
           </tr>
           </div>
            <div class="fitem">
				<table width="100%" border="0" cellpadding="0" cellspacing="6">
  <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
  <tr><td colspan="4"  class="style2"><font color="blue">装卸组人员</font>
  <button name="deluse"  id="deluseBtn" type="button" iconCls="icon-remove" onclick="deleteuser()">删除</button>
  </td></tr>
                 </table></td>
</table>
			</div>
				<div style="padding:8" id="tabdiv1">
		<table id="dataTable1" ShowHeader="false"></table>
		</div>
  </table>
			</div>
			<br>
		</form>
	</div>
	<div id="add-dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="newsave()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="newclose()">取消</a>
	</div>

	
		<!-- 修改对话框 -->
	<div id="edit-dlg" class="easyui-dialog" style="width:550px;height:550px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#edit-dlg-buttons">
		<div class="ftitle"></div>
		<form id="edit-fm" method="post" action="" novalidate  >
			<div class="fitem"><input type=hidden name=id id=id>
				<table width="100%" border="0" cellpadding="0" cellspacing="6">
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
              <tr><td colspan="4"  class="style2"><font color="blue">修改装卸组信息</font></td></tr>
               </table></td>
           </tr>          
     <tr>
    <td >
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="">
             <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>装卸组编号：</td>
            <td width="24%" height="20" align="left" nowrap>
            <input name="groupcode" id="groupcode"   class="easyui-validatebox tb" data-options="multiline:true,validType:'length[1,25]'"  data-options="" Readonly>
                    </td> 
             <td width="5%"  height="20" align="left" nowrap>装卸组名称：</td>
           <td width="25%" height="20" align="left"  nowrap>
               <input name="groupname" id="groupname"   class="easyui-validatebox tb" Readonly>
           </td>  </tr>        
        </table>
	</td>
  </tr>
  <div id="splchoose-vdiv1">
			<tr>
              <td> 
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
               <tr><td colspan="8"  class="style2"><font color="blue">选择人员:</font></td></tr>
               </table></td>
           </tr>  
        <tr>
           <td height="120px">
           <div style="WIDTH: 100%; HEIGHT: 200px; BACKGROUND-COLOR: transparent; OVERFLOW: scroll; scrollbar-face-color:#c1d9f5; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff">
          <table width="100%" class="gridtable">
          <tr>
			  <td colspan="3">部门：
                    <input name="deptid1" id="deptid1" class="easyui-combobox"style="width:auto;"  >
               <button name="groupuser" type="button"  id="grantBtn" iconCls="icon-mygroup" onClick="saveuser()" >添加人员</button> </td> 
                     </td>
            </tr>
            <tr>
            <th>id</th>
            <th>姓名</th>
             <th>选择</th>
            </tr>
          </table>
           </div>
           </td>
           </tr>
           </div>
    </table>
            <div class="fitem">
				<table width="100%" border="0" cellpadding="0" cellspacing="6">
  <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class=""><input type=hidden name=id id=id>
  <tr><td colspan="4"  class="style2"><font color="blue">装卸组人员</font>
  <button name="deluse"  id="deluseBtn" type="button" iconCls="icon-remove" onclick="deluser()">删除</button></td></tr>
                 </table></td>
</table>
			</div>
				<div style="padding:8" id="tabdiv1">
		<table id="dataTable2" ShowHeader="false"></table>
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
  </body>
</html>
