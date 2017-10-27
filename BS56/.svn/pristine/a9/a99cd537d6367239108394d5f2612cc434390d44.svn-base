/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	var begdate=getDateYM01();
	var enddate=getDateYMD();
	$('#begdate').datebox('setValue', begdate);
	$('#enddate').datebox('setValue', enddate);
	$('#dataTable').datagrid({
		//title:'区域表', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/wms/inventory/getInventoryPageList.json", //数据来源
		sortName: 'createtime', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: false, //服务器端排序
		idField:'INVENTORYID', //主键字段
		pageNumber: 1, 
		pageSize : 10,
		pageList: [10,30,50],
		queryParams:{
			begdate:begdate,
			enddate:enddate
		}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'inventoryid',checkbox:true,width:2}, //显示复选框
//			{field:'inventoryid',title:'编号id',width:30,
//				formatter:function(value,row,index){return row.inventoryid;} //需要formatter一下才能显示正确的数据
//			},
			{field:'createtime',title:'盘点日期',width:30,sortable:true,
				formatter:function(value,row,index){return row.createtime.substring(0,10);}
			},
			{field:'inventorytype',title:'盘点类型',width:30,
				formatter:function(value,row,index){
					if(row.inventorytype=='10')return '<span >日清日结</span>';
					else return row.inventorytype;
				} //需要formatter一下才能显示正确的数据
			},
			{field:'status',title:'盘点状态',width:30,
				formatter:function(value,row,index){
					if( row.status == '10'){
						return '<span >已完成</span>';
					}else{
					return '<span ><font color="red">未完成</font></span>';}}
			},
			{field:'createname',title:'记录人',width:30,sortable:true,
				formatter:function(value,row,index){return row.createname;}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			//$('#tabdiv .panel-header').css('display','none'); 
		}
	});
	
});

/**
 * 打开新增窗口
 * @returns
 */
function openNew(){
	$('#add-fm').form('clear');
	$('#add-dlg').dialog('open').dialog('setTitle','新增盘点信息');
	
	var createtime=getDateYMD();
	$('#createtime').datebox('setValue', createtime);
	
	$('#inventorytype').textbox('setValue',10);
	$('#inventorytype').textbox('setText','日清日结');
}

/**
 * 保存基礎類別信息
 */
function saveAdd(){
	$('#add-fm').form('submit',{
		onSubmit: function(){
			var isValidate = $(this).form('validate');
			if(isValidate){
				//$('#loading').window('open');
			}
			return isValidate;
		},
		url:baseURL+"/wms/inventory/doInventoryAdd.json",
		data:$("#add-fm").serializeArray(),
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
				msg :  '盘点信息表新增'+data.msg+'！',
			});
		}
	});
}

/**
 * 打开盘点窗口
 * @returns
 */
function openInventory(){
	var rows = $('#dataTable').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择盘点信息!",'info');
		return;
	}
	if(rows.length > 1){
		$.messager.alert('提示',"每次只能选择一条盘点信息进行更新!",'info');
		return;
	}
	var row = $('#dataTable').datagrid('getSelected');
	if(row.status=='10'){
		$.messager.alert('提示',"请选择未完成的盘点信息！",'info');
		return;
	}
	if (row){
		var date=row.createtime.substring(0,10);
		$('#inventory-dlg').dialog('open').dialog('setTitle','盘点信息');
		$('#inventoryid').val(row.inventoryid);
		ATSCellInit(date);//立库
		ScatteredInit(date);//散烟区
		SortingCellInit(date);//分拣区
		ShelfInit(date);//重力式货架
	}
}


/**
 * 立库区盘点初始化
 * @returns
 */
