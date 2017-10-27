var url;
var islegal = "0";


/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	$('#dataTabel').datagrid({
		title:'角色维护', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/cost/SPLApplyList/getSPLApplyList.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 20,
		pageList: [20,30,50],
		queryParams:{
			}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'id2',title:'id',width:10,sortable:true,
				formatter:function(value,row,index){return row.id;} //需要formatter一下才能显示正确的数据
			},
			{field:'deptname',title:'部门',width:10,
				formatter:function(value,row,index){return row.deptname;}
			},
			{field:'applyusername',title:'申请人',width:10,sortable:true,
				formatter:function(value,row,index){return row.applyusername;} //需要formatter一下才能显示正确的数据
			},
			{field:'applydate',title:'申请时间',width:10,sortable:true,
				formatter:function(value,row,index){return row.applydate;}
			},
			{field:'mngusername',title:'审核人',width:10,sortable:true,
				formatter:function(value,row,index){return row.mngusername;}
			},
			{field:'auditflagname',title:'审核状态',width:10,sortable:true,
				formatter:function(value,row,index){return row.auditflagname;}
			},
			{field:'remarks',title:'备注信息',width:30,
				formatter:function(value,row,index){
					//return row.deptName;  //该列的值是deptId，显示的是deptName
					return row.remarks;  
				}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none'); 
			
		}
	});
	
	
});

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
	$('#queryForm').form('clear');
	searchUser();
}

/**
 * 打开新增窗口
 */
function openCreate(){
	$('#add-fm').form('clear');
	$('#add-dlg').dialog('open').dialog('setTitle','领料申请');
	// $('#applyusername').textbox('setText', username);
	$("#splchoose-div" ).css("display", "block"); //类型显示
	initSplType();
	initDept();
	var nowTime = getDateYMD();
	 $('#applydate').textbox('setText', nowTime);
	 $("#audit-div" ).css("display", "none"); //审核不显示
	 $("#listtabdiv" ).css("display", "none"); //列表不显示
	 $("#purpose").textbox('textbox').attr('readonly',false);//移除只读，在审核窗口设置过为只读
	 $('#btn-audit').linkbutton('disable'); 
	 $('#btn-back').linkbutton('disable'); 
	 $('#applyusername').textbox('setText', username);
	 
}

