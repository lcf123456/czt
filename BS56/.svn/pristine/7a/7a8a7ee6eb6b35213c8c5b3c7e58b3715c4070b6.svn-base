/**
 * 页面列表datagrid初始化
 */

	var editRow = undefined; //定义全局变量：当前编辑的行
	
jQuery(function($){

	$('#dataTabel').datagrid({
		title:'车辆入园', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //单选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/gis/truckseq/getTruckseqTodayPageList.json", //数据来源
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
			{field:'companyname',title:'工业公司名称',width:$(this).width()*0.15,sortable:true,
				formatter:function(value,row,index){return row.companyname;}
			},
			{field:'vehicleno',title:'车牌',width:$(this).width()*0.05,sortable:true,
				formatter:function(value,row,index){return row.vehicleno;}
			},
			{field:'qty',title:'数量（件）',width:$(this).width()*0.05,sortable:true,
				formatter:function(value,row,index){return row.qty;}
			},
			{field:'seq',title:'排序',width:$(this).width()*0.05,sortable:true,editor:'numberbox',
				formatter:function(value,row,index){return row.seq;}
			},
			{field:'arrivetime',title:'入园时间',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.arrivetime;} //需要formatter一下才能显示正确的数据
			},
			{field:'begscantime',title:'开始扫码时间',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.begscantime;}
			},
			{field:'endscantime',title:'结束扫码时间',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.endscantime;}
			},
			
			{field:'backtime',title:'出园时间',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.backtime;}
			},
			{field:'statusname',title:'状态',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.statusname;}
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
  
/**
 * 查看窗口
 */
  function viewD(index){
	  $('#add-fm').form('clear');
	  $('#dataTabel').datagrid('selectRow', index);  
      var row = $('#dataTabel').datagrid('getSelected');
		if (row){
			$('#add-dlg').dialog('open').dialog('setTitle',"查看");
			$('#add-dlg').form('load',row);
		}
		
	}

