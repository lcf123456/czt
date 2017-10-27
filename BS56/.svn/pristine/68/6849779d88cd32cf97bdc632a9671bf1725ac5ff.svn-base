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
		title:'自动语音考核项', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:520, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/sq/evalitem/getEvalItemPageList.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'asc', //倒序 desc
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 100,
		pageList: [50,100],
		queryParams:{
			}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号  
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'contentshort',title:'考核项简写',width:40,sortable:false,
				formatter:function(value,row,index){return row.contentshort;} //需要formatter一下才能显示正确的数据
			},
			{field:'content',title:'考核项内容',width:40,sortable:false,
				formatter:function(value,row,index){return row.content;} //需要formatter一下才能显示正确的数据
			},
			{field:'usetypename',title:'考核对象',width:20,sortable:false,
				formatter:function(value,row,index){return row.usetypename;}
			},
			{field:'assessweight',title:'考核权重',width:20,sortable:false,
				formatter:function(value,row,index){return row.assessweight;}
			},
			{field:'voxfile',title:'语音文件名',width:20,sortable:false,
				formatter:function(value,row,index){return row.voxfile;}
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
 * 打开新增考核项信息窗口
 */
function openNew(){
	$('#add-dlg').dialog('open').dialog('setTitle','增加自动语音考核项信息');
	$('#add-fm').form('clear');
	$('#add-fm').form('reset');
}

/**
 * 保存新建考核项信息
 */
function saveAdd(){
	$("#flag").val("20");
	$("#voteflag").val("10");
	$("#assesstype").val("4");
	$("#itemnum").val("2");
	$('#add-fm').form('submit',{
		onSubmit: function(){
			var isValidate = $(this).form('validate');
			if(isValidate){
				var filename=$('#voxfile').val();
				if(filename.length>4){
					var str=filename.substring(filename.length-4);
					if(str!='.vox'&&str!='.VOX'&&str!='.Vox'){
						alert("语音文件名需要以.vox为后缀！");
						return false;
					}
				}else{
					if(filename.length==0){
						alert("语音文件名不能为空！");
						return false;
					}else{
						alert("语音文件名需要以.vox为后缀！");
						return false;
					}
				}
			}
			return isValidate;
		},
		//sys/user/user/
		url:baseURL+"/sq/evalitem/doInsertEvalItem.json",
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
				msg :  data.total+'个自动语音考核项信息新增'+data.msg+'！',
			});
			//$('#loading').window('close');
		}
	});
}

function delRow(){
	var rows = $('#dataTable').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择你要删除的自动语音考核项信息",'info');
		return;
	}
	$.messager.confirm('提示','确定要删除吗?',function(result){
    if (result){
    	var ps = "";
    	$.each(rows,function(i,n){
    		if(i==0) 
    			ps += "?id="+n.id;
    		else
    			ps += "&id="+n.id;
    	});
    	
    	$.post(baseURL+'/sq/evalitem/doDelEvalItem.json'+ps,function(data){
        	$('#dataTable').datagrid('reload'); 
    		$.messager.show({
				title : '提示',
				msg :  data.total+'条自动语音考核项信息被删除'+data.msg+'！',
			});
    	});
    }
});
}

/**
 * 打开修改窗口
 */
  function openEdit(){
		var rows = $('#dataTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要修改的自动语音考核项信息",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一个自动语音考核项进行修改操作",'info');
			return;
		}
		var row = $('#dataTable').datagrid('getSelected');
		if (row){
			$('#edit-dlg').dialog('open').dialog('setTitle','修改自动语音考核项信息');
			$('#edit-fm').form('load',row);
		}
  }
		
  /**
   * 保存修改的考核项信息
   */
  function saveEdit(){
  	$('#edit-fm').form('submit',{
  		onSubmit: function(){
  			var isValidate = $(this).form('validate');
			if(isValidate){
				var filename=$('#voxfile1').val();
				if(filename.length>4){
					var str=filename.substring(filename.length-4);
					if(str!='.vox'&&str!='.VOX'&&str!='.Vox'){
						alert("语音文件名需要以.vox为后缀！");
						return false;
					}
				}else{
					if(filename.length==0){
						alert("语音文件名不能为空！");
						return false;
					}else{
						alert("语音文件名需要以.vox为后缀！");
						return false;
					}
				}
			}
			return isValidate;
  		},
  		url:baseURL+"/sq/evalitem/doUpdateEvalItem.json",
  		success: function(data){
			//$('#loading').window('close');
			data = eval('('+data+')');
			$('#edit-dlg').dialog('close');
			$('#edit-fm').form('clear');
			$('#dataTable').datagrid('reload'); 
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个自动语音考核项信息修改'+data.msg+'！',
			});
			//$('#loading').window('close');
		}
  	});
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