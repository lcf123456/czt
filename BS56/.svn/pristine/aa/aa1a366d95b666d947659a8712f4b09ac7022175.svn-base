/**
 * 页面列表datagrid初始化
 */
jQuery(function($){

	//初始化日期控件
	var obj = $("#searchtime");
	initDataboxYM(obj);
	
	var deptobj = $("#searchdept");
	initDept(deptobj);
	//initctype();
	$('#dataTabel').datagrid({
		title:'月计划调整', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //单选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		nowrap:false,
		//货主默认为长沙11430101
		url:baseURL+"/perform/keywork/getKeyworkList.json?ctype=20", //ctype=20:月计划调整
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		//pageNumber: 1, 
		//pageSize : 15,
		//pageList: [15,30,50],
		queryParams:{
			}, //查询条件
		pagination:false, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'createname',title:'责任人',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.createname;}
			},
			{field:'checkdeptname',title:'部门',width:$(this).width()*0.07,
				formatter:function(value,row,index){return row.checkdeptname;}
			},
			{field:'content',title:'工作内容',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.content;}
			},
			{field:'target',title:'要求及目标',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.target;}
			},
			{field:'process',title:'过程进度',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.process;}
			},
			{field:'finishdate',title:'完成期限',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.finishdate;}
			},
			{field:'partake',title:'实施人',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.partake;}
			},
			{field:'statusname',title:'状态',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.statusname;}
			},
			{field:'weight',title:'权重',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.weight;}
			}
			
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none'); 
			
		}
	});
	

});

function initDept(obj){
	//部门下拉列表
    obj.combobox({
    	url : baseURL+"/sys/dept/getDeptComboboxByCond.json",//返回json数据的url
    	valueField : "id",//这个id和你返回json里面的id对应
    	textField : "deptname", //这个text和你返回json里面的text对应
    	onLoadSuccess: function () { //加载完成后,设置选中用户所属部门
                    //$(this).combobox("select", deptid);  
    		var typecode=obj.val();
    		var choosebz = 0;
	    	if(typecode==null||typecode==""){
	            var val = $(this).combobox("getData");
	          //如果登陆用户部门在二级部门里面，则默认选中用户部门，否则默认选中第一个
	            for (var i = 0; i < val.length; i++){
	            	for (var item in val[i]) {
	            		if (item == "id") {
		                	if(deptid==val[i][item]){
		                		choosebz=1;
		 	                   typecode=deptid;
		 	                    $(this).combobox("select", typecode);
		                	}
		                }
	            	}
	            }
	            if(choosebz==0){//没有选中用户所在部门,则默认选中第一个
	            	for (var item in val[0]) {
		                if (item == "id") {
		                   typecode=val[0][item];
		                    $(this).combobox("select", typecode);
		                }
		            }
	            }
	            
	    	}
        }
    	//panelHeight: 'auto'
    })
}

//初始化考核类型
function initctype(){
	$("#ctype").combobox({
        valueField:'id',
        textField:'value',
        data:[
        	{
        		id:10,
        		value:"考核"
        	},{
        		id:20,
        		value:"奖励"
        	}],
        	onLoadSuccess: function () {
        		$("#ctype").combobox('select', ""); //默认不选中
        	}
    });
}

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

//初始被考核部门
function initdep(obj){
	obj.combotree({
      url:baseURL+"/sys/user/getDeptTree.json", //数据来源
	onLoadSuccess: function (node, data) {
		if (data != null) { 
			obj.combotree('setValue', { id: data[0].id, text: data[0].text }); 
		}
	}
    });
}

function initchecktype(){
	$('#checktype_new').combobox({
        valueField:'id',
        textField:'contentlist',
        url:baseURL+"/comm/combobox/getComboboxByTypeCode.json?typeCode=ASSESSTYPE", //数据来源
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
	initctype();
	searchData();
}


/**
 * 打开新增窗口
 */
function openCreate(){
	$('#add-fm').form('clear');
	$('#add-dlg').dialog('open').dialog('setTitle','月计划调整新增');
	
	//初始化记录人、记录日期、检查日期、检查部门
	var obj = $("#workdate_new");
	initDataboxYM(obj);
	
	var deptobj = $("#deptid_new");
	initDept(deptobj);
	
	var date = new Date();
    var month = date.getMonth() + 1;
    $('#finishdate_new').textbox("setValue",month+"月28日");
    $('#finishdate_new').textbox("setText",month+"月28日");
    $('#partake_new').textbox("setValue",username+"等人");
    $('#partake_new').textbox("setText",username+"等人");
    var obj = $('#kwlist');
	getkwlist(obj,150);
}

//列表
function getkwlist(obj,dgheight)
{
		//获取领料新增分配的物资明细列表
	obj.datagrid({
		title:'本月重点工作', //标题
		method:'post',
		iconCls:'icon-view', //图标
		singleSelect:false, //多选
		height:dgheight, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		nowrap:false,
		url:baseURL+"/perform/keywork/getKeyworkList.json?ctype=20", //ctype=20:月计划调整
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		singleSelect: true,
		pagination:false, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'createname',title:'责任人',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.createname;}
			},
			{field:'content',title:'工作内容',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.content;}
			},
			{field:'target',title:'要求及目标',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.target;}
			},
			{field:'process',title:'过程进度',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.process;}
			},
			{field:'finishdate',title:'完成期限',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.finishdate;}
			},
			{field:'weight',title:'权重',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.weight;}
			}
		]],
		onLoadSuccess:function(){
			//$('#inboundlinediv .panel-header').css('display','none'); 
			
		}
	});
	}

function setRadioValue(value) {  
    $("input[name='ctype_new']").each(function() {  
        if ($(this).val() == value) {  
            $(this).prop("checked", "checked");  
        }  
    });  
} 


/**
 * 保存
 * @returns
 */
function toSave()
{
	var workdate_new = $("#workdate_new").val();
	var weight_new = $("#weight_new").val();
	var content_new = $("#content_new").val();
	 $('#ctype').textbox("setValue","20");
    
	if(workdate_new==""){
		  $.messager.alert('提示',"请输入工作时间！",'info');
			return;
	  }
	  if(weight_new=="" || parseFloat(weight_new)==0 || isNaN(parseFloat(weight_new))){
		  $.messager.alert('提示',"请输入权重或您输入的权重为0，请重新输入！",'info');
			return;
	  }
	  if(content_new==""){
		  $.messager.alert('提示',"请输工作内容！",'info');
			return;
	  }
	 
	  $('#add-fm').form('submit',{
		  url:baseURL+"/perform/keywork/doSave.json", 
			success : function(data) {
				data = eval('('+data+')');
				//$('#add-dlg').dialog('close');
				$('#dataTabel').datagrid('reload'); 
				$.messager.show({
					title : '提示',
					msg :  '月计划调整'+data.msg+'！',
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
		//var outboundid = row.outboundid;
		//row.assessdate=formatDatebox(row.assessdate);
		//row.outtime=formatDatebox(row.outtime);
		if (row){
			$('#view-dlg').dialog('open').dialog('setTitle',"查看");
			$('#view-dlg').form('load',row);
		}

	}
  
	
	  
	  
	//删除
		function deleterow(){
			var rows = $('#dataTabel').datagrid('getSelections');
			if(rows.length==0){
			$.messager.alert('提示',"请选择你要删除的信息",'info');
			return;
		}
			var row = $('#dataTabel').datagrid('getSelected');
			//var outboundid = row.outboundid;
			var status=row.status;
			var id = row.id;
			if(status!="0"){
				$.messager.alert('提示',"您选择的信息已经审核，不能再删除！",'info');
				return;
			}
			
			$.messager.confirm('提示','确定要删除吗?',function(result){
	        if (result){
	        	
	        	$.post(baseURL+'/perform/keywork/doDel.json?id='+id,function(data){
		        	$('#dataTabel').datagrid('reload'); 
	        		$.messager.show({
						title : '提示',
						msg :  data.total+'个信息被删除'+data.msg+'！',
					});
	        	});
	        }
	    });
		}

 

		