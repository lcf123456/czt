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
	//alert(baseURL+"/sq/starinfo/getStarinfo.json");
	$('#dataTable').datagrid({
		title:'装卸组信息维护', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选		//onCheckSelect:true,
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。		striped: true, //奇偶行颜色不同		collapsible:true,//可折叠		url:baseURL+"/gis/groupinfo/getGroupinfoVoList.json", //数据来源
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
		pageSize : 1,
		rownumbers:true, //显示行号
		rowStyler:function(index,row){
				},
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'groupcode',title:'装卸组编号',width:15,
				formatter:function(value,row,index){return row.groupcode;} //需要formatter一下才能显示正确的数据
			},
			{field:'groupname',title:'装卸组名称',width:15,
				formatter:function(value,row,index){return row.groupname;}
			},
			{field:'membername',title:'装卸组人员',width:15,
				formatter:function(value,row,index){return row.membername;}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题			$('#tabdiv .panel-header').css('display','none');

		}
	});
});

/**
 * 打开装卸组信息新增窗口
 */
function newadd(){
	initDept();
	$('#add-dlg').dialog('open').dialog('setTitle','新增装卸组');
	$('#add-fm').form('reset');
}

function initDept(){
$("#deptid").combobox({
	url : baseURL+"/sys/user/getDeptCombobox.json",//返回json数据的url
	valueField : "id",//这个id和你返回json里面的id对应
	textField : "deptname", //这个text和你返回json里面的text对应
	onSelect: function (row1){
		if (row1 != null) {
			$.ajax({
            	url : baseURL+"/sys/user/getUserListByDeptID.json?depId="+row1.id,//返回json数据的url
            	//valueField : "id",//这个id和你返回json里面的id对应
            	//textField : "username", //这个text和你返回json里面的text对应
            	//required:true,
            	success : function(data) {
   				 $(".gridtable tr").remove(".dynamic-tr");
   				var listTmp = "";
   	             $.each(data, function(i, user) {
   	            	listTmp = listTmp +"<tr class='dynamic-tr' ><td>"+user.id+"</td><td>"+user.username+"</td>"+
   	            	"<td><input name='rad' id=rad"+i+" type='radio' onClick=radClick('"+user.id+"','"+user.username+"') value="+user.deptid+"></td></tr>";
   	             });
   	          $(".gridtable tbody").append (listTmp);
   	            //alert(listTmp);
  	           if(listTmp==""){
  	        	$.messager.alert('系统提示', '该部门人员为零！', 'warning');
  	           }
  	         },
  	         error: function(e) { 
  	        	$.messager.alert('系统提示', '部门人员查询出错！', 'warning');
  	        	}
		});
	}
	}
});
}

function radClick(id,username){
	$('#member').textbox("setValue",id);
	$('#membername').textbox("setText",username);
}


/**
 * 保存新建装卸组信息
 */
function saveAdd(){
	var url = "";
	var member= $("#member").val(); 
	var membername= $("#membername").val(); 
	if(groupcode==null||groupcode==""){
		$.messager.alert('系统提示', '请选择耗用部门！', 'warning');
		return;
	}
	if(groupgname==null||groupname==""){
		$.messager.alert('系统提示', '请选择物资类别！', 'warning');
		return;
	}
	$('#add-fm').form('submit',{
		onSubmit: function(){
			var isValidate = $(this).form('validate');
			if(isValidate){
			}
			return isValidate;
		},
		url:baseURL+"/gis/groupinfo/doinsertGroupinfoVo.json",
		data:$("#add-fm").serializeArray(),
		beforeSend : function () {
			$.messager.progress({
				text : '正在新增中...',
			});
		},
		success: function(data){
			data = eval('('+data+')');
			$('#add-dlg').dialog('close');
			$('#dataTable').datagrid('reload'); 
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个装卸组信息新增'+data.msg+'！',
			});
    		$('#add-fm').form('clear');
			//$('#loading').window('close');
		}	
	});
}

