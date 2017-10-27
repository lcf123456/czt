<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 调拨出库界面 -->
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/perform/userperformlevel.js"/>"></script>
  <head>
  <script type="text/javascript">
  var outboundtype = "${outboundtype}";////出库类型(10订单出库 20 调拨出库)
  var username = "${userVo.username}";
  var deptname = "${userVo.deptname}";
  var deptid = "${userVo.deptid}";
  var obid=0;//出库单主表id
</script>
  </head>
  
  <body>
	
	<!-- datagrid页面列表数据 -->
	<div style="padding:10" id="tabdiv">
		<table id="dataTabel"></table>
	</div>
	
	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;border-top:1px solid #95B8E7">
	<form id="queryForm" style="margin:10;">
	<input type="hidden" name="datetype" id="datetype" value="10"></input>
		<div style="margin-bottom:5px">
		<a href="#" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openCreate()">新增</a>
		<a href="#" id="delBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleterow()">删除</a>
		
		时间选择：<input name="checkdate_new" id="checkdate_new"  style="width:120px" >&nbsp;
		
			<a href="#" onclick="searchData();" class="easyui-linkbutton" iconCls="icon-search" style="height:24px;">查询</a>
		</div>
		</form>
	</div>
	
    <!-- 1、新增--------------------------------------------------------------------------------------->
	<div id="add-dlg" class="easyui-dialog" style="width:960px;height:420px;padding:5px 10px;align:center;"
			 closed="true"  data-options="modal:true,draggable:false">
		<form  id="add-fm" method="post" action="" novalidate  >
			<div >
			<tr>
			<td>
			<table width="100%" class="gridtable">
			<tr>
		  		 	<td width="5%" height="20" align="left" nowrap>记录人：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="username_new" id="username_new" class="easyui-textbox"  style="width:120px" readonly >
		           	</td>
		            <td width="5%" height="20" align="left" nowrap>部门：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="deptid_new" id="deptid_new" class="easyui-textbox" style="width:120px" readonly>
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>考核月份：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="scoremonth_new" id="scoremonth_new"  style="width:160px" />
		           </td>
	         </tr>
			</table>
			</td>
			</tr>
			</div>
			 <div style="WIDTH: 100%; HEIGHT: 380; BACKGROUND-COLOR: transparent; scrollbar-face-color:#c1d9f5; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff">
	<table id="worklist" class="easyui-datagrid" title="人员列表" "
            data-options="
                iconCls: 'icon-edit',
                singleSelect: true,
                toolbar: '#dlg-buttons',
                url:baseURL+'/perform/userperformlevel/getAddUserlevelList.json', 
                method: 'get',
                height:380,
                onClickCell: onClickCell,
                onLoadSuccess:function(data){  
                //判断重点工作及日常工作列表是否有记录，没有记录则需要加上缺少部分的分值
                }
            ">
        <thead>
            <tr>
            <th data-options="field:'userid',width:$(this).width()*0.1,align:'right'">ID</th>
                <th data-options="field:'username',width:$(this).width()*0.1,align:'right'">人员</th>
                <th data-options="field:'levelname',width:$(this).width()*0.2,align:'right',
                 editor:{  
                         type: 'combobox',  
                         options: {  
                            data: [{'value':'A','text':'A级'},{'value':'B','text':'B级'},{'value':'C','text':'C级'},{'value':'D','text':'D级'}],  
                            valueField: 'value',  
                            textField: 'text',  
                            editable: false,  
                            panelHeight:70,  
                            required: true,
                            onLoadSuccess: function () { //加载完成后,设置选中第一项
                            var row = $('#worklist').datagrid('getSelected');  
                            var lvname=row.levelname;
                            var choosebz = 0;
					    	var val = $(this).combobox('getData');
							for (var i = 0; i < val.length; i++){
						            	for (var item in val[i]) {
						            		if (item == 'value') {
							                	if(lvname==val[i][item]){
							                		choosebz=1;
							 	                    $(this).combobox('select', lvname);
							                	}
							                }
						            	}
						      }
						      if(choosebz==0){//前面没有选中,则默认选中第一个
			            	for (var item in val[0]) {
				                if (item == 'value') {
				                   lvname=val[0][item];
				                    $(this).combobox('select',lvname);
				                }
				            }
	            			} 
                        }
                        }  
                     }">级别</th>
                <th data-options="field:'score',width:$(this).width()*0.1,editor:{type:'numberbox',options:{max:100,min:0,required: true}}">分值</th>
                <th data-options="field:'remarks',width:$(this).width()*0.5,editor:'textbox'">备注</th>
            </tr>
        </thead>
    </table>
    </div>
    		<input type="hidden" name="allrows" id="allrows" class="easyui-textbox"></input> <!--保存所有行的数据 -->
    		<input type="hidden" name="updaterows" id="updaterows" class="easyui-textbox"></input> <!--保存修改行的数据 -->
		</form>
		
	 <div id="dlg-buttons">
	    <a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-ok" onclick="tosave()">保存</a>
	    <a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-back" onclick="reject()">撤销</a>
	</div>
	</div>
	
	    <!-- 1、查看--------------------------------------------------------------------------------------->
	<div id="view-dlg" class="easyui-dialog" style="width:800px;height:320px;padding:5px 10px;align:center;"
			 closed="true" buttons="#view-buttons"  data-options="modal:true,draggable:false">
		<form  id="view-fm" method="post" action="" novalidate  >
			<div >
			<tr>
			<td>
			<table>
			<tr>
		  		 	<td width="5%" height="20" align="left" nowrap>工作时间：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="workdate" id="workdate" style="width:120px"  >
		           	</td>
		           	<td width="5%"  height="20" align="left" nowrap>计划调整部门：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="deptname" id="deptname" style="width:120px" />
		           </td>  
		            <td width="5%" height="20" align="left" nowrap>权重：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="weight" id="weight" class="easyui-numberbox" style="width:120px" >
		           	</td>
	         </tr>
	          <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>工作内容：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="5">
		                <input name="content" id="content" class="easyui-textbox"  style="width:630px"  >
		           	</td>
	         </tr>
	         <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>完成期限：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="5">
		                <input name="finishdate" id="finishdate" class="easyui-textbox"  style="width:630px"  >
		           	</td>
	         </tr>
	         <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>过程进度：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="5">
		                <input name="process" id="process" class="easyui-textbox"  style="width:630px"  >
		           	</td>
	         </tr>
	         <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>要求及目标：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="5">
		                <input name="target" id="target" class="easyui-textbox"  style="width:630px"  >
		           	</td>
	         </tr>
	         <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>实施人：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="5">
		                <input name="partake" id="partake" class="easyui-textbox" style="width:630px"  >
		           	</td>
	         </tr>
			</table>
			</td>
			</tr>
			</div>
		</form>
	 <div id="view-buttons">
		<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-cancel" onclick="javascript:$('#view-dlg').dialog('close')">关闭</a>
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
		  function onEndEdit(index, row){
//		   var ed = $(this).datagrid('getEditor', {
//		    index: index,
//		    field: 'id'
//		   });
//		   row.selfscore = $(ed.target).combobox('getText');
		  }
		  function accept(){
			var entities = "";
			var effectRow = new Object();
		   if (endEditing()){
		    var $dg = $('#worklist');
		    var uprows = $dg.datagrid('getChanges');
		    if (uprows.length) {
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
		   
		   $('#updaterows').textbox("setValue",entities);//修改的行记录
		   $('#updaterows').textbox("setText",entities);
		  }
		  function tosave(){
			  accept();//修改行的记录传到后台，因为所有行的记录时未做修改的情况
			  var rows = $('#worklist').datagrid('getRows');
			    var entities = "";
			    for(i = 0;i < rows.length;i++){
			    	 entities = entities  + JSON.stringify(rows[i]);  
			    }
			    entities = "["+entities+"]";
			    $('#allrows').textbox("setValue",entities);//所有行记录
				   $('#allrows').textbox("setText",entities);
					 $('#add-fm').form('submit',{
							onSubmit: function(){
								var isValidate = $(this).form('validate');
								if(isValidate){
									//$('#loading').window('open');
								}
								return isValidate;
							},
							url:baseURL+"/perform/userperformlevel/doSave.json",
							//data: {'},
							beforeSend : function () {
								$.messager.progress({
									text : '正在新增中...',
								});
							},
							success: function(data){
								//$('#loading').window('close');
								data = eval('('+data+')');
								
								$('#add-dlg').dialog('close');
								$('#dataTabel').datagrid('reload'); 
					    		$.messager.show({
									title : '提示',
									msg :  data.total+'个考核'+data.msg+'！',
								});
								//$('#loading').window('close');
							}
						});
			    	
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
		      var total = tmpscore;
		      for (var i = 0; i < rows.length-1; i++) {//最后一行合计不计算
		    	  if(!isNaN(parseFloat(rows[i][colName]))){
		        		total += parseFloat(rows[i][colName]);
		    	  }
		      }
		      return total;
		    }
		   </script>
  </body>
</html>
