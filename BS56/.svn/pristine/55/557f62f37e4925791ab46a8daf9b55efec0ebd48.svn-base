/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	var nowTime01 = getDateYM01();
	var nowTime = getDateYMD();
		$('#searchtime').datebox("setValue",nowTime01);
		$('#searchtime2').datebox("setValue",nowTime);
	initdatetype();
	var obj = $("#consignsorsearch");
	initconsignsor(obj);
	
	$('#dataTabel').datagrid({
		title:'入库单', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/wms/outbound/getOutBoundPageList.json?outboundtype="+outboundtype, //数据来源
		sortName: 'outboundid', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'outboundid', //主键字段
		pageNumber: 1, 
		pageSize : 20,
		pageList: [20,30,50],
		queryParams:{
			}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'outboundid',checkbox:true,width:2}, //显示复选框
			{field:'vehicleno',title:'车组号',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.vehicleno;}
			},
			{field:'qty',title:'数量',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.qty;} //需要formatter一下才能显示正确的数据
			},
			{field:'orderdate',title:'订单日期',width:$(this).width()*0.1,	formatter: formatDatebox,sortable:true},
			{field:'outtime',title:'出库日期',width:$(this).width()*0.1,formatter: formatDatebox,sortable:true},
			{field:'consignsor',title:'货主',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.consignsor;}
			},
			{field:'createtime',title:'记录时间',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.createtime;}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none'); 
			
		}
	});
	
	
});



//初始化日期类型
function initdatetype(){
	$("#datetype").combobox({
        valueField:'id',
        textField:'value',
        data:[
        	{
        		id:10,
        		value:"出库日期"
        	},{
        		id:20,
        		value:"订单日期"
        	}],
	onLoadSuccess: function () {
		 var val = $(this).combobox("getData");
		 for (var item in val[0]) {
             if (item == "id") {
                 $(this).combobox("select", val[0][item]);
             }
         }
	}
    });
}

//初始货主
function initconsignsor(obj){
	obj.combobox({
        valueField:'code',
        textField:'company',
        url:baseURL+"/wms/consignor/getConsignorListForComb.json", //数据来源
	onLoadSuccess: function () {
		 var val = $(this).combobox("getData");
		 for (var item in val[0]) {
             if (item == "code") {
                 $(this).combobox("select", val[0][item]);
             }
         }
	}
    });
}

function initOrderDatebox(){
    $('#orderdate_new').datebox({
    	onSelect: function(date){
    		var orderdate = $("#orderdate_new").val();
    		var consignsor = $("#consignsor_new").val();
    		initRow(orderdate,consignsor);
    	}
    });
}

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
	//$('#queryForm').form('clear');
    $('#keyword').textbox("setValue","");
	$('#keyword').textbox("setText","");
	searchData();
}


/**
 * 打开新增窗口
 */
function openCreate(){
	$('#add-fm').form('clear');
	$('#add-dlg').dialog('open').dialog('setTitle','出库单');
	
	//初始化订单日期
	initOrderDatebox();
		//初始化货主
		var obj = $('#consignsor_new');
		initconsignsor(obj);
 
	var nowTime = getDateYMD();
	//初始化订单日期从系统订单任务表中取订单日期
	var consignsor = $("#consignsor_new").val();
	
	$.ajax({
		url:baseURL+"/account/timebydm/getTimebydm.json?type=0&date="+nowTime, //此处fid无用
		success : function(data) {
			$('#orderdate_new').datebox("setValue",data.timebydm);
			$('#orderdate_new').datebox("setText",data.timebydm);
			var orderdate = $("#orderdate_new").val();
			initRow(orderdate,consignsor);
         },
         error: function(e) { 
        	 $('#orderdate_new').datebox("setValue",nowTime);
        	 var orderdate = $("#orderdate_new").val();
        	 initRow(orderdate,consignsor);
          	} 
	});
	
	$('#outtime_new').datebox("setValue",nowTime);
	
		 $("#checkall").click(
				  function(){
				    if(this.checked){
				        $("input[name='routecodecb']").prop('checked', true)
				    }else{
				        $("input[name='routecodecb']").prop('checked', false)
				    }
				  });
}

function initRow(orderdate,consignsor){
	$.ajax({
		url:baseURL+"/wms/outbound/getRouteCodeList.json?orderdate="+orderdate+"&consigsor="+consignsor, //此处fid无用
		beforeSend : function () {
			$.messager.progress({
				text : '加载中... 请稍候！',
			});
		},
		complete: function(){  
	        //AJAX请求完成时隐藏loading提示  
	        $.messager.progress('close');
	    },
		success : function(data) {
			 $(".gridtable tr").remove(".dynamic-tr");
			var listTmp = "<tr class='dynamic-tr' >";
             $.each(data, function(i, cust) {
            	 if(i != 0 && i % 7 ==0){  
            		 listTmp = listTmp +"</tr><tr class='dynamic-tr' >";
            	 }
            	 listTmp = listTmp +"<td><input type='checkbox' value="+cust.routecode+"-"+cust.totalqty+"-"+cust.orgCode+" name='routecodecb'>"+cust.routecode+"("+cust.totalqty+"条)</td>";
             });
             listTmp = listTmp +"</tr>";
           $(".gridtable tbody").append (listTmp);
           if(listTmp==""){
  	        	$.messager.alert('系统提示', '未查询到指定订单日期的订单，请重新选择订单日期！', 'warning');
  	           }
         },
         error: function(e) { 
          	} 
	});
}

