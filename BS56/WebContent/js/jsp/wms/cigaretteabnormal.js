/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	$('#dataTabel').datagrid({
		title:'称重异常', //标题
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
		var inboundid = row.id;
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
  		var inboundid = row.id;
  		abid = inboundid;
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

    //查询卷烟品牌
    function searchType(){
    	var keyword = $("#keyworditem").val();
    	initItemList(keyword);
    }
  //清空卷烟品牌查询条件
    function clearFormitem(){
    	$('#add-fm').form('clear');
    	searchType();
    }
    
    function doAudit(checkflag){
    	var checkdescribe = $("#checkdescribe1").val();
    	var alertMsg = "确定通过吗？";
    	if(checkflag=="30"){alertMsg = "确定驳回吗？";}
    	$.messager.confirm('提示',alertMsg,function(result){
    		if(result){
    			$('#add-fm').form('submit',{
        			url:baseURL+"/wms/cigarettedamaged/doAuditabnormal.json?inboundid="+abid+"&checkflag="+checkflag+"&checkdescribe="+checkdescribe, 
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
	$('#add-dlg').dialog('open').dialog('setTitle','称重异常');
	
	//初始化订单日期
	$('#username_new').textbox("setValue",username);
	$('#username_new').textbox("setText",username);
		//初始化货主
		//var obj = $('#consignsor_new');
		//initconsignsor(obj);
 
	var nowTime = getDateYMD();
	//初始化订单日期从系统订单任务表中取订单日期
	//var consignsor = $("#consignsor_new").val();
	
	$('#outtime_new').datebox("setValue",nowTime);
	
	//卷烟列表
	var keyword = $("#keyworditem").val();
	initItemList(keyword);
	
	abid = 0;
	var obj = $('#cigarettedamagedlineTabel');
	getcigarettedamagedlinelist(obj,abid,240)
	//出库明细列表
	//abid = 0;
	//getcigarettedamagedlinelist();
	//var obj = $('#outboundlinelist');
	//getinboundlinelist(obj,obid,150);
}

function initItemList(keyword){
	$('#itemlist').datagrid({
		title:'卷烟列表', //标题
		method:'post',
		iconCls:'icon-view', //图标
		singleSelect:false, //多选
		height:140, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		url:baseURL+"/wms/outbound/getItemList.json?keyword="+keyword, 
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		singleSelect: true,
		pagination:false, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'itemno',title:'卷烟编码',width:$(this).width()*0.05,sortable:true,
				formatter:function(value,row,index){return row.itemno;} //需要formatter一下才能显示正确的数据
			},
			{field:'itemname',title:'卷烟名称',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.itemname;}
			},
			{field:'shiptypename',title:'卷烟类别',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.shiptypename;} //需要formatter一下才能显示正确的数据
			},
			{field:'jtSize',title:'件条比例',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.jtSize;}
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
	 $('#itemlist').datagrid('selectRow',index);// 关键在这里  
	    var row = $('#itemlist').datagrid('getSelected');  
	    if (row){  
	    	$('#cigarettenamenew').textbox("setValue",row.itemname);
	    	$('#cigarettenamenew').textbox("setText",row.itemname);
	    	$('#cigarettecodenew').textbox("setValue",row.itemno);
	    	$('#cigarettecodenew').textbox("setText",row.itemno);
	    }
}

//卷烟破损明细列表
function getcigarettedamagedlinelist(obj,inboundid,dgheight)
{
	obj.datagrid({
		title:'称重异常明细', //标题
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
			{field:'inboundid',title:'上级id',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.inboundid;}
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
	var cigarettenamenew= $("#cigarettenamenew").val(); 
	var otherqty= $("#otherqty").val(); 
	var actualqty= $("#actualqtynew").val(); 
	if(otherqty==null||otherqty==""){
		$.messager.alert('系统提示', '请输入破损数量！', 'warning');
		return;
	}
	if(cigarettenamenew==null||cigarettenamenew==""){
		$.messager.alert('系统提示', '请选择卷烟！', 'warning');
		return;
	}
	if(otherqty==null||actualqty==""){
		$.messager.alert('系统提示', '请输入破损数量(件)！', 'warning');
		return;
	}
	if(actualqty==null||actualqty==""){
		$.messager.alert('系统提示', '输入实际破损条数量(条)！', 'warning');
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
		url:baseURL+"/wms/cigarettedamaged/doSaveabnormal.json?abid="+abid,
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
			abid = data.abid;
			$('#dataTabel').datagrid('reload'); 
			var obj = $('#cigarettedamagedlineTabel');
			getcigarettedamagedlinelist(obj,abid,240);
			//$('#cigarettedamagedlineTabel').datagrid('reload');
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个保存'+data.msg+'！',
			});
			//$('#loading').window('close');
		}
	});
}