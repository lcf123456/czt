<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<%@ page import="com.ztel.framework.util.DateUtil" %>
<!DOCTYPE html >
<html>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
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
	   <p style="margin-left:250px;padding:5px 0;text-align:left;height:4px;font-weight:200;font-size:10" >商品信息同步和客户信息同步无需选择时间,订单信息同步请选择订单日期!</p>
	   <p id="datainfo" style="margin-left:250px;padding:5px 0;text-align:left;height:4px;font-weight:200;font-size:10" ></p>
	        <p id="showMsg" style="margin-left:250px;padding:5px 0;text-align:left;color:red;height:4px;font-weight:200;font-size:10" >同步结果显示区</p>
	   </div>
	</div>

    <script>
    /**
     * 页面列表datagrid初始化
     */
    jQuery(function($){
    	//alert("aa");
    	//$("#resultarea" ).css("display", "none");
    	var nowTime = getDateYMD();
    	$('#orderdate').datebox("setValue",nowTime);
    	initOrderDatebox();
    	document.getElementById('showMsg').innerHTML="";
    	var orderdate = $('#orderdate').val();
    	getOrderdata(orderdate);
    });
    
    function initOrderDatebox(){
        $('#orderdate').datebox({
        	onSelect: function(date){
        		var orderdate = $("#orderdate").val();
        		getOrderdata(orderdate);
        	}
        });
    }
    
    function getOrderdata(orderdate){
    	//取各信息记录的条数
    	$.ajax({ 
		    url: baseURL+'/sale/saletolocal/doGetDataCount.json?orderdate='+orderdate, 
		    type: 'POST',
		    beforeSend : function () {
				$.messager.progress({
					text : '正在加载中...',
				});
			},
			complete: function(){  
		        //AJAX请求完成时隐藏loading提示  
		        $.messager.progress('close');
		    },
		    success: function(data){
		    	var msg = data.resultmsg;
		    	document.getElementById('datainfo').innerHTML=msg;
		    	$.messager.show({
					title : '提示',
					msg :  msg,
				});
			}
		   });
    }
   // alert("aa");
	//$("#resultarea" ).css("display", "none");
	//document.getElementById('showMsg').innerHTML="";
	
	//零售户同步
	function syncCustomer(){
		$.messager.confirm('提示','确定要执行零售户数据同步吗?',function(result){
	        if (result){
		$.ajax({ 
		    url: baseURL+'/sale/saletolocal/doSyncCustomer.json', 
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
		    	document.getElementById('showMsg').innerHTML=msg;
		    	$.messager.show({
					title : '提示',
					msg :  msg,
				});
			}
		   }); 
	}
		});
	}
	
	
	//订单同步
	function syncOrder(){
		$.messager.confirm('提示','确定要执行订单数据同步吗?',function(result){
	        if (result){
		$.ajax({ 
		    url: baseURL+'/sale/saletolocal/doSyncOrder.json?orderdate='+$('#orderdate').val(), 
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
		    	document.getElementById('showMsg').innerHTML=msg;
		    	$.messager.show({
					title : '提示',
					msg :  msg,
				});
			}
		   }); 
	}
		});
	}
	
	//商品同步
	function syncItem(){
		$.messager.confirm('提示','确定要执行商品数据同步吗?',function(result){
	        if (result){
		$.ajax({ 
		    url: baseURL+'/sale/saletolocal/doSyncItem.json', 
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
		    	document.getElementById('showMsg').innerHTML=msg;
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
