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

jQuery(function($){
	$("#ctype").combobox({
		url : baseURL+"/safe/hiddendanger/getCtypeCombobox.json",//返回json数据的url
    	valueField : "id",//这个id和你返回json里面的id对应
    	textField : "ctype", //这个text和你返回json里面的text对应
    	loadFilter : function (data) {
	        if (data && data instanceof Array) {
	            data.splice(0, 0, {id: '', ctype: '--请选择--'});　// 此处空格用全角
	        }
	        return data;
	    }   
	})
	var statusval=$("#status").val();
	$("#status").combobox({
        onChange: function (n,o) {
        	searchDangerrectify();
		}
	})
	
$('#dangercontents').textbox('textbox').keydown(function(e){
		if(e.keyCode==13){
			searchDangerrectify();
		}
	})
	$('#dataTable').datagrid({
		title:'隐患整改', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		url:baseURL+"/safe/hiddendanger/getDangerrectify.json",  //数据来源
        collapsible:true,
         //data:data,
		queryParams:{
			status:statusval,
			}, //查询条件
		pagination:true, //显示分页
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'dangercontent',title:'隐患内容',width:30,sortable:false,
				formatter:function(value,row,index){return row.dangercontent;} //需要formatter一下才能显示正确的数据
			},
			{field:'ctype',title:'隐患类别',width:10,sortable:false,
				formatter:function(value,row,index){return row.ctype;}
			},
			{field:'dangerdate',title:'隐患日期',width:10,sortable:false,
				formatter:function(value,row,index){return row.dangerdate.substring(0,10); }  
			},
			{field:'status',title:'排除标志',width:10,sortable:false,
				formatter:function(value,row,index){
					return row.status;
					} 
			},
			{field:'dangerstatus',title:'隐患标志',width:10,sortable:false,
				formatter:function(value,row,index){
					return row.dangerstatus;
					} 
			},
			
			
		
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none');
		},
		onDblClickCell:function(index,field,value){
			openView();
		}
	});
});
	  //查询
	function searchDangerrectify(){
		var params = $('#dataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		//alert(params.length);
		//alert(fields.length);
		$.each( fields, function(i, field){
			//alert(field.name);
			//alert(field.value);
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#dataTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		$('#queryForm').form('reset');
		searchDangerrectify();
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
				$.messager.alert('提示',"请选择你要查看的隐患记录信息",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条隐患记录信息进行查看",'info');
				return;
			}
			viewRow = $('#dataTable').datagrid('getSelected');
			//alert(viewRow);
			if (viewRow!=null){
				$('#view-dlg').dialog('open').dialog('setTitle','查看隐患记录信息');
				$("#id").attr("value",viewRow.id);	
				$("#v-dangercreatescn").html(viewRow.dangercreatescn);
				$("#v-dangercreateno").html(viewRow.dangercreateno);
				//var typec=viewRow.vehicletype;
				//if(typec==null)typec="";
				$("#v-dangercreatetype").html(viewRow.dangercreatetype);
				$("#v-regload").html(viewRow.regload);
				$("#v-register" ).html(viewRow.register);
			    //*因日期为空会导致后面数据不显示，需重新定义日期截取*
			    //$("#v-buydate").html(viewRow.buydate.substring(0,10));
				$("#v-dangertimerange").html(viewRow.dangertimerange);
			   
				$("#v-dangerdate").html(viewRow.dangerdate.substring(0,10));
				$("#v-ctype").html(viewRow.ctype);
				$("#v-createname").html(viewRow.createname);
				$("#v-dangercontent").html(viewRow.dangercontent);
				$("#v-verifyname").html(viewRow.verifyname);
				$("#v-dangerstatus").html(viewRow.dangerstatus);
				$("#v-deptname").html(viewRow.deptname);
				$("#v-verifydate").html(viewRow.verifydate.substring(0,10));
				$("#v-checkid").html(viewRow.checkid);
				$("#v-demands").html(viewRow.demands);
				$("#v-rectifyname").html(viewRow.rectifyname);
				$("#v-status").html(viewRow.status);
				$("#v-rectifynote").html(viewRow.rectifynote); 
				
			}
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
	  			$.messager.alert('提示',"请选择你要更新的隐患整改信息",'info');
	  			return;
	  		}
	  		if(rows.length > 1){
	  			$.messager.alert('提示',"只能选择一条隐患整改信息进行更新",'info');
	  			return;
	  		}
	  		var row = $('#dataTable').datagrid('getSelected');
	  		if (row){
	  			$('#edit-dlg').dialog('open').dialog('setTitle','修改隐患整改信息');
	  			$('#edit-fm').form('load',row);
	  			
	  		  
	  			//url = '/BS56/sys/roleNew.json';
	  		}
	  		
	  		
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
	  			url:baseURL+"/safe/hiddendanger/doHangerrectifyUpdate.json",
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
	  					msg :  data.total+'个信息已修改'+data.msg+'！',
	  				});
	  				$('#loading').window('close');
	  			}
	  		});
	  	}
	    
	  //删除
		function deleterow(){
			var rows = $('#dataTable').datagrid('getSelections');
			if(rows.length==0){
			$.messager.alert('提示',"请选择你要删除的隐患整改信息",'info');
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
	        	$.post(baseURL+'/safe/hiddendanger/doHangerrectifyDel.json'+ps,function(data){
		        	$('#dataTable').datagrid('reload'); 
		        	//console.log("del--"+data);
	        		$.messager.show({
						title : '提示',
						msg :  data.total+'个隐患整改信息'+data.msg+'！',
					});
	        	});
	        }
	    });
		}
		/**
		   * 打开审核窗口
		   */
		    function openhandle(){  
		    	
		    	var nowTime = getDateYMD();
			  	 $('#verifydate').datebox('setValue',nowTime );
		  	    $('#handle-fm').form('clear');
		  		var rows = $('#dataTable').datagrid('getSelections');
		  		if(rows.length==0){
		  			$.messager.alert('提示',"请选择你要处理的信息",'info');
		  			return;
		  		}
		  		if(rows.length > 1){
		  			$.messager.alert('提示',"只能选择一条整改信息进行处理",'info');
		  			return;
		  		}
		  		var row = $('#dataTable').datagrid('getSelected');
			  	if (row) {
					if (row.handlestatus == '20' ) {
						$('#handle-dlg').dialog('open').dialog('setTitle', '隐患整改信息');
						$('#handle-fm').form('load', row);
					} else {
						$.messager.alert('提示', "此隐患整改信息还未审核或已整改完成", 'info');
						return;
					}
		  		}
		  		
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
					url:baseURL+"/safe/hiddendanger/doDangerRectify.json",
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
							msg :  data.total+'个隐患整改信息已处理'+data.msg+'！',
						});
						$('#loading').window('close');
					}
				});
			}