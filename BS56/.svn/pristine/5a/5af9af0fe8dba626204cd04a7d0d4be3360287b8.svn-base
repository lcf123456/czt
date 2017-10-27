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
	var begdate=getDateYM01();
	var enddate=getDateYMD();
	$('#begdate').datebox('setValue', begdate);
	$('#enddate').datebox('setValue', enddate);
	$('#dataTable').datagrid({
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //多选,当true时只允许当前选择一行。
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同

		collapsible:true,//可折叠
		url:baseURL+"/produce/equipmentfailure/getEquipmentFailureInfoPageList.json",  //数据来源
		//sortName: 'id', //排序的列
		//sortOrder: 'desc', //正序asc、倒序 desc
		remoteSort: false, //服务器端排序
		//idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 10,
		pageList: [10,30,50],
		queryParams:{
			begdate:begdate,
	        enddate:enddate
			}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			//{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'equipmentcode',title:'设备编号',width:10,
				formatter:function(value,row,index){return row.equipmentcode;} //需要formatter一下才能显示正确的数据
			},
			{field:'equipmentname',title:'设备名称',width:10,
				formatter:function(value,row,index){return row.equipmentname;} //需要formatter一下才能显示正确的数据
			},
			{field:'failuredate',title:'故障日期',width:10,sortable:false,
				formatter:function(value,row,index){return row.failuredate.substring(0,10);} 
			},
			{field:'failurepart',title:'故障部位',width:10,
				formatter:function(value,row,index){return row.failurepart;} //需要formatter一下才能显示正确的数据
			},
			{field:'failurereason',title:'故障原因',width:10,sortable:false,
				formatter:function(value,row,index){return row.failurereason;} 
			},
			{field:'solutiondesc',title:'解决办法',width:10,
				formatter:function(value,row,index){return row.solutiondesc;} //需要formatter一下才能显示正确的数据
			},
			{field:'repairdate',title:'维修日期',width:10,sortable:false,
				formatter:function(value,row,index){return row.repairdate.substring(0,10);} 
			},
			{field:'createname',title:'记录人',width:10,
				formatter:function(value,row,index){return row.createname;} //需要formatter一下才能显示正确的数据
			},
			{field:'createtime',title:'记录日期',width:10,sortable:false,
				formatter:function(value,row,index){return row.createtime.substring(0,19);} 
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			//$('#tabdiv .panel-header').css('display','none');
		}
	});
});
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
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		var begdate=getDateYM01();
		var enddate=getDateYMD();
		$('#begdate').datebox('setValue', begdate);
		$('#enddate').datebox('setValue', enddate);
		searchData();
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