//function saveNew(){
//	var url = "";
//	if(id==0){
//		url=baseURL+"/gis/groupinfo/doinsertGroupinfoVo.json?member="+id+"&membername="+username;
//		
//	}
//	else{//继续领用
//		//获取领用单信息 填充备注
//		//清空物资类别等信息
//		url= baseURL+"/gis/groupinfo/doinsertGroupinfoVo.json?member="+id+"&membername="+username;
//	}
//	saveNewStart(url);
//}
///**
// * 提交新增
// */
//function saveNewStart(url){
//	var groupcode= $("#groupcode").val(); 
//	var groupname= $("#groupname").val(); 
//	var member= $('#id').val();
//	var membername= $("#username").val(); 
//	if(groupcode==null||groupcode==""){
//		$.messager.alert('系统提示', '请填写装卸组编号！', 'warning');
//		return;
//	}
//	if(groupname==null||groupname==""){
//		$.messager.alert('系统提示', '请选填写装卸组名称！', 'warning');
//		return;
//	}
//	$('#add-fm').form('submit',{
//		onSubmit: function(){
//			var isValidate = $(this).form('validate');
//			if(isValidate){
//			}
//			return isValidate;
//
//		},
//		url:url,
//		data:$("#add-fm").serializeArray(),
//		beforeSend : function () {
//			$.messager.progress({
//				text : '正在新增中...',
//			});
//		},
//		success: function(data){
//				//$('#loading').window('close');
//				data = eval('('+data+')');
//				$('#add-dlg').dialog('close');
//				$('#dataTable').datagrid('reload'); 
//	    		$.messager.show({
//					title : '提示',
//					msg :  data.total+'个装卸组信息新增'+data.msg+'！',
//				});
//	    		//$('#add-fm').form('clear');
//				//$('#loading').window('close');
//    		getsplapplylistline();
//		}
//	});
//}
//
//function getsplapplylistline()
//{
//		//获取领料新增分配的物资明细列表
//	$('#listdataTable').datagrid({
//		title:'装卸组人员列表', //标题
//		method:'post',
//		iconCls:'icon-view', //图标
//		singleSelect:false, //多选
//		height:200, //高度
//		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
//		striped: true, //奇偶行颜色不同
//		collapsible:false,//可折叠
//		url:baseURL+"/gis/groupinfo/getGroupUserList.json?groupcode="+groupcode, 
//		sortName: 'id', //排序的列
//		sortOrder: 'desc', //倒序
//		remoteSort: true, //服务器端排序
//		idField:'id', //主键字段
//		pagination:false, //显示分页
//		//pageSize : 1,
//		rownumbers:true, //显示行号
//		columns:[[
//				{field:'id',checkbox:true,width:2}, //显示复选框
//				{field:'groupcode',title:'装卸组编号',width:15,
//					formatter:function(value,row,index){return row.groupcode;} //需要formatter一下才能显示正确的数据
//				},
//				{field:'groupname',title:'装卸组名称',width:15,
//					formatter:function(value,row,index){return row.groupname;}
//				},
//				{field:'membername',title:'装卸组人员',width:15,
//					formatter:function(value,row,index){return row.membername;}
//				}
//			]],
//		onLoadSuccess:function(){
//			$('#listtabdiv .panel-header').css('display','none'); 
//			
//		}
//	});
//	
//	//移除本次新增的数据
//	$(".gridtable tr").remove(".dynamic-tr");
//	$('#typename').textbox("setValue","");
//	$('#typename').textbox("setText","");
//	$('#surplusqty').textbox("setValue","");
//	$('#surplusqty').textbox("setText","");
//	$('#unit').textbox("setValue","");
//	$('#unit').textbox("setText","");
//	}
//
///**
// * 打开修改窗口
// */
//  function openEdit(){
//	    $('#edit-fm').form('clear');
//		var rows = $('#dataTable').datagrid('getSelections');
//		if(rows.length==0){
//			$.messager.alert('提示',"请选择你要修改的装卸组信息",'info');
//			return;
//		}
//		if(rows.length > 1){
//			$.messager.alert('提示',"只能选择一条装卸组信息进行更新",'info');
//			return;
//		}
//		var viewRow = $('#dataTable').datagrid('getSelected');
//		if (viewRow!=null){
//			$("#v-groupcode").html(viewRow.groupcode);}
//		var row = $('#dataTable').datagrid('getSelected');
//		if (row){
//			$('#edit-dlg').dialog('open').dialog('setTitle','装卸组信息');
//			$('#edit-fm').form('load',row);
//			//url = '/BS56/sys/roleNew.json';
//		}
//	}
  
  /**
   * 保存修改的参数信息   */
  function saveEdit1111(){
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
			$('#dataTable').datagrid('reload'); 
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个装卸组信息修改'+data.msg+'！',
			});
			//$('#loading').window('close');
		}
  	});
  }
  
  function saveEdit(){
		$('#edit-fm').form('submit',{
			onSubmit: function(){
				var isValidate = $(this).form('validate');
				if(isValidate){
					//$('#loading').window('open');
				}
				return isValidate;
			},
			url:baseURL+"/gis/groupinfo/doupdateGroupinfoVo.json",
			data:$("#edit-fm").serializeArray(),
			beforeSend : function () {
				$.messager.progress({
					text : '正在修改中...',
				});
			},
			success: function(data){
				//$('#loading').window('close');
				data = eval('('+data+')');
				$('#edit-dlg').dialog('close');
				$('#dataTable').datagrid('reload'); 
	    		$.messager.show({
					title : '提示',
					msg :  data.total+'个装卸组信息已修改'+data.msg+'！',
				});
				$('#loading').window('close');
			}
		});
	}
  
