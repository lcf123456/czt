<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/cost/splapplylist.js"/>"></script>
  <head>
  <script type="text/javascript">
    var username = "${userVo.username}";
    var userid = "${userVo.id}";
    var deptid = "${userVo.deptid}";
    var listid = 0;//申请单id
    var listtype = "${listtype}";
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
		<a href="#" id="viewBtn" class="easyui-linkbutton" iconCls="icon-view" plain="true" onclick="viewD()">查看</a>
			<a href="#" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openCreate()">领料</a>
			<a href="#" id="settingBtn" class="easyui-linkbutton" iconCls="icon-mysetting" plain="true" onclick="openAudit()">审核</a>
			<a href="#" id="delBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleterow()">删除</a>
			<input class="easyui-textbox"  name="keyword"  data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchData();},prompt:'请输入申请人、审核人、部门...'" style="width:220px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>
	
	
	
    <!-- 1、新增对话框 --------------------------------------------------------------------------------------->
	<div id="add-dlg" class="easyui-dialog" style="width:800px;height:420px;padding:5px 10px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<form  id="add-fm" method="post" action="" novalidate  >
			<div >
			<tr>
			<td>
			<table>
			<tr>
		  		 	<td width="5%" height="20" align="left" nowrap>申请人：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="applyusername" id="applyusername" class="easyui-textbox" style="width:120px"  readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>申请日期：</td>
		           	<td width="23%" height="20" align="left" nowrap>
		               <input name="applydate" id="applydate" class="easyui-textbox" style="width:120px" readonly/>
		           </td>  
		            <td width="5%" height="20" align="left" nowrap>耗用部门：</td>
		           	<td width="5%" height="20" align="left" nowrap colspan="3">
		                <input name="deptid" id="deptid" class="easyui-textbox" style="width:120px" >
		           	</td>
	           </tr>
	           <tr>
		            <td width="5%" height="20" align="left" nowrap>物料用途：</td>
		           	<td width="20%" height="20" align="left" nowrap colspan="7">
		               <input name="purpose" id="purpose" class="easyui-textbox" data-options="multiline:true"  value="0"  style="width:460px" >
		           	</td>
	           </tr>
			</table>
			</td>
			</tr>
			<div id="splchoose-div">
			<tr>
              <td> 
              
              <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
               <tr><td colspan="8"  class="style2"><font color="blue">物资类别</font></td></tr>
               </table></td>
           </tr>  
           <tr>
           <td height="120px">
           <div style="WIDTH: 100%; HEIGHT: 160px; BACKGROUND-COLOR: transparent; OVERFLOW: scroll; scrollbar-face-color:#c1d9f5; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff">
          <table width="100%" class="gridtable">
          <tr>
			  <td colspan="5">
                    <input name="lvl1" id="lvl1" class="easyui-combobox"style="width:auto;"  >
						<input name="lvl2" id="lvl2" class="easyui-combobox"style="width:auto;"  >
                   或:<input class="easyui-textbox"  name="condname" id="condname" data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchType();},prompt:'请输入类型名称...'" style="width:200px;height:24px;">
			
                     </td>
            </tr>
            <tr>
            <th>id</th>
            <th>名称</th>
            <th>数量</th>
             <th>单位</th>
             <th>选择</th>
            </tr>
          </table>
           </div>
           </td>
           </tr>
           <tr>
	    	<td >
		  	 <table width="100%" border="0" cellpadding="2" cellspacing="2" >
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>可用物资：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="typename" id="typename" class="easyui-textbox" style="width:160px" readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>可用数量：</td>
		           	<td width="23%" height="20" align="left" nowrap>
		               <input name="surplusqty" id="surplusqty" value="" class="easyui-textbox" style="width:80px" readonly/>
		           </td>  
		           	 <td width="5%" height="20" align="left" nowrap>单位：</td>
		           	<td width="5%" height="20" align="left" nowrap>
		                <input name="unit" id="unit" class="easyui-textbox" style="width:80px" readonly>
		           	</td>
		           	<td width="5%" height="20" align="left" nowrap>申请数量：</td>
		           	<td width="5%" height="20" align="left" nowrap>
		                <input name="applyqty" id="applyqty" class="easyui-numberbox" style="width:80px" data-options="validType:'length[1,10]'"><strong><font color="red" >*</font></strong>
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
		                <input name="mngusername" id="mngusername" class="easyui-textbox" style="width:120px"  readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>审核日期：</td>
		           	<td width="23%" height="20" align="left" nowrap>
		               <input name="mngauditdate" id="mngauditdate" class="easyui-textbox" style="width:120px" readonly/>
		           </td>  
	           </tr>
	           <tr>
		            <td width="5%" height="20" align="left" nowrap>审核意见：</td>
		           	<td width="20%" height="20" align="left" nowrap colspan="7">
		               <input name="mngsuggestion" id="mngsuggestion" class="easyui-textbox" data-options="multiline:true" style="width:460px" >
		           	</td>
	           </tr>
			</table>
			</td>
			</tr>
			</div>
			
			<input type="hidden" name="typeid" id="typeid"></input>
			<input type="hidden" name="applyid" id="applyid" ></input>
			<input type="hidden" name="mngid" id="mngid" ></input>
		</form>
	 <div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" id="btn-save" iconCls="icon-ok" onclick="saveNew()">保存</a>
		<a href="#" class="easyui-linkbutton" id="btn-audit" iconCls="icon-ok" onclick="doAudit('30')">通过</a>
		<a href="#" class="easyui-linkbutton" id="btn-back" iconCls="icon-cancel" onclick="doAudit('40')">驳回</a>
		<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-ok" onclick="javascript:$('#add-dlg').dialog('close')">关闭</a>
	</div>
	
	<div style="padding:10" id="listtabdiv">
		<table id="listdataTabel"></table>
	</div>
	</div>
	
	
  </body>
</html>
