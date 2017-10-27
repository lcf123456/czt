/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	$('#dataTable').datagrid({
		title:'巷道表', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/wms/laneway/getLanewayPageList.json", //数据来源
		sortName: 'lanewayno', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: false, //服务器端排序
		idField:'lanewayno', //主键字段
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
			{field:'lanewayno',title:'巷道编号',width:10,sortable:true,
				formatter:function(value,row,index){return row.lanewayno;} //需要formatter一下才能显示正确的数据
			},
			{field:'lanewayname',title:'巷道名称',width:30,
				formatter:function(value,row,index){return row.lanewayname;}
			},
			{field:'status',title:'状态',width:10,
				formatter:function(value,row,index){
					if( row.status == '10'){
						return '正常';
					}
					else if( row.status == '20'){
						return '禁入';
					}
					else if( row.status == '30'){
						return '禁出';
					}
					else if( row.status == '40'){
					return '禁用';
					}
				}
			},
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none'); 
			
		}
	});
	
});

//查询
function searchLaneway(){
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
	searchLaneway();
}

