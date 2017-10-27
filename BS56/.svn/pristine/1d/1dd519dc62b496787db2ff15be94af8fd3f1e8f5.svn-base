<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>
<html>
<head>
	<meta charset="UTF-8">
	 <title><c:out value="${titleInfo}"/></title>
	<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>


</head>

<body>

	<div id="toolbar" style="padding:1px;height:auto;">
	<a href="javascript:void(0)" iconCls="icon-myarrowout" class="easyui-linkbutton" onclick="expandAll()">全部展开</a>
		<a href="javascript:void(0)" iconCls="icon-myarrowin" class="easyui-linkbutton" onclick="collapseAll()">全部收起</a>
		<a href="javascript:void(0)" iconCls="icon-save" class="easyui-linkbutton" onclick="save()">保存</a>
		<a href="javascript:void(0)" iconCls="icon-remove" class="easyui-linkbutton" onclick="cancel()">取消</a>
	</div>
	<div id="tg" style="width:100%;height:440px;"></div>
	
	<script type="text/javascript">
		jQuery(function($){
			$('#tg').treegrid({
				iconCls: 'icon-ok',
				rownumbers: true,
				animate: true,
				fitColumns: true,
				url: baseURL+'/sys/menu/getSysMenuRightByRole.json?sysid=${sysid}&roleid=${roleid}',
				method: 'POST',
				idField: 'id',
				treeField: 'menuname',
				showFooter: true,
				toolbar:'#toolbar',
				checkbox:true,
				lines:true,
				columns:[[
					{field:'menuname',title:'菜单名称',width:120},
					{field:'menulevel',title:'菜单级次',width:40},
					{field:'url',title:'url',width:180},
					{field:'seq',title:'排序',width:40},
					{field:'remarks',title:'备注',width:80}
				]],
				onLoadSuccess:function(){
					expandAll();
				  // $('.panel datagrid easyui-fluid .panel-header').css('display','none'); 
	        	}
			});
		})
		
		function formatProgress(value){
	    	if (value){
		    	var s = '<div style="width:100%;border:1px solid #ccc">' +
		    			'<div style="width:' + value + '%;background:#cc0000;color:#fff">' + value + '%' + '</div>'
		    			'</div>';
		    	return s;
	    	} else {
		    	return '';
	    	}
		}
		var editingId;
		function edit(){
			if (editingId != undefined){
				$('#tg').treegrid('select', editingId);
				return;
			}
			var row = $('#tg').treegrid('getSelected');
			if (row){
				editingId = row.id
				$('#tg').treegrid('beginEdit', editingId);
			}
		}
		function save(){
			var idList = ""; 
			
			
			$(".tree-checkbox1").parents(".datagrid-row").each(function(){
			  var id = $(this).attr("node-id")
						idList += id+',';
			})
			if(idList=="")
				{
				    $.messager.alert("操作提示", "请选择需要授权的菜单！","info");
				    return;
				}
			else
				{
				$.ajax({
					type: "POST",
					url: baseURL+"/sys/role/doRoleSetting.json",
					data: {idList:idList,roleid:'${roleid}'},  //第二种方式传参
					 success: function(msg){
					     $.messager.show({
								title : '提示',
								msg :  msg.msg
							});
					   }
				});
				//alert("come");
				}
			
			}
		
		
		function cancel(){
			$.messager.confirm("操作提示", "您确定要取消操作吗？", function (data) {  
	            if (data) {  
	            	parent.$("#rolewin").window("close");
	            	//window.opener = window;
	               // var win = window.open("","_self");
	               // win.close();
	               // top.close();
	            }  
	        });  
			
		}
		
		/**
		 * 打开新增角色窗口
		 */
		function openMenu(){
			var t = $('#tg');
			var rows =t.treegrid('getSelected');
			console.log("level:"+rows.menulevel);
			if(rows)
				{
				if(rows.menulevel==3){
					$.messager.alert('提示',"最多只能建三级菜单，请选择上级菜单再新增",'info');
					return;
				}
				}
			
			$('#add-dlg').dialog('open').dialog('setTitle','增加菜单');
			$('#add-fm').form('clear');
		}
		
		/**
		 * 新建菜单，待实现------
		 */
		function saveMenu(){
			$('#add-fm').form('submit',{
				onSubmit: function(){
					var isValidate = $(this).form('validate');
					if(isValidate){
						//$('#loading').window('open');
					}
					return isValidate;
				},
				url:baseURL+"/sys/role/roleNew.json",
				data:$("#add-fm").serializeArray(),
				success: function(data){
					//$('#loading').window('close');
					data = eval('('+data+')');
					$('#add-dlg').dialog('close');
					$('#dataTabel').datagrid('reload'); 
		    		$.messager.show({
						title : '提示',
						msg :  data.total+'个角色新增'+data.msg+'！',
					});
					//$('#loading').window('close');
				}
			});
		}
		
		function collapseAll(){
            var node = $('#tg').treegrid('getSelected');
            if (node) {
                $('#tg').treegrid('collapseAll', node.target);
            }
            else {
                $('#tg').treegrid('collapseAll');
            }
        }
       
        function expandAll(){
            var node = $('#tg').treegrid('getSelected');
            if (node) {
                $('#tg').treegrid('expandAll', node.target);
            }
            else {
                $('#tg').treegrid('expandAll');
            }
        }
	</script>

</body>
</html>