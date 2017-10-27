<%@page import="com.ztel.framework.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/cost/vehiclerepair.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/jquery.PrintArea.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/jquery.jqprint-0.3.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/jquery-migrate-1.2.1.min.js"/>"></script>
  </head>
  <script type="text/javascript">
	var showFlag="${showFlag}";
	</script>
  <body>
	
	
	<!-- datagrid页面列表数据 -->
	<div style="padding:10" id="tabdiv">
		<table id="dataTable"></table>
	</div>
	
	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">
			<a href="#" id= "newBtn"  class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openView();">查看</a>
			<a href="#" id= "newBtn"  class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openNew();">申报</a>
			<a href="#" id= "newBtn"  class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openAudit();">审核</a>
			<a href="#"  id="refundBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="openReceiptList();">回单</a>
			<a href="#"  id="refundBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="openPrint();">打印</a>
			时间:<input id=begdate name=begdate class="easyui-datebox" style="width:100px;" >到<input id=enddate name=enddate class="easyui-datebox" style="width:100px;" >
			<input class="easyui-textbox"  name="param"  id=param data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchData();},prompt:'请输入车牌/车组/记录人...'" style="width:220px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>
	
					<!-- 1、新增对话框 --------------------------------------------------------------------------------------->
					<div id="add-dlg" class="easyui-dialog" style="width:700px;height:400px;padding:5px 10px;align:center;"
							 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
						<form  id="add-fm" method="post" action=""   >
							<div >
           <tr>
	    	<td >
		  	 <table width="100%" border="0" cellpadding="2" cellspacing="2" >
		  	 	<tr>
	           	<td width="5%"  height="20" align="left" nowrap>查询条件：</td>
		           	<td width="13%" height="20" align="left" colspan=3  nowrap>
		           	<input class="easyui-textbox"  name="keywd"  id=keywd data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchVehicle();},prompt:'请输入车组或车牌...'" style="width:200px;height:24px;">
		           </td> 
	           </tr>
		  	  <tr>
		  		 	<td width="5%"  height="20" align="left" nowrap>车组名称：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		               <input name="routecode" id="routecode" value="" class="easyui-textbox"  data-options="required:true,validType:'length[0,50]'" readonly/>
		           </td> 
		           <td width="5%"  height="20" align="left" nowrap>车牌号码：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		               <input name="vehicleno" id="vehicleno" class="easyui-textbox"  style="width:auto;" data-options="required:true,validType:'length[0,50]'"  readonly >
		               <input type="hidden" name="vehicleid" id="vehicleid" class="easyui-textbox"  />
		           </td>
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>车辆型号：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		                <input name="vehicletype" id="vehicletype"  class="easyui-textbox"  data-options="validType:'length[1,30]'" readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>司机姓名：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		               <input name="drivername" id="drivername" value="" class="easyui-textbox"   data-options="required:true,validType:'length[0,50]'" readonly/>
		               <input type="hidden" name="driverid" id="driverid"class="easyui-textbox"   />
		           </td>  
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>申报日期：</td>
		           	<td width="13%" height="20" align="left" colspan=3 nowrap>
		                <input id=applydate name=applydate class="easyui-datebox" style="width:100px;" >
		           	</td>
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>维保项目：</td>
		           	<td width="13%" height="20" align="left"colspan=3  nowrap>
		           		<input name="lvl1" id="lvl1" class="easyui-combobox"style="width:auto;"  >
						<input name="lvl2" id="lvl2" class="easyui-combobox"style="width:auto;"  >
						<input name="repairinfoid" id="repairinfoid" class="easyui-combobox"style="width:auto;"  >
		               <input type="hidden" name=repairinfo id="repairinfo" />
		           </td>  
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>维保项目：</td>
		           	<td width="13%" height="20" align="left" colspan=3  nowrap>
		                <textarea id="mainitem"  name="mainitem"  cols="60" rows="2"></textarea>
		           	</td>
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>备注信息：</td>
		           	<td width="13%" height="20" align="left" colspan=3  nowrap>
		                <textarea id="remarks"  name="remarks"  cols="60" rows="2"></textarea>
		           	</td>
	           </tr>
	           
	           </table>
			</div>
			<br>
		</form>
	</div>
	</table></td></tr></table></tr></div></form></div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveAdd()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#add-dlg').dialog('close');">取消</a>
	</div>
	
	<!-- 2、分部主任审核对话框 --------------------------------------------------------------------------------------->
	<div id="audit-dlg" class="easyui-dialog" style="width:700px;height:400px;padding:5px 10px;align:center;"
							 closed="true" buttons="#auditdlg-buttons"  data-options="modal:true,draggable:false">
						<form  id="audit-fm" method="post" action=""   >
	<div >
           <tr>
	    	<td >
		  	 <table width="100%" border="0" cellpadding="2" cellspacing="2" >
		  	  <tr>
		  		 	<td width="5%"  height="20" align="left" nowrap>车组名称：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		               <input name="routecode" id="routecode" value="" class="easyui-textbox"  data-options="required:true,validType:'length[0,50]'" readonly/>
		           </td> 
		           <td width="5%"  height="20" align="left" nowrap>车牌号码：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		               <input name="vehicleno" id="vehicleno" class="easyui-textbox"  style="width:auto;" data-options="required:true,validType:'length[0,50]'"  readonly >
		               <input type="hidden" name="vehicleid" id="vehicleid" class="easyui-textbox"  />
		               <input type="hidden" name="id" id="id" class="easyui-textbox"  />
		               <input type="hidden" name="status" id="status" class="easyui-textbox"  />
		           </td>
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>车辆型号：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		                <input name="vehicletype" id="vehicletype"  class="easyui-textbox"  data-options="validType:'length[1,30]'" readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>司机姓名：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		               <input name="drivername" id="drivername" value="" class="easyui-textbox"   data-options="required:true,validType:'length[0,50]'" readonly/>
		               <input type="hidden" name="driverid" id="driverid"class="easyui-textbox"   />
		           </td>  
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>申报日期：</td>
		           	<td width="13%" height="20" align="left" colspan=3 nowrap>
		                <input id=applydate name=applydate class="easyui-datebox" style="width:100px;" readonly>
		           	</td>
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>维保项目：</td>
		           	<td width="13%" height="20" align="left" colspan=3  nowrap>
		                <textarea id="mainitem"  name="mainitem"  cols="60" rows="2"readonly></textarea>
		           	</td>
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>备注信息：</td>
		           	<td width="13%" height="20" align="left" colspan=3  nowrap>
		                <textarea id="remarks"  name="remarks"  cols="60" rows="2"readonly></textarea>
		           	</td>
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>分部主任审核意见：</td>
		           	<td width="13%" height="20" align="left" colspan=3  nowrap>
		                <textarea id="leadersuggestion"  name="leadersuggestion"  cols="60" rows="2">同意维修</textarea>
		           	</td>
	           </tr>
	           
	           </table>
			</div>
			<br>
		</form>
	</div>
	</table></td></tr></table></tr></div></form></div>
	<div id="auditdlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveAudit('40')">通过</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="saveAudit('50')">驳回</a>
	</div>
	
	<!-- 3、车管员审核对话框 --------------------------------------------------------------------------------------->
