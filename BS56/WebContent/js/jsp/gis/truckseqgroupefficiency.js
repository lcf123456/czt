/**
 * 装卸效率
 */

	
jQuery(function($){
	var nowTime01 = getDateYM01();
	var nowTime = getDateYMD();
		$('#searchtime').datebox("setValue",nowTime01);
		$('#searchtime2').datebox("setValue",nowTime);
		
	   $('#dataTabel').datagrid({
		title:'组装卸效率', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //单选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/gis/truckseq/gettGroupEfficiencyList.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 20,
		pageList: [20,30,50],
		queryParams:{
			}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'groupcode',title:'装卸组编码',width:$(this).width()*0.15,sortable:true,
				formatter:function(value,row,index){return row.groupcode;}
			},
			{field:'groupname',title:'装卸组名称',width:$(this).width()*0.45,sortable:true,
				formatter:function(value,row,index){return row.groupname;}
			},
			{field:'qty',title:'数量（件）',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.qty;}
			},
			{field:'loadtime',title:'用时(小时)',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.loadtime;}
			},
			{field:'efficiency',title:'效率(件/时)',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.efficiency;}
			},
			{field:'_operate',title:'操作',width:$(this).width()*0.05,
				formatter:formatOper
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none'); 
			
		}
	});
});

function formatOper(val,row,index){  
    return '<a href="#" onclick="viewD('+index+')">查看</a>';  
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

/**
 * 查看窗口
 */
  function viewD(index){
	  $('#dataTabel').datagrid('selectRow', index);  
      var row = $('#dataTabel').datagrid('getSelected');
      var stime = $("#searchtime").val();
       var stime2 = $("#searchtime2").val();
      
		if (row){
			var groupcode = row.groupcode;
			$('#add-dlg').dialog('open').dialog('setTitle',"查看");
			$('#detaildataTabel').datagrid({
				title:'装卸效率明细', //标题
				method:'post',
				iconCls:'icon-edit', //图标
				singleSelect:true, //单选
				height:360, //高度
				fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, //奇偶行颜色不同
				collapsible:true,//可折叠
				url:baseURL+"/gis/truckseq/getGroupEfficiencyDetailPageList.json?groupcode="+groupcode+"&searchtime="+stime+"&searchtime2="+stime2, //数据来源
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
					{field:'companyname',title:'工业公司名称',width:$(this).width()*0.15,sortable:true,
						formatter:function(value,row,index){return row.companyname;}
					},
					{field:'vehicleno',title:'车牌',width:$(this).width()*0.05,sortable:true,
						formatter:function(value,row,index){return row.vehicleno;}
					},
					{field:'qty',title:'数量(件)',width:$(this).width()*0.05,sortable:true,
						formatter:function(value,row,index){return row.qty;}
					},
					{field:'begscantime',title:'开始扫码时间',width:$(this).width()*0.1,
						formatter:function(value,row,index){return row.begscantime;}
					},
					{field:'endscantime',title:'结束扫码时间',width:$(this).width()*0.1,
						formatter:function(value,row,index){return row.endscantime;}
					},
					{field:'loadtime',title:'用时',width:$(this).width()*0.05,
						formatter:function(value,row,index){return row.loadtime;}
					},
					{field:'efficiency',title:'效率(件/时)',width:$(this).width()*0.05,
						formatter:function(value,row,index){return row.efficiency;}
					},
					{field:'statusname',title:'状态',width:$(this).width()*0.05,
						formatter:function(value,row,index){return row.statusname;}
					}
				]],
				onLoadSuccess:function(){
					$('#detaildataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
					$('#tabdiv2 .panel-header').css('display','none'); 
					
				}
			});
		}
		
	}

