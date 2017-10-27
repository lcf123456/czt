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
 * 页面列表datagrid初始化 */
jQuery(function(def){
	var orderdate="";
	var nowTime=getDateYMD();
	$.post(baseURL+'/account/timebydm/getTimebydm.json?type=0&date='+nowTime,function(data){
		//alert(data);
		orderdate=data.timebydm;
		$('#orderdate').datebox("setValue",orderdate);
		//alert(orderdate);
	});
	$('#dataTable').datagrid({
		//title:'退货入库', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //多选,当true时只允许当前选择一行。		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠		url:baseURL+"/account/accounttotal/getAccountTotalPageList.json", //数据来源
		queryParams:{
			orderdate:$('#orderdate').datebox("getValue")
			}, //查询条件
		pagination:false, //显示分页
		//pageSize : 1,
		showFooter: true,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'routecode',title:'车组',width:10,
				formatter:function(value,row,index){return row.routecode;} //需要formatter一下才能显示正确的数据
			},
			{field:'saleamount',title:'销售额(元)',width:10,
				formatter:function(value,row,index){return row.saleamount;}
			},
			{field:'onlineamount',title:'在线代扣(元)',width:10,
				formatter:function(value,row,index){return row.onlineamount;}
			},
			{field:'prepayamount',title:'预收款(元)',width:10,
				formatter:function(value,row,index){return row.prepayamount;}
			},
			{field:'cardamount',title:'刷卡金额(元)',width:10,
				formatter:function(value,row,index){return row.cardamount;}
			},
			{field:'cashamount',title:'现金金额(元)',width:10,
				formatter:function(value,row,index){return row.cashamount;}
			},
			{field:'returnamount',title:'退货(元)',width:10,
				formatter:function(value,row,index){return row.returnamount;}
			},
			{field:'tempstorageamount',title:'暂存1(元)',width:10,
				formatter:function(value,row,index){return row.tempstorageamount;}
			},
			{field:'deliveramount',title:'暂存送货1(元)',width:10,
				formatter:function(value,row,index){return row.deliveramount;}
			},
			{field:'tempstorageamount2',title:'暂存2(元)',width:10,
				formatter:function(value,row,index){return row.tempstorageamount2;}
			},
			{field:'deliveramount2',title:'暂存送货2(元)',width:10,
				formatter:function(value,row,index){return row.deliveramount2;}
			},
			{field:'otheramount',title:'其它金额(元)',width:10,
				formatter:function(value,row,index){return row.otheramount;}
			}
	
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题			//$('#tabdiv .panel-header').css('display','none');
			$('#dataTable').datagrid('reloadFooter',[{
				routecode: '<B>合计</B>',
				todayarrears: '<B>'+compute("todayarrears")+'</B>',
				saleamount: '<B>'+compute("saleamount")+'</B>',
				onlineamount: '<B>'+compute("onlineamount")+'</B>',
				prepayamount: '<B>'+compute("prepayamount")+'</B>',
				cardamount: '<B>'+compute("cardamount")+'</B>',
				cashamount: '<B>'+compute("cashamount")+'</B>',
				returnamount: '<B>'+compute("returnamount")+'</B>',
				tempstorageamount: '<B>'+compute("tempstorageamount")+'</B>',
				deliveramount: '<B>'+compute("deliveramount")+'</B>',
				tempstorageamount2: '<B>'+compute("tempstorageamount2")+'</B>',
				deliveramount2: '<B>'+compute("deliveramount2")+'</B>',
				otheramount: '<B>'+compute("otheramount")+'</B>'
			}]);
		}
	});
});

//指定列求和
function compute(colName) {
     var rows = $('#dataTable').datagrid('getRows');
     var total = 0;
     for (var i = 0; i < rows.length; i++) {
         total = Math.round((total+parseFloat(rows[i][colName]))*10000)/10000;
     }
     return total;
 }


	  //查询
	function searchData(){
		var params = $('#dataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#dataTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了	}
	
	//查询
	function searchOrder(){
		var routecode=$('#routecode1').combobox("getValue");
		if(routecode==null||routecode==""){
			$.messager.alert('提示',"请先选择车组信息",'info');
			return;
		}
		var orderdate=$('#orderdate').datebox("getValue");
		$('#routecode2').val(routecode);
		$('#orderdatestr').val(orderdate);
		var params = $('#orderDataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数		var fields =$('#searchForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#orderDataTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了	}
	
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
	
