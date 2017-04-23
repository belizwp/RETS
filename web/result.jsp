<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="list-group list-special result">

        <sql:query var="resultSet" dataSource="${dataSource}">
            SELECT Res_id, Res_name FROM residential ORDER BY dt_modified DESC LIMIT ${param.page - 1}, 10;
        </sql:query>
        <c:forEach var="res" items="${resultSet.rows}">
            <sql:query var="details" dataSource="${dataSource}">
                SELECT remark, price FROM details WHERE Res_id = ${res.Res_id};
            </sql:query>
            <c:forEach var="detail" items="${details.rows}">
                <a href="/RETS/residential?id=${res.Res_id}" class="list-group-item row-fluid">
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 nopadding">
                        <figure class="pull-left">
                            <img class="img-responsive" src="/RETS/image?type=thumbnail&id=${res.Res_id}">
                        </figure>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 ">
                        <div class="caption">
                            <h4>${res.Res_name}</h4>
                            <p>${detail.remark}</p>
                        </div>
                    </div>
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 ">
                        <h4 class="pull-right" >
                            <fmt:formatNumber value="${detail.price}"/>
                            <small> บาท </small>
                        </h4>
                    </div>
                </a>
            </c:forEach>
        </c:forEach>

    </div> <!-- /result list-->
</div> <!-- /row-->