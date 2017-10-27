
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="com.alibaba.fastjson.JSON"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <html>
    <head>
        <meta charset="UTF-8">
        <title><c:out value="${titleInfo}"/></title>
        
        <%@include file="/WEB-INF/jsp/pub/commonJsCss.jsp" %>
        <script type="text/javascript" src="<spring:url value="/js/jsp/sys/menu.js"/>"></script>
        
    </head>
    <body style="margin: 0px;">
        <div id="tabs-box" class="easyui-tabs" data-options="iconCls:'icon-help',closable:true" style="width:100%;height:520px;overflow-y:hidden">
            <div title="About" style="padding:10px">
                <p style="font-size:14px">jQuery EasyUI framework helps you build your web pages easily.</p>
                <ul>
                    <li>easyui is a collection of user-interface plugin based on jQuery.</li>
                    <li>easyui provides essential functionality for building modem, interactive, javascript applications.</li>
                </ul>
            </div>
        </div>
    </body>
    </html>