<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
    <table class="table table-bordered table-hover">
        <thead>
            <tr>
                <th>ID</th>
                <th>买家</th>
                <th>买家电话</th>
                <th>订单名称</th>
                <th>价格(分)</th>
                <th>订单时间</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${pagination.records}" var="record">
          <tr>
          <th scope="row">${record.orderId }</th>
          <td><c:out value="${record.buyerName }" /></td>
          <td><c:out value="${record.buyerMobile }" /></td>
          <td><c:out value="${record.orderName }" /></td>
          <td><c:out value="${record.orderPrice }" /></td>
          <td><fmt:formatDate value="${record.orderTime }" pattern="yyyy-MM-dd HH:mm"/></td>
          </tr>        
        </c:forEach>
        </tbody>
    </table>
    <c:import url="/WEB-INF/jsp/include/pagination.jsp"></c:import>

