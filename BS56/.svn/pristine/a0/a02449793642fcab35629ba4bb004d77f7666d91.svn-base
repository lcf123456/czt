/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	$.post(baseURL+'/cost/vehiclerepairapply/getReceiptInfo.json?id='+id,function(data){
		
		alert(data);
		
		data = eval('('+data+')');
		$('#add-fm').form('load',data);
		
    	alert(data.vehicleno);
    	
//    	$('#vehicleno').textbox('setValue',data.vehicleno);
//    	$('#vehicleno').textbox('setText',data.vehicleno);
	});
});

//查询
function searchData(){
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
	var begdate=getDateYY0101();
	var enddate=getDateYMD();
	$('#begdate').datebox('setValue', begdate);
	$('#enddate').datebox('setValue', enddate);
	searchData();
}


function getConfiscationLineView(inboundid)
{
		//获取领料新增分配的物资明细列表
	$('#listdataTabel').datagrid({
		title:'入库明细', //标题
		method:'post',
		iconCls:'icon-view', //图标
		singleSelect:false, //多选
		height:200, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		url:baseURL+"/wms/inbound/getInBoundLineList.json?inboundid="+inboundid, 
		sortName: 'inbounddetailid', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'inbounddetailid', //主键字段
		pagination:false, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'inbounddetailid',title:'序号',width:10,
				formatter:function(value,row,index){return row.inbounddetailid;} //需要formatter一下才能显示正确的数据
			},
			{field:'inboundid',title:'入库编号',width:10,
				formatter:function(value,row,index){return row.inboundid;}
			},
			{field:'cigarettecode',title:'卷烟代码',width:10,
				formatter:function(value,row,index){return row.cigarettecode;} //需要formatter一下才能显示正确的数据
			},
			{field:'cigarettename',title:'卷烟名称',width:10,
				formatter:function(value,row,index){return row.cigarettename;} //需要formatter一下才能显示正确的数据
			},
			{field:'boxqty',title:'卷烟数量',width:10,
				formatter:function(value,row,index){return row.boxqty;}
			},
			{field:'otherqty',title:'散烟区入库数量',width:10,
				formatter:function(value,row,index){return row.otherqty;}
			}
		]],
		onLoadSuccess:function(){
			//$('#listtabdiv .panel-header').css('display','none'); 
			
		}
	});
}

function getConfiscationLine(inboundid)
{
	//获取领料新增分配的物资明细列表
	$('#newlistdataTabel').datagrid({
		title:'入库明细', //标题
		method:'post',
		iconCls:'icon-view', //图标
		singleSelect:false, //多选
		height:200, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		url:baseURL+"/wms/inbound/getInBoundLineList.json?inboundid="+inboundid, 
		sortName: 'inbounddetailid', //排序的列
		sortOrder: 'asc', //倒序
		remoteSort: true, //服务器端排序
		idField:'inbounddetailid', //主键字段
		pagination:false, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'inbounddetailid',title:'序号',width:10,
				formatter:function(value,row,index){return row.inbounddetailid;} //需要formatter一下才能显示正确的数据
			},
			{field:'inboundid',title:'入库编号',width:10,
				formatter:function(value,row,index){return row.inboundid;}
			},
			{field:'cigarettecode',title:'卷烟代码',width:10,
				formatter:function(value,row,index){return row.cigarettecode;} //需要formatter一下才能显示正确的数据
			},
			{field:'cigarettename',title:'卷烟名称',width:10,
				formatter:function(value,row,index){return row.cigarettename;} //需要formatter一下才能显示正确的数据
			},
			{field:'boxqty',title:'卷烟数量',width:10,
				formatter:function(value,row,index){return row.boxqty;}
			},
			{field:'otherqty',title:'散烟区入库数量',width:10,
				formatter:function(value,row,index){return row.otherqty;}
			}
			]],
			onLoadSuccess:function(){
				//$('#listtabdiv .panel-header').css('display','none'); 
			}
	});
}


