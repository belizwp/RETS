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
                    <a type="button" class="btn btn-danger pull-right">ลงประกาศกับเรา</a>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-9">
            <jsp:include page="result.jsp"/>
        </div>
        <div class="col-md-3">
            <!-- advertise -->
        </div>
    </div>
</div>

<jsp:include page="templates/footer.jsp" />
