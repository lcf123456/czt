<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/wms/cigaretteinspection.js"/>"></script>
  <head>
  <script type="text/javascript">
  var damagedtype = "${damagedtype}";//破损类别(10：来烟破损，20：称重异常  30：抽检)
  var username = "${userVo.username}";
  var abid;
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
			<input class="easyui-textbox"  name="keyword"  data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchData();},prompt:'请输入入库单号、审核人...'" style="width:220px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>
	
	<div id="view-dlg" class="easyui-dialog" style="width:800px;height:420px;padding:5px 10px;align:center;"
			 closed="true" buttons="#dlg-buttons2"  data-options="modal:true,draggable:false">
		<form  id="view-fm" method="post" action="" novalidate  >
		
		<div id="seconddiv2">
			<tr>
			<td>
			<table>
	           <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>记录时间：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="createtime" id="createtime" class="easyui-textbox" style="width:120px"  readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>记录人：</td>
		           	<td width="23%" height="20" align="left" nowrap>
		               <input name="createuser" id="createuser" class="easyui-textbox" style="width:120px" readonly/>
		           </td>  
		            <td width="5%" height="20" align="left" nowrap>破损数量：</td>
		           	<td width="5%" height="20" align="left" nowrap >
		                <input name="qty" id="qty" class="easyui-textbox" style="width:120px" readonly>(条)
		           	</td>
	           </tr>
	            <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>审核日期：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="checktime" id="checktime" class="easyui-textbox" style="width:120px"  readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>审核人员：</td>
		           	<td width="23%" height="20" align="left" nowrap>
		               <input name="checkusername" id="checkusername" class="easyui-textbox" style="width:120px" readonly/>
		           </td>  
		            <td width="5%" height="20" align="left" nowrap>审核说明：</td>
		           	<td width="5%" height="20" align="left" nowrap >
		                <input name="checkdescribe" id="checkdescribe" class="easyui-textbox" style="width:120px" readonly>
		           	</td>
	           </tr>
			</table>
			</td>
			</tr>

	  	   <!-- 卷烟破损明细列表 -->
	  	   <tr>
			<td>
				  <table id="cigarettedamagedlineTabel2"></table>
			</td>
		</tr>
		</div>
		
		<div id="audit-div" >
			<tr>
			<td>
			<table>
			<tr>
		  		 	<td width="5%" height="20" align="left" nowrap>审核人：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="checkusername1" id="checkusername1" class="easyui-textbox" style="width:120px"  readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>审核日期：</td>
		           	<td width="23%" height="20" align="left" nowrap>
		               <input name="checktime1" id="checktime1" class="easyui-datebox" style="width:120px" />
		           </td>  
	           </tr>
	           <tr>
		            <td width="5%" height="20" align="left" nowrap>审核意见：</td>
		           	<td width="20%" height="20" align="left" nowrap colspan="7">
		               <input name="checkdescribe1" id="checkdescribe1" class="easyui-textbox" data-options="multiline:true" style="width:460px" >
		           	</td>
	           </tr>
			</table>
			</td>
			</tr>
			</div>
		
		</form>
	 <div id="dlg-buttons2">
	 <a href="#" class="easyui-linkbutton" id="btn-audit" iconCls="icon-ok" onclick="doAudit('20')">通过</a>
		<a href="#" class="easyui-linkbutton" id="btn-back" iconCls="icon-cancel" onclick="doAudit('30')">驳回</a>
		<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-ok" onclick="javascript:$('#view-dlg').dialog('close')">关闭</a>
	</div>
	</div>
	
    <!-- 1、新增--------------------------------------------------------------------------------------->
	<div id="add-dlg" class="easyui-dialog" style="width:960px;height:420px;padding:5px 10px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<form  id="add-fm" method="post" action="" novalidate  >
		<input type="hidden" name="damagedtype" value="${damagedtype}"/>
		<!-- 入库单列表 -->
		<!-- div >
			<tr>
			<td>
			<table>
			<tr>
		  		 	<td width="5%" height="20" align="left" nowrap>记录人：</td>
		           	<td width="10%" height="20" align="left" nowrap>
		                <input name="username_new" id="username_new" class="easyui-textbox"  style="width:120px"  >
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>记录日期：</td>
		           	<td width="70%" height="20" align="left" nowrap>
		               <input name="outtime_new" id="outtime_new" class="easyui-datebox" style="width:120px" />
		           </td>  
	         </tr>
	          
			</table>
			</td>
			</tr>
			</div> -->
		
		<div >
			<tr >
			<td width="5%" height="20" align="left" nowrap>记录人：</td>
		           	<td width="10%" height="20" align="left" nowrap>
		                <input name="username_new" id="username_new" class="easyui-textbox"  style="width:120px"  >
		           	</td>
            <td >
                  请输入查询： <input class="easyui-textbox"  name="keyworditem" id="keyworditem" data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchType();},prompt:'请输入卷烟名称或编码...'" style="width:300px;height:24px;">
                  <a href="#" onclick="clearFormitem();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
           </td>
           </tr>
			<tr>
			<td>
			<table width="100%" id="itemlist"><!-- 卷烟列表 -->
			</table>
			</td>
			</tr>
			<tr>
	    	<td >
		  	  <table width="100%" border="0" cellpadding="2" cellspacing="2" >
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>卷烟名称：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="cigarettenamenew" id="cigarettenamenew" class="easyui-textbox" style="width:160px" readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>卷烟编码：</td>
		           	<td width="5%" height="20" align="left" nowrap>
		               <input name="cigarettecodenew" id="cigarettecodenew" value="" class="easyui-textbox" style="width:80px" readonly/>
		           </td>  
		           	<td width="5%" height="20" align="left" nowrap>抽检数量：</td>
		           	<td width="5%" height="20" align="left" nowrap>
		                <input name="otherqty" id="otherqty" class="easyui-numberbox" style="width:80px" data-options="validType:'length[1,10]'"><strong><font color="red" >*</font></strong>(件)
		           	</td>
		           	<td width="5%" height="20" align="left" nowrap>实际抽检数量：</td>
		           	<td width="5%" height="20" align="left" nowrap>
		                <input name="actualqtynew" id="actualqtynew" class="easyui-numberbox" style="width:80px" data-options="validType:'length[1,10]'"><strong><font color="red" >*</font></strong>(条)
		           	</td>
	           </tr>
		 	 </table>
		     </td>
	  	   </tr>
	  	   
	  	   <tr>
			<td>
			<table width="100%" id="cigarettedamagedlineTabel">
			</table>
			</td>
			</tr>
			</div>
		
		</form>
	 <div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" id="saveBtn" iconCls="icon-ok" onclick="dosave()">保存</a>
		<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-ok" onclick="javascript:$('#add-dlg').dialog('close')">关闭</a>
	</div>
	</div>
	
	
  </body>
</html>
