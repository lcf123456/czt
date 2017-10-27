/**
 * 页面列表datagrid初始化
 */
var editRow = undefined; //定义全局变量：当前编辑的行
jQuery(function($){
	var nowTime01 = getDateYM01();
	var nowTime = getDateYMD();
	$('#searchtime').datebox("setValue",nowTime01);
	$('#searchtime2').datebox("setValue",nowTime);

	initctype();
	$('#dataTabel').datagrid({
		title:'考评报表', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //单选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		nowrap:false,
		//货主默认为长沙11430101
		url:baseURL+"/perform/transverseAssess/getTransverseAssessList.json", //数据来源
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
			{field:'assessdate',title:'考核日期',width:$(this).width()*0.05,sortable:true,
				formatter: formatDatebox //需要formatter一下才能显示正确的数据
			},
			{field:'checkdeptname',title:'检查部门',width:$(this).width()*0.07,
				formatter:function(value,row,index){return row.checkdeptname;}
			},
			{field:'checktypename',title:'检查项目',width:$(this).width()*0.07,
				formatter:function(value,row,index){return row.checktypename;}
			},
			{field:'checkeddeptname',title:'被考核部门',width:$(this).width()*0.07,
				formatter:function(value,row,index){return row.checkeddeptname;}
			},
			{field:'content',title:'考核内容',width:$(this).width()*0.24,
				formatter:function(value,row,index){return row.content;}
			},
			{field:'amount',title:'考核金额',width:$(this).width()*0.05,sortable:true,editor:'numberbox',
				formatter:function(value,row,index){return row.amount;}
			},
			{field:'ctypename',title:'类型',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.ctypename;}
			},
			{field:'flagname',title:'考核状态',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.flagname;}
			}
			
		]],
		toolbar:'#toolbar',
		onAfterEdit: function (rowIndex, rowData, changes) {
            //endEdit该方法触发此事件
            var amount = rowData.amount;
            var bz=0;
            if (parseInt(amount).toString() == "NaN"){
            	$.messager.alert('提示',"请输入合法的数字！",'info');
            	bz=1;
            }

            if(bz==0){//提交修改
            	$.post(baseURL+'/perform/transverseAssess/doAudit.json?asid='+rowData.id+'&amount='+amount, rowData, function(data) {
					$.messager.show({
						title : '提示',
						msg :  data.total+'个信息'+data.msg+'！',
					});
					$('#dataTabel').datagrid('reload'); 
				});
            }
            
            editRow = undefined;
        },
		onLoadSuccess:function(){
			$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none'); 
			
		}
	});
	

});

//调整
function editseq()
{
	//修改时要获取选择到的行
    var rows = $('#dataTabel').datagrid("getSelections");
    //如果只选择了一行则可以进行修改，否则不操作
    if (rows.length == 1) {
    	var row = $('#dataTabel').datagrid('getSelected');
    	var flag = row.flag;
    	if(status=="30"){
    		$.messager.alert('提示',"该数据已经分配完成，不需要再考核！",'info');
			return;
    	}
        //修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
        if (editRow != undefined) {
            $('#dataTabel').datagrid("endEdit", editRow);
        }
        //当无编辑行时
        if (editRow == undefined) {
            //获取到当前选择行的下标
            var index = $('#dataTabel').datagrid("getRowIndex", rows[0]);
            //开启编辑
            $('#dataTabel').datagrid("beginEdit", index);
            //把当前开启编辑的行赋值给全局变量editRow
            editRow = index;
            //当开启了当前选择行的编辑状态之后，
            //应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
            $('#dataTabel').datagrid("unselectAll");
        }
    }
	}
//保存
function saveseq() {
    //保存时结束当前编辑的行，自动触发onAfterEdit事件如果要与后台交互可将数据通过Ajax提交后台
    $('#dataTabel').datagrid("endEdit", editRow);
}
//取消调整
function cancelseq() {
    //取消当前编辑行把当前编辑行罢undefined回滚改变的数据,取消选择的行
    editRow = undefined;
    $('#dataTabel').datagrid("rejectChanges");
    $('#dataTabel').datagrid("unselectAll");
}

//初始化考核类型
function initctype(){
	$("#ctypesearch").combobox({
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
        		$("#ctypesearch").combobox('select', ""); //默认不选中
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

function exportxls()
{
	 $.messager.confirm('提示','确定要导出到excel吗?',function(result){
	        if (result){
	        	 $('#queryForm').form('submit', {
	        		 url: baseURL+'/perform/transverseAssess/doExportToExcel.json'
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
		row.assessdate=formatDatebox(row.assessdate);
		//row.outtime=formatDatebox(row.outtime);
		if (row){
			$('#view-dlg').dialog('open').dialog('setTitle',"查看");
			$('#view-dlg').form('load',row);
		}

	}
  
	