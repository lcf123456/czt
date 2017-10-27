/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	//初始化记录人、记录日期、检查日期、检查部门
	initgrading();
    $.ajax({ 
	    url: baseURL+'/perform/deptmonthsum/getDeptmonthsumByid.json?obid='+obid, 
	    type: 'POST', 
	    success: function(data){
	    	//data = eval('('+data+')');
	    	$('#username_new').textbox("setValue",data.selfuser);
	    	$('#username_new').textbox("setText",data.selfuser);
	    	$('#deptid_new').textbox("setValue",data.belongdeptname);
	    	$('#deptid_new').textbox("setText",data.belongdeptname);
	    	$('#scoremonth_new').textbox("setValue",scoremonth+"-01");
	    	$('#scoremonth_new').textbox("setText",scoremonth+"-01");
	    	$('#matters_new').textbox("setValue",data.matter);
	    	$('#matters_new').textbox("setText",data.matter);
	    	$('#unfinished_new').textbox("setValue",data.unfinished);
	    	$('#unfinished_new').textbox("setText",data.unfinished);
		}
	   }); 
    
});
//初始化日期类型
function initgrading(){
	$("#genmngrating").combobox({
        valueField:'code',
        textField:'name',
        data:[
        	{
        		code:"A",
        		name:"A"
        	},{
        		code:"B",
        		name:"B"
        	},{
        		code:"C",
        		name:"C"
        	},{
        		code:"D",
        		name:"D"
        	}],
	onLoadSuccess: function () {
		 var val = $(this).combobox("getData");
		 for (var item in val[0]) {
             if (item == "code") {
                 $(this).combobox("select", val[0][item]);
             }
         }
	}
    });
}