//删除
	function deleterow(){
		var rows = $('#dataTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要删除的装卸组信息",'info');
			return;
		}
				
		$.messager.confirm('提示','确定要删除吗?',function(result){
			if (result){
        	var ps = "";
        	$.each(rows,function(i,n){
        		if(i==0) 
        			ps += "?groupcode="+n.groupcode;
        		else
        			ps += "&groupcode="+n.groupcode;
        	});
        	$.post(baseURL+'/gis/groupinfo/dodelGroupinfoVo.json'+ps,
        	function(data){
	        	$('#dataTable').datagrid('reload'); 
	        	//console.log("del--"+data);
        		$.messager.show({
					title : '提示',
					msg :  data.total+'条装卸组信息被删除'+data.msg+'！',
				});
        	});
        }
    });
	}
	
	
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		$('#vehicles').textbox("setValue","10");
		$('#vehicles').textbox("setText","配送车辆");
		searchVehicle();
	}
	
	
	//function openGrant(){
//	
//	var row= $('.gridtable').datagrid('getSelections');
//	//console.log(row.groupcode);
//	parent.addTab('添加用户',baseURL+"/chooseUser?optype=groupuser",'icon-mygroup');
//}
//
//function openTopWindow(url, title, width, height) {
//    title = title == undefined ? '&nbsp;' : title;
//    width = width == undefined ? 800 : width;
//    height = height == undefined ? 300 : height;
//    if (url != undefined) {
//        var content = '<iframe name="eui-open-page" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>';
//        parent.$('#openWindow').window({
//            title: title,
//            width: width,
//            height: height,
//            content: content,
//            modal: true,
//            animate: true,
//            minimizable: false
//        });
//    }
//}
//
//var url;
//
////添加选项卡  
//function addTab(id,title, url){           
//        content = '<iframe scrolling="no" frameborder="0"  src="' + url+ '" style="width:100%;height:100%;"></iframe>';  
//        if(!$("#tabs-box").tabs('exists', title)){
//            $("#add-dlg").tabs("add", { 
//         member : id,
//         membername : title,  
//         closable :  false,  
//         collapsible : true,
//         content : content,  
//         width: 600 ,  
//         height: 400,  
//         selected : true,
//         iconCls : 'icon-add',
//         border:true
//        } );                   
//    }else{  
//         $('##add-dlg').tabs('membername', title);  
//    } 
//        
//        
//} 