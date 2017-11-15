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
	var begdate=$('#begdate').val();	 
	var enddate=$('#enddate').val();
	var settlementflagval=$("#settlementflag").val();
	//$('#param').textbox('textbox').keydown(function(e){
	//	if(e.keyCode==13){
		//	searchStoragelist();
		//}
	//})
	$('#dataTable').datagrid({
		title:'入库单', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:420, //高度
		fitColumns: true , //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/cost/suppliesimp/getSuppliesImpList.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序 desc
		remoteSort: true, //服务器端排序
		//idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 10,
		pageList: [10,30,50],
		showFooter: true,
		queryParams:{
			begdate:begdate,
			enddate:enddate,
			settlementflag:settlementflagval,
			}, //查询条件
		//pagination:false, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'typename',title:'物资类别',width:20,
				formatter:function(value,row,index){return row.typename;} //需要formatter一下才能显示正确的数据
			},
			{field:'code',title:'物品编号',width:20,
				formatter:function(value,row,index){return row.code;} //需要formatter一下才能显示正确的数据
			},
			{field:'splname',title:'物品名称',width:30,
				formatter:function(value,row,index){return row.splname;}
			},
			{field:'suppliername',title:'供应商',width:40,
				formatter:function(value,row,index){return row.suppliername;}
			},
			{field:'price',title:'单价（元）',width:20,
				formatter:function(value,row,index){return row.price;}
			},
			{field:'unitid',title:'单位',width:10,
				formatter:function(value,row,index){return row.unitid;}
			},
			{field:'initqty',title:'实收数量',width:20,
				formatter:function(value,row,index){return row.initqty;}
			},
			{field:'totalamount',title:'总价',width:20,
				formatter:function(value,row,index){return row.totalamount;}
			},
			{field:'settleflagname',title:'结算状态',width:20,
				formatter:function(value,row,index){
					if(row.settleflagname == '未结算'){
						return '<span style="color:red;">'+row.settleflagname +'</span>';
					}else{
					return row.settleflagname;}} 
			},
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			compute();//调用计算函数			$('#tabdiv .panel-header').css('display','none');
			$('#param').textbox('textbox').keydown(function(e){
				if(e.keyCode==13){
					searchStoragelist();
				}
			})
		}
	});
});
function compute() {//计算函数
//	alert("--");
	var totalnum=0;
    var rows = $('#dataTable').datagrid('getRows')//获取当前的数据行
    var ptotalnum = 0;//计算totalamount的总和 
    for (var i = 0; i < rows.length; i++) {
    	totalnum=rows[i]['totalamount'];if(totalnum==null||totalnum=="")totalnum=0;//drivernum为空时赋值为0，否则会显示NaN。
    	ptotalnum += totalnum;
    }

    //新增一行显示统计信息
    //$('#dataTable').datagrid('appendRow', { initqty: '<b>合计：</b>', totalamount: ptotalnum});
    
    $('#dataTable').datagrid('reloadFooter',[{
    	suppliername:'<div id="printarea" > <B>打印时间：</B><input id=printdate name=printdate class="easyui-datebox" style="width:100px;" value="'+getDateYMD()+'" ></div>',
    	initqty: '<B>合计</B>',
		totalamount: '<B>' + ptotalnum.toFixed(2) + '</B>'
	}]);
    $.parser.parse($('#printarea'));
}	
//导出excel表格
function exportxls(){
	 $.messager.confirm('提示','确定要导出到excel吗?',function(result){
	        if (result){ 
	        	 $('#queryForm').form('submit', {
	        		 url: baseURL+'/cost/suppliesimp/getSuppliesimpExcel.json'
	        		
	        	 });
	        	 }
	 });
	
}
	  //查询
	function searchStoragelist(){
		//alert("------");
		var params = $('#dataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#dataTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了

	}
	
	/**
	 * 打开打印窗口
	 */
	  function printpage(){
		   
		    var printdate=$('#printdate').val();
		    var enddate=$('#enddate').val();
		    
			var rows = $('#dataTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择要打印的入库信息",'info');
				return;
			}
			if(printdate<enddate){
				$.messager.alert('提示',"打印时间不能小于结束时间",'info');
				return;
			}
			var contentstr='<table width="100%" border="0" cellpadding="0" cellspacing="0">';
			contentstr+='<h2  style="text-align:center;">入库清单</h2>';
			contentstr+='<tr><td align=left>&nbsp&nbsp'+printdate+'</td><td align=right>单位(元)&nbsp&nbsp&nbsp&nbsp</td></tr></table>';
			contentstr +='<table width="100%" border="1" cellpadding="0" cellspacing="0"<tr><td align="center">物资类别</td><td align="center">编号</td> <td align="center">物品名称</td><td align="center">供应商</td><td align="center">单价</td><td align="center">单位</td><td align="center">实收数量</td><td align="center">总价</td> </tr>';
			var totalamount=0;
			$.each(rows,function(i,n){
        		//alert(n.code+'---'+n.splname);
        		contentstr +="<tr><td align='center'>"+n.typename+"</td><td align='center'>"+n.code+"</td><td align='center'>"+n.splname+"</td><td align='center'>"+n.suppliername+"</td><td align='center'>"+n.price.toFixed(2)+"</td><td align='center'>"+n.unitid+"</td><td align='center'>"+n.initqty+"</td><td align='center'>"+n.totalamount.toFixed(2)+"</td></tr>";
        		totalamount=totalamount+parseFloat(n.totalamount.toFixed(3));
        	});
		   //alert(totalamount);
			contentstr +='<tr><td colspan="2"><strong>备注：</strong></td> <td colspan="3" ><strong>验收人签章：</strong></td><td align="center"><strong >合计：</strong></td><td align="center" colspan="2"><strong >'+totalamount.toFixed(2)+'</strong ></td> </tr>'; 
			contentstr +="</table>";
			//contentstr +="<div style='text-align:center; margin:auto;padding:5px'>";
			//contentstr +="<input type='button'  value='关闭' onclick='window.location.reload();'></div>";
			//alert(contentstr);
			$("#printTable").html(contentstr);
			$('#print-dlg').dialog('open').dialog('setTitle','入库清单打印');
			//$("#printTable").attr("style","display:none;");
			//alert($("printTable"))
			 //var bdhtml=window.document.body.innerHTML;  
		  		//var sprnstr="<!--startprint-->";  
		  		//var eprnstr="<!--endprint-->";  
		  		//var prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);  
		  		//prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
		  		//window.document.body.innerHTML=$("#print-dlg").html();
		  		//window.location.reload();
		  		//window.print();  
		  		//window.document.body.innerHTML= bdhtml;
		  		//window.document.body.innerHTML=bdhtml;
		  		//$("div#printTable").printArea();  	
			
		}
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		$('#queryForm').form('reset');

		searchStoragelist();
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