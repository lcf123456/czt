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
jQuery(function(def){
	var orderdate="";
	var nowTime=getDateYMD();
//	$.ajax({
//		url:baseURL+"/account/timebydm/getTimebydm.json?type=0&date="+nowTime, 
//		success : function(data) {
//			orderdate=data.timebydm;
//			//alert(orderdate);
//			$('#orderdate').datebox("setValue",orderdate);
//			//$('#orderdate').datebox("setValue","2017-09-07");
//	     },
//	});
	$.post(baseURL+'/account/timebydm/getTimebydm.json?type=0&date='+nowTime,function(data){
		alert(data);
		orderdate=data.timebydm;
		$('#orderdate').datebox("setValue",orderdate);
		alert(orderdate);
	});
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
        	searchData();
		}   	
	})
	$('#dataTable').datagrid({
		//title:'退货入库', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //多选,当true时只允许当前选择一行。		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠		url:baseURL+"/account/accounttotal/getAccountTotalPageList.json", //数据来源
		queryParams:{
			orderdate:$('#orderdate').datebox("getValue")
			}, //查询条件
		pagination:false, //显示分页
		//pageSize : 1,
		showFooter: true,
		rownumbers:true, //显示行号
		columns:[[
			{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'routecode',title:'车组',width:10,
				formatter:function(value,row,index){return row.routecode;} //需要formatter一下才能显示正确的数据
			},
			{field:'todayarrears',title:'当日欠款(元)',width:10,
				formatter:function(value,row,index){return row.todayarrears;}
			},
			{field:'saleamount',title:'销售额(元)',width:10,
				formatter:function(value,row,index){return row.saleamount;}
			},
			{field:'onlineamount',title:'在线代扣(元)',width:10,
				formatter:function(value,row,index){return row.onlineamount;}
			},
			{field:'prepayamount',title:'预收款(元)',width:10,
				formatter:function(value,row,index){return row.prepayamount;}
			},
			{field:'cardamount',title:'刷卡金额(元)',width:10,
				formatter:function(value,row,index){return row.cardamount;}
			},
			{field:'cashamount',title:'现金金额(元)',width:10,
				formatter:function(value,row,index){return row.cashamount;}
			},
			{field:'returnamount',title:'退货(元)',width:10,
				formatter:function(value,row,index){return row.returnamount;}
			},
			{field:'tempstorageamount',title:'暂存1(元)',width:10,
				formatter:function(value,row,index){return row.tempstorageamount;}
			},
			{field:'deliveramount',title:'暂存送货1(元)',width:10,
				formatter:function(value,row,index){return row.deliveramount;}
			},
			{field:'tempstorageamount2',title:'暂存2(元)',width:10,
				formatter:function(value,row,index){return row.tempstorageamount2;}
			},
			{field:'deliveramount2',title:'暂存送货2(元)',width:10,
				formatter:function(value,row,index){return row.deliveramount2;}
			},
			{field:'otheramount',title:'其它金额(元)',width:10,
				formatter:function(value,row,index){return row.otheramount;}
			}
	
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题			//$('#tabdiv .panel-header').css('display','none');
			$('#dataTable').datagrid('reloadFooter',[{
				routecode: '<B>合计</B>',
				todayarrears: '<B>'+compute("todayarrears")+'</B>',
				saleamount: '<B>'+compute("saleamount")+'</B>',
				onlineamount: '<B>'+compute("onlineamount")+'</B>',
				prepayamount: '<B>'+compute("prepayamount")+'</B>',
				cardamount: '<B>'+compute("cardamount")+'</B>',
				cashamount: '<B>'+compute("cashamount")+'</B>',
				returnamount: '<B>'+compute("returnamount")+'</B>',
				tempstorageamount: '<B>'+compute("tempstorageamount")+'</B>',
				deliveramount: '<B>'+compute("deliveramount")+'</B>',
				tempstorageamount2: '<B>'+compute("tempstorageamount2")+'</B>',
				deliveramount2: '<B>'+compute("deliveramount2")+'</B>',
				otheramount: '<B>'+compute("otheramount")+'</B>'
			}]);
		}
	});
});

//指定列求和
function compute(colName) {
     var rows = $('#dataTable').datagrid('getRows');
     var total = 0;
     for (var i = 0; i < rows.length; i++) {
         total = Math.round((total+parseFloat(rows[i][colName]))*10000)/10000;
     }
     return total;
 }

/**
 * 打开新增窗口
 */
