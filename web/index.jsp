<%@ taglib prefix="rets" tagdir="/WEB-INF/tags/"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="templates/header.jsp" />

<div class="jumbotron">
    <div class="container text-center">
        <h1>Real Estate Trading System</h1>
        <p>(RETS)</p>
        <jsp:include page="searchbox"/>
    </div>
</div>

<div class="container">
    <rets:ads type="main" />
    <div class="text-center">
        <a href="search" type="button" class="btn btn-default">ดูโครงการทั้งหมด</a>
        <a href="CreateAnnounce" type="button" class="btn btn-danger">ลงประกาศกับเรา</a>
    </div>
</div>

<jsp:include page="templates/footer.jsp" />