<div id="safeaudit-dlg" class="easyui-dialog" style="width:700px;height:400px;padding:5px 10px;align:center;"
							 closed="true" buttons="#safeauditdlg-buttons"  data-options="modal:true,draggable:false">
						<form  id="safeaudit-fm" method="post" action=""   >
	<div >
           <tr>
	    	<td >
		  	 <table width="100%" border="0" cellpadding="2" cellspacing="2" >
		  	  <tr>
		  		 	<td width="5%"  height="20" align="left" nowrap>车组名称：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		               <input name="routecode" id="routecode_safe" value="" class="easyui-textbox"  data-options="required:true,validType:'length[0,50]'" readonly/>
		           </td> 
		           <td width="5%"  height="20" align="left" nowrap>车牌号码：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		               <input name="vehicleno" id="vehicleno_safe" class="easyui-textbox"  style="width:auto;" data-options="required:true,validType:'length[0,50]'"  readonly >
		               <input type="hidden" name="vehicleid_safe" id="vehicleid" class="easyui-textbox"  />
		               <input type="hidden" name="id" id="id_safe" class="easyui-textbox"  />
		               <input type="hidden" name="status" id="status_safe" class="easyui-textbox"  />
		           </td>
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>车辆型号：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		                <input name="vehicletype" id="vehicletype_safe"  class="easyui-textbox"  data-options="validType:'length[1,30]'" readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>司机姓名：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		               <input name="drivername" id="drivername_safe" value="" class="easyui-textbox"   data-options="required:true,validType:'length[0,50]'" readonly/>
		               <input type="hidden" name="driverid" id="driverid_safe"class="easyui-textbox"   />
		           </td>  
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>申报日期：</td>
		           	<td width="13%" height="20" align="left" colspan=3 nowrap>
		                <input id=applydate_safe name=applydate class="easyui-datebox" style="width:100px;" readonly>
		           	</td>
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>维保项目：</td>
		           	<td width="13%" height="20" align="left" colspan=3  nowrap>
		                <textarea id="mainitem_safe"  name="mainitem"  cols="60" rows="2"readonly></textarea>
		           	</td>
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>备注信息：</td>
		           	<td width="13%" height="20" align="left" colspan=3  nowrap>
		                <textarea id="remarks_safe"  name="remarks"  cols="60" rows="2"readonly></textarea>
		           	</td>
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>分部主任审核意见：</td>
		           	<td width="13%" height="20" align="left" colspan=3  nowrap>
		                <textarea id="leadersuggestion_safe"  name="leadersuggestion"  cols="60" rows="2"readonly>同意维修</textarea>
		           	</td>
	           </tr>
	           <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>维修站点：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		                <input name="unitid" id="unitid" class="easyui-combobox" style="width:200px;" data-options="" >
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>类型：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		               <input name="repairtype" id="repairtype" class="easyui-combobox" style="width:120px;" data-options="" >
		           </td>  
	           </tr>
	           <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>是否二次维修：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		                <input name="isrepeat" id="isrepeat" class="easyui-combobox" style="width:120px;" data-options="" >
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>预计费用：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		               <input name="estimatecost" id="estimatecost" value="0" class="easyui-numberbox tb"  data-options="required:true,min:0,precision:2"  />
		           </td>  
	           </tr>
	           <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>维保项目确认：</td>
		           	<td width="13%" height="20" align="left" colspan=3  nowrap>
		                <textarea id="vehiclemainitem"  name="vehiclemainitem"  cols="60" rows="2"></textarea>
		           	</td>
	           </tr>
	           </table>
			</div>
			<br>
		</form>
	</div>
	</table></td></tr></table></tr></div></form></div>
	<div id="safeauditdlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveSafeAudit('20')">通过</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="saveSafeAudit('30')">驳回</a>
	</div>
	
	<!-- 4、查看对话框 --------------------------------------------------------------------------------------->
