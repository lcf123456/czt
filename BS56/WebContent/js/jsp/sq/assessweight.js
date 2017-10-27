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
	//alert(baseURL+"/sq/starinfo/getAssessweight.json");
	$('#dataTabel').datagrid({
		title:'得分权重信息维护', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //多选
		checkOnSelect:true,		height:220, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。		striped: true, //奇偶行颜色不同		collapsible:true,//可折叠		url:baseURL+"/sq/assessweight/getAssessweight.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'desc', //正序asc、倒序 desc
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 10,
		pageList: [10,30,50],
		queryParams:{
			}, //查询条件
		pagination:false, //显示分页
		//pageSize : 1,
		rownumbers:false, //显示行号
		columns:[[
			//{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'name',title:'权重名称',width:20,align:'center',
				formatter:function(value,row,index){return row.weightname;} //需要formatter一下才能显示正确的数据
			},
			{field:'score',title:'得分权重',width:20,align:'center',
				formatter:function(value,row,index){return row.weightscore;}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题			$('#tabdiv .panel-header').css('display','none');
		}
	});
});



/**
 * 打开修改窗口
 */
  function openEdit(){
		//var rows = $('#dataTabel').datagrid('getSelections');
	    $('#edit-fm').form('clear')
		var rows = $("#dataTabel").datagrid("getRows"); //取所有行
		var fid=rows[0].id;
		var marketweight=rows[0].weightscore;
		var autoweight=rows[1].weightscore;
		//var row = $('#dataTabel').datagrid('getSelections');
		//给marketweight、autoweight重新赋值
		$('#marketweight').textbox("setValue",marketweight);
		$('#marketweight').textbox("setText",marketweight);
		$('#autoweight').textbox("setValue",autoweight);
		$('#autoweight').textbox("setText",autoweight);
		//id隐藏域赋值      无变量id   $('#id').val(id);
		$('#id').val(fid);
		if (fid){
			$('#edit-dlg').dialog('open').dialog('setTitle','修改得分权重');
		}

	}
  
  /**
   * 保存修改的得分权重
   */
  
  function saveEdit(){
		$('#edit-fm').form('submit',{
			onSubmit: function(){
				var isValidate = $(this).form('validate');
				if(isValidate){
					//$('#loading').window('open');
					
				var sum=parseFloat($('#marketweight').val())+parseFloat($('#autoweight').val());
				//alert("sum="+sum);
				if(sum!=1){
				    alert("权重之和请调整为1");
					return false;
				}
				}
				return isValidate;
			},
			url:baseURL+"/sq/assessweight/dodeleteAssessweight.json",
			data:$("#edit-fm").serializeArray(),
			beforeSend : function () {
				$.messager.progress({
					text : '正在修改中...',
				});
			},
			success: function(data){
				//alert(data);
				//$('#loading').window('close');
				data = eval('('+data+')');
				$('#edit-dlg').dialog('close');
				$('#dataTabel').datagrid('reload'); 
	    		$.messager.show({
					title : '提示',
					msg :  '权重得分已修改'+data.msg+'！',
				});
				//$('#loading').window('close');
			}
		});
	
    }