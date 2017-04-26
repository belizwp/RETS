<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="type"%>

<%-- any content can be specified here e.g.: --%>
<sql:query var="ads_rows" dataSource="${dataSource}">
    SELECT Ads_id, topic, detail, Res_id FROM advertised WHERE role_ads = true ORDER BY present_date DESC LIMIT 4;
</sql:query>

<c:if test="${ads_rows.getRowCount() > 0}">
    <h4><b>ประชาสัมพันธ์</b></h4>
</c:if>

<div class="row">
    <c:choose>
        <c:when test="${type == 'main'}">
            <c:forEach var="ads" items="${ads_rows.rows}" end="4">
                <div class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                    <a href="/RETS/residential?id=${ads.Res_id}" class="thumbnail">
                        <img src="/RETS/image?id=${ads.Ads_id}&type=ads">
                        <div class="caption">
                            <h4>${ads.topic}</h4>
                            <p>${ads.detail}</p>
                        </div>
                    </a>
                </div>

            </c:forEach>
        </c:when>
        <c:when test="${type == 'sidebar'}">
            <c:forEach var="ads" items="${ads_rows.rows}" end="1">

                <div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
                    <a href="/RETS/residential?id=${ads.Res_id}" class="thumbnail">
                        <img src="/RETS/image?id=${ads.Ads_id}&type=ads">
                        <div class="caption">
                            <h4>${ads.topic}</h4>
                            <p>${ads.detail}</p>
                        </div>
                    </a>
                </div>

            </c:forEach>
        </c:when>
    </c:choose>

</div>