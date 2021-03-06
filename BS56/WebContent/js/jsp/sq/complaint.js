var url;
//投诉流程：受理——核实——审核——处理——回访
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
	$('#keyword').textbox('textbox').keydown(function(e){
		if(e.keyCode==13){
			searchData();
		}
	})
	$('#dataTabel').datagrid({
		title:'投诉维护', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		url:baseURL+"/sq/complaint/getComplaintList.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 10,
		pageList: [10,30,50],
		queryParams:{
			}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'croutecode',title:'车组',width:$(this).width()*0.05,
				formatter:function(value,row,index){return row.croutecode;} //需要formatter一下才能显示正确的数据
			},
			{field:'licensecode',title:'专卖证',width:$(this).width()*0.1,
				formatter:function(value,row,index){return row.licensecode;}
			},
			{field:'custname',title:'零售户',width:$(this).width()*0.23,
				formatter:function(value,row,index){return row.custname;}
			},
			{field:'content',title:'投诉内容',width:$(this).width()*0.46,
				formatter:function(value,row,index){
					var conttmp = row.content;
					if(conttmp.length>30)conttmp = conttmp.substr(0,40)+"...";
					return conttmp;  
				}
			} 
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none'); 
			$("#source").combobox('select','10');
		},
		onDblClickCell:function(index,field,value){
			viewD();
		}
	});
});

/**
 * 打开新增窗口
 */
function openCreate(){
	$('#add-fm').form('clear');
	//$("#source").combobox('select','10');
	//$("input[name$='source']").val("10");
	$("#source").combobox({
		valueField:'id',
		textField:'text',
		value:"10" ,
		panelHeight:'auto',
		data:[{
			id:"10",
			text:"直接投诉"
			//selected:true
		},
		{
			id:"20",
			text:"96368"
		}]
		});
	
	initType();//获取投诉分类
	initPreuser();//核实人员
	//initRoute();
	 // $("#source").val("10");
	 // $("#source").find("option[text='10']").attr("selected",true);
	//$("#source   option[value='10']").attr("selected",true);
	$('#add-dlg').dialog('open').dialog('setTitle','投诉受理');
	//
	//url = '/BS56/sys/roleNew.json';
	//$('#add-dlg').dialog('colse');
}

/**
 * 获取车组列表
 */
 function initRoute() {
 	var html = "";
     $('#croutecode').empty();
     $("<option >" +"---请选择---"+ "</option>").appendTo("#croutecode");
     $.ajax({
    	 url : baseURL+"/comm/combobox/getRoutesComboboxByDeptid.json?deptid=",
         success : function(data) {
             $.each(data, function(i, one) {
             	html = "";
             	html = "<option value='" + one.routecode + "'>" + one.routename + "</option>";
                $(html).appendTo("#croutecode");
             });
             
             $('#croutecode').change(function() {
          		var routecode= $("#croutecode").val(); 
          		if(routecode!=null&&routecode!=""&&routecode!="---请选择---")
          			{
          			initRoutePerson(routecode);
          			}
          		else{
          			$.messager.alert('系统提示', '请选择车组！', 'warning');
          			//alert("请选择类别");
          		}
      
             });
         },
         error: function(e) { 
         	//$("<option >" +"---市---"+ "</option>").appendTo("#city");
          	} 

    });
 }
/**
 * 获取车组人员列表
 */
function initRoutePerson(routecode) {
    $('#cusername').empty();
    $("<option >" +"---请选择---"+ "</option>").appendTo("#cusername");
    $.ajax({
        type : "POST",
        url:baseURL+"/sq/complaint/getDriverAndCashierByRotecode.json?routecode="+routecode, //数据来源
        success : function(data) {
            	html = "";
            	var cashier = data.cashierid+";"+data.cashiername;
            	var driver = data.driverid+";"+data.drivername;
             	html = "<option value='" + cashier + "' selected>" + data.cashiername + "</option>";
             	html = html+"<option value='" + driver + "'>" + data.drivername + "</option>";
                $(html).appendTo("#cusername");
               // var data = $('#cusername').combobox('getData');
              //  $('#cusername').combobox('select',data[1].value);
        },
        error: function(e) { 
        	//$("<option >" +"---市---"+ "</option>").appendTo("#city");
         	} 
    });
}

