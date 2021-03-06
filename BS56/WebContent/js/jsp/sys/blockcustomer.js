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

	$('#custcode').textbox('textbox').keydown(function(e){
			if(e.keyCode==13){
				searchBlockcustomer();
			}
		})
	$('#dataTable').datagrid({
		title:'零售户异动', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
        
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。

		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠

		url:baseURL+"/sys/blockcustomer/getBlockCustomers.json", //数据来源
		
		sortName: 'id', //排序的列
		sortOrder: 'desc', //正序asc、倒序 desc
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 10,
		pageList: [10,30,50],
		queryParams:{
			}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'custname',title:'零售户名称',width:25,
				formatter:function(value,row,index){return row.custname;} //需要formatter一下才能显示正确的数据
			},
			{field:'custid',title:'专卖证号',width:15,
				formatter:function(value,row,index){return row.custid;}
			},
			{field:'contacter',title:'联系人',width:8,
				formatter:function(value,row,index){return row.contacter;}
			},
			{field:'telnum',title:'联系电话',width:10,
				formatter:function(value,row,index){return row.telnum;}
			},
			{field:'routecode',title:'送货车组',width:10,
				formatter:function(value,row,index){return row.routecode;}
			},
			{field:'reasonname',title:'原因',width:20,
				formatter:function(value,row,index){return row.reasonname;}
			},
			{field:'handleflag',title:'处理标志',width:8,
				formatter: function(value,row,index){
				if(row.handleflag == '未处理'){
					return '<span style="color:red;">'+row.handleflag +'</span>';
				}else{
				return row.handleflag;
				}}  
				
			},
			{field:'handledate',title:'处理时间',width:15,
				formatter:function(value,row,index){return row.handledate;}
				
			},
		
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题			$('#tabdiv .panel-header').css('display','none');

		},
		onDblClickCell:function(index,field,value){
			openView();
		}
	});
});

/**
 * 打开零售户异动信息新增窗口
 */
function newadd(){
	$('#add-dlg').dialog('open').dialog('setTitle','零售户异动信息');
	$('#add-fm').form('reset');
	var nowTime = getDateYMD();
	 $('#blocktimerange').datebox('setValue',nowTime );
	$("#reasonid").combobox({
		url : baseURL+"/comm/combobox/getComboboxByTypeCode.json?typeCode=BLOCKREASON",//返回json数据的url
		valueField : "id",//这个id和你返回json里面的id对应
		textField : "contentlist", //这个text和你返回json里面的text对应
		//panelHeight: 'auto'
		   onLoadSuccess : function(){ 
			 $("#reasonid").combobox('setValue', '432');
		  $("#reasonid").combobox('setText', '门面不存在（拆除、搬迁、歇业）');
		}
	})
$("#routecode").combobox({
		url : baseURL+"/comm/combobox/getRoutesComboboxByDeptid.json?deptid=",//返回json数据的url
		valueField : "routecode",//这个id和你返回json里面的id对应
		textField : "routecode", //这个text和你返回json里面的text对应
		//panelHeight: 'auto'
		   onLoadSuccess : function(){         
			$("#routecode").combobox('setText', row.routecode);
		}
		})
}


/**
 * 保存新建零售户异动信息
 */
