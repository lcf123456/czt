/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	$('#dataTabel').datagrid({
		title:'机损烟', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //多选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		url:baseURL+"/wms/machinedamaged/getMachinedamagedPageList.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 20,
		pageList: [20,30,50],
		queryParams:{
			}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'id2',title:'id',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.id;} //需要formatter一下才能显示正确的数据
			},
			{field:'createtime',title:'记录时间',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.createtime;}
			},
			{field:'qty',title:'破损数量(条)',width:$(this).width()*0.06,
				formatter:function(value,row,index){return row.qty;}
			},
			{field:'createusername',title:'记录人',width:$(this).width()*0.06,
				formatter:function(value,row,index){return row.createusername;}
			},
			{field:'checkusername',title:'审核人',width:$(this).width()*0.06,
				formatter:function(value,row,index){return row.checkusername;}
			},
			{field:'checkflagname',title:'状态',width:$(this).width()*0.06,
				formatter:function(value,row,index){return row.checkflagname;}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none'); 
			
		}
	});
	
});

//查询
function searchData(){
	var params = $('#dataTabel').datagrid('options').queryParams; //先取得 datagrid 的查询参数
	var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
	$.each( fields, function(i, field){
		params[field.name] = field.value; //设置查询参数
	}); 
	$('#dataTabel').datagrid('reload'); //设置好查询参数 reload 一下就可以了
}
//清空查询条件
function clearForm(){
	$('#queryForm').form('clear');
	searchData();
}

/**
 * 查看窗口
 */
  function viewD(){
	  $('#edit-fm').form('clear');
	  var rows = $('#dataTabel').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要查看的信息",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一条信息进行查看",'info');
			return;
		}
		
		var row = $('#dataTabel').datagrid('getSelected');
		var id = row.id;
		if (row){
			$('#edit-dlg').dialog('open').dialog('setTitle','机损烟');
			$('#edit-fm').form('load',row);
			$('#btn-audit').linkbutton('disable'); 
			$('#btn-back').linkbutton('disable'); 
		}
		var obj = $('#machinedamagedlineTabel2');
		getmachinedamagedlinelist(obj,id,150);
	}

  /**
   * 审核窗口
   */
    function openAudit(){
  	  $('#edit-fm').form('clear');
  	  
  	  var rows = $('#dataTabel').datagrid('getSelections');
  		if(rows.length==0){
  			$.messager.alert('提示',"请选择你要审核的信息",'info');
  			return;
  		}
  		if(rows.length > 1){
  			$.messager.alert('提示',"只能选择一条信息进行审核",'info');
  			return;
  		}
  		$('#checkusername').textbox("setValue",username);
  		$('#checkusername').textbox("setText",username);
  		var nowTime = getDateYMD();
  		$('#checktime').textbox("setValue",nowTime);
  		$('#checktime').textbox("setText",nowTime);
  		var row = $('#dataTabel').datagrid('getSelected');
  		var id = row.id;
  		mdid=id;
  		var checkflag = row.checkflag;
  		if(checkflag!="10"){
  			$.messager.alert('提示',"对不起，该信息已经审核过!",'info');
  			return;
  		}
  		if (row){
  			$('#edit-dlg').dialog('open').dialog('setTitle',"查看");
  			$('#edit-fm').form('load',row);
  			$('#btn-audit').linkbutton('enable');
  		    $('#btn-back').linkbutton('enable');
  		}
  		var obj = $('#machinedamagedlineTabel2');
		getmachinedamagedlinelist(obj,id,150);
  		//getsplapplylistline(inboundid);
  	}

    function doAudit(checkflag){
    	var checkdescribe = $("#checkdescribe").val();
    	var alertMsg = "确定通过吗？";
    	if(checkflag=="30"){alertMsg = "确定驳回吗？";}
    	$.messager.confirm('提示',alertMsg,function(result){
    		if(result){
    			$('#add-fm').form('submit',{
        			url:baseURL+"/wms/machinedamaged/doAudit.json?mdid="+mdid+"&checkflag="+checkflag+"&checkdescribe="+checkdescribe, 
            		data:$("#add-fm").serializeArray(),
            		beforeSend : function () {
            			$.messager.progress({
            				text : '正在新增中...',
            			});
            		},
            		success: function(data){
            			data = eval('('+data+')');
            			$('#edit-dlg').dialog('close');
            			$('#dataTabel').datagrid('reload'); 
                		$.messager.show({
            				title : '提示',
            				msg :  data.msg,
            			});
                		
                		//$("#listtabdiv" ).css("display", "block");
                		//getsplapplylistline();
            		}
            	});
    		}
    		
    	});
    }
    
  //删除
	function deleterow(){
		var rows = $('#dataTabel').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要删除的信息",'info');
			return;
		}
		if(rows.length > 1){
  			$.messager.alert('提示',"只能选择一条信息进行删除",'info');
  			return;
  		}
		var row = $('#dataTabel').datagrid('getSelected');
		var checkflag = row.checkflag;
		var mdid = row.id;
		if(checkflag=="20"){
  			$.messager.alert('提示',"对不起，您无权删除已经审核通过的信息！",'info');
  			return;
  		}
		
		$.messager.confirm('提示','确定要删除吗?',function(result){
	        if (result){
	        	$.post(baseURL+'/wms/machinedamaged/doDel.json?mdid='+mdid,function(data){
		        	$('#dataTabel').datagrid('reload'); 
		        	//console.log("del--"+data);
	        		$.messager.show({
						title : '提示',
						msg :  data.total+'个信息'+data.msg+'！',
					});
	        	});
	        }
	    });
	}
	
