<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
  <table class="gridtable" width="100%" id="tabexport">
        <thead>
            <tr>
            <c:forEach items="${basetypeInfoVoList}" var="base">
            <th>${base}&nbsp;</th>
             </c:forEach>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${complaintList}" var="complaint">
        <c:set value="${ fn:split(complaint.getCtstr(), ';') }" var="ctSplit" />
        <tr>
        <td>${complaint.getDeptname()}&nbsp;</td>
	        <c:forEach items="${ctSplit}" var="ct">
	        <td>${ct} &nbsp;</td>
	         </c:forEach>
        </tr>
         </c:forEach>
        </tbody>
    </table>

