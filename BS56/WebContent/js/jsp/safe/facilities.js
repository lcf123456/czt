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
	
	var ctypeval=$("#ctype").val();
	$("#ctype").combobox({
        onChange: function (n,o) {
        	searchFacilities();
		}
	})
	$('#dataTable').datagrid({
		title:'设施台账', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		url:baseURL+"/safe/firefacilities/getFireFacilities.json",  //数据来源
        collapsible:true,
         //data:data,
		queryParams:{
			ctype:ctypeval,
			}, //查询条件
		pagination:true, //显示分页
		rownumbers:true, //显示行号
		columns:[[
			//{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'serial',title:'序号',width:10,sortable:false,
				formatter:function(value,row,index){return row.serial;} //需要formatter一下才能显示正确的数据
			},
			{field:'device',title:'设施名称',width:15,sortable:false,
				formatter:function(value,row,index){return row.device;}
			},
			{field:'devicecode',title:'设施编号',width:15,sortable:false,
				formatter:function(value,row,index){return row.devicecode;} 
			},
			{field:'addr',title:'使用地点',width:15,sortable:false,
				formatter:function(value,row,index){return row.addr;} 
			},
			{field:'model',title:'设施型号',width:15,sortable:false,
				formatter:function(value,row,index){return row.model;} 
			},
			{field:'deptname',title:'归属部门',width:15,sortable:false,
				formatter:function(value,row,index){return row.deptname;} 
			},
		
			{field:'ctype',title:'使用状态',width:20,
				formatter:function(value,row,index)
					{if( row.ctype == '10'){
						return '完好';}
					 else if ( row.ctype == '20'){
					return '一般';}
					else { return '差';}
				    }
			},
			{field:'important',title:'重要程度',width:15,
				formatter:function(value,row,index)
					{if( row.important == '10'){
						return '重要';
					}else{
					return '不重要';
					}
					}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none');
		}
	});
});
	  //查询
	function searchFacilities(){
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
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('reset');
		searchFacilities();
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