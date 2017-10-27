$(function () {

	
	$('#dept').panel({
		//id : 'pox',
		title : '部门',
		width : 288,
		height : 410,
		iconCls : 'icon-search',
		//left : 100,
		//top : 100,	
		cls : 'a',
		headerCls : 'b',
		bodyCls : 'c',
		style : {
			'min-height' : '200px',
		},
		//fit : true,
		//border : false,
		//doSize : false,
		//noheader : true,
		//content : '修改了',
		//collapsible : true,
		//minimizable : true,
		//maximizable : true,
		//closable : true,
		//tools : '#tt',
		tools : [{
			iconCls : 'icon-help',
			handler : function () {
				alert('help');
			},
		},{
		}],
		//collapsed : true,
		//minimized : true,
		//maximized : true,
		//closed : true,
		//href : 'content.php',
		//loadingMessage : '加载中...',
		//extractor : function (data) {
		//	return data.substring(0,3);
		//}
	});
	
	/*
	$.ajax({
	     url: '/BS56/sys/getDeptTree.json',
	     success: function (result) {
	         var myJson = eval( result );
	         $('#tt').tree({
	             data: myJson
	         });
	     }
	 })
	 */
	
	$('#dept').tree({
		url: '/BS56/user/getDeptTree.json',
		lines : true,
		onClick:function(node){
				console.log(node.id+":"+node.text);
				if(hasGetDept!=node.id)
					{
						$.ajax({
						     url: '/BS56/user/getUserListByDeptID.json?depId='+node.id,
						     method:"GET",
						     success: function (result) {
						    	 $("#userUL").children("li").remove();//清空控件中存在的用户然后在添加获取的用户
						    	 var has = false;
						         for(var i in result)
						         {  
						        	 hasGetDept = node.id;
						        	 
											    $("#userUL").append ("<li id='user_list'>"+result[i].username+"</li>");
						         }
						         initUserList();
						     }
						 })
				   }
		}
	});
	
	$('#button_ok').click(function(){
		var text ="";
		 $(".selected_user li").each(function(){
			  console.log($(this).text());
					  text+= $(this).text()+";";
			  });
		//alert(text);
	});
	//$('#box').panel('panel').css('position', 'absolute');
	
	
});
