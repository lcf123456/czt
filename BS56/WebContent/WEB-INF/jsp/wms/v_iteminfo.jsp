<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/wms/iteminfo.js"/>"></script>
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
			<a href="#"id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newadd()">新增</a>
			<a href="#"id="delBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleterow()">删除</a>
			<input class="easyui-textbox"  name="cdtype"  data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchiteminfo();},prompt:'件码...'" style="width:180px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	
	
			<!-- 新增对话框 -->
	<div id="add-dlg" class="easyui-dialog" style="width:825px;height:450px;padding:5px 10px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<form  id="add-fm" method="post" action="" novalidate  >
			<div class="fitem">
			<tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
               <tr><td colspan="6"  class="style2"><font color="blue">新增商品信息</font></td></tr>
               </table></td>
           </tr>      
      <tr>
    <td >
	  <table width="90%" border="0" cellpadding="2" cellspacing="2" class="">
              <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>商品编号：</td>
            <td width="14%" height="20" align="left" nowrap>
                   <input name="itemno" id="itemno" class="easyui-validatebox tb" style="width:150px" data-options="required:true,validType:'length[1,30]'"><strong><font color="red" >*</font></strong>
            </td>
            <td width="5%" height="20" align="left" nowrap>商品名称：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="itemname" id="itemname"  class="easyui-combobox "style="width:100px" >
            </td>
           <td width="5%" height="20" align="left" nowrap>商品简称：</td>
           <td  width="14%"height="20" align="left" nowrap>
            <input name="shortname" id="shortname"   class="easyui-validatebox tb" style="width:300px"data-options="" >
           </td>
           </tr>
                <tr align="center" >
           <td width="5%"  height="20" align="left" nowrap>规格：</td>
           <td width="14%"  height="20" align="left" nowrap>
               <input name="spec" id="spec"   class="easyui-validatebox tb"style="width:150px" data-options="" >
           </td>  
           <td width="5%" height="20" align="left" nowrap>ABC编码：</td>
            <td width="14%" height="20" align="left" nowrap>
                <select name="abccode" id="abccode" class="easyui-combobox tb" style="width:100px" data-options="" >
            <option value="30" selected>C类</option>
				<option value="20" >B类</option>
				<option value="10" >A类</option>
				</select>
            </td> 
            <td width="5%" height="20" align="left" nowrap>类型：</td>
            <td width="14%" height="20" align="left" nowrap>
                <select name="shiptype" id="shiptype" class="easyui-combobox tb" style="width:300px" data-options="" >
                 <option value="0" selected>正常烟</option>
				<option value="1" >异型烟</option>
				</select>
           </td>
           </tr>
              <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>状态：</td>
            <td width="14%" height="20" align="left" nowrap>
               <select name="rowstatus" id="rowstatus" class="easyui-combobox " style="width:150px;"data-options="" >
                <option value="10" selected>正常</option>
				<option value="0" >删除</option>
				</select>
				</td> 
          <td width="5%" height="20" align="left" nowrap>产区：</td>
           <td width="14%" height="20" align="left" nowrap>
           <select name="producearea" id="producearea" class="easyui-combobox" style="width:300px"></select>
            </td>
            </tr>
       <tr align="center">
       <td width="5%" height="20" align="left" nowrap>重量：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="weight" id="weight" class="easyui-validatebox tb" style="width:100px" data-options="" >
            </td> 
            <td width="5%" height="20" align="left" nowrap>扫码识别类型：</td>
            <td width="14%" height="20" align="left" nowrap>
                <select name="iscanscancode" id="iscanscancode" class="easyui-combobox tb" style="width:100px" data-options="" >
            <option value="10" selected>能扫码识别</option>
				<option value="0" >无法扫码识别</option>
				</select>
            </td> 
            <td width="5%" height="20" align="left" nowrap>垛型：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="dxtype" id="dxtype" class="easyui-validatebox tb" style="width:100px" data-options="" >
            </td> 
            </tr>
            <td width="5%" height="20" align="left" nowrap>件条：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="jt_size" id="jt_size" class="easyui-validatebox tb" style="width:100px" data-options="" >
            </td> 
            <td width="5%" height="20" align="left" nowrap>万条：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="wz_size" id="wz_size" class="easyui-validatebox tb" style="width:100px" data-options="" >
            </td> 
            <td width="5%" height="20" align="left" nowrap>出库类型：</td>
            <td width="14%" height="20" align="left" nowrap>
                <select name="outtype" id="outtype" class="easyui-combobox tb" style="width:100px" data-options="" >
            <option value="1" selected>一楼出</option>
				<option value="2" >二楼出</option>
				</select>
            </td> 
            <td width="5%" height="20" align="left" nowrap>满盘数量：</td>
            <td width="14%" height="20" align="left" nowrap>
                <input name="fullcount" id="fullcount" class="easyui-validatebox tb" style="width:100px" data-options="" >
            </td> 
            <td width="5%" height="20" align="left" nowrap>拆垛类型：</td>
            <td width="14%" height="20" align="left" nowrap>
                <select name="cdtype" id="cdtype" class="easyui-combobox tb" style="width:100px" data-options="" >
            <option value="10" selected>自动拆垛</option>
				<option value="0" >人工拆垛</option>
				</select>
            </td> 
      
  	 </table>
		     </td>
	  	   </tr>
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveNew()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#add-dlg').dialog('close')">取消</a>
	</div>
	
<c:import url="/WEB-INF/jsp/pub/sessionPage.jsp?paramPage=Brandinfo"></c:import>
  </body>
</html>
