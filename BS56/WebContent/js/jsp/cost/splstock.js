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
 * 页面列表datagrid初始化 */
jQuery(function($){
	var sign=0;
	//物资归属下拉列表
	$("#maintypeid").combobox({
		url : baseURL+"/cost/suppliesimp/getSPLTypesCombobox.json?clevel=2&fid=2",//返回json数据的url
    	valueField : "id",//这个id和你返回json里面的id对应
    	textField : "typename", //这个text和你返回json里面的text对应
    	onLoadSuccess: function() {
    		if(sign==0){
                var data = $(this).combobox('getData');
                data.unshift({'id':'0','typename':'全部'});
                sign=1;
                $("#maintypeid").combobox("loadData", data);//重新加载数据
    		}
    		$(this).combobox("select", "0");
        },
        onChange: function (n,o) {
			 //console.log("new="+n+",old="+o);
			 searchSPL();
		}
    })
	//当前库存
	if(showFlag=='currStock'){
		$('#dataTable').datagrid({
			title:'当前库存', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:true, //多选			height:420, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。			striped: true, //奇偶行颜色不同			collapsible:true,//可折叠			url:baseURL+"/cost/stock/getTypeStockList.json", //数据来源
			queryParams:{
				}, //查询条件
			//pageSize : 1,
			rownumbers:true, //显示行号
			showFooter:true,
			view: detailview,
			columns:[[
				//{field:'id',checkbox:true,width:2}, //显示复选框
				{field:'typename',title:'物资类别',width:30,
					formatter:function(value,row,index){return row.typename;} //需要formatter一下才能显示正确的数据
				},
				{field:'maintypename',title:'物资所属',width:30,
					formatter:function(value,row,index){return row.maintypename;} //需要formatter一下才能显示正确的数据
				},
				{field:'unit',title:'单位',width:20,
					formatter:function(value,row,index){return row.unit;} //需要formatter一下才能显示正确的数据
				},
				{field:'surplusqty',title:'数量',width:20,sortable:false,
					formatter:function(value,row,index){return row.surplusqty;}
				},
				{field:'totalamount',title:'金额(元)',width:20,
					formatter:function(value,row,index){return row.totalamount;}
					
				}
			]],
			toolbar:'#toolbar',
			onLoadSuccess:function(){
				$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				$('#tabdiv .panel-header').css('display','none');
				$('#dataTable').datagrid('reloadFooter',[{
					unit: '<B>合计</B>',
					surplusqty: '<B>'+compute("surplusqty")+'</B>',
					totalamount: '<B>'+compute("totalamount")+'</B>'
				}]);
			},
			detailFormatter:function(index,row){
                return '<div style="padding:2px"><table class="ddv"></table></div>';
            },
            onExpandRow: function(index,row){
                var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
                ddv.datagrid({
                    url:baseURL+'/cost/stock/getStockDetailList.json?typeid='+row.typeid,
                    fitColumns:true,
                    singleSelect:true,
                    rownumbers:true,
                    loadMsg:'',
                    height:'auto',
                    columns:[[
                        {field:'code',title:'物资编号',width:30},
                        {field:'splname',title:'物资名称',width:30,align:'left'},
                        {field:'suppliername',title:'供应商',width:70,align:'left'},
                        {field:'price',title:'单价',width:20,align:'left'},
                        {field:'quantity',title:'剩余数量',width:20,align:'left'},
                        {field:'totalamount',title:'金额',width:20,align:'left'},
                        {field:'lockqty',title:'锁定数量',width:20,align:'left'},
                    ]],
                    onResize:function(){
                        $('#dataTable').datagrid('fixDetailRowHeight',index);
                    },
                    onLoadSuccess:function(){
                        setTimeout(function(){
                            $('#dataTable').datagrid('fixDetailRowHeight',index);
                        },0);
                    }
                });
                $('#dataTable').datagrid('fixDetailRowHeight',index);
            }
		});
	}else{
		var begdate=getDateYM01();
		var enddate=getDateYMD();
		$('#begdate').datebox('setValue', begdate);
		$('#enddate').datebox('setValue', enddate);
		$('#dataTable').datagrid({
			title:'库存报表', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:true, //多选
			height:420, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:true,//可折叠
			url:baseURL+"/cost/stock/getSPLTypeStockByDate.json", //数据来源
			queryParams:{
				begdate:begdate,
				enddate:enddate
				}, //查询条件
			//pageSize : 1,
			rownumbers:true, //显示行号
			showFooter:true,
			toolbar:'#toolbar',
			onLoadSuccess:function(){
				$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				$('#tabdiv .panel-header').css('display','none');
				$('#dataTable').datagrid('reloadFooter',[{
					typename: '<B>合计</B>',
					lastqty: '<B>'+compute("lastqty")+'</B>',
					lastamount: '<B>'+compute("lastamount")+'</B>',
					impqty: '<B>'+compute("impqty")+'</B>',
					impamount: '<B>'+compute("impamount")+'</B>',
					consumeqty: '<B>'+compute("consumeqty")+'</B>',
					consumeamount: '<B>'+compute("consumeamount")+'</B>',
					currqty: '<B>'+compute("currqty")+'</B>',
					curramount: '<B>'+compute("curramount")+'</B>'
				}]);
			},
		});
	}
});

	
	function openPagePrint(){
		$('#queryForm').form('submit',{
			onSubmit: function(){
			},
			url:baseURL+"/cost/stock/getTypeStockListForPrint.json", //数据来源
			success: function(data){
				    var contentstr='<table width="100%" border="0" cellpadding="0" cellspacing="0">';
					contentstr+='<h2  style="text-align:center;">当前物资库存信息</h2>';
					contentstr+='<tr><td align=left>&nbsp;打印日期：'+getDateYMD()+'</td></tr></table>';
					contentstr +='<table width="100%" border="1" cellpadding="0" cellspacing="0"<tr><td align="center">物资编号</td><td align="center">物品名称</td><td align="center">供应商</td><td align="center">单价</td><td align="center">剩余数量</td><td align="center">金额</td> </tr>';
					var totalamount=0,totalqty=0;
					var typeid="",tmpid="";
					 data = eval('('+data+')');
		             $.each(data, function(i, n) {
		            	 typeid=n.typeid;
		     			 if(tmpid!=typeid){
		     				contentstr +="<tr><td align='left' colspan='9'>&nbsp;<strong><b>"+n.typename+"("+n.maintypename+")--"+n.surplusqty+"("+n.unit+")--"+n.totalamount+"(元)</b></strong></td>";
		     				totalamount=totalamount+parseFloat(n.totalamount);
			         		totalqty=totalqty+parseFloat(n.surplusqty);
		     			 }
		         		 contentstr +="<tr><td align='center'>"+n.splcode+"</td><td align='center'>"+n.splname+"</td><td align='center'>"+n.suppliername+"</td><td align='center'>"+n.splprice+"</td><td align='center'>"+n.splqty+"</td><td align='center'>"+n.splamount+"</td></tr>";
		         		 tmpid=typeid;
		             });
		            contentstr +="<tr><td align='center'>&nbsp;</td><td align='center'>&nbsp;</td><td align='center'>&nbsp;</td><td align='center'><b>合计</b></td><td align='center'><b>"+Math.round(totalqty*1000)/1000+"</b></td><td align='center'><b>"+Math.round(totalamount*1000)/1000+"</b></td></tr>";
		     		contentstr +="</table>";
		     		//alert(contentstr);
		     		$("#printTable").html(contentstr);
		     		//$("#printTable").attr("style","display:none;");
		     		//alert($("printTable"))
		     		$('#print-dlg').dialog('open').dialog('setTitle','入库结算总表打印');
			}
		});
	}
	
	//库存报表打印
	function openStockPagePrint(){
		$('#queryForm').form('submit',{
			onSubmit: function(){
			},
			url:baseURL+"/cost/stock/getSPLTypeStockByDate.json", //数据来源
			success: function(data){
				var begdate=$('#begdate').datebox('getValue');
				var enddate=$('#enddate').datebox('getValue');
				var contentstr='<table width="100%" border="0" cellpadding="0" cellspacing="0" id="pTable">';
				contentstr+='<h2  style="text-align:center;">库存报表</h2>';
				contentstr+='<tr><td align=left>&nbsp;日期：'+begdate+'&nbsp;到&nbsp;'+enddate+'</td></tr></table>';
				contentstr +='<table width="100%" border="1" cellpadding="0" cellspacing="0"><tr><td align="center" rowspan="2">物资类型</td><td align="center" rowspan="2">物资归属</td><td align="center" rowspan="2">单位</td><td align="center" colspan="2">上月库存</td><td align="center" colspan="2">本月收料</td><td align="center" colspan="2">本月发料</td><td align="center" colspan="2">本月库存</td></tr>';
				contentstr +='<tr><td align="center">数量</td><td align="center">金额</td><td align="center">数量</td><td align="center">金额</td><td align="center">数量</td><td align="center">金额</td><td align="center">数量</td><td align="center">金额</td></tr>';
				var tlastqty=0,tlastamount=0,timpqty=0,timpamount=0,tcqty=0,tcamount=0,tcurrqty=0,tcurramount=0;
				data = eval('('+data+')');
				$.each(data.rows, function(i, n) {
					contentstr +="<tr><td align='center'>"+n.typename+"</td><td align='center'>"+n.maintypename+"</td><td align='center'>"+n.unit+"</td><td align='center'>"+n.lastqty+"</td><td align='center'>"+n.lastamount+"</td><td align='center'>"+n.impqty+"</td><td align='center'>"+n.impamount+"</td><td align='center'>"+n.consumeqty+"</td><td align='center'>"+n.consumeamount+"</td><td align='center'>"+n.currqty+"</td><td align='center'>"+n.curramount+"</td></tr>";
					tlastqty=tlastqty+n.lastqty;
					tlastamount=tlastamount+n.lastamount;
					timpqty=timpqty+n.impqty;
					timpamount=timpamount+n.impamount;
					tcqty=tcqty+n.consumeqty;
					tcamount=tcamount+n.consumeamount;
					tcurrqty=tcurrqty+n.currqty;
					tcurramount=tcurramount+n.curramount;
				});
				contentstr +="<tr><td align='center'><B>合计</B></td><td align='center'>&nbsp;</td><td align='center'>&nbsp;</td><td align='center'><b>"+tlastqty+"</b></td><td align='center'><b>"+Math.round(tlastamount*1000)/1000+"</b></td><td align='center'><b>"+timpqty+"</b></td><td align='center'><b>"+Math.round(timpamount*1000)/1000+"</b></td><td align='center'><b>"+tcqty+"</b></td><td align='center'><b>"+Math.round(tcamount*1000)/1000+"</b></td><td align='center'><b>"+tcurrqty+"</b></td><td align='center'><b>"+Math.round(tcurramount*1000)/1000+"</b></td></tr>";
				contentstr +="</table>";
				//alert(contentstr);
				$("#printTable").html(contentstr);
				//$("#printTable").attr("style","display:none;");
				//alert($("printTable"))
				$('#print-dlg').dialog('open').dialog('setTitle','库存报表打印');
			}
		});
	}
	
	  //查询
	function searchSPL(){
		var params = $('#dataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			//alert(field.name+"===="+field.value);
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#dataTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了	}
	//清空查询条件
	function clearForm(){
		//$('#keywd').val('');
		$('#param').textbox("setValue","");
		$('#param').textbox("setText","");
		$('#maintypeid').combobox("select","0");
		
		if(showFlag=='stockByDate'){
			var begdate=getDateYM01();
			var enddate=getDateYMD();
			$('#begdate').datebox('setValue', begdate);
			$('#enddate').datebox('setValue', enddate);
		}
		
		searchSPL();
	}
	
	  
	  /**
	   * 导出Excel
	   * @returns
	   */
	  function toExcel(){
		  //alert("==");
		  var rows = $('#dataTable').datagrid('getRows');
		  $.messager.confirm('提示','确定要导出库存信息到Excel吗?',function(result){
		        if (result){
			      	 var param=$('#param').val();
			      	 var maintypeid=$('#maintypeid').val();
			      	 //alert(settlementflag);
			      	 $('#param1').val(param);
			      	 $('#maintypeid1').val(maintypeid);
		        	 $('#excelForm').form('submit', {
		        		 url: baseURL+'/cost/stock/doExportStockToExcel.json' 
		        	 });
		        }
		  });
	  }
	  
	  //指定列求和
      function compute(colName) {
           var rows = $('#dataTable').datagrid('getRows');
           var total = 0;
           for (var i = 0; i < rows.length; i++) {
               total = Math.round((total+parseFloat(rows[i][colName]))*10000)/10000;
           }
           return total;
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