function saveNew(){


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
		url:baseURL+"/sys/blockcustomer/doBlockCustomerNew.json",
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
				msg :  data.total+'个异动新增'+data.msg+'！',
			});
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
			$.messager.alert('提示',"请选择你要查看的零售户异动信息",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一条零售户异动信息进行查看",'info');
			return;
		}
		viewRow = $('#dataTable').datagrid('getSelected');
		//alert(viewRow);
		if (viewRow!=null){
			$('#view-dlg').dialog('open').dialog('setTitle','零售户异动信息');
			$("#id").attr("value",viewRow.id);	
			$("#v-blockcustomerscn").html(viewRow.blockcustomerscn);
			$("#v-blockcustomerno").html(viewRow.blockcustomerno);
			//var typec=viewRow.vehicletype;
			//if(typec==null)typec="";
			$("#v-blockcustomertype").html(viewRow.blockcustomertype);
			$("#v-regload").html(viewRow.regload);
			$("#v-register" ).html(viewRow.register);
		    //*因日期为空会导致后面数据不显示，需重新定义日期截取*
		    //$("#v-buydate").html(viewRow.buydate.substring(0,10));
			$("#v-blocktimerange").html(viewRow.blocktimerange);
		   
			$("#v-custid").html(viewRow.custid);
			$("#v-contacter").html(viewRow.contacter);
			$("#v-custname").html(viewRow.custname);
			$("#v-deliveryaddr").html(viewRow.deliveraddr);
			$("#v-telnum").html(viewRow.telnum);
			$("#v-reasonname").html(viewRow.reasonname);
			$("#v-remarks").html(viewRow.remarks);
			$("#v-deliveryaddr").html(viewRow.deliveryaddr);
			$("#v-routecode").html(viewRow.routecode);
			$("#v-handleflag").html(viewRow.handleflag);
			$("#v-createname").html(viewRow.createname);
			$("#v-handlename").html(viewRow.handlename);
			$("#v-handlesitu").html(viewRow.handlesitu); 
			$("#v-actualsitu").html(viewRow.actualsitu);
			$("#v-handledate").html(viewRow.handledate);
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
	    $('#edit-fm').form('reset');
	    $(".formtd").each(function(){
		 	   $(this).html("");
		 	 });
		var rows = $('#dataTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要更新的零售户异动信息",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一条零售户异动信息进行更新",'info');
			return;
		}
		var row = $('#dataTable').datagrid('getSelected');
		if (row){
			$('#edit-dlg').dialog('open').dialog('setTitle','零售户异动信息');			
			$('#edit-fm').form('load',row);
			//url = '/BS56/sys/roleNew.json';
		}
		
			
		$("#reasonid1").combobox({
			url : baseURL+"/comm/combobox/getComboboxByTypeCode.json?typeCode=BLOCKREASON",//返回json数据的url
			valueField : "id",//这个id和你返回json里面的id对应
			textField : "contentlist", //这个text和你返回json里面的text对应
			//panelHeight: 'auto'
			   onLoadSuccess : function(){         
				$("#reasonid1").combobox('setText', row.reasonname);
			}
		})
		$("#routecode1").combobox({
		url : baseURL+"/comm/combobox/getRoutesComboboxByDeptid.json?deptid=",//返回json数据的url
		valueField : "routecode",//这个id和你返回json里面的id对应
		textField : "routecode", //这个text和你返回json里面的text对应
		//panelHeight: 'auto'
		   onLoadSuccess : function(){         
			$("#routecode1").combobox('setText', row.routecode);
		}
		})
	}
  
  /**
   * 保存修改的信息

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
			url:baseURL+"/sys/blockcustomer/doBlockCustomerUpdate.json",
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
					msg :  data.total+'个异动信息已修改'+data.msg+'！',
				});
				$('#loading').window('close');
			}
		});
	}
  
  /**
   * 打开处理窗口
   */
    function openhandle(){   
  	    $('#handle-fm').form('clear');
  		var rows = $('#dataTable').datagrid('getSelections');
  		if(rows.length==0){
  			$.messager.alert('提示',"请选择你要处理的零售户异动信息",'info');
  			return;
  		}
  		if(rows.length > 1){
  			$.messager.alert('提示',"只能选择一条零售户异动信息进行处理",'info');
  			return;
  		}
  		var row = $('#dataTable').datagrid('getSelected');
	  	if (row) {
			if (row.handleflag == '未处理' || row.handleflag == null) {
				$('#handle-dlg').dialog('open').dialog('setTitle', '零售户异动信息');
				$('#handle-fm').form('load', row);
				$('#handlename').textbox('setValue', handlerid);
				$('#handlename').textbox('setText', handlername);
			} else {
				$.messager.alert('提示', "此零售户异动信息已处理完成", 'info');
				return;
			}
  		}
  		$("#reasonid2").combobox({
  			url : baseURL+"/comm/combobox/getComboboxByTypeCode.json?typeCode=BLOCKREASON",//返回json数据的url
  			valueField : "id",//这个id和你返回json里面的id对应
  			textField : "contentlist", //这个text和你返回json里面的text对应
  			//panelHeight: 'auto'
  			   onLoadSuccess : function(){         
  				$("#reasonid2").combobox('setText', row.reasonname);
  			}
  		})
  	}
    
    /**
     * 保存处理的信息


     */
    function handleEdit(){
		$('#handle-fm').form('submit',{
			onSubmit: function(){
				var isValidate = $(this).form('validate');
				if(isValidate){
					//$('#loading').window('open');
				}
				return isValidate;
			},
			url:baseURL+"/sys/blockcustomer/doBlockCustomerHandle.json",
			data:$("#handle-fm").serializeArray(),
			beforeSend : function () {
				$.messager.progress({
					text : '正在处理中...',
				});
			},
			success: function(data){
				//$('#loading').window('close');
				data = eval('('+data+')');
				$('#handle-dlg').dialog('close');
				$('#dataTable').datagrid('reload'); 
	    		$.messager.show({
					title : '提示',
					msg :  data.total+'个异动信息已处理'+data.msg+'！',
				});
				$('#loading').window('close');
			}
		});
	}
