<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 调拨出库界面 -->
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/wms/movefromsytolk.js"/>"></script>
  <head>
  <script type="text/javascript">
  var movetype = "${movetype}";////移库类别 10：散烟区至分拣 20：散烟区至立库 30：通用移库
  var areaid = "${areaid}";
  var targetareaid = "${targetareaid}";
  var username = "${userVo.username}";
  var obid=0;//移库主表id
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
	<input type="hidden" name="movetype" id="movetype" value="${movetype}"></input>
		<div style="margin-bottom:5px">
		<a href="#" id="viewBtn" class="easyui-linkbutton" iconCls="icon-view" plain="true" onclick="viewD()">查看</a>
		<a href="#" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openCreate()">录入</a>
		<a href="#" id="settingBtn" class="easyui-linkbutton" iconCls="icon-mysetting" plain="true" onclick="openAudit('20')">出库</a>
		<a href="#" id="settingBtn" class="easyui-linkbutton" iconCls="icon-mysetting" plain="true" onclick="openAudit('30')">收货</a>
		<a href="#" id="delBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleterow()">删除</a>
		
		出库日期从：<input name="searchtime" id="searchtime" class="easyui-datebox" style="width:120px" >&nbsp;
		到：<input name="searchtime2" id="searchtime2" class="easyui-datebox" style="width:120px" >&nbsp;&nbsp;&nbsp;
			<a href="#" onclick="searchData();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">查询</a>
		</div>
		</form>
	</div>
	
    <!-- 1、新增--------------------------------------------------------------------------------------->
	<div id="add-dlg" class="easyui-dialog" style="width:960px;height:420px;padding:5px 10px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<form  id="add-fm" method="post" action="" novalidate  >
		
			<div id="baseinfodiv">
			<tr>
			<td>
			<table>
			<tr>
		  		 	<td width="5%" height="20" align="left" nowrap>记录人：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="username_new" id="username_new" class="easyui-textbox"  style="width:120px"  >
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>记录日期：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="createtime_new" id="createtime_new" class="easyui-datebox" style="width:120px" />
		           </td>  
	         </tr>
	          <tr>
		  		 	 <td width="5%" height="20" align="left" nowrap>原区域：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="sourceareaname_new" id="sourceareaname_new"  class="easyui-textbox" style="width:120px" readonly>
		           	</td>
		            <td width="5%" height="20" align="left" nowrap>目标区域：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="targetareaname_new" id="targetareaname_new"  class="easyui-textbox" style="width:120px" readonly>
		           	</td>
	         </tr>
			</table>
			</td>
			</tr>
			</div>
			
			<div id="fisstdiv" style="padding:10">
			<tr >
            <td >
                                    请输入查询： <input class="easyui-textbox"  name="keyword_item" id="keyword_item" data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchType();},prompt:'请输入卷烟名称或编码'" style="width:300px;height:24px;">
                  <a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
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
		  		 	<td width="5%" height="20" align="left" nowrap>卷烟编码：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="cigarettecode" id="cigarettecode" class="easyui-textbox" style="width:160px" readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>卷烟名称：</td>
		           	<td width="23%" height="20" align="left" nowrap>
		               <input name="cigarettename" id="cigarettename" value="" class="easyui-textbox" style="width:80px" readonly/>
		           </td>  
		           <td width="5%"  height="20" align="left" nowrap>件条比率：</td>
		           	<td width="23%" height="20" align="left" nowrap>
		               <input name="jtsize" id="jtsize" value="" class="easyui-textbox" style="width:80px" readonly/>
		           </td>
		           	<td width="5%" height="20" align="left" nowrap>移库数量：</td>
		           	<td width="5%" height="20" align="left" nowrap>
		                <input name="qtynew" id="qtynew" class="easyui-numberbox" style="width:80px" data-options="validType:'length[1,10]'"><strong><font color="red" >*</font></strong>(条)
		           	</td>
	           </tr>
		 	 </table>
		     </td>
	  	   </tr>
			</div>
			
			<div>
				<tr>
				<td>
				<table width="100%" id="Moveareastocklinelist">
				</table>
				</td>
				</tr>
			</div>
			<input type="hidden" class="easyui-textbox" name="barcode" id="barcode"></input>
		</form>
	 <div id="dlg-buttons">
	    <a href="#" class="easyui-linkbutton" id="saveBtn" iconCls="icon-ok" onclick="toSave()">保存移库</a>
		<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-cancel" onclick="javascript:$('#add-dlg').dialog('close')">关闭</a>
	</div>
	</div>
	
	    <!-- 1、查看--------------------------------------------------------------------------------------->
	<div id="view-dlg" class="easyui-dialog" style="width:960px;height:420px;padding:5px 10px;align:center;"
			 closed="true" buttons="#view-buttons"  data-options="modal:true,draggable:false">
		<form  id="view-fm" method="post" action="" novalidate  >
			<div >
			<tr>
			<td>
			<table>
			<tr>
		  		 	<td width="5%" height="20" align="left" nowrap>记录人：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="createusername" id="createusername" class="easyui-textbox"  style="width:120px"  readonly>
		           	</td>
		            <td width="5%" height="20" align="left" nowrap>记录日期：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="createtime" id="createtime" class="easyui-textbox" style="width:120px" readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>原区域名称：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="sourceareaname" id="sourceareaname" class="easyui-textbox" style="width:120px" readonly/>
		           </td>  
		            <td width="5%" height="20" align="left" nowrap>目标区域名称：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="targetareaname" id="targetareaname" class="easyui-textbox" style="width:120px" readonly>
		           	</td>
	         </tr>
	         <tr>
		           	<td width="5%"  height="20" align="left" nowrap>移库数量(件)：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="boxqty" id="boxqty" class="easyui-textbox" style="width:120px" readonly/>
		           </td>  
		            <td width="5%" height="20" align="left" nowrap>移库数量(条)：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="barqty" id="barqty" class="easyui-textbox" style="width:120px" readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>出库人员：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="outcheckusername" id="outcheckusername" class="easyui-textbox" style="width:120px" readonly/>
		           </td>  
		            <td width="5%" height="20" align="left" nowrap>出库日期：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="outchecktime" id="outchecktime" class="easyui-textbox" style="width:120px" readonly>
		           	</td>
	         </tr>
	         <tr>
		           	<td width="5%"  height="20" align="left" nowrap>收货人员：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="receivecheckusername" id="receivecheckusername" class="easyui-textbox" style="width:120px" readonly/>
		           </td>  
		            <td width="5%" height="20" align="left" nowrap>收货日期：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="receivechecktime" id="receivechecktime" class="easyui-textbox" style="width:120px" readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>审核标志：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="auditflagname" id="auditflagname" class="easyui-textbox" style="width:120px" readonly/>
		           </td>  
		            <td width="5%" height="20" align="left" nowrap>移库类别：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="movetypename" id="movetypename" class="easyui-textbox" style="width:120px" readonly>
		           	</td>
	         </tr>
	          
			</table>
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
		           	<td width="52%" height="20" align="left" nowrap>
		               <input name="checktime1" id="checktime1" class="easyui-textbox" style="width:120px" readonly/>
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
			
			<div >
			<tr>
			<td>
			<table width="100%" id="Moveareastocklinelist1">
			</table>
			</td>
			</tr>
			</div>
		</form>
	 <div id="view-buttons">
	    <a href="#" class="easyui-linkbutton" id="btn-audit" iconCls="icon-ok" onclick="doAudit('20')">出库</a>
	    <a href="#" class="easyui-linkbutton" id="btn-receive" iconCls="icon-ok" onclick="doAudit('30')">收货</a>
		<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-cancel" onclick="javascript:$('#view-dlg').dialog('close')">关闭</a>
	</div>
	</div>
	
  </body>
</html>
