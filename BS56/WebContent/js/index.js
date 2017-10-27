$(function () {

	$('#box').panel({
		//id : 'pox',
		title : '面板',
		width : 500,
		height : 150,
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
		collapsible : true,
		minimizable : true,
		maximizable : true,
		closable : true,
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
	
	
	//$('#box').panel('panel').css('position', 'absolute');
	
	
});
