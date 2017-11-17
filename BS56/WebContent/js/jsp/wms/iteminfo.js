/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	$('#itemname1').textbox('textbox').keydown(function(e){
		if(e.keyCode==13){
			searchiteminfo();
		}
	})
	
	$('#dataTable').datagrid({
		//title:'商品信息维护', //标题
		method:'post',
		//iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/wms/item/getIteminfoList.json", //数据来源
		sortName: 'createtime', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: false, //服务器端排序
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
			{field:'itemno',title:'商品编号',width:20,
				formatter:function(value,row,index){return row.itemno;} //需要formatter一下才能显示正确的数据
			},
			
			{field:'itemname',title:'商品名称',width:40,
				formatter:function(value,row,index){return row.itemname;} //需要formatter一下才能显示正确的数据
			},
			
			{field:'spec',title:'规格',width:20,
				formatter:function(value,row,index){return row.spec;} //需要formatter一下才能显示正确的数据
			},
			{field:'abccode',title:'ABC编码',width:15,
				formatter:function(value,row,index){
					if( row.abccode == '10'){
						return 'A类';
					}
					else if( row.abccode == '20'){
						return 'B类';
					}
					else if( row.abccode == '30'){
						return 'C类';
					}
				} //需要formatter一下才能显示正确的数据
			},
			{field:'shiptype',title:'类型',width:15,
				formatter:function(value,row,index){
					if( row.shiptype == '0'){
						return '正常烟';
					}
					else if( row.shiptype == '1'){
						return '异型烟';
					}
				} //需要formatter一下才能显示正确的数据
			},
			{field:'weight',title:'重量',width:10,
				formatter:function(value,row,index){return row.weight;} //需要formatter一下才能显示正确的数据
			},
			{field:'iscanscancode',title:'扫码识别类型',width:25,
				formatter:function(value,row,index){
					if( row.iscanscancode == '10'){
						return '能扫码识别';
					}
					else if( row.iscanscancode == '0'){
						return '无法扫码识别';
					}
				} //需要formatter一下才能显示正确的数据
			},
			{field:'dxtype',title:'垛型',width:10,
				formatter:function(value,row,index){return row.dxtype;} //需要formatter一下才能显示正确的数据
			},
			{field:'jt_size',title:'件条',width:10,
				formatter:function(value,row,index){return row.jtSize;} //需要formatter一下才能显示正确的数据
			},
			{field:'wz_size',title:'万条',width:10,
				formatter:function(value,row,index){return row.wzSize;} //需要formatter一下才能显示正确的数据
			},
			{field:'outtype',title:'出库类型',width:15,
				formatter:function(value,row,index){
					if( row.outtype == '1'){
						return '一楼出';
					}
					else if( row.outtype == '2'){
						return '二楼出';
					}
					}
			},
			{field:'fullcount',title:'满盘数量',width:15,
				formatter:function(value,row,index){return row.fullcount;}
			},
			{field:'createuser',title:'创建人',width:20,
				formatter:function(value,row,index){return row.createuser;}
			},
			{field:'createtime',title:'创建时间',width:20,
				formatter:function(value,row,index){return row.createtime.substring(0,10);}
			},
			{field:'cdtype',title:'拆垛类型',width:20,
				formatter:function(value,row,index){
					if( row.cdtype == '10'){
					return '自动拆垛';
				}
				else if( row.cdtype == '0'){
					return '人工拆垛';
				}
			 }
			},
			
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv.panel-header').css('display','none'); 
			
		}
	});
	
	
	
	
});

/**
 * 打开商品信息新增窗口
 */
function newadd(){
	$('#add-dlg').dialog('open').dialog('setTitle','新增商品信息');
	$('#add-fm').form('reset');
	var nowTime = getDateYMD();
	 $('#createtime').datebox('setValue',nowTime );
}



/**
 * 保存新增商品信息
 */
function saveNew(){
	
	$('#add-fm').form('submit',{
		onSubmit: function(){
			var isValidate = $(this).form('validate');
			if(isValidate){
				//这里直接调用ajax的方法
				var itemname = $("#itemname").val().trim();
				var itemno = $("#itemno").val().trim();
				//alert(itemname);
				//alert(itemno);
				$.ajax({
			        type : 'post',
			        url : baseURL+"/wms/item/doItemnameCheck.json",
			       data : {
			            "itemname" : itemname,
			            "itemno" : itemno
			         },
			        success : function(datavalue) {
			             if(datavalue=="0"){
			            	//alert("11");
			            	 addItem();
			             }
			             else{
			            	$.messager.alert('提示',"该商品名称或商品编号已存在，请重新输入！",'info');
				        	 return false;
			             }
			          },
			          error:function(){
			        	  return false;
			          }
			     });
				/*$.ajax({
			        type : 'post',
			        url : baseURL+"/wms/item/doItemnoCheck.json",
			       data : {
			            "itemno" : itemno
			         },
			        success : function(datavalue) {
			             if(datavalue=="0"){
			            	//alert("11");
			            	 addItem();
			             }
			             else{
			            	$.messager.alert('提示',"该商品编号已存在，请重新输入！",'info');
				        	 return false;
			             }
			          },
			          error:function(){
			        	  return false;
			          }
			     });*/
			}
						
			return isValidate;
		},
		
		
	});
}

function addItem(){
	$.ajax({
		type : 'post',
        url:baseURL+"/wms/item/doIteminfoNew.json",
		data:$("#add-fm").serializeArray(),
		beforeSend : function () {
			$.messager.progress({
				text : '正在新增中...',
			});
		},
		/*complete: function(){  
	        //AJAX请求完成时隐藏loading提示  
	        $.messager.progress('close');
	   },*/
	    success: function(data){
	    	$.messager.progress('close');
	    	//console.log("--"+data)
			//data = eval('('+data+')');
			//alert(data.msg);
			$('#add-dlg').dialog('close');
			$('#dataTable').datagrid('reload'); 
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个商品信息新增'+data.msg+'！',
			});
		}
        });
}
//删除
function deleterow(){
	var rows = $('#dataTable').datagrid('getSelections');
	if(rows.length==0){
	$.messager.alert('提示',"请选择你要删除的商品信息",'info');
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
    	$.post(baseURL+'/wms/item/doIteminfoDel.json'+ps,function(data){
        	$('#dataTable').datagrid('reload'); 
        	//console.log("del--"+data);
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个商品信息'+data.msg+'！',
			});
    	});
    }
});
}
 
  
//查询
function searchiteminfo(){
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
	searchiteminfo();
}