/**
 * 获取投诉分类
 */
function initType() {
    $('#type').empty();
    $("#type").combobox({
    	url:baseURL+"/comm/combobox/getComboboxByTypeCode.json?typeCode=COMPLAINT", //数据来源
	    valueField : "id",//这个id和你返回json里面的id对应
	    textField : "contentlist", //这个text和你返回json里面的text对应
	    panelHeight:"auto",
	    editable:false,
	    onLoadSuccess: function () { //加载完成后,设置选中第一项
	    	var val = $(this).combobox("getData");  
            for (var item in val[0]) {  
                    $(this).combobox("select", val[0]["id"]);  
            } 
        }
	})
	
}

/**
 * 获取核实人员
 */
function initPreuser() {
    $('#preusernameComb').empty();
//    $("<option >" +"---请选择---"+ "</option>").appendTo("#preusername");
//    $.ajax({
//        type : "POST",
//        url:baseURL+"/comm/combobox/getUserComboboxByRoleId.json?roleid=14", //投诉处理角色
//        success : function(data) {
//             	 $.each(data, function(i, one) {
//                  	html = "";
//                  	var tmp = one.id+";"+ one.username;
//                  	html = "<option value='" +tmp + "'>" + one.username + "</option>";
//                     $(html).appendTo("#preusername");
//                  });
//        },
//        error: function(e) { 
//        	//$("<option >" +"---市---"+ "</option>").appendTo("#city");
//         	} 
//    });
//    
    $("#preusernameComb").combobox({
    	url:baseURL+"/comm/combobox/getUserComboboxByRoleId.json?roleid=14", //投诉处理角色
	    valueField : "id",//这个id和你返回json里面的id对应
	    textField : "username", //这个text和你返回json里面的text对应
	    panelHeight:"auto",
	    editable:false,
	    onLoadSuccess: function () { //加载完成后,设置选中第一项
	    	var val = $(this).combobox("getData");  
	    	//console.log("val=="+val);
            for (var item in val[0]) {  
            	//console.log("val[0]=="+val[0]+",item="+item);
                    $(this).combobox("select", val[0]["id"]);  
            } 
        }
	})
}

/**
 * 提交新增
 */
function saveNew(){
	var licensecode= $("#licensecode").val(); 
	var cusername= $("#cusername").val(); 
	var type= $('#type').val();
	var content= $("#content").val(); 
	var preusernameComb= $('#preusernameComb').val()+";"+$('#preusernameComb').combobox('getText');
	 $("#preusername").val(preusernameComb); 
	var source= $("#source").val(); 
	var ctime= $("#ctime").val(); 
	if(licensecode==null||licensecode==""||licensecode=="---请选择---"){
		$.messager.alert('系统提示', '请先选择投诉零售户！', 'warning');
		return;
	}
	if(cusername==null||cusername==""||cusername=="---请选择---"){
		$.messager.alert('系统提示', '请选择被投人！', 'warning');
		return;
	}
	if(source==null||source==""||source=="---请选择---"){
		$.messager.alert('系统提示', '请选择投诉来源！', 'warning');
		return;
	}
	if(content==null||content==""){
		$.messager.alert('系统提示', '请输入投诉内容！', 'warning');
		return;
	}
	if(type==null||type==""||type=="---请选择---"){
		$.messager.alert('系统提示', '请选择投诉分类！', 'warning');
		return;
	}
	if(ctime==null||ctime==""){
		$.messager.alert('系统提示', '请选择受理时间！', 'warning');
		return;
	}
	if(preusername==null||preusername==""||preusername=="---请选择---"){
		$.messager.alert('系统提示', '请选择投诉核实人员！', 'warning');
		return;
	}
	$('#add-fm').form('submit',{
		onSubmit: function(){
			var isValidate = $(this).form('validate');
			if(isValidate){
				//$('#loading').window('open');
			}
			
			return isValidate;

		},
		url:baseURL+"/sq/complaint/doSave.json",
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
				msg :  data.total+'个投诉受理'+data.msg+'！',
			});
			//$('#loading').window('close');
		}
	});
}

