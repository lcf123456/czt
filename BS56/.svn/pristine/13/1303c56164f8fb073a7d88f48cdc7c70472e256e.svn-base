<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/safe/dangercreate.js"/>"></script>
<script type="text/javascript">
	var createname="${userVo.username}";
	var createid="${userVo.id}";
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
		<a href="#" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newadd()">增加</a>
		    <a href="#" id="viewBtn" class="easyui-linkbutton" iconCls="icon-view" plain="true" onclick="openView()">查看</a>
		排除状态：<select name="status" id="status" class="easyui-combobox" style="width:70px;" data-option='selected:true;'>
			<option value="20" selected>全部</option>
			<option value="10" >已排除</option>
			<option value="0">未排除</option> 
			</select> 
             隐患类别：<input class="easyui-combobox" name="ctype" id="ctype" style="width:150px;" data-options="">
			<input class="easyui-textbox"  name="dangercontent"  data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchDangercreate();},prompt:'请输入隐患内容/记录人...'" style="width:350px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>
	<!-- 新增对话框 -->
	<div id="add-dlg" class="easyui-dialog" style="width:600px;height:300px;padding:5px 10px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<form  id="add-fm" method="post" action="" novalidate  >
			<div class="fitem"><input type=hidden name=id id=id>
				<table width="100%" border="0" cellpadding="0" cellspacing="6">
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
              <tr><td colspan="4"  class="style2"><font color="blue">新增隐患记录</font></td></tr>
               </table></td>
                       
    <tr>
    <td >
    
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" >
              <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>记录人：</td>
            <td width="14%" height="20" align="left" nowrap>
                   <input name="createname" id="createname" class="easyui-validatebox tb" value="${userVo.username}" style="width:150px" data-options="" >
            </td>
               <td width="5%" height="20" align="left" nowrap>隐患日期：</td>
           <td width="14%" height="20" align="left" nowrap>
                   <input  name="dangerdate" id="dangerdate" class="easyui-datebox " style="width:150px" data-options="">
            </td>
						      </tr>
                <tr align="center" >
           <td width="5%"  height="20" align="left" nowrap>隐患类别：</td>
           <td width="14%" height="20" align="left" nowrap>
                   <input name="dangertype" id="dangertype" class="easyui-combobox" style="width:150px" data-options="
                   valueField : 'id',
	    	       textField : 'ctype',
	    	       value:'3901',
	    	       url : '<%=basePath %>/safe/hiddendanger/getCtypeCombobox.json', " />
            </td> 
              </tr>
       
                <tr align="center" >
                <td width="5%" height="20" align="left" nowrap>隐患内容：</td>
            <td width="14%" height="60" align="left" nowrap>
                   <input name="dangercontent" id="dangercontent" class="easyui-textbox"  data-options="multiline:true,validType:'length[1,200]'" maxlength="200"  style="width:150px" >
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
       <td id="v-deptname" class="formtd" font style="color:darkblue;" width="14%" height="20" align="left" nowrap></td>
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
       <td width="5%" height="20" align="left" nowrap="">整改内容：</td>
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
	
	


  </body>
</html>
