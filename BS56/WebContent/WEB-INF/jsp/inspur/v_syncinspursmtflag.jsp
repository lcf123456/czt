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
	<div id="toolbar" style="margin:0 auto; width:400px; height:100px; ">
	<form id="queryForm">
		<div style="margin:auto;text-align:center">
		订单日期：<input name="orderdate" id="orderdate" class="easyui-datebox"  style="width:120px"  >&nbsp;&nbsp;
			<a href="#" onclick="syncSmtflag();" class="easyui-linkbutton" iconCls="icon-search" style="height:44px;width:120px;">扣款同步</a>
		</div>
		</form>
	</div>
    
    <div class="resultarea">
	   <div style="text-align: center;   vertical-align: middle;">
	        <p id="showMsg" style="padding:5px 0;text-align:center;color:red;height:4px;font-weight:200;font-size:10" >同步结果显示区</p>
	   </div>
	</div>

    <script>
    /**
     * 页面列表datagrid初始化
     */
    jQuery(function($){
    	var nowTime = getDateYMD();
    	$('#orderdate').datebox("setValue",nowTime);
    	document.getElementById('showMsg').innerHTML="";
    });
    
   // alert("aa");
	//$("#resultarea" ).css("display", "none");
	//document.getElementById('showMsg').innerHTML="";
	
	//清空查询条件
	function syncSmtflag(){
		$.messager.confirm('提示','确定要执行同步吗?',function(result){
	        if (result){
		$.ajax({ 
		    url: baseURL+'/inspur/doSyncsettlementflag.json?orderdate='+$('#orderdate').val(), 
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