var opname="";

/**
 * 2、打开投诉核实窗口
 * 投诉流程：受理——核实——审核——处理——回访
 */
  function openEdit(opstatus){
	 
	  
		var rows = $('#dataTabel').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要"+opname+"的投诉信息",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一条投诉信息进行"+opname,'info');
			return;
		}
		var row = $('#dataTabel').datagrid('getSelected');
		var bz=0;
		 var status =row.status;
		 
		//1、受理后可以核实和删除，但不能审核、处理、回访
		  if(status==10 && (opstatus==30 || opstatus==40 || opstatus==50))
			  {
			  bz=1;
			  $.messager.alert('提示',"对不起，请先核实",'info');
				return;
			  }
		  if(status==10 && (opstatus==30 || opstatus==40 || opstatus==50))
		  {
			  bz=1;
		  $.messager.alert('提示',"对不起，请先核实",'info');
			return;
		  }
		  if(status==20 && (opstatus==40 || opstatus==50))
		  {
			  bz=1;
		  $.messager.alert('提示',"对不起，请先审核",'info');
			return;
		  }
		  if(status==30 && (opstatus==50))
		  {
			  bz=1;
		  $.messager.alert('提示',"对不起，请先处理",'info');
			return;
		  }
		
		if (row&&bz==0){
			$('#saveUpdate').linkbutton('enable'); 
			trShow(opstatus);//控制行显示
			$('#edit-dlg-pre').dialog('open').dialog('setTitle',opname);
			$('#edit-fm-pre').form('load',row);
			$('#status').val(status);
		}

	}
  
  function btnShow(status,opstatus){
	
  }
  /**
   * 保存投诉核实
   */
  function saveEdit(){
  	$('#edit-fm-pre').form('submit',{
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
			$('#edit-dlg-pre').dialog('close');
			$('#dataTabel').datagrid('reload'); 
    		$.messager.show({
				title : '提示',
				msg :  data.total+'个操作'+data.msg+'！',
			});
			//$('#loading').window('close');
		}
  	});
  }
  
