<%@page import="com.ztel.framework.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/cost/vehiclerepairreceipt.js"/>"></script>
  </head>
  <script type="text/javascript">
	var id="${id}";
	//alert(id);
	</script>
  <body>
	
	
	<!-- datagrid页面列表数据 -->
	<div id="add-dlg" >
		<form  id="add-fm" method="post" action="" novalidate  >
			<div >
			<tr>
			<td>
			<table width="100%" class="gridtable">
			<tr>
		  		 	<td width="5%" height="20" align="left" nowrap>车辆型号：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="vehicletype" id="vehicletype" class="easyui-textbox"  readonly >
		           	</td>
		            <td width="5%" height="20" align="left" nowrap>车牌号码：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="vehicleno" id="vehicleno" class="easyui-textbox"  >
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>发动机号：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="enginenum" id="enginenum" class="easyui-textbox" readonly />
		           </td>
	         </tr>
	         <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>购买日期：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="1">
		                <input name="buydate" id="buydate" class="easyui-textbox"  readonly >
		           	</td>
		  		 	<td width="5%" height="20" align="left" nowrap>里程数：</td>
		           	<td width="14%" height="20" align="left" nowrap >
		                <input name="mileage" id="mileage" class="easyui-textbox"   >
		           	</td>
		  		 	<td width="5%" height="20" align="left" nowrap>受理单位：</td>
		           	<td width="14%" height="20" align="left" nowrap >
		                <input name="unitname" id="unitname" class="easyui-textbox"  readonly  >
		           	</td>
	         </tr>
	         <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>维修日期：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="1">
		                <input name="rdate" id="rdate" class="easyui-textbox"  readonly >
		           	</td>
		  		 	<td width="5%" height="20" align="left" nowrap>是否重复维修：</td>
		           	<td width="14%" height="20" align="left" nowrap >
		                <input name="isrepeat" id="isrepeat" class="easyui-textbox"   readonly>
		           	</td>
		  		 	<td width="5%" height="20" align="left" nowrap>预计费用：</td>
		           	<td width="14%" height="20" align="left" nowrap >
		                <input name="estimatecost" id="estimatecost" class="easyui-textbox"  readonly  >
		           	</td>
	         </tr>
			</table>
    
          
		</form>
	</div>
	
	
<div id="receipt-dlg" class="easyui-dialog" style="width:700px;height:400px;padding:5px 10px;align:center;"
							 closed="true" buttons="#receiptdlg-buttons"  data-options="modal:true,draggable:false">
						
	<div >
           <tr>
	    	<td >
		  	 
			</div>
			<br>
	</div>
	</table></td></tr></table></tr></div></form></div>
	<div id="receiptdlg-buttons">
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
