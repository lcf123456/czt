var begdate=getDateYM01();
var enddate=getDateYMD();
/**
* 页面列表datagrid初始化
 */
jQuery(function($){
	$('#begdate').datebox('setValue', begdate);
	$('#enddate').datebox('setValue', enddate);
	$('#billtype1').combobox('setValue', "10");
	$('#param').textbox('textbox').keydown(function(e){
		if(e.keyCode==13){
			searchCust();
		}
	})
	$('#dataTable').datagrid({
		//title:'发票打印', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/account/invoiceexport/getInvoiceExportList.json", //数据来源
		sortName: 'name', //排序的列
		sortOrder: 'asc', //正序asc、倒序 desc
		remoteSort: true, //服务器端排序
//		idField:'id', //主键字段
//		pageNumber: 1, 
//		pageSize : 10,
//		pageList: [10,30,50],
		queryParams:{
			begdate:begdate,
			enddate:enddate,
			billtype:"10"
			}, //查询条件
		//pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		toolbar:'#toolbar',
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'id2',title:'客户代码',width:20,
				formatter:function(value,row,index){
					return row.id;
				}
			},
			{field:'code',title:'客户代码',width:20,
				formatter:function(value,row,index){
					return row.code;
				}
			},
			{field:'name',title:'客户名称',width:30,
				formatter:function(value,row,index){
					return row.name;}
			},
			{field:'qty',title:'订单量',width:10,
				formatter:function(value,row,index){return row.qty;}
			},
			{field:'amount',title:'订单金额',width:10,
				formatter:function(value,row,index){return row.amount;}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			//$('#tabdiv .panel-header').css('display','none'); 
		},
		onDblClickCell:function(index,field,value){
			openView();
		}
	});
	
	
});


function openView(){
	var rows = $('#dataTable').datagrid('getSelections');
	if(rows.length==0){
	$.messager.alert('提示',"请选择要查看订单信息的零售户！",'info');
		return;
	}
	if(rows.length>1){
		$.messager.alert('提示',"只能选择一个零售户进行订单信息的查看！",'info');
		return;
	}
	var row = $('#dataTable').datagrid('getSelected');
	var url=baseURL+'/account/invoiceexport/getOrderDetail.json?id='+row.id+'&begdate='+$('#begdate').datebox('getValue')+'&enddate='+$('#enddate').datebox('getValue');
	$('#view-dlg').dialog('open').dialog('setTitle','订单信息--查看');
	initOrderTable(url);
}

function searchCust(){
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
	$('#begdate').datebox('setValue', begdate);
	$('#enddate').datebox('setValue', enddate);
	searchCust();
}

function initOrderTable(url){
	
	$('#viewDataTable').datagrid({
		//title:'区域表', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:url, //数据来源
		queryParams:{
		}, //查询条件
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			//{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'orderdate',title:'订单日期',width:15,
				formatter:function(value,row,index){return row.orderdate.substring(0,10);}
			},
			{field:'itemcode',title:'品牌代码',width:15,
				formatter:function(value,row,index){return row.itemcode;}
			},
			{field:'itemname',title:'品牌名称',width:15,
				formatter:function(value,row,index){return row.itemname;}
			},
			{field:'itemprice',title:'品牌单价',width:10,
				formatter:function(value,row,index){return row.itemprice;}
			},
			{field:'itemqty',title:'订单数量',width:10,
				formatter:function(value,row,index){return row.itemqty;}
			},
			{field:'itemamount',title:'订单金额',width:10,
				formatter:function(value,row,index){return row.itemamount;}
			}
		]],
		//toolbar:'#customerToolbar',
		onLoadSuccess:function(){
			$('#viewDataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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

/**
 * 导出到TXT
 * @returns
 */
function exportFormToTxt(){
	begdate=$('#begdate').datebox('getValue');
	enddate=$('#enddate').datebox('getValue');
	var  billtype=$('#billtype1').combobox("getValue");
	var rows = $('#dataTable').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择要导出的发票客户！",'info');
		return;
	}
	var ids="";
	$.each(rows,function(i,n){
		if(i==0)ids += +n.id;
		else ids += ","+n.id;
	});
	$.messager.confirm('提示','确定要导出所选的客户的发票信息吗?',function(result){
		if (result){
			$('#billtype').val(billtype);
			$('#ids').val(ids);
			$('#exportBegdate').val(begdate);
			$('#exportEnddate').val(enddate);
			$('#exportForm').form('submit', {
				url:baseURL+"/account/invoiceexport/doExportDatal.json",
       	 	});
		}})
}

