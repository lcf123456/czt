<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 调拨出库界面 -->
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/perform/deptmonthsumadd.js"/>"></script>
  <head>
  <script type="text/javascript">
  var deptid = "${userVo.deptid}";////出库类型(10订单出库 20 调拨出库)
  var username = "${userVo.username}";
  var deptname = "${userVo.deptname}";
  var scoremonth = getDateYMD();
  var obid=0;//出库单主表id
	
</script>
  </head>
  
  <body>
	

    <!-- 1、新增--------------------------------------------------------------------------------------->
	<div id="add-dlg" >
		<form  id="add-fm" method="post" action="" novalidate  >
			<div >
			<tr>
			<td>
			<table width="100%" class="gridtable">
			<tr>
		  		 	<td width="5%" height="20" align="left" nowrap>记录人：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="username_new" id="username_new" class="easyui-textbox"  style="width:120px"  >
		           	</td>
		            <td width="5%" height="20" align="left" nowrap>部门：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="deptid_new" id="deptid_new" class="easyui-textbox" style="width:120px" >
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>考核日期：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="scoremonth_new" id="scoremonth_new"  style="width:160px" />
		           </td>
	         </tr>
	         <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>需注意工作事项：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="3">
		                <input name="matters_new" id="matters_new" class="easyui-textbox"  style="width:430px"  >
		           	</td>
		  		 	<td width="5%" height="20" align="left" nowrap>未完成事项说明：</td>
		           	<td width="14%" height="20" align="left" nowrap >
		                <input name="unfinished_new" id="unfinished_new" class="easyui-textbox"  style="width:230px"  >
		           	</td>
	         </tr>
			</table>
			</td>
			</tr>
			<input type="hidden" name="updaterows" id="updaterows" class="easyui-textbox"></input> <!--保存修改行的数据 -->
			<input type="hidden" name="scoresum" id="scoresum" class="easyui-textbox"></input> <!-- 保存评分合计 -->
			</div>
			
	<table id="worklist" class="easyui-datagrid" title="考核列表" style="width:100%;height:auto"
            data-options="
                iconCls: 'icon-edit',
                singleSelect: true,
                toolbar: '#dlg-buttons',
                url:baseURL+'/perform/keywork/getAllKeyworkList.json?searchdept='+deptid+'&searchtime='+scoremonth, 
                method: 'get',
                onClickCell: onClickCell,
                onLoadSuccess:function(data){  
		            $('#worklist').datagrid('appendRow',  
                {  
                	id:0,
                    typename:'合计',  
                    content:' ',
                    scorevalue:' ',
                    process:' ',
                    finishnote:' ',
                    selfscore:100,
                    note:''
            //合并单元格  
                });  
		        }
            ">
        <thead>
            <tr>
                <th data-options="field:'id',width:$(this).width()*0.05,align:'right'">id</th>
                <th data-options="field:'typename',width:$(this).width()*0.1,align:'right'">类别</th>
                <th data-options="field:'content',width:$(this).width()*0.2,align:'right'">考核项目</th>
                <th data-options="field:'scorevalue',width:$(this).width()*0.05,">分值</th>
                <th data-options="field:'process',width:$(this).width()*0.2,align:'right'">考核标准</th>
                <th data-options="field:'finishnote',width:$(this).width()*0.2,align:'right',editor:'textbox'">完成情况</th>
                <th data-options="field:'selfscore',width:$(this).width()*0.1,align:'right',editor:{type:'numberbox',options:{precision:2,max:100,min:0}}">评分</th>
                <th data-options="field:'note',width:$(this).width()*0.1,align:'right',editor:'textbox'">加扣分说明</th>
            </tr>
        </thead>
    </table>
    
		</form>
		
	 <div id="dlg-buttons">
	    <a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-ok" onclick="accept()">保存</a>
	    <a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-back" onclick="reject()">撤销</a>
		<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-cancel" onclick="javascript:$('#add-dlg').dialog('close')">关闭</a>
	</div>
	</div>
	

  <script type="text/javascript">
  		//编辑的行
		  var editIndex = undefined;
		  function endEditing() {
		   if (editIndex == undefined){return true}
		   $('#worklist').datagrid('endEdit', editIndex);
		   editIndex = undefined;
		   return true;
		  }
		   
		  function onClickCell(index, field){
			//合计行不编辑：
		      $('#worklist').datagrid('selectRow',index);// 关键在这里  
	          var row = $('#worklist').datagrid('getSelected'); 
		      var typename = row.typename;
		      if(typename!="合计"){
		    	  if (editIndex != index) {
		  		    if (endEditing()) {
		  		     $('#worklist').datagrid('selectRow', index)
		  		       .datagrid('beginEdit', index);
		  		     var ed = $('#worklist').datagrid('getEditor', { index: index, field: field });
		  		     if (ed) {
		  		      ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
		  		     }
		  		     editIndex = index;
		  		    } else {
		  		     setTimeout(function () {
		  		      $('#worklist').datagrid('selectRow', editIndex);
		  		     }, 0);
		  		    }
		  		   }
		      }
		   // alert(  compute("selfscore"));
		    var hj  = compute("selfscore");//$('#worklist').datagrid('getRows')
		   // $('#worklist').datagrid('updateRow',{index:$('#worklist').datagrid('getRowIndex','0'),row:{selfscore:hj}})
		    $('#worklist').datagrid('updateRow',{index:$('#worklist').datagrid('getRows').length-1,row:{selfscore:hj}});
		    $('#scoresum').textbox("setValue",hj);
			$('#scoresum').textbox("setText",hj);
		  }
		  function onEndEdit(index, row){
//		   var ed = $(this).datagrid('getEditor', {
//		    index: index,
//		    field: 'id'
//		   });
//		   row.selfscore = $(ed.target).combobox('getText');
		  }