function cigSubmit(){
	$('#cigarettename').val($('#cigarettecode').combobox("getText"));
	var inboundid=$('#newinboundid').val();
	if(inboundid==null||inboundid=="")inboundid="-1";
	var navicert= $("#navicert").val(); 
	var contractno= $("#contractno").val(); 
	var cigarettecode= $("#cigarettecode").val(); 
	var itemqty= $("#itemqty").val(); 
	
	//alert($("#consignsor").val()+"=="+contractno+"--"+qty);
	if(navicert==null||navicert==""){
		$.messager.alert('系统提示', '请输入准运证号！', 'warning');
		return;
	}else{
		if(navicert.length>40){
			$.messager.alert('系统提示', '准运证号输入过长！', 'warning');
			return;
		}
	}
	if(contractno==null||contractno==""){
		$.messager.alert('系统提示', '请输入合同号！', 'warning');
		return;
	}else{
		if(contractno.length>40){
			$.messager.alert('系统提示', '合同号输入过长！', 'warning');
			return;
		}
	}
//	if(qty==null||qty==""){
//		$.messager.alert('系统提示', '请输入入库总数量！', 'warning');
//		return;
//	}
	if(cigarettecode==null||cigarettecode==""){
		$.messager.alert('系统提示', '请选择要入库的卷烟！', 'warning');
		return;
	}
	if(itemqty==null||itemqty==""){
		$.messager.alert('系统提示', '请输入入库卷烟数量！', 'warning');
		return;
	}
	var qty= $("#qty").val();
	if(qty==""||qty==null)qty=0
	$('#qty').numberbox("setValue",parseInt(qty)+parseInt(itemqty));
	$('#qty').numberbox("setText",parseInt(qty)+parseInt(itemqty));
	qty= $("#qty").val();
	
	$('#new-fm').form('submit',{
		onSubmit: function(){
			var isValidate = $(this).form('validate');
			if(isValidate){
			}
			return isValidate;
		},
		url:baseURL+"/wms/inbound/doAddInboundAndLine.json?inboundid="+inboundid, 
		//data:$("#new-fm").serializeArray(),
		beforeSend : function () {
			$.messager.progress({
				text : '正在新增中...',
			});
		},
		success: function(data){
			
			data = eval('('+data+')');
			$("#newinboundid" ).val(data.inboundid);
			//$("#status" ).combobox("setValue".data.status);
			$('qty').attr("readonly",true);
			$('status').attr("readonly",true);
			$('navicert').attr("readonly",true);
			$('contractno').attr("readonly",true);
			//$('#add-dlg').dialog('close');
			$('#dataTabel').datagrid('reload'); 
			$('#newlistdataTabel').datagrid('reload');
    		$.messager.show({
				title : '提示',
				msg :  data.total+'条入库明细新增'+data.msg+'！',
			});
    		//$("#listtabdiv" ).css("display", "block");
    		getConfiscationLine(data.inboundid);
		}
	});
}

/**
 * 入散烟区
 * @returns
 */
