<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<c:if test="${param.province_id != null}">
    <sql:query var="amphur_rows" dataSource="${applicationScope.dataSource}"> select * from amphur where province_id = ${param.province_id}</sql:query>
        <option value='0'>ทั้งหมด</option>

        <c:forEach var="amphur_row" items="${amphur_rows.rows}">
        <option value="${amphur_row.amphur_id}" >${amphur_row.amphur_name}</option>
    </c:forEach>
</c:if>