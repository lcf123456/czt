var url;

//信息上传进度条初始化
$(function(){
	$.ajax({ 
	    url: baseURL+'/sys/getSysMenu.json', 
	    type: 'POST', 
	    success: function(data){
			//console.log(data);
			//var result = eval('('+data.rows+')');
	    	var url;
			$(data.rows).each(function(i,val) {
				console.log(val.id+"--"+val.name+",roleid="+roleid);
			  url = baseURL+"/sys/toRoleSetting?id="+val.id+"&roleid="+roleid;
			  addTab(val.id,val.name,url);

				}); 
		}
	   }) 
});


//添加选项卡  
function addTab(id,title, url){                  
        content = '<iframe scrolling="no" frameborder="0"  src="' + url+ '" style="width:100%;height:100%;"></iframe>';  
        if(!$("#tabs-box").tabs('exists', title)){  
            $("#tabs-box").tabs("add", { 
         id : id,
         title : title,  
         closable :  false,  
         collapsible : true,
         content : content,  
         width: 600 ,  
         height: 400,  
         selected : true,
         iconCls : 'icon-add',
         border:true
        } );                   
    }else{  
         $('#tabs-box').tabs('select', title);  
    }            
} 