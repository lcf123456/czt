var url;
var grantRow;	

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
 * 页面列表datagrid初始化
 */
jQuery(function($){
	$('#dataTable').datagrid({
		title:'车组信息', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:520, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/sys/route/getRoutesList.json", //数据来源
		sortName: 'routecode', //排序的列
		sortOrder: 'asc', //倒序 desc
		remoteSort: true, //服务器端排序
		idField:'routeid', //主键字段
		pageNumber: 1, 
		pageSize : 100,
		pageList: [10,30,50,100],
		queryParams:{
			}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号  
		columns:[[
			{field:'routeid',checkbox:true,width:2}, //显示复选框
			{field:'routecode',title:'车组CODE',width:20,sortable:true,
				formatter:function(value,row,index){return row.routecode;} //需要formatter一下才能显示正确的数据
			},
			{field:'routename',title:'车组名称',width:20,sortable:false,
				formatter:function(value,row,index){return row.routename;}
			},
			{field:'routetypename',title:'车组类型',width:30,sortable:true,
				formatter:function(value,row,index){return row.routetypename;}
			},
			{field:'areatypename',title:'车组网络',width:30,sortable:true,
				formatter:function(value,row,index){return row.areatypename;}
			},
			{field:'gisareaname',title:'GIS区域',width:30,sortable:true,
				formatter:function(value,row,index){return row.gisareaname;}
			},
			{field:'deptname',title:'所属部门',width:30,sortable:true,
				formatter:function(value,row,index){return row.deptname;}
			},
			{field:'vehicleno',title:'配送车辆',width:30,sortable:true,
				formatter:function(value,row,index){return row.vehicleno;}
			},
			{field:'drivername',title:'司机',width:30,
				formatter:function(value,row,index){return row.drivername;}
			},
			{field:'cashiername',title:'收款员',width:30,
				formatter:function(value,row,index){return row.cashiername;}
			},
			{field:'calcname',title:'货款核算员',width:30,
				formatter:function(value,row,index){return row.calcname;}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none');
		}
	});
});

/**
 * 打开新增车组信息窗口
 */
function newAddRoute(){
	
    //部门下拉列表
    $("#deptid").combobox({
    	url : baseURL+"/comm/combobox/getDeptCombobox.json",//返回json数据的url
    	valueField : "id",//这个id和你返回json里面的id对应
    	textField : "deptname", //这个text和你返回json里面的text对应
    	//panelHeight: 'auto'
    })
    //配送车辆下拉列表
    $("#vehicleid").combobox({
    	url : baseURL+"/comm/combobox/getVechilesCombobox.json",//返回json数据的url
    	valueField : "id",//这个id和你返回json里面的id对应
    	textField : "vehicleno", //这个text和你返回json里面的text对应
    	//panelHeight: 'auto'
    })
    //暂存仓库下拉列表
    $("#warehouse").combobox({
    	url : baseURL+"/comm/combobox/getComboboxByTypeCode.json?typeCode=WAREHOUSE",//返回json数据的url
    	valueField : "id",//这个id和你返回json里面的id对应
    	textField : "contentlist", //这个text和你返回json里面的text对应
    	//panelHeight: 'auto'
    })
    //司机下拉列表
    $("#driverid").combobox({
    	url : baseURL+"/comm/combobox/getUserComboboxByRoleId.json?roleid=34",//返回json数据的url
    	valueField : "id",//这个id和你返回json里面的id对应
    	textField : "username", //这个text和你返回json里面的text对应
    	//panelHeight: 'auto'
    })
    //收款员下拉列表
    $("#cashierid").combobox({
    	url : baseURL+"/comm/combobox/getUserComboboxByRoleId.json?roleid=34",//返回json数据的url
    	valueField : "id",//这个id和你返回json里面的id对应
    	textField : "username", //这个text和你返回json里面的text对应
    	//panelHeight: 'auto'
    })
    //核算员下拉列表
    $("#calcid").combobox({
    	url : baseURL+"/comm/combobox/getUserComboboxByRoleId.json?roleid=30",//返回json数据的url
    	valueField : "id",//这个id和你返回json里面的id对应
    	textField : "username", //这个text和你返回json里面的text对应
    	//panelHeight: 'auto'
    })
    //核算员下拉列表
    $("#gisarea").combobox({
    	url : baseURL+"/comm/combobox/getRegionsCombobox.json",//返回json数据的url
    	valueField : "id",//这个id和你返回json里面的id对应
    	textField : "name", //这个text和你返回json里面的text对应
    	//panelHeight: 'auto'
    })
	$('#add-dlg').dialog('open').dialog('setTitle','增加车组信息');
	
}

/**
 * 保存新建车组信息
 */
function saveAdd(){
	$('#add-fm').form('submit',{
		onSubmit: function(){
			//var isValidate = $(this).form('validate');
			//if(isValidate){
				//$('#loading').window('open');
			//}
			//return isValidate;
		},
		//sys/user/user/
		url:baseURL+"/sys/route/doRouteNew.json",
		//data:$("#add-fm").serializeArray(),
		beforeSend : function () {
			$.messager.progress({
				text : '正在新增中...',
			});
		},
		success: function(data){
			//$('#loading').window('close');
			data = eval('('+data+')');
			$('#add-dlg').dialog('close');
			$('#add-fm').form('clear');
			$('#dataTable').datagrid('reload'); 
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个车组信息新增'+data.msg+'！',
			});
			//$('#loading').window('close');
		}
	});
}


