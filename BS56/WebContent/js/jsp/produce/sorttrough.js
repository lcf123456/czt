var url;

//信息上传进度条初始化
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
});







/**
 * 页面列表datagrid初始化
 */
jQuery(function($){
	//alert(baseURL+"/sq/starinfo/getStarinfo.json");
	var orderdate=getDateYMD();
	//alert(orderdate);
	$('#orderdate').datebox("setValue",orderdate);
	$('#dataTabel').datagrid({
		title:title, //标题
		method:'post',
		halign: 'center' ,
		//iconCls:'icon-edit', //图标
		singleSelect:true, //多选
		fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, //奇偶行颜色不同
		//collapsible:true,//可折叠
		url:baseURL+"/produce/sorttrough/getSortTroughinfo.json", //数据来源
		sortName: 'id', //排序的列
		sortOrder: 'asc', //正序asc、倒序 desc
		remoteSort: true, //服务器端排序
		//idField:'id', //主键字段
		//pageNumber: 1, 
		//pageSize : 10,
		//pageList: [10,30,50],
		queryParams:{troughtype:ty,cigarettetype:cty,groupno:groupno,orderdate:orderdate
			}, //查询条件
		//pagination:true, //显示分页
		//pageSize : 1,
		//rownumbers:true, //显示行号
		//toolbar:toolbar,
		columns:[[
			//{field:'id',width:2}, //显示复选框
			{field:'cigarettecode',title:'卷烟编码',width:40,
				formatter:function(value,row,index){return row.cigarettecode;} //需要formatter一下才能显示正确的数据
			},
			{field:'cigarettename',title:'卷烟名称',width:60,
				formatter:function(value,row,index){return row.cigarettename;}
			},
			{field:'troughnum',title:'通道号',width:25,
				formatter:function(value,row,index){return row.troughnum;}
			},
			{field:'lastmantissa',title:'理论尾数',width:25,
				formatter:function(value,row,index){return row.lastmantissa;}
			},
			{field:'fillqty',title:'填报尾数',width:30,
				formatter:function(value,row,index){
				
					if(row.actcount!=999)
						{
						
						return "<font color='red'>"+row.fillqty+"</font>";
						}
					
					else
						{
						return row.fillqty;
						}
					},
			editor:{
				type:'numberbox'
				
			}
					},
																																																																																																																																																																											{field:'action',title:'操作',align:'center',width:$(this).width()*0.1,
																																																																																																																																																																												formatter:function(value, row, index){
				
					if (row.editing){
					var str = '<a href="#"  class="easyui-linkbutton" onclick="saverow('+index+')">保存</a>';
					return str;
					}
					else
						{
						var str = '<a href="#"  class="easyui-linkbutton" onclick="editmainssa('+index+')">修改</a>';
						return str;
						}
			}}
				
			
			
		]],
	
		onBeforeEdit:function(index,row){
			row.editing = true;
			updateActions(index);
			//$('#dataTabel').datagrid('refreshRow', index);
		},
		onAfterEdit:function(index,row){
			row.editing = false;
			submitModify(row);
			updateActions(index);
			
		},
		onCancelEdit:function(index,row){
			row.editing = false;
			updateActions(index);
		},
		toolbar:"#toolbar",
		onLoadSuccess:function(){
			$('#dataTabel').datagrid('clearSelections'); //一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题		}
	});
	
});
var i=0;
function updateActions(index){

	$('#dataTabel').datagrid('updateRow',{
		index: index,
		row:{boxcount:i++,actcount:999}
	});
	
}

function submitModify(row)
{
	$.ajax({
		  type: 'POST',
		  url:baseURL+"/produce/sorttrough/UpdateMainssa.json", //数据来源
		  //data:{row},
		  data:{ fillqty:row.fillqty, troughnum:row.troughnum, groupno:row.groupno,cigarettecode:row.cigarettecode,cigarettename:row.cigarettename,troughtype:ty,cigarettetype:cty,id:row.id,mantissa:row.mantissa,orderdate:$("#orderdate").datebox("getValue")} ,
		  dataType: 'json',
		  success:function(data){if(data==10){$.messager.show({
				title : '提示',
				msg :  '修改成功！',
			});}},
		});
}
function saverow(index){
	$('#dataTabel').datagrid('endEdit', index);
	
}
function editmainssa(index)
{
	$('#dataTabel').datagrid('beginEdit', index);
	}


  
 