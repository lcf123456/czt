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
	//alert(baseURL+"/sq/starinfo/getStarinfo.json");
	$('#dataTable').datagrid({
		title:'车辆信息维护', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选		//onCheckSelect:true,
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。		striped: true, //奇偶行颜色不同		collapsible:true,//可折叠		url:baseURL+"/sys/vehicle/getVehicleVoList.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'desc', //正序asc、倒序 desc
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 10,
		pageList: [10,30,50],
		queryParams:{
			}, //查询条件
		pagination:false, //显示分页
		pageSize : 1,
		rownumbers:true, //显示行号
		rowStyler:function(index,row){
			if (row.delstatuscontent=='报废'){
					return 'color:red ;';}
				},
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'vehicleno',title:'车牌号码',width:15,
				formatter:function(value,row,index){return row.vehicleno;} //需要formatter一下才能显示正确的数据
			},
			{field:'vehicletype',title:'车型',width:15,
				formatter:function(value,row,index){return row.vehicletype;}
			},
			{field:'buydate',title:'购车日期',width:15,
				formatter:function(value,row,index){
					var bdate=row.buydate;
					if(bdate!=null&&bdate.length>10){
						bdate=bdate.substring(0,10);
					}
					
					return bdate;
				}
			},
			{field:'fueltypecn',title:'车辆燃油',width:15,
				formatter:function(value,row,index){return row.fueltypecn;}
			},
			{field:'vehiclescn',title:'车辆用途',width:15,
				formatter:function(value,row,index){return row.vehiclescn;}
			},
			{field:'register',title:'登记注册',width:15,
				formatter:function(value,row,index){return row.register;}
			},
			{field:'delstatuscontent',title:'车辆状态',width:10,
				formatter:function(value,row,index){return row.delstatuscontent;}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题			$('#tabdiv .panel-header').css('display','none');

		}
	});
});

/**
 * 打开车辆信息新增窗口
 */
function newadd(){
	$('#add-dlg').dialog('open').dialog('setTitle','车辆信息');
	$('#add-fm').form('reset');
	var data=getDateYMD();
	//alert(data);
	$("#buydate").datebox('setValue',data);
	
}

/**
 * 保存新建车辆信息
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
		url:baseURL+"/sys/vehicle/doinsertVehicleVo.json",
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
				msg :  data.total+'个车辆信息新增'+data.msg+'！',
			});
    		$('#add-fm').form('clear');
			//$('#loading').window('close');
		}	
	});
}

/**
 * 打开查看窗口
 */
  function openView(){
	 	//$(".formtd");
	 	$(".formtd").each(function(){
	 	   $(this).html("");
	 	 });
		var rows = $('#dataTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要查看的车辆信息",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一条车辆信息进行查看",'info');
			return;
		}
		viewRow = $('#dataTable').datagrid('getSelected');
		//alert(viewRow);
		if (viewRow!=null){
			$('#view-dlg').dialog('open').dialog('setTitle','车辆信息');
			$("#id").attr("value",viewRow.id);
			$("#v-vehiclescn").html(viewRow.vehiclescn);
			$("#v-username").html(viewRow.username);
			$("#v-vehicleno").html(viewRow.vehicleno);
			//var typec=viewRow.vehicletype;
			//if(typec==null)typec="";
			$("#v-vehicletype").html(viewRow.vehicletype);
			$("#v-regload").html(viewRow.regload);
			$("#v-register").html(viewRow.register);
		    //*因日期为空会导致后面数据不显示，需重新定义日期截取*
		    //$("#v-buydate").html(viewRow.buydate.substring(0,10));
			var bdate=viewRow.buydate;
		    if(bdate==null)bdate="";
		    if (bdate!=null&&bdate.length>10)bdate=bdate.substring(0,10)
			$("#v-buydate").html(bdate);
			$("#v-enginenum").html(viewRow.enginenum);
			$("#v-vinno").html(viewRow.vinno);
			$("#v-gpscode").html(viewRow.gpscode);
			$("#v-maxloadnum").html(viewRow.maxloadnum);
			$("#v-maxordernum").html(viewRow.maxordernum);
			$("#v-fueltypecn").html(viewRow.fueltypecn);
			$("#v-delstatuscontent").html(viewRow.delstatuscontent);
			$("#v-maintainmileage").html(viewRow.maintainmileage);
			$("#v-tyrenum").html(viewRow.tyrenum);
			$("#v-remarks").html(viewRow.remarks);
			//$('#view-fm').form('clear');
			//alert("----="+viewRow.vehicletype);
			//return;
		}
		
				//$('#loading').window('close');
				//data = eval('('+data+')');
				//$('#view-dlg').dialog('close');
				//$('#dataTable').datagrid('reload'); 
	    		

	}
