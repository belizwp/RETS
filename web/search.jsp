<%@ taglib prefix="rets" tagdir="/WEB-INF/tags/"%>
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
            <!-- search result -->
            <jsp:include page="result.jsp"/>
        </div>
        <div class="col-md-3">
            <!-- advertise -->
            <rets:ads type="sidebar" />
        </div>
    </div>
</div>

<jsp:include page="templates/footer.jsp" />