function openNew(){
	var rows = $('#dataTable').datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择你要录入的货款回笼信息",'info');
		return;
	}
	if(rows.length > 1){
		$.messager.alert('提示',"只能选择一条货款回笼信息进行录入",'info');
		return;
	}
	var row = $('#dataTable').datagrid('getSelected');
	if (row){
		$('#add-dlg').dialog('open').dialog('setTitle','车组货款回笼信息');
		$('#add-fm').form('load',row);
	}
}


	  //查询
	function searchData(){
		var params = $('#dataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#dataTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了	}
	
	//查询
	function searchOrder(){
		var routecode=$('#routecode1').combobox("getValue");
		if(routecode==null||routecode==""){
			$.messager.alert('提示',"请先选择车组信息",'info');
			return;
		}
		var orderdate=$('#orderdate').datebox("getValue");
		$('#routecode2').val(routecode);
		$('#orderdatestr').val(orderdate);
		var params = $('#orderDataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数		var fields =$('#searchForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#orderDataTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了	}
	
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
	
	function saveAdd(){
	  	$('#add-fm').form('submit',{
	  		onSubmit: function(){
	  			var isValidate = $(this).form('validate');
	  			if(isValidate){
	  				var routecode=$('#routecode1').combobox("getValue");
	  				if(routecode==""){
	  					$.messager.alert('提示',"请选择车组信息",'info');
	  					return false;
	  				}
	  				var reasonid=$('#reasonid').combobox("getValue");
	  				if(reasonid==""){
	  					$.messager.alert('提示',"请选择车组退货原因",'info');
	  					return false;
	  				}
	  				var quantity=$('#quantity').numberbox("getValue");
	  				if(quantity==""){
	  					$.messager.alert('提示',"请选择要退货的订单",'info');
	  					return false;
	  				}
	  			}
	  			return isValidate;
	  		},
	  		url:baseURL+"/account/operate/doOperateAdd.json",
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
				$('#dataTable').datagrid('reload'); 
	    		$.messager.show({
					title : '提示',
					msg :  data.total+'个车组退货新增'+data.msg+'！',
				});
				//$('#loading').window('close');
			}
	  	});
	  }
	  
	
	function delOperate(){
		var rows = $('#dataTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择要删除的退货信息",'info');
			return;
		}
		$.messager.confirm('提示','确定要删除所选退货信息吗?',function(result){
			if (result){
				var ids = "";
				$.each(rows,function(i,n){
					if(i==0){ 
						ids += "?id="+n.id;
					}else{
						ids += "&id="+n.id;
					}	
				});
				$.post(baseURL+'/account/operate/doDelOperate.json'+ids,function(data){
					$('#dataTable').datagrid('reload'); 
					//console.log("del--"+data);
					data = eval('('+data+')');
					$.messager.show({
						title : '提示',
						msg :  data.total+'个退货信息删除'+data.msg+'！',
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
	
	function jsamount()
	 {
		alert("begin---");
	 	var saleamount = $('#saleamount').val();if(saleamount==null)saleamount=0;
	 	var onlineamount = $('#onlineamount').val();if(onlineamount==null)onlineamount=0;
	 	var prepayamount = $('#prepayamount').val();if(prepayamount==null)prepayamount=0;
	 	var returnamount = $('#returnamount').val();if(returnamount==null)returnamount=0;
	 	alert("begin---");
	 	var turngoodsamount2 = $('#turngoodsamount2').val();if(turngoodsamount2==null)turngoodsamount2=0;
	 	var tempstorageamount2 = $('#tempstorageamount2').val();if(tempstorageamount2==null)tempstorageamount2=0;
	 	var cardamount = $('#cardamount').val();if(cardamount==null)cardamount=0;
	 	var cashamount = $('#cashamount').val();if(cashamount==null)cashamount=0;
	 	alert("begin---");
	 	var totalarrears = $('#totalarrears').val();if(totalarrears==null)totalarrears=0;
	 	var otheramount = $('#otheramount').val();if(otheramount==null)otheramount=0;
	 	var deliver2 = $('#deliver2').val();if(deliver2==null)deliver2=0;
	 	alert(saleamount);
	 	alert(onlineamount);
	 	alert(prepayamount);
	 	alert(returnamount);
	 	alert(turngoodsamount2);
	 	alert(tempstorageamount2);
	 	alert(cardamount);
	 	alert(cashamount);
	 	alert(totalarrears);
	 	alert(otheramount);
	 	alert(deliver2);
	 	var amountdiff=-(saleamount-onlineamount-prepayamount-returnamount-tempstorageamount2-cardamount-cashamount-otheramount+parseFloat(turngoodsamount2)+parseFloat(deliver2));
	 	//document.add-fm.todayarrears.value=amountdiff;
	 	alert(amountdiff);
	 	$('#todayarrears').val(amountdiff);
	 }
	 
//	  function jscount()
//	 {
//	 	var salecount = document.add-fm.salecount.value;
//	 	var onlinecount = document.add-fm.onlinecount.value;
//	 	var prepaycount = document.add-fm.prepaycount.value;
//	 	var returncount = document.add-fm.returncount.value;
//	 	var turngoodscount2 = document.add-fm.turngoodscount2.value;
//	 	var tempstoragecount2 = document.add-fm.tempstoragecount2.value;
//	 	var cardcount = document.add-fm.cardcount.value;
//	 	if(cardcount.indexOf(".") > 0){
//	 		alert("刷卡笔数是小数，请修改为整数！");
//	 		document.add-fm.cardcount.value="";
//	 		document.add-fm.cardcount.focus();
//	 		return false;
//	 	}
//	 	var cashcount = document.add-fm.cashcount.value;
//	 	if(cashcount.indexOf(".") > 0){
//	 		alert("现金笔数是小数，请修改为整数！");
//	 		document.add-fm.cashcount.value="";
//	 		document.add-fm.cashcount.focus();
//	 		return false;
//	 	}
//	 	var othercount = document.add-fm.othercount.value;
//	 	if(othercount.indexOf(".") > 0){
//	 		alert("其他笔数是小数，请修改为整数！");
//	 		document.add-fm.othercount.value="";
//	 		document.add-fm.othercount.focus();
//	 		return false;
//	 	}
//	 	var countdiff=-(salecount-onlinecount-prepaycount-returncount-tempstoragecount2-cardcount-cashcount-othercount+parseFloat(turngoodscount2));
//	 	document.add-fm.countdiff.value=countdiff;
//	 }