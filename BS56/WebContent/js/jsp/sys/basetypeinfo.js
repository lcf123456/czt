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

function initDatagrid(typecode){
	//alert("3=--="+typecode);
	$('#dataTabel').datagrid({
		title:'基础类型信息维护', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/sys/basetypeInfo/getBasetypeInfoByTypecode.json?typecode="+typecode, //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序 desc
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 10,
		pageList: [10,30,50],
		queryParams:{
			}, //查询条件
		pagination:false, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'typecode',title:'类型代码',width:20,sortable:false,
				formatter:function(value,row,index){return row.typecode;} //需要formatter一下才能显示正确的数据
			},
			{field:'typename',title:'类型名称',width:30,
				formatter:function(value,row,index){return row.typename;}
			},
			{field:'remarks',title:'类型值',width:30,
				formatter:function(value,row,index){
					return row.contentlist;  
				}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none');
		}
	});
}



/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	var typecode="";
	//alert("-11--");
	$("#typecode").combobox({
	    url : baseURL+"/comm/combobox/getBaseTypesCombobox.json",//返回json数据的url
	    valueField : "typecode",//这个id和你返回json里面的id对应
	    textField : "typename", //这个text和你返回json里面的text对应
	    onLoadSuccess: function () { //加载完成后,设置选中第一项
	    	typecode=$("#typecode").val();
	    	if(typecode==null||typecode==""){
	            var val = $(this).combobox("getData");
	            for (var item in val[0]) {
	                if (item == "typecode") {
	                	typecode=val[0][item];
	                    $(this).combobox("select", typecode);
	                    //alert("1=="+typecode);
	                    //initDatagrid(typecode);
	                }
	            }
	    	}
        },
        onSelect: function (row) {
        	//alert("2=="+row.typecode);
        	initDatagrid(row.typecode);
    	}
	})
});

/**
 * 打开新增窗口
 */
function openNew(){
	$('#add-fm').form('clear');
	$('#add-dlg').dialog('open').dialog('setTitle','增加基础类型信息');
	var comval=$('#typecode').combobox('getValue');
	var comtxt=$('#typecode').combobox('getText');
	$("#typecode_new").combobox({
	    url : baseURL+"/comm/combobox/getBaseTypesCombobox.json",//返回json数据的url
	    valueField : "typecode",//这个id和你返回json里面的id对应
	    textField : "typename", //这个text和你返回json里面的text对应
	    onLoadSuccess: function () { //加载完成后,设置选中第一项
                    $(this).combobox("select", comval);
        }
	})
	$('#typecode_txt').val(comval);
	$('#typename_txt').val(comtxt);
	$('#bak1').val('10');
	$('#typeflag').val('10');
}

/**
 * 注册新增页面中下拉列表的onchange事件
 * @returns
 */
$(document).ready(function () {

	$("#typecode_new").combobox({

	onChange: function (n,o) {
		var comval=$('#typecode_new').combobox('getValue');
		var comtxt=$('#typecode_new').combobox('getText');
		$('#typecode_txt').val(comval);
		$('#typename_txt').val(comtxt);
	}

	});

	});

/**
 * 保存基礎類別信息
 */
function saveAdd(){
	//alert($("#add-fm").serializeArray());
	$('#add-fm').form('submit',{
		onSubmit: function(){
			var isValidate = $(this).form('validate');
			if(isValidate){
				//$('#loading').window('open');
			}
			return isValidate;
		},
		url:baseURL+"/sys/basetypeInfo/doBasetypeInfoNew.json",
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
			$('#dataTabel').datagrid('reload'); 
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个基礎類別信息新增'+data.msg+'！',
			});
			//$('#loading').window('close');
		}
	});
}


/**
 * 打开修改窗口
 */
  function openEdit(){
		var rows = $('#dataTabel').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要更新的基础类型信息",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一条基础类型信息进行更新",'info');
			return;
		}
		var row = $('#dataTabel').datagrid('getSelected');
		if (row){
			$('#edit-dlg').dialog('open').dialog('setTitle','修改基础类型信息');
			$('#edit-fm').form('load',row);
		}

	}
  
  /**
   * 保存修改的基础类型信息
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
  		success: function(data){
			//$('#loading').window('close');
			data = eval('('+data+')');
			$('#edit-dlg').dialog('close');
			$('#dataTabel').datagrid('reload'); 
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个基础类别信息修改'+data.msg+'！',
			});
			//$('#loading').window('close');
		}
  	});
  }
  
  function deleterow(){
		var rows = $('#dataTabel').datagrid('getSelections');
		if(rows.length==0){
		$.messager.alert('提示',"请选择你要删除的基础类型信息",'info');
		return;
	}
		$.messager.confirm('提示','确定要删除所选基础类型信息吗?',function(result){
      if (result){
      	var ps = "";
      	$.each(rows,function(i,n){
      		if(i==0) 
      			ps += "?id="+n.id;
      		else
      			ps += "&id="+n.id;
      	});
      	$.post(baseURL+'/sys/basetypeInfo/doBasetypeinfoDel.json'+ps,function(data){
	        	$('#dataTabel').datagrid('reload'); 
	        	//console.log("del--"+data);
      		$.messager.show({
					title : '提示',
					msg :  data.total+'个基础类型信息被删除'+data.msg+'！',
				});
      	});
      }
  });
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