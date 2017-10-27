<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title><c:out value="${title}"/></title>
	<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
</head>

	<style>
	.menuUL{list-style-type:none;margin:5px}
	.menuUL li{float:left;margin:5px}
	.menuleft {width:70px;align:left;}
	.menuright {width:300px;align:left;}
	.menuright input{width:250px;align:left;}
	</style>
	<script type="text/javascript">
	var lastIndex=0;
	</script>
	
<body style="margin: 0px;">
	<div id="toolbar" style="height:auto;border-top:1px solid #95B8E7">
	<a href="javascript:void(0)" id="newBtn" iconCls="icon-add" class="easyui-linkbutton" onclick="openGrant()">授权</a>
	<a href="javascript:void(0)" id="delBtn" iconCls="icon-cancel" class="easyui-linkbutton" onclick="del()">删除</a>
	</div>
	<table id="tg" class="easyui-treegrid" title="用户级次" style="width:100%;height:470px;"
			data-options="
				iconCls: 'icon-ok',
				rownumbers: true,
				animate: true,
				collapsible: true,
				fitColumns: true,
				url: baseURL+'/perform/userlevel/getUserlevelList.json',
				method: 'POST',
				idField: 'userid',
				treeField: 'username',
				showFooter: true,
				toolbar:'#toolbar',
				//checkbox:true,
				 checkOnSelect:false ,
				onLoadSuccess:function(){
					//expandAll();
				   $('.panel-header').css('display','none'); 
	        	},
	        	onClickRow:function (row){
	        	var rowindex = row.id;
	        		if(lastIndex==rowindex){
	        		    $('#tg').treegrid('unselectAll');
	        		}
	        		lastIndex=rowindex;
	        	},
		     onDblClickRow: function (row) {
		    			 var rowindex = row.id;
			            var rows =$('#tg').treegrid('getSelected');
						if(rows==null){
							 $('#tg').treegrid('select',rowindex);
						}

		      }              
			">
		<thead >
			<tr >
				
				<th data-options="field:'username',width:120,editor:'text'">用户姓名</th>
			</tr>
		</thead>
	</table>
	
	
	
	<script type="text/javascript">
	
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
		function saveEdit(){
			if (editingId != undefined){
				var t = $('#tg');
				t.treegrid('endEdit', editingId);
				editingId = undefined;
				//提交数据到服务器
				var node =t.treegrid('getSelected');
				if(node){
					$.post(baseURL+"/cost/SPLTypeInfo/doEditSPLTypeInfo.json", node, function(data) {
						$.messager.show({
							title : '提示',
							msg :  data.total+'个类型'+data.msg+'！',
						});
					});
				}
				
				
				//var frow = t.treegrid('getFooterRows')[0];
				//frow.persons = persons;
				//t.treegrid('reloadFooter');
			}
		}
			
		function cancel(){
			if (editingId != undefined){
				$('#tg').treegrid('cancelEdit', editingId);
				editingId = undefined;
			}
		}
		
		/**
		 * 打开新增角色窗口
		 */
		function openMenu(){
			var t = $('#tg');
			var rows =t.treegrid('getSelected');
			if(rows==null){
					$('#add-dlg').dialog('open').dialog('setTitle','增加菜单');
					//$('#add-fm').form('clear');
					$('#fid').val("0");
					$('#clevel').val("0");
			}
			else
				{
				var levl = rows.clevel;
				//console.log("fid="+rows.id+"父菜单的menulevel:"+rows.menulevel);
				$('#fid').val(rows.id);
				$('#clevel').val(levl);
				//$('#ftypename').textbox("setValue",rows.ftypename);
				if(levl==1){
					$('#ftypename').textbox("setText","无");
				}
				else
					{
					$('#ftypename').textbox("setText",rows.ftypename);
					}
				
				//$('#ftypename').val(rows.ftypename);
				if(rows)
					{
					if(levl==4){
						$.messager.alert('提示',"最多只能建四级类别，请选择上级类别再新增",'info');
						return;
					}
					}
				$('#add-dlg').dialog('open').dialog('setTitle','增加菜单');
				}
		}
		
		/**
		 * 新建菜单
		 */
		function saveMenu(){
			$('#add-fm').form('submit',{
				onSubmit: function(param){
					//param.sysid='${sysid}';
					//console.log($("----"+"#add-fm").serializeArray());
					var isValidate = $(this).form('validate');
					if(isValidate){
						//$('#loading').window('open');
					}
					return isValidate;
				},
				url:baseURL+"/cost/SPLTypeInfo/doAddSPLTypeInfo.json",
				data:$("#add-fm").serializeArray(),
				success: function(data){
					//$('#loading').window('close');
					data = eval('('+data+')');
					$('#add-dlg').dialog('close');
					$('#tg').treegrid('reload'); 
					$.messager.show({
						title : '提示',
						msg :  data.total+'个类型'+data.msg+'！',
					});
					//$('#loading').window('close');
		    		$('#add-fm').form('clear');
		    		$('#belongsys').val('${sysid}');
				}
			});
		}
		
		function del(){
			var row = $('#tg').treegrid('getSelected');
			if(row==null){
				$.messager.alert('提示',"请选择你要删除的该类别吗？",'info');
				return;
			}
			$.messager.confirm('提示','确定要删除吗?',function(result){
		        if (result){
		        	$.ajax({
						url:baseURL+"/perform/userlevel/doDelUserlevel.json",
						data:{userid:row.userid},
						success:function(data){
							console.log(data.msg);
							if(data.total==0){//删除失败或不能删除
								$.messager.alert('提示',data.msg,'info');
								return;
							}
							else{
								$.messager.show({
									title : '提示',
									msg :  data.msg+'！',
								});
								$('#tg').treegrid('reload'); 
							}
						}
					});
		        }
			});
		}
		
		function openGrant(){
			
			var rows = $('#tg').treegrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要授权的用户",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"一次只能选择一个角色授权",'info');
				return;
			}
			var row = $('#tg').treegrid('getSelected');
			//console.log(row.id);
			var userid=row.userid;
			var userlevel = row.userlevel;
			parent.addTab('用户授权',baseURL+"/chooseUser?roleid="+userid+"&optype=performuserlevel",'icon-mygroup');
		}
	</script>
	
</body>
</html>