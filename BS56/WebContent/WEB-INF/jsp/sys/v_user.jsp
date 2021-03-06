<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/sys/user.js"/>"></script>

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
			<a href="#"  id= "newBtn"class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newAddUser()">增加</a>
			<a href="#"  id= "editBtn"class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="openEdit()">修改</a>
			<a href="#"  id= "grantBtn"class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="openGrant()">授权</a>
			<a href="#"  id= "resetBtn" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="resetPsw()">密码重置</a>
			<select class="easyui-combobox" name="workstatus"  style="width:auto;">
				<option value="10" selected>在职</option>
				<option value="0">离职</option>
				<option value="20">退休</option>
			</select>
			<input class="easyui-textbox"  name="usercode" id="usercode"  data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchUser();},prompt:'请输入工号/姓名/部门/角色/岗位...'" style="width:250px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>
	
	<!-- 新增对话框 -->
	<div id="add-dlg" class="easyui-dialog" style="width:850px;height:400px;padding:10px 20px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<div class="ftitle"></div>
		<form  id="add-fm" method="post" action="" novalidate  enctype="multipart/form-data" >
			<div class="fitem">
    <tr>
  <table width="100%" border="0" cellpadding="0" cellspacing="8">
      <td background="">
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
               <tr><td colspan="6"  class="style2"><font color="blue">员工基础信息</font></td></tr>
               </table></td>
           </tr>          
     <tr>
    <td >
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="">
        <tr align="center" >
            
           <td width="5%" height="20" align="left" nowrap>姓名：</td>
           <td width="14%" height="20" align="left" nowrap>
                <input name="username" id="username" class="easyui-validatebox tb" data-options="required:true,validType:'length[2,10]'"><strong><font color="red" >*</font></strong>
           </td>
           <td width="5%"  height="20" align="left" nowrap>身份证号码：</td>
           <td width="13%" height="20" align="left" nowrap>
               <input name="idnum" id="idnum" value="" maxlength="18"  class="easyui-validatebox tb" data-options="validType:'length[1,18]'" style="ime-mode:Disabled"/>
           </td>   
       </tr>
       
       <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>性别：</td>
            <td width="14%" height="20" align="left" nowrap>
                   <select name="sex" id="sex" class="easyui-combobox">
                   	    <option value="1">男</option>
						<option value="0"  selected>女</option>
					</select>
            </td>
           <td width="5%" height="20" align="left" nowrap>婚姻状况：</td>
            <td width="14%" height="20" align="left" nowrap>
            
                <select name="ismarried" id="ismarried" class="easyui-combobox">
                         <option value="0" selected>未婚</option>
				         <option value="1">已婚</option>
				         <option value="2">离异</option>
                  </select>
            
            </td>

       </tr>
       <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>民族：</td>
            <td width="14%" height="20" align="left" nowrap>
                  <input name="nation" id="nation" class="easyui-combobox"style="width:auto;"data-option=''>
            </td>
           <td width="5%" height="20" align="left" nowrap>政治面貌：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="politicalstatus" id="politicalstatus" class="easyui-combobox"style="width:auto;"data-option=''>
           </td>
       </tr>
       <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>户口：</td>
            <td width="12%" height="20" align="left" nowrap>
                 <input type="text" name="idregister" id="idregister" maxlength="38"/>
            </td>
           <td width="5%" height="20" align="left" nowrap>籍贯：</td>
           <td width="14%" height="20" align="left" nowrap>
               <input type="text" name="nativeplace" id="nativeplace" maxlength="38" />
           </td>
       </tr>
       <tr align="center">
            <td width="5%"  height="20" align="left" nowrap>部门：</td>
           <td width="13%" height="20" align="left" nowrap>
           <input name="deptid" id="deptid" class="easyui-combobox"style="width:auto;"data-option=''>
           </td>
           <td width="5%"  height="20" align="left" nowrap>出生日期：</td>
            <td width="14%" height="20" align="left" nowrap>
               <input id="birthdate" name="birthdate" class="easyui-datebox"  style="ime-mode:Disabled"/>
            </td>
       </tr>
       <tr align="center">
            <td width="5%"  height="20" align="left" nowrap>社保号：</td>
           <td width="13%" height="20" align="left" nowrap>
           <input name="socialsecurityid" id="socialsecurityid" value="" maxlength="20" style="ime-mode:Disabled"/></td>
            <td width="5%"  height="20" align="left" nowrap>用户CODE：</td>
           <td width="13%" height="20" align="left" nowrap>
           <input name="usercode" id="usercode" maxlength="20"  class="easyui-validatebox tb" data-options="required:true,validType:'length[1,20]'"  /><strong><font color="red" >*</font></strong></td>
       </tr>
       
       <tr align="center">
           <td width="5%" height="20" align="left" nowrap>用户寸照：</td>
           <td width="13%" height="20" align="left" nowrap colspan="1">
           <input class="easyui-filebox" name="photoname1"  style="width:300;margin-top:10px">
           </td>
           <td width="5%" height="20" align="left" nowrap>签名图片：</td>
           <td width="13%" height="20" align="left" nowrap colspan="1">
           <input class="easyui-filebox" name="signatureimg1"  style="width:300;margin-top:10px">
           </td>
       
       </tr>
    </table>
    
	
	</td>
  </tr>
  
  <tr>
  <table width="100%" border="0" cellpadding="0" cellspacing="8">
    
     
      <td background="">
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
               <tr><td colspan="6"  class="style2"><font color="blue">员工通讯信息</font></td></tr>
               </table></td>
           </tr>          
     <tr>
    <td >
     
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="">
	  
	      <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>家庭地址：</td>
            <td width="12%" height="20" align="left" nowrap>
            <input name="addr" id="addr" value="" maxlength="80"/></td>
           <td width="5%" height="20" align="left" nowrap>家庭电话：</td>
           <td width="14%" height="20" align="left" nowrap>
              <input name="telnum" id="telnum" value="" maxlength="13" style="ime-mode:Disabled" />
              </td>
	       <td width="5%"  height="20" align="left" nowrap>移动电话：</td>
           <td width="13%" height="20" align="left" nowrap>
           <input name="phonenum" id="phonenum" value="" maxlength="11" style="ime-mode:Disabled" /></td>

       </tr>
       
        <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>办公电话：</td>
            <td width="12%" height="20" align="left" nowrap>
            <input name="officenum" id="officenum" value="" maxlength="13" style="ime-mode:Disabled" /></td>
           <td width="5%" height="20" align="left" nowrap>QQ号码：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="qqnum" id="qqnum" value="" maxlength="12" style="ime-mode:Disabled"/></td>
	       <td width="=5%"  height="20" align="left" nowrap>公司邮件：</td>
           <td width="13%" height="20" align="left" nowrap>
           <input name="companyemail" id="companyemail" /></td>
       </tr>
       <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>毕业学校：</td>
            <td width="12%" height="20" align="left" nowrap>
            <input name="school" id="school" value="" maxlength="40" /></td>
           <td width="5%" height="20" align="left" nowrap>电子邮件：</td>
           <td width="14%" height="20" align="left" colspan="1" nowrap>
           <input name="email" id="email" value="" maxlength="80" /></td>
	       <td width="5%" height="20" align="left" nowrap>手机短号：</td>
           <td width="14%" height="20" align="left" nowrap>
              <input name="shorttelnum" id="shorttelnum" value="" maxlength="13" style="ime-mode:Disabled"/>
              </td>

       </tr>
    </table>
    
	
	</td>
  </tr>
  </table>
  </tr>
  <tr>
  <table width="100%" border="0" cellpadding="0" cellspacing="8">
    <td>
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
               <tr><td colspan="6"  class="style2"><font color="blue">员工技能信息</font></td></tr>
               </table></td>
           </tr>
     <tr>
    <td >
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="">
	  
	      <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>专业：</td>
            <td width="12%" height="20" align="left" nowrap>
               <input name="major" id="major" value="" maxlength="20" />
            </td>
           <td width="5%" height="20" align="left" nowrap>学历：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="education" id="education" class="easyui-combobox"style="width:auto;"data-option=''>
           </td>
	       <td width="5%"  height="20" align="left" nowrap>外语语种：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="foreignlanguage" id="foreignlanguage" class="easyui-combobox"style="width:auto;"data-option=''>
       </tr>
        <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>外语水平：</td>
            <td width="12%" height="20" align="left" nowrap>
            <input name="foreignlanguagelevel" id="foreignlanguagelevel" value="" maxlength="10" /></td>
           <td width="5%" height="20" align="left" nowrap>计算机水平：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="computerlevel" id="computerlevel" value="" maxlength="10" /></td>
	       <td width="5%"  height="20" align="left" nowrap>特长：</td>
           <td width="13%" height="20" align="left" nowrap><input name="specialty" id="specialty" value="" maxlength="100"/></td>

       </tr>
    </table>
    
	
	</td>
  </tr>
  </table>
  </tr>
  <tr>
  <td>
  <table width="100%" border="0" cellpadding="0" cellspacing="8">
     <td background="../images/pagetitle_bg.jpg">
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
               <tr><td colspan="6"  class="style2"><font color="blue">员工工作信息</font></td></tr>
               </table></td>
           </tr>
     <tr>
    <td >
     
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="">
	  
	      <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>岗位：</td>
            <td width="12%" height="20" align="left" nowrap>
			    <input name="userpostid" id="userpostid" class="easyui-combobox"style="width:auto;"data-option=''>
            </td>
           <td width="5%" height="20" align="left" nowrap>在职状况：</td>
           <td width="14%" height="20" align="left" nowrap>
              <select name="workstatus" id="workstatus" class="easyui-combobox">
              					<option value="10" selected>在职</option>
                                  <option value="0">离职</option>
                                  <option value="20">退休</option>
               </select>
           </td>
	       <td width="5%"  height="20" align="left" nowrap>职称：</td>
           <td width="13%" height="20" align="left" nowrap>
           <input name="title" id="title" value="" maxlength="40"/></td>

       </tr>
       
        <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>入单位时间：</td>
            <td width="12%" height="20" align="left" nowrap>
            <input name="joinunitdate" id="joinunitdate" value="" class="easyui-datebox"  style="ime-mode:Disabled" /></td>
            <td width="5%" height="20" align="left" nowrap>离职时间：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="dimissiondate" id="dimissiondate" value="" class="easyui-datebox"  style="ime-mode:Disabled" /></td>
	       <td width="5%"  height="20" align="left" nowrap>本单位工龄：</td>
           <td width="13%" height="20" align="left" nowrap><input name="seniority" id="seniority" value="" maxlength="2" style="ime-mode:Disabled"/></td>

       </tr>
       <tr align="center" >
       		<td width="5%" height="20" align="left" nowrap>参加工作时间：</td>
            <td width="14%" height="20" align="left" nowrap>
            <input name="joinworkdate" id="joinworkdate" value="" class="easyui-datebox"  style="ime-mode:Disabled" /></td>
            <td width="5%" height="20" align="left" nowrap>签合同时间：</td>
            <td width="12%" height="20" align="left" nowrap>
            <input name="bgbegdate" id="bgbegdate" value="" class="easyui-datebox"  style="ime-mode:Disabled" /></td>
           <td width="5%" height="20" align="left" nowrap>合同终止时间：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="bgenddate" id="bgenddate" value="" class="easyui-datebox"  style="ime-mode:Disabled" /></td>
       </tr>
    
        <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>银行名称：</td>
           
             <td width="14%" height="20" align="left" nowrap>
             <input name="bankid" id="bankid" class="easyui-combobox"style="width:auto;"data-option=''>
           </td>
           <td width="5%" height="20" align="left" nowrap>银行卡号：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="bankcard" id="bankcard" value="" maxlength="20" style="ime-mode:Disabled"/></td>
            <td width="5%" height="20" align="left" nowrap>工作工龄：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="selfseniority" id="selfseniority" value="" maxlength="2" style="ime-mode:Disabled"/></td>
       </tr>
       <tr>
           <td width="5%" height="20" align="left" nowrap>外派来源：</td>
           <td width="14%" height="20" align="left" nowrap colspan="1">
           <input name="worksource" id="worksource" class="easyui-combobox"style="width:auto;"data-option=''>
           </td>
           <td width="5%" height="20" align="left" nowrap>用户类型：</td>
           <td width="14%" height="20" align="left" nowrap colspan="1">
                <select name="usertype" id=""usertype"" class="easyui-combobox">
                         <option value="10" selected>内部用户</option>
				         <option value="20">外部用户</option>
				         <option value="30">虚拟用户</option>
                  </select>
            </td>
            <td width="5%" height="20" align="left" nowrap>用户角色：</td>
           <td width="14%" height="20" align="left" nowrap colspan="1">
           <input name="roleid" id="roleid" class="easyui-combobox"style="width:auto;"data-option=''>
           </td>
       </tr>
    </table>
	</td>
  </tr>
			</div>
			<br>
		</form>
	</div>
	</table></td></tr></table></tr></div></form></div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveAdd()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#add-dlg').dialog('close')">取消</a>
	</div>
	<!-- 修改对话框 -->
	<div id="edit-dlg" class="easyui-dialog" style="width:850px;height:400px;padding:10px 20px;align:center;" closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<div class="ftitle"></div>
		<form  id="edit-fm" method="post" action="" novalidate  enctype="multipart/form-data" >
		<div class="fitem"><input type=hidden name=id id=id>
  			<tr>
  <table width="100%" border="0" cellpadding="0" cellspacing="8">
      <td background="">
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
               <tr><td colspan="6"  class="style2"><font color="blue">员工基础信息</font></td></tr>
               </table></td>
           </tr>          
     <tr>
    <td >
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="">
        <tr align="center" >
            <td width="5%"  height="20" align="left" nowrap>用户CODE：</td>
           <td width="13%" height="20" align="left" nowrap>
           <input name="usercode" id="usercode" maxlength="20"  class="easyui-validatebox tb" data-options="required:true,validType:'length[1,20]'"  /><strong><font color="red" >*</font></strong></td>
           <td width="5%" height="20" align="left" nowrap>姓名：</td>
           <td width="14%" height="20" align="left" nowrap>
                <input name="username" id="username" class="easyui-validatebox tb" data-options="required:true,validType:'length[2,10]'"><strong><font color="red" >*</font></strong>
           </td>
           
           <td width="12%" height="20" align="left" nowrap rowspan="5" colspan="2">
					<img id="photoname" name="photoname" src=""  /><input type=hidden name=signatureimg id=signatureimg>
								&nbsp;
			</td>   
       </tr>
       
       <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>性别：</td>
            <td width="14%" height="20" align="left" nowrap>
                   <select name="sex" id="sex" class="easyui-combobox">
                   	    <option value="1">男</option>
						<option value="0"  >女</option>
					</select>
            </td>
           <td width="5%" height="20" align="left" nowrap>婚姻状况：</td>
            <td width="14%" height="20" align="left" nowrap>
            
                <select name="ismarried" id="ismarried" class="easyui-combobox">
                         <option value="0" >未婚</option>
				         <option value="1">已婚</option>
				         <option value="2">离异</option>
                  </select>
            
            </td>

       </tr>
       <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>民族：</td>
            <td width="14%" height="20" align="left" nowrap>
                  <input name="nation" id="nation1" class="easyui-combobox"style="width:auto;"data-option=''>
            </td>
           <td width="5%" height="20" align="left" nowrap>政治面貌：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="politicalstatus" id="politicalstatus1" class="easyui-combobox"style="width:auto;"data-option=''>
           </td>
       </tr>
       <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>户口：</td>
            <td width="12%" height="20" align="left" nowrap>
                 <input type="text" name="idregister" id="idregister" maxlength="38"/>
            </td>
           <td width="5%" height="20" align="left" nowrap>籍贯：</td>
           <td width="14%" height="20" align="left" nowrap>
               <input type="text" name="nativeplace" id="nativeplace" maxlength="38" />
           </td>
       </tr>
       <tr align="center">
            <td width="5%"  height="20" align="left" nowrap>部门：</td>
           <td width="13%" height="20" align="left" nowrap>
           <input name="deptid" id="deptid1" class="easyui-combobox"style="width:auto;"data-option=''>
           </td>
           <td width="5%"  height="20" align="left" nowrap>出生日期：</td>
            <td width="14%" height="20" align="left" nowrap>
               <input id="birthdate" name="birthdate" class="easyui-datebox"  style="ime-mode:Disabled"/>
            </td>
       </tr>
       <tr align="center">
            <td width="5%"  height="20" align="left" nowrap>社保号：</td>
           <td width="13%" height="20" align="left" nowrap>
           <input name="socialsecurityid" id="socialsecurityid" value="" maxlength="20" style="ime-mode:Disabled"/></td>
           <td width="5%"  height="20" align="left" nowrap>身份证号码：</td>
           <td width="13%" height="20" align="left" nowrap>
               <input name="idnum" id="idnum" value="" maxlength="18"  class="easyui-validatebox tb" data-options="validType:'length[1,18]'" style="ime-mode:Disabled"/>
           </td>
           <td width="13%" height="20" align="left" nowrap colspan="2">
           <input class="easyui-filebox" name="photoname1" data-options="prompt:'重新上传...'"style="width:400;margin-top:10px">
           </td>
       </tr>
       
    </table>
    
	
	</td>
  </tr>
  
  <tr>
  <table width="100%" border="0" cellpadding="0" cellspacing="8">
    
     
      <td background="">
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
               <tr><td colspan="6"  class="style2"><font color="blue">员工通讯信息</font></td></tr>
               </table></td>
           </tr>          
     <tr>
    <td >
     
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="">
	  
	      <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>家庭地址：</td>
            <td width="12%" height="20" align="left" nowrap>
            <input name="addr" id="addr" value="" maxlength="80"/></td>
           <td width="5%" height="20" align="left" nowrap>家庭电话：</td>
           <td width="14%" height="20" align="left" nowrap>
              <input name="telnum" id="telnum" value="" maxlength="13" style="ime-mode:Disabled" />
              </td>
	       <td width="5%"  height="20" align="left" nowrap>移动电话：</td>
           <td width="13%" height="20" align="left" nowrap>
           <input name="phonenum" id="phonenum" value="" maxlength="11" style="ime-mode:Disabled" /></td>

       </tr>
       
        <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>办公电话：</td>
            <td width="12%" height="20" align="left" nowrap>
            <input name="officenum" id="officenum" value="" maxlength="13" style="ime-mode:Disabled" /></td>
           <td width="5%" height="20" align="left" nowrap>QQ号码：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="qqnum" id="qqnum" value="" maxlength="12" style="ime-mode:Disabled"/></td>
	       <td width="=5%"  height="20" align="left" nowrap>公司邮件：</td>
           <td width="13%" height="20" align="left" nowrap>
           <input name="companyemail" id="companyemail" /></td>
       </tr>
       <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>毕业学校：</td>
            <td width="12%" height="20" align="left" nowrap>
            <input name="school" id="school" value="" maxlength="40" /></td>
           <td width="5%" height="20" align="left" nowrap>电子邮件：</td>
           <td width="14%" height="20" align="left" colspan="1" nowrap>
           <input name="email" id="email" value="" maxlength="80" /></td>
	       <td width="5%" height="20" align="left" nowrap>手机短号：</td>
           <td width="14%" height="20" align="left" nowrap>
              <input name="shorttelnum" id="shorttelnum" value="" maxlength="13" style="ime-mode:Disabled"/>
              </td>

       </tr>
    </table>
    
	
	</td>
  </tr>
  </table>
  </tr>
  <tr>
  <table width="100%" border="0" cellpadding="0" cellspacing="8">
    <td>
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
               <tr><td colspan="6"  class="style2"><font color="blue">员工技能信息</font></td></tr>
               </table></td>
           </tr>
     <tr>
    <td >
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="">
	  
	      <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>专业：</td>
            <td width="12%" height="20" align="left" nowrap>
               <input name="major" id="major" value="" maxlength="20" />
            </td>
           <td width="5%" height="20" align="left" nowrap>学历：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="education" id="education1" class="easyui-combobox"style="width:auto;"data-option=''>
           </td>
	       <td width="5%"  height="20" align="left" nowrap>外语语种：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="foreignlanguage" id="foreignlanguage1" class="easyui-combobox"style="width:auto;"data-option=''>
       </tr>
        <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>外语水平：</td>
            <td width="12%" height="20" align="left" nowrap>
            <input name="foreignlanguagelevel" id="foreignlanguagelevel" value="" maxlength="10" /></td>
           <td width="5%" height="20" align="left" nowrap>计算机水平：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="computerlevel" id="computerlevel" value="" maxlength="10" /></td>
	       <td width="5%"  height="20" align="left" nowrap>特长：</td>
           <td width="13%" height="20" align="left" nowrap><input name="specialty" id="specialty" value="" maxlength="100"/></td>

       </tr>
    </table>
    
	
	</td>
  </tr>
  </table>
  </tr>
  <tr>
  <td>
  <table width="100%" border="0" cellpadding="0" cellspacing="8">
     <td background="../images/pagetitle_bg.jpg">
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
               <tr><td colspan="6"  class="style2"><font color="blue">员工工作信息</font></td></tr>
               </table></td>
           </tr>
     <tr>
    <td >
     
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="">
	  
	      <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>岗位：</td>
            <td width="12%" height="20" align="left" nowrap>
			    <input name="userpostid" id="userpostid1" class="easyui-combobox"style="width:auto;"data-option=''>
            </td>
           <td width="5%" height="20" align="left" nowrap>在职状况：</td>
           <td width="14%" height="20" align="left" nowrap>
              <select name="workstatus" id="workstatus" class="easyui-combobox">
              					<option value="10" >在职</option>
                                  <option value="0">离职</option>
                                  <option value="20">退休</option>
               </select>
           </td>
	       <td width="5%"  height="20" align="left" nowrap>职称：</td>
           <td width="13%" height="20" align="left" nowrap>
           <input name="title" id="title" value="" maxlength="40"/></td>

       </tr>
       
        <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>入单位时间：</td>
            <td width="12%" height="20" align="left" nowrap>
            <input name="joinunitdate" id="joinunitdate" value="" class="easyui-datebox"  style="ime-mode:Disabled" /></td>
            <td width="5%" height="20" align="left" nowrap>离职时间：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="dimissiondate" id="dimissiondate" value="" class="easyui-datebox"  style="ime-mode:Disabled" /></td>
	       <td width="5%"  height="20" align="left" nowrap>本单位工龄：</td>
           <td width="13%" height="20" align="left" nowrap><input name="seniority" id="seniority" value="" maxlength="2" style="ime-mode:Disabled"/></td>

       </tr>
       <tr align="center" >
       		<td width="5%" height="20" align="left" nowrap>参加工作时间：</td>
            <td width="14%" height="20" align="left" nowrap>
            <input name="joinworkdate" id="joinworkdate" value="" class="easyui-datebox"  style="ime-mode:Disabled" /></td>
            <td width="5%" height="20" align="left" nowrap>签合同时间：</td>
            <td width="12%" height="20" align="left" nowrap>
            <input name="bgbegdate" id="bgbegdate" value="" class="easyui-datebox"  style="ime-mode:Disabled" /></td>
           <td width="5%" height="20" align="left" nowrap>合同终止时间：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="bgenddate" id="bgenddate" value="" class="easyui-datebox"  style="ime-mode:Disabled" /></td>
       </tr>
    
        <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>银行名称：</td>
           
             <td width="14%" height="20" align="left" nowrap>
             <input name="bankid" id="bankid1" class="easyui-combobox"style="width:auto;"data-option=''>
           </td>
           <td width="5%" height="20" align="left" nowrap>银行卡号：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="bankcard" id="bankcard" value="" maxlength="20" style="ime-mode:Disabled"/></td>
            <td width="5%" height="20" align="left" nowrap>工作工龄：</td>
           <td width="14%" height="20" align="left" nowrap>
           <input name="selfseniority" id="selfseniority" value="" maxlength="2" style="ime-mode:Disabled"/></td>
       </tr>
       <tr>
           <td width="5%" height="20" align="left" nowrap>外派来源：</td>
           <td width="14%" height="20" align="left" nowrap colspan="1">
           <input name="worksource" id="worksource1" class="easyui-combobox"style="width:auto;"data-option=''>
           </td>
           <td width="5%" height="20" align="left" nowrap>用户类型：</td>
           <td width="14%" height="20" align="left" nowrap colspan="1">
                <select name="usertype" id=""usertype"" class="easyui-combobox">
                         <option value="10" >内部用户</option>
				         <option value="20">外部用户</option>
				         <option value="30">虚拟用户</option>
                  </select>
            </td>
            <td width="5%" height="20" align="left" nowrap>用户角色：</td>
           <td width="14%" height="20" align="left" nowrap colspan="1">
           <input name="roleid" id="roleid1" class="easyui-combobox"style="width:auto;"data-option=''>
           </td>
       </tr>
    </table>
	</td>
  </tr>
			</div>
			<br>
		</form>
	</div>
	</table></td></tr></table></tr></div></form></div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveEdit()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#edit-dlg').dialog('close')">取消</a>
	</div>
	
	<!-- 授权对话框 -->
	<div id="grant-dlg" class="easyui-dialog" style="width:450px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#grant-dlg-buttons">
		<div class="ftitle"></div>
		<form id="grant-fm" method="post"  validate action="" >
			<input type="hidden" id="id" name="id"/>
			<div class="fitem">
    <tr>
  <table width="100%" border="0" cellpadding="0" cellspacing="8">
     <tr>
    <td >
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="">
        <tr align="center" >
            <input type=hidden name=userid id=userid>
           <td width="5%" height="20" align="left" nowrap>用户工号：</td>
           <td id="grantusercode" width="14%" height="20" align="left" nowrap>
           </td>
           <td width="5%"  height="20" align="left" nowrap>用户姓名：</td>
           <td id="grantusername" width="14%" height="20" align="left" nowrap>
           </td>   
       </tr>
        <tr align="center" >
            
           <td width="5%" height="20" align="left" nowrap>用户部门：</td>
           <td id="deptname" width="14%" height="20" align="left" nowrap>
                
           </td>
           <td width="5%"  height="20" align="left" nowrap>用户岗位：</td>
           <td id="postname" width="14%" height="20" align="left" nowrap>
           </td>   
       </tr>
        <tr align="center" >
            
           <td width="5%" height="20" align="left" nowrap>授权角色：</td>
           <td width="14%" height="20" align="left" colspan=3 nowrap>
                <input name="roleid" id="roleid2" class="easyui-combobox"style="width:auto;"data-option=''>
           </td>
       </tr>
    </table>
			</div><br>
			
		</form>
	</div>
	<div id="grant-dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveGrant()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#grant-dlg').dialog('close')">取消</a>
	</div>
	
	<!-- 进度条，建议对于耗时长的操作才启用
	<div id="loading">
		<div class="inputdiv" >
			<img  src="/BS56/img/loading.gif" style="padding-top: 20px; padding-left:72px;"/><br>
			<h3 style="padding-left: 35px;">&nbsp;&nbsp;&nbsp;&nbsp;数据上传中,请稍候......</h3>
		</div>
	 </div>
	  -->
	  <c:import url="/WEB-INF/jsp/pub/sessionPage.jsp?paramPage=User"></c:import>	
  </body>
</html>
