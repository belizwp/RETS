<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rets" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp" />

<div class="container">
    <div class="panel panel-default">
        <div class="panel-body">
            <div class="row">
                <div class="col-md-10">
                    <jsp:include page="searchbox"/>
                </div>
                <div class="col-md-2">
                    <a href="/RETS/NewAnnounce" type="button" class="btn btn-danger">ลงประกาศกับเรา</a>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-9">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h4>ผลการค้นหา ${requestScope.found_rows} ประกาศ</h4>
                    <!-- search result -->

                    <div id="result">
                        <div class="row">
                            <div class="list-group list-special result">

                                <c:forEach var="res" items="${requestScope.result}">
                                    <a href="/RETS/residential?id=${res.id}" class="list-group-item row-fluid">
                                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 nopadding">
                                            <figure class="pull-left">
                                                <img class="img-responsive" src="/RETS/image?type=thumbnail&id=${res.id}">
                                            </figure>
                                        </div>
                                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 ">
                                            <div class="caption">
                                                <h4>${res.name}</h4>
                                                <p>${res.detail}</p>
                                            </div>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 ">
                                            <h4 class="pull-right" >
                                                <fmt:formatNumber value="${res.price}"/>
                                                <small> บาท </small>
                                            </h4>
                                        </div>
                                    </a>
                                </c:forEach>

                            </div> <!-- /result list-->
                        </div> <!-- /row-->
                    </div>

                    <div id="page-selection" class="text-center"></div>

                </div> <!-- /panel-body-->
            </div> <!-- /panel-->
        </div>
        <div class="col-md-3">
            <!-- advertise -->
            <rets:ads type="sidebar" />
        </div>
    </div>
</div>

<%
    String query = request.getQueryString();
    try {
        query = query.substring(0, query.lastIndexOf("page") - 1);

    } catch (Exception e) {
    }
%>

<script src="/RETS/assets/js/jquery.bootpag.min.js"></script>
<script>
    $('#page-selection').bootpag({
        total: Math.ceil('${requestScope.found_rows}' / '${requestScope.limit}'), // total pages
        page: '${requestScope.page}',
        maxVisible: 10
    }).on("page", function (event, num) {
        window.location = "/RETS/search?" + '<%= query%>' + '&page=' + num;
    });
</script>

<jsp:include page="templates/footer.jsp" />
