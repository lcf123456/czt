/**
 * 页面列表datagrid初始化
 */

jQuery(function($){
	var nowTime01 = getDateYM01();
	var nowTime = getDateYMD();
	$('#searchtime').datebox("setValue",nowTime01);
	$('#searchtime2').datebox("setValue",nowTime);
	//initdatetype();
	//var obj = $("#consignsorsearch");
	//initconsignsor(obj);
	initctype();
	$('#dataTabel').datagrid({
		title:'绩效考核', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //单选
		height:490, //高度
		fitColumns: false, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		//货主默认为长沙11430101
		nowrap:false,
		url:baseURL+"/perform/deptmonthsum/getDeptmonthsumPageList.json?ctype=10", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 15,
		pageList: [15,30,50],
		queryParams:{
			}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'selfuser',title:'姓名',width:$(this).width()*0.2,sortable:true,
				formatter:function(value,row,index){return row.selfuser;}
			},
			{field:'selfuser',title:'岗位',width:$(this).width()*0.2,
				formatter:function(value,row,index){return row.selfuser;}
			},
			{field:'scoremonth',title:'考核时间',width:$(this).width()*0.2,
				formatter:function(value,row,index){return row.scoremonth;}
			},
			{field:'createtime',title:'记录日期',width:$(this).width()*0.2,
				formatter: formatDatebox //需要formatter一下才能显示正确的数据
			},
			{field:'statusname',title:'考评状态',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.statusname;}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none'); 
			
		}
	});
	

});

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
	parent.addTab('绩效新增',baseURL+"/perform/deptmonthsum/todeptmonthsumadd",'icon-mygroup');
}

/**
 * 修改
 */
function edit(){
	var rows = $('#dataTabel').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择你要操作的信息",'info');
		return;
	}
	if(rows.length > 1){
		$.messager.alert('提示',"只能选择一条信息进行操作",'info');
		return;
	}
	
	var row = $('#dataTabel').datagrid('getSelected');
	var id = row.id;
	var status = row.status;
	if(status != "10"){
		$.messager.alert('提示',"对不起，该信息已经提交,不能再次修改",'info');
		return;
	}
	parent.addTab('绩效修改',baseURL+"/perform/deptmonthsum/todeptmonthsumedit?obid="+id,'icon-mygroup');
	
}

/**
 * 它评
 */
function doAuditOne(){
	var rows = $('#dataTabel').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择你要操作的信息",'info');
		return;
	}
	if(rows.length > 1){
		$.messager.alert('提示',"只能选择一条信息进行操作",'info');
		return;
	}
	
	var row = $('#dataTabel').datagrid('getSelected');
	var id = row.id;
	var status = row.status;
	if(status != "20"){
		$.messager.alert('提示',"对不起，该信息还未提交或者已经处理完成,不能再次它评",'info');
		return;
	}
	parent.addTab('部长评分',baseURL+"/perform/deptmonthsum/todeptmonthsumauditone?obid="+id,'icon-mygroup');
	
}

/**
 * 总评
 */
function doAuditTwo(){
	var rows = $('#dataTabel').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择你要操作的信息",'info');
		return;
	}
	if(rows.length > 1){
		$.messager.alert('提示',"只能选择一条信息进行操作",'info');
		return;
	}
	
	var row = $('#dataTabel').datagrid('getSelected');
	var id = row.id;
	var status = row.status;
	if(status != "30"){
		$.messager.alert('提示',"对不起，该信息还未提交或者部长未评分,暂不能总评",'info');
		return;
	}
	parent.addTab('总经理评',baseURL+"/perform/deptmonthsum/todeptmonthsumaudittwo?obid="+id,'icon-mygroup');
	
}

