var url;
//alert("-====");
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
var begdate=$('#begdate').val();	 
var enddate=$('#enddate').val();
//alert(begdate+"--"+enddate);
	$('#dataTable').datagrid({
		title:'市场督查', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/sq/routescore/getRoutescore_market.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'desc', //正序asc、倒序 desc
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 10,
		pageList: [10,30,50],
		queryParams:{
			begdate:begdate,
			enddate:enddate
			}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'routecode',title:'车组',width:20,sortable:true,
				formatter:function(value,row,index){return row.routecode;} //需要formatter一下才能显示正确的数据
			},
			{field:'license',title:'专卖证号',width:20,sortable:true,
				formatter:function(value,row,index){return row.license;} //需要formatter一下才能显示正确的数据
			},
			{field:'custname',title:'零售户名称',width:50,sortable:true,
				formatter:function(value,row,index){return row.custname;} //需要formatter一下才能显示正确的数据
			},
			{field:'dscore',title:'司机得分',width:20,
				formatter:function(value,row,index){return row.dname+"("+row.dscore+"分)";}
			},
			{field:'cscore',title:'收款员得分',width:30,
				formatter:function(value,row,index){return row.cname+"("+row.cscore+"分)";}
			},
			{field:'createname',title:'记录人',width:30,
				formatter:function(value,row,index){return row.createname;}
			},
			{field:'scoringtime',title:'得分时间',width:30,
				formatter:function(value,row,index){return row.scoringtime;}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题			$('#tabdiv .panel-header').css('display','none');
		}
	});
});

function openNew(){
	$(".gridtable tr").remove(".dynamic-tr");
	//$('#scoringtime').focus();
	$('#add-dlg').dialog('open').dialog('setTitle','市场督查新增');
	//$('#add-fm').form('clear');
	$('#add-fm').form('reset');
	$('#condname').val('');
	var datetimeval=getDateYMDHMS();
	//alert(datetimeval);
	$('#scoringtime1').datetimebox('setValue', datetimeval);
}

//查找零售户
function searchCustomer(){
	var condi=$('#condname').val();
	if(condi!=null&&condi.length!=""){
		$('#param').val(condi);
		$('#qform').form('submit',{
			onSubmit: function(){
			},
			url:baseURL+"/sq/routescore/getCustListByCondition.json",
			success: function(data){
					 $(".gridtable tr").remove(".dynamic-tr");
					 var listTmp = "";
					 data = eval('('+data+')');
		             $.each(data, function(i, cust) {
		            	listTmp = listTmp +"<tr class='dynamic-tr' ><td>"+cust.name+"</td><td>"+cust.id+"</td><td>"+cust.contact+"</td><td>"+cust.contactphone+"</td>"+
		            	"<td><input name='rad' id=rad"+i+" type='radio' onClick=radClick('"+cust.id+"','"+cust.name+"','"+cust.contact+"','"+cust.contactphone+"','"+cust.contactaddress+"','"+cust.routecode+"') value="+cust.id+"></td></tr>";
		            	//"<td><input name='rad' id=rad"+i+" type='radio' onClick='radClick('"+cust.id+"','"+cust.name+"','"+cust.contact+"','"+cust.contactphone+"','"+cust.contactaddress+"')' value="+cust.id+"></td></tr>";
		             });
		           $(".gridtable tbody").append (listTmp);
			}
		});
	}else{
		alert("请输入零售户查询条件！");
		return false;
	}
}

function radClick(id,name,contact,tel,addr,routecode){
		$('#licensecode').val(id);
		$('#custid').val(id);
	    $('#custname').val(name);
		$('#routecode').val(routecode);
  		if(routecode!=null&&routecode!=""&&routecode!="---请选择---")
  		{
  			$.ajax({
  				url:baseURL+"/sq/routescore/getDriverAndCashierByRouteCode.json?routecode="+routecode, //数据来源
  				success : function(data) {
  		            	$('#driverid').val(data.driverid);
  		            	$('#cashierid').val(data.cashierid);
  		            	$('#drivername').val(data.drivername);
  		            	$('#cashiername').val(data.cashiername);
  		            	//alert(data.driverid+"--"+data.cashierid+"--"+data.drivername+"--"+data.cashiername);
  		         },
  		         error: function(e) { 
  		          	} 
  			})
  		}else{
  			$.messager.alert('系统提示', '该零售户暂无配送车组信息！', 'warning');
  			return;
  		}
}

	function  ForDight(num,n)  
	{  
	           num  =  Math.round  (num*Math.pow(10,n))/Math.pow(10,n);  
	           return  num;  
	}  

	function js()
	{
	  var che1Obj=document.getElementsByName("che1");
	  var che2Obj=document.getElementsByName("che2");
	  var che3Obj=document.getElementsByName("che3");
	  var che4Obj=document.getElementsByName("che4");
	  var che5Obj=document.getElementsByName("che5");
	  var che6Obj=document.getElementsByName("che6");
	  var che1Val;
	  var che2Val;
	  var che3Val;
	  var che4Val;
	  var che5Val;
	  var che6Val;
	  for(i=0;i<che1Obj.length;i++)
	　　{
	    　　 if(che1Obj[i].checked)
	       　　che1Val=che1Obj[i].value;
	　　}
		for(i=0;i<che2Obj.length;i++)
		　　{
			　　 if(che2Obj[i].checked)
			   　　che2Val=che2Obj[i].value;
		　　}
		for(i=0;i<che3Obj.length;i++)
		　　{
			　　 if(che3Obj[i].checked)
			   　　che3Val=che3Obj[i].value;
		　　}
		for(i=0;i<che4Obj.length;i++)
		　　{
			　　 if(che4Obj[i].checked)
			   　　che4Val=che4Obj[i].value;
		　　}
		for(i=0;i<che5Obj.length;i++)
		　　{
			　　 if(che5Obj[i].checked)
			   　　che5Val=che5Obj[i].value;
		　　}
		for(i=0;i<che6Obj.length;i++)
		　　{
			　　 if(che6Obj[i].checked)
			   　　che6Val=che6Obj[i].value;
		　　}
		var dscore=(parseFloat(che1Val)+parseFloat(che2Val)+parseFloat(che5Val));
		var cscore=(parseFloat(che1Val)+parseFloat(che3Val)+parseFloat(che6Val));
		//alert(dscore+","+cscore);
		document.getElementById("dscore").value=ForDight(dscore,2);
		document.getElementById("cscore").value=ForDight(cscore,2);
		document.getElementById("routescore").value=ForDight(dscore+cscore,2);
	   //document.forms[1].cscore.value=ForDight(cscore,2);
	   //document.forms[1].routescore.value=ForDight(dscore+cscore,2);
	
	}
	
	/**
	 * 保存市场督查新增信息
	 */
	function saveAdd(){
		$('#add-fm').form('submit',{
			onSubmit: function(){
					var licensecode=$('#licensecode').val();
					var routecode=$('#routecode').val();
					var drivername=$('#drivername').val();
					if(licensecode.length==0){
						alert("请选择市场督查的零售户信息！");
						return false;
					}else if(routecode.length==0||drivername.length==0){
						alert("所选零售户暂无配送车组信息，无法保存市场督查信息！");
						return false;
					}
			},
			url:baseURL+"/sq/routescore/doRoutescoreMarketNew.json",
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
					msg :  data.total+'个市场督查信息新增'+data.msg+'！',
				});
			}
		});
	}


