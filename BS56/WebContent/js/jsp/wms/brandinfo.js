/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	$('#dataTable').datagrid({
		//title:'品牌信息维护', //标题
		method:'post',
		//iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/wms/item/getBrandinfoList.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: false, //服务器端排序
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
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'bigbox_bar',title:'件码',width:20,
				formatter:function(value,row,index){return row.bigboxBar;} //需要formatter一下才能显示正确的数据
			},
			{field:'itemno',title:'商品编号',width:20,
				formatter:function(value,row,index){return row.itemno;} //需要formatter一下才能显示正确的数据
			},
			
			{field:'itemname',title:'商品名称',width:30,
				formatter:function(value,row,index){return row.itemname;} //需要formatter一下才能显示正确的数据
			},
			{field:'shortname',title:'商品简称',width:25,
				formatter:function(value,row,index){return row.shortname;} //需要formatter一下才能显示正确的数据
			},
			{field:'spec',title:'规格',width:20,
				formatter:function(value,row,index){return row.spec;} //需要formatter一下才能显示正确的数据
			},
			{field:'fullcount',title:'满盘数量',width:20,
				formatter:function(value,row,index){return row.fullcount;}
			},
			{field:'cdtype',title:'拆垛类型',width:10,
				formatter:function(value,row,index){
					if( row.cdtype == '10'){
					return '自动拆垛';
				}
				else if( row.cdtype == '0'){
					return '人工拆垛';
				}
			 }
			},
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv.panel-header').css('display','none'); 
			
		}
	});
	
});

  /**
   * 打开修改窗口
   */
    function openEdit(){
  		var rows = $('#dataTable').datagrid('getSelections');
  		if(rows.length==0){
  			$.messager.alert('提示',"请选择你要更新的品牌信息",'info');
  			return;
  		}
  		if(rows.length > 1){
  			$.messager.alert('提示',"只能选择一条品牌进行更新",'info');
  			return;
  		}
  		var row = $('#dataTable').datagrid('getSelected');
  		if (row){
  			$('#edit-dlg').dialog('open').dialog('setTitle','修改品牌信息');
  			$('#edit-fm').form('load',row);
  			//url = '/BS56/sys/roleNew.json';
  		}

  	}
    
    /**
     * 保存修改的参数信息

     */
    function saveEdit(){
    	$('#edit-fm').form('submit',{
    		onSubmit: function(){
    			var isValidate = $(this).form('validate');
    			if(isValidate){
    				//$('#loading').window('open');
    			}
    			return isValidate;
    		},
    		url:baseURL+"/wms/item/doEditBrandinfo.json",
  		data:$("#edit-fm").serializeArray(),
  		beforeSend : function () {
  			$.messager.progress({
  				text : '正在修改中...',
  			});	
  		},
    		success: function(data){
  			//$('#loading').window('close');
  			data = eval('('+data+')');
  			$('#edit-dlg').dialog('close');
  			$('#dataTable').datagrid('reload'); 
      		$.messager.show({
  				title : '提示',
  				msg :  data.total+'个品牌信息修改'+data.msg+'！',
  			});
  			//$('#loading').window('close');
  		}
    	});
    }
    
  
//查询
function searchbrandinfo(){
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
	searchbrandinfo();
}

