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
	//alert(baseURL+"/sys/dept/getDeptinfoList.json?keywd="+$('#keywd').val());

	$('#keywd').textbox('textbox').keydown(function(e){
			if(e.keyCode==13){
				searchPara();
			}
		})
	$('#dataTabel').datagrid({
		title:'部门参数维护', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: false, //奇偶行颜色不同
		collapsible:true,//可折叠		url:baseURL+"/sys/dept/getDeptinfoList.json?keywd="+$('#keywd').val(), //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'asc', //倒序 desc
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 10,
		pageList: [10,30,50],
		queryParams:{
			}, //查询条件
		pagination:false, //不显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		//通地deptlevel给文字设置不周颜色
	//rowStyler:function(index,row){
	//	if (row.deptlevel=='3'){
	//			return 'color:blue ;';}
	//	if (row.deptlevel=='2'){
	//		return 'color:NAVY;';}
	//	if (row.deptlevel=='1'){
	//		return 'color:black;';}
	//		},
					
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'deptcode',title:'部门CODE',width:20,
			formatter:function(value,row,index){return row.deptcode;} //需要formatter一下才能显示正确的数据
			},
			{field:'deptname',title:'部 门 名 称',width:20,
				formatter:function(value,row,index){
					if(row.deptlevel=='1')return row.deptname;
					if(row.deptlevel=='2')return '&nbsp;&nbsp&nbsp;&nbsp;'+row.deptname;
					if(row.deptlevel=='3')return '&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp&nbsp;&nbsp;'+row.deptname;
					}	
			},
			{field:'deptlevel',title:'部 门 级 次',width:30,
				formatter:function(value,row,index) 
				//{return row.deptlevel+'级'}
				{if(row.deptlevel=='1')return '一级';
			     if(row.deptlevel=='2')return '二级';
			     if(row.deptlevel=='3')return '三级';}
			},
						
			{field:'depttype',title:'部 门 类 型',width:30,
				formatter:function(value,row,index){
					//return row.depttype;  
					if (row.depttype==10) return "办公";
					if (row.depttype==8) return "生产";
				}
			},
			{field:'seq',title:'排序',width:30,
				formatter:function(value,row,index){return row.seq;}
			}
		]],
		
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题			$('#tabdiv .panel-header').css('display','none');
		}
	});
});

/**
 * 打开新增系统参数窗口
 */
function newaddDept(){
	$('#add-fm').form('clear');
	var rows = $('#dataTabel').datagrid('getSelections');
	//没有选中
	if(rows.length==0){
		//$('#deptlevel').val((parseInt(row.deptlevel)+1));
		$('#deptlevel').val(1);
		$('#fid').val(0);
	}else{
		//选中多个
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一个上级部门",'info');
			return;
		}else{
			//选中一个
			var row = $('#dataTabel').datagrid('getSelected');
			//选中三级胡判断
			if(row.deptlevel=='3'){
				$.messager.alert('提示',"第三级部门已是最小部门单位，无法新增子部门，请重新选择！",'info');
				return;
			}else{
				//正常赋值
				//$('#fid').textbox("setValue",row.id);
				//$('#lvl').textbox("setValue",(parseInt(row.lvl)+10));
				$('#fid').val(row.id);
				$('#deptlevel').val((parseInt(row.deptlevel)+1));
				$('#seq').val(row.seq);
				//alert($('#fid').val());
				//alert($('#lvl').val());
			}
		}
	}
	$('#add-dlg').dialog('open').dialog('setTitle','增加部门信息');
	
	
}

/**
 * 保存新建系统参数信息
 */
function saveAdd(){
	$('#add-fm').form('submit',{
		onSubmit: function(){
			var isValidate = $(this).form('validate');
			if(isValidate){
				//$('#loading').window('open');
			}
			return isValidate;
		},
		url:baseURL+"/sys/dept/doinsertDeptVo.json",
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
			$('#dataTabel').datagrid('reload'); 
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个部门信息新增'+data.msg+'！',
			});
			//$('#loading').window('close');
		}
	});
}


/**
 * 打开修改窗口
 */
  function openEdit(){
		var rows = $('#dataTabel').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要更新的部门名称",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一条部门信息进行更新",'info');
			return;
		}
		var row = $('#dataTabel').datagrid('getSelected');
		if (row){
			$('#edit-dlg').dialog('open').dialog('setTitle','修改部门名称信息');
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
			url:baseURL+"/sys/dept/doupdateDeptVo.json",
			data:$("#edit-fm").serializeArray(),
			beforeSend : function () {
				$.messager.progress({
					text : '正在新增中...',
				});
			},
			success: function(data){
				//$('#loading').window('close');
				data = eval('('+data+')');
				$('#edit-dlg').dialog('close');
				$('#dataTabel').datagrid('reload'); 
	    		$.messager.show({
					title : '提示',
					msg :  data.total+'个部门名称修改'+data.msg+'！',
				});
				//$('#loading').window('close');
			}
		});
	}

  
//删除
	function deleterow(){
		var rows = $('#dataTabel').datagrid('getSelections');
		if(rows.length==0){
		$.messager.alert('提示',"请选择你要删除的部门信息",'info');
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
        	$.post(baseURL+'/sys/dept/dodelDeptVo.json'+ps,function(data){
	        	$('#dataTabel').datagrid('reload'); 
	        	//console.log("del--"+data);
        		$.messager.show({
					title : '提示',
					msg :  data.total+'个系统参数信息被删除'+data.msg+'！',
				});
        	});
        }
    });
	}
	
	  //查询
	function searchPara(){

		$('#dataTabel').datagrid({
			title:'部门参数维护', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选

			height:420, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。

			striped: true, //奇偶行颜色不同

			collapsible:true,//可折叠
			url:baseURL+"/sys/dept/getDeptinfoList.json?keywd="+encodeURI($('#keywd').val()), //数据来源
			sortName: 'id', //排序的列
			sortOrder: 'asc', //倒序 desc
			remoteSort: true, //服务器端排序
			idField:'id', //主键字段
			pageNumber: 1, 
			pageSize : 10,
			pageList: [10,30,50],
			queryParams:{
				}, //查询条件
			pagination:false, //显示分页
			//pageSize : 1,
			rownumbers:true, //显示行号
				   	
						
			columns:[[
				{field:'id',checkbox:true,width:2}, //显示复选框
				{field:'deptcode',title:'部门CODE',width:20,sortable:true,
					formatter:function(value,row,index){return row.deptcode;} //需要formatter一下才能显示正确的数据
				},
				{field:'deptname',title:'部 门 名 称',width:20,sortable:true,
					formatter:function(value,row,index){
						if(row.deptlevel=='1')return row.deptname;
						if(row.deptlevel=='2')return '&nbsp;&nbsp&nbsp;&nbsp;'+row.deptname;
						if(row.deptlevel=='3')return '&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp&nbsp;&nbsp;'+row.deptname;
						}	
				},
				{field:'deptlevel',title:'部 门 级 次',width:30,
					formatter:function(value,row,index){return row.deptlevel+'级'}
					},
							
				{field:'depttype',title:'部 门 类 型',width:30,
					formatter:function(value,row,index){
						//return row.depttype;  
						if (row.depttype==10) return "办公";
						if (row.depttype==8) return "生产";
					}
				},
					{field:'seq',title:'排序',width:30,
						formatter:function(value,row,index){return row.seq;}
					}	
			]],
			
			toolbar:'#toolbar',
			onLoadSuccess:function(){
				$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			
			}
		});

	}
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		searchPara();
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