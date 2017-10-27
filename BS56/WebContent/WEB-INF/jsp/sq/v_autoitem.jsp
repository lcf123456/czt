<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/sq/evalitem.js"/>"></script>

  <head>
  </head>
  
  <body>
	
	<!-- datagrid页面列表数据 labelPosition="right"-->
	<div style="padding:10" id="tabdiv">
		<table id="dataTable"></table>
	</div>
	
	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;border-top:1px solid #95B8E7">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">
			<a href="#" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openNew()">增加</a>
			<a href="#" id="editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openEdit()">修改</a>
			<a href="#" id="delBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRow()">删除</a>
		</div>
		</form>
	</div>
	
	<!-- 新增对话框 -->
	<div id="add-dlg" class="easyui-dialog" style="width:850px;height:400px;padding:10px 20px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<div class="ftitle"></div>
		<form  id="add-fm" method="post" action=""  >
			<div class="fitem">
    <tr>
  <table width="100%" border="0" cellpadding="0" cellspacing="8">
      <td background="">
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
               <tr><td colspan="6"  class="style2"><font color="blue">考核项信息</font></td></tr>
               </table></td>
           </tr>          
     <tr>
    <td >
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="">
        <tr align="center" >
           <td width="5%" height="20" align="left" nowrap>考核项简写：</td>
           <td width="50%" height="20" align="left" colspan="3" nowrap>
                <input name="contentshort" id="contentshort" size="80" class="easyui-validatebox tb" data-options="required:true">
                <input type="hidden" id="flag" name="flag" value="20"/>
                <input type="hidden" id="voteflag"name="voteflag" value="10"/>
                <input type="hidden" id="assesstype"name="assesstype" value="4"/>
                <input type="hidden" id="itemnum"name="itemnum" value="2"/>
           </td>
           </tr>
           <tr>
           <td width="5%"  height="20" align="left" nowrap>考核项内容：</td>
           <td width="13%" height="20" align="left"colspan="3"  nowrap>
               <input name="content" id="content"size="80"   class="easyui-validatebox tb" data-options="required:true" style=""/>
           </td>   
           </tr>
           <tr>
           <td width="5%" height="20" align="left" nowrap>考核项分数：</td>
            <td width="14%" height="20" align="left"  nowrap>
                  <input name="score" id="score" class="easyui-numberbox"style="width:auto;" value="10"data-option='min:0'>
            </td>
           <td width="5%" height="20" align="left" nowrap>考核项权重：</td>
            <td width="14%" height="20" align="left"  nowrap>
                  <input name="assessweight" id="assessweight" class="easyui-numberbox"style="width:auto;"value="0"data-options="min:0,max:1,precision:2">
            </td>
       </tr>
           <tr>
           <td width="5%" height="20" align="left" nowrap>考 核 对 象：</td>
            <td width="14%" height="20" align="left"  nowrap>
            	<select id=usetype name="usetype" class="easyui-combobox" width="auto;">
            		<option value="10" selected>司机</option>
            		<option value="20">收款员</option>
            		<option value="30">司机收款员</option>
            	</select>
            </td>
           <td width="5%" height="20" align="left" nowrap>语音文件名：</td>
            <td width="14%" height="20" align="left"  nowrap>
                  <input name="voxfile" id="voxfile" class="easyui-validatebox"style="width:auto;"data-option="" ><strong><font color="red" >VOX格式音频文件名</font></strong>
            </td>
       </tr>
       
	      <tr align="center" >
	       <td width="5%"  height="20" align="left" nowrap>备注信息：</td>
           <td width="50%" height="20" align="left" colspan=3 nowrap>
           			 <textarea  name="remarks" cols="74.8" rows="3" ></textarea>
           	</td>
       </tr>
       
    </table>
    
	
	</td>
  </tr>
  </table>
  </tr>
  </table>
  </tr>
			</div>
			<br>
		</form>
	</div>
	</table></td></tr></table></tr></div></div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveAdd()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#add-dlg').dialog('close');">取消</a>
	</div>
	
	<!-- 修改对话框 -->
	<div id="edit-dlg" class="easyui-dialog" style="width:850px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#grant-dlg-buttons">
		<div class="ftitle"></div>
		<form id="edit-fm" method="post"  validate action="" >
			<input type="hidden" id="id" name="id"/>
			<div class="fitem">
    <tr>
  <table width="100%" border="0" cellpadding="0" cellspacing="8">
      <td background="">
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
               <tr><td colspan="6"  class="style2"><font color="blue">考核项信息</font></td></tr>
               </table></td>
           </tr>          
     <tr>
    <td >
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="">
        <tr align="center" >
           <td width="5%" height="20" align="left" nowrap>考核项简写：</td>
           <td width="50%" height="20" align="left" colspan="3" nowrap>
                <input name="contentshort" id="contentshort" size="80" class="easyui-validatebox tb" data-options="required:true">
                <input type="hidden" id="flag" name="flag" value="20"/>
                <input type="hidden" id="voteflag"name="voteflag" value="10"/>
                <input type="hidden" id="assesstype"name="assesstype" value="4"/>
                <input type="hidden" id="itemnum"name="itemnum" value="2"/>
           </td>
           </tr>
           <tr>
           <td width="5%"  height="20" align="left" nowrap>考核项内容：</td>
           <td width="13%" height="20" align="left"colspan="3"  nowrap>
               <input name="content" id="content"size="80"   class="easyui-validatebox tb" data-options="required:true" style=""/>
           </td>   
           </tr>
           <tr>
           <td width="5%" height="20" align="left" nowrap>考核项分数：</td>
            <td width="14%" height="20" align="left"  nowrap>
                  <input name="score" id="score" class="easyui-numberbox"style="width:auto;" value="10"data-option='min:0'>
            </td>
           <td width="5%" height="20" align="left" nowrap>考核项权重：</td>
            <td width="14%" height="20" align="left"  nowrap>
                  <input name="assessweight" id="assessweight" class="easyui-numberbox"style="width:auto;"value="0"data-options="min:0,max:1,precision:2">
            </td>
       </tr>
           <tr>
           <td width="5%" height="20" align="left" nowrap>考 核 对 象：</td>
            <td width="14%" height="20" align="left"  nowrap>
            	<select id=usetype name="usetype" class="easyui-combobox" width="auto;">
            		<option value="10" selected>司机</option>
            		<option value="20">收款员</option>
            		<option value="30">司机收款员</option>
            	</select>
            </td>
           <td width="5%" height="20" align="left" nowrap>语音文件名：</td>
            <td width="14%" height="20" align="left"  nowrap>
                  <input name="voxfile" id="voxfile1" class="easyui-validatebox"style="width:auto;"data-option="" ><strong><font color="red" >VOX格式音频文件名</font></strong>
            </td>
       </tr>
       
	      <tr align="center" >
	       <td width="5%"  height="20" align="left" nowrap>备注信息：</td>
           <td width="50%" height="20" align="left" colspan=3 nowrap>
           			 <textarea  name="remarks" cols="74.8" rows="3" ></textarea>
           	</td>
       </tr>
       
    </table>
    
	
	</td>
  </tr>
  </table>
			</div><br>
			
		</form>
	</div>
	<div id="grant-dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveEdit()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#edit-dlg').dialog('close')">取消</a>
	</div>
	
	<!-- 进度条，建议对于耗时长的操作才启用
	<div id="loading">
		<div class="inputdiv" >
			<img  src="/BS56/img/loading.gif" style="padding-top: 20px; padding-left:72px;"/><br>
			<h3 style="padding-left: 35px;">&nbsp;&nbsp;&nbsp;&nbsp;数据上传中,请稍候......</h3>
		</div>
	 </div>-->	
	  
	  <c:import url="/WEB-INF/jsp/pub/sessionPage.jsp?paramPage=autoItem"></c:import>
  </body>
</html>
