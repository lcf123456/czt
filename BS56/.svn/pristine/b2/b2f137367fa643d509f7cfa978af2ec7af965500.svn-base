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
	var sign=0;
	$("#deptid").combobox({
		url : baseURL+"/comm/combobox/getDeptCombobox.json",//返回json数据的url
		valueField : "id",//这个id和你返回json里面的id对应
		textField : "deptname", //这个text和你返回json里面的text对应
    	onLoadSuccess: function() {
    		if(sign==0){
                var data = $(this).combobox('getData');
                data.unshift({'id':'','deptname':'请选择部门...'});
                sign=1;
                $("#deptid").combobox("loadData", data);//重新加载数据
    		}
    		$(this).combobox("select", "");
        },
        onChange: function (n,o) {
			 //console.log("new="+n+",old="+o);
        	searchSplconsumelist();
		}
	})
	var begdate=$('#begdate').val();	 
	var enddate=$('#enddate').val();
	$('#dataTable').datagrid({
		title:'出库单', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/cost/SPLConsume/getSPLConsumeVoPageList.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序 desc
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 500,
		pageList: [500,2000,5000],
		showFooter: true,
		queryParams:{
			begdate:begdate,
			enddate:enddate,
			}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			//{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'id1',title:'编号',width:10,sortable:false,
				formatter:function(value,row,index){return row.id;} //需要formatter一下才能显示正确的数据
			},
			{field:'deptname',title:'部门',width:20,sortable:false,
				formatter:function(value,row,index){return row.deptname;} 
			},
			{field:'applyid',title:'申请单号',width:15,sortable:false,
				formatter:function(value,row,index){return row.applyid;} 
			},
			{field:'typename',title:'物品类别',width:30,
				formatter:function(value,row,index){return row.typename;}
			},
			{field:'splname',title:'物品名称',width:30,
				formatter:function(value,row,index){return row.splname;}
			},
			{field:'issuedate',title:'发料日期',width:25,
				formatter:function(value,row,index){return row.issuedate;}
			},
			{field:'unitid',title:'单位',width:10,
				formatter:function(value,row,index){return row.unitid;}
			},
			{field:'price',title:'单价（元）',width:15,
				formatter:function(value,row,index){return row.price;}
			},
			{field:'quantity',title:'出库数量',width:10,
				formatter:function(value,row,index){return row.quantity;}
			},
			{field:'totalamount',title:'总价（元）',width:20,
				formatter:function(value,row,index){return row.totalamount;}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			compute();//调用计算函数
			$('#tabdiv .panel-header').css('display','none');
			
		}
	});
});
function compute() {//计算函数
	//新增一行显示统计信息
    //$('#dataTable').datagrid('appendRow', { typename:'',code:'', splname:'<b>打印日期：  </b><input id=printdate name=printdate class="easyui-datebox" style="width:100px;" value="'+getDateYMD()+'" >',suppliername:'',price:'',unitid:'',initqty: '<b>金额合计:</b>', totalamount:((ptotalnum*10000)/10000),imptime:'' });
	//	alert("--");
	var pnum=0;
	var tnum=0;
    var rows = $('#dataTable').datagrid('getRows')//获取当前的数据行
    var pricenum = 0;//计算price的总和 
    var totalnum = 0;//计算totalamount的总和 
    for (var i = 0; i < rows.length; i++) {
    	pnum=rows[i]['quantity'];if(pnum==null||pnum=="")pnum=0;//pricenum为空时赋值为0，否则会显示NaN。
    	pricenum +=parseFloat(pnum);}
    for (var l = 0; l < rows.length; l++) {
    	tnum=rows[l]['totalamount'];if(tnum==null||tnum=="")tnum=0;//totalnum为空时赋值为0，否则会显示NaN。
    	totalnum += parseFloat(tnum);
    }
    totalnum=Math.round(totalnum*10000)/10000;
    $('#dataTable').datagrid('reloadFooter',[{
    	deptname: '<div id="printarea" hidden><B>打印时间：</B><input  id=printdate name=printdate class="easyui-datebox" editable="false" style="width:100px;" value="'+getDateYMD()+'" ></div>',
    	price: '<B>合计:</B>',
    	quantity:'<B>' + pricenum +'</B>',
    	totalamount:'<B>' + totalnum +'</B>'
	}]);
    $.parser.parse($('#printarea')); 
    $('#printdate').datebox({editable:false});
}
		/**
		 * 打开打印窗口
		 */
		  function printpage(sprnstr,eprnstr){
			    var printdate=$('#printdate').val();
			    var begdate=$('#begdate').val().replace("-","").replace("-","");
			    var enddate=$('#enddate').val().replace("-","").replace("-","");

			    $(".formtd").each(function(){
				 	   $(this).html("");
				 	 });
				var rows = $('#dataTable').datagrid('getRows');
				if(rows.length==0){
					$.messager.alert('提示',"请先查询要打印的出库信息",'info');
					return;
				}
				//验证打印时间不得小于查询时间
//				if(printdate!=null&&printdate!=""&&parseInt(printdate)<parseInt(enddate)){
//					$.messager.alert('提示',"打印时间不能小于结束时间",'info');
//					return;
//				}
				var contentstr='<table width="100%" border="0" cellpadding="0" cellspacing="0">';
				contentstr+='<h3  style="text-align:center;">&nbsp;出库单</h3>';
				contentstr+='<tr><td align=left>&nbsp&nbsp打印时间:&nbsp&nbsp'+printdate+'</td><td align=right>单位(元)&nbsp&nbsp&nbsp&nbsp</td></tr></table>';
				contentstr +='<table width="100%" border="1" cellpadding="0" cellspacing="0"<tr><td align="center">编号</td><td align="center">部门</td><td align="center">申请人</td><td align="center">物品类别</td><td align="center">物品名称</td><td align="center">发料日期</td><td align="center">单位</td><td align="center">单价</td><td align="center" width="10%">出库数量</td><td align="center">总价</td> </tr>';
				var totalamount=0;
				var quantity=0;
				$.each(rows,function(i,n){
	        		contentstr +="<tr><td align='center'>"+n.id+"</td><td align='center'>"+n.deptname+"</td><td align='center'>"+n.applyuser+"</td><td align='center'>"+n.typename+"</td><td align='center'>"+n.splname+"</td><td align='center'>"+n.issuedate.substring(0,10)+"</td><td align='center'>"+n.unitid+"</td><td align='center'>&nbsp&nbsp"+n.price+"&nbsp&nbsp</td><td align='center'>&nbsp&nbsp"+n.quantity+"&nbsp&nbsp</td><td align='center'>&nbsp&nbsp"+n.totalamount+"&nbsp&nbsp</td></tr>";
	        		totalamount=(totalamount+parseFloat(n.totalamount.toFixed(3)));
	        		quantity=(quantity+parseFloat(n.quantity.toFixed(3)))
	        	});
				contentstr +='<tr><td colspan="7" align="left" width="100%" style="margin-left:6px">&nbsp&nbsp附件&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp张<td align="center" width="100%" ><strong >合    计：<td colspan="0" align="center" >' +quantity+'<td colspan="0" align="center" >'+totalamount+'</strong></td></td></tr>'; 
				contentstr +='<tr><td colspan="11"  align="left" ><strong style="margin-left:6px">制表人：</strong></td></tr>'; 
				contentstr +="</table>";
				$("#printTable").html(contentstr);
				$('#print-dlg').dialog('open').dialog('setTitle','出库单打印');
			}
	//查询
		function searchSplconsumelist(){
			var params = $('#dataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
			var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		    //将时间格式由字符串转换成date型
			var begdate=$('#begdate').val().replace("-","").replace("-","");
		    var enddate=$('#enddate').val().replace("-","").replace("-","");  
			//验证查询时开始时间不得小于结束时间
		    if(begdate!=null&&begdate!=""&&enddate!=null&&enddate!=""&&begdate>enddate){
				$.messager.alert('提示',"开始时间不能大于结束时间",'info');
				return;
			}
			
			$.each( fields, function(i, field){
				params[field.name] = field.value; //设置查询参数
			}); 
			$('#dataTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了

		}
		
	  
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		$('#queryForm').form('reset');
//		$('#deptid').combobox("setValue","");
//		$('#deptid').combobox("setText","请选择部门...");
		$('#begdate').datebox("setValue",getDateYM01());
		$('#enddate').datebox("setValue",getDateYMD());
		searchSplconsumelist();
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