function cigImp(){
	var rows = $('#dataTabel').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择入库单信息",'info');
		return;
	}else{
		for(var i=0;i<rows.length;i++){
			var row=rows[i];
			if(row.status=="30"){
				$.messager.alert('提示',"所选入库单部分为入库完成状态，请重新选择并提交！",'info');
				return;
			}
		}
	}
	$.messager.confirm('提示','确定要对所选入库单进行入库操作吗?',function(result){
		if (result){
			var ids = "";
			$.each(rows,function(i,n){
	      		if(i==0){ 
	      			ids += "?id="+n.inboundid;
	      		}else{
	      			ids += "&id="+n.inboundid;
				}	
	      	});
	      	$.post(baseURL+'/wms/inbound/doConfiscationImp.json'+ids,function(data){
		        	$('#dataTabel').datagrid('reload'); 
		        	//console.log("del--"+data);
		        	data = eval('('+data+')');
	      		$.messager.show({
						title : '提示',
						msg :  data.total+'个入库单入库'+data.msg+'！',
					});
	      	});
		}
	})
}

	/**
	 * 打开新增窗口
	 * @returns
	 */
	function openNew(){
		$('#add-fm').form('clear');
		$('#add-dlg').dialog('open').dialog('setTitle',"维修申报");
		var applydate=getDateYMD();
		$('#applydate').datebox('setValue', applydate);
		//维修类别
		$("#lvl1").combobox({
			url : baseURL+"/sys/basemultitypeinfo/getMultitypeComboboxByCond.json?typecode=VEHICLEREPAIR&typelevel=1&parentid=0",//返回json数据的url
			//url : baseURL+"/cost/suppliesimp/getSPLTypesCombobox.json?clevel=2&fid=2",//返回json数据的url
	    	valueField : "id",//这个id和你返回json里面的id对应
	    	textField : "content", //这个text和你返回json里面的text对应
	    	required:true,
	    	onSelect: function (row1) {  
	            if (row1 != null) {  
	            	//物资类别二级
	                $('#lvl2').combobox({  
	                	url : baseURL+"/sys/basemultitypeinfo/getMultitypeComboboxByCond.json?typecode=VEHICLEREPAIR&typelevel=2&parentid="+row1.id,//返回json数据的url
	                	//url : baseURL+"/cost/suppliesimp/getSPLTypesCombobox.json?clevel=3&fid="+row1.id,//返回json数据的url
	                    valueField : "id",//这个id和你返回json里面的id对应
	                	textField : "content", //这个text和你返回json里面的text对应
	                	required:true,
	                	onSelect: function (row2) {  
	                        if (row2 != null) {  
	                        	//物资类别三级
	                            $('#repairinfoid').combobox({  
	                            	url : baseURL+"/sys/basemultitypeinfo/getMultitypeComboboxByCond.json?typecode=VEHICLEREPAIR&typelevel=3&parentid="+row2.id,//返回json数据的url
	                            	//url : baseURL+"/cost/suppliesimp/getSPLTypesCombobox.json?clevel=4&fid="+row2.id,//返回json数据的url
	                            	valueField : "id",//这个id和你返回json里面的id对应
	                            	textField : "content", //这个text和你返回json里面的text对应
	                            	required:true,
	                            	onSelect: function (row3) {
	                            		$('#repairinfo').val(row3.content);
	                            		$('#mainitem').val(row3.content);
	                            	}
	                            });  
	                        }  
	                    }  
	                });  
	            }  
	        }  
	    })
	}
	
	function searchVehicle(){
		var keywd=$('#keywd').textbox("getValue");
		$.ajax({
  			url:baseURL+"/cost/vehiclerepairapply/getRouteVehicleInfo.json?keywd="+keywd, //数据来源
  			success : function(data) {
  	             $.each(data, function(i, route) {
  	            	if(i==0){
  	            		$('#routecode').textbox("setValue",route.routecode);
  	            		$('#vehicleid').textbox("setValue",route.vehicleid);
  	            		$('#vehicleno').textbox("setValue",route.vehicleno);
  	            		$('#vehicletype').textbox("setValue",route.vehicletype);
  	            		$('#drivername').textbox("setValue",route.drivername);
  	            		$('#driverid').textbox("setValue",route.driverid);
  	            	}
  	             });
  	         },
  	         error: function(e) { 
  	          	} 
  		})
	}
	
	function saveAdd(){
		$('#add-fm').form('submit',{
			onSubmit: function(){
				var isValidate = $(this).form('validate');
				if(isValidate){
					//$('#loading').window('open');
				}
				return isValidate;
			},
			url:baseURL+"/cost/vehiclerepairapply/doVehicleRepairApplyAdd.json",
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
					msg :  data.total+'个车辆维修申报信息新增'+data.msg+'！',
				});
				//$('#loading').window('close');
			}
		});
	}

	function openAudit(){
		var rows = $('#dataTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要审核的维修申报信息",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一条维修申报信息进行审核",'info');
			return;
		}
		var row = $('#dataTable').datagrid('getSelected');
		var status=row.status;
		//未审核的进行分部主任审核
		if (status=="10"){
			$('#audit-dlg').dialog('open').dialog('setTitle','车辆维修申报--分部主任审核');
			$('#audit-fm').form('load',row);
		}
		//分部主任审核通过的进行车管员审核
		if (status=="40"){
			$('#vehiclemainitem').val(row.mainitem);
			//初始化维修站点
			$("#unitid").combobox({
		    	url : baseURL+"/sys/user/getComboboxByTypeCode.json?typeCode=REPAIR",//返回json数据的url
		    	valueField : "id",//这个id和你返回json里面的id对应
		    	textField : "contentlist", //这个text和你返回json里面的text对应
		    	required:true,
				loadFilter : function (data) {
					if (data && data instanceof Array) {
						data.splice(0, 0, {id: '', contentlist: '--请选择--'});　
					}
					return data;
				}
		    })
		    $("#repairtype").combobox({
		    	//url : baseURL+"/sys/user/getComboboxByTypeCode.json?typeCode=REPAIR",//返回json数据的url
		    	valueField : "id",//这个id和你返回json里面的id对应
		    	textField : "contentlist", //这个text和你返回json里面的text对应
		    	required:true,
				data: [{
					id: '10',
					contentlist: '维修'
				},{
					id: '20',
					contentlist: '保养'
				}]
		    })
		    $("#isrepeat").combobox({
		    	//url : baseURL+"/sys/user/getComboboxByTypeCode.json?typeCode=REPAIR",//返回json数据的url
		    	valueField : "id",//这个id和你返回json里面的id对应
		    	textField : "contentlist", //这个text和你返回json里面的text对应
		    	required:true,
		    	data: [{
		    		id: '0',
		    		contentlist: '否'
		    	},{
		    		id: '1',
		    		contentlist: '是'
		    	}]
		    })
			$('#safeaudit-dlg').dialog('open').dialog('setTitle','车辆维修申报--车管员审核');
			$('#safeaudit-fm').form('load',row);
		}
	}
	
