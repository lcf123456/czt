<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
<!-- 卷烟库存（各地市库存账面量） -->
  <table class="gridtable" width="100%" id="tabexport">
        <thead>
            <tr>
            <th>卷烟名称</th>
            <th>卷烟编码</th>
            <th>入库量(件)</th>
            <th>出库量(件)</th>
            <th>库存量(件)</th>
            <th>入库量(条)</th>
            <th>出库量(条)</th>
            <th>库存量(条)</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${itemStockList}" var="itemstocklist">
        <tr>
        <td>${itemstocklist.getCigarettename()}&nbsp;</td>
        <td>${itemstocklist.getCigarettecode()}&nbsp;</td>
        <td>${itemstocklist.getInboxqty()}&nbsp;</td>
        <td>${itemstocklist.getOutboxqty()}&nbsp;</td>
        <td>${itemstocklist.getSumboxqty()}&nbsp;</td>
        <td>${itemstocklist.getInitemqty()}&nbsp;</td>
        <td>${itemstocklist.getOutitemqty()}&nbsp;</td>
        <td>${itemstocklist.getSumitemqty()}&nbsp;</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>

