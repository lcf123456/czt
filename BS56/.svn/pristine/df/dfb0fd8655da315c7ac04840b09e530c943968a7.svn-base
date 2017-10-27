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
	 function scoringtime(/** timestamp=0 **/) {
	     var ts = arguments[0] || 0;
	     var t, y, m;
	     t = ts ? new Date(ts * 1000) : new Date();
	     y = t.getFullYear();
	     m = t.getMonth() + 1;
	     // 可根据需要在这里定义时间格式  
	     return y +  (m < 10 ? '0' + m : m) ;
	 }
	$('#scoringtime').datebox({    
        onShowPanel : function() {// 显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层    
            span.trigger('click'); // 触发click事件弹出月份层    
            if (!tds)    
                setTimeout(function() {// 延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔    
                    tds = p.find('div.calendar-menu-month-inner td');    
                    tds.click(function(e) {    
                        e.stopPropagation(); // 禁止冒泡执行easyui给月份绑定的事件    
                        var year = /\d{4}/.exec(span.html())[0]// 得到年份    
                        , month = parseInt($(this).attr('abbr'), 10) ; // 月份    
                        $('#scoringtime').datebox('hidePanel')// 隐藏日期对象    
                        .datebox('setValue', year + month); // 设置日期的值    
                    });    
                }, 0);    
        },    
        parser : function(s) {// 配置parser，返回选择的日期    
            if (!s)    
                return new Date();    
           /* var arr = s.split('-');    
            return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1); */
            
            var yearValue = s.substr(0,4); 
            var monthValue = s.substr(4,2);
            return new Date(parseInt(yearValue, 10), parseInt(monthValue, 10) , 1);    
        },    
        formatter : function(d) {    
            var month = d.getMonth();  
            if(month<=10){  
                month = "0"+month;  
            }  
            if (d.getMonth() == 0) {    
                return d.getFullYear()-1 +  "12";    
            } else {    
                return d.getFullYear() + month+"";    
            }    
        }// 配置formatter，只返回年月    
    }); 
	
    var p = $('#scoringtime').datebox('panel'), // 日期选择对象    
    tds = false, // 日期选择对象中月份    
    span = p.find('span.calendar-text'); // 显示月份层的触发控件    
    
    
    //显示当前日期前一月
 formatterDate = function (date) {
       if (date.getMonth() == 0) {    
                 return date.getFullYear()-1 +  "12";    
             } else if(date.getMonth()<10) {    
                 return date.getFullYear() +"0"+date.getMonth();    
             }else{
 			    return date.getFullYear() +date.getMonth(); 
 			} 
 };

 $('#scoringtime').datebox('setValue', formatterDate(new Date()));
 
 var time=$('#scoringtime').datebox('getValue');
 //var time='201702';

	
	$('#dataTable').datagrid({
		title:'自动语音考核汇总表', //标题
		method:'post',
		iconCls:'icon-edit', //图标
		singleSelect:false, //多选
		height:420, //高度
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		collapsible:true,//可折叠
		url:baseURL+"/sq/routescoresummary/getRouteScoresummarys.json", //数据来源
		sortName: 'routecode', //排序的列
		sortOrder: 'desc', //降序
		remoteSort: false, //服务器端排序
		idField:'id', //主键字段
		pageNumber: 1, 
		pageSize : 50,
		pageList: [10,30,50],
		queryParams:{
		
			}, //查询条件
		pagination:true, //显示分页
		//pageSize : 1,
		rownumbers:true, //显示行号
		columns:[[
			//{field:'id',checkbox:true,width:2}, //显示复选框
			{field:'routecode',title:'考核车组',width:20,sortable:true,
				formatter:function(value,row,index){return row.routecode;}
			},
			{field:'custid',title:'访问零售户数量',width:20,sortable:true,
				formatter:function(value,row,index){return row.custid;}
			},
			{field:'scoringtime',title:'考核时间',width:20,
				formatter:function(value,row,index){return row.scoringtime;}
			},
		
		]],
		
		toolbar:'#toolbar',
		onLoadSuccess:function(){
			$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			compute();//调用计算函数
			$('#tabdiv .panel-header').css('display','none');
		}
			
	});
	
});