/**
 * 打开修改窗口
 */
  function openEdit(){
		var rows = $('#dataTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要修改的车组信息",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一个车组进行修改操作",'info');
			return;
		}
		var row = $('#dataTable').datagrid('getSelected');
		if (row){
			//部门下拉列表
		    $("#deptid1").combobox({
		    	url : baseURL+"/comm/combobox/getDeptCombobox.json",//返回json数据的url
		    	valueField : "id",//这个id和你返回json里面的id对应
		    	textField : "deptname", //这个text和你返回json里面的text对应
		    	//panelHeight: 'auto'
		    })
		    //配送车辆下拉列表
		    $("#vehicleid1").combobox({
		    	url : baseURL+"/comm/combobox/getVechilesCombobox.json",//返回json数据的url
		    	valueField : "id",//这个id和你返回json里面的id对应
		    	textField : "vehicleno", //这个text和你返回json里面的text对应
		    	//panelHeight: 'auto'
		    })
		    //暂存仓库下拉列表
		    $("#warehouse1").combobox({
		    	url : baseURL+"/comm/combobox/getComboboxByTypeCode.json?typeCode=WAREHOUSE",//返回json数据的url
		    	valueField : "id",//这个id和你返回json里面的id对应
		    	textField : "contentlist", //这个text和你返回json里面的text对应
		    	//panelHeight: 'auto'
		    })
		    //司机下拉列表
		    $("#driverid1").combobox({
		    	url : baseURL+"/comm/combobox/getUserComboboxByRoleId.json?roleid=34",//返回json数据的url
		    	valueField : "id",//这个id和你返回json里面的id对应
		    	textField : "username", //这个text和你返回json里面的text对应
		    	//panelHeight: 'auto'
		    })
		    //收款员下拉列表
		    $("#cashierid1").combobox({
		    	url : baseURL+"/comm/combobox/getUserComboboxByRoleId.json?roleid=34",//返回json数据的url
		    	valueField : "id",//这个id和你返回json里面的id对应
		    	textField : "username", //这个text和你返回json里面的text对应
		    	//panelHeight: 'auto'
		    })
		    //核算员下拉列表
		    $("#calcid1").combobox({
		    	url : baseURL+"/comm/combobox/getUserComboboxByRoleId.json?roleid=30",//返回json数据的url
		    	valueField : "id",//这个id和你返回json里面的id对应
		    	textField : "username", //这个text和你返回json里面的text对应
		    	//panelHeight: 'auto'
		    })
		    //核算员下拉列表
		    $("#gisarea1").combobox({
		    	url : baseURL+"/comm/combobox/getRegionsCombobox.json",//返回json数据的url
		    	valueField : "id",//这个id和你返回json里面的id对应
		    	textField : "name", //这个text和你返回json里面的text对应
		    	//panelHeight: 'auto'
		    })
			$('#edit-dlg').dialog('open').dialog('setTitle','修改车组信息');
			$('#edit-fm').form('load',row);
		}
  }
		
  /**
   * 保存修改的用戶信息
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
  		url:baseURL+"/sys/route/doUpdateRoute.json",
  		success: function(data){
			//$('#loading').window('close');
			data = eval('('+data+')');
			$('#edit-dlg').dialog('close');
			$('#edit-fm').form('clear');
			$('#dataTable').datagrid('reload'); 
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个车组信息修改'+data.msg+'！',
			});
			//$('#loading').window('close');
		}
  	});
  }
	
	  //查询
	function searchRoutes(){
		var params = $('#dataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		//alert(params.length);
		//alert(fields.length);
		$.each( fields, function(i, field){
			//alert(field.name);
			//alert(field.value);
			params[field.name] = field.value; //设置查询参数
		}); 
		//$('#dataTable').datagrid('options').queryParams=params;
		$('#dataTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
	//清空查询条件
	function clearForm(){
		//$('#queryForm').form('clear');
		$('#routecode').textbox("setValue","");
		$('#routecode').textbox("setText","");
		//alert($('#routecode').textbox("getText"));
		searchRoutes();
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