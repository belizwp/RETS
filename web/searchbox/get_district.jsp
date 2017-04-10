<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${param.amphur_id != null}">
    <sql:query var="district_rows" dataSource="${dataSource}">
        SELECT * FROM district WHERE amphur_id = ?
        <sql:param value="${param.amphur_id}"/>
    </sql:query>

    <option value='0'>ทั้งหมด</option>

    <c:forEach var="district_row" items="${district_rows.rows}">
        <c:choose>
            <c:when test="${district_row.district_id == param.district}">
                <option selected value="${district_row.district_id}" >${district_row.district_name}</option>
            </c:when>
            <c:otherwise>
                <option value="${district_row.district_id}" >${district_row.district_name}</option>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</c:if>