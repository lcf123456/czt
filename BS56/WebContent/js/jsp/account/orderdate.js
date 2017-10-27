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
	var begdate=$('#begdate').val();	 
	var enddate=$('#enddate').val();
	$('#dataTable').datagrid({
		title:'订单配送日期', //标题
		method:'post',
		iconCls:'icon-edit', //图标
singleSelect:false, //多选

        
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。


		striped: true, //奇偶行颜色不同

		collapsible:true,//可折叠


		url:baseURL+"/account/timebydm/getTimebydmdateList.json", //数据来源
		
		sortName: 'id', //排序的列
		sortOrder: 'desc', //正序asc、倒序 desc
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 10,
		pageList: [10,30,50],
		queryParams:{
			begdate,enddate
			}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'orderdate',title:'订单日期',width:30,sortable:false,
				formatter:function(value,row,index){
					       var date = new Date(value);
				            var year = date.getFullYear().toString();
				            var month = (date.getMonth() + 1);
				            var day = date.getDate().toString();
				  if (month < 10) {
					                month = "0" + month;
					            }
				 if (day < 10) {
					                day = "0" + day;
					            }
					 return year + "-" + month + "-" + day ; } //需要formatter一下才能显示正确的数据
			},
			{field:'deliverydate',title:'配送日期',width:30,
			formatter:function(value,row,index){
				        var date = new Date(value);
			            var year = date.getFullYear().toString();
			            var month = (date.getMonth() + 1);
			            var day = date.getDate().toString();
			  if (month < 10) {
				                month = "0" + month;
				            }
			 if (day < 10) {
				                day = "0" + day;
				            }
				 return year + "-" + month + "-" + day ; }
		},
	
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none');
		}
	});
});
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
			$.messager.alert('提示',"请选择你要更新的订单配送日期信息",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一条订单配送日期进行更新",'info');
			return;
		}
		var row = $('#dataTable').datagrid('getSelected');
		if (row){
			$('#edit-dlg').dialog('open').dialog('setTitle','修改订单配送日期');
			$('#edit-fm').form('load',row);
			//alert(row.id);
			//url = '/BS56/sys/roleNew.json';
		}

	}
  
  /**
   * 保存修改的信息

   */
  function saveEdit(){
	 // alert('---');
  	$('#edit-fm').form('submit',{
  		onSubmit: function(){
			var isValidate = $(this).form('validate');
			 //alert('11');
			if(isValidate){ 
				//alert('33');
				//$('#loading').window('open');
			}
			return isValidate;	
		},
  		url:baseURL+"/account/timebydm/doupdateOrderdate.json",
		data:$("#edit-fm").serializeArray(),
		beforeSend : function () {
			$.messager.progress({	
				text : '正在修改中...',
			});		 
		},
  		success: function(data){
  			//alert('44');	
			//$('#loading').window('close');
  			//alert(data);
  		data = eval('('+data+')');
			$('#edit-dlg').dialog('close');
			$('#dataTable').datagrid('reload'); 
  		$.messager.show({
				title : '提示',
				msg :  data.total+'个订单配送日期修改'+data.msg+'！',
			});
		}
  	});
  }
	  //查询
	function searchOrderdate(){
		//alert("------");
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
		$('#queryForm').form('reset');

		searchOrderdate();
	}
	
	
       
       
    