function saveAudit(bz){
	$('#status').textbox("setValue",bz);
	$('#audit-fm').form('submit',{
		onSubmit: function(){
			var isValidate = $(this).form('validate');
			if(isValidate){
				//$('#loading').window('open');
			}
			return isValidate;
		},
		url:baseURL+"/cost/vehiclerepairapply/doVehicleRepairApplyAudit.json",
		data:$("#audit-fm").serializeArray(),
		beforeSend : function () {
			$.messager.progress({
				text : '正在审核中...',
			});
		},
		success: function(data){
			//$('#loading').window('close');
			data = eval('('+data+')');
			$('#audit-dlg').dialog('close');
			$('#dataTable').datagrid('reload'); 
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个车辆维修申报信息审核'+data.msg+'！',
			});
			//$('#loading').window('close');
		}
	});
}

function openView(){
	$('#viewaudit-fm').form('clear');
	var rows = $('#dataTable').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择你要查看的维修申报信息",'info');
		return;
	}
	if(rows.length > 1){
		$.messager.alert('提示',"只能选择一条维修申报信息进行查看",'info');
		return;
	}
	var row = $('#dataTable').datagrid('getSelected');
	var isreply=row.isreply;
	//未回单
	if (isreply=="10"){
		$('#viewaudit-dlg').dialog('open').dialog('setTitle','车辆维修申报--查看');
		$('#viewaudit-fm').form('load',row);
		var status=row.status;
		
		var leaderstatus="分部主任审核通过";
		if(status=="10")leaderstatus="分部主任未审核";
		if(status=="50")leaderstatus="分部主任审核驳回";
		$('#leaderstatus').textbox('setValue',leaderstatus);
		
		var vehiclectrstatus="车管员未审核";
		if(status=="20")vehiclectrstatus="车管员审核通过";
		if(status=="30")vehiclectrstatus="车管员审核驳回";
		$('#vehiclectrstatus').textbox('setValue',vehiclectrstatus);
		
		var isrepeat=row.isrepeat;
		if(isrepeat=="0")$('#isrepeat_view').textbox('setValue',"否");
		if(isrepeat=="1")$('#isrepeat_view').textbox('setValue',"是");
		
	}
}

