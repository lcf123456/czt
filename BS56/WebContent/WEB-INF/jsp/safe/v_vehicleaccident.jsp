<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>

<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/safe/vehicleaccident.js"/>"></script>
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
			<a href="#" id="viewBtn" class="easyui-linkbutton" iconCls="icon-view" plain="true" onclick="openView()">查看</a>
			<input name="routecode" id="routecode" class="easyui-combobox" style="width:90px;" data-options="" >
			</input>
			<input class="easyui-textbox" id="keyword" name="keyword"  data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchAccident();},prompt:'请输入驾驶员/车牌号码/事故金额...'" style="width:350px;height:24px;">
			
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>
	
	
	<!-- 查看对话框 -->
	<div id="view-dlg" class="easyui-dialog" style="width:550px;height:450px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#view-dlg-buttons">
		<div class="ftitle"></div>
		<form id="view-fm" method="post" action="" novalidate  >
			<div class="fitem">
				<table width="100%" border="0" cellpadding="0" cellspacing="6">
            <tr>
              <td> <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="">
              <tr><td colspan="4"  class="style2"><font color="blue">事故管理</font></td></tr>
               </table></td>
           </tr>           
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="">
        <tr align="center" >
           <td width="5%" height="20" align="left" nowrap>车组CODE:</td>
           <td id="v-routecode"class="formtd" font style=" color:darkblue" width="20%" height="20" align="left" nowrap>
           </td>
        <td width="5%" height="20" align="left" nowrap>车牌号码：</td>
            <td id="v-vehicleno" class="formtd" font style="color:darkblue;" width="20%" height="20" align="left" nowrap>
            </td></tr> 
            
       <tr align="center" >
       <td width="5%" height="20" align="left"  nowrap>驾驶员姓名：</td>
            <td id="v-drivername"class="formtd" font style=" color:darkblue" width="20%" height="20" align="left" nowrap>
            </td>
            <td width="5%" height="20" align="left" nowrap>发生地段：</td>
            <td id="v-locale"class="formtd" font style=" color:darkblue" width="20%" height="20" align="left" nowrap>
            </td></tr>
       <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>发生日期：</td>
            <td id="v-happendate" class="formtd" font style="color:darkblue;" width="20%" height="20"  align="left" nowrap>
            </td>
            <td width="5%" height="20" align="left" nowrap>发生金额：</td>
            <td id="v-amount"class="formtd" font style=" color:darkblue" width="20%" height="20"  align="left"  nowrap>
            </td>
         <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>车速：</td>
            <td id="v-speed" class="formtd" font style="color:darkblue;" width="20%" height="20"  align="left" nowrap>
            </td>
            <td width="5%" height="20" align="left" nowrap>驾驶员情绪：</td>
            <td id="v-emotion"class="formtd" font style=" color:darkblue" width="20%" height="20"  align="left"  nowrap>
            </td>
          <tr>
          <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>事故等级：</td>
            <td id="v-register" class="formtd" font style="color:darkblue;" width="20%" height="20"  align="left" nowrap>
            </td>
            <td width="5%" height="20" align="left" nowrap>事故责任：</td>
            <td id="v-liable"class="formtd" font style=" color:darkblue" width="20%" height="20"  align="left"  nowrap>
            </td>
         <tr align="center" >
            <td width="5%" height="20" align="left" nowrap>伤亡情况伤：</td>
            <td id="v-injurynum" class="formtd" font style="color:darkblue;" width="20%" height="20"  align="left" nowrap>
            </td>
            <td width="5%" height="20" align="left" nowrap>伤亡情况亡：</td>
            <td id="v-deadnum"class="formtd" font style=" color:darkblue" width="20%" height="20"  align="left"  nowrap>
            </td>
            <tr align="center">
            <td width="5%"  height="20" align="left" nowrap>事故描述：</td>
            <td id="v-note" class="formtd" colspan=3 font style="color:darkblue;"width="14%" height="20" align="left" >
           </td>
          <tr>
            <tr align="center">
            <td width="5%"  height="20" align="left" nowrap>车辆损坏情况：</td>
           <td id="v-damagenote" class="formtd" colspan=3 font style="color:darkblue;" width="14%" height="20" align="left" >
           </td>
          <tr>
            <tr align="center">
            <td width="5%"  height="20" align="left" nowrap>事故分析：</td>
            <td id="v-analy" class="formtd" colspan=3 font style="color:darkblue;" width="14%" height="20" align="left" >
           </td>
          <tr>
            <tr align="center">
            <td width="5%" height="20" align="left" nowrap>整改措施：</td>
              <td id="v-rectify" class="formtd" colspan=3 font style="color:darkblue;" width="14%" height="20" align="left" >
           </td>
           </td>
           
       </tr>
    </table>
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
	 </div>-->
   <c:import url="/WEB-INF/jsp/pub/sessionPage.jsp?paramPage=VehicleAccident"></c:import>	 
  </body>
</html>
