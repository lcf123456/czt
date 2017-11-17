/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	//var nowTime01 = getDateYM01();
	var nowTime = getDateYMD();
	$('#searchtime').datebox("setValue",nowTime);
	$('#searchtime2').datebox("setValue",nowTime);
	//initdatetype();
	//var obj = $("#consignsorsearch");
	//initconsignsor(obj);
	$('#dataTabel').datagrid({
		title:'订单分析', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //单选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		nowrap:false,
		//货主默认为长沙11430101
		url:baseURL+"/wms/shiporder/getShiporderAnalyzeList.json", //数据来源
		sortName: 'routecode', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'orderno', //主键字段
		//pageNumber: 1, 
		//pageSize : 15,
		//pageList: [15,30,50],
		queryParams:{
			}, //查询条件
		//pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'routecode',title:'配送线路',width:$(this).width()*0.15},
			{field:'ordercount',title:'订单总数',width:$(this).width()*0.08,sortable:true},
			{field:'totalqty',title:'订单总量(条)',width:$(this).width()*0.08,sortable:true},
			{field:'totalamount',title:'订单总金额',width:$(this).width()*0.08,sortable:true},
			{field:'paymentcount',title:'代扣笔数',width:$(this).width()*0.1},
			{field:'paymentamount',title:'代扣金额',width:$(this).width()*0.1}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none'); 
			
		},
		onDblClickCell:function(index,field,value){
			viewD();
		}
	});
});

function initItem(){
		$("#searchitemid").combobox({
	        valueField:'itemno',
	        textField:'itemname',
	        url:baseURL+"/wms/item/getItemComboboxByCond.json", //数据来源
		onLoadSuccess: function () {
			 var val = $(this).combobox("getData");
			 for (var item in val[0]) {
	             if (item == "itemno") {
	                 $(this).combobox("select", "");
	             }
	         }
		}
	    });
}

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
	//$('#queryForm').form('clear');
	$('#keyword').textbox("setValue","");
	$('#keyword').textbox("setText","");
	initItem();
	searchData();
}



/**
 * 查看窗口
 */
  function viewD(){
	  $('#view-fm').form('clear');
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
		var orderNo = row.orderno;
		if (row){
			$('#view-dlg').dialog('open').dialog('setTitle',"查看");
			$('#view-dlg').form('load',row);
			getShiporderlinelist(orderNo);
		}

	}
  
	//出库单明细列表
	  function getShiporderlinelist(orderNo)
	  {
	  		//获取领料新增分配的物资明细列表
	  	$('#linelist').datagrid({
	  		title:'订单明细列表', //标题
	  		method:'post',
	  		iconCls:'icon-view', //图标
	  		singleSelect:false, //多选
	  		height:280, //高度
	  		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
	  		striped: true, //奇偶行颜色不同
	  		collapsible:false,//可折叠
	  		url:baseURL+"/wms/shiporder/getShiporderlineByNo.json?orderNo="+orderNo, 
	  		sortName: 'id', //排序的列
	  		sortOrder: 'desc', //倒序
	  		remoteSort: true, //服务器端排序
	  		idField:'id', //主键字段
	  		singleSelect: true,
	  		pagination:false, //显示分页
	  		//pageSize : 1,
	  		rownumbers:true, //显示行号
	  		columns:[[
	  			{field:'itemId',title:'卷烟编码',width:$(this).width()*0.1,
	  				formatter:function(value,row,index){return row.itemId;}
	  			},
	  			{field:'itemname',title:'卷烟名称',width:$(this).width()*0.1,
	  				formatter:function(value,row,index){return row.itemname;} //需要formatter一下才能显示正确的数据
	  			},
	  			{field:'itemprice',title:'单价',width:$(this).width()*0.1,
	  				formatter:function(value,row,index){return row.itemprice;} //需要formatter一下才能显示正确的数据
	  			},
	  			{field:'qty',title:'数量',width:$(this).width()*0.1,
	  				formatter:function(value,row,index){return row.qty;} //需要formatter一下才能显示正确的数据
	  			},
	  			{field:'saleamount',title:'金额',width:$(this).width()*0.1,
	  				formatter:function(value,row,index){return row.saleamount;}
	  			}
	  		]],
	  		onLoadSuccess:function(){
	  			//$('#inboundlinediv .panel-header').css('display','none'); 
	  			
	  		}
	  	});
	  	}
	  
	  