$(document).ready(function () {

	$("#ctype").combobox({

	onChange: function (n,o) {
		var ctypeval=$("#ctype").val();
		var scoringtimeval=$("#scoringtime").val();
	 if (ctypeval=='10'||ctypeval==null){
		$('#dataTable').datagrid({
			title:'自动语音考核汇总表', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			height:420, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:true,//可折叠
			url:baseURL+"/sq/routescoresummary/getRouteScoresummarys.json", //数据来源
			sortName: 'routecode', //排序的列
			sortOrder: 'desc', //降序
			remoteSort: false, //服务器端排序
			idField:'id', //主键字段
			pageNumber: 1, 
			pageSize : 50,
			pageList: [10,30,50],
			queryParams:{
				scoringtime:scoringtimeval,
				ctype:ctypeval,
				}, //查询条件
			pagination:true, //显示分页
			//pageSize : 1,
			rownumbers:true, //显示行号
			columns:[[
				//{field:'id',checkbox:true,width:2}, //显示复选框
				{field:'routecode',title:'考核车组',width:20,sortable:true,
					formatter:function(value,row,index){return row.routecode;}
				},
				{field:'custid',title:'访问零售户数量',width:20,sortable:true,
					formatter:function(value,row,index){return row.custid;}
				},
				{field:'scoringtime',title:'考核时间',width:20,
					formatter:function(value,row,index){return row.scoringtime;}
				},
			
			]],
			
			toolbar:'#toolbar',
			onLoadSuccess:function(){
				$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				compute();//调用计算函数
				$('#tabdiv .panel-header').css('display','none');
			}
		
		});
	 }else{
			$('#dataTable').datagrid({
			title:'自动语音考核汇总表', //标题
			method:'post',
			iconCls:'icon-edit', //图标
			singleSelect:false, //多选
			height:420, //高度
			fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, //奇偶行颜色不同
			collapsible:true,//可折叠
			url:baseURL+"/sq/routescoresummary/getRouteScoresummary.json", //数据来源
			sortName: 'routecode', //排序的列
			sortOrder: 'desc', //降序
			remoteSort: false, //服务器端排序
			idField:'id', //主键字段
			pageNumber: 1, 
			pageSize : 50,
			pageList: [10,30,50],
			queryParams:{
				scoringtime:scoringtimeval,
				ctype:ctypeval,
				}, //查询条件
			pagination:true, //显示分页
			//pageSize : 1,
			rownumbers:true, //显示行号
			columns:[[
				//{field:'id',checkbox:true,width:2}, //显示复选框
				{field:'routecode',title:'车组代码',width:20,sortable:true,
					formatter:function(value,row,index){return row.routecode;}
				},
				{field:'custsum',title:'访问零售户数量',width:20,sortable:true,
					formatter:function(value,row,index){return row.custsum;}
				},
				{field:'scoretime',title:'考核时间',width:20,
					formatter:function(value,row,index){return row.scoretime;}
				},
			
			]],
			
			toolbar:'#toolbar',
			onLoadSuccess:function(){
				$('#dataTable').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				$('#tabdiv .panel-header').css('display','none');
			}
			});
		}
	}

	});

	});
function compute() {//计算函数
	var cnum=0;
    var rows = $('#dataTable').datagrid('getRows')//获取当前的数据行
    var cnumtotal = 0;//计算零售户的总和
    var time=$('#scoringtime').datebox('getValue');
    for (var i = 0; i < rows.length; i++) {   	
       	cnum=rows[i]['custid'];if(cnum==null||cnum=="")cnum=0;//零售户数量为空时赋值为0，否则会显示NaN。
    	cnumtotal += cnum;
    }

    //新增一行显示统计信息
    $('#dataTable').datagrid('appendRow', { routecode: '<b>合计：</b>', custid: cnumtotal,scoringtime: time });
}

	  //查询
	function searchRouteScoresummary(){
		var params = $('#dataTable').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			params[field.name] = field.value; //设置查询参数
		}); 
		$('#dataTable').datagrid('reload'); 
	}
	
	//清空查询条件
	function clearForm(){
		$('#queryForm').form('clear');
		$('#queryForm').form('reset');
		 $('#scoringtime').datebox('setValue', formatterDate(new Date()));
		 $("#ctype").combobox('setValue',"10")
		  $("#ctype").combobox('setText',"已考核车组信息")
		searchRouteScoresummary();
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