<%@page import="com.ztel.app.util.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/wms/inventory_new.js"/>"></script>
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
	<div id="toolbar" style="padding:5px;height:auto">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">
		<a href="#" id="viewBtn" class="easyui-linkbutton" iconCls="icon-view" plain="true" onclick="openView()">查看</a>
		<a href="#" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openNew()">新增</a>
		<a href="#" id="inventoryBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openInventory()">日清日结</a>
			时间：<input id=begdate name=begdate class="easyui-datebox" style="width:100px;" >到<input id=enddate name=enddate class="easyui-datebox" style="width:100px;" >
			<a href="#" onclick="searchData();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">查询</a>
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>
	
	<!-- 新增对话框 -->
	<div id="add-dlg" class="easyui-dialog" style="width:650px;height:180px;padding:10px 20px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<div class="ftitle"></div>
		<form  id="add-fm" method="post" action=""   >
			<div class="fitem">
				<table width="100%" border="0" cellpadding="0" cellspacing="8">
     <tr>
    <td >
	  <table width="100%" border="0" cellpadding="2" cellspacing="2" class="">
        <tr align="center" >
            
            <td width="5%" height="20" align="left" nowrap>订单日期：</td>
           <td width="24%" height="20" align="left" nowrap>
                <input id="orderdate" name="orderdate" class="easyui-datebox" style="width:100px;" />
            </td>	
           <td width="5%" height="20" align="left" nowrap>盘点时间：</td>
           <td width="24%" height="20" align="left" nowrap>
                <input id="createtime" name="createtime" class="easyui-datetimebox" style="width:140px;" />
            </td>
           <td width="5%" height="20" align="left" nowrap>盘点类型：</td>
           <td width="14%" height="20" align="left" nowrap>
                <input name="inventorytype" id="inventorytype" class="easyui-textbox" style="width:100px;" readonly/>
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
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="checkNum()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#add-dlg').dialog('close')">关闭</a>
	</div>
	
	<!-- 盘点对话框 -->
	<div id="inventory-dlg" class="easyui-dialog" style="width:1160px;height:500px;padding:10px 10px;align:center;"
			 closed="true" buttons="#inventorydlg-buttons"  data-options="modal:true,draggable:false">
		<form  id="inventoryForm" method="post" action="" novalidate  >
        <div style="padding:10px">
           		<table id="newTable">
           		<thead>
		            <tr>
		                <th data-options="field:'cigarettecode',width:10,align:'center'" rowspan="2">品牌代码</th>
		                <th data-options="field:'cigarettename',width:30,align:'center'" rowspan="2">品牌名称</th>
		                <th data-options="field:'paperqty',width:10,align:'center'" rowspan="2">账面量</th>
		                <th data-options="field:'atscellqty',width:10,align:'center'" rowspan="2">立库区</th>
		                <th data-options="field:'scatteredqty',width:10,align:'center'" rowspan="2">散烟区</th>
		                <th colspan="2">分拣区</th>
		                <th colspan="2">重力式货架</th>
		                <th colspan="2">异型烟分拣一区</th>
		                <th colspan="2">异型烟分拣二区</th>
		                <th colspan="2">异型烟公共区</th>
		                <th data-options="field:'virtualqty',width:10,align:'center'" rowspan="2">虚拟区</th>
		                <th data-options="field:'diffqty',width:10,align:'center'" rowspan="2">差额</th>
		            </tr>
		            <tr>
		                <th data-options="field:'sortingno',width:10,align:'center'">通道号</th>
		                <th data-options="field:'sortingqty',width:10,align:'center'">数量</th>
		                <th data-options="field:'shelfno',width:10,align:'center'">通道号</th>
		                <th data-options="field:'shelfqty',width:10,align:'center'">数量</th>
		                <th data-options="field:'unnormalno1',width:10,align:'center'">通道号</th>
		                <th data-options="field:'unnormalqty1',width:10,align:'center'">数量</th>
		                <th data-options="field:'unnormalno2',width:10,align:'center'">通道号</th>
		                <th data-options="field:'unnormalqty2',width:10,align:'center'">数量</th>
		                <th data-options="field:'commonno',width:10,align:'center'">通道号</th>
		                <th data-options="field:'commonqty',width:10,align:'center'">数量</th>
		            </tr>
		        </thead>
           		</table>
        </div>
        <!-- <div title="异型分拣区"  style="padding:10px">
            <table id="AbnormalScatteredTable">44</table>
        </div> 
        <div title="立库区" style="padding:10px">
           		<table id="ATSCellTable"></table>
        </div>
        <div title="重力式货架" style="padding:10px">
            <table id="ShelfTable"></table>
        </div>
        <div title="分拣区"  style="padding:10px">
            <table id="SortingCellTable"></table>
        </div>
        <div title="散烟区" style="padding:10px">
            <table id="ScatteredTable"></table>
        </div>-->
        <input type='hidden' name='inventoryid' id='inventoryid'/>
        <input type='hidden' name='createtime' id='createtime1'/>
        <input type='hidden' name='orderdate' id='orderdate1'/>
        <input type='hidden' name='ATSCellTableAreaid' id='ATSCellTableAreaid' value="<%=Constant.storagearea_lk%>"/>
        <input type='hidden' name='ShelfTableAreaid' id='ShelfTableAreaid' value="<%=Constant.storagearea_zlshj%>"/>
        <input type='hidden' name='SortingCellTableAreaid' id='SortingCellTableAreaid' value="<%=Constant.storagearea_fj%>"/>
        <input type='hidden' name='ScatteredTableAreaid' id='ScatteredTableAreaid' value="<%=Constant.storagearea_sy%>"/>
    </form>
	</div>
	<div id="inventorydlg-buttons">
		<a href="#" id="subBtn"class="easyui-linkbutton" iconCls="icon-ok" onclick="saveInventory()">日清日结</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#inventory-dlg').dialog('close')">关闭</a>
	</div>
	
  </body>
</html>
