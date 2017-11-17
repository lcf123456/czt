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
	$("#deptid").combobox({
		url : baseURL+"/comm/combobox/getDeptCombobox.json",//返回json数据的url
		valueField : "id",//这个id和你返回json里面的id对应
		textField : "deptname", //这个text和你返回json里面的text对应
		loadFilter : function (data) {
	        if (data && data instanceof Array) {
	            data.splice(0, 0, {id: '', deptname: '--请选择--'});　// 此处空格用全角
	        }
	        return data;},
        onChange: function (n,o) {
			 //console.log("new="+n+",old="+o);
        	searchHazardsAudit();
		}
	})

	$("#hazardstype").combobox({
		url : baseURL+"/safe/hazards/getHazardstypeCombobox.json",//返回json数据的url
    	valueField : "id",//这个id和你返回json里面的id对应
    	textField : "ctype", //这个text和你返回json里面的text对应
    	loadFilter : function (data) {
	        if (data && data instanceof Array) {
	            data.splice(0, 0, {id: '', ctype: '--请选择--'});　// 此处空格用全角
	        }
	        return data;
	    } ,
	onChange: function (n,o) {
		 //console.log("new="+n+",old="+o);
		searchHazardsAudit();
	}
	})
	$("#levelid").combobox({
		url : baseURL+"/safe/hazards/getClevelCombobox.json",//返回json数据的url
    	valueField : "id",//这个id和你返回json里面的id对应
    	textField : "clevel", //这个text和你返回json里面的text对应
    	loadFilter : function (data) {
	        if (data && data instanceof Array) {
	            data.splice(0, 0, {id: '', clevel: '--请选择--'});　// 此处空格用全角
	        }
	        return data;
	    } ,
	onChange: function (n,o) {
		 //console.log("new="+n+",old="+o);
		searchHazardsAudit();
	}
	})
	$('#keyword').textbox('textbox').keydown(function(e){
		if(e.keyCode==13){
			searchHazardsAudit();
		}
	})
	$('#dataTable').datagrid({
		title:'危险源评估', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		url:baseURL+"/safe/hazards/getHazardsAudit.json",  //数据来源
        collapsible:true,
         //data:data,
		queryParams:{
			
			}, //查询条件
		pagination:true, //显示分页
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'hazards',title:'危险源：作业/流程/活动/场所',width:30,sortable:false,
				formatter:function(value,row,index){return row.hazards;} //需要formatter一下才能显示正确的数据
			},
			{field:'hazardstype',title:'危险源类别',width:30,sortable:false,
				formatter:function(value,row,index){return row.hazardstype;}
			},
			{field:'deptname',title:'相关部门',width:12,sortable:false,
				formatter:function(value,row,index){return row.deptname;} 
			},
			{field:'lvalue',title:'L',width:10,sortable:false,
				formatter:function(value,row,index){return row.lvalue;} 
			},
			{field:'evalue',title:'E',width:10,sortable:false,
				formatter:function(value,row,index){return row.evalue;} 
			},
			{field:'cvalue',title:'C',width:10,sortable:false,
				formatter:function(value,row,index){return row.cvalue;} 
			},
			{field:'dvalue',title:'D=LEC',width:10,sortable:false,
				formatter:function(value,row,index){return row.dvalue;} 
			},
			{field:'clevel',title:'风险等级评定',width:10,sortable:false,
				formatter:function(value,row,index){return row.clevel;} 
			},
			{field:'auditname',title:'评估人',width:10,sortable:false,
				formatter:function(value,row,index){return row.auditname;} 
			},
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none');
		}
	});
});
	  //查询
	function searchHazardsAudit(){
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
		$('#queryForm').form('reset');
		searchHazardsAudit();
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