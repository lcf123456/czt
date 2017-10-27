
/**
 * 页面列表datagrid初始化
 */

jQuery(function($){

	var obj = $("#consignsorsearch");
	initconsignsor(obj);
});

//初始货主
function initconsignsor(obj){
	obj.combobox({
        valueField:'code',
        textField:'company',
        url:baseURL+"/wms/consignor/getConsignorListForComb.json", //数据来源
	onLoadSuccess: function () {
		 var val = $(this).combobox("getData");
		 for (var item in val[0]) {
             if (item == "code") {
                 $(this).combobox("select", val[0][item]);
             }
         }
	},
	onSelect: function (row2) {  
        if (row2 != null) {  
        	$.messager.progress();	// 显示进度条
	   		 $.ajax({
	   			 url: baseURL+'/wms/itemstock/getItemstockList.json?consignsor='+row2.code, 
	   		 	success: function(html){
	   		 		var main = $("#main");
	   		 		main.html(html);
	   		 		$.messager.progress('close');	// 如果提交成功则隐藏进度条
	   		 	}
	   		 });
        }
        }
    });
}

function exportxls()
{
	var consignsorsearch  =  $("#consignsorsearch").val();
	 $.messager.confirm('提示','确定要导出到excel吗?',function(result){
	        if (result){
	        	 $('#searchForm').form('submit', {
	        		 url: baseURL+'/wms/itemstock/doExportItemstockToExcel.json?consignsor='+consignsorsearch
	        		
	        	 });
	        }
	 });
	
}
	
