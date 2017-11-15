<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/wms/machinedamaged.js"/>"></script>
  <head>
  <script type="text/javascript">
  var username = "${userVo.username}";
  var mdid=0;//机损烟主表id
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
		<div style="margin-bottom:5px">
		<a href="#" id="newBtn" class="easyui-linkbutton" iconCls="icon-view" plain="true" onclick="viewD()">查看</a>
		<a href="#" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openCreate()">新增</a>
		<a href="#" id="settingBtn" class="easyui-linkbutton" iconCls="icon-mysetting" plain="true" onclick="openAudit()">审核</a>
		<a href="#" id="delBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleterow()">删除</a>
			<input class="easyui-textbox"  id="keyword"name="keyword"  data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchData();},prompt:'请输入记录人、审核人...'" style="width:220px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>
	
	
	
    <!-- 1、新增--------------------------------------------------------------------------------------->
	<div id="add-dlg" class="easyui-dialog" style="width:800px;height:420px;padding:5px 10px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<form  id="add-fm" method="post" action="" novalidate  >
		
		<div id="sortthroughdiv">
		<tr >
            <td >
                  请输入查询： <input class="easyui-textbox"  name="keyword" id="keyword" data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchType();},prompt:'请输入卷烟名称或编码'" style="width:300px;height:24px;">
           </td>
           </tr>
           
           <!-- 通道列表 -->
           <tr><td>
            <table id="sortthrough">
          </table>
           </td></tr>
           </div>
           
           <div id="maindiv">
			<tr>
			<td>
			<table>
		
	           <tr>
	           <td width="5%"  height="20" align="left" nowrap>录入人：</td>
		           	<td width="23%" height="20" align="left" nowrap>
		               <input name="createusername" id="createusername" class="easyui-textbox" style="width:120px" readonly/>
		           </td>  
		  		 	<td width="5%" height="20" align="left" nowrap>记录时间：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="createtime" id="createtime" class="easyui-textbox" style="width:120px"  readonly>
		           	</td>
		            <td width="5%" height="20" align="left" nowrap>类型：</td>
		           	<td width="5%" height="20" align="left" nowrap >
		           		<input name="cigarettetype" id="cigarettetype" class="easyui-textbox" style="width:120px" >
		           	</td>
	           </tr>
	            
	            <tr>
	           
	         
		           	<td width="5%"  height="20" align="left" nowrap>组次：</td>
		           	<td width="23%" height="20" align="left" nowrap>
		               <input name="groupno" id="groupno" class="easyui-textbox" style="width:120px" readonly>
		           </td>  
	           		<td width="5%" height="20" align="left" nowrap>通道物理编号：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="machineseq" id="machineseq" class="easyui-textbox" style="width:120px"  readonly>
		           	</td>
		            <td width="5%" height="20" align="left" nowrap>通道编号：</td>
		           	<td width="5%" height="20" align="left" nowrap colspan="3">
		           		<input name="troughnum" id="troughnum" class="easyui-textbox" style="width:120px" readonly>
		           	</td>
	           </tr>
	            <tr>
	           <td width="5%"  height="20" align="left" nowrap>卷烟编码：</td>
		           	<td width="23%" height="20" align="left" nowrap>
		               <input name="cigarettecode" id="cigarettecode" class="easyui-textbox" style="width:120px" readonly>
		           </td>  
		  		 	<td width="5%" height="20" align="left" nowrap>卷烟名称：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="cigarettename" id="cigarettename" class="easyui-textbox" style="width:120px"  readonly>
		           	</td>
	             <td width="5%" height="20" align="left" nowrap>破损数量(条)：</td>
		           	<td width="5%" height="20" align="left" nowrap colspan="3">
		           		<input name="qty" id="qty" class="easyui-numberbox" style="width:120px" >
		           	</td>
	           </tr>
			</table>
			</td>
			</tr>
			<!-- 破损明细列表 -->
	  	   <tr>
			<td>
				  <table id="machinedamagedlineTabel"></table>
			</td>
		</tr>
		</div>
		
		</form>
	 <div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" id="saveBtn" iconCls="icon-ok" onclick="dosave()">保存</a>
		<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-ok" onclick="javascript:$('#add-dlg').dialog('close')">关闭</a>
	</div>
	</div>
	
	
	<!-- 审核对话框 -->
	<div id="edit-dlg" class="easyui-dialog" style="width:800px;height:420px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#edit-dlg-buttons">
		<div class="ftitle"></div>
		<form id="edit-fm" method="post" novalidate action="" >
			<div id="maindiv">
			<tr>
			<td>
			<table>
		
	           <tr>
	           <td width="5%"  height="20" align="left" nowrap>录入人：</td>
		           	<td width="23%" height="20" align="left" nowrap>
		               <input name="createusername" id="createusername" class="easyui-textbox" style="width:120px" readonly/>
		           </td>  
		  		 	<td width="5%" height="20" align="left" nowrap>记录时间：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="createtime" id="createtime" class="easyui-textbox" style="width:120px"  readonly>
		           	</td>
		            <td width="5%" height="20" align="left" nowrap>破损数量：</td>
		           	<td width="5%" height="20" align="left" nowrap >
		           		<input name="qty" id="qty" class="easyui-textbox" style="width:120px" >
		           	</td>
	           </tr>
	            
	           <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>审核人：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="checkusername" id="checkusername" class="easyui-textbox" style="width:120px"  readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>审核日期：</td>
		           	<td width="23%" height="20" align="left" nowrap colspan="3">
		               <input name="checktime" id="checktime" class="easyui-textbox" style="width:120px" readonly/>
		           </td>  
	           </tr>
	           <tr>
		            <td width="5%" height="20" align="left" nowrap>审核意见：</td>
		           	<td width="20%" height="20" align="left" nowrap colspan="5">
		               <input name="checkdescribe" id="checkdescribe" class="easyui-textbox" data-options="multiline:true" style="width:460px" >
		           	</td>
	           </tr>
			</table>
			</td>
			</tr>
			<!-- 破损明细列表 -->
	  	   <tr>
			<td>
				  <table id="machinedamagedlineTabel2"></table>
			</td>
		</tr>
		</div>
		
		</form>
	</div>
	<div id="edit-dlg-buttons">
		<a href="#" class="easyui-linkbutton" id="btn-audit" iconCls="icon-ok" onclick="doAudit('20')">通过</a>
		<a href="#" class="easyui-linkbutton" id="btn-back" iconCls="icon-cancel" onclick="doAudit('30')">驳回</a>
		<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-ok" onclick="javascript:$('#edit-dlg').dialog('close')">关闭</a>
	</div>
	
	
	
  </body>
</html>
