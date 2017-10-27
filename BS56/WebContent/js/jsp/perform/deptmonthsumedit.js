/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	//初始化记录人、记录日期、检查日期、检查部门
	var nowTimeYMD = getDateYMD();
	$('#username_new').textbox("setValue",username);
	$('#username_new').textbox("setText",username);
	$('#deptid_new').textbox("setValue",deptname);
	$('#deptid_new').textbox("setText",deptname);
	
    $('#scoremonth_new').datebox({
    	onSelect: function(date){
    		scoremonth = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
    		$('#worklist').datagrid('reload'); 
    	}
    });
    $('#scoremonth_new').datebox("setValue",nowTimeYMD);//考核日期
    
    $.ajax({ 
	    url: baseURL+'/perform/deptmonthsum/getDeptmonthsumByid.json?obid='+obid, 
	    type: 'POST', 
	    success: function(data){
	    	//data = eval('('+data+')');
	    	console.log(data);
	    	console.log("--"+data.matter);
	    	$('#scoremonth_new').datebox("setValue",scoremonth+"-01");
	    	$('#matters_new').textbox("setValue",data.matter);
	    	$('#matters_new').textbox("setText",data.matter);
	    	$('#unfinished_new').textbox("setValue",data.unfinished);
	    	$('#unfinished_new').textbox("setText",data.unfinished);
		}
	   }); 
    
});

function closethistab(tabname){
	$.messager.confirm('提示','确定关闭吗?',function(result){
		if (result){
			window.parent.tabCloseForChild(tabname);
			//window.parent.refreshTab("绩效考核");
		}
	});
}