//删除
	function deleterow(){
		var rows = $('#dataTabel').datagrid('getSelections');
		if(rows.length==0){
		$.messager.alert('提示',"请选择你要删除的信息",'info');
		return;
	}
		$.messager.confirm('提示','确定要删除吗?',function(result){
        if (result){
        	var ps = "";
        	$.each(rows,function(i,n){
        		if(i==0) 
        			ps += "?id="+n.id;
        		else
        			ps += "&id="+n.id;
        	});
        	$.post(baseURL+'/sq/complaint/doDel.json'+ps,function(data){
	        	$('#dataTabel').datagrid('reload'); 
	        	//console.log("del--"+data);
        		$.messager.show({
					title : '提示',
					msg :  data.total+'个信息被删除'+data.msg+'！',
				});
        	});
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
		$('#queryForm').form('clear');
		searchData();
	}
	
	//查找零售户
	function searchCustomer(){
		//先选择车组
		//var routecode= $("#croutecode").val(); 
		var condname= $("#condname").val(); 
		/*
  		if(routecode!=null&&routecode!=""&&routecode!="---请选择---")
  			{
  			initRoutePerson(routecode);
  			}
  		else{
  			$.messager.alert('系统提示', '请选择车组！', 'warning');
  			return ;
  			//alert("请选择类别");
  		}*/
  		if(condname==null||condname=="")
  			{
  			$.messager.alert('系统提示', '请输入查询信息！', 'warning');
  			return;
  			}
  		//根据车组返回零售户信息getCustListByRouteCode
  		$.ajax({
  			url:baseURL+"/sq/complaint/getCustListByRouteCode.json?condname="+condname, //数据来源
  			success : function(data) {
  				 $(".gridtable tr").remove(".dynamic-tr");
  				var listTmp = "";
  	             $.each(data, function(i, cust) {
  	            	listTmp = listTmp +"<tr class='dynamic-tr' ><td>"+cust.name+"</td><td>"+cust.id+"</td><td>"+cust.contact+"</td><td>"+cust.contactphone+"</td>"+
  	            	"<td><input name='rad' id=rad"+i+" type='radio' onClick=radClick('"+cust.id+"','"+cust.name+"','"+cust.contact+"','"+cust.contactphone+"','"+cust.contactaddress+"','"+cust.routecode+"') value="+cust.id+"></td></tr>";
  	            	//"<td><input name='rad' id=rad"+i+" type='radio' onClick='radClick('"+cust.id+"','"+cust.name+"','"+cust.contact+"','"+cust.contactphone+"','"+cust.contactaddress+"')' value="+cust.id+"></td></tr>";
  	             });
  	           $(".gridtable tbody").append (listTmp);
  	         },
  	         error: function(e) { 
  	          	} 
  		})
  		
	}
	
	function radClick(id,name,contact,tel,addr,routecode){
			$('#licensecode').val(id);
	        $('#custname').val(name);
			$('#complainant').val(contact);
			$('#telnum').val(tel);
			$('#addr').val(addr);
			$('#croutecode').val(routecode);
      		if(routecode!=null&&routecode!=""&&routecode!="---请选择---")
      			{
      			initRoutePerson(routecode);
      			}
      		else{
      			$.messager.alert('系统提示', '请选择车组！', 'warning');
      			return;
      			//alert("请选择类别");
      		}
			//$("input[name=#id]").val("22015");
	}
	
	/**
	 * 2、打开投诉核实窗口
	 */
	  function viewD(status){
		  var rows = $('#dataTabel').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要查看的投诉信息",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条投诉信息进行查看",'info');
				return;
			}
			
			var row = $('#dataTabel').datagrid('getSelected');
			var status = row.status;
			trShow(status);
			if (row){
				$('#edit-dlg-pre').dialog('open').dialog('setTitle',"查看");
				$('#edit-fm-pre').form('load',row);
				$('#saveUpdate').linkbutton('disable'); 
			}

		}
	  
	  
	  function trShow(status)
	  {
		  if(status=="10"){
			  opname = "新增";
			  var trs = $("tr[class='pre']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = "none"; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
			  var trs = $("tr[class='audit']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = "none"; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
			  var trs = $("tr[class='handle']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = "none"; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
			  var trs = $("tr[class='visit']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = "none"; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
		  }
		  if(status=="20"){
			  opname = "核实";
			  var trs = $("tr[class='pre']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = ""; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
			  var trs = $("tr[class='audit']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = "none"; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
			  var trs = $("tr[class='handle']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = "none"; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
			  var trs = $("tr[class='visit']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = "none"; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
		  }
		  if(status=="30"){
			  opname = "审核";
			  var trs = $("tr[class='pre']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = ""; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
			  var trs = $("tr[class='audit']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = ""; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
			  var trs = $("tr[class='handle']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = "none"; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
			  var trs = $("tr[class='visit']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = "none"; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
		  }
		  if(status=="40"){
			  opname = "处理";
			  var trs = $("tr[class='pre']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = ""; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
			  var trs = $("tr[class='audit']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = ""; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
			  var trs = $("tr[class='handle']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = ""; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
			  var trs = $("tr[class='visit']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = "none"; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
		  }
		  if(status=="50"){
			  opname = "回访";
			  var trs = $("tr[class='pre']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = ""; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
			  var trs = $("tr[class='audit']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = ""; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
			  var trs = $("tr[class='handle']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = ""; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
			  var trs = $("tr[class='visit']"); 
			  for(i = 0; i < trs.length; i++){ 
			  trs[i].style.display = ""; //这里获取的trs[i]是DOM对象而不是jQuery对象，因此不能直接使用hide()方法 
			  }
		  }
	  }