function initDept(){
	//部门下拉列表
    $("#deptid").combobox({
    	url : baseURL+"/sys/user/getDeptCombobox.json",//返回json数据的url
    	valueField : "id",//这个id和你返回json里面的id对应
    	textField : "deptname", //这个text和你返回json里面的text对应
    	onLoadSuccess: function () { //加载完成后,设置选中第一项
                    $(this).combobox("select", deptid);  
        }
    	//panelHeight: 'auto'
    })
}
function initSplType()
{
	//物资类别一级
	$("#lvl1").combobox({
		url : baseURL+"/cost/SPLTypeInfo/getSPLTypesCombobox.json?clevel=2&fid=2&reqType=forComb",//返回json数据的url
		valueField : "id",//这个id和你返回json里面的id对应
		textField : "typename", //这个text和你返回json里面的text对应
		//required:true,
		onSelect: function (row1) {  
	        if (row1 != null) {  
	        	//物资类别二级
	            $('#lvl2').combobox({  
	            	url : baseURL+"/cost/SPLTypeInfo/getSPLTypesCombobox.json?clevel=3&fid="+row1.id+"&reqType=forComb",//返回json数据的url
	                valueField : "id",//这个id和你返回json里面的id对应
	            	textField : "typename", //这个text和你返回json里面的text对应
	            	//required:true,
	            	onSelect: function (row2) {  
	                    if (row2 != null) {  
	                    	$('#lvl3').combobox({ 
	                    		url : baseURL+"/cost/SPLTypeInfo/getSPLTypesCombobox.json?clevel=4&fid="+row2.id+"&reqType=forComb",//返回json数据的url
	        	                valueField : "id",//这个id和你返回json里面的id对应
	        	            	textField : "typename", //这个text和你返回json里面的text对应
	        	            	onSelect: function (row3) {  
	        	            		//物资信息
	     	                       // $('#typeid').combobox({  
	     	                    	$.ajax({
	     	                        	url : baseURL+"/cost/stock/getStockDetailList.json?typeid="+row3.id,//返回json数据的url
	     	                        	//valueField : "id",//这个id和你返回json里面的id对应
	     	                        	//textField : "typename", //这个text和你返回json里面的text对应
	     	                        	//required:true,
	     	                        	success : function(data) {
	     	               				 $(".gridtable tr").remove(".dynamic-tr");
	     	               				var listTmp = "";
	     	               	             $.each(data.rows, function(i, cust) {
	     	               	            	listTmp = listTmp +"<tr class='dynamic-tr' ><td>"+cust.id+"</td><td>"+cust.splname+"</td><td>"+cust.quantity+"</td><td>"+cust.unitid+"</td>"+
	     	               	            	"<td><input name='rad' id=rad"+i+" type='radio' onClick=radClick('"+cust.id+"','"+cust.splname+"','"+cust.quantity+"','"+cust.unitid+"','"+cust.typeid+"',"+cust.price+") value="+cust.id+"></td></tr>";
	     	               	             });
	     	               	           $(".gridtable tbody").append (listTmp);
	     	               	           if(listTmp==""){
	     	               	        	$.messager.alert('系统提示', '该物资库存为零或请输入正确的物资类型查询！', 'warning');
	     	               	           }
	     	               	         },
	     	               	         error: function(e) { 
	     	               	        	$.messager.alert('系统提示', '该物资库存为零或请输入正确的物资类型查询！', 'warning');
	     	               	          	} 
	     	                    	});
	        	            	}
	                    	});
	                    	
	                        	//onSelect: function (row3) {
	                        	//	$('#unitid').val(row3.unit);
	                        	//}
	                     //   });  
	                    }  
	                }  
	            });  
	        }  
	    }  
	})
	}

function radClick(id,typename,canUseQty,unit,typeid,price){
	$('#splid').val(id);
	$('#typeid').val(typeid);
	$('#price').val(price);
	$('#typename').textbox("setValue",typename);
	$('#typename').textbox("setText",typename);
	$('#surplusqty').textbox("setValue",canUseQty);
	$('#surplusqty').textbox("setText",canUseQty);
	$('#unit').textbox("setValue",canUseQty);
	$('#unit').textbox("setText",unit);
}

//查找类别
function searchType(){
	//先选择车组
	//var routecode= $("#croutecode").val(); 
	var condname= $("#condname").val(); 
		if(condname==null||condname=="")
			{
			$.messager.alert('系统提示', '请输入查询信息！', 'warning');
			return;
			}
		//根据车组返回零售户信息getCustListByRouteCode
		$.ajax({
			url:baseURL+"/cost/suppliesimp/getSuppliesImpListByCond.json?splname="+condname, //此处fid无用
			success : function(data) {
				 $(".gridtable tr").remove(".dynamic-tr");
				var listTmp = "";
	             $.each(data.rows, function(i, cust) {
	            	 listTmp = listTmp +"<tr class='dynamic-tr' ><td>"+cust.id+"</td><td>"+cust.splname+"</td><td>"+cust.quantity+"</td><td>"+cust.unitid+"</td>"+
	            	 "<td><input name='rad' id=rad"+i+" type='radio' onClick=radClick('"+cust.id+"','"+cust.splname+"','"+cust.quantity+"','"+cust.unitid+"','"+cust.typeid+"',"+cust.price+") value="+cust.id+"></td></tr>";
	             });
	           $(".gridtable tbody").append (listTmp);
	           if(listTmp==""){
      	        	$.messager.alert('系统提示', '该物资库存为零或请输入正确的物资类型查询！', 'warning');
      	           }
	         },
	         error: function(e) { 
	          	} 
		})
}

function saveNew(){
	var url = "";
		url= baseURL+"/cost/SPLApplyList/doSaveForConsume.json?reqType=second&listid="+listid;
	saveNewStart(url);
}
/**
 * 提交新增
 */
