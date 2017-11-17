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
jQuery(function(def){
	var begdate=getDateYM01();
	var enddate=getDateYMD();
	$('#begdate').datebox('setValue', begdate);
	$('#enddate').datebox('setValue', enddate);
	$("#routecode").combobox({
	    url : baseURL+"/comm/combobox/getRoutesComboboxByDeptid.json?deptid=",//返回json数据的url
	    valueField : "routecode",//这个id和你返回json里面的id对应
	    textField : "routename", //这个text和你返回json里面的text对应
	    loadFilter : function (data) {
	        if (data && data instanceof Array) {
	            data.splice(0, 0, {routecode: '', routename: '请选择车组'});　
	        }
	        return data;
	    },
        onChange: function (n,o) {
			 //console.log("new="+n+",old="+o);
			 search();
		}   	
	})
	$('#keywd').textbox('textbox').keydown(function(e){
		if(e.keyCode==13){
			search();
		}
	})
	$('#dataTable').datagrid({
		//title:'审核定性', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选,当true时只允许当前选择一行。		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠		url:baseURL+"/account/operate/getOperatePageList.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'desc', //正序asc、倒序 desc
		remoteSort: false, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 10,
		pageList: [10,30,50],
		queryParams:{
			status:'30',
			begdate:begdate,
			enddate:enddate
		}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'routecode',title:'车组',width:10,sortable:false,
				formatter:function(value,row,index){return row.routecode;} //需要formatter一下才能显示正确的数据
			},
			{field:'cuscode',title:'专卖证号',width:20,sortable:false,
				formatter:function(value,row,index){return row.cuscode;}
			},
			{field:'cusname',title:'零售户名',width:30,
				formatter:function(value,row,index){return row.cusname;}
			},
			{field:'quantity',title:'条数',width:10,
				formatter:function(value,row,index){return row.quantity;}
			},
			{field:'amount',title:'金额',width:10,
				formatter:function(value,row,index){return row.amount;}
			},
			{field:'reasoncontent',title:'原因',width:30,
				formatter:function(value,row,index){return row.reasoncontent;}
			},
			{field:'orderdate',title:'订单日期',width:10,
				formatter:function(value,row,index){return row.orderdate.substring(0,10);}
			},
			{field:'createname',title:'记录人',width:10,
				formatter:function(value,row,index){return row.createname;}
			},
			{field:'ctype',title:'类型',width:10,
				formatter:function(value,row,index){
					var ctype=row.ctype;
					if(ctype=="10")return "退货";
					else if(ctype=="20")return "暂存";
					else if(ctype=="30")return "组间转货";
					else if(ctype=="40")return "暂存送货";
					else return "未定义";
				}
			},
			{field:'prestatuscontent',title:'结算状态',width:10,
				formatter:function(value,row,index){return row.prestatuscontent;}
			},
			{field:'statuscontent',title:'审核状态',width:10,
				formatter:function(value,row,index){return row.statuscontent;}
			}
	
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题			//$('#tabdiv .panel-header').css('display','none');
		}
	});
});


	  //查询
	function search(){
		var params = $('#dataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#dataTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了	}
	
	//清空查询条件
	function clearForm(){
		$('#keywd').textbox("setValue","");
		$('#keywd').textbox("setText","");
		$('#routecode').combobox("select","");
		
		var begdate=getDateYM01();
		var enddate=getDateYMD();
		$('#begdate').datebox('setValue', begdate);
		$('#enddate').datebox('setValue', enddate);
		
		search();
	}
	
	function doOperate(type){
		var content="退货",ctype="10";
		if(type=='storage'){
			content="暂存";
			ctype="20";
		}
		var rows = $('#dataTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择要确认为"+content+"的信息",'info');
			return;
		}
		$.messager.confirm('提示','确定要将所选信息确认为'+content+'吗?',function(result){
			if (result){
				var ids = "";
				$.each(rows,function(i,n){
					if(i==0){ 
						ids += "?id="+n.id;
					}else{
						ids += "&id="+n.id;
					}	
				});
				$.post(baseURL+'/account/operate/doOperateAudit.json'+ids+'&ctype='+ctype,function(data){
					$('#dataTable').datagrid('reload'); 
					//console.log("del--"+data);
					data = eval('('+data+')');
					$.messager.show({
						title : '提示',
						msg :  data.total+'个'+content+'信息确认'+data.msg+'！',
					});
				});
			}
		})
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