function dopost(){
	var rows = $('#dataTabel').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择你要操作的信息",'info');
		return;
	}
	if(rows.length > 1){
		$.messager.alert('提示',"只能选择一条信息进行操作",'info');
		return;
	}
	var row = $('#dataTabel').datagrid('getSelected');
	var id = row.id;
	var status = row.status;
	if(status != "10"){
		$.messager.alert('提示',"对不起，该信息已经提交",'info');
		return;
	}
	
	$.messager.confirm('提示','确定要提交吗?',function(result){
        if (result){
        	$.post(baseURL+'/perform/deptmonthsum/doPost.json?obid='+id,function(data){
	        	$('#dataTabel').datagrid('reload'); 
        		$.messager.show({
					title : '提示',
					msg :  data.total+'个信息操作'+data.msg+'！',
				});
        	});
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
	var ctypevalue = $("input[name='ctype_new']:checked").val();  
	$('#ctype_new').textbox("setValue",ctypevalue);//通过隐藏变量设置考核类别时奖励还是考核
	var amount_new = $("#amount_new").val();
	 var content_new = $("#content_new").val();
	 
	 var cktype = $('#checktype_new').combobox('getValue')
	 
	  if(amount_new=="" || parseFloat(amount_new)==0 || isNaN(parseFloat(amount_new))){
		  $.messager.alert('提示',"请输入考核金额或您输入的考核金额为0，请重新输入！",'info');
			return;
	  }
	  if(content_new==""){
		  $.messager.alert('提示',"请输入考核内容！",'info');
			return;
	  }
	  if(cktype==""){
		  $.messager.alert('提示',"请输检查项目！",'info');
			return;
	  }
	 
	  $('#add-fm').form('submit',{
		  url:baseURL+"/perform/transverseAssess/doSave.json", 
			success : function(data) {
				data = eval('('+data+')');
				$('#add-dlg').dialog('close');
				$('#dataTabel').datagrid('reload'); 
				$.messager.show({
					title : '提示',
					msg :  '横向考核'+data.msg+'！',
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
			initwklist(row.id);
		}
	}
  
	//出库单明细列表
	  function initwklist(obid)
	  {
	  		//获取领料新增分配的物资明细列表
		  
	  $('#worklist').datagrid({
	  		title:'考核列表', //标题
	  		method:'post',
	  		iconCls:'icon-view', //图标
	  		singleSelect:false, //多选
	  		height:300, //高度
	  		fitColumns: false, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
	  		striped: true, //奇偶行颜色不同
	  		collapsible:false,//可折叠
	  		nowrap:false,
	  		url:baseURL+'/perform/deptmonthsum/getDeptmonthListByid.json?obid='+obid, 
	  		//sortName: 'id', //排序的列
	  		//sortOrder: 'desc', //倒序
	  		remoteSort: true, //服务器端排序
	  		//idField:'id', //主键字段
	  		singleSelect: true,
	  		pagination:false, //显示分页
	  		//pageSize : 1,
	  		rownumbers:true, //显示行号
	  		onClickCell: onClickCell,
	  		columns:[[
	  			{field:'typename',title:'类别',width:$(this).width()*0.1,
	  				formatter:function(value,row,index){return row.typename;}
	  			},
	  			{field:'content',title:'考核项目',width:$(this).width()*0.2,
	  				formatter:function(value,row,index){return row.content;} //需要formatter一下才能显示正确的数据
	  			},
	  			{field:'scorevalue',title:'分值',width:$(this).width()*0.05,
	  				formatter:function(value,row,index){return row.scorevalue;} //需要formatter一下才能显示正确的数据
	  			},
	  			{field:'process',title:'考核标准',width:$(this).width()*0.2,
	  				formatter:function(value,row,index){return row.process;}
	  			},
	  			{field:'process',title:'完成情况',width:$(this).width()*0.2,
	  				formatter:function(value,row,index){return row.process;}
	  			},
	  			{field:'selfscore',title:'评分',width:$(this).width()*0.1,
	  				formatter:function(value,row,index){return row.selfscore;}
	  			},
	  			{field:'otherscore',title:'它评分',width:$(this).width()*0.1,
	  				formatter:function(value,row,index){return row.otherscore;}
	  			},
	  			{field:'note',title:'加扣分说明',width:$(this).width()*0.1,
	  				formatter:function(value,row,index){return row.note;}
	  			}
	  		]],
	  		onLoadSuccess:function(data){
	  			if(data.total != 0) {
		               $('#worklist').datagrid('selectRow',0);// 关键在这里  
		               var row = $('#worklist').datagrid('getSelected'); 
		                 tmpscore = row.tmpscore;
	               }
	  			$('#worklist').datagrid('appendRow',  
	  	                {  
	  	                	id:0,
	  	                    typename:'合计',  
	  	                    content:' ',
	  	                    scorevalue:' ',
	  	                    process:' ',
	  	                    finishnote:' ',
	  	                    selfscore:100,
	  	                    otherscore:100,
	  	                    note:''
	  	            //合并单元格  
	  	                });  
	  	                var hj  = compute('selfscore');
	  			        $('#worklist').datagrid('updateRow',{index:$('#worklist').datagrid('getRows').length-1,row:{selfscore:hj}});
	  	                var hj  = compute('otherscore');
	  			        $('#worklist').datagrid('updateRow',{index:$('#worklist').datagrid('getRows').length-1,row:{otherscore:hj}});
	  			        
	  			//$('#inboundlinediv .panel-header').css('display','none'); 
//	  			var mark=1;                                                 //这里涉及到简单的运算，mark是计算每次需要合并的格子数
//	  		　　　　for (var i=1; i <data.rows.length; i++) {     //这里循环表格当前的数据
//	  		　　　　　　if (data.rows[i]['typename'] == data.rows[i-1]['typename']) {   //后一行的值与前一行的值做比较，相同就需要合并
//	  		　　　　　　　　mark += 1;                                            
//	  		　　　　　　　　$(this).datagrid('mergeCells',{ 
//	  		　　　　　　　　　　index: i+1-mark,                 //datagrid的index，表示从第几行开始合并；紫色的内容需是最精髓的，就是记住最开始需要合并的位置
//	  		　　　　　　　　　　field: 'typename',                 //合并单元格的区域，就是clomun中的filed对应的列
//	  		　　　　　　　　　　rowspan:mark                   //纵向合并的格数，如果想要横向合并，就使用colspan：mark
//	  		　　　　　　　　}); 
//	  				$(this).datagrid('mergeCells',{ 
//		　　　　　　　　　　index: i+1-mark,                 //datagrid的index，表示从第几行开始合并；紫色的内容需是最精髓的，就是记住最开始需要合并的位置
//		　　　　　　　　　　field: 'scorevalue',                 //合并单元格的区域，就是clomun中的filed对应的列
//		　　　　　　　　　　rowspan:mark                   //纵向合并的格数，如果想要横向合并，就使用colspan：mark
//		　　　　　　　　});
//	  		　　　　　　}else{
//	  		　　　　　　　　mark=1;                                         //一旦前后两行的值不一样了，那么需要合并的格子数mark就需要重新计算
//	  		　　　　　　}
//	  		　　　　}
	  		
	  		}
	  	});
	  	}
	//指定列求和
	    function compute(colName) {
	      var rows = $('#worklist').datagrid('getRows');
	      var total = tmpscore;
	      for (var i = 0; i < rows.length-1; i++) {//最后一行合计不计算
	    	  if(!isNaN(parseFloat(rows[i][colName]))){
	        		total += parseFloat(rows[i][colName]);
	    	  }
	      }
	      return total;
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
			var flag=row.status;
			var id = row.id;
			if(flag=="30"||flag=="40"){//新增和自评提交还可以删除、它评后不能再删除
				$.messager.alert('提示',"您选择的信息领导已经评分，不能再删除！",'info');
				return;
			}
			
			$.messager.confirm('提示','确定要删除吗?',function(result){
	        if (result){
	        	
	        	$.post(baseURL+'/perform/deptmonthsum/doDel.json?obid='+id,function(data){
		        	$('#dataTabel').datagrid('reload'); 
	        		$.messager.show({
						title : '提示',
						msg :  data.total+'个信息被删除'+data.msg+'！',
					});
	        	});
	        }
	    });
		}

		//编辑的行
		  var editIndex = undefined;
		  function endEditing() {
		   if (editIndex == undefined){return true}
		   $('#worklist').datagrid('endEdit', editIndex);
		   editIndex = undefined;
		   return true;
		  }
		   
		  function onClickCell(index, field){
		   if (editIndex != index) {
		    if (endEditing()) {
		     $('#worklist').datagrid('selectRow', index)
		       .datagrid('beginEdit', index);
		     var ed = $('#worklist').datagrid('getEditor', { index: index, field: field });
		     if (ed) {
		      ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
		     }
		     editIndex = index;
		    } else {
		     setTimeout(function () {
		      $('#worklist').datagrid('selectRow', editIndex);
		     }, 0);
		    }
		   }
		  }
		  function onEndEdit(index, row){
		   var ed = $(this).datagrid('getEditor', {
		    index: index,
		    field: 'productid'
		   });
		   row.productname = $(ed.target).combobox('getText');
		  }
		  function append(){
		   var index = $('#worklist').datagrid('getRowIndex', $('#worklist').datagrid('getSelected'));
		   if (index == -1)
		    index = 0;
		   $("#worklist").datagrid("insertRow", {
		    index: index+1,
		    row: {oper: "<a href='javascript:append()'>+<a> <a href='javascript:removeit()'>-<a>",status:'P'}
		    });
		  }
		  function removeit(){
		   if (editIndex == undefined){return}
		   $('#worklist').datagrid('selectRow', editIndex);
		 
		    $('#worklist').datagrid('cancelEdit', editIndex)
		     .datagrid('deleteRow', editIndex);
		   editIndex = undefined;
		  }
		  function accept(){
		   if (endEditing()){
		    var $dg = $('#worklist');
		    var rows = $dg.datagrid('getChanges');
		    if (rows.length) {
		     var inserted = $dg.datagrid('getChanges', "inserted");
		     var deleted = $dg.datagrid('getChanges', "deleted");
		     var updated = $dg.datagrid('getChanges', "updated");
		     var effectRow = new Object();
		     if (inserted.length) {
		      effectRow["inserted"] = JSON.stringify(inserted);
		     }
		     if (deleted.length) {
		      effectRow["deleted"] = JSON.stringify(deleted);
		     }
		     if (updated.length) {
		      effectRow["updated"] = JSON.stringify(updated);
		     }
		     //alert(inserted);
		     //alert(deleted);
		     //alert(updated);
		    }
		   }
		   //$.post("/Home/Commit", effectRow, function (rsp) {
		   // if (rsp) {
		   //  $dg.datagrid('acceptChanges');
		   //  bindData();
		   // }
		   //}, "JSON").error(function () {
		   // $.messager.alert("提示", "提交错误了！");
		   //});
		  }
		  function reject(){
		   $('#worklist').datagrid('rejectChanges');
		   editIndex = undefined;
		  }
		  function getChanges(){
		   var rows = $('#worklist').datagrid('getChanges');
		   alert(rows.length+' rows are changed!');
		  }
		 
		  function contains(arr, obj) {
		   var i = arr.length;
		   while (i--) {
		    if (arr[i] === obj) {
		     return true;
		    }
		   }
		   return false;
		  }
