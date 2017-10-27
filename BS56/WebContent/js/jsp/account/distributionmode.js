/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	$('#dataTable').datagrid({
		//title:'区域表', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //多选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/account/distributionmode/getDistributionModes.json", //数据来源
		queryParams:{
		}, //查询条件
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'id2',title:'编号id',width:10,
				formatter:function(value,row,index){return row.id;} //需要formatter一下才能显示正确的数据
			},
			{field:'cName',title:'访配模式',width:30,
				formatter:function(value,row,index){return row.cName;}
			},
			{field:'status',title:'盘点状态',width:30,
				formatter:function(value,row,index){
					if( row.status == '10'){
						return '<span >启用</span>';
					}else{
					return '<span ><font color="red">禁用</font></span>';}}
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
 * 启用
 * @returns
 */
function changeMode(){
	var rows = $('#dataTable').datagrid('getSelections');
	if(rows.length==0){
	$.messager.alert('提示',"请选择要启用的访配模式！",'info');
		return;
	}
	var row = $('#dataTable').datagrid('getSelected');
        $.messager.confirm('确认', '是否确认开启所选访配模式？更改模式将影响货款核算数据的接收！', function(r){  
            if (r){  
                $.ajax({  
                    type: "post",  
                    //contentType: "application/json", //必须有  
                    url: baseURL+'/account/distributionmode/doChangeMode.json',
                    data:{
                    	id:row.id,
                    	status:10
                    },	
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
            				msg :  '访配模式更新'+data.msg+'！',
            			});
                    } 
                });  
            }  
        });  
}

