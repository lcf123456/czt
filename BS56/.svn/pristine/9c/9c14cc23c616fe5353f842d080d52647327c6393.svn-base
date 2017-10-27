<%@page import="com.ztel.framework.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/sq/routescore.js"/>"></script>
  </head>
    <script type="text/javascript">
	var sourceid="${sourceid}";
	</script>
  <body>
	
	
	<!-- datagrid页面列表数据 -->
	<div style="padding:10" id="tabdiv">
		<table id="dataTable"></table>
	</div>

	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;border-top:1px solid #95B8E7">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">
			<a href="#" id="viewBtn" class="easyui-linkbutton" iconCls="icon-view" plain="true" onclick="openView()">查看</a>
			<a href="#"  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleterow()">删除</a>
			<text name="scoringtime" id="time" class="easyui-datebox" style="width:auto;" value="<%=DateUtil.getyyyy_mm_dd()%>"></text>
			<input class="easyui-textbox"  name="keywd" id="keywd"  data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchRoutescore();},prompt:'请选择得分时间或输入车组...'" style="width:450px;height:24px;">
			
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>
	
	
	<!-- 查看对话框 -->
	<div id="view-dlg" class="easyui-dialog" style="width:550px;height:500px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#view-dlg-buttons">
		<div class="ftitle"></div>
		<form id="view-fm" method="post" action="" novalidate  >
			<div class="fitem">
				<table width="100%" border="0" cellpadding="0" cellspacing="6">
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
              <tr><td colspan="4"  class="style2"><font color="blue">考核信息</font></td></tr>
               </table></td>
           </tr>           
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="">
        <tr align="center" >
           <td width="5%" height="20" align="left" nowrap>回访车组CODE:</td>
           <td id="v-routecode"class="formtd" font style=" color:darkblue" width="14%" height="20" align="left" nowrap>
           </td>
           <td width="5%"  height="20" align="left" nowrap>回访车组名称:</td>
           <td id="routecode"class="formtd" font style=" color:darkblue" width="13%" height="20" align="left" nowrap>
           </td>   
       </tr>
       <tr align="center" >
            <td width="5%" height="20" align="left" id="v-dname" nowrap >得分:</td>
            <td id="v-dscore"class="formtd" font style=" color:darkblue" width="14%" height="20" align="left" nowrap>
           </td>
           <td width="5%" height="20" align="left" id="v-cname" nowrap>得分：</td>
            <td id="v-cscore"class="formtd" font style=" color:darkblue" width="14%" height="20" align="left" nowrap>
                
            </td>
       </tr>
       <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>得分时间：</td>
            <td id="v-scoringtime"class="formtd" font style=" color:darkblue" width="14%" height="20" align="left" nowrap>
            </td>
            <td width="5%" height="20" align="left" nowrap>记录人：</td>
            <td id="v-createname" class="formtd" font style="color:darkblue;" width="14%" height="20" align="left" nowrap>
            </td>         
          <tr>
            <tr align="center">
            <td width="5%"  height="20" align="left" nowrap>备注信息：</td>
           <td width="13%" height="20" align="left" colspan=5 nowrap>
           <input id="v-remarks" rows="2" name="remarks" style="width: 340px; height: 72px;color:darkblue;" maxlength="190"  >
           </td>
       </tr>
    </table>
  
  <div class="fitem">
				<table width="100%" border="0" cellpadding="0" cellspacing="6">
            <tr><tr><tr><tr></tr></tr></tr></tr>
  <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
  <tr><td colspan="4"  class="style2"><font color="blue">单项考核得分信息</font></td></tr>
                 </table></td>
           </tr>         
</table>
			</div>
			
			<br>
				<div style="padding:8" id="tabdiv1">
		<table id="dataTable1" ShowHeader="false"></table>
	</div>
		</form>
	</div>		
	<div id="view-dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#view-dlg').dialog('close')">关闭</a>
	</div>
	
	<!-- 进度条，建议对于耗时长的操作才启用
	<div id="loading">
		<div class="inputdiv" >
			<img  src="/BS56/img/loading.gif" style="padding-top: 20px; padding-left:72px;"/><br>
			<h3 style="padding-left: 35px;">&nbsp;&nbsp;&nbsp;&nbsp;数据上传中,请稍候......</h3>
		</div>
	 </div>
	-->
<c:import url="/WEB-INF/jsp/pub/sessionPage.jsp?paramPage=Routescore"></c:import>	
  </body>
</html>
