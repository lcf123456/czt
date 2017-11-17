/**
 * 装卸效率
 */

	var nowTime = getDateYMD();
jQuery(function($){
	
		//$('#orderdate').datebox("setValue",nowTime);
		initTextbox();
		$('#keywd').textbox('textbox').keydown(function(e){
			if(e.keyCode==13){
				searchData();
			}
		})

	$('#dataTabel').datagrid({
		title:'预付退货', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //单选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/account/prepay/getPrepayreturn.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		//pageNumber: 1, 
		//pageSize : 20,
		//pageList: [20,30,50],
		queryParams:{
			}, //查询条件
		pagination:false, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			
			{field:'cuscode',title:'专卖证',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.cuscode;}
			},
			{field:'cusname',title:'客户名称',width:$(this).width()*0.2,
				formatter:function(value,row,index){return row.cusname;}
			},
			{field:'routecode',title:'线路',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.routecode;}
			},
			{field:'amount',title:'消费金额',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.amount;}
			},
			{field:'quantity',title:'条数',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.quantity;}
			},
			{field:'orderdate',title:'订单日期',width:$(this).width()*0.1,
				formatter:formatDatebox
			},
			{field:'statusname',title:'退货状态',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.statusname;}
			}
			,
			{field:'_operate',title:'操作',width:$(this).width()*0.1,
				formatter:formatOper
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none'); 
			
		}
	});
});

function formatOper(val,row,index){  
    return '<a href="#" onclick="viewD('+index+')">查看</a>';  
}  
  
function initTextbox(){
	//初始订单日期
	$.ajax({
		url:baseURL+"/account/timebydm/getTimebydm.json?type=0&date="+nowTime, //此处fid无用
		success : function(data) {
			$('#orderdateStart').datebox("setValue",data.timebydm);
			$('#orderdateEnd').datebox("setValue",data.timebydm);
			//initRow(orderdate,consignsor);
         },
         error: function(e) { 
        	 $('#orderdateStart').datebox("setValue",nowTime);
        	 $('#orderdateEnd').datebox("setValue",nowTime);
        	 //initRow(orderdate,consignsor);
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
    $('#keywd').textbox("setText","");
    $('#keywd').textbox("setValue","");
	searchData();
}



/**
 * 查看窗口
 */
  function viewD(index){
	 // var orderNo = 'CS08352434';
	  
	  $('#add-fm').form('clear');
	  $('#dataTabel').datagrid('selectRow', index);  
	  var row = $('#dataTabel').datagrid('getSelected');
		
		var orderNo = row.orderno;
		$.ajax({ 
		    url: baseURL+'/wms/shiporder/getShiporderByNo.json?orderNo='+orderNo, 
		    type: 'POST', 
		    success: function(data){
		    	$('#add-dlg').dialog('open').dialog('setTitle',"查看");
				$('#add-dlg').form('load',data);
				getsplapplylistline(orderNo);
			}
		   }); 
		
		
	}
  
  function getsplapplylistline(orderNo)
  {
  		//获取领料新增分配的物资明细列表
  	$('#listdataTabel').datagrid({
  		title:'入库明细', //标题
  		method:'post',
  		iconCls:'icon-view', //图标
  		singleSelect:false, //多选
  		height:270, //高度
  		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
  		striped: true, //奇偶行颜色不同
  		collapsible:false,//可折叠
  		url:baseURL+"/wms/shiporder/getShiporderlineByNo.json?orderNo="+orderNo, 
  		sortName: 'id', //排序的列
  		sortOrder: 'desc', //倒序
  		remoteSort: true, //服务器端排序
  		idField:'id', //主键字段
  		pagination:false, //显示分页
  		//pageSize : 1,
  		rownumbers:true, //显示行号
  		columns:[[
  			{field:'itemname',title:'品牌',width:$(this).width()*0.15,
  				formatter:function(value,row,index){return row.itemname;}
  			},
  			{field:'qty',title:'数量',width:$(this).width()*0.1,
  				formatter:function(value,row,index){return row.qty;} //需要formatter一下才能显示正确的数据
  			},
  			{field:'itemprice',title:'单价',width:$(this).width()*0.1,
  				formatter:function(value,row,index){return row.itemprice;}
  			},
  			{field:'saleamount',title:'总金额',width:$(this).width()*0.1,
  				formatter:function(value,row,index){return row.saleamount;}
  			}
  		]],
  		onLoadSuccess:function(){
  			$('#listtabdiv .panel-header').css('display','none'); 
  		}
  	});
  }

