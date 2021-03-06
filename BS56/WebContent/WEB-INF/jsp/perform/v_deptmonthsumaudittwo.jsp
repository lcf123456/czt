<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<!-- 调拨出库界面 -->
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/perform/deptmonthsumaudittwo.js"/>"></script>
  <head>
  <script type="text/javascript">
  var deptid = "${userVo.deptid}";////出库类型(10订单出库 20 调拨出库)
  var username = "${userVo.username}";
  var deptname = "${userVo.deptname}";
  var scoremonth = getDateYMD();
  var obid="${obid}";//主表id,用于标识是否为第一次提交 0为第一次，否则是修改
  var tmpscore=0;//临时变量，判断重点工作及日常工作列表是否有记录，没有记录则需要加上缺少部分的分值  
</script>
  </head>
  
  <body>
	
	<div id="add-dlg" >
		
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
		           	<td width="5%"  height="20" align="left" nowrap>考核日期：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		               <input name="scoremonth_new" id="scoremonth_new" class="easyui-textbox"   style="width:160px" readonly/>
		           </td>
	         </tr>
	         <tr>
		  		 	<td width="5%" height="20" align="left" nowrap>需注意工作事项：</td>
		           	<td width="14%" height="20" align="left" nowrap colspan="3">
		                <input name="matters_new" id="matters_new" class="easyui-textbox"  style="width:430px"  readonly>
		           	</td>
		  		 	<td width="5%" height="20" align="left" nowrap>未完成事项说明：</td>
		           	<td width="14%" height="20" align="left" nowrap >
		                <input name="unfinished_new" id="unfinished_new" class="easyui-textbox"  style="width:230px"  readonly>
		           	</td>
	         </tr>
			</table>
			</td>
			</tr>
			</div>
			 <div style="WIDTH: 100%; HEIGHT:320; BACKGROUND-COLOR: transparent;scrollbar-face-color:#c1d9f5; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff">
	<table id="worklist" class="easyui-datagrid" title="考核列表" style="width:100%"
            data-options="
                iconCls: 'icon-edit',
                singleSelect: true,
                url:baseURL+'/perform/deptmonthsum/getDeptmonthListByid.json?obid='+obid, 
                method: 'get',
                height:320,
                //onClickCell: onClickCell,
                onLoadSuccess:function(data){  
                //判断重点工作及日常工作列表是否有记录，没有记录则需要加上缺少部分的分值
                if(data.total != 0) {
	               $('#worklist').datagrid('selectRow',0);// 关键在这里  
	               var row = $('#worklist').datagrid('getSelected'); 
	                 tmpscore = row.tmpscore;
               }
		            $('#worklist').datagrid('appendRow',  
                {  
                	id:0,
                    typename:'合计',  
                    content:' ',
                    scorevalue:' ',
                    process:' ',
                    finishnote:' ',
                    selfscore:100,
                    otherscore:100,
                    note:''
            //合并单元格  
                });  
                var hj  = compute('selfscore');
		        $('#worklist').datagrid('updateRow',{index:$('#worklist').datagrid('getRows').length-1,row:{selfscore:hj}});
                var hj  = compute('otherscore');
		        $('#worklist').datagrid('updateRow',{index:$('#worklist').datagrid('getRows').length-1,row:{otherscore:hj}});
		        }
            ">
        <thead>
            <tr>
                <th data-options="field:'typename',width:$(this).width()*0.1">类别</th>
                <th data-options="field:'content',width:$(this).width()*0.2">考核项目</th>
                <th data-options="field:'scorevalue',width:$(this).width()*0.05,">分值</th>
                <th data-options="field:'process',width:$(this).width()*0.2">考核标准</th>
                <th data-options="field:'finishnote',width:$(this).width()*0.2">完成情况</th>
                <th data-options="field:'selfscore',width:$(this).width()*0.05">自评分</th>
                <th data-options="field:'otherscore',width:$(this).width()*0.05">部长评分</th>
                <th data-options="field:'note',width:$(this).width()*0.1">加扣分说明</th>
            </tr>
        </thead>
    </table>
    </div>
		
	
	<div >
	<form  id="add-fm" method="post" action="" novalidate  >
			<tr>
			<td>
			<table width="100%" class="gridtable">
			<tr>
		  		 	<td width="5%" height="20" align="left" nowrap>等级评定：</td>
		           	<td width="14%" height="20" align="left" nowrap>
		                <input name="genmngrating" id="genmngrating" style="width:120px"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" id="btn-done" iconCls="icon-ok" onclick="tosave()">保存</a>
					</td>
	         </tr>
			</table>
			</td>
			</tr>
			<input type="hidden" name="obid" id="obid" class="easyui-textbox"/>
			</form>
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
		   // alert(  compute("otherscore"));
		    var hj  = compute("otherscore");//$('#worklist').datagrid('getRows')
		   // $('#worklist').datagrid('updateRow',{index:$('#worklist').datagrid('getRowIndex','0'),row:{selfscore:hj}})
		    $('#worklist').datagrid('updateRow',{index:$('#worklist').datagrid('getRows').length-1,row:{otherscore:hj}});
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
		   
		   //entities = '{"updated":'+ entities+'}';
		   console.log("updated-5--"+entities);
		   
		   $('#updaterows').textbox("setValue",entities);//修改的行记录
		   $('#updaterows').textbox("setText",entities);
		   /* var hj  = compute("selfscore");//$('#worklist').datagrid('getRows')
		    $('#scoresum').textbox("setValue",hj);//修改的后的合计
			$('#scoresum').textbox("setText",hj);
			 $('#obid').textbox("setValue",obid);//主表id,用于标识是否为第一次提交 0为第一次，否则是修改
				$('#obid').textbox("setText",obid);
			
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
			}); */
		  }
		  function tosave(){
			  
					$('#obid').textbox("setValue",obid);//主表id,用于标识是否为第一次提交 0为第一次，否则是修改
					$('#obid').textbox("setText",obid);
					 $('#add-fm').form('submit',{
							onSubmit: function(){
								var isValidate = $(this).form('validate');
								if(isValidate){
									//$('#loading').window('open');
								}
								return isValidate;
							},
							url:baseURL+"/perform/deptmonthsum/doAuditTwo.json?obid="+obid,
							//data: {'},
							beforeSend : function () {
								$.messager.progress({
									text : '正在新增中...',
								});
							},
							success: function(data){
								//$('#loading').window('close');
								data = eval('('+data+')');
								
								//obid =  data.obid;
								//提交完成后，刷新左页面
								//window.parent.refreshTab("绩效考核");
						                //  window.parent.tabCloseForChild("绩效修改");
					    		$.messager.show({
									title : '提示',
									msg :  '考核总评'+data.msg+'！',
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
