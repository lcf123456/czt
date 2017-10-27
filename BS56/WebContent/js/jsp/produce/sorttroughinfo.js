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
	var troughtype=$("#troughtype").combobox("getValue");
	var cigarettetype=$("#cigarettetype").combobox("getValue");
	//下拉框选中自动搜索
	$("#troughtype").combobox({
        onChange: function (n,o) {
        	searchSorttrough();
		}
	})
	//下拉框选中自动搜索
		$("#cigarettetype").combobox({
        onChange: function (n,o) {
        	searchSorttrough();
		}
	})

	$('#dataTable').datagrid({
		title:'通道信息', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		url:baseURL+"/produce/sorttrough/getSorttroughList.json",  //数据来源
        collapsible:true,
         //data:data,
		queryParams:{
			troughtype:troughtype,
	        cigarettetype:cigarettetype,
			}, //查询条件
		pagination:false, //显示分页
		rownumbers:true, //显示行号
		columns:[[
			//{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'troughnum',title:'烟道编号',width:15,sortable:false,
				formatter:function(value,row,index){return row.troughnum;} //需要formatter一下才能显示正确的数据
			},
			{field:'cigarettecode',title:'品牌代码',width:15,sortable:false,
				formatter:function(value,row,index){return row.cigarettecode;}
			},
			{field:'cigarettename',title:'品牌名称',width:15,sortable:false,
				formatter:function(value,row,index){return row.cigarettename;} 
			},
			{field:'cigarettetype',title:'品牌类型',width:15,
				formatter:function(value,row,index)
                    {if(row.cigarettetype=='20')return '标准';
				     if(row.cigarettetype=='30')return '异型';
				     if(row.cigarettetype=='40')return '异形混和';}
			},
			{field:'troughtype',title:'通道类型',width:20,
				formatter:function(value,row,index)
					{if(row.troughtype=='10')return '分拣通道';
				     if(row.troughtype=='20')return '重力式货架';
				     if(row.troughtype=='30')return '皮带机';
				     if(row.troughtype=='40')return '分拣出口';}
			},
			{field:'state',title:'使用状态',width:15,
				formatter:function(value,row,index)
					{if(row.state=='0')return '<span style="color:red;">停用</span>';
				     if(row.state=='10')return '正常';}
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
	function searchSorttrough(){
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
		searchSorttrough();
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