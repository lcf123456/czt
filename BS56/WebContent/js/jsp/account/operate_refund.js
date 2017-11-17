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
jQuery(function(def){
	var begdate=getDateYM01();
	var enddate=getDateYMD();
	$('#begdate').datebox('setValue', begdate);
	$('#enddate').datebox('setValue', enddate);
	$("#routecode").combobox({
	    url : baseURL+"/comm/combobox/getRoutesComboboxByDeptid.json?deptid=",//返回json数据的url
	    valueField : "routecode",//这个id和你返回json里面的id对应
	    textField : "routename", //这个text和你返回json里面的text对应
	    loadFilter : function (data) {
	        if (data && data instanceof Array) {
	            data.splice(0, 0, {routecode: '', routename: '请选择车组'});　
	        }
	        return data;
	    },
        onChange: function (n,o) {
			 //console.log("new="+n+",old="+o);
			 search();
		}   	
	})
	$('#keywd').textbox('textbox').keydown(function(e){
		if(e.keyCode==13){
			search();
		}
	})
	$('#dataTable').datagrid({
		//title:'车组退货', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选,当true时只允许当前选择一行。		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠		url:baseURL+"/account/operate/getOperatePageList.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'desc', //正序asc、倒序 desc
		remoteSort: false, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 10,
		pageList: [10,30,50],
		queryParams:{
			ctype:'10',
			//status:'10',
			begdate:begdate,
			enddate:enddate
		}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'routecode',title:'车组',width:10,sortable:false,
				formatter:function(value,row,index){return row.routecode;} //需要formatter一下才能显示正确的数据
			},
			{field:'cuscode',title:'专卖证号',width:20,sortable:false,
				formatter:function(value,row,index){return row.cuscode;}
			},
			{field:'cusname',title:'零售户名',width:30,
				formatter:function(value,row,index){return row.cusname;}
			},
			{field:'quantity',title:'条数',width:10,
				formatter:function(value,row,index){return row.quantity;}
			},
			{field:'amount',title:'金额',width:10,
				formatter:function(value,row,index){return row.amount;}
			},
			{field:'reasoncontent',title:'退货原因',width:30,
				formatter:function(value,row,index){return row.reasoncontent;}
			},
			{field:'orderdate',title:'订单日期',width:10,
				formatter:function(value,row,index){return row.orderdate.substring(0,10);}
			},
			{field:'createname',title:'记录人',width:10,
				formatter:function(value,row,index){return row.createname;}
			},
			{field:'prestatuscontent',title:'结算状态',width:10,
				formatter:function(value,row,index){return row.prestatuscontent;}
			},
			{field:'statuscontent',title:'审核状态',width:10,
				formatter:function(value,row,index){return row.statuscontent;}
			}
	
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题			//$('#tabdiv .panel-header').css('display','none');
		}
	});
});

/**
 * 打开新增窗口
 */
