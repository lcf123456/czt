/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	$('#keyword').textbox('textbox').keydown(function(e){
		if(e.keyCode==13){
			searchData();
		}
	})
	$('#dataTabel').datagrid({
		title:'来烟破损', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		url:baseURL+"/wms/cigarettedamaged/getCigarettedamagedPageList.json?damagedtype="+damagedtype, //数据来源
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
			{field:'inboundid',title:'入库单',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.inboundid;}
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
			
		},
		onDblClickCell:function(index,field,value){
			viewD();
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
	  $('#view-fm').form('clear');
	  $("#audit-div" ).css("display", "none");
	  $('#btn-audit').linkbutton('disable');
	  $('#btn-back').linkbutton('disable');
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
		var inboundid = row.inboundid;
		if (row){
			$('#view-dlg').dialog('open').dialog('setTitle',"查看");
			$('#view-dlg').form('load',row);
			$('#inboundid1').textbox("setValue",row.inboundid);
			$('#inboundid1').textbox("setText",row.inboundid);
			$('#qty1').textbox("setValue",row.qty);
			$('#qty1').textbox("setText",row.qty);
			
		}
		var obj = $('#cigarettedamagedlineTabel2');
		getcigarettedamagedlinelist(obj,inboundid,240)
		//getsplapplylistline(inboundid);
	}

  /**
   * 审核窗口
   */
    function openAudit(){
  	  $('#view-fm').form('clear');
  	  $("#audit-div" ).css("display", "block");
  	$('#btn-audit').linkbutton('enable');
	  $('#btn-back').linkbutton('enable');
  	  var rows = $('#dataTabel').datagrid('getSelections');
  		if(rows.length==0){
  			$.messager.alert('提示',"请选择你要审核的信息",'info');
  			return;
  		}
  		if(rows.length > 1){
  			$.messager.alert('提示',"只能选择一条信息进行审核",'info');
  			return;
  		}
  		$('#checkusername1').textbox("setValue",username);
  		$('#checkusername1').textbox("setText",username);
  		var nowTime = getDateYMD();
  		$('#checktime1').textbox("setValue",nowTime);
  		$('#checktime1').textbox("setText",nowTime);
  		var row = $('#dataTabel').datagrid('getSelected');
  		var inboundid = row.inboundid;
  		cdid = inboundid;
  		var checkflag = row.checkflag;
  		if(checkflag=="20"||checkflag=="30"){
  			$.messager.alert('提示',"该信息已完成审核，请选择其它信息进行审核！",'info');
  			return;
  		}
  		if (row){
  			$('#view-dlg').dialog('open').dialog('setTitle',"查看");
  			$('#view-dlg').form('load',row);
  		}
  		var obj = $('#cigarettedamagedlineTabel2');
  		getcigarettedamagedlinelist(obj,inboundid,160)
  		//getsplapplylistline(inboundid);
  	}

    function doAudit(checkflag){
    	var checkdescribe = $("#checkdescribe1").val();
    	var alertMsg = "确定通过吗？";
    	if(checkflag=="30"){alertMsg = "确定驳回吗？";}
    	$.messager.confirm('提示',alertMsg,function(result){
    		if(result){
    			$('#add-fm').form('submit',{
        			url:baseURL+"/wms/cigarettedamaged/doAudit.json?inboundid="+cdid+"&checkflag="+checkflag+"&checkdescribe="+checkdescribe, 
            		data:$("#add-fm").serializeArray(),
            		beforeSend : function () {
            			$.messager.progress({
            				text : '正在新增中...',
            			});
            		},
            		success: function(data){
            			data = eval('('+data+')');
            			$('#add-dlg').dialog('close');
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
			$.messager.alert('提示',"请选择你要删除的领料申请信息",'info');
			return;
		}
		if(rows.length > 1){
  			$.messager.alert('提示',"只能选择一条领料信息进行删除",'info');
  			return;
  		}
		var row = $('#dataTabel').datagrid('getSelected');
		var checkflag = row.checkflag;
		var inboundid = row.inboundid;
		if(checkflag=="20"){
  			$.messager.alert('提示',"对不起，您无权删除已经审核通过的信息！",'info');
  			return;
  		}
		
		$.messager.confirm('提示','确定要删除吗?',function(result){
	        if (result){
	        	$.post(baseURL+'/wms/cigarettedamaged/doDel.json?inboundid='+inboundid,function(data){
		        	$('#dataTabel').datagrid('reload'); 
		        	//console.log("del--"+data);
	        		$.messager.show({
						title : '提示',
						msg :  data.total+'个来烟破损信息'+data.msg+'！',
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
	$('#add-dlg').dialog('open').dialog('setTitle','来烟破损');
	
	$("#fisstdiv" ).css("display", "block"); 
	$("#seconddiv" ).css("display", "none"); 
	$('#firstBtn').linkbutton('disable'); 
	$('#saveBtn').linkbutton('disable'); 
	$('#nextBtn').linkbutton('enable'); 
	 //获取入库单列表
	getInboundList();
}

function getInboundList(){
	$('#keyworditem').textbox('textbox').keydown(function(e){
		if(e.keyCode==13){
			searchType();
		}
	})
	$('#inboundTabel').datagrid({
		title:'入库单', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //多选
		height:340, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		url:baseURL+"/wms/inbound/getInBoundPageList.json?intype=10", //数据来源
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
			{field:'qty',title:'数量(件)',width:$(this).width()*0.1,
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

//上一步
function fiststep()
{
	$("#fisstdiv" ).css("display", "block"); 
	$("#seconddiv" ).css("display", "none"); 
	$('#firstBtn').linkbutton('disable'); 
	$('#saveBtn').linkbutton('disable'); 
	$('#nextBtn').linkbutton('enable'); 
	}

//下一步
function nextstep()
{
	var rows = $('#inboundTabel').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择入库单信息",'info');
		return;
	}
	if(rows.length > 1){
		$.messager.alert('提示',"只能选择一条入库单信息进行操作",'info');
		return;
	}
	
	var row = $('#inboundTabel').datagrid('getSelected');
	if (row){
		var inboundid = row.inboundid;
		//如果该入库单的来烟破损已经添加并审核通过，则不能继续新增
		$.ajax({ 
		    url: baseURL+'/wms/cigarettedamaged/doSelectByInboundid.json?inboundid='+inboundid, 
		    type: 'POST', 
		    success: function(data){
		    	if(data.checkflag=="20"||data.checkflag=="30"){
		    		$.messager.alert('提示',"该入库单已经新增过破损并审核，不能再次新增！",'info');
		    		return;
		    	}
		    	else{
		    		$('#firstBtn').linkbutton('enable'); 
		    		$('#nextBtn').linkbutton('disable'); 
		    		$('#saveBtn').linkbutton('enable'); 
		    		$('#add-dlg').form('load',row);
		    		$('#inboundid').textbox("setValue",row.inboundid);
					$('#inboundid').textbox("setText",row.inboundid);
		    		$("#fisstdiv" ).css("display", "none"); //审核不显示
		    		$("#seconddiv" ).css("display", "block"); //审核不显示
		    		
		    		//入库单详情
		    		getinboundlinelist(inboundid);
		    		
		    		//卷烟破损明细列表
		    		var obj= $('#cigarettedamagedlineTabel');
		    		getcigarettedamagedlinelist(obj,inboundid,140);
		    	}
			}
		   }); 
		
		
	}
	}

//入库单明细列表
function getinboundlinelist(inboundid)
{
		//获取领料新增分配的物资明细列表
	$('#inboundlineTabel').datagrid({
		title:'入库单明细', //标题
		method:'post',
		iconCls:'icon-view', //图标
		singleSelect:false, //多选
		height:140, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		url:baseURL+"/wms/inbound/getInBoundLineList.json?inboundid="+inboundid, 
		sortName: 'inbounddetailid', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'inbounddetailid', //主键字段
		singleSelect: true,
		pagination:false, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'inbounddetailid',title:'序号',width:$(this).width()*0.05,sortable:true,
				formatter:function(value,row,index){return row.inbounddetailid;} //需要formatter一下才能显示正确的数据
			},
			{field:'inboundid',title:'入库编号',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.inboundid;}
			},
			{field:'cigarettename',title:'卷烟名称',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.cigarettename;} //需要formatter一下才能显示正确的数据
			},
			{field:'boxqty',title:'件烟数量',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.boxqty;}
			},
			{field:'consignsor',title:'货主',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.consignsor;}
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
	 $('#inboundlineTabel').datagrid('selectRow',index);// 关键在这里  
	    var row = $('#inboundlineTabel').datagrid('getSelected');  
	    if (row){  
	    	$('#cigarettename').textbox("setValue",row.cigarettename);
	    	$('#cigarettename').textbox("setText",row.cigarettename);
	    	$('#boxqty').textbox("setValue",row.boxqty);
	    	$('#boxqty').textbox("setText",row.boxqty);
	    	$('#inbounddetailid2').val(row.inbounddetailid);
	    	$('#cigarettecode2').val(row.cigarettecode);
	    }
}

//卷烟破损明细列表
function getcigarettedamagedlinelist(obj,inboundid,dgheight)
{
	obj.datagrid({
		title:'入库单明细', //标题
		method:'post',
		iconCls:'icon-view', //图标
		singleSelect:false, //多选
		height:dgheight, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		url:baseURL+"/wms/cigarettedamaged/getCigarettedamagedLineList.json?inboundid="+inboundid, 
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		singleSelect: true,
		pagination:false, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',title:'序号',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.id;} //需要formatter一下才能显示正确的数据
			},
			{field:'inboundid',title:'入库单号',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.inboundid;}
			},
			{field:'inbounddetailid',title:'入库单明细号',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.inbounddetailid;}
			},
			{field:'cigarettename',title:'卷烟名称',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.cigarettename;} //需要formatter一下才能显示正确的数据
			},
			{field:'boxqty',title:'件烟数量(件)',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.boxqty;}
			},
			{field:'damagedqty',title:'破损数量(条)',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.damagedqty;}
			},
			{field:'actualqty',title:'实际破损数量(条)',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.actualqty;}
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
/**
 * 新建角色
 */
function dosave(){
	var boxqty= $("#boxqty").val(); 
	var otherqty= $("#otherqty").val(); 
	var actualqty= $("#actualqtynew").val(); 
	if(otherqty==null||otherqty==""){
		$.messager.alert('系统提示', '请输入破损数量！', 'warning');
		return;
	}
	if(boxqty==null||boxqty==""){
		$.messager.alert('系统提示', '请选择入库明细并输入破损数量再保存！', 'warning');
		return;
	}
	if(otherqty>boxqty){
		$.messager.alert('系统提示', '对不起，破损数量不能大于入库件烟数量！', 'warning');
		return;
	}
	if(actualqty==null||actualqty==""){
		$.messager.alert('系统提示', '输入实际破损条数量！', 'warning');
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
		url:baseURL+"/wms/cigarettedamaged/doSave.json",
		data:$("#add-fm").serializeArray(),
		beforeSend : function () {
			$.messager.progress({
				text : '正在新增中...',
			});
		},
		success: function(data){
			//$('#loading').window('close');
			
			data = eval('('+data+')');
			//$('#add-dlg').dialog('close');
			$('#dataTabel').datagrid('reload'); 
			$('#cigarettedamagedlineTabel').datagrid('reload');
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个保存'+data.msg+'！',
			});
			//$('#loading').window('close');
		}
	});
}