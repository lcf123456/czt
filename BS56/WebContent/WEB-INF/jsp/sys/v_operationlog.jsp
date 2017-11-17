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
	
	<!-- datagrid页面列表数据 -->
	<div style="padding:10" id="tabdiv">
		<table id="dataTabel"></table>
	</div>
	
	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;border-top:1px solid #95B8E7">
	<form id="queryForm" style="margin:10;">
		<div style="margin-bottom:5px">
			<input class="easyui-textbox"  name="keyword"  id="keyword"data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchData();},prompt:'请输入菜单、功能点、操作人员...'" style="width:450px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>
    
    <script>
    /**
     * 页面列表datagrid初始化
     */
    jQuery(function($){
    	$('#keyword').textbox('textbox').keydown(function(e){
    		if(e.keyCode==13){
    			searchData();
    		}
    	})
    	$('#dataTabel').datagrid({
    		title:'操作日志', //标题
    		method:'post',
    		iconCls:'icon-edit', //图标
    		singleSelect:false, //多选
    		height:420, //高度
    		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
    		striped: true, //奇偶行颜色不同
    		collapsible:true,//可折叠
    		url:baseURL+"/sys/operationlog/getOperationlogList.json", //数据来源
    		sortName: 'id', //排序的列
    		sortOrder: 'desc', //倒序
    		remoteSort: true, //服务器端排序
    		idField:'id', //主键字段
    		pageNumber: 1, 
    		pageSize : 10,
    		pageList: [10,30,50],
    		queryParams:{
    			}, //查询条件
    		pagination:true, //显示分页
    		//pageSize : 1,
    		rownumbers:true, //显示行号
    		columns:[[
    			{field:'menu',title:'菜单',width:30,
    				formatter:function(value,row,index){return row.menu;}
    			},
    			{field:'point',title:'功能点',width:30,
    				formatter:function(value,row,index){return row.point;}
    			},
    			{field:'url',title:'链接',width:30,
    				formatter:function(value,row,index){return row.url;}
    			},
    			{field:'username',title:'操作人员',width:20,sortable:true,
    				formatter:function(value,row,index){return row.username;} //需要formatter一下才能显示正确的数据
    			},
    			{field:'operationdate',title:'操作时间',width:20,sortable:true,
    				formatter:function(value,row,index){return row.operationdate;}
    			},
    			
    			{field:'remarks',title:'备注信息',width:30,
    				formatter:function(value,row,index){
    					//return row.deptName;  //该列的值是deptId，显示的是deptName
    					return row.remarks;  
    				}
    			}
    		]],
    		toolbar:'#toolbar',
    		onLoadSuccess:function(){
    			$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
    			$('#tabdiv .panel-header').css('display','none'); 
    			
    		}
    	});
    	
    	
    });
    
    //查询
	function searchData(){
		var params = $('#dataTabel').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#dataTabel').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		searchData();
	}
    </script>
    
  </body>
</html>
