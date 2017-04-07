<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp" />

<div class="jumbotron">
    <div class="container text-center">
        <h1>Real Estate Trading System</h1>
        <p>(RETS)</p>
        <jsp:include page="searchbox/searchbox.jsp"/>
    </div>
</div>

<div class="container">
    <h4><b>ประชาสัมพันธ์</b></h4>
    <div class="row">
        <jsp:include page="advertise.jsp"/>
    </div>
</div>

<jsp:include page="templates/footer.jsp" />

