/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	//初始化记录人、记录日期、检查日期、检查部门
	var nowTimeYMD = getDateYMD();
	$('#username_new').textbox("setValue",username);
	$('#username_new').textbox("setText",username);
	
	
	$('#deptid_new').textbox("setValue",deptname);
	$('#deptid_new').textbox("setText",deptname);
	
	
	
    $('#scoremonth_new').datebox({
    	onSelect: function(date){
    		
    		//alert(date.getFullYear()+":"+(date.getMonth()+1)+":"+date.getDate());
    		scoremonth = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
    		$('#worklist').datagrid('reload'); 
    	}
    });
    $('#scoremonth_new').datebox("setValue",nowTimeYMD);//考核日期
   // scoremonth = $('#scoremonth_new').val();
	//initwklist(scoremonth);
});

function closethistab(){
	$.messager.confirm('提示','确定关闭吗?',function(result){
		if (result){
			window.parent.tabCloseForChild("绩效新增");
		}
	});
}
//function initwklist(scoremonth)
//{
//		//获取领料新增分配的物资明细列表
//	  
//$('#worklist').datagrid({
//		title:'考核列表', //标题
//		method:'post',
//		iconCls:'icon-view', //图标
//		height:300, //高度
//		fitColumns: false, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
//		striped: true, //奇偶行颜色不同
//		collapsible:false,//可折叠
//		nowrap:false,
//		url:baseURL+"/perform/keywork/getAllKeyworkList.json?searchdept="+deptid+"&searchtime="+scoremonth, 
//		//sortName: 'id', //排序的列
//		//sortOrder: 'desc', //倒序
//		remoteSort: true, //服务器端排序
////		idField:'id', //主键字段
//		singleSelect: true,
//		//pageSize : 1,
//		rownumbers:true, //显示行号
//		onClickCell: onClickCell,
//		//onEndEdit: onEndEdit,
//		columns:[[
//			{field:'typename',title:'类别',width:$(this).width()*0.1,
//				formatter:function(value,row,index){return row.typename;}
//			},
//			{field:'content',title:'考核项目',width:$(this).width()*0.2,
//				formatter:function(value,row,index){return row.content;} //需要formatter一下才能显示正确的数据
//			},
//			{field:'scorevalue',title:'分值',width:$(this).width()*0.05,
//				formatter:function(value,row,index){return row.scorevalue;} //需要formatter一下才能显示正确的数据
//			},
//			{field:'process',title:'考核标准',width:$(this).width()*0.2,
//				formatter:function(value,row,index){return row.process;}
//			},
//			{field:'process',title:'完成情况',width:$(this).width()*0.2,editor:'textbox'},
//			{field:'selfscore',title:'评分',width:$(this).width()*0.1,editor:{type:'numberbox',options:{precision:2,max:100,min:0}},
//				formatter:function(value,row,index){return row.selfscore;}
//			},
//			{field:'note',title:'加扣分说明',width:$(this).width()*0.1}
//		]],
//		onLoadSuccess:function(data){
//			//$('#inboundlinediv .panel-header').css('display','none'); 
////			var mark=1;                                                 //这里涉及到简单的运算，mark是计算每次需要合并的格子数
////		　　　　for (var i=1; i <data.rows.length; i++) {     //这里循环表格当前的数据
////		　　　　　　if (data.rows[i]['typename'] == data.rows[i-1]['typename']) {   //后一行的值与前一行的值做比较，相同就需要合并
////		　　　　　　　　mark += 1;                                            
////		　　　　　　　　$(this).datagrid('mergeCells',{ 
////		　　　　　　　　　　index: i+1-mark,                 //datagrid的index，表示从第几行开始合并；紫色的内容需是最精髓的，就是记住最开始需要合并的位置
////		　　　　　　　　　　field: 'typename',                 //合并单元格的区域，就是clomun中的filed对应的列
////		　　　　　　　　　　rowspan:mark                   //纵向合并的格数，如果想要横向合并，就使用colspan：mark
////		　　　　　　　　}); 
////				$(this).datagrid('mergeCells',{ 
////	　　　　　　　　　　index: i+1-mark,                 //datagrid的index，表示从第几行开始合并；紫色的内容需是最精髓的，就是记住最开始需要合并的位置
////	　　　　　　　　　　field: 'scorevalue',                 //合并单元格的区域，就是clomun中的filed对应的列
////	　　　　　　　　　　rowspan:mark                   //纵向合并的格数，如果想要横向合并，就使用colspan：mark
////	　　　　　　　　});
////		　　　　　　}else{
////		　　　　　　　　mark=1;                                         //一旦前后两行的值不一样了，那么需要合并的格子数mark就需要重新计算
////		　　　　　　}
////		　　　　}
//		
//		}
//	});
//	}
//
//
////初始被考核部门
//function initdep(obj){
//	obj.combotree({
//      url:baseURL+"/sys/user/getDeptTree.json", //数据来源
//	onLoadSuccess: function (node, data) {
//		if (data != null) { 
//			obj.combotree('setValue', { id: data[0].id, text: data[0].text }); 
//		}
//	}
//    });
//}
//
//
//
//
///**
// * 保存
// * @returns
// */
//function toSave()
//{
//	var ctypevalue = $("input[name='ctype_new']:checked").val();  
//	$('#ctype_new').textbox("setValue",ctypevalue);//通过隐藏变量设置考核类别时奖励还是考核
//	var amount_new = $("#amount_new").val();
//	 var content_new = $("#content_new").val();
//	 
//	 var cktype = $('#checktype_new').combobox('getValue')
//	 
//	  if(amount_new=="" || parseFloat(amount_new)==0 || isNaN(parseFloat(amount_new))){
//		  $.messager.alert('提示',"请输入考核金额或您输入的考核金额为0，请重新输入！",'info');
//			return;
//	  }
//	  if(content_new==""){
//		  $.messager.alert('提示',"请输入考核内容！",'info');
//			return;
//	  }
//	  if(cktype==""){
//		  $.messager.alert('提示',"请输检查项目！",'info');
//			return;
//	  }
//	 
//	  $('#add-fm').form('submit',{
//		  url:baseURL+"/perform/transverseAssess/doSave.json", 
//			success : function(data) {
//				data = eval('('+data+')');
//				$('#add-dlg').dialog('close');
//				$('#dataTabel').datagrid('reload'); 
//				$.messager.show({
//					title : '提示',
//					msg :  '横向考核'+data.msg+'！',
//				});
//	         },
//	         error: function(e) { 
//	        	 $.messager.show({
//	 				title : '提示',
//	 				msg :  '新增出错，请联系管理员!',
//	 			});
//	          	} 
//	  });
//	  
//
//	}


