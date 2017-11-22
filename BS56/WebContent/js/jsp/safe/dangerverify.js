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
	$("#ctype1").combobox({
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
	var handlestatusval=$("#handlestatus").val();
	$("#handlestatus").combobox({
        onChange: function (n,o) {
        	searchDangerverify();
		}
	})

$('#dangercontents').textbox('textbox').keydown(function(e){
		if(e.keyCode==13){
			searchDangerverify();
		}
	})
	$('#dataTable').datagrid({
		title:'隐患核实', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		url:baseURL+"/safe/hiddendanger/getDangerverify.json",  //数据来源
        collapsible:true,
         //data:data,
		queryParams:{
			handlestatus:handlestatusval,
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
				formatter:function(value,row,index){
					return row.dangerdate.substring(0,10);
					 }  
			},
			{field:'handlestatus',title:'处理状态',width:10,sortable:false,
				formatter:function(value,row,index){
					if( row.handlestatus == '10'){
						return '提交';
					}else if( row.handlestatus == '20'){
						return '整改下发';
					}else{
					return '整改完成';
					}
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
	function searchDangerverify(){
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
		searchDangerverify();
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
				$("#v-deptid").html(viewRow.deptname);
				$("#v-verifydate").html(viewRow.verifydate.substring(0,10));
				$("#v-checkid").html(viewRow.checkid);
				$("#v-demands").html(viewRow.demands);
				$("#v-rectifyname").html(viewRow.rectifyname);
				$("#v-status").html(viewRow.status);
				$("#v-rectifynote").html(viewRow.rectifynote); 
				
			}
}
	 
	  /**
	   * 打开审核窗口
	   */
	    function openhandle(){ 
	    	
	    	$("#deptid").combobox({
	    		url : baseURL+"/comm/combobox/getDeptCombobox.json",//返回json数据的url
	        	valueField : "id",//这个id和你返回json里面的id对应
	        	textField : "deptname", //这个text和你返回json里面的text对应
	    	    loadFilter : function (data) {
	    	        if (data && data instanceof Array) {
	    	            data.splice(0, 0, {id: '', deptname: '--请选择--'});　// 此处空格用全角
	    	        }
	    	        return data;
	    	    }   	
	    	})
	    	
	    	var nowTime = getDateYMD();
	  	 $('#verifydate').datebox('setValue',nowTime );
	  	//$('#handle-fm').form('clear');
  	   // $('#handle-fm').form('reset');
  	   // $(".formtd").each(function(){
  		 	//   $(this).html("");
  		 	// });
	  		var rows = $('#dataTable').datagrid('getSelections');
	  		if(rows.length==0){
	  			$.messager.alert('提示',"请选择你要核实的信息",'info');
	  			return;
	  		}
	  		if(rows.length > 1){
	  			$.messager.alert('提示',"只能选择一条核实信息进行处理",'info');
	  			return;
	  		}
	  		var row = $('#dataTable').datagrid('getSelected');
	  		
	  		//alert(row.dangerdate.substring(0,10));
		  	if (row) {
				if (row.handlestatus == '10' || row.handlestatus == null) {
					$('#handle-dlg').dialog('open').dialog('setTitle', '隐患核实信息');
					//alert(row.handlestatus);
					//if('有效隐患'==row.dangerstatus){
						//row.dangerstatus = 10;
		    		//}else if('无效隐患'==row.dangerstatus){
		    			//row.dangerstatus = 20;
		    		//}else{
		    		//	row.dangerstatus = 30;
		    		//}
					$('#handle-fm').form('load', row);
					$('#handle-dlg').form('load',row);
					/*$("#dangerdate").datebox("setValue", row.dangerdate.substring(0,10));
					$("#ctype").textbox("setValue", row.ctype);
					$("#createname").textbox("setValue", row.createname);
					$("#dangercontent").textbox("setValue", row.dangercontent);
			  		/*$('#dangerdate').val(row.dangerdate.substring(0,10));
			  		$('#ctype').val(row.ctype);alert($('#ctype').val());
			  		$('#createname').val(row.createname);
			  		$('#dangercontent').val(row.dangercontent);*/
					
				} else {
					$.messager.alert('提示', "此隐患整改信息已下发或已整改完成", 'info');
					return;
				}
	  		}
	  		
	  	}
	    
	  
	    
	    $(document).ready(function () {

	    	$("#dangerstatus").combobox({

	    	onChange: function (n,o) {
	    		 var table1 = document.getElementById("table1");
	    		
	    		if(10==n){//alert(n+"--"+1);
	    			table1.style.display='block';
	    	
	    		}else if(20==n){//alert(n+"--"+2);
	    			table1.style.display='none';
	    		
	    		}//else if(30==n){//alert(n+"--"+3);
	    			//table1.style.display='none';
	    			
	    		//}

	    	}

	    	});

	    	});
	    
	    /**
	     * 保存审核的信息
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
				url:baseURL+"/safe/hiddendanger/doDangerVerify.json",
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
						msg :  data.total+'个隐患核实信息已审核'+data.msg+'！',
					});
					$('#loading').window('close');
				}
			});
		}
	    
	    
	    function checkundefined(s){
			   if(typeof(s)=="undefined"){
			     return '';
			   }else{
			     return s.substring(0,10);
			   }
			}
	    
	    function check(n){
			   if(typeof(n)=="undefined"){
			     return '';
			   }else{
			     return n;
			   }
			}
	    
	    /**
		 * 打开打印窗口
		 */
		  function printpage(){  
				var rows = $('#dataTable').datagrid('getSelections');
				if(rows.length==0){
					$.messager.alert('提示',"请选择要打印的信息",'info');
					return;
				}
				if(rows.length>1){
					$.messager.alert('提示',"只能选择一条信息进行打印",'info');
					return;
				}
				var contentstr='<table width="100%" border="0" cellpadding="0" cellspacing="0">';
				contentstr+='<h2  style="text-align:center;">隐患整改通知单</h2>';
				contentstr+='<tr><td align=left><strong>'+getDateYMD()+'</strong></td><td align="right"><strong style="margin-left:150px">(存根)</strong></td><td align=right ><strong style="margin-left:190px" >签发人：</strong></td></tr></table>';
				//contentstr +='<table width="100%" border="1" cellpadding="0" cellspacing="0"<tr><td align="center">部门</td><td align="center" colspan="2">'+n.deptname+'</td><td align="center">检查时间</td><td align="center" colspan="2">'+n.verifydate+'</td> </tr><tr><td align="center">部门负责人签字</td><td align="center">检查人员</td></tr>';
				//var totalamount=0;
				$.each(rows,function(i,n){
					contentstr +='<table width="100%" border="1" cellpadding="0" cellspacing="0"<tr><td align="center">部门</td><td align="center" colspan="2">'+check(n.deptname)+'</td><td align="center">检查时间</td><td align="center" colspan="2">'+checkundefined(n.verifydate)+'</td> </tr><tr><td align="center">部门负责人签字</td><td align="center" colspan="2"></td><td align="center">检查人员</td><td align="center" colspan="2">'+check(n.checkid)+'</td></tr><tr><td align="center">隐患内容</td><td align="center" colspan="5">'+check(n.dangercontent)+'</td></tr><tr><td align="center">整改项目要求及期限</td><td align="center" colspan="5">'+check(n.demands)+'</td></tr><tr><td align="center">安全管理部签字</td><td align="center" colspan="2"></td><td align="center">领导审核签字</td><td align="center" colspan="2"></td></tr><tr><td align="center">整改验收情况</td><td align="center" colspan="5">'+check(n.rectifynote)+'</td></tr>';
	        		
	        	});
				
				contentstr +="</table>";
				
				contentstr+='<tr><td align="center" colspan="2"></td></tr>';
				contentstr+='<h2  style="text-align:center;">隐患整改通知单</h2>';
				contentstr+='<tr><td align=left><strong>'+getDateYMD()+'</strong></td><td align=right><strong style="margin-left:200px">(整改部门)</strong></td><td align=right ><strong style="margin-left:200px">签发人：</strong></td></tr></table>';
				//contentstr +='<table width="100%" border="1" cellpadding="0" cellspacing="0"<tr><td align="center">部门</td><td align="center" colspan="2">'+n.deptname+'</td><td align="center">检查时间</td><td align="center" colspan="2">'+n.verifydate+'</td> </tr><tr><td align="center">部门负责人签字</td><td align="center">检查人员</td></tr>';
				//var totalamount=0;
				$.each(rows,function(i,n){
					contentstr +='<table width="100%" border="1" cellpadding="0" cellspacing="0"<tr><td align="center">部门</td><td align="center" colspan="2">'+check(n.deptname)+'</td><td align="center">检查时间</td><td align="center" colspan="2">'+checkundefined(n.verifydate)+'</td> </tr><tr><td align="center">部门负责人签字</td><td align="center" colspan="2"></td><td align="center">检查人员</td><td align="center" colspan="2">'+check(n.checkid)+'</td></tr><tr><td align="center">隐患内容</td><td align="center" colspan="5">'+check(n.dangercontent)+'</td></tr><tr><td align="center">整改项目要求及期限</td><td align="center" colspan="5">'+check(n.demands)+'</td></tr><tr><td align="center">安全管理部签字</td><td align="center" colspan="2"></td><td align="center">领导审核签字</td><td align="center" colspan="2"></td></tr><tr><td align="center">整改验收情况</td><td align="center" colspan="5">'+check(n.rectifynote)+'</td></tr>';
	        		
	        	});
				contentstr +="</table>";
				$("#printTable").html(contentstr);
				$('#print-dlg').dialog('open').dialog('setTitle','隐患整改通知单');
					
				
			}
		
	 