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
	var begdate=getDateYM01();
	var enddate=getDateYMD();
	$('#begdate').datebox('setValue', begdate);
	$('#enddate').datebox('setValue', enddate);
	$('#dataTable').datagrid({
		title:'部门月领料汇总', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:true, //多选
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:false,//可折叠
		url:baseURL+"/cost/deptconsume/getSPLTypeConsumeByDept.json", //数据来源
         collapsible:true,
         //data:data,
		queryParams:{
			begdate:begdate,
			enddate:enddate
			}, //查询条件
		pagination:false, //显示分页
		rownumbers:true, //显示行号
		columns:[[
			//{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'deptname',title:'部门名称',width:20,
				formatter:function(value,row,index){return row.deptname;} //需要formatter一下才能显示正确的数据
			},
			{field:'spltype0',title:'消防耗材',width:20,
				formatter:function(value,row,index){return row.spltype0;} //需要formatter一下才能显示正确的数据
			},
			{field:'spltype1',title:'劳保用品',width:20,
				formatter:function(value,row,index){return row.spltype1;} //需要formatter一下才能显示正确的数据
			},
			{field:'spltype2',title:'工具器具',width:20,
				formatter:function(value,row,index){return row.spltype2;} //需要formatter一下才能显示正确的数据
			},
			{field:'spltype3',title:'车辆维修配件',width:20,
				formatter:function(value,row,index){return row.spltype3;} //需要formatter一下才能显示正确的数据
			},
			{field:'spltype4',title:'计算机耗材',width:20,
				formatter:function(value,row,index){return row.spltype4;} //需要formatter一下才能显示正确的数据
			},
			{field:'spltype5',title:'低值易耗品',width:20,
				formatter:function(value,row,index){return row.spltype5;} //需要formatter一下才能显示正确的数据
			},
			{field:'spltype6',title:'公用设施维修配件',width:20,
				formatter:function(value,row,index){return row.spltype6;} //需要formatter一下才能显示正确的数据
			},
			{field:'spltype7',title:'办公用品',width:20,
				formatter:function(value,row,index){return row.spltype7;} //需要formatter一下才能显示正确的数据
			},
			{field:'spltype8',title:'配送耗材',width:20,
				formatter:function(value,row,index){return row.spltype8;} //需要formatter一下才能显示正确的数据
			},
			{field:'spltype9',title:'物流设备专用配件',width:20,
				formatter:function(value,row,index){return row.spltype9;} //需要formatter一下才能显示正确的数据
			},
			{field:'totalamount',title:'共计',width:20,
				formatter:function(value,row,index){return '<B>'+row.totalamount+'</B>';}
			}
		]],
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			$('#tabdiv .panel-header').css('display','none');
			$('#dataTable').datagrid('appendRow',{
				deptname: '<B>累计</B>',
				spltype0: '<B>'+compute("spltype0")+'</B>',
				spltype1: '<B>'+compute("spltype1")+'</B>',
				spltype2: '<B>'+compute("spltype2")+'</B>',
				spltype3: '<B>'+compute("spltype3")+'</B>',
				spltype4: '<B>'+compute("spltype4")+'</B>',
				spltype5: '<B>'+compute("spltype5")+'</B>',
				spltype6: '<B>'+compute("spltype6")+'</B>',
				spltype7: '<B>'+compute("spltype7")+'</B>',
				spltype8: '<B>'+compute("spltype8")+'</B>',
				spltype9: '<B>'+compute("spltype9")+'</B>',
				totalamount: '<B>'+compute("totalamount")+'</B>'
			});
		}
	});
});

	
	function openPagePrint(){
		var begdate=$('#begdate').datebox('getValue')
		var enddate=$('#enddate').datebox('getValue')
		var rows = $('#dataTable').datagrid('getRows');
	    var contentstr='<table width="100%" border="0" cellpadding="0" cellspacing="0">';
		contentstr+='<h2  style="text-align:center;">各部门每月领料开支一览表</h2>';
		contentstr+='<tr><td align=left>&nbsp;统计日期：'+begdate+' 到 '+enddate+'</td><td align=right>单位：元&nbsp;</td></tr></table>';
		contentstr +='<table width="100%" border="1" cellpadding="0" cellspacing="0"<tr><td align="center">部门信息</td><td align="center">消防耗材</td><td align="center">劳保用品</td><td align="center">工具器具</td><td align="center">车辆维修配件</td><td align="center">计算机耗材</td><td align="center">低值易耗品</td> <td align="center">公用设施维修配件</td><td align="center">办公用品</td><td align="center">配送耗材</td><td align="center">物流设备专用配件</td><td align="center">共计</td>  </tr>';
		$.each(rows,function(i,n){
    		contentstr +="<tr><td align='center'>"+n.deptname+"</td><td align='center'>"+n.spltype0+"</td><td align='center'>"+n.spltype1+"</td><td align='center'>"+n.spltype2+"</td><td align='center'>"+n.spltype3+"</td><td align='center'>"+n.spltype4+"</td><td align='center'>"+n.spltype5+"</td><td align='center'>"+n.spltype6+"</td><td align='center'>"+n.spltype7+"</td><td align='center'>"+n.spltype8+"</td><td align='center'>"+n.spltype9+"</td><td align='center'>"+n.totalamount+"</td></tr>";
    	});
		contentstr +="</table>";
		contentstr +='<table width="100%" border="0" cellpadding="0" cellspacing="0">';
		contentstr +='<tr><td align=left width="82%">&nbsp;制表人：'+123+'</td><td align=left width="18%">核对人：</td></tr></table>';
		$("#printTable").html(contentstr);
		$('#print-dlg').dialog('open').dialog('setTitle','部门月领料汇总打印');
	}
	
	  //查询
	function searchConsume(){
		var params = $('#dataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			//alert(field.name+"===="+field.value);
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#dataTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了	}
	  
	  //指定列求和
      function compute(colName) {
           var rows = $('#dataTable').datagrid('getRows');
           var total = 0;
           for (var i = 0; i < rows.length; i++) {
               total = (total+parseFloat(rows[i][colName]))*10000/10000;
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