<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>

<html>
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>

<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/produce/sorttrough.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/datagrid-cellediting.js"/>"></script>
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
		<table id="dataTabel"></table>
	</div>
	<!-- 查询-->
	<div id="toolbar" style="padding:5px;height:auto;border-top:1px solid #95B8E7">
		<div style="margin-bottom:5px">
		<input class="easyui-datebox" style="width:140px" id="orderdate" name="orderdate">
		<a href="/BS56/produce/sorttrough/toMantissa" class="easyui-linkbutton" id="btn-done" iconCls="icon-cancel" onclick="">返回</a>
		</div>
	</div>
<script type="text/javascript">
var ty=document.getElementById("troughtype").value;
var cty=document.getElementById("ctype").value;
var groupno=document.getElementById("groupno").value;
var title="";
function getTitle()
{
	if(ty==20)
		{
		title="重力式货架尾数维护";
		
		}
	else if(ty==10)
		{
		if(cty==20)
			{
			title="烟柜尾数维护";
			}
		if(cty==30 && groupno==1)
		{
		title="异型烟一线尾数维护";
		}
		if(cty==30 && groupno==2)
		{
		title="异型烟二线尾数维护";
		}
		if(cty==30 && groupno==3)
		{
		title="异型烟共用道尾数维护";
		}
		if(cty==40 && groupno==1)
		{
		title="异型烟一线混合道尾数维护";
		}
		if(cty==40 && groupno==2)
		{
		title="异型烟二线混合道尾数维护";
		}
		}
	}
getTitle();
</script>
   	</form>	  
  </body>
</html>
