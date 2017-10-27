<%@page import="com.ztel.framework.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/account/accounttotal.js"/>"></script>
  </head>
  <body>
	
	
	<!-- datagrid页面列表数据 -->
	<form id="refundForm" style="margin:10;" method="post">
	<div style="padding:10" id="tabdiv">
		<table id="dataTable"></table>
	</div>
	</form>
	
	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">
			<a href="#"  id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openNew()">录入</a>
			车组选择：<input name="routecode" id="routecode" class="easyui-combobox"  >&nbsp;
			时间：<input id=orderdate name=orderdate class="easyui-datebox" style="width:100px;" >
			<a href="#" onclick="searchData();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">查询</a>
		</div>
		</form>
	</div>
	
	<!-- 订单查询toolbar --> 
	<div id="add-dlg" class="easyui-dialog" style="width:1000px;height:420px;padding:5px 10px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<form  id="add-fm" method="post" action=""   >
			<div >
			<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" > 
			  <tr><td colspan="8"  id="tdtitle" class="style2"><b><font color=red>货款回笼明细</font></b></td></tr>
			  <tr align="center"  >
			   <td width="9%"  height="20" align="left" nowrap>销售额：</td>
			   <td  align="left"><input class='easyui-numberbox'id="saleamount" name="saleamount" size="10" maxlength="10"   disabled> &nbsp;</td>
			   <td width="9%"  height="20" align="left" nowrap>销售笔数：</td>
			   <td  align="left"><input class='easyui-numberbox'id="salecount"  name="salecount" size="10" maxlength="10"   disabled> &nbsp;</td>
			  <td width="13%" height="20" align="left" nowrap>在线代扣：</td>			
			  <td align="left"><input class='easyui-numberbox'id="onlineamount" name="onlineamount" size="10" maxlength="10"   disabled> &nbsp;</td>
			  <td width="8%" height="20" align="left" nowrap>在线代扣笔数：</td>
			  <td  align="left"><input class='easyui-numberbox' id="onlinecount" name="onlinecount" size="10" maxlength="10" disabled></td>
			</tr>
			<tr align="center"  >
			   <td width="9%"  height="20" align="left" nowrap>预收款金额：</td>
			   <td  align="left"><input class='easyui-numberbox' id="prepayamount" name="prepayamount" size="10" maxlength="10"   disabled> &nbsp;</td>
			   <td width="9%"  height="20" align="left" nowrap>预收款笔数：</td>
			   <td  align="left"><input class='easyui-numberbox' id="prepaycount" name="prepaycount" size="10" maxlength="10" disabled> &nbsp;</td>
			   <td width="9%"  height="20" align="left" nowrap>退货金额：</td>
			   <td  align="left"><input class='easyui-numberbox' id="returnamount" name="returnamount" size="10" maxlength="10"  disabled> &nbsp;</td>
			   <td width="9%"  height="20" align="left" nowrap>退货笔数：</td>
			   <td  align="left"><input class='easyui-numberbox' id="returncount" name="returncount" size="10" maxlength="10"  disabled> &nbsp;</td>
 			</tr>
 			<!-- <tr align="center"  >
			  <td width="13%" height="20" align="left" nowrap>组间转账（预付、代扣）金额：</td>			
			  <td align="left"><input class='easyui-numberbox'name="turngoodsamount_dis" size="10" maxlength="10"  disabled> &nbsp;</td>
			  <td width="13%" height="20" align="left" nowrap>组间转账（预付、代扣）笔数：</td>
			  <td  align="left"><input class='easyui-numberbox'name="turngoodscount_dis" size="10" maxlength="10" disabled></td>
			  <td width="13%" height="20" align="left" nowrap>组间转账（现金、刷卡）金额：</td>			
			  <td align="left"><input class='easyui-numberbox'name="turngoodsamount2_dis" size="10" maxlength="10" disabled> &nbsp;</td>
			  <td width="13%" height="20" align="left" nowrap>组间转账（现金、刷卡）笔数：</td>
			  <td  align="left"><input class='easyui-numberbox'name="turngoodscount2_dis" size="10" maxlength="10"disabled></td>
			</tr> -->
 			<tr align="center"  >
			  <td width="13%" height="20" align="left" nowrap>暂存（已结算）金额：</td>			
			  <td align="left"><input class='easyui-numberbox' id="tempstorageamount_dis" name="tempstorageamount_dis" size="10" maxlength="10"   disabled> &nbsp;</td>
			  <td width="13%" height="20" align="left" nowrap>暂存（已结算）笔数：</td>
			  <td  align="left"><input class='easyui-numberbox' id="tempstoragecount_dis" name="tempstoragecount_dis" size="10" maxlength="10" disabled></td>
			  <td width="13%" height="20" align="left" nowrap>暂存（未结算）金额：</td>			
			  <td align="left"><input class='easyui-numberbox'id="tempstorageamount2_dis"  name="tempstorageamount2_dis" size="10" maxlength="10"  disabled> &nbsp;</td>
			  <td width="13%" height="20" align="left" nowrap>暂存（未结算）笔数：</td>
			  <td  align="left"><input class='easyui-numberbox' id="tempstoragecount2_dis" name="tempstoragecount2_dis" size="10" maxlength="10" disabled></td>
			</tr>
			<tr align="center"  >
			 <td width="9%"  height="20" align="left" nowrap>刷卡金额：
			 </td>
			   <td  align="left"><input class='easyui-numberbox' id="cardamount" name="cardamount" size="10" maxlength="10" onBlur="jsamount()"  > <font color="#FF0000">*</font>&nbsp;</td>
			   <td width="9%"  height="20" align="left" nowrap>刷卡笔数：</td>
			   <td  align="left"><input class='easyui-numberbox' id="cardcount" name="cardcount" size="10" maxlength="10" onBlur="jscount()" style="ime-mode:disabled" onkeypress="return event.keyCode>=48&&event.keyCode<=57" > <font color="#FF0000">*</font>&nbsp;</td>
  		       <td width="13%" height="20" align="left" nowrap>现金金额：
  		       </td>			
			  <td align="left"><input class='easyui-numberbox' id="cashamount" name="cashamount" size="10" maxlength="10" onBlur="jsamount()"><font color="#FF0000">*</font> &nbsp;</td>
			  <td width="8%" height="20" align="left" nowrap>现金单据数：</td>
			  <td  align="left"><input class='easyui-numberbox' id="cashcount" name="cashcount" size="10" maxlength="10" onBlur="jscount()" style="ime-mode:disabled" onkeypress="return event.keyCode>=48&&event.keyCode<=57" ><font color="#FF0000">*</font></td>
			</tr>
			<tr align="center"  >
			  <td width="13%" height="20" align="left" nowrap>其他金额：</td>		
			  <td  align="left"><input class='easyui-numberbox' id="otheramount" name="otheramount" size="10" maxlength="10" onBlur="jsamount()" ></td>	
			  <td width="8%" height="20" align="left" nowrap>其他笔数：</td>
			  <td  align="left"><input class='easyui-numberbox' id="othercount" name="othercount" size="10" maxlength="10" onBlur="jscount()" style="ime-mode:disabled" onkeypress="return event.keyCode>=48&&event.keyCode<=57"></td>
			  <td width="13%" height="20" align="left" nowrap>暂存送货金额：</td>			
			  <td align="left" colspan="3"><input class='easyui-numberbox'id="deliveramount"  name="deliveramount" size="10" maxlength="10" disabled  >&nbsp;
			  							   <!--2012-08-10 jsamount()没有计算暂存送货金额,加上一个现金或刷卡暂存送货金额的隐藏文本 -->
			  							   <input type="hidden" name="deliver2" id="deliver2">
			  </td>
			</tr>
			<tr align="center"  >
			<td width="13%" height="20" align="left" nowrap>待审金额：</td>		
			  <td  align="left"><input class='easyui-numberbox' id="dsamount" name="dsamount" size="10" maxlength="10"  readonly></td>	
			  <td width="13%" height="20" align="left" nowrap>今日欠款：</td>		
			  <td  align="left"><input class='easyui-numberbox' id="todayarrears" name="todayarrears" size="10" maxlength="10" readonly></td>	
			  <td width="8%" height="20" align="left" nowrap>累计欠款：</td>
			  <td  align="left" colspan="3"><input class='easyui-numberbox' id="totalarrears" name="totalarrears" size="10" maxlength="10" disabled></td>
			  <!-- <td width="8%" height="20" align="left" nowrap>笔数差：</td>
			  <td  align="left"  ><input class='easyui-numberbox'name="countdiff" size="10" maxlength="10" disabled></td> -->
			</tr>
			<tr align="center"  >
			  <td width="13%" height="20" align="left" nowrap>备注：</td>		
			  <td  align="left" colspan="7"><textarea id="remarks"  name="remarks"  cols="80" rows="3"></textarea></td>	
			</tr>
			<tr align="center"  >
			  <td width="13%" height="20" align="left" nowrap>其他金额备注说明：</td>		
			  <td  align="left" colspan="7"><textarea id="otherremarks"  name="otherremarks" cols="80" rows="3"></textarea></td>	
			</tr>
		</table>
			</div>
		</form>
	 <div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" id="btn-ok" iconCls="icon-ok" onclick="saveAdd();">保存</a>
		<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-cancel" onclick="javascript:$('#add-dlg').dialog('close')">关闭</a>
	</div>
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
