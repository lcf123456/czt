<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>

<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/produce/sorttrough.js"/>"></script>
  </head>
  <style>
  .datagrid-row {
  height: 35px;
}

  </style>
  <body>
  <form action="">
	<input type="hidden" id="troughtype" name="troughtype" value="${type}" />
	<input type="hidden" id="ctype" name="ctype" value="${ctype}" />
	<input type="hidden" id="groupno" name="groupno" value="${groupno}" />
	<!-- datagrid页面列表数据 -->
	<div style="padding:10" id="tabdiv">
		<table class="easyui-datagrid"style="width:600px;height:420px"
    data-options="fitColumns:true,singleSelect:true">
	    <thead>
			<tr>
				<th data-options="field:'sortid'">序号</th>
				<th data-options="field:'sortname'">区域</th>
				<th data-options="field:'handle'">操作</th>
			</tr>
	    </thead>
	    <tbody>
			<tr>
				<td>001</td><td>烟柜</td><td>
					<a href="/BS56/produce/sorttrough/detail/10/20/0" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" >录入</a>
				</td>
			</tr>
			</tr>
			<tr>
				<td>002</td><td>重力式货架</td>
				<td>
					<a href="/BS56/produce/sorttrough/detail/20/20/0" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" >录入</a>
				</td>
			</tr>
			<tr>
				<td>003</td><td>异型烟一线</td><td>
					<a href="/BS56/produce/sorttrough/detail/10/30/1" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" >录入</a>
				</td>
			</tr>
			</tr>
			<tr>
				<td>004</td><td>异型烟一线混合道</td><td>
					<a href="/BS56/produce/sorttrough/detail/10/40/1" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" >录入</a>
				</td>
			</tr>
			</tr>
			<tr>
				<td>005</td><td>异型烟二线</td><td>
					<a href="/BS56/produce/sorttrough/detail/10/30/2" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" >录入</a>
				</td>
			</tr>
			</tr>
			<tr>
				<td>006</td><td>异型烟二线混合道</td><td>
					<a href="/BS56/produce/sorttrough/detail/10/40/2" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" >录入</a>
				</td>
			</tr>
			</tr>
			<tr>
				<td>007</td><td>异型烟公共道</td><td>
					<a href="/BS56/produce/sorttrough/detail/10/30/3" id="newBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true" >录入</a>
				</td>
			</tr>
			</tr>
		</tbody>
	</table>
	</div>
   	</form>	  
  </body>
</html>
