<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title><c:out value="${title}"/></title>
	<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
</head>

	<script type="text/javascript">
	var lastIndex=0;
	var editRow = undefined; //定义全局变量：当前编辑的行
	</script>
	
<body style="margin: 0px;">
	<div id="toolbar" style="height:auto;border-top:1px solid #95B8E7">
	<form id="queryForm" style="margin:10;">
		<a href="javascript:void(0)" id="newBtn" iconCls="icon-add" class="easyui-linkbutton" onclick="openMenu()">新增</a>
		<a href="javascript:void(0)" id="editBtn" iconCls="icon-edit" class="easyui-linkbutton" onclick="edit()">修改</a>
		<a href="javascript:void(0)" id="saveBtn" iconCls="icon-save" class="easyui-linkbutton" onclick="saveEdit()">保存</a>
		<a href="javascript:void(0)" id="candelBtn" iconCls="icon-remove" class="easyui-linkbutton" onclick="cancel()">取消</a>
		<a href="javascript:void(0)" id="delBtn" iconCls="icon-cancel" class="easyui-linkbutton" onclick="del()">删除</a>
		类别：<input name="typeid" id="typeid" class="easyui-textbox" style="width:120px" >
		</form>
	</div>
	<table id="tg" >
	</table>
	
	<!-- 新增对话框 -->
	<div id="add-dlg" class="easyui-dialog" style="margin:0 auto;width:600px;height:300px;padding:10px 20px;left:150px;"
			 closed="true" buttons="#dlg-buttons"  data-options="modal:true">
		<form  id="add-fm" method="post" action="" novalidate  >
		<input type="hidden"  name="parentid" id="parentid">
		<input type="hidden"  name="typelevel" id="typelevel">
			<div class="fitem">
			<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2" >
			<tr  height="20" style="margin:5px">
			<td width="15%" height="20">类别代码:</td>
			<td width="85%" height="20"><input name="typecode" class="easyui-textbox"   required="true" data-options="validType:'length[1,20]'"  style="width:360px;height:25px;align:left;"></td>
			</tr>
			<tr  height="20" >
			<td width="15%">类别名称：</td>
			<td width="85%"><input name="typename" class="easyui-textbox"  required="true" data-options="validType:'length[0,50]'" style="width:360px;height:25px;align:left;"></td>
			</tr >
			<tr>
			<td width="15%">内容： </td>
			<td width="85%"><input name="contentlist" class="easyui-textbox" required="true"  data-options="validType:'length[0,50]'" style="width:360px;height:25px;align:left;"> </td>
			</tr>
			<tr  height="20" >
			<td width="15%">排序： </td>
			<td width="85%"> <input name="seq" class="easyui-numberbox" data-options="min:1,precision:0"  required="true" style="width:360px;height:25px;align:left;"></td>
			</tr>
			</table>
			</td>
			</tr>
			</div>
		</form>
	</div>
	<div id="dlg-buttons" style="margin-right:20px;">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveMenu()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#add-dlg').dialog('close')">取消</a>
	</div>
	
	<script type="text/javascript">
	jQuery(function($){
		inittype();
		var typeid = $("#typeid").val()
		initTree(typeid);
	});
	
	//初始类别
	function inittype(){
		$("#typeid").combobox({
	        valueField:'id',
	        textField:'name',
	        url:baseURL+"/sys/basemultitypeinfo/getMultitypeOneLevelCombobox.json", //数据来源
	        onSelect: function (row) {  
                if (row != null) {  
                	initTree(row.id);
                }
	        }
	    });
	}
	
	function initTree(typeid){
		$('#tg').treegrid({
			iconCls: 'icon-ok',
			rownumbers: true,
			animate: true,
			collapsible: true,
			fitColumns: true,
			fit:true,
			url: baseURL+'/sys/basemultitypeinfo/getBaseMultitypeInfoList.json?typeid='+typeid,
			method: 'POST',
			idField: 'id',
			treeField: 'typecode',
			showFooter: true,
			toolbar:'#toolbar',
			//checkbox:true,
			 checkOnSelect:false ,
			 columns:[[
				 {field:'typecode',title:'类别代码',width:80,editor:'validatebox',require:true,validType:'length[1,20]'}, 
				 {field:'typename',width:140,editor:'validatebox',require:true,validType:'length[1,50]',title:'类别名称'}, 
				 {field:'contentlist',width:180,editor:'text',require:true,validType:'length[0,50]',title:'内容'}, 
				 {field:'typelevel',width:30,title:'级次'}, 
				 {field:'seq',width:30,editor:'numberbox',title:'排序'}
				 ]],
			onLoadSuccess:function(){
				//expandAll();
			   $('.panel-header').css('display','none'); 
        	},
	     onDblClickRow: function (rowIndex) {
            if (editRow != undefined) {
                $('#listdataTabel').treegrid('endEdit', editRow);
            }
            if (editRow == undefined) {
                $('#listdataTabel').treegrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
	      }
		});
	}
	//查询
	function searchData(typeid){
		var params = $('#tg').datagrid('options').queryParams; //先取得 datagrid 的查询参数
		var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
		$.each( fields, function(i, field){
			//params[field.name] = field.value; //设置查询参数
			params[field.name] = typeid; //设置查询参数
			//alert(typeid);
		}); 
		$('#tg').treegrid('reload'); //设置好查询参数 reload 一下就可以了
	}
	
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
				
			}else
				{
				$.messager.alert('提示',"请选择你需要修改的信息",'info');
				return;
				}
		}
		function saveEdit(){
			if (editingId != undefined){
				var t = $('#tg');
				t.treegrid('endEdit', editingId);
				editingId = undefined;
				//提交数据到服务器
				
				var node =t.treegrid('getSelected');
				var typecode = node.typecode;
				var typename = node.typename;
				var contentlist = node.contentlist;
				if(trim(typecode).length==0){
					$.messager.alert('提示',"请输入类别代码",'info');
					return;
				}
				if(trim(typecode).length>20){
					$.messager.alert('提示',"您输入的类别代码过长，请控制在20字以内",'info');
					return;
				}
				if(trim(typename).length>50){
					$.messager.alert('提示',"您输入的类别名称过长，请控制在50字以内",'info');
					return;
				}
				if(trim(contentlist).length>50){
					$.messager.alert('提示',"您输入的内容过长，请控制在50字以内",'info');
					return;
				}
				if(node){
					$.post(baseURL+"/sys/basemultitypeinfo/doEditMultitype.json", node, function(data) {
						console.log(data.msg);
						$.messager.show({
							title : '提示',
							msg :  data.total+'个多级类别'+data.msg+'！',
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
				//alert("1");
					$('#add-dlg').dialog('open').dialog('setTitle','增加多级类别');
					//$('#add-fm').form('clear');
					$('#parentid').val("0");
					$('#typelevel').val("0");
			}
			else
				{
				//console.log("fid="+rows.id+"父菜单的menulevel:"+rows.menulevel);
				$('#parentid').val(rows.id);
				$('#typelevel').val(rows.typelevel);
				if(rows)
					{
					if(rows.typelevel==4){
						$.messager.alert('提示',"最多只能建四级类别，请选择上级类别再新增",'info');
						return;
					}
					}
				
				$('#add-dlg').dialog('open').dialog('setTitle','增加多级类别');
				//$('#add-fm').form('clear');
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
				url:baseURL+"/sys/basemultitypeinfo/doAddMultitype.json",
				data:$("#add-fm").serializeArray(),
				success: function(data){
					//$('#loading').window('close');
					data = eval('('+data+')');
					$('#add-dlg').dialog('close');
					$('#tg').treegrid('reload'); 
		    		$.messager.show({
						title : '提示',
						msg : '操作成功',
					});
					//$('#loading').window('close');
		    		$('#add-fm').form('clear');
		    		//$('#belongsys').val('${sysid}');
				}
			});
		}
		
		function del(){
			var row = $('#tg').treegrid('getSelected');
			if(row==null){
				$.messager.alert('提示',"请选择你要删除的类别",'info');
				return;
			}
			$.messager.confirm('提示','确定要删除吗?',function(result){
		        if (result){
		        	$.ajax({
						url:baseURL+"/sys/basemultitypeinfo/doDelMultitype.json",
						data:{id:row.id},
						success:function(){
							$.messager.show({
								title : '提示',
								msg : '删除成功',
							});
							$('#tg').treegrid('reload'); 
						}
					});
		        }
			});
			
		}
	</script>
</body>
</html>