<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/include/taglib.jsp" %>
    <nav>
        <ul class="pagination">
            <c:set var="previousPage" value="${pagination.previousPage}" ></c:set>
            <c:choose>
            <c:when test="${empty previousPage }">
              <li class="disabled"><span aria-hidden="true">&laquo;上一页</span></li>
            </c:when>
            <c:otherwise>
              <li><a class="ajax-link" href="<c:out value="${previousPage.url}" />" aria-label="Previous"> <span aria-hidden="true">&laquo;上一页</span></a></li>
            </c:otherwise>
            </c:choose>
            
            <c:forEach items="${pagination.visualPages }"  var="page" >
              <c:choose>
              <c:when test="${empty page }">
                <li><span>...</span></li>
              </c:when>
              <c:otherwise>
              <li <c:if test="${page.pageNum==pagination.pageNum }"> class="active" </c:if> ><a class="ajax-link" href="<c:out value="${page.url}" />" aria-label="Previous"> <span aria-hidden="true">${page.pageNum }</span></a></li>
              </c:otherwise>
              </c:choose>
            </c:forEach>

            <c:set var="nextPage" value="${pagination.nextPage}" ></c:set>
            <c:choose>
            <c:when test="${empty nextPage }">
              <li class="disabled"><span aria-hidden="true">下一页&raquo;</span></li>
            </c:when>
            <c:otherwise>
              <li><a class="ajax-link" href="<c:out value="${nextPage.url}" />" aria-label="Next"> <span aria-hidden="true">下一页&raquo</span></a></li>
            </c:otherwise>
            </c:choose>
            
            <li>
            <div class="pull-left am-padding-left">
            <input class="form-control" data-total-page="${pagination.totalPage}" style="display: inline-block;width:auto;" size="1" type="text" value="${pagination.pageNum }">/${pagination.totalPage }页
            <a class="btn btn-default pagination-btn"  href="<c:out value="${pagination.urlWithoutPageNum }"/>">Go</a>
            </div>
            </li>            
        </ul>
    </nav>