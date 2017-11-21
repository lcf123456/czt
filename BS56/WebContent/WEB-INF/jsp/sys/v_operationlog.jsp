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
		<a href="#" id="newBtn" class="easyui-linkbutton" iconCls="icon-view" plain="true" onclick="viewD()">查看</a>
			<input class="easyui-textbox" id="searchbtn" name="keyword"  data-options="buttonText:'查询',buttonIcon:'icon-search',onClickButton:function(){searchData();},prompt:'请输入菜单、功能点、操作人员...'" style="width:450px;height:24px;">
			<a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">清空</a>
		</div>
		</form>
	</div>
    
        <!-- 1、明细查看--------------------------------------------------------------------------------------->
	<div id="add-dlg" class="easyui-dialog" style="width:800px;height:420px;padding:5px 10px;align:center;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true,draggable:false">
		<form  id="add-fm" method="post" action="" novalidate  >
			<div >
			<tr>
			<td>
			<table>
			<tr>
		  		 	<td width="5%" height="20" align="left" nowrap>菜单：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="menu" id="menu" class="easyui-textbox" style="width:150px"  readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>操作时间：</td>
		           	<td width="23%" height="20" align="left" nowrap >
		               <input name="operationdate" id="operationdate" class="easyui-textbox" style="width:150px" readonly/>
		           </td>  		           
		            <td width="5%" height="20" align="left" nowrap>链接：</td>
		           	<td width="5%" height="20" align="left" nowrap >
		                <input name="url" id="url" class="easyui-textbox" style="width:150px" readonly>
		           	</td>
	           </tr>
	           <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>操作人员：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="username" id="username" class="easyui-textbox" style="width:150px"  readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>功能点：</td>
		           	<td width="23%" height="20" align="left" nowrap colspan="3">
		               <input name="point" id="point" class="easyui-textbox" style="width:450px" readonly/>
		           </td>  
	           </tr>
				<tr>
		            <td width="5%" height="20" align="left" nowrap>备注：</td>
		           	<td width="20%" height="20" align="left" nowrap colspan="5">
		               <input name="remarks" id="remarks" class="easyui-textbox" data-options="multiline:true"   style="width:680px;height:260px" readonly>
		           	</td>
	           </tr>
			</table>
			</td>
			</tr>
			</div>
		</form>
	 <div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-ok" onclick="javascript:$('#add-dlg').dialog('close')">关闭</a>
	</div>
	</div>
	
    <script>
    /**
     * 页面列表datagrid初始化
     */
    jQuery(function($){
    	
    	$('#searchbtn').textbox('textbox').keydown(function (e) {
            if (e.keyCode == 13) {
            	searchData();
            }
        }); 
    	
    	$('#dataTabel').datagrid({
    		title:'操作日志', //标题
    		method:'post',
    		iconCls:'icon-edit', //图标
    		singleSelect:true, //多选
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
    			{field:'id',checkbox:true,width:2}, //显示复选框
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
    			
    		},
    		onDblClickCell: function(index,field,value){
    			alert("a");
    	       viewD();
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
	
	/**
	 * 查看窗口
	 */
	  function viewD(){
		  $('#add-fm').form('clear');
		  var rows = $('#dataTabel').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要查看的信息",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条信息进行查看",'info');
				return;
			}
			
			var row = $('#dataTabel').datagrid('getSelected');
			var inboundid = row.inboundid;
			if (row){
				$('#add-dlg').dialog('open').dialog('setTitle',"查看");
				$('#add-dlg').form('load',row);
			}
		}
    </script>
    
  </body>
</html>
