/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
$("#routecode").combobox({
	    url : baseURL+"/comm/combobox/getRoutesComboboxByDeptid.json?deptid=",//返回json数据的url
	    valueField : "routecode",//这个id和你返回json里面的id对应
	    textField : "routename", //这个text和你返回json里面的text对应
	    loadFilter : function (data) {
	        if (data && data instanceof Array) {
	            data.splice(0, 0, {routecode: '', routename: '请选择车组'});　
	        }
	        return data;
	    }   	
	})
	$('#dataTable').datagrid({
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/wms/customer/getBillCustomersPageList.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'asc', //正序asc、倒序 desc
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
		toolbar:'#toolbar',
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'name',title:'零售户店名',width:30,
				formatter:function(value,row,index){
					return row.name;
				}
			},
			{field:'code',title:'老专卖证',width:20,
				formatter:function(value,row,index){
					return row.code;}
			},
			{field:'remarks',title:'新专卖证',width:20,
				formatter:function(value,row,index){
					return row.remarks;}
			},
			{field:'contact',title:'联系人',width:10,
				formatter:function(value,row,index){return row.contact;}
			},
			{field:'contactphone',title:'联系电话',width:20,
				formatter:function(value,row,index){return row.contactphone;}
			},
			{field:'routecode',title:'配送车组',width:10,
				formatter:function(value,row,index){return row.routecode;}
			},
			{field:'billtypename',title:'发票类型',width:10,
				formatter:function(value,row,index){return row.billtypename;}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			//$('#tabdiv .panel-header').css('display','none'); 
		}
	});
	
});


function openPLNew(){
	
	$('#pladd-dlg').dialog('open').dialog('setTitle','发票客户--新增');
	$('#pladd-fm').form('clear');
	initCutomerTable();
}

function searchCustomer(){
	var params = $('#customerTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
	var fields =$('#searchForm').serializeArray(); //自动序列化表单元素为JSON对象
	$.each( fields, function(i, field){
		params[field.name] = field.value; //设置查询参数
	}); 
	$('#customerTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了

}

function search(){
	var params = $('#dataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
	var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
	$.each( fields, function(i, field){
		params[field.name] = field.value; //设置查询参数
	}); 
	$('#dataTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
}

function clearForm(){
	//$('#keywd').val('');
	$('#param').textbox("setValue","");
	$('#param').textbox("setText","");
	$('#routecode').combobox("setValue","");
	$('#billtype1').combobox("setValue","");
	search();
}

function initCutomerTable(){
	$("#billtype").val("10");
	$("#routecode1").combobox({
	    url : baseURL+"/comm/combobox/getRoutesComboboxByDeptid.json?deptid=",//返回json数据的url
	    valueField : "routecode",//这个id和你返回json里面的id对应
	    textField : "routename", //这个text和你返回json里面的text对应
	    loadFilter : function (data) {
	        if (data && data instanceof Array) {
	            data.splice(0, 0, {routecode: '', routename: '请选择车组'});　
	        }
	        return data;
	    }   	
	})
	$('#customerTable').datagrid({
		//title:'区域表', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		
		url:baseURL+"/wms/customer/getCustomersByCond.json", //数据来源
		queryParams:{
		}, //查询条件
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'code',title:'老专卖证',width:15,
				formatter:function(value,row,index){return row.code;}
			},
			{field:'remarks',title:'新专卖证',width:15,
				formatter:function(value,row,index){return row.remarks;}
			},
			{field:'name',title:'零售户店名',width:50,
				formatter:function(value,row,index){return row.name;}
			},
			{field:'contact',title:'联系人',width:10,
				formatter:function(value,row,index){return row.contact;}
			},
			{field:'contactphone',title:'联系电话',width:15,
				formatter:function(value,row,index){return row.contactphone;}
			},
			{field:'routecode',title:'配送车组',width:10,
				formatter:function(value,row,index){return row.routecode;}
			}
		]],
		toolbar:'#customerToolbar',
		onLoadSuccess:function(){
			$('#customerTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			//$('#tabdiv .panel-header').css('display','none'); 
		}
	});
}
/**
 * 设置发票客户
 * @returns
 */
function savePLAdd(){
	var rows = $('#customerTable').datagrid('getSelections');
	if(rows.length==0){
	$.messager.alert('提示',"请选择要添加为发票客户的零售户！",'info');
		return;
	}
	var ids="";
	var billtype=$('#billtype').val();
	$.each(rows,function(i,n){
  			ids += "&id="+n.id;
  	});
                $.ajax({  
                    type: "post",  
                    //contentType: "application/json", //必须有
                    //将父id放在id串的第一个
                    url: baseURL+'/wms/customer/doBillCustomerPLAdd.json?billtype='+billtype+ids,
                        beforeSend : function () {
                			$.messager.progress({
                				text : '正在更新中...',
                			});
                		},
                    success: function (data) {  
            			$.messager.progress('close');
            			//$('#pladd-dlg').dialog('close');
            			$('#dataTable').datagrid('reload'); 
                		$.messager.show({
            				title : '提示',
            				msg :  '发票客户添加'+data.msg+'！',
            			});
                    } 
                });  
}

/**
 * 取消预付款客户设置
 * @returns
 */
function delBillCustomer(){
	var rows = $('#dataTable').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择要删除的发票客户！",'info');
		return;
	}
	var ids="";
	$.each(rows,function(i,n){
		if(i==0)ids += "?id="+n.id;
		else ids += "&id="+n.id;
	});
	$.messager.confirm('提示','确定要删除所选的发票客户信息吗?',function(result){
	if (result){
	$.ajax({  
		type: "post",  
		//contentType: "application/json", //必须有
		//将父id放在id串的第一个
		url: baseURL+'/wms/customer/doDelBillCustomer.json'+ids,
		beforeSend : function () {
			$.messager.progress({
				text : '正在更新中...',
			});
		},
		success: function (data) {  
			$.messager.progress('close');
			$('#dataTable').datagrid('reload'); 
			$.messager.show({
				title : '提示',
				msg :  '发票客户删除'+data.msg+'！',
			});
		} 
	});  
	}})
}

