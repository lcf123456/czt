
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.alibaba.fastjson.JSON"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %><!doctype html>
   
    <%
    String roleid = request.getParameter("roleid"); 
    System.out.println("roleid= "+roleid);
    %>
    <html>
    <head>
        <meta charset="UTF-8">
        <title><c:out value="${title}"/></title>
        
<%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
<script type="text/javascript" src="<spring:url value="/js/jsp/sys/rolemenu.js"/>"></script>

<script type="text/javascript">
  var roleid=<%=roleid%>;
</script>
    </head>
    <body>
   <div id="win" class="easyui-window" title="My Window" style="width:600px;height:400px"   
        data-options="iconCls:'icon-save',modal:true">   
   
        <div style="margin:20px 0 10px 0;"></div>
        <div id="tabs-box" class="easyui-tabs" data-options="iconCls:'icon-help',closable:true" style="width:100%;height:490px">
            <div title="About" style="padding:10px">
                <p style="font-size:14px">jQuery EasyUI framework helps you build your web pages easily.</p>
                <ul>
                    <li>easyui is a collection of user-interface plugin based on jQuery.</li>
                    <li>easyui provides essential functionality for building modem, interactive, javascript applications.</li>
                </ul>
            </div>
        </div>
    </div>
    </body>
    </html>