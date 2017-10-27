var mid;
var mcode;
var murl;
var mname;

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
	 
	  $('#cmbox').combobox({
		    url:baseURL+"/sys/menu/getSysMenuforCMBOX.json",
			valueField:'id',  
			textField:'text',
			 onChange: function (n,o) {
				 $('#loading').window('open');
				  console.log("new="+n+",old="+o);
				  sysid = n;
				 
				  $.ajax({
					  url:baseURL+'/sys/role/getRoleoperationList.json?roleid='+roleid+"&sysid="+sysid,
					  success:function(data){
						  //console.log(data);
						  
						  $(".gridtable tr").remove(".dynamic-tr");
						  $('#loading').window('close');
						  for(var i=0; i<data.length; i++)  
						  {  
							 // console.log(data[i].id+" " + data[i].menuname)  
							  var checkTmp = "";
							  for(var j=0;j<data[i].operationinfoVoList.length;j++){
								 // <input name="operation" type="checkbox" value="${operation.id }" title="${operation.remarks} "/>${operation.name }&nbsp;&nbsp;&nbsp;&nbsp;
								  checkTmp += "<input name='operation' type='checkbox' value='"+data[i].operationinfoVoList[j].id+"' title='"+data[i].operationinfoVoList[j].remarks+"' "+data[i].operationinfoVoList[j].checked+"/>"+data[i].operationinfoVoList[j].name+"&nbsp;&nbsp;&nbsp;&nbsp;";
							  }
							  $(".gridtable tbody").append ("<tr class='dynamic-tr'><td>"+data[i].fmenuname+"</td><td>"
									  +data[i].menuname+"</td><td>"
									  +checkTmp+"</td></tr>");
						  } 
						 
						 
					  }
				  });
			  }
		});
	  /*
	  $.ajax({
		  url:baseURL+"/sys/role/getMenuOperationList.json?sysid=1",
		  type: 'GET', 
		  success: function(data){
			  console.log(data);
		  }
	  });
	  */
	  
	  $("#checkall").click(
			  function(){
			    if(this.checked){
			        $("input[name='operation']").prop('checked', true)
			    }else{
			        $("input[name='operation']").prop('checked', false)
			    }
			  }
			);
});

function saveCheck()
{
	var obj=document.getElementsByName('operation'); 
	var s='';
	for(var i=0; i<obj.length; i++){
	if(obj[i].checked) {
		if(s==''){
		s+="?opid="+obj[i].value; //如果选中，将value添加到变量s中
		}else{
			s+="&opid="+obj[i].value; //如果选中，将value添加到变量s中
		}
		
	}
	}
	if(s==''){
		$.messager.alert('提示',"请选择你要授权的功能点",'info');
		return;
	}
	//那么现在来检测s的值就知道选中的复选框的值了
	$.post(baseURL+'/sys/role/doRoleOperation.json'+s+"&roleid="+roleid+"&sysid="+sysid,function(data){
		$.messager.show({
			title : '提示',
			msg :  data.total+'个功能点'+data.msg+'！',
		});
	});
}