function saveNewStart(url){
	var typename= $("#typename").val(); 
	var surplusqty= $('#surplusqty').val();
	var applyqty= $("#applyqty").val(); 
	$('#applyid').val(userid);
	if(typename==null||typename==""){
		$.messager.alert('系统提示', '请选择物资类别！', 'warning');
		return;
	}
	if(applyqty==null||applyqty==""){
		$.messager.alert('系统提示', '请输入申请数量！', 'warning');
		return;
	}
	if((parseInt(surplusqty)-parseInt(applyqty))<0){
		$.messager.alert('系统提示', '您的申请数量超过了库存数量！', 'warning');
		return;
	}
	$('#add-fm').form('submit',{
		onSubmit: function(){
			var isValidate = $(this).form('validate');
			if(isValidate){
			}
			return isValidate;

		},
		url:url,
		data:$("#add-fm").serializeArray(),
		beforeSend : function () {
			$.messager.progress({
				text : '正在新增中...',
			});
		},
		success: function(data){
			
			data = eval('('+data+')');
			listid = data.listid;
			//$('#add-dlg').dialog('close');
			$('#dataTabel').datagrid('reload'); 
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个领料申请'+data.msg+'！',
			});
    		$("#listtabdiv" ).css("display", "block");
    		getsplapplylistline();
		}
	});
}