function openNew(){
	$('#add-fm').form('clear');
	$('#add-dlg').dialog('open').dialog('setTitle','车组退货新增');
	$('#ctype').val("10");
	$('#status').val("10");
	$("#routecode1").combobox({
	    url : baseURL+"/comm/combobox/getRoutesComboboxByDeptid.json?deptid=",//返回json数据的url
	    valueField : "routecode",//这个id和你返回json里面的id对应
	    textField : "routename", //这个text和你返回json里面的text对应
	    required:true,
	    loadFilter : function (data) {
	        if (data && data instanceof Array) {
	            data.splice(0, 0, {routecode: '', routename: '--请选择车组--'});　
	        }
	        return data;
	    }
	})
	$("#reasonid").combobox({
		url:baseURL+"/sys/basetypeInfo/getBasetypeInfoByTypecode.json?typecode=RREASON", //数据来源
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
	var orderdate="",deliverydate="";
	var nowTime=getDateYMD();
	$.ajax({
		url:baseURL+"/account/timebydm/getTimebydm.json?type=0&date="+nowTime, 
		success : function(data) {
			orderdate=data.timebydm;
			//alert(orderdate);
			$('#orderdate').datebox("setValue",orderdate);
			$('#calcdate').datebox("setValue",nowTime);
			//$('#orderdate').datebox("setValue","2017-09-07");
//			$.ajax({
//				url:baseURL+"/account/timebydm/getTimebydm.json?type=1&date="+orderdate, 
//				success : function(data) {
//					deliverydate=data.timebydm;
//					$('#calcdate').datebox("setValue",deliverydate);
//				}
//			});
         },
	});
	initOrderTable();
}

function initOrderTable(){
		var begdate=getDateYM01();
		var enddate=getDateYMD();
		$('#orderDataTable').datagrid({
			//title:'退货入库', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:true, //多选,当true时只允许当前选择一行。
			height:220, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同

			collapsible:true,//可折叠
			url:baseURL+"/account/operate/getShiporderByCondition.json", //数据来源
			remoteSort: false, //服务器端排序
			idField:'orderno', //主键字段
			//pageSize : 1,
			rownumbers:true, //显示行号
			columns:[[
				//{field:'orderno',checkbox:true,width:2}, //显示复选框
				{field:'customerId',title:'专卖证号',width:20,sortable:false,
					formatter:function(value,row,index){return row.customerId;}
				},
				{field:'customercode',title:'客户代码',width:20,sortable:false,
					formatter:function(value,row,index){return row.customercode;}
				},
				{field:'customername',title:'零售名称',width:30,
					formatter:function(value,row,index){return row.customername;}
				},
				{field:'totalqty',title:'条数',width:10,
					formatter:function(value,row,index){return row.totalqty;}
				},
				{field:'totalamount',title:'金额',width:10,
					formatter:function(value,row,index){return row.totalamount;}
				}
			]],
			toolbar:'#ordertoolbar',
			onLoadSuccess:function(){
				$('#orderDataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				$("#searchForm").form("clear");
				//$('#tabdiv .panel-header').css('display','none');
			},
			onSelect:function(rowIndex, row){  
				$('#quantity').numberbox("setValue",row.totalqty);
				$('#amount').numberbox("setValue",row.totalamount);
				$('#orderno').val(row.orderno);
				var prestatus=row.paymentflag;
				if(prestatus=="0")prestatus="20";//未结算
				else prestatus="10";//已结算
				$('#prestatus').val(prestatus);
	        }
		});
}

	  //查询
	function search(){
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
	
	//清空查询条件
	function clearForm(){
		$('#keywd').textbox("setValue","");
		$('#keywd').textbox("setText","");
		$('#routecode').combobox("select","");
		
		var begdate=getDateYM01();
		var enddate=getDateYMD();
		$('#begdate').datebox('setValue', begdate);
		$('#enddate').datebox('setValue', enddate);
		
		search();
	}
	
	function saveAdd(){
	  	$('#add-fm').form('submit',{
	  		onSubmit: function(){
	  			var isValidate = $(this).form('validate');
	  			if(isValidate){
	  				var routecode=$('#routecode1').combobox("getValue");
	  				if(routecode==""){
	  					$.messager.alert('提示',"请选择车组信息",'info');
	  					return false;
	  				}
	  				var reasonid=$('#reasonid').combobox("getValue");
	  				if(reasonid==""){
	  					$.messager.alert('提示',"请选择车组退货原因",'info');
	  					return false;
	  				}
	  				var quantity=$('#quantity').numberbox("getValue");
	  				if(quantity==""){
	  					$.messager.alert('提示',"请选择要退货的订单",'info');
	  					return false;
	  				}
	  			}
	  			return isValidate;
	  		},
	  		url:baseURL+"/account/operate/doOperateAdd.json",
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
					msg :  data.total+'个车组退货新增'+data.msg+'！',
				});
				//$('#loading').window('close');
			}
	  	});
	  }
	  
	
	function delOperate(){
		var rows = $('#dataTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择要删除的退货信息",'info');
			return;
		}
		$.messager.confirm('提示','确定要删除所选退货信息吗?',function(result){
			if (result){
				var ids = "";
				$.each(rows,function(i,n){
					if(i==0){ 
						ids += "?id="+n.id;
					}else{
						ids += "&id="+n.id;
					}	
				});
				$.post(baseURL+'/account/operate/doDelOperate.json'+ids,function(data){
					$('#dataTable').datagrid('reload'); 
					//console.log("del--"+data);
					data = eval('('+data+')');
					$.messager.show({
						title : '提示',
						msg :  data.total+'个退货信息删除'+data.msg+'！',
					});
				});
			}
		})
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