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
            <div class="panel panel-default">
                <div class="panel-body">
                    <h4>ผลการค้นหา 12,345 ประกาศ</h4>
                    <!-- search result -->

                    <div id="result">
                        <jsp:include page='<%= "result?" + request.getQueryString() + "&page=1" %>' />
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

<script src="/RETS/assets/js/jquery.bootpag.min.js"></script>
<script>
    $('#page-selection').bootpag({
        total: 5, // total pages
        page : 1,
        maxVisible: 10
    }).on("page", function (event, num) {
        $.ajax({
            type: "POST",
            url: "/RETS/result?" + '<%= request.getQueryString()%>' + '&page=' + num,
            success: function (data) {
                $("#result").html(data);
            }
        });
    });
</script>

<jsp:include page="templates/footer.jsp" />