function ATSCellInit(date){
		$('#ATSCellTable').datagrid({
			//title:'区域表', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:true, //多选
			height:320, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			url:baseURL+"/wms/atscellinfodetail/getATSCellInfoSummary.json", //数据来源
			sortName: 'cigarettename', //排序的列
			sortOrder: 'asc', //倒序
			remoteSort: false, //服务器端排序
			//idField:'INVENTORYID', //主键字段
			//pageNumber: 1, 
			//pageSize : 10,
			//pageList: [10,30,50],
			queryParams:{
				searchdate:date
			}, //查询条件
			//pagination:true, //显示分页
			//pageSize : 1,
			rownumbers:true, //显示行号
			columns:[[
				{field:'cigarettecode',title:'品牌代码',width:10,sortable:true,
					formatter:function(value,row,index){return row.cigarettecode;}
				},
				{field:'cigarettename',title:'品牌名称',width:10,sortable:true,
					formatter:function(value,row,index){return row.cigarettename;}
				},
				{field:'llqty',title:'理论数量',width:5,
					formatter:function(value,row,index){return row.qty;}
				},
				{field:'qty',title:'实际数量',width:5,editor:'numberbox',
					formatter:function(value,row,index){return row.qty;}
				}
			]],
		});
		$('#ATSCellTable').datagrid('enableCellEditing').datagrid('gotoCell', {
            index: 0,
            field: 'cigarettecode'
        });
}

/**
 * 散烟区盘点初始化
 * @returns
 */
function ScatteredInit(date){
		$('#ScatteredTable').datagrid({
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:true, //多选
			height:320, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			url:baseURL+"/wms/inout/getInOutInfoSummaryByCond.json", //数据来源
			sortName: 'cigarettename', //排序的列
			sortOrder: 'asc', //倒序
			remoteSort: false, //服务器端排序
			queryParams:{
				searchdate:date
			}, //查询条件
			rownumbers:true, //显示行号
			columns:[[
				{field:'cigarettecode',title:'品牌代码',width:10,sortable:true,
					formatter:function(value,row,index){return row.cigarettecode;}
				},
				{field:'cigarettename',title:'品牌名称',width:10,sortable:true,
					formatter:function(value,row,index){return row.cigarettename;}
				},
				{field:'itemqty',title:'上期盘点',width:5,
					formatter:function(value,row,index){return row.itemqty;}
				},
				{field:'inoutqty',title:'调拨数量',width:5,
					formatter:function(value,row,index){return row.inoutqty;}
				},
				{field:'totalqty',title:'合计数量',width:5,editor:'numberbox',
					formatter:function(value,row,index){return row.totalqty;}
				}
				]],
		});
		$('#ScatteredTable').datagrid('enableCellEditing').datagrid('gotoCell', {
			index: 0,
			field: 'cigarettecode'
		});
}

/**
 * 分拣区盘点初始化
 * @returns
 */
function SortingCellInit(date){
	$('#SortingCellTable').datagrid({
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //多选
		height:320, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		url:baseURL+"/produce/sorttrough/getSortTroughInfoSummary.json", //数据来源
		sortName: 'machineseq', //排序的列
		sortOrder: 'asc', //倒序
		remoteSort: false, //服务器端排序
		queryParams:{
			searchdate:date
		}, //查询条件
		rownumbers:true, //显示行号
		columns:[[
			{field:'machineseq',title:'通道号',width:10,sortable:true,
				formatter:function(value,row,index){return row.machineseq;}
			},
			{field:'cigarettecode',title:'品牌代码',width:10,sortable:true,
				formatter:function(value,row,index){return row.cigarettecode;}
			},
			{field:'cigarettename',title:'品牌名称',width:10,sortable:true,
				formatter:function(value,row,index){return row.cigarettename;}
			},
			{field:'mantissa',title:'上期尾数',width:5,
				formatter:function(value,row,index){return row.mantissa;}
			},
			{field:'inoutqty',title:'调拨数量',width:5,
				formatter:function(value,row,index){return row.inoutqty;}
			},
			{field:'lastmantissa',title:'理论数量',width:5,
				formatter:function(value,row,index){
//					var mt=row.mantissa;if(mt==null||mt=='')mt=0;
//					var io=row.inoutqty;if(io==null||io=='')io=0;
//					return parseInt(mt)+parseInt(io);
					return row.lastmantissa;
				}
			},
			{field:'fillqty',title:'盘点数量',width:5,editor:'numberbox',
				formatter:function(value,row,index){return row.fillqty;}
			}
			]],
	});
	$('#SortingCellTable').datagrid('enableCellEditing').datagrid('gotoCell', {
		index: 0,
		field: 'cigarettecode'
	});
}

/**
 * 重力式货架盘点初始化
 * @returns
 */
