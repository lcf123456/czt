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
		title:'入库单', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //多选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/wms/inbound/getInBoundPageList.json", //数据来源
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
			{field:'qty',title:'数量',width:$(this).width()*0.1,
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


function getsplapplylistline(inboundid)
{
		//获取领料新增分配的物资明细列表
	$('#listdataTabel').datagrid({
		title:'入库明细', //标题
		method:'post',
		iconCls:'icon-view', //图标
		singleSelect:false, //多选
		height:200, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		url:baseURL+"/wms/inbound/getInBoundLineList.json?inboundid="+inboundid, 
		sortName: 'inbounddetailid', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'inbounddetailid', //主键字段
		pagination:false, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'inboundid',title:'入库编号',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.inboundid;}
			},
			{field:'cigarettename',title:'卷烟名称',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.cigarettename;} //需要formatter一下才能显示正确的数据
			},
			{field:'boxqty',title:'件烟数量',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.boxqty;}
			},
			{field:'itemqty',title:'条烟数量',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.itemqty;}
			},
			{field:'consignsor',title:'货主',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.consignsor;}
			},
			{field:'aboxqty',title:'实际入库',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.aboxqty;}
			},
			{field:'otherqty',title:'散烟区入库数量',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.otherqty;}
			}
		]],
		onLoadSuccess:function(){
			$('#listtabdiv .panel-header').css('display','none'); 
			
		}
	});
	

	}


