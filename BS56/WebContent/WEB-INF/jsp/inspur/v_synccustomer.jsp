<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<%@ page import="com.ztel.framework.util.DateUtil" %>
<!DOCTYPE html >
<html>

<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<%
 String time = DateUtil.getyyyy_mm_dd_hh_mi_s();
%>
  <head>
  </head>
  
  <body>
	
	
	<!-- 查询-->
	<div id="toolbar" style="margin:0 auto; width:800px; height:auto; ">
	<form id="queryForm">
			<div style="margin:auto;text-align:left">
			订单日期：<input name="orderdate" id="orderdate" class="easyui-datebox"  style="width:120px"  >&nbsp;&nbsp;
			<a href="#" onclick="syncItem();" class="easyui-linkbutton" iconCls="icon-search" style="height:34px;width:120px;">商品同步</a>
			&nbsp;&nbsp;
			<a href="#" onclick="syncCustomer();" class="easyui-linkbutton" iconCls="icon-search" style="height:34px;width:120px;">零售户同步</a>
			&nbsp;&nbsp;
			<a href="#" onclick="syncOrder();" class="easyui-linkbutton" iconCls="icon-search" style="height:34px;width:120px;">订单同步</a>
		</div>
		</form>
	</div>
    
    <div class="resultarea">
	   <div style="text-align: center;   vertical-align: middle;">
	   <p style="margin-left:250px;padding:5px 0;text-align:left;height:4px;font-weight:200;font-size:10" >此基本信息导入为将营销接口数据导入到本地中间表</p>
	   <p style="margin-left:250px;padding:5px 0;text-align:left;height:4px;font-weight:200;font-size:10" >商品信息同步和客户信息同步无需选择时间,订单信息同步请选择订单日期!</p>
	   <p id="datainfoitem" style="margin-left:250px;padding:5px 0;text-align:left;color:red;height:4px;font-weight:200;font-size:10" ></p>
	   <p id="datainfocustomer" style="margin-left:250px;padding:5px 0;text-align:left;color:red;height:4px;font-weight:200;font-size:10" ></p>
	   <p id="datainfoorder" style="margin-left:250px;padding:5px 0;text-align:left;color:red;height:4px;font-weight:200;font-size:10" ></p>
	   </div>
	</div>
	
		</form>
	</div>

    <script>
    /**
     * 页面列表datagrid初始化
     */
    jQuery(function($){
    	var nowTime = getDateYMD();
    	$('#orderdate').datebox("setValue",nowTime);
    });
    
   // alert("aa");
	//$("#resultarea" ).css("display", "none");
	//document.getElementById('showMsg').innerHTML="";
	
	//清空查询条件
	function syncCustomer(){
		$.messager.confirm('提示','确定要执行同步吗?',function(result){
	        if (result){
		$.ajax({ 
		    url: baseURL+'/inspur/doSyncCustomer.json', 
		    type: 'POST',
		    beforeSend : function () {
				$.messager.progress({
					text : '正在同步中...',
				});
			},
			complete: function(){  
		        //AJAX请求完成时隐藏loading提示  
		        $.messager.progress('close');
		    },
		    success: function(data){
		    	var msg = data.custCount +"条零售户记录"+data.resultmsg;
		    	document.getElementById('datainfocustomer').innerHTML=msg;
		    	$.messager.show({
					title : '提示',
					msg :  msg,
				});
			}
		   }); 
	}
		});
	}
	
	function syncItem(){
		$.messager.confirm('提示','确定要执行同步吗?',function(result){
	        if (result){
		$.ajax({ 
		    url: baseURL+'/inspur/doSyncItem.json', 
		    type: 'POST',
		    beforeSend : function () {
				$.messager.progress({
					text : '正在同步中...',
				});
			},
			complete: function(){  
		        //AJAX请求完成时隐藏loading提示  
		        $.messager.progress('close');
		    },
		    success: function(data){
		    	var msg = data.custCount +"条商品记录"+data.resultmsg;
		    	document.getElementById('datainfoitem').innerHTML=msg;
		    	$.messager.show({
					title : '提示',
					msg :  msg,
				});
			}
		   }); 
	}
		});
	}
	
	function syncOrder(){
		$.messager.confirm('提示','确定要执行订单数据同步吗?',function(result){
	        if (result){
		$.ajax({ 
		    url: baseURL+'/inspur/doSyncOrder.json?orderdate='+$('#orderdate').val(), 
		    type: 'POST',
		    beforeSend : function () {
				$.messager.progress({
					text : '正在同步中...',
				});
			},
			complete: function(){  
		        //AJAX请求完成时隐藏loading提示  
		        $.messager.progress('close');
		    },
		    success: function(data){
		    	var msg = data.resultmsg;
		    	document.getElementById('datainfoorder').innerHTML=msg;
		    	$.messager.show({
					title : '提示',
					msg :  msg,
				});
			}
		   }); 
	}
		});
	}
    </script>
    
  </body>
</html>