function ShelfInit(date){
	$('#ShelfTable').datagrid({
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //多选
		height:320, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		url:baseURL+"/produce/sorttrough/getShelfInfoSummary.json", //数据来源
		sortName: 'machineseq', //排序的列
		sortOrder: 'asc', //倒序
		remoteSort: false, //服务器端排序
		queryParams:{
			searchdate:date
		}, //查询条件
		rownumbers:true, //显示行号
		columns:[[
			{field:'machineseq',title:'通道号',width:10,sortable:true,
				formatter:function(value,row,index){return row.machineseq;}
			},
			{field:'cigarettecode',title:'品牌代码',width:10,sortable:true,
				formatter:function(value,row,index){return row.cigarettecode;}
			},
			{field:'cigarettename',title:'品牌名称',width:10,sortable:true,
				formatter:function(value,row,index){return row.cigarettename;}
			},
			{field:'mantissa',title:'上期尾数',width:5,
				formatter:function(value,row,index){return row.mantissa;}
			},
			{field:'inoutqty',title:'调拨数量',width:5,
				formatter:function(value,row,index){return row.inoutqty;}
			},
			{field:'lastmantissa',title:'理论数量',width:5,
				formatter:function(value,row,index){
//					var mt=row.mantissa;if(mt==null||mt=='')mt=0;
//					var io=row.inoutqty;if(io==null||io=='')io=0;
//					return parseInt(mt)+parseInt(io);
					return row.lastmantissa;
				}
			},
			{field:'fillqty',title:'盘点数量',width:5,editor:'numberbox',
				formatter:function(value,row,index){return row.fillqty;}
			}
			]],
	});
	$('#ShelfTable').datagrid('enableCellEditing').datagrid('gotoCell', {
		index: 0,
		field: 'cigarettecode'
	});
}

/**
 * 提交
 * @returns
 */
function saveInventory(){
        $.messager.confirm('确认', '是否已完成所有区域的盘点信息确认?', function(r){  
            if (r){  
                $.ajax({  
                    type: "post",  
                    contentType: "application/json", //必须有  
                    url: baseURL+'/wms/inventory/doInventoryInfoComplete.json?inventoryid='+$('#inventoryid').val()
                    //?atscellJson=JSON.stringify($('#ATSCellTable').datagrid("getRows"))
                    						//+'&sortJson='+JSON.stringify($('#ATSCellTable').datagrid("getRows"))
                    						,  
                    dataType: 'json',  
                    //data:JSON.stringify($('#ATSCellTable').datagrid("getRows")),
                    //data:JSON.stringify({"atscell":JSON.stringify($('#ATSCellTable').datagrid("getRows"),"sort":JSON.stringify($('#ATSCellTable').datagrid("getRows")}),
                    data:
                    	JSON.stringify({
	                    	"atscell":JSON.stringify($('#ATSCellTable').datagrid("getRows")),
	                        "scattered":JSON.stringify($('#ScatteredTable').datagrid("getRows")),
	                        "sorting":JSON.stringify($('#SortingCellTable').datagrid("getRows")),
	                        "shelf":JSON.stringify($('#ShelfTable').datagrid("getRows"))
                        }),	
                        beforeSend : function () {
                			$.messager.progress({
                				text : '正在更新中...',
                			});
                		},
                    success: function (data) {  
            			$('#inventory-dlg').dialog('close');
            			$.messager.progress('close');
            			$('#dataTable').datagrid('reload'); 
                		$.messager.show({
            				title : '提示',
            				msg :  '盘点信息表更新'+data.msg+'！',
            			});
                    } 
                });  
            }  
        });  
}

