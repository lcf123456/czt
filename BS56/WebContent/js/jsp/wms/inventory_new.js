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
		url:baseURL+"/wms/inventorynew/getInventoryPageList.json", //数据来源
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
			{field:'orderdate',title:'订单日期',width:30,sortable:true,
				formatter:function(value,row,index){return row.orderdate.substring(0,10);}
			},
			{field:'createtime',title:'盘点日期',width:30,sortable:true,
				formatter:function(value,row,index){return row.createtime.substring(0,19);}
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
		},

		onDblClickCell:function(index,field,value){
			openView();
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
	
	var createtime=getDateYMDHMS();
	$('#createtime').datetimebox('setValue', createtime);

	var orderdate=getDateYMD();
	$('#orderdate').datebox('setValue', orderdate);
	
	$('#inventorytype').textbox('setValue',10);
	$('#inventorytype').textbox('setText','日清日结');
}

function checkNum(){
	var flag=0;
	var indate=$('#createtime').datebox("getValue").substring(0,10);
	var rows = $('#dataTable').datagrid('getRows');
	var createtime="";
	for(var i=0;i<rows.length;i++){
		createtime=rows[i]["createtime"].substring(0,10);
		if(indate==createtime){
			flag=1;
			$.messager.alert('提示',"所选日期已经存在盘点信息，请重新选择要盘点的时间！",'info');
			break;
		}
	}
	
	if(flag==0){
		saveAdd();
	}
}

/**
  * 保存盘点主表信息
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
		url:baseURL+"/wms/inventorynew/doInventoryAdd.json",
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
		var date=row.createtime.substring(0,19);
		var orderdate=row.orderdate.substring(0,10);
		$('#inventory-dlg').dialog('open').dialog('setTitle','盘点信息');
		$('#subBtn').linkbutton({disabled:false});
		$('#inventoryid').val(row.inventoryid);
		$('#createtime1').val(date);
		$('#orderdate1').val(orderdate);
		newTableInit(date,orderdate);
	}
}


/**
 * 盘点数据初始化
 * @returns
 */
function newTableInit(date,orderdate){
		$('#newTable').datagrid({
			//title:'区域表', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:true, //多选
			height:395, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			url:baseURL+"/wms/inventorynew/getInventoryInfo.json", //数据来源
			sortName: 'cigarettename', //排序的列
			sortOrder: 'asc', //倒序
			remoteSort: false, //服务器端排序
			//idField:'INVENTORYID', //主键字段
			//pageNumber: 1, 
			//pageSize : 10,
			//pageList: [10,30,50],
			queryParams:{
				searchdate:date,
				orderdate:orderdate
			}, //查询条件
			//pagination:true, //显示分页
			//pageSize : 1,
			rownumbers:true, //显示行号
			showFooter:true,
			onLoadSuccess:function(){
				mergeCellsByField("newTable", "cigarettecode,cigarettename,paperqty,atscellqty,scatteredqty,unnormalno1,unnormalqty1,unnormalno2,unnormalqty2,commonno,commonqty,virtualqty,diffqty", 0);
				$('#newTable').datagrid('reloadFooter',[{
					cigarettename: '<B>合计</B>',
					paperqty: '<B>'+compute("paperqty")+'</B>',
					atscellqty: '<B>'+compute("atscellqty")+'</B>',
					scatteredqty: '<B>'+compute("scatteredqty")+'</B>',
					shelfqty: '<B>'+compute("shelfqty")+'</B>',
					sortingqty: '<B>'+compute("sortingqty")+'</B>',
					unnormalqty1: '<B>'+compute("unnormalqty1")+'</B>',
					unnormalqty2: '<B>'+compute("unnormalqty2")+'</B>',
					commonqty: '<B>'+compute("commonqty")+'</B>',
					virtualqty: '<B>'+compute("virtualqty")+'</B>',
					diffqty: '<B>'+compute("diffqty")+'</B>'
				}]);
			},
		});
}

/**
 * EasyUI DataGrid根据字段动态合并单元格
 * param tableID 要合并table的id
 * param colList 要合并的列,用逗号分隔(例如："name,department,office");
 * param mainColIndex 要合并的主列索引
 */
 function mergeCellsByField(tableID, colList, mainColIndex) {
     var ColArray = colList.split(",");
     var tTable = $('#' + tableID);
     var TableRowCnts = tTable.datagrid("getRows").length;
     var tmpA;
     var tmpB;
     var PerTxt = "";
     var CurTxt = "";
     var alertStr = "";
     for (var i = 0; i <= TableRowCnts ; i++) {
         if (i == TableRowCnts) {
             CurTxt = "";
         }
         else {
             CurTxt = tTable.datagrid("getRows")[i][ColArray[mainColIndex]];
         }
         if (PerTxt == CurTxt) {
             tmpA += 1;
         }
         else {
             tmpB += tmpA;
             for (var j = 0; j < ColArray.length; j++) {
                 tTable.datagrid('mergeCells', {
                     index: i - tmpA,
                     field: ColArray[j],
                     rowspan: tmpA,
                     colspan: null
                 });
             }
             tmpA = 1;
         }
         PerTxt = CurTxt;
     }
 }