/**
 * 打开查看窗口
 */
function openView(){
	$(".formtd").each(function(){
	 	   $(this).html("");
	 	 });
		var rows = $('#dataTable').datagrid('getSelections');
		
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要查看的市场督查信息",'info');
			return;
		}
		
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一条市场督查信息进行查看",'info');
			return;
		}
 	//$(".formtd");
	viewRow = $('#dataTable').datagrid('getSelected');
		$('#dataTable1').datagrid({
			method:'post',
			width:570,
			singleSelect:false, //多选
			url:baseURL+"/sq/routescore/getViewRoutescore.json", //数据来源
			idField:'id', //主键字段
			queryParams:{id:viewRow.id}, //查询条件
			pagination:false, //显示分页
			rownumbers:false, //显示行号
			columns:[[
				{field:'contentshort',title:'考核项名称:',width:420,
					formatter:function(value,row,index){return '考核项名称: '+row.contentshort;} //需要formatter一下才能显示正确的数据
				},
				{field:'actualscore',title:'得分',width:75,
					formatter:function(value,row,index){return '得分: '+row.actualscore;}
				},
				{field:'assessweight',title:'权重',width:75,
					formatter:function(value,row,index){return '权重: '+row.assessweight;}
				}
			]],
			//toolbar:'#toolbar',
			onLoadSuccess:function(){
				$('#dataTable1').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题

			}
				
		});
	
 	
	
	
	//alert(viewRow);
	if (viewRow!=null){
		$('#view-dlg').dialog('open').dialog('setTitle','市场督察查看');
		$("#id").attr("value",viewRow.id);
		$("#v-routecode").html(viewRow.routecode);
		$("#v-custid").html(viewRow.custid);
		$("#v-dname").html(viewRow.dname+"得分：");
		$("#v-cname").html(viewRow.cname+"得分：");
		$("#v-dscore").html(viewRow.dscore+"分");
		$("#v-cscore").html(viewRow.cscore+"分");
		$("#v-createname").html(viewRow.createname);
		var scoringtime=viewRow.scoringtime;
	    if(scoringtime==null)scoringtime="";
	    if (scoringtime!=null&&scoringtime.length>19)scoringtime=scoringtime.substring(0,19)
		$("#v-scoringtime").html(scoringtime);
		$("#v-remarks").html(viewRow.remarks);
		$("#v-custname").html(viewRow.custname);
		$("#v-custid").html(viewRow.license);
		$("#contactinfo").html(viewRow.contact+"("+viewRow.telnum+")");
		//$("#contact").html(viewRow.contact);
		//$("#telnum").html(viewRow.telnum);
		$("#custaddr").html(viewRow.custaddr);
	}

	}
  
//删除
	function deleterow(){
		var rows = $('#dataTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要删除的市场督查得分信息",'info');
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
        	
        	$.post(baseURL+'/sq/routescore/dodelRoutescore.json'+ps,function(data){
	        	$('#dataTable').datagrid('reload'); 
	        	//console.log("del--"+data);
        		$.messager.show({
					title : '提示',
					msg :  data.total+'条市场督查得分信息被删除'+data.msg+'！',
				});
        	});
        }
    });
	}
	
	  //查询
	function searchRoutescore(){
		var params = $('#dataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			//alert(field.value);
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#dataTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
	}
	//清空查询条件
	function clearForm(){
		$('#keywd').textbox("setValue","");
		$('#keywd').textbox("setText","");
		//alert(getDateYM01());
		//$('#dd').datebox('setValue', '6/1/2012');
		$('#begdate').datebox("setValue",getDateYM01());
		$('#enddate').datebox("setValue",getDateYMD());
		searchRoutescore();
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