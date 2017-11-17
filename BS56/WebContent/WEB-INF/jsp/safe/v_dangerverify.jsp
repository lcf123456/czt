<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>

<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/safe/dangerverify.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/jquery.PrintArea.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/jquery.jqprint-0.3.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/jquery-migrate-1.2.1.min.js"/>"></script>
  <script type="text/javascript">
	var verifyname="${userVo.username}";
	var verifymen="${userVo.id}";
</script>
 <head>
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
		<a href="#" id="grantBtn" class="easyui-linkbutton" iconCls="icon-myuser" plain="true" onclick="openhandle()">审核</a>
		    <a href="#" id="viewBtn" class="easyui-linkbutton" iconCls="icon-view" plain="true" onclick="openView()">查看</a>
		    处理状态：<select name="handlestatus" id="handlestatus" class="easyui-combobox" style="width:100px;" data-option='selected:true;'>
			<option value="40" selected>全部</option>
			<option value="10" >提交</option>
			<option value="20">整改下发</option> 
			<option value="30">整改完成</option> 
			</select>  
			   隐患类别：<input class="easyui-combobox" name="ctype" id="ctype" style="width:150px;" data-options="">
			<input class="easyui-textbox"  name="dangercontent" id="dangercontents" data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchDangerverify();},prompt:'请输入隐患内容...'" style="width:350px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
			<a href="#" id="printBtn" onclick="printpage();" class="easyui-linkbutton" iconCls="icon-myprinter" style="height:24px;">打印</a>
		</div>
		</form>
	</div>
	<!-- 打印窗口 -->
	<!--startprint-->
	<div id="print-dlg" class="easyui-dialog" style="width:800px;height:500px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#print-dlg-buttons">
		<div class="ftitle"></div>
		<form id="print-fm" method="post" action="" novalidate  >
		 <div class="fitem" id="printTable">
		<div id="print-dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-myprinter" onclick="$('#printTable').jqprint();$('#print-dlg').dialog('close'); ">打印</a>
	</div>
	</div>
	<!-- 审核对话框 -->
	<div id="handle-dlg" class="easyui-dialog"
		style="width: 700px; height: 400px; padding: 10px 20px; align: center;"
		data-options="modal:true,draggable:false" closed="true"
		buttons="#handle-dlg-buttons">
		<div class="ftitle"></div>
		<form id="handle-fm" method="post" action="" novalidate>
			<div class="fitem">
				<input type=hidden name=id id=id>
				<table width="100%" border="0" cellpadding="0" cellspacing="6">
					<tr>
						<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="">
								<tr>
									<td colspan="4" class="style2"><font color="blue">审核隐患核实信息</font></td>
								</tr>
							</table>
						</td>

					</tr>
				</table>
				<table width="100%" border="0" cellpadding="2" cellspacing="2"	>
					<tr align="center">
						<td width="5%" height="20" align="left" nowrap>记录人：</td>
						<td width="14%" height="20" align="left" nowrap><input
							name="createname" id="createname" class="easyui-validatebox tb"
							style="width: 150px" data-options="" readonly></td>
							<td width="5%" height="20" align="left" nowrap>隐患日期：</td>
						<td width="14%" height="20" align="left" nowrap><input
							name="dangerdate" id="dangerdate" class="easyui-datebox"
							style="width: 150px" data-options="" readonly></td>
						
					<!--  	<td width="5%" height="20" align="left" nowrap>核实人员：</td>
						<td width="14%" height="20" align="left" nowrap><input
							name="verifyname" id="verifyname" class="easyui-validatebox tb"
							value="${userVo.username}" style="width: 150px" data-options=""readonly>
						</td>-->
					</tr>
					<tr align="center">

						<td width="5%" height="20" align="left" nowrap>隐患类别：</td>
						<td width="14%" height="20" align="left" nowrap><input
							name="ctype" id="ctype" class="easyui-validatebox tb"
							style="width: 150px" data-options="" readonly></td>
						<td width="5%" height="20" align="left" nowrap>隐患核实：</td>
						<td width="14%" height="20" align="left"nowrap><select
							name="dangerstatus" id="dangerstatus" class="easyui-combobox "
							style="width: 150px;" data-options="">
								<option value="30"selected>--请选择--</option>
								<option value="10">有效隐患</option>
								<option value="20">无效隐患</option>
						</select>
					</td>

					</tr>
					<tr align="center">

						
						<td width="5%" height="20" align="left" nowrap>隐患内容：</td>
						<td width="14%" height="20" align="left"colspan="5" nowrap><input
							name="dangercontent" id="dangercontent" class="easyui-textbox"
							data-options="multiline:true,validType:'length[1,200]'"
							maxlength="200" style="width: 480px" readonly></td>
					</tr>
					 </table>
           <table width="100%" border="0" cellpadding="2" cellspacing="2" id="table1">
						<tr align="center" >
							<td width="5%" height="20" align="left" colspan="1"nowrap>整改部门：</td>
							<td width="14%" height="20" align="left" nowrap><input
								name="deptid" id="deptid" class="easyui-combobox"
								style="width: 150px;" data-options=""></td>
					
							<td width="5%" height="20" align="left" nowrap>检查时间：</td>
							<td width="14%" height="20" align="left" nowrap><input
								name="verifydate" id="verifydate" class="easyui-datebox"style="width: 150px" data-options=""></td>
						</tr>
						<tr align="center" >
							<td width="5%" height="20" align="left" colspan="1"nowrap>检查人员：</td>
							<td width="14%" height="20" align="left"nowrap><input
								name="checkid" id="checkid" class="easyui-textbox"
								style="width: 150px" data-options=""> 
								<!--<a href="#"class="easyui-linkbutton" onclick="openUser()">选择人员</a>-->
								</td>
						</tr>
						<tr align="center" >
							<td width="5%" height="20" align="left" nowrap>整改项目要求及期限：</td>
							<td width="14%" height="20" align="left"colspan="5" nowrap><input
								name="demands" id="demands" class="easyui-textbox"
								data-options="multiline:true,validType:'length[1,200]'"
								maxlength="200" style="width: 450px"></td>

						</tr>

				</table>
			</div>
			<br>
		</form>
	</div>
	<div id="handle-dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"onclick="handleEdit()">保存</a> 
		<a href="#" class="easyui-linkbutton"iconCls="icon-cancel"onclick="javascript:$('#handle-dlg').dialog('close')">取消</a>
	</div>

	<!-- 查看对话框 -->
	<div id="view-dlg" class="easyui-dialog" style="width:600px;height:400px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#view-dlg-buttons">
		<div class="ftitle"></div>
		<form id="view-fm" method="post" action="" novalidate  >
			<div class="fitem">
				<table width="100%" border="0" cellpadding="0" cellspacing="6">
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
              <tr><td colspan="4"  class="style2"><font color="blue">查看隐患记录信息</font></td></tr>
               </table></td>
           </tr>          
     <tr>
    <td >
    
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" >
              <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>记录人：</td>
           <td id="v-createname" class="formtd" font style="color:darkblue;" width="14%" height="20" align="left" nowrap>
            </td>
   
           <td width="5%" height="20" align="left" nowrap>隐患日期：</td>
       <td id="v-dangerdate" class="formtd" font style="color:darkblue;" width="14%" height="20" align="left" nowrap>
           </td>
           </tr>
                <tr align="center" >
           <td width="5%"  height="20" align="left" nowrap>隐患类别：</td>
             <td id="v-ctype" class="formtd" font style="color:darkblue;" width="14%" height="20" align="left" nowrap>
    
           </td>   
        </tr>
                <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>隐患内容：</td>
              <td id="v-dangercontent" class="formtd" font style="color:darkblue;" width="14%" height="20" colspan=5 align="left" >
             
           </td>
           </tr>
              <tr align="center" >
           <td width="5%" height="20" align="left" nowrap>核实人员：</td>
              <td id="v-verifyname" class="formtd" font style="color:darkblue;" width="14%" height="20" align="left" nowrap>
                
            </td>
    
            <td width="5%" height="20" align="left" nowrap>隐患核实：</td>
              <td id="v-dangerstatus" class="formtd" font style="color:darkblue;" width="14%" height="20" align="left" nowrap>
              
            </td>
            </tr>
            <tr align="center" >
               <td width="5%" height="20" align="left" nowrap>整改部门：</td>
       <td id="v-deptid" class="formtd" font style="color:darkblue;" width="14%" height="20" align="left" nowrap></td>
           <td width="5%" height="20" align="left" nowrap>检查时间：</td>
              <td id="v-verifydate" class="formtd" font style="color:darkblue;" width="14%" height="20" align="left" nowrap>
       </tr>
    
      <tr align="center">
       <td width="5%" height="20" align="left" nowrap>检查人员：</td>
             <td id="v-checkid" class="formtd" font style="color:darkblue;" width="14%" height="20" align="left" nowrap>
           </td>
            </tr>
                <tr align="center" >
            <td width="5%"  height="20" align="left" nowrap>整改项目要求及期限：</td>
             <td id="v-demands" class="formtd" font style="color:darkblue;" width="14%" height="20" align="left" nowrap>
      
	</td>
  </tr>
          <tr align="center">
           <td width="5%" height="20" align="left" nowrap>整改记录人：</td>
              <td id="v-rectifyname" class="formtd" font style="color:darkblue;" width="14%" height="20" align="left" nowrap>
              
            </td>
       <td width="5%" height="20" align="left" nowrap>隐患排除：</td>
             <td id="v-status" class="formtd" font style="color:darkblue;" width="14%" height="20" align="left" nowrap>
           </td>
            </tr>
              <tr align="center">
       <td width="5%" height="20" align="left" nowrap>整改内容：</td>
             <td id="v-rectifynote" class="formtd" font style="color:darkblue;"   width="14%" height="20" colspan=5 align="left" >
           </td>
             </tr>
	 </table>
	</td>
  </tr>
  </table>
			</div>
			<br>
		</form>
	</div>
<div id="view-dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#view-dlg').dialog('close')">关闭</a>
	</div>
<c:import url="/WEB-INF/jsp/pub/sessionPage.jsp?paramPage=DangerVerify"></c:import>
  </body>
</html>