/**
 * 车管员审核
 * @param bz
 * @returns
 */
function saveSafeAudit(bz){
	$('#status_safe').textbox("setValue",bz);
	$('#safeaudit-fm').form('submit',{
		onSubmit: function(){
			var isValidate = $(this).form('validate');
			if(isValidate){
				//$('#loading').window('open');
			}
			return isValidate;
		},
		url:baseURL+"/cost/vehiclerepairapply/doVehicleRepairApplySafeAudit.json",
		data:$("#safeaudit-fm").serializeArray(),
		beforeSend : function () {
			$.messager.progress({
				text : '正在审核中...',
			});
		},
		success: function(data){
			//$('#loading').window('close');
			data = eval('('+data+')');
			$('#safeaudit-dlg').dialog('close');
			$('#dataTable').datagrid('reload'); 
			$.messager.show({
				title : '提示',
				msg :  data.total+'个车辆维修申报信息审核'+data.msg+'！',
			});
			//$('#loading').window('close');
		}
	});
}

/**
 * 打印窗口
 */
function openPrint(){
	var rows = $('#dataTable').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择你要打印的维修申报信息",'info');
		return;
	}
	if(rows.length > 1){
		$.messager.alert('提示',"只能选择一条维修申报信息进行打印",'info');
		return;
	}
	var row = $('#dataTable').datagrid('getSelected');
	var printdate=getDateYMD();
	var isrepeat=row.isrepeat;
	var isrepeatcontent="否";
	if(isrepeat=="1")isrepeatcontent="是";
	var safesuggestion=row.safesuggestion;
	if(safesuggestion==null)safesuggestion="";
    var contentstr='<table width="100%" border="0" cellpadding="0" cellspacing="0">';
	contentstr+='<h2  style="text-align:center;">白沙物流车辆维修、保养申报单</h2>';
	contentstr+='<tr><td align=left>R/W1/WY-'+printdate.substring(5)+'：</td><td align=left>(维修联)：</td><td align=right>'+row.code+'</td></tr></table>';
	contentstr +='<table width="100%" border="1" cellpadding="0" cellspacing="0">'+
							'<tr><td align="left"><strong>车辆型号</strong></td><td align="left">'+row.vehicletype+'</td>'+
							'<td align="left"><strong>车牌号码</strong></td><td align="left">'+row.vehicleno+'</td>'+
							'<td align="left"><strong>申报日期</strong></td><td align="left">'+row.applydate+'</td></tr>'+
							'<tr><td align="left"><strong>维修站点</strong></td> <td align="left">'+row.unitname+'</td>'+
							'<td align="left"><strong>重复维修</strong></td> <td align="left">'+isrepeatcontent+'</td>'+
							'<td align="left"><strong>预计费用</strong></td> <td align="left">'+row.estimatecost+'</td>'+
							'</tr>';
	contentstr +='<tr><td    align="left" nowrap colspan="6"> <strong>维修保养项目：</strong><br/>'+row.vehiclemainitem+
							'</br></br>注：<strong>本单两日内有效 如系刹车、方向故障必须立即送修 </strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+ 
							'&nbsp;&nbsp;&nbsp;驾驶员签名：</td></tr>';
	contentstr +='<tr><td   align="left" nowrap colspan="6"><strong>分部主任意见：</strong>'+row.leadersuggestion+
						    '<strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;车管员意见：</strong>'+safesuggestion+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
						    '<B>维修方确认(签字)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维修结果认定(签字)</B></td></tr>'+
						    '</table></td></tr><tr><td></br></br></br></tr></table>';
	//---------------------------------------------------------------------------------------------------------------------------------------------------
	contentstr+='<table width="100%" border="0" cellpadding="0" cellspacing="0">';
	contentstr+='<h2  style="text-align:center;">白沙物流车辆维修、保养申报单</h2>';
	contentstr+='<tr><td align=left>R/W1/WY-'+printdate.substring(5)+'：</td><td align=left>(存根联)：</td><td align=right>'+row.code+'</td></tr></table>';
	contentstr +='<table width="100%" border="1" cellpadding="0" cellspacing="0">'+
							'<tr><td align="left"><strong>车辆型号</strong></td><td align="left">'+row.vehicletype+'</td>'+
							'<td align="left"><strong>车牌号码</strong></td><td align="left">'+row.vehicleno+'</td>'+
							'<td align="left"><strong>申报日期</strong></td><td align="left">'+row.applydate+'</td></tr>'+
							'<tr><td align="left"><strong>维修站点</strong></td> <td align="left">'+row.unitname+'</td>'+
							'<td align="left"><strong>重复维修</strong></td> <td align="left">'+isrepeatcontent+'</td>'+
							'<td align="left"><strong>预计费用</strong></td> <td align="left">'+row.estimatecost+'</td>'+
							'</tr>';
	contentstr +='<tr><td  align="left" nowrap colspan="6"> <strong>维修保养项目：</strong><br/>'+row.vehiclemainitem+
							'</br></br>注：<strong>本单两日内有效 如系刹车、方向故障必须立即送修 </strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+ 
							'&nbsp;&nbsp;&nbsp;驾驶员签名：</td></tr>';
	contentstr +='<tr><td align="left" nowrap colspan="6"><strong>分部主任意见：</strong>'+row.leadersuggestion+
							'<strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;车管员意见：</strong>'+safesuggestion+'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
							'<B>维修方确认(签字)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维修结果认定(签字)</B></td></tr>'+
							'</table></td></tr><tr><td></br></br></td></tr>';
	contentstr +="</table>";
	//alert(contentstr);
	$("#printTable").html(contentstr);
	$('#print-dlg').dialog('open').dialog('setTitle','车辆维修申报打印');
}

