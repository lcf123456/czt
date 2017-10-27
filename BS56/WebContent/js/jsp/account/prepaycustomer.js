/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	$('#dataTable').datagrid({
		//title:'区域表', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		
		url:baseURL+"/wms/customer/getPrepayCustomers.json", //数据来源
		queryParams:{
		}, //查询条件
		//pageSize : 1,
		rownumbers:true, //显示行号
//		view:groupview,
//		groupField:'parentcustomer',
//        groupFormatter:function(value,rows){
//            //return value + ' - ' + rows.length + ' Item(s)';
//            return "<input type=checkbox name='zid'>"+value;
//        },
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'name',title:'零售户店名',width:30,
				formatter:function(value,row,index){
					if(row.customertype=='2')return row.name;
					else return "&nbsp;&nbsp;&nbsp;&nbsp;"+row.name;
				}
			},
			{field:'code',title:'老专卖证',width:30,
				formatter:function(value,row,index){
					if(row.customertype!='2')return row.code;}
			},
			{field:'remarks',title:'新专卖证',width:30,
				formatter:function(value,row,index){
					if(row.customertype!='2')return row.remarks;}
			},
			{field:'contact',title:'联系人',width:30,
				formatter:function(value,row,index){return row.contact;}
			},
			{field:'contactphone',title:'联系电话',width:30,
				formatter:function(value,row,index){return row.contactphone;}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			//$('#tabdiv .panel-header').css('display','none'); 
		}
	});
	
});

function openNew(){
	$('#add-dlg').dialog('open').dialog('setTitle','一级预付款客户--新增');
	$('#add-fm').form('clear');
	
	$('#delstatus').val("10");
	$('#prepayflag').val("1");
	$('#customertype').val("2");
}

function saveAdd(){
	$('#add-fm').form('submit',{
		onSubmit: function(){
			var isValidate = $(this).form('validate');
			if(isValidate){
			}
			return isValidate;
		},
		url:baseURL+"/wms/customer/doCustomerAdd.json",
		beforeSend : function () {
			$.messager.progress({
				text : '正在新增中...',
			});
		},
		success: function(data){
			data = eval('('+data+')');
			$('#add-dlg').dialog('close');
			$('#dataTable').datagrid('reload'); 
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个预付款客户新增'+data.msg+'！',
			});
		}
	});
}

function openPLNew(){
	
	var rows = $('#dataTable').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择一级预付款客户！",'info');
		return;
	}
	var row = $('#dataTable').datagrid('getSelected');
	if(row.customertype!='2'){
		$.messager.alert('提示',"请选择一级预付款客户！",'info');
		return;
	}
	$('#pladd-dlg').dialog('open').dialog('setTitle','预付款客户--新增');
	$('#pladd-fm').form('clear');
	$('#prepayparentid').val(row.id);
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
	search();
}

function initCutomerTable(){
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
 * 添加二级预付款客户
 * @returns
 */
function savePLAdd(){
	var rows = $('#customerTable').datagrid('getSelections');
	if(rows.length==0){
	$.messager.alert('提示',"请选择要添加为预付款客户的零售户！",'info');
		return;
	}
	var ids="";
	var prepayparentid=$('#prepayparentid').val();
	$.each(rows,function(i,n){
  			ids += "&id="+n.id;
  	});
                $.ajax({  
                    type: "post",  
                    //contentType: "application/json", //必须有
                    //将父id放在id串的第一个
                    url: baseURL+'/wms/customer/doCustomerPLAdd.json?id='+prepayparentid+ids,
                        beforeSend : function () {
                			$.messager.progress({
                				text : '正在更新中...',
                			});
                		},
                    success: function (data) {  
            			$.messager.progress('close');
            			$('#pladd-dlg').dialog('close');
            			$('#dataTable').datagrid('reload'); 
                		$.messager.show({
            				title : '提示',
            				msg :  '预付款客户添加'+data.msg+'！',
            			});
                    } 
                });  
}

/**
 * 取消预付款客户设置
 * @returns
 */
function delPrepayCustomer(){
	var rows = $('#dataTable').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择要删除的预付款客户！",'info');
		return;
	}
	var ids="";
	$.each(rows,function(i,n){
		if(i==0)ids += "?id="+n.id;
		else ids += "&id="+n.id;
	});
	$.messager.confirm('提示','确定要删除所选的预付款客户信息吗?',function(result){
	if (result){
	$.ajax({  
		type: "post",  
		//contentType: "application/json", //必须有
		//将父id放在id串的第一个
		url: baseURL+'/wms/customer/doDelPrepayCustomer.json'+ids,
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
				msg :  '预付款客户删除'+data.msg+'！',
			});
		} 
	});  
	}})
}