/**
 * 生成出库单
 * @returns
 */
function toCKD()
{
	 var ct = 0;
	 var ps = "";
	  $('input[name="routecodecb"]:checked').each(function(){    
			  ps += "&routes="+$(this).val();
	  });    
	  //alert(chk_value.length==0 ?'你还没有选择任何内容！':chk_value);    
	  
	  if(ps=="" || ps.length==0){
			$.messager.alert('提示',"请选择你要生成出库单的车组",'info');
			return;
		}
	  var outtime = $("#outtime_new").val();
	  var orderdate = $("#orderdate_new").val();
	  var consignsor = $("#consignsor_new").val();
	  //if(consignsor=="0000"){
		//  $.messager.alert('提示',"请选择货主",'info');
		//	return;
	  //}
	  $.ajax({
			url:baseURL+"/wms/outbound/doSave.json?outtime="+outtime+"&orderdate="+orderdate+ps,//+"&consignsor="+consignsor, 
			success : function(data) {
				$('#add-dlg').dialog('close');
				$('#dataTabel').datagrid('reload'); 
				$.messager.show({
					title : '提示',
					msg :  '出库单'+data.msg+'！',
				});
	         },
	         error: function(e) { 
	        	 $.messager.show({
	 				title : '提示',
	 				msg :  '新增出错，请联系管理员!',
	 			});
	          	} 
		});
	  
	}

/**
 * 查看窗口
 */
  function viewD(){
	  $('#view-fm').form('clear');
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
		var outboundid = row.outboundid;
		row.orderdate=formatDatebox(row.orderdate);
		row.outtime=formatDatebox(row.outtime);
		if (row){
			$('#view-dlg').dialog('open').dialog('setTitle',"查看");
			$('#view-dlg').form('load',row);
		}
		
		getoutboundlinelist(outboundid);
	}
  
	//出库单明细列表
	  function getoutboundlinelist(outboundid)
	  {
	  		//获取领料新增分配的物资明细列表
	  	$('#outboundlinelist').datagrid({
	  		title:'出库单明细列表', //标题
	  		method:'post',
	  		iconCls:'icon-view', //图标
	  		singleSelect:false, //多选
	  		height:280, //高度
	  		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
	  		striped: true, //奇偶行颜色不同
	  		collapsible:false,//可折叠
	  		url:baseURL+"/wms/outbound/getOutboundLineList.json?outboundid="+outboundid, 
	  		sortName: 'id', //排序的列
	  		sortOrder: 'desc', //倒序
	  		remoteSort: true, //服务器端排序
	  		idField:'id', //主键字段
	  		singleSelect: true,
	  		pagination:false, //显示分页
	  		//pageSize : 1,
	  		rownumbers:true, //显示行号
	  		columns:[[
	  			{field:'cigarettename',title:'卷烟名称',width:$(this).width()*0.1,
	  				formatter:function(value,row,index){return row.cigarettename;}
	  			},
	  			{field:'cigarettecode',title:'卷烟编码',width:$(this).width()*0.1,sortable:true,
	  				formatter:function(value,row,index){return row.cigarettecode;} //需要formatter一下才能显示正确的数据
	  			},
	  			{field:'itemqty',title:'条烟数量',width:$(this).width()*0.1,sortable:true,
	  				formatter:function(value,row,index){return row.itemqty;} //需要formatter一下才能显示正确的数据
	  			},
	  			{field:'boxqty',title:'件烟数量',width:$(this).width()*0.1,sortable:true,
	  				formatter:function(value,row,index){return row.boxqty;}
	  			}
	  		]],
	  		onLoadSuccess:function(){
	  			//$('#inboundlinediv .panel-header').css('display','none'); 
	  			
	  		}
	  	});
	  	}
	  
	  
	//删除
		function deleterow(){
			var rows = $('#dataTabel').datagrid('getSelections');
			if(rows.length==0){
			$.messager.alert('提示',"请选择你要删除的用户",'info');
			return;
		}
			$.messager.confirm('提示','确定要删除吗?',function(result){
	        if (result){
	        	var ps = "";
	        	$.each(rows,function(i,n){
	        		if(i==0) 
	        			ps += "?id="+n.outboundid;
	        		else
	        			ps += "&id="+n.outboundid;
	        	});
	        	$.post(baseURL+'/wms/outbound/dodelete.json'+ps,function(data){
		        	$('#dataTabel').datagrid('reload'); 
		        	//console.log("del--"+data);
	        		$.messager.show({
						title : '提示',
						msg :  data.total+'个信息被删除'+data.msg+'！',
					});
	        	});
	        }
	    });
		}
