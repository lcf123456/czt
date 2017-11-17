/**
 * 页面列表datagrid初始化
 */
var editRow = undefined; //定义全局变量：当前编辑的行
jQuery(function($){

	//初始化日期控件
	var obj = $("#searchtime");
	initDataboxYM(obj);
	
	var deptobj = $("#searchdept");
	initDept(deptobj);
	
	//初始化类型 1:选中默认值  0：不选择
	var propobj = $("#searchproperty");
	initproperty(propobj,"0");
	$('#keyword').textbox('textbox').keydown(function(e){
		if(e.keyCode==13){
			searchData();
		}
	})
	//initctype();
	$('#dataTabel').datagrid({
		title:'日常工作', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //单选
		height:490, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		nowrap:false,
		//货主默认为长沙11430101
		url:baseURL+"/perform/workcontent/getWorkcontentList.json?showhj=0", //不显示合计
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
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'propertyname',title:'类型',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.propertyname;}
			},
			{field:'matters',title:'工作事项',width:$(this).width()*0.3,editor:'text',
				formatter:function(value,row,index){return row.matters;}
			},
			{field:'standard',title:'标准及要求',width:$(this).width()*0.3,editor:'text',
				formatter:function(value,row,index){return row.standard;}
			},
			{field:'weight',title:'权重',width:$(this).width()*0.05,editor:{type:'numberbox',options:{precision:2,max:1,min:0}},
				formatter:function(value,row,index){return row.weight;}
			},
			{field:'createusername',title:'记录人',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.createusername;}
			}
		]],
		toolbar:'#toolbar',
		onAfterEdit: function (rowIndex, rowData, changes) {
            //endEdit该方法触发此事件
            var amount = rowData.weight;
            //alert(amount);
            var bz=0;
            if (amount=="" || parseFloat(amount)==0 || isNaN(parseFloat(amount))){
            	$.messager.alert('提示',"请输入合法的数字！",'info');
            	bz=1;
            }

            if(bz==0){//提交修改
            	$.post(baseURL+'/perform/workcontent/doUpdate.json?wkid='+rowData.id+'&weight='+amount+'&matters='+rowData.matters+'&standard='+rowData.standard, rowData, function(data) {
					$.messager.show({
						title : '提示',
						msg :  data.total+'个信息修改'+data.msg+'！',
					});
					$('#dataTabel').datagrid('reload'); 
				});
            }
            
            editRow = undefined;
        },
		onLoadSuccess:function(){
			$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none'); 
		},
		onDblClickCell:function(index,field,value){
			viewD();
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

function initDept(obj){
	//部门下拉列表
    obj.combobox({
    	url : baseURL+"/sys/dept/getDeptComboboxByCond.json",//返回json数据的url
    	valueField : "id",//这个id和你返回json里面的id对应
    	textField : "deptname", //这个text和你返回json里面的text对应
    	onLoadSuccess: function () { //加载完成后,设置选中用户所属部门
                    //$(this).combobox("select", deptid);  
    		var typecode=obj.val();
    		var choosebz = 0;
	    	if(typecode==null||typecode==""){
	            var val = $(this).combobox("getData");
	          //如果登陆用户部门在二级部门里面，则默认选中用户部门，否则默认选中第一个
	            for (var i = 0; i < val.length; i++){
	            	for (var item in val[i]) {
	            		if (item == "id") {
		                	if(deptid==val[i][item]){
		                		choosebz=1;
		 	                   typecode=deptid;
		 	                    $(this).combobox("select", typecode);
		                	}
		                }
	            	}
	            }
	            if(choosebz==0){//没有选中用户所在部门,则默认选中第一个
	            	for (var item in val[0]) {
		                if (item == "id") {
		                   typecode=val[0][item];
		                    $(this).combobox("select", typecode);
		                }
		            }
	            }
	            
	    	}
        },
    	onSelect: function (row2) {
    		//初始化用户
    		deptid = row2.id;
    		var obj = $("#userid_new");
            	initUser(obj,deptid);
        } 
        
    	//panelHeight: 'auto'
    })
}

//初始化考核类型   bz= 1:选中默认值  0：不选择
function initproperty(obj,bz){
	obj.combobox({
        valueField:'id',
        textField:'value',
        data:[
        	{
        		id:1,
        		value:"关键隐患控制"
        	},{
        		id:2,
        		value:"日常工作"
        	},{
        		id:3,
        		value:"临时性工作"
        	}],
        	onLoadSuccess: function () {
        		if(bz=="1")
        			{
		        		var val = $(this).combobox("getData");
		       		    for (var item in val[0]) {
		                    if (item == "id") {
		                        $(this).combobox("select", val[0][item]);
		                    }
		                }
        			}
        	},
        	onSelect: function (row2) {
        		 var obj = $('#kwlist');
        			getkwlist(obj,220,row2.id);
            } 
    });
}

//初始被考核部门
function initUser(obj,deptid){
	obj.combobox({
		valueField:'id',
        textField:'username',
      url:baseURL+"/sys/user/getUserAndChildComboboxByDeptId.json?deptid="+deptid, //数据来源
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
	//初始化类型 1:选中默认值  0：不选择
	var propobj = $("#searchproperty");
	initproperty(propobj,"0");
	searchData();
}


/**
 * 打开新增窗口
 */
function openCreate(){
	$('#add-fm').form('clear');
	$('#add-dlg').dialog('open').dialog('setTitle','本月重点工作新增');
	
	//初始化记录人、记录日期、检查日期、检查部门
	var nowTime = getDateYMD();
	$('#workdate_new').datebox("setValue",nowTime);
	$('#workdate_new').datebox("setText",nowTime);
	//initDataboxYM(obj);
	
	var deptobj = $("#deptid_new");
	initDept(deptobj);
	
	//初始化类型 1:选中默认值  0：不选择
	var propobj = $("#property_new");
	initproperty(propobj,"1");
	
	var date = new Date();
    var month = date.getMonth() + 1;

    var obj = $('#kwlist');
	getkwlist(obj,220,"");
}

//列表
function getkwlist(obj,dgheight,propertyid)
{
	obj.datagrid({
		title:'日常工作', //标题
		method:'post',
		iconCls:'icon-view', //图标
		singleSelect:false, //多选
		height:dgheight, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		nowrap:false,
		url:baseURL+"/perform/workcontent/getWorkcontentList.json?deptid="+deptid+"&searchproperty="+propertyid+"&showhj=1", //ctype=10:重点工作
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		singleSelect: true,
		pagination:false, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'propertyname',title:'类型',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.propertyname;}
			},
			{field:'matters',title:'工作事项',width:$(this).width()*0.3,editor:'text',
				formatter:function(value,row,index){return row.matters;}
			},
			{field:'standard',title:'标准及要求',width:$(this).width()*0.3,editor:'text',
				formatter:function(value,row,index){return row.standard;}
			},
			{field:'weight',title:'权重',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.weight;}
			},
			{field:'createusername',title:'记录人',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.createusername;}
			}
		]],
		onLoadSuccess:function(){
			//$('#inboundlinediv .panel-header').css('display','none'); 
			
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
	
	var weight_new = $("#weight_new").val();
	var matters_new = $("#matters_new").val();//工作事项
	var standard_new = $("#standard_new").val();//考评标准
	var userid_new = $("#userid_new").val();//人员
	
	
	  if(weight_new=="" || parseFloat(weight_new)==0 || isNaN(parseFloat(weight_new))){
		  $.messager.alert('提示',"请输入权重或您输入的权重为0，请重新输入！",'info');
			return;
	  }
	  if(matters_new==""){
		  $.messager.alert('提示',"请输入工作事项！",'info');
			return;
	  }
	  if(standard_new==""){
		  $.messager.alert('提示',"请输入考评标准！",'info');
			return;
	  }
	  if(userid_new==""){
		  $.messager.alert('提示',"请选择人员！",'info');
			return;
	  }
	 
	  $('#add-fm').form('submit',{
		  url:baseURL+"/perform/workcontent/doSave.json", 
			success : function(data) {
				data = eval('('+data+')');
				//$('#add-dlg').dialog('close');
				$('#dataTabel').datagrid('reload'); 
				$('#kwlist').datagrid('reload'); 
				$.messager.show({
					title : '提示',
					msg :  '日常工作'+data.msg+'！',
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
		}
	}
	
	//删除
		function deleterow(){
			var rows = $('#dataTabel').datagrid('getSelections');
			if(rows.length==0){
			$.messager.alert('提示',"请选择你要删除的信息",'info');
			return;
		}
			var row = $('#dataTabel').datagrid('getSelected');
			
			//此处是否需要考虑，如果该项已经加入月重点考核中，将不能再进行删除？待续......
			
			var id = row.id;
			$.messager.confirm('提示','确定要删除吗?',function(result){
	        if (result){
	        	$.post(baseURL+'/perform/workcontent/doDel.json?id='+id,function(data){
		        	$('#dataTabel').datagrid('reload'); 
	        		$.messager.show({
						title : '提示',
						msg :  data.total+'个信息被删除'+data.msg+'！',
					});
	        	});
	        }
	    });
		}

 

		