//指定列求和
function compute(colName) {
     var rows = $('#newTable').datagrid('getRows');
     var total = 0;
     var itemno="",tmpno="";
     for (var i = 0; i < rows.length; i++) {
    	 itemno=data=rows[i]["cigarettecode"];
    	 var data=rows[i][colName];
    	 if(data==null||data=="")data=0;
    	 if(colName=="scatteredqty"||colName=="paperqty"||colName=="atscellqty"||colName=="diffqty"||colName=="virtualqty"){
    		 if(itemno!=tmpno)total=Math.round((total+parseFloat(data))*10000)/10000;
    	 }else{
    		 total=Math.round((total+parseFloat(data))*10000)/10000; 
    	 }
    	 tmpno=itemno;
     }
     return total;
 }

/**
 * 提交
 * @returns
 */
function saveInventory(){
		var totaldiff=compute("diffqty");
		if(totaldiff!=0){
			alert("当前盘点数量与账面量不符，您可要通过调整虚拟区数量将账面做平再继续保存！");
			return false;
		}else{
	        $.messager.confirm('确认', '是否已完成所有区域的盘点信息确认?', function(r){  
	            if (r){  
	                $.ajax({  
	                    type: "post",  
	                    contentType: "application/json", //必须有  
	                    url: baseURL+'/wms/inventorynew/doInventoryInfoComplete.json?inventoryid='+$('#inventoryid').val()+'&createtime='+$('#createtime1').val()
	                    //?atscellJson=JSON.stringify($('#ATSCellTable').datagrid("getRows"))
	                    						//+'&sortJson='+JSON.stringify($('#ATSCellTable').datagrid("getRows"))
	                    						,  
	                    dataType: 'json',  
	                    //data:JSON.stringify($('#ATSCellTable').datagrid("getRows")),
	                    //data:JSON.stringify({"atscell":JSON.stringify($('#ATSCellTable').datagrid("getRows"),"sort":JSON.stringify($('#ATSCellTable').datagrid("getRows")}),
	                    data:
	                    	JSON.stringify({
		                    	"newdata":JSON.stringify($('#newTable').datagrid("getRows"))
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
		var date=row.createtime.substring(0,19);
		newTableView(inventoryid,date);  //立库
	}
}

function newTableView(inventoryid,searchdate){
	$('#newTable').datagrid({
		//title:'区域表', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //多选
		height:395, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		url:baseURL+"/wms/inventorynew/getInventoryInfoView.json", //数据来源
		sortName: 'cigarettename', //排序的列
		sortOrder: 'asc', //倒序
		remoteSort: false, //服务器端排序
		//idField:'INVENTORYID', //主键字段
		//pageNumber: 1, 
		//pageSize : 10,
		//pageList: [10,30,50],
		queryParams:{
			searchdate:searchdate,
			inventoryid:inventoryid
		}, //查询条件
		//pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		showFooter:true,
		onLoadSuccess:function(){
			mergeCellsByField("newTable", "cigarettecode,cigarettename,paperqty,atscellqty,scatteredqty,unnormalno1,unnormalqty1,unnormalno2,unnormalqty2,commonno,commonqty,diffqty", 0);
			$('#newTable').datagrid('reloadFooter',[{
				cigarettename: '<B>合计</B>',
				paperqty: '<B>'+compute("paperqty")+'</B>',
				atscellqty: '<B>'+compute("atscellqty")+'</B>',
				scatteredqty: '<B>'+compute("scatteredqty")+'</B>',
				shelfqty: '<B>'+compute("shelfqty")+'</B>',
				sortingqty: '<B>'+compute("sortingqty")+'</B>',
				unnormalqty1: '<B>'+compute("unnormalqty1")+'</B>',
				unnormalqty2: '<B>'+compute("unnormalqty2")+'</B>',
				commonqty: '<B>'+compute("commonqty")+'</B>',
				diffqty: '<B>'+compute("diffqty")+'</B>'
			}]);
		},
	});
}
