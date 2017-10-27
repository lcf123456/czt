var url;

//信息上传进度条初始化
$(function(){
	  $('#loading').window({
			title:'系统提示',
			closable:false,
			collapsible:false,
			minimizable:false,
			maximizable:false,
			resizable:false,
			width:355,
			height:120,
			closed:true,
		    modal:true   
		});
	  
});

/**
 * 页面列表datagrid初始化 */

jQuery(function($){
	var searchdate=getDateYM01();
	$('#searchdate').datebox('setValue', searchdate);
	$('#dataTable').datagrid({
		//title:'退货入库', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //多选,当true时只允许当前选择一行。
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同

		collapsible:true,//可折叠
		url:baseURL+"/gis/deliveryinfo/getDeliveryInfoList.json",  //数据来源
		queryParams:{
			searchdate:searchdate
			}, //查询条件
		pagination:false, //显示分页
		//pageSize : 1,
		showFooter: true,
		rownumbers:true, //显示行号
		columns:[[
			//{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'routecode',title:'车组',width:10,
				formatter:function(value,row,index){return row.routecode;} //需要formatter一下才能显示正确的数据
			},
			{field:'totalcount',title:'总户数',width:10,
				formatter:function(value,row,index){return row.totalcount;}
			},
			{field:'signcount',title:'签收户数',width:10,sortable:false,
				formatter:function(value,row,index){return row.signcount;} 
			},
			{field:'durationlen',title:'时长',width:10,sortable:false,
				formatter:function(value,row,index){return Math.round(row.durationlen*100)/100;} 
			},
			{field:'perdurationlen',title:'平均时长',width:10,sortable:false,
				formatter:function(value,row,index){return Math.round(row.perdurationlen*100)/100;} 
			},
			{field:'qsl',title:'签收率',width:10,sortable:false,
				formatter:function(value,row,index){return Math.round(row.signcount/row.totalcount*10000)/100+"%";} 
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			//$('#tabdiv .panel-header').css('display','none');
		}
	});
});
//	$('#dataTable').datagrid({
//		method:'post',
//		iconCls:'icon-edit', //图标
//		singleSelect:false, //多选
//		height:420, //高度
//		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
//		striped: true, //奇偶行颜色不同
//		collapsible:false,//可折叠
//		url:baseURL+"/gis/deliveryinfo/getDeliveryInfoList.json",  //数据来源
//        collapsible:true,
//         //data:data,
//		queryParams:{
//			searchdate:'2017-08-31'
//			}, //查询条件
//		pagination:false, //显示分页
//		rownumbers:true, //显示行号
//		columns:[[
//			{field:'routecode',title:'车组',width:10,sortable:false,
//				formatter:function(value,row,index){return row.routecode;} //需要formatter一下才能显示正确的数据
//			},
//			{field:'totalcount',title:'总户数',width:10,sortable:false,
//				formatter:function(value,row,index){return row.totalcount;}
//			},
//			{field:'signcount',title:'签收户数',width:10,sortable:false,
//				formatter:function(value,row,index){return row.signcount;} 
//			},
//			{field:'duration',title:'配送时长',width:10,
//				formatter:function(value,row,index){return duration;}
//			},
//			{field:'perduration',title:'平均时长',width:10,
//				formatter:function(value,row,index){return perduration;}
//			}
//		]],
//		toolbar:'#toolbar',
//		onLoadSuccess:function(){
//			alert(123);
//			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
//			//$('#tabdiv .panel-header').css('display','none');
//		}
//	});
//});
	  //查询
	function searchData(){
		var params = $('#dataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		//alert(params.length);
		//alert(fields.length);
		$.each( fields, function(i, field){
			//alert(field.name);
			//alert(field.value);
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#dataTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
	
	function openTopWindow(url, title, width, height) {
        title = title == undefined ? '&nbsp;' : title;
        width = width == undefined ? 800 : width;
        height = height == undefined ? 300 : height;
        if (url != undefined) {
            var content = '<iframe name="eui-open-page" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>';
            parent.$('#openWindow').window({
                title: title,
                width: width,
                height: height,
                content: content,
                modal: true,
                animate: true,
                minimizable: false
            });
        }
    }