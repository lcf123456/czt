<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>
<!-- 卷烟库存（各地市库存账面量） -->
<html>
<title><c:out value="${titleInfo}"/></title>
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/wms/itemstock.js"/>"></script>
  <head>
  </head>

  <body>
	
	<!-- 查询-->
	<div style="height:auto;border:1px solid #95B8E7" >
	<div id="toolbar" style="padding:5px;height:auto;background-color: #F4F4F4;">
	<form id="searchForm" style="margin:10;">
		<div style="font-size:12px;" >
		<tr align="center">
            <td width="5%"  height="15" align="left"  nowrap >
                               地市：<input name="consignsorsearch" id="consignsorsearch" class="easyui-textbox" style="width:120px" >
			<input class="easyui-textbox"  id=excelBtn name="toexcel" id="toexcel"  data-options="buttonText:'导出excel',buttonIcon:'icon-myto_excel',onClickButton:function(){exportxls();}," style="width:80px;height:24px;">
		</td>
		</tr>
		</div>
		</form>
	</div>
  <div id="main" style="WIDTH: 100%; HEIGHT: 440px; BACKGROUND-COLOR: transparent; OVERFLOW: scroll; scrollbar-face-color:#c1d9f5; scrollbar-arrow-color:#ffffff; scrollbar-highlight-color:#ffffff; scrollbar-3dlight-color:#70807d; scrollbar-shadow-color:#ffffff; scrollbar-darkshadow-color:#70807d; scrollbar-track-color:#ffffff">
  <c:import url="v_itemstockList.jsp"></c:import>
  </div>
  </div>

  </body>
</html>