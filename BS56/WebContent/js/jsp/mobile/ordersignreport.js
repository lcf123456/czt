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
		title:'签收统计', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //单选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		nowrap:true,
		//货主默认为长沙11430101
		url:baseURL+"/mobile/ordersign/getOrdersignReportList.json", //数据来源
		sortName: 'routecode', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'orderno', //主键字段
//		pageNumber: 1, 
//		pageSize : 15,
//		pageList: [15,30,50],
		queryParams:{
			}, //查询条件
//		pagination:true, //显示分页
		rownumbers:true, //显示行号
		columns:[[
			{field:'routecode',title:'车组',width:$(this).width()*0.1},
			{field:'deleverycount',title:'签收数量',width:$(this).width()*0.1},
			{field:'deliverytime',title:'总时长(小时)',width:$(this).width()*0.1},
			{field:'avgcount',title:'均值(户/小时)',width:$(this).width()*0.1}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none'); 
			
		}
	});
});


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
	searchData();
}