//删除
	function deleterow(){
		var rows = $('#dataTable').datagrid('getSelections');
		if(rows.length==0){
		$.messager.alert('提示',"请选择你要删除的异动信息",'info');
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
        	$.post(baseURL+'/sys/blockcustomer/doBlockCustomerDel.json'+ps,function(data){
	        	$('#dataTable').datagrid('reload'); 
	        	//console.log("del--"+data);
        		$.messager.show({
					title : '提示',
					msg :  data.total+'个异动信息'+data.msg+'！',
				});
        	});
        }
    });
	}
	  //查询
	function searchBlockcustomer(){
		//alert("------");
		var params = $('#dataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		//alert(params.length);
		//alert(fields.length);
		$.each( fields, function(i, field){
			//alert(field.name);
			//alert(field.value);
			params[field.name] = field.value; //设置查询参数
		}); 
		//alert("------");
		$('#dataTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了

	}
	
	 
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		searchBlockcustomer();
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
	//查找零售户
	function searchBlockcustomer1(){
		
		var condname= $("#condname").val(); 
		
  	
  		if(condname==null||condname=="")
  			{
  			$.messager.alert('系统提示', '请输入查询信息！', 'warning');
  			return;
  			}
  		//根据车组返回零售户信息getCustListByRouteCode
  		$.ajax({
  			url:baseURL+"/sq/complaint/getCustListByRouteCode.json?condname="+condname, //数据来源
  			success : function(data) {
  				 $(".gridtable tr").remove(".dynamic-tr");
  				var listTmp = "";
  	             $.each(data, function(i, cust) {
  	            	listTmp = listTmp +"<tr class='dynamic-tr' ><td>"+cust.name+"</td><td>"+cust.id+"</td><td>"+cust.contact +"</td><td>"+cust.contactphone+"</td>"+
  	            	"<td><input name='rad' id=rad"+i+" type='radio' onClick=radClick('"+cust.id+"','"+cust.name+"','"+cust.contact+"','"+cust.contactphone+"','"+cust.contactaddress+"','"+cust.routecode+"') value="+cust.id+"></td></tr>";        	
  	             });
  	           $(".gridtable tbody").append (listTmp);
  	         },
  	         error: function(e) { 
  	          	} 
  		})
  		
	}
	function radClick(id,name,contact,tel,addr,routecode){
		$('#custid').val(id);
        $('#custname').val(name);
		$('#contacter').val(contact);
		$('#telnum').val(tel);
		$('#deliveryaddr').val(addr);
		$('#routecode').val(routecode);
		if(routecode!=null&&routecode!=""&&routecode!="---请选择---")
			{
			initRoutePerson(routecode);
			}
		else{
			$.messager.alert('系统提示', '请选择车组！', 'warning');
			return;
			
		}
  		
}