/**
 * 查看窗口
 */
  function viewD(){
	  $('#add-fm').form('clear');
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
			$('#add-dlg').dialog('open').dialog('setTitle',"查看");
			$('#add-dlg').form('load',row);
		}
		
		getsplapplylistline(inboundid);
	}
  
  /**
	 * 打开新增窗口
	 * @returns
	 */
	function openNew(){
		$('#new-fm').form('clear');
		$('#new-dlg').dialog('open').dialog('setTitle',"入库单新增");
		$('#intype1').textbox("setValue","10");
		$('#intype1').textbox("setText","工业来烟");
		$('#createtime1').datetimebox('setValue',getDateYMDHMS());
		$("#cigarettecode1").combobox({
		    url : baseURL+"/comm/combobox/getItemCombobox.json",//返回json数据的url
		    valueField : "itemval",//这个id和你返回json里面的id对应
		    textField : "itemname", //这个text和你返回json里面的text对应
		    onLoadSuccess: function () { //加载完成后,设置选中第一项
		    	var val = $(this).combobox("getData");
		    	//typecode=val[0][item];
	            if(val!=null){
	            	$(this).combobox("select", val[0]["itemval"]);
	            	$('#cigarettename1').val(val[0]["itemname"]);
	            }
	        }
		})
		$("#consignsor1").combobox({
			url : baseURL+"/comm/combobox/getConsignsorCombobox.json",//返回json数据的url
			valueField : "code",//这个id和你返回json里面的id对应
			textField : "company", //这个text和你返回json里面的text对应
			onLoadSuccess: function () { //加载完成后,设置选中第一项
				var val = $(this).combobox("getData");
				//typecode=val[0][item];
				if(val!=null){
					$(this).combobox("select", val[0]["code"]);
				}
			}
		})
		$("#supplierid1").combobox({
			url : baseURL+"/sq/industrialdriver/getCigfactoryCombobox.json",//返回json数据的url
			valueField : "id",//这个id和你返回json里面的id对应
			textField : "name", //这个text和你返回json里面的text对应
			onLoadSuccess : function(){ 
				var val = $(this).combobox("getData");
		    	//typecode=val[0][item];
	            if(val!=null){
	            	$(this).combobox("select", val[0]["id"]);
	            	$('#supplier1').val(val[0]["name"]);
	            }
			},
			onSelect: function(rec){
	            //alert(rec.name);
	            $('#supplier1').val(rec.name);
	        }
		})
		$("#status1").combobox({
			valueField : "label",//这个id和你返回json里面的id对应
			textField : "value", //这个text和你返回json里面的text对应
			data: [{
				label: '10',
				value: '新增'
			}
//			,{
//				label: '30',
//				value: '入库完成'
//			}
			]
		})
		$('#createtime1').attr("readonly",false);
				//$('#navicert1').textbox({readonly:true});
				$('#navicert1').textbox('textbox').attr('readonly',false); 
				$('#contractno1').textbox('textbox').attr('readonly',false); 
				//$('#createtime1').datetimebox({readonly:true});
				//$(".datebox :text").attr("readonly","readonly");
				$('#supplierid1').combobox({disabled:false});
				$('#consignsor1').combobox({disabled:false});
				$('#status1').combobox({disabled:false});
		getInboundLine(-1);
	}
	

	function getInboundLine(inboundid)
	{
		$('#newlistdataTabel').datagrid({
			title:'入库单明细', //标题
			method:'post',
			iconCls:'icon-view', //图标
			singleSelect:false, //多选
			height:200, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:false,//可折叠
			url:baseURL+"/wms/inbound/getInBoundLineList.json?inboundid="+inboundid, 
			sortName: 'inbounddetailid', //排序的列
			sortOrder: 'asc', //倒序
			remoteSort: true, //服务器端排序
			idField:'inbounddetailid', //主键字段
			pagination:false, //显示分页
			//pageSize : 1,
			rownumbers:true, //显示行号
			columns:[[
				{field:'inbounddetailid',checkbox:true,width:2}, //显示复选框
				{field:'inboundid',title:'入库编号',width:10,
					formatter:function(value,row,index){return row.inbounddetailid;}
				},
				{field:'cigarettecode',title:'卷烟代码',width:10,
					formatter:function(value,row,index){return row.cigarettecode;} //需要formatter一下才能显示正确的数据
				},
				{field:'cigarettename',title:'卷烟名称',width:10,
					formatter:function(value,row,index){return row.cigarettename;} //需要formatter一下才能显示正确的数据
				},
				{field:'boxqty',title:'件烟数量',width:10,
					formatter:function(value,row,index){return row.boxqty;}
				},
				{field:'aboxqty',title:'入库数量',width:10,
					formatter:function(value,row,index){return row.aboxqty;}
				},
				{field:'lockqty',title:'锁定数量',width:10,
					formatter:function(value,row,index){return row.lockqty;}
				},
				{field:'otherqty',title:'散烟区入库数量',width:10,
					formatter:function(value,row,index){return row.otherqty;}
				}
				]],
				onLoadSuccess:function(){
					var  boxqqty=compute("boxqty");
					//var boxqqty=30;
			      	//alert(boxqqty+"---");
			  		$('#qty1').numberbox("setValue",boxqqty);
			  		$('#qty1').numberbox("setText",boxqqty);
				}
		});
	}
	

	function cigSubmit(){
		$('#cigarettename1').val($('#cigarettecode1').combobox("getText"));
		var inboundid=$('#newinboundid').val();
		if(inboundid==null||inboundid=="")inboundid="-1";
		var navicert= $("#navicert1").val(); 
		var contractno= $("#contractno1").val(); 
		var cigarettecode= $("#cigarettecode1").val(); 
		var itemqty= $("#itemqty1").val(); 
		
		//alert($("#consignsor").val()+"=="+contractno+"--"+qty);
		if(navicert==null||navicert==""){
			$.messager.alert('系统提示', '请输入准运证号！', 'warning');
			return;
		}else{
			if(navicert.length>40){
				$.messager.alert('系统提示', '准运证号输入过长！', 'warning');
				return;
			}
		}
		if(contractno==null||contractno==""){
			$.messager.alert('系统提示', '请输入合同号！', 'warning');
			return;
		}else{
			if(contractno.length>40){
				$.messager.alert('系统提示', '合同号输入过长！', 'warning');
				return;
			}
		}
//		if(qty==null||qty==""){
//			$.messager.alert('系统提示', '请输入入库总数量！', 'warning');
//			return;
//		}
		if(cigarettecode==null||cigarettecode==""){
			$.messager.alert('系统提示', '请选择要入库的卷烟！', 'warning');
			return;
		}
		if(itemqty==null||itemqty==""){
			$.messager.alert('系统提示', '请输入入库卷烟数量！', 'warning');
			return;
		}
//		var qty= $("#qty1").val();
//		if(qty==""||qty==null)qty=0
//		$('#qty1').numberbox("setValue",parseInt(qty)+parseInt(itemqty));
//		$('#qty1').numberbox("setText",parseInt(qty)+parseInt(itemqty));
//		qty= $("#qty1").val();
		
		$('#new-fm').form('submit',{
			onSubmit: function(){
				var isValidate = $(this).form('validate');
				if(isValidate){
				}
				return isValidate;
			},
			url:baseURL+"/wms/inbound/doAddInboundAndLine.json?inboundid="+inboundid, 
			//data:$("#new-fm").serializeArray(),
			beforeSend : function () {
				$.messager.progress({
					text : '正在新增中...',
				});
			},
			success: function(data){
				
				data = eval('('+data+')');
				$("#newinboundid" ).val(data.inboundid);
				//$("#status" ).combobox("setValue".data.status);
				//$('#createtime1').attr("readonly",true);
				//$('#navicert1').textbox({readonly:true});
				$('#navicert1').textbox('textbox').attr('readonly',true); 
				$('#contractno1').textbox('textbox').attr('readonly',true); 
				//$('#createtime1').datetimebox({readonly:true});
				//$(".datebox :text").attr("readonly","readonly");
//				$('#supplierid1').combobox({readonly:true});
//				$('#consignsor1').combobox({disabled:true});
//				$('#status1').combobox({disabled:true});
				//$('#add-dlg').dialog('close');
				$('#dataTabel').datagrid('reload'); 
				//$('#newlistdataTabel').datagrid('reload');
				
//				var  boxqqty=compute("boxqty");
//	        	var qty= $("#qty1").val();
//	    		if(qty==""||qty==null)qty=0;
//	    		$('#qty1').numberbox("setValue",boxqqty);
//	    		$('#qty1').numberbox("setText",boxqqty);
				
	    		$.messager.show({
					title : '提示',
					msg :  data.total+'条入库明细新增'+data.msg+'！',
				});
	    		//$("#listtabdiv" ).css("display", "block");
	    		getInboundLine(data.inboundid);
			}
		});
	}
	
	function delInboundLine(){
			var rows = $('#newlistdataTabel').datagrid('getSelections');
			if(rows.length==0){
			$.messager.alert('提示',"请选择你要删除的入库单明细信息",'info');
			return;
		}
			
			
			$.messager.confirm('提示','确定要删除所选入库单明细信息吗?',function(result){
	      if (result){
	      	var ps = "";var totalnum=compute("boxqty");
	      	$.each(rows,function(i,n){
	      		if(i==0) 
	      			ps += "?inbounddetailid="+n.inbounddetailid;
	      		else
	      			ps += "&inbounddetailid="+n.inbounddetailid;
	      		
	      		totalnum=totalnum-n.boxqty;
	      	});
	      	$.post(baseURL+'/wms/inbound/doInboundLineDel.json'+ps+'&inboundid='+$("#newinboundid" ).val()+'&totalnum='+totalnum,function(data){
	      			data = eval('('+data+')');
		        	$('#newlistdataTabel').datagrid('reload'); 
		        	$('#dataTabel').datagrid('reload'); 
	      		$.messager.show({
						title : '提示',
						msg :  data.total+'个入库明细信息被删除'+data.msg+'！',
					});
	      	});
	      }
	    //console.log("del--"+data);
//      	var  boxqqty=compute("boxqty");
//      	alert(boxqqty);
////      	var qty= $("#qty1").val();
////  		if(qty==""||qty==null)qty=0;
//  		$('#qty1').numberbox("setValue",boxqqty);
//  		$('#qty1').numberbox("setText",boxqqty);
  		//qty= $("#qty1").val();
	  });
	}
	
	function compute(colName) {
	    var rows = $('#newlistdataTabel').datagrid('getRows');
	    var total = 0;
	    for (var i = 0; i < rows.length; i++) {
	        total = (total+parseFloat(rows[i][colName]))*10000/10000;
	    }
	    return total;
	}
	
	function destroyInbound(){
		var rows = $('#dataTabel').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要作废的入库单信息",'info');
			return;
		}else{
			var bz=0;
			var status="";
			$.each(rows,function(i,n){
				status=n.status;
	      		if(status!="10"){
	      			bz=1;
	      		} 
	      	});
			if(bz==1){
				$.messager.alert('提示',"所选入库单存在已入库信息，请重新选择！",'info');
				return;
			}
		}
		
		$.messager.confirm('提示','确定要作废所选入库单信息吗?',function(result){
      if (result){
      	var ps = "";
      	$.each(rows,function(i,n){
      		if(i==0) 
      			ps += "?inboundid="+n.inboundid;
      		else
      			ps += "&inboundid="+n.inboundid;
      	});
      	$.post(baseURL+'/wms/inbound/doDestroyInbound.json'+ps,function(data){
      			data = eval('('+data+')');
	        	$('#dataTabel').datagrid('reload'); 
	    		
      		$.messager.show({
					title : '提示',
					msg :  data.total+'个入库单信息被作废'+data.msg+'！',
				});
      	});
      }
  });
}