function getsplapplylistline()
{
	var editRow = undefined; //定义全局变量：当前编辑的行
		//获取领料新增分配的物资明细列表
	$('#listdataTabel').datagrid({
		title:'事情物品列表', //标题
		method:'post',
		iconCls:'icon-view', //图标
		singleSelect:false, //多选
		height:200, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		url:baseURL+"/cost/SPLApplyList/getSPLApplyListLine.json?listid="+listid, 
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		pagination:false, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',title:'id',width:$(this).width()*0.05,sortable:true,
				formatter:function(value,row,index){return row.id;} //需要formatter一下才能显示正确的数据
			},
			{field:'typename',title:'类型',width:$(this).width()*0.15,
				formatter:function(value,row,index){return row.typename;}
			},
			{field:'splname',title:'物品',width:$(this).width()*0.2,sortable:true,
				formatter:function(value,row,index){return row.splname;} //需要formatter一下才能显示正确的数据
			},
			{field:'editapplyqty',title:'申请数量',width:$(this).width()*0.1,sortable:true,editor:'numberbox',
				formatter:function(value,row,index){return row.applyqty;}
			},
			{field:'quantity',title:'剩余数量',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.quantity;}
			},
			{field:'price',title:'单价',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.price;}
			},
			{field:'amount',title:'总金额',width:$(this).width()*0.1,sortable:true,
				formatter:function(value,row,index){return row.amount;}
			},
			{field:'applyqty',title:'原始申请数量',hidden:true,
				formatter:function(value,row,index){return row.applyqty;}
			},
		]],
		onLoadSuccess:function(data){
			$('#listtabdiv .panel-header').css('display','none'); 
			islegal = data.islegal;
			//console.log("islegal = "+islegal);
		},
		toolbar: [
         { text: '修改', iconCls: 'icon-edit', handler: function () {
             //修改时要获取选择到的行
             var rows = $('#listdataTabel').datagrid("getSelections");
             //如果只选择了一行则可以进行修改，否则不操作
             if (rows.length == 1) {
                 //修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
                 if (editRow != undefined) {
                     $('#listdataTabel').datagrid("endEdit", editRow);
                 }
                 //当无编辑行时
                 if (editRow == undefined) {
                     //获取到当前选择行的下标
                     var index = $('#listdataTabel').datagrid("getRowIndex", rows[0]);
                     //开启编辑
                     $('#listdataTabel').datagrid("beginEdit", index);
                     //把当前开启编辑的行赋值给全局变量editRow
                     editRow = index;
                     //当开启了当前选择行的编辑状态之后，
                     //应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
                     $('#listdataTabel').datagrid("unselectAll");
                 }
             }
         }
         }, '-',
         { text: '保存', iconCls: 'icon-save', handler: function () {
             //保存时结束当前编辑的行，自动触发onAfterEdit事件如果要与后台交互可将数据通过Ajax提交后台
             $('#listdataTabel').datagrid("endEdit", editRow);
         }
         }, '-',
         { text: '取消编辑', iconCls: 'icon-redo', handler: function () {
             //取消当前编辑行把当前编辑行罢undefined回滚改变的数据,取消选择的行
             editRow = undefined;
             $('#listdataTabel').datagrid("rejectChanges");
             $('#listdataTabel').datagrid("unselectAll");
         }
         },'-', { text: '删除', iconCls: 'icon-remove', handler: function () {
             //删除时先获取选择行
             var rows = $('#listdataTabel').datagrid("getSelections");
             //选择要删除的行
     		if(rows.length==0){
     			$.messager.alert('提示',"请选择您要删除的信息。",'info');
     			return;
     		}
     		if(rows.length > 1){
     			$.messager.alert('提示',"只能选择一条信息进行查看",'info');
     			return;
     		}
     		
     		var row = $('#listdataTabel').datagrid('getSelected');
     		
                 $.messager.confirm("提示", "你确定要删除吗?", function (r) {
                     if (r) {
                             //$('#editapplyqty').val("0");//删除即将申请数量修改为0
                             row.editapplyqty="0";
                             $.post(baseURL+"/cost/SPLConsume/doEditConsume.json", row, function(data) {
             					$.messager.show({
             						title : '提示',
             						msg :  '1个申请删除成功！',
             					});
             				});
                         //将选择到的行存入数组并用,分隔转换成字符串，
                         //本例只是前台操作没有与数据库进行交互所以此处只是弹出要传入后台的id
                         //alert(ids.join(','));
                     }
                 });
                 $('#listdataTabel').datagrid('reload'); 
         }
         }, '-',],
        onAfterEdit: function (rowIndex, rowData, changes) {
            //endEdit该方法触发此事件
            //console.info(rowData);
            var editapplyqty = rowData.editapplyqty;
            var quantity = rowData.quantity;
            var bz=0;
            if (parseInt(editapplyqty).toString() == "NaN"){
            	$.messager.alert('提示',"请输入合法的数字！",'info');
            	bz=1;
            }
            if(parseInt(editapplyqty)>quantity){
            	$.messager.alert('提示',"对不起，申请数量需小于剩余数量。",'info');
            	bz=1;
            }
            if(bz==0){//申请数量符合条件，提交修改
            	$.post(baseURL+"/cost/SPLConsume/doEditConsume.json", rowData, function(data) {
					$.messager.show({
						title : '提示',
						msg :  data.total+'个类型'+data.msg+'！',
					});
				});
            }
            $('#listdataTabel').datagrid('reload'); 
            editRow = undefined;
        },
        onDblClickRow: function (rowIndex, rowData) {
        //双击开启编辑行
            if (editRow != undefined) {
                $('#listdataTabel').datagrid("endEdit", editRow);
            }
            if (editRow == undefined) {
                $('#listdataTabel').datagrid("beginEdit", rowIndex);
                editRow = rowIndex;
            }
        }
	});
	
	//移除本次新增的数据
	$(".gridtable tr").remove(".dynamic-tr");
	$('#typename').textbox("setValue","");
	$('#typename').textbox("setText","");
	$('#surplusqty').textbox("setValue","");
	$('#surplusqty').textbox("setText","");
	$('#unit').textbox("setValue","");
	$('#unit').textbox("setText","");
	}

