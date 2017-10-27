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
 * 页面列表datagrid初始化

 */
jQuery(function($){
    	$('#dataTable').datagrid({
		title:'供应商信息维护', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //多选,当true时只允许当前选择一行。
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠		url:baseURL+"/cost/supplierinfo/getSupplierInfoVoPageList.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'desc', //正序asc、倒序 desc
		remoteSort: false, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 30,
		pageList: [10,30,50],
		queryParams:{
			}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
//		rowStyler:function(index,row){
//			if (row.delstatuscontent=='报废'){
//					return 'color:red ;';}
//				},
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'supplier',title:'供应商名称',width:15,
				formatter:function(value,row,index){return row.supplier;} //需要formatter一下才能显示正确的数据
			},
			{field:'addr',title:'地址',width:15,
				formatter:function(value,row,index){return row.addr;}
			},
			{field:'contacts',title:'联系人',width:15,
				formatter:function(value,row,index){return row.contacts;}
			},
			{field:'phone',title:'联系电话',width:15,
				formatter:function(value,row,index){return row.phone;}
			},
			{field:'range',title:'供货范围',width:15,
				formatter:function(value,row,index){return row.range;}
			},
			{field:'ctype',title:'类型',width:15,
				formatter:function(value,row,index){
					{if(row.ctype=='10')return '生产商';
				     if(row.ctype=='20')return '流通商';
				     if(row.ctype=='30')return '代理商';
				     if(row.ctype=='40')return '其它';}
					}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题			$('#tabdiv .panel-header').css('display','none');
		}
	});
});

/**
 * 打开供应商信息新增窗口
 */
function newadd(){
	$('#add-dlg').dialog('open').dialog('setTitle','供应商信息');
	$('#add-fm').form('reset');
}

/**
 * 保存新建供应商信息
 */
function saveAdd(){
	//var bdate=$('#buydate_string').val();
	//alert("---");
	$('#add-fm').form('submit',{
		onSubmit: function(){
			var isValidate = $(this).form('validate');
			if(isValidate){
				//$('#loading').window('open');
			}
			return isValidate;
		},
		//url:baseURL+"/sys/vehicle/doinsertVehicleVo.json?bdate="+bdate,
		url:baseURL+"/cost/supplierinfo/doinsertSupplierInfoVo.json",
		data:$("#add-fm").serializeArray(),
		beforeSend : function () {
			$.messager.progress({
				text : '正在新增中...',
			});
		},
		success: function(data){
			//$('#loading').window('close');
			data = eval('('+data+')');
			$('#add-dlg').dialog('close');
			$('#dataTable').datagrid('reload'); 
    		$.messager.show({
				title : '提示',
				msg :  data.total+'条供应商信息已新增'+data.msg+'！',
				
			});
    		$('#add-fm').form('clear');
    		$('#dataTable').datagrid('reload');
    		//$('#loading').window('close');
		}	
	});
}

/**
 * 打开修改窗口
 */
  function openEdit(){
	    $('#edit-fm').form('clear');
		var rows = $('#dataTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要更新的供应商信息",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一条供应商信息进行更新",'info');
			return;
		}
		var row = $('#dataTable').datagrid('getSelected');
		if (row){
			$('#edit-dlg').dialog('open').dialog('setTitle','供应商信息');
			$('#edit-fm').form('load',row);
			$('#saveEditBtn').linkbutton('enable');
			$("#supplier1").textbox('textbox').attr('readonly',false);
			$("#addr1").textbox('textbox').attr('readonly',false);
			$("#contacts1").textbox('textbox').attr('readonly',false);
			$("#phone1").textbox('textbox').attr('readonly',false);
			$("#range1").textbox('textbox').attr('readonly',false);
			$("#remarks1").textbox('textbox').attr('readonly',false);
			//url = '/BS56/sys/roleNew.json';
		}

	}
  
  /**
   * 保存修改的供应商信息   */
  function saveEdit(){
		$('#edit-fm').form('submit',{
			onSubmit: function(){
				var isValidate = $(this).form('validate');
				if(isValidate){
					//$('#loading').window('open');
				}
				return isValidate;
			},
			url:baseURL+"/cost/supplierinfo/doupdateSupplierInfoVo.json",
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
					msg :  data.total+'条供应商信息已修改'+data.msg+'！',
				});
				$('#loading').window('close');
			}
		});
	}
  
//删除供应商信息
	function deleterow(){
		var rows = $('#dataTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要删除的供应商信息",'info');
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
        	$.post(baseURL+'/cost/supplierinfo/dodelSupplierInfoVo.json'+ps,function(data){
	        	$('#dataTable').datagrid('reload'); 
	        	//console.log("del--"+data);
        		$.messager.show({
					title : '提示',
					msg :  data.total+'条供应商信息已删除'+data.msg+'！',
				});
        	});
        }
    });
	}
	
	//打开查看窗口
	 function openView(){
		    $('#edit-fm').form('clear');
			var rows = $('#dataTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要查看的供应商信息",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条供应商信息进行查看",'info');
				return;
			}
			var row = $('#dataTable').datagrid('getSelected');
			if (row){
				$('#edit-dlg').dialog('open').dialog('setTitle','查看');
				$('#edit-fm').form('load',row);
				$("#supplier1").textbox('textbox').attr('readonly',true);
				$("#addr1").textbox('textbox').attr('readonly',true);
				$("#contacts1").textbox('textbox').attr('readonly',true);
				$("#phone1").textbox('textbox').attr('readonly',true);
				$("#range1").textbox('textbox').attr('readonly',true);
				$("#remarks1").textbox('textbox').attr('readonly',true);
				$('#saveEditBtn').linkbutton('disable');
				//url = '/BS56/sys/roleNew.json';
				//$('#saveEditBtn').linkbutton('enable'); 
				//$("input").prop("disabled",false);
			}

		}
	  
	  
	//退出按钮，清除文本西框只读状态、清除缓存
	  function cancel(){
		  $('#edit-fm').form('clear');
		  $("input").prop("disabled",false);
	      $("select").prop('disabled',false);
		  $('#saveEditBtn').linkbutton('enable');
		  $('#edit-dlg').dialog('close');
	}
	  
	  //查询
	function searchSupplierInfo(){
		var params = $('#dataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数

		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		//alert(params.length);
		//alert(fields.length);
		$.each( fields, function(i, field){
			//alert(field.value);
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#dataTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了

	}
		
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		$('#ctype').textbox("setValue","");
		$('#ctype').textbox("setText","请选择...");
		searchSupplierInfo();
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