<div id="viewaudit-dlg" class="easyui-dialog" style="width:700px;height:400px;padding:5px 10px;align:center;"
							 closed="true" buttons="#viewauditdlg-buttons"  data-options="modal:true,draggable:false">
						<form  id="viewaudit-fm" method="post" action=""   >
	<div >
           <tr>
	    	<td >
		  	 <table width="100%" border="0" cellpadding="2" cellspacing="2" >
		  	  <tr>
		  	  		<td width="5%" height="20" align="left" nowrap>车辆型号：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		                <input name="vehicletype" id="vehicletype_view"  class="easyui-textbox"  data-options="validType:'length[1,30]'" readonly>
		           	</td>
		           <td width="5%"  height="20" align="left" nowrap>车牌号码：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		               <input name="vehicleno" id="vehicleno_view" class="easyui-textbox"  style="width:auto;" data-options="required:true,validType:'length[0,50]'"  readonly >
		               <input type="hidden" name="vehicleid_view" id="vehicleid" class="easyui-textbox"  />
		               <input type="hidden" name="id" id="id_view" class="easyui-textbox"  />
		               <input type="hidden" name="status" id="status_view" class="easyui-textbox"  />
		           </td>
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>申报日期：</td>
		           	<td width="13%" height="20" align="left" colspan=1 nowrap>
		                <input id=applydate_view name=applydate class="easyui-textbox"  readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>申请人：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		               <input name="createname" id="createname" value="" class="easyui-textbox"   data-options="required:true,validType:'length[0,50]'" readonly/>
		           </td>  
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>维保项目：</td>
		           	<td width="13%" height="20" align="left" colspan=3  nowrap>
		                <textarea id="mainitem_view"  name="mainitem"  cols="60" rows="2"readonly></textarea>
		           	</td>
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>备注信息：</td>
		           	<td width="13%" height="20" align="left" colspan=3  nowrap>
		                <textarea id="remarks_view"  name="remarks"  cols="60" rows="2"readonly></textarea>
		           	</td>
	           </tr>
	            <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>分部主任：</td>
		           	<td width="13%" height="20" align="left" colspan=1 nowrap>
		                <input id=leadername_view name=leadername class="easyui-textbox"  readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>分部主任审核：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		               <input name="leaderstatus" id="leaderstatus" value="" class="easyui-textbox"   readonly/>
		           </td>  
	           </tr>
		  	  <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>分部主任审核意见：</td>
		           	<td width="13%" height="20" align="left" colspan=3  nowrap>
		                <textarea id="leadersuggestion_view"  name="leadersuggestion"  cols="60" rows="2"readonly>同意维修</textarea>
		           	</td>
	           </tr>
	           <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>车管员：</td>
		           	<td width="13%" height="20" align="left" colspan=1 nowrap>
		                <input id=vehiclectrname_view name=vehiclectrname class="easyui-textbox"  readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>车管员审核：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		               <input name="vehiclectrstatus" id="vehiclectrstatus" value="" class="easyui-textbox"  readonly/>
		           </td>  
	           </tr>
	           <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>是否重复维修：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		                <input name="isrepeat_view" id="isrepeat_view" class="easyui-textbox tb"  data-options="" readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>预计费用：</td>
		           	<td width="13%" height="20" align="left" nowrap>
		               <input name="estimatecost" id="estimatecost" value="0" class="easyui-numberbox tb" readonly/>
		           </td>  
	           </tr>
	           <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>维修站点：</td>
		           	<td width="13%" height="20" align="left" colspan=3 nowrap>
		                <input name="unitname" id="unitname" class="easyui-textbox"  style="width:300px;"data-options="" readonly>
		           	</td>
	           </tr>
	           <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>维保项目确认：</td>
		           	<td width="13%" height="20" align="left" colspan=3  nowrap>
		                <textarea id="vehiclemainitem"  name="vehiclemainitem"  cols="60" rows="2" readonly></textarea>
		           	</td>
	           </tr>
	           </table>
			</div>
			<br>
		</form>
	</div>
	</table></td></tr></table></tr></div></form></div>
	<div id="viewauditdlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#viewaudit-dlg').dialog('close');">关闭</a>
	</div>
    
    <!-- 打印窗口 -->
	<!--startprint-->
	<div id="print-dlg" class="easyui-dialog" style="width:900px;height:400px;padding:10px 20px;align:center;"  data-options="modal:true,draggable:false"
			 closed="true" buttons="#print-dlg-buttons">
		<div class="ftitle"></div>
		<form id="print-fm" method="post" action="" novalidate  >
		 <div class="fitem" id="printTable">
		<div id="print-dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-myprinter" onclick="$('#printTable').jqprint();$('#print-dlg').dialog('close'); ">打印</a>
	</div>
	</div>
	<!-- 回单对话框 enctype="multipart/form-data"-->
	<div id="receipt-dlg" class="easyui-dialog" style="width:700px;height:400px;padding:5px 10px;align:center;"
							 closed="true" buttons="#receiptdlg-buttons"  data-options="modal:true,draggable:false">
						<form  id="abc-fm" method="post" action=""   >
	<div >
           <input name="vehicleno1" id="vehicleno_receipt" class="easyui-textbox tb"   >
		  	 <table width="100%" border="0" cellpadding="2" cellspacing="2"  class="tablemain">
			   <tr><td colspan="6"  class="style2">修理车辆信息</td></tr>
			    <tr align="center" >
		      <td width="10%"  align="left" nowrap>车辆型号：</td>
		      <td width="20%"  align="left" nowrap><input name="vehicletype" id="vehicletype_receipt" class="easyui-textbox tb"   ></td>
		      <td width="10%"  align="left" nowrap>车牌号码：</td>
		      <td width="20%"  align="left" nowrap><input name="vehicleno" id="vehicleno_receipt" class="easyui-textbox tb"   ></td>
			   <td width="10%"  align="left" nowrap>发动机号：</td>
		      <td width="20%"  align="left" nowrap><input name="enginenum" id="enginenum_receipt" class="easyui-textbox tb" ></td>
		      </tr>
			 
			  
		    </table>
    
			</div>
			<br>
		</form>
	</div>
	</table></td></tr></table></tr></div></form></div>
	<div id="receiptdlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveReceipt()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#receipt-dlg').dialog('close');">关闭</a>
	</div>
	
	<!-- 进度条，建议对于耗时长的操作才启用
	<div id="loading">
		<div class="inputdiv" >
			<img  src="/BS56/img/loading.gif" style="padding-top: 20px; padding-left:72px;"/><br>
			<h3 style="padding-left: 35px;">&nbsp;&nbsp;&nbsp;&nbsp;数据上传中,请稍候......</h3>
		</div>
	 </div>-->
  </body>
</html>