function openReceiptList(){
	//$('#abc-fm').form('clear');
	var row = $('#dataTable').datagrid('getSelected');
	//$('#receipt-dlg').dialog('open').dialog('setTitle','车辆维修申报--回单');
	parent.addTab('车辆维修申报--回单',baseURL+"/cost/vehiclerepairapply/toVehicleRepairReceipt?id="+row.id,'icon-mygroup');

	//alert(document.getElementById("vehicleno10").value);
	//$('abc-fm').form('load',{vehicleno10:'湘A-YG421'});
}

function openReceipt(){
	$('#receipt-fm').form('clear');
	var rows = $('#dataTable').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择你要回单的维修申报信息",'info');
		return;
	}
	if(rows.length > 1){
		$.messager.alert('提示',"只能选择一条维修申报信息进行回单",'info');
		return;
	}
	var row = $('#dataTable').datagrid('getSelected');
	var isreply=row.isreply;
	//未回单isreply=="10"
	if (row){
		$('#receipt-dlg').dialog('open').dialog('setTitle','车辆维修申报--回单');
		alert($('abc-fm'));
		alert($('#receipt-fm'));
		$('receipt-fm').form('load',row);
		//$('vehicletype-receipt').textbox("setValue",row.vehicletype);
//		var status=row.status;
		
//		var leaderstatus="分部主任审核通过";
//		if(status=="10")leaderstatus="分部主任未审核";
//		if(status=="50")leaderstatus="分部主任审核驳回";
//		$('#leaderstatus').textbox('setValue',leaderstatus);
//		
//		var vehiclectrstatus="车管员未审核";
//		if(status=="20")vehiclectrstatus="车管员审核通过";
//		if(status=="30")vehiclectrstatus="车管员审核驳回";
//		$('#vehiclectrstatus').textbox('setValue',vehiclectrstatus);
		alert($('#isrepeat_receipt'));
		var isrepeat=row.isrepeat;
		alert(isrepeat);
		if(isrepeat=="0")$('#isrepeat_receipt').textbox('setValue',"否");
		if(isrepeat=="1")$('#isrepeat_receipt').textbox('setValue',"是");
		
	}
}