/**
 * 打开修改窗口
 */
  function openEdit(){
	    $('#edit-fm').form('clear');
		var rows = $('#dataTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要更新的车辆信息",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一条车辆信息进行更新",'info');
			return;
		}
		var row = $('#dataTable').datagrid('getSelected');
		if (row){
			$('#edit-dlg').dialog('open').dialog('setTitle','车辆信息');
			$('#edit-fm').form('load',row);
			//url = '/BS56/sys/roleNew.json';
		}

	}
  
  /**
   * 保存修改的参数信息

   */
  function saveEdit1111(){
  	$('#edit-fm').form('submit',{
  		onSubmit: function(){
  			var isValidate = $(this).form('validate');
  			if(isValidate){
  				//$('#loading').window('open');
  			}
  			return isValidate;
  		},
  		success: function(data){
			//$('#loading').window('close');
			data = eval('('+data+')');
			$('#edit-dlg').dialog('close');
			$('#dataTable').datagrid('reload'); 
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个星级信息修改'+data.msg+'！',
			});
			//$('#loading').window('close');
		}
  	});
  }
  
  function saveEdit(){
		$('#edit-fm').form('submit',{
			onSubmit: function(){
				var isValidate = $(this).form('validate');
				if(isValidate){
					//$('#loading').window('open');
				}
				return isValidate;
			},
			url:baseURL+"/sys/vehicle/doupdateVehicleVo.json",
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
					msg :  data.total+'个车辆信息已修改'+data.msg+'！',
				});
				$('#loading').window('close');
			}
		});
	}
  
//删除
	function deleterow(){
		var rows = $('#dataTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要报废的车辆信息",'info');
			return;
		}else{
			var row=rows[0];
			var bfbz=row.delstatus;
			if(bfbz=="0"){
				$.messager.alert('提示',"该车辆已经报废，请重新选择",'info');
				return false;
			}
		}
		
		
		
		$.messager.confirm('提示','确定要报废吗?',function(result){
        if (result){
        	var ps = "";
        	$.each(rows,function(i,n){
        		if(i==0) 
        			ps += "?id="+n.id;
        		else
        			ps += "&id="+n.id;
        	});
        	$.post(baseURL+'/sys/vehicle/dodelVehicleVo.json'+ps,function(data){
	        	$('#dataTable').datagrid('reload'); 
	        	//console.log("del--"+data);
        		$.messager.show({
					title : '提示',
					msg :  data.total+'辆车辆被报废'+data.msg+'！',
				});
        	});
        }
    });
	}
	
	  //查询
	function searchVehicle(){
		$('#dataTable').datagrid({
			title:'车辆信息维护', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选			height:420, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。			striped: true, //奇偶行颜色不同			collapsible:true,//可折叠			url:baseURL+"/sys/vehicle/getVehicleVoList.json?vehicleno="+encodeURI($('#vehicleno').val())+"&vehicles="+$('#vehicles').val(), //数据来源
			sortName: 'id', //排序的列
			sortOrder: 'desc', //倒序 desc
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
			rowStyler:function(index,row){
				if (row.delstatuscontent=='报废'){
						return 'color:red ;';}
					},	   	
						
			columns:[[
				{field:'id',checkbox:true,width:2}, //显示复选框
				{field:'vehicleno',title:'车牌号码',width:15,
					formatter:function(value,row,index){return row.vehicleno;} //需要formatter一下才能显示正确的数据
				},
				{field:'vehicletype',title:'车型',width:15,
					formatter:function(value,row,index){return row.vehicletype;}
				},
				{field:'buydate',title:'购车日期',width:15,
					formatter:function(value,row,index){
						var bdate=row.buydate;
						if(bdate!=null&&bdate.length>10){
							bdate=bdate.substring(0,10);
						}
						
						return bdate;
					}
				},
				{field:'fueltypecn',title:'车辆燃油',width:15,
					formatter:function(value,row,index){return row.fueltypecn;}
				},
				{field:'vehiclescn',title:'车辆用途',width:15,
					formatter:function(value,row,index){return row.vehiclescn;}
				},
				{field:'register',title:'登记注册',width:15,
					formatter:function(value,row,index){return row.register;}
				},
				{field:'delstatuscontent',title:'车辆状态',width:10,
					formatter:function(value,row,index){return row.delstatuscontent;}
				}
			]],
			
			toolbar:'#toolbar',
			onLoadSuccess:function(){
				$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题

			
			}
		});
	}
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		$('#vehicles').textbox("setValue","10");
		$('#vehicles').textbox("setText","配送车辆");
		searchVehicle();
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