function formatOper(){  
    alert("a");
}  
/**
 * 查看窗口
 */
  function viewD(){
	  $('#add-fm').form('clear');
	  var rows = $('#dataTabel').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要查看的领料信息",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一条领料信息进行查看",'info');
			return;
		}
		
		var row = $('#dataTabel').datagrid('getSelected');
		var auditflag = row.auditflag;//审核状态
		//未审核时不现实审核div
		if(auditflag=="0"){
			$("#audit-div" ).css("display", "none"); 
		}
		else{
			$("#audit-div" ).css("display", "block"); //审核显示
		}
		
		listid = row.id;
		if (row){
			$('#add-dlg').dialog('open').dialog('setTitle',"查看");
			$('#add-dlg').form('load',row);
			$("#splchoose-div" ).css("display", "none"); 
			$("#listtabdiv" ).css("display", "block"); //列表显示
			$('#btn-save').linkbutton('disable'); 
			 $('#btn-audit').linkbutton('disable'); 
		}
		getsplapplylistline();
	}

  /**
   * 审核
   */
  /**
   * 查看窗口
   */
    function openAudit(){
    //	$('#add-fm').form('clear');
    	initSplType();
    	$("#audit-div" ).css("display", "block"); //审核显示
    	$("#listtabdiv" ).css("display", "block"); //列表显示
    	$("#splchoose-div" ).css("display", "block"); 
    	var nowTime = getDateYMD();
     	$('#keeperid').val(userid);
  	  var rows = $('#dataTabel').datagrid('getSelections');
  		if(rows.length==0){
  			$.messager.alert('提示',"请选择你要查看的领料信息",'info');
  			return;
  		}
  		if(rows.length > 1){
  			$.messager.alert('提示',"只能选择一条领料信息进行查看",'info');
  			return;
  		}
  		
  		var row = $('#dataTabel').datagrid('getSelected');
  		listid = row.id;
  		var auditflag = row.auditflag;
  		if(auditflag!="30"){
  			$.messager.alert('提示',"对不起，本条申请信息暂不能发料，必须审核通过！或本申请已完成发料",'info');
  			return;
  		}
  		if (row){
  			$('#add-dlg').dialog('open').dialog('setTitle',"查看");
  			$('#add-dlg').form('load',row);
  			//$("#splchoose-div" ).css("display", "none"); 
  			$('#btn-save').linkbutton('enable'); 
  			$('#btn-audit').linkbutton('enable'); 
  			$('#btn-done').linkbutton('enable'); 
  		}
  		getsplapplylistline();
  	}
    
    function doAudit(auditflag){
    	if(islegal=="1")
    		{
    		$.messager.alert('提示',"对不起，申请数量大于库存数量，请修改后再发料。",'info');
			return;
    		}
    	var keeperid= $("#keeperid").val(); 
    	$.messager.confirm('提示',"确定发料吗？",function(result){
    		$('#add-fm').form('submit',{
    			url:baseURL+"/cost/SPLConsume/doConsume.json?listid="+listid+"&auditflag="+auditflag+"&keeperid="+keeperid, 
        		data:$("#add-fm").serializeArray(),
        		beforeSend : function () {
        			$.messager.progress({
        				text : '正在新增中...',
        			});
        		},
        		success: function(data){
        			data = eval('('+data+')');
        			$('#add-dlg').dialog('close');
        			$('#dataTabel').datagrid('reload'); 
            		$.messager.show({
        				title : '提示',
        				msg :  data.msg,
        			});
            		
            		//$("#listtabdiv" ).css("display", "block");
            		//getsplapplylistline();
        		}
        	});
    	});
    }
    
  //删除
	function deleterow(){
		var rows = $('#dataTabel').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要删除的领料申请信息",'info');
			return;
		}
		if(rows.length > 1){
  			$.messager.alert('提示',"只能选择一条领料信息进行删除",'info');
  			return;
  		}
		var row = $('#dataTabel').datagrid('getSelected');
  		listid = row.id;
  		var applyid = row.applyid;
  		var auditflag = row.auditflag;//审核状态
  		if(auditflag=="50"||auditflag=="60")//发料完成或退库完成的，不再允许删除
  			{
	  			$.messager.alert('提示',"对不起，该领料信息不允许删除！",'info');
	  			return;
  			}
		$.messager.confirm('提示','确定要删除吗?',function(result){
        if (result){
        	$.post(baseURL+'/cost/SPLApplyList/doDel.json?listid='+listid+"&auditflag="+auditflag,function(data){
	        	$('#dataTabel').datagrid('reload'); 
	        	//console.log("del--"+data);
        		$.messager.show({
					title : '提示',
					msg :  data.total+'个领料信息'+data.msg+'！',
				});
        	});
        }
    });
	}