//查询
function searchData(){
	var params = $('#dataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
	var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
	$.each( fields, function(i, field){
		params[field.name] = field.value; //设置查询参数
	}); 
	$('#dataTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
}
//清空查询条件
function clearForm(){
	$('#queryForm').form('clear');
	var begdate=getDateYM01();
	var enddate=getDateYMD();
	$('#begdate').datebox('setValue', begdate);
	$('#enddate').datebox('setValue', enddate);
	searchData();
}

/**
 * 打开查看窗口
 * @returns
 */
function openView(){
	var rows = $('#dataTable').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择要查看的盘点信息!",'info');
		return;
	}
	if(rows.length > 1){
		$.messager.alert('提示',"请只选择一条盘点信息查看!",'info');
		return;
	}
	var row = $('#dataTable').datagrid('getSelected');
	if(row.status=='0'){
		$.messager.alert('提示',"请选择已完成的盘点信息查看！",'info');
		return;
	}
	if (row){
		$('#inventory-dlg').dialog('open').dialog('setTitle','盘点信息查看');
		$('#subBtn').linkbutton({disabled:true});
		var inventoryid=row.inventoryid;
		ATSCellView(inventoryid);  //立库
		shelfView(inventoryid);    //重力式货架
		sortCellView(inventoryid);    //分拣区
		scatteredView(inventoryid);  //散烟区
	}
}

function ATSCellView(inventoryid){
	$('#ATSCellTable').datagrid({
		singleSelect:true, //多选
		height:320, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		url:baseURL+"/wms/inventory/getInventoryLine.json", //数据来源
		queryParams:{
			inventoryid:inventoryid,
			areaid:$('#ATSCellTableAreaid').val()
		}, //查询条件
		rownumbers:true, //显示行号
		columns:[[
			{field:'cigarettecode',title:'品牌代码',width:10,
				formatter:function(value,row,index){return row.cigarettecode;}
			},
			{field:'cigarettename',title:'品牌名称',width:10,
				formatter:function(value,row,index){return row.cigarettename;}
			},
			{field:'boxqty',title:'盘点数量',width:5,
				formatter:function(value,row,index){return row.boxqty;}
			}
		]],
	});
}

function shelfView(inventoryid){
	$('#ShelfTable').datagrid({
		singleSelect:true, //多选
		height:320, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		url:baseURL+"/wms/inventory/getInventoryLine.json", //数据来源
		queryParams:{
			inventoryid:inventoryid,
			areaid:$('#ShelfTableAreaid').val()
		}, //查询条件
		rownumbers:true, //显示行号
		columns:[[
			{field:'troughno',title:'通道号',width:10,
				formatter:function(value,row,index){return row.troughno;}
			},
			{field:'cigarettecode',title:'品牌代码',width:10,
				formatter:function(value,row,index){return row.cigarettecode;}
			},
			{field:'cigarettename',title:'品牌名称',width:10,
				formatter:function(value,row,index){return row.cigarettename;}
			},
			{field:'boxqty',title:'盘点数量',width:5,
				formatter:function(value,row,index){return row.boxqty;}
			}
			]],
	});
}

function sortCellView(inventoryid){
	$('#SortingCellTable').datagrid({
		singleSelect:true, //多选
		height:320, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		url:baseURL+"/wms/inventory/getInventoryLine.json", //数据来源
		queryParams:{
			inventoryid:inventoryid,
			areaid:$('#SortingCellTableAreaid').val()
		}, //查询条件
		rownumbers:true, //显示行号
		columns:[[
			{field:'troughno',title:'通道号',width:10,
				formatter:function(value,row,index){return row.troughno;}
			},
			{field:'cigarettecode',title:'品牌代码',width:10,
				formatter:function(value,row,index){return row.cigarettecode;}
			},
			{field:'cigarettename',title:'品牌名称',width:10,
				formatter:function(value,row,index){return row.cigarettename;}
			},
			{field:'itemqty',title:'盘点数量',width:5,
				formatter:function(value,row,index){return row.itemqty;}
			}
			]],
	});
}

function scatteredView(inventoryid){
	$('#ScatteredTable').datagrid({
		singleSelect:true, //多选
		height:320, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		url:baseURL+"/wms/inventory/getInventoryLine.json", //数据来源
		queryParams:{
			inventoryid:inventoryid,
			areaid:$('#ScatteredTableAreaid').val()
		}, //查询条件
		rownumbers:true, //显示行号
		columns:[[
			{field:'cigarettecode',title:'品牌代码',width:10,
				formatter:function(value,row,index){return row.cigarettecode;}
			},
			{field:'cigarettename',title:'品牌名称',width:10,
				formatter:function(value,row,index){return row.cigarettename;}
			},
			{field:'itemqty',title:'盘点数量',width:5,
				formatter:function(value,row,index){return row.itemqty;}
			}
			]],
	});
}