/**
 * 打开新增窗口
 */
function openCreate(){
	$('#add-fm').form('clear');
	$('#add-dlg').dialog('open').dialog('setTitle','机损烟');
	
 
	    $('#createusername').textbox("setValue",username);
		$('#createusername').textbox("setText",username);
		var nowTime = getDateYMD();
		$('#createtime').textbox("setValue",nowTime);
		$('#createtime').textbox("setText",nowTime);
		
		//初始化数据
		initData();
		var obj = $('#machinedamagedlineTabel');
		getmachinedamagedlinelist(obj,mdid,150);
}

function initData(){
	$("#cigarettetype").combobox({
        valueField:'id',
        textField:'value',
        data:[
        	{
        		id:20,
        		value:"标准"
        	},{
        		id:30,
        		value:"异形"
        	},{
        		id:40,
        		value:"异形烟混合道"
        	}],
	onLoadSuccess: function () {
		 var val = $(this).combobox("getData");
		 for (var item in val[0]) {
             if (item == "id") {
                typecode=val[0][item];
                 $(this).combobox("select", typecode);
             }
         }
	},
	onSelect: function (row) {  
        if (row != null) {  
        	//只有标准烟才有组次、异形和异形烟混合道都没有
        		//获取组次
        	var keyword = $("#keyword").val();
        	getsortthroughlist(keyword,row.id);
        }
	}
    });
}


//通道列表
function getsortthroughlist(keyword,cigarettetype)
{
		//获取领料新增分配的物资明细列表
	$('#sortthrough').datagrid({
		title:'通道列表', //标题
		method:'post',
		iconCls:'icon-view', //图标
		singleSelect:false, //多选
		height:150, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		url:baseURL+"/wms/machinedamaged/getSortTroughVoList.json?keyword="+keyword+"&cigarettetype="+cigarettetype, 
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		singleSelect: true,
		pagination:false, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'machineseq',title:'通道物理编号',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.machineseq;}
			},
			{field:'troughnum',title:'通道编号',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.troughnum;} //需要formatter一下才能显示正确的数据
			},
			{field:'cigarettecode',title:'卷烟编码',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.cigarettecode;} //需要formatter一下才能显示正确的数据
			},
			{field:'cigarettename',title:'卷烟名称',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.cigarettename;}
			},
			{field:'cigarettetype',title:'类型',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.cigarettetype;}
			},
			{field:'groupno',title:'组次',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.groupno;}
			},
			{field:'_operate',title:'操作',width:$(this).width()*0.05,
				formatter:function(value,row,index){return '<input name="isShow" type="radio" onclick="radClick('+index+')"/>';}
			}
		]],
		onLoadSuccess:function(){
			//$('#inboundlinediv .panel-header').css('display','none'); 
			
		}
	});
	}

function radClick(index){ 
	 $('#sortthrough').datagrid('selectRow',index);// 关键在这里  
	    var row = $('#sortthrough').datagrid('getSelected');  
	    if (row){  
	    	$('#groupno').textbox("setValue",row.groupno);
	    	$('#groupno').textbox("setText",row.groupno);
	    	$('#machineseq').textbox("setValue",row.machineseq);
	    	$('#machineseq').textbox("setText",row.machineseq);
	    	$('#troughnum').textbox("setValue",row.troughnum);
	    	$('#troughnum').textbox("setText",row.troughnum);
	    	$('#cigarettecode').textbox("setValue",row.cigarettecode);
	    	$('#cigarettecode').textbox("setText",row.cigarettecode);
	    	$('#cigarettename').textbox("setValue",row.cigarettename);
	    	$('#cigarettename').textbox("setText",row.cigarettename);
	    }
}