//		  function append(){
//		   var index = $('#worklist').datagrid('getRowIndex', $('#worklist').datagrid('getSelected'));
//		   if (index == -1)
//		    index = 0;
//		   $("#worklist").datagrid("insertRow", {
//		    index: index+1,
//		    row: {oper: "<a href='javascript:append()'>+<a> <a href='javascript:removeit()'>-<a>",status:'P'}
//		    });
//		  }
//		  function removeit(){
//		   if (editIndex == undefined){return}
//		   $('#worklist').datagrid('selectRow', editIndex);
//		 
//		    $('#worklist').datagrid('cancelEdit', editIndex)
//		     .datagrid('deleteRow', editIndex);
//		   editIndex = undefined;
//		  }
		  function accept(){
			var entities = "";
			var effectRow = new Object();
		   if (endEditing()){
		    var $dg = $('#worklist');
		    var rows = $dg.datagrid('getChanges');
		    if (rows.length) {
		     var inserted = $dg.datagrid('getChanges', "inserted");
		     var deleted = $dg.datagrid('getChanges', "deleted");
		     var updated = $dg.datagrid('getChanges', "updated");
		     
		     
		     if (inserted.length) {
		      effectRow["inserted"] = JSON.stringify(inserted);
		     }
		     if (deleted.length) {
		      effectRow["deleted"] = JSON.stringify(deleted);
		      
		     }
		     if (updated.length) {
		      effectRow["updated"] = JSON.stringify(updated);
		      entities = entities +JSON.stringify(updated);
		     }
		     //alert(inserted);
		     //alert(deleted);
		     
		    }
		   }
		   
		   //entities = '{"updated":'+ entities+'}';
		   console.log("updated-5--"+entities);
		   
		   $('#updaterows').textbox("setValue",entities);//修改的行记录
		   $('#updaterows').textbox("setText",entities);
		   var hj  = compute("selfscore");//$('#worklist').datagrid('getRows')
		    $('#scoresum').textbox("setValue",hj);//修改的后的合计
			$('#scoresum').textbox("setText",hj);
			
		   $('#add-fm').form('submit',{
				onSubmit: function(){
					var isValidate = $(this).form('validate');
					if(isValidate){
						//$('#loading').window('open');
					}
					return isValidate;
				},
				url:baseURL+"/perform/deptmonthsum/doSave.json",
				//data: {'},
				beforeSend : function () {
					$.messager.progress({
						text : '正在新增中...',
					});
				},
				success: function(data){
					//$('#loading').window('close');
					data = eval('('+data+')');
					//$('#add-dlg').dialog('close');
					//$('#worklist').datagrid('reload'); 
		    		$.messager.show({
						title : '提示',
						msg :  data.total+'个考核新增'+data.msg+'！',
					});
					//$('#loading').window('close');
				}
			});
		   //$.post("/Home/Commit", effectRow, function (rsp) {
		   // if (rsp) {
		   //  $dg.datagrid('acceptChanges');
		   //  bindData();
		   // }
		   //}, "JSON").error(function () {
		   // $.messager.alert("提示", "提交错误了！");
		   //});
		  }
		  function reject(){
		   $('#worklist').datagrid('rejectChanges');
		   editIndex = undefined;
		  }
		  function getChanges(){
		   var rows = $('#worklist').datagrid('getChanges');
		   alert(rows.length+' rows are changed!');
		  }
		 
		  function contains(arr, obj) {
		   var i = arr.length;
		   while (i--) {
		    if (arr[i] === obj) {
		     return true;
		    }
		   }
		   return false;
		  }
		  
		//指定列求和
		    function compute(colName) {
		      var rows = $('#worklist').datagrid('getRows');
		      var total = 0;
		      for (var i = 0; i < rows.length-1; i++) {//最后一行合计不计算
		        total += parseFloat(rows[i][colName]);
		      }
		      return total;
		    }
		   </script>
  	
  </body>
</html>
