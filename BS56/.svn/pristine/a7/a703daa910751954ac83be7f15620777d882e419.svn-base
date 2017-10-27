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
	$("#deptname").combobox({
		url : baseURL+"/comm/combobox/getDeptCombobox.json",//返回json数据的url
    	valueField : "id",//这个id和你返回json里面的id对应
    	textField : "deptname", //这个text和你返回json里面的text对应
	    loadFilter : function (data) {
	        if (data && data instanceof Array) {
	            data.splice(0, 0, {id: '', deptname: '所有部门'});　// 此处空格用全角
	        }
	        return data;
	    }   	
	})
	var begdate=$('#begdate').val();	 
	var enddate=$('#enddate').val();
	$('#dataTable').datagrid({
		title:'出库汇总表', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选

		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。

		striped: true, //奇偶行颜色不同

		collapsible:true,//可折叠
		url:baseURL+"/cost/SPLConsume/getSPLConsummaryList.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'desc', //倒序 desc
		remoteSort: true, //服务器端排序
		idField:'id', //主键字段
		 view:groupview,
         groupField:'maintypename',
         groupFormatter:function(value,rows){
            // return value + ' - ' +rows[0][deptname];
             return value;
         },
		pageNumber: 1, 
	     pageSize : 10,
		pageList: [10,30,50],
		showFooter: true,
		queryParams:{
		begdate:begdate,
			enddate:enddate,
			}, //查询条件
		pagination:false, //显示分页
		pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			{field:'typename',title:'物资类别',width:30,sortable:true,
				formatter:function(value,row,index){return row.typename;} //需要formatter一下才能显示正确的数据
			},
			{field:'splname',title:'物品名称',width:30,
			formatter:function(value,row,index){return row.splname;}
		},
		{field:'code',title:'物品编号',width:30,sortable:true,
			formatter:function(value,row,index){return row.code;} //需要formatter一下才能显示正确的数据
		},
			{field:'price',title:'单价（元）',width:20,
				formatter:function(value,row,index){return row.price;}
			},
			{field:'unitid',title:'单位',width:10,
				formatter:function(value,row,index){return row.unitid;}
			},
			{field:'surplusqty',title:'可用数量',width:20,
				formatter:function(value,row,index){return row.surplusqty;}
			},
			{field:'quantity',title:'申领数量',width:20,
				formatter:function(value,row,index){return row.quantity;}
			},
			{field:'totalamount',title:'金额(元)',width:20,
				formatter:function(value,row,index){return row.totalamount;}
			},
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none');
			$('#dataTable').datagrid('reloadFooter',[{
				surplusqty: '<B>合计</B>',
				quantity: '<B>'+compute("quantity")+'</B>',
				totalamount: '<B>'+compute("totalamount").toFixed(2)+'</B>'
			}]);
		}
	});
});


function compute(colName) {
    var rows = $('#dataTable').datagrid('getRows');
    var total = 0;
    for (var i = 0; i < rows.length; i++) {
        total = (total+parseFloat(rows[i][colName]))*10000/10000;
    }
    return total;
}

	  //查询
	function searchSPLConsummarylist(){
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
		  var deptname=$('#deptname').combobox('getText');
			var rows = $('#dataTable').datagrid('getRows');
			if(rows.length==0){
				$.messager.alert('提示',"请先查询要打印的出库信息",'info');
				return;
			}
			var contentstr='<table width="100%" border="0" cellpadding="0" cellspacing="0">';
			contentstr+='<h2  style="text-align:center;">出库汇总表</h2>';
			contentstr+='<tr><td align=left>&nbsp&nbsp领料部门:&nbsp '+deptname+'</td><td align=center>打印时间:&nbsp'+getDateYMD()+'</td><td align=right>单位(元)&nbsp&nbsp&nbsp&nbsp</td></tr></table>';
			contentstr +='<table width="100%" border="1" cellpadding="0" cellspacing="0"<tr><td align="center">物资类别</td><td align="center">物品名称</td><td align="center">编号</td> <td align="center">单价</td><td align="center">单位</td><td align="center">可用数量</td><td align="center">申领数量</td><td align="center">金额(元)</td> </tr>';
			var totalamount=0, quantity=0;
			var maintypeid="",tmpid="";
			$.each(rows,function(i,n){
		
				maintypeid=n.maintypename;
				//alert(n.maintypeid);
				if(tmpid!=maintypeid)contentstr +="<tr><td align='left' colspan='8'>&nbsp;<strong><b>"+n.maintypename+"</b></strong></td>";
        		contentstr +="<tr><td align='center'>"+n.typename+"</td><td align='center'>"+n.splname+"</td><td align='center'>"+n.code+"</td><td align='center'>"+n.price.toFixed(2)+"</td><td align='center'>"+n.unitid+"</td><td align='center'>"+n.surplusqty+"</td><td align='center'>"+n.quantity+"</td><td align='center'>"+n.totalamount.toFixed(2)+"</td></tr>";
        		totalamount=totalamount+parseFloat(n.totalamount.toFixed(2));
        		quantity=quantity+parseFloat(n.quantity);
        		tmpid=maintypeid;
        		
        	});
		   //alert(totalamount);
			contentstr +='<tr><td align="left" colspan="5"><strong >&nbsp附件&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp张</strong ></td><td align="center"colspan="1"><strong >合计</strong></td><td align="center"colspan="1"><strong >'+quantity+'</strong></td><td align="center" colspan="1"><strong >'+totalamount.toFixed(2)+'</strong ></td> </tr>'; 
			contentstr +="</table>";
			$("#printTable").html(contentstr);
			$('#print-dlg').dialog('open').dialog('setTitle','出库汇总表打印');
				
			
		}
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		$('#queryForm').form('reset');

		searchSPLConsummarylist();
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