function searchType(){
	var keyword = $("#keyword").val();
	var cigarettetype = $('#cigarettetype').combobox('getValue');
	getsortthroughlist(keyword,cigarettetype);
}

function getInboundList(){
	$('#inboundTabel').datagrid({
		title:'入库单', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:240, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		url:baseURL+"/wms/inbound/getInBoundPageList.json", //数据来源
		sortName: 'inboundid', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'inboundid', //主键字段
		pageNumber: 1, 
		pageSize : 20,
		pageList: [20,30,50],
		queryParams:{
			}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'inboundid',checkbox:true,width:2}, //显示复选框
			{field:'id2',title:'inboundid',width:$(this).width()*0.08,
				formatter:function(value,row,index){return row.inboundid;} //需要formatter一下才能显示正确的数据
			},
			{field:'navicert',title:'准运证',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.navicert;}
			},
			{field:'contractno',title:'合同号',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.contractno;} //需要formatter一下才能显示正确的数据
			},
			{field:'createtime',title:'记录时间',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.createtime;}
			},
			{field:'qty',title:'数量(条)',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.qty;}
			},
			{field:'consignsor',title:'货主',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.consignsor;}
			},
			{field:'intypename',title:'类型',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.intypename;}
			},
			{field:'statusname',title:'状态',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.statusname;}
			}
		]],
		onLoadSuccess:function(){
			//$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#inboundlinediv .panel-header').css('display','none'); 
		}
	});
}




//卷烟破损明细列表
function getmachinedamagedlinelist(obj,id,dgheight)
{
	obj.datagrid({
		title:'破损明细', //标题
		method:'post',
		iconCls:'icon-view', //图标
		singleSelect:false, //多选
		height:dgheight, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		url:baseURL+"/wms/machinedamaged/getMachinedamagedLineList.json?fid="+id, 
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		singleSelect: true,
		pagination:false, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',title:'序号',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.id;} //需要formatter一下才能显示正确的数据
			},
			{field:'troughnum',title:'通道编号',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.troughnum;}
			},
			{field:'cigarettecode',title:'卷烟编码',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.cigarettecode;} //需要formatter一下才能显示正确的数据
			},
			{field:'cigarettename',title:'卷烟名称',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.cigarettename;} //需要formatter一下才能显示正确的数据
			},
			{field:'barcode',title:'件码',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.barcode;}
			},
			{field:'damagedqty',title:'破损数量(条)',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.damagedqty;}
			}
		]],
		onLoadSuccess:function(){
			//$('#inboundlinediv .panel-header').css('display','none'); 
			
		}
	});
	}

/**
 * 提交保存
 */
function dosave(){
	var troughnum= $("#troughnum").val(); //通道号
	var cigarettecode= $("#cigarettecode").val(); 
	var cigarettename= $("#cigarettename").val(); 
	var qty= $("#qty").val();
	if(qty==null||qty==""){
		$.messager.alert('系统提示', '请输入破损数量！', 'warning');
		return;
	}
	if(parseFloat(qty)<=0){
		$.messager.alert('系统提示', '请输入正确的破损数量！', 'warning');
		return;
	}
	if(troughnum==null||troughnum==""){
		$.messager.alert('系统提示', '通道号不能为空,请选择通道信息！', 'warning');
		return;
	}
	if(cigarettecode==null||cigarettecode==""||cigarettename==null||cigarettename==""){
		$.messager.alert('系统提示', '卷烟信息不能为空,请选择通道信息！', 'warning');
		return;
	}
	$('#add-fm').form('submit',{
		onSubmit: function(){
			var isValidate = $(this).form('validate');
			if(isValidate){
				//$('#loading').window('open');
			}
			return isValidate;
		},
		url:baseURL+"/wms/machinedamaged/doSave.json?mdid="+mdid,
		data:$("#add-fm").serializeArray(),
		beforeSend : function () {
			$.messager.progress({
				text : '正在新增中...',
			});
		},
		success: function(data){
			data = eval('('+data+')');
			//$('#add-dlg').dialog('close');
			$('#dataTabel').datagrid('reload'); 
			
			mdid=data.mdid;
			var obj = $('#machinedamagedlineTabel');
			getmachinedamagedlinelist(obj,mdid,150);
			
			
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个保存'+data.msg+'！',
			});
			//$('#loading').window('close');
		}
	});
}