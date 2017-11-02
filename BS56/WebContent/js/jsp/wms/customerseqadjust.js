var url;
var editRow = undefined; //定义全局变量：当前编辑的行
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
    $("#orderbatch").combobox({
    	url : baseURL+"/sys/user/getComboboxByTypeCode.json?typeCode=ORDERBATCH",//返回json数据的url
    	valueField : "contentlist",//这个id和你返回json里面的id对应
    	textField : "valuelist", //这个text和你返回json里面的text对应
    	loadFilter : function (data) {
	        if (data && data instanceof Array) {
	            data.splice(0, 0, {contentlist: '', valuelist: '请选择批次'});　// 此处空格用全角
	        }
	        return data;
	    } 
    	//panelHeight: 'auto'
    })
	$("#routecode").combobox({
	    url : baseURL+"/comm/combobox/getRoutesComboboxByDeptid.json?deptid=",//返回json数据的url
	    valueField : "routecode",//这个id和你返回json里面的id对应
	    textField : "routename", //这个text和你返回json里面的text对应
	    loadFilter : function (data) {
	        if (data && data instanceof Array) {
	            data.splice(0, 0, {routecode: '', routename: '请选择车组'});　// 此处空格用全角
	        }
	        return data;
	    }   	
	})
	//alert(baseURL+"/sq/starinfo/getStarinfo.json");
	$('#dataTable').datagrid({
		title:'零售户顺序调', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //多选,当true时只允许当前选择一行。
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同

		collapsible:true,//可折叠
		url:baseURL+"/wms/customer/getCustomerseqList.json", //数据来源
		sortName: 'addressseq', //排序的列
		sortOrder: 'asc', //正序asc、倒序 desc
		remoteSort: false, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 30,
		pageList: [10,30,50],
		queryParams:{
			}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'id2',title:'专卖证号',width:$(this).width()*0.15,sortable:true,
				formatter:function(value,row,index){return row.id;} //需要formatter一下才能显示正确的数据
			},
			{field:'name',title:'零售户信息',width:$(this).width()*0.15,sortable:true,
				formatter:function(value,row,index){return row.name;}
			},
			{field:'contact',title:'联系信息',width:$(this).width()*0.15,sortable:true,
				formatter:function(value,row,index){return row.contact+"-"+row.contactphone;}
			},
			{field:'routecode',title:'送货车组',width:$(this).width()*0.15,sortable:true,
				formatter:function(value,row,index){return row.routecode;}
			},
			{field:'orderbatch',title:'送货批次',width:$(this).width()*0.15,sortable:true,
				formatter:function(value,row,index){return row.orderbatch;}
			},
			{field:'addressseq',title:'送货顺序',width:$(this).width()*0.05,sortable:true,editor:'numberbox',
				formatter:function(value,row,index){return row.addressseq;}
			},
			{field:'delstatus',title:'状态',width:$(this).width()*0.15,sortable:true,
				formatter:function(value,row,index){//return row.delstatus;}
					if (row.delstatus==0) return "无效"
					if (row.delstatus==10) return "有效";}
			}
		]],
		
		toolbar:'#toolbar',
		onAfterEdit: function (rowIndex, rowData, changes) {
            //endEdit该方法触发此事件
            var addressseq = rowData.addressseq;
            var bz=0;
            if (parseInt(addressseq).toString() == "NaN"){
            	$.messager.alert('提示',"请输入合法的数字！",'info');
            	bz=1;
            }

            if(bz==0){//提交修改
            	$.post(baseURL+'/wms/customer/doUpdateseq.json?id='+rowData.id+'&addressseq='+addressseq, rowData, function(data) {
					$.messager.show({
						title : '提示',
						msg :  data.total+'个信息'+data.msg+'！',
					});
					$('#dataTable').datagrid('reload'); 
				});
            }
            
            editRow = undefined;
        },
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none');
		}
	});
});



//顺序调整
function editseq()
{
	//修改时要获取选择到的行
    var rows = $('#dataTable').datagrid("getSelections");
    //如果只选择了一行则可以进行修改，否则不操作
    if (rows.length == 1) {
    	var row = $('#dataTable').datagrid('getSelected');
    	var status = row.status;
    	if(status=="60"  || status=="1"){
    		$.messager.alert('提示',"请选择的车辆状态为在途或出园，不需排队",'info');
			return;
    	}
        //修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
        if (editRow != undefined) {
            $('#dataTable').datagrid("endEdit", editRow);
        }
        //当无编辑行时
        if (editRow == undefined) {
            //获取到当前选择行的下标
            var index = $('#dataTable').datagrid("getRowIndex", rows[0]);
            //开启编辑
            $('#dataTable').datagrid("beginEdit", index);
            //把当前开启编辑的行赋值给全局变量editRow
            editRow = index;
            //当开启了当前选择行的编辑状态之后，
            //应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
            $('#dataTable').datagrid("unselectAll");
        }
    }
	}
//顺序保存
function saveseq() {
    //保存时结束当前编辑的行，自动触发onAfterEdit事件如果要与后台交互可将数据通过Ajax提交后台
    $('#dataTable').datagrid("endEdit", editRow);
}
//取消顺序调整
function cancelseq() {
    //取消当前编辑行把当前编辑行罢undefined回滚改变的数据,取消选择的行
    editRow = undefined;
    $('#dataTable').datagrid("rejectChanges");
    $('#dataTable').datagrid("unselectAll");
}
	
	  //查询
	function searchCustomer(){
		var params = $('#dataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
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
		$('#queryForm').form('reset');
		//$('#routecode').combobox(value,'0');
		//$('#orderbatch').combobox(value,'');
		searchCustomer();
	}
	
