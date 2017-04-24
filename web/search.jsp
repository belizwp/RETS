<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="rets" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp" />

<c:set var="p_id" value="${param.province != '' && param.province != null && param.province != 0 ? param.province : 'select province_id from province'}"/>
<c:set var="a_id" value="${param.amphur != '' && param.amphur != null ? param.amphur : 'select amphur_id from amphur'}"/>
<c:set var="d_id" value="${param.district != '' && param.district != null ? param.district : 'select district_id from district'}"/>
<c:set var="ann" value="${param.announce_type != '' && param.announce_type != 'ทั้งหมด' && param.announce_type != null ? '\"' += param.announce_type += '\"' : '\"ขาย\", \"เช่า\"'}"/>
<c:set var="type" value="${param.property_type != '' && param.property_type != 'ทั้งหมด' && param.property_type != null ? '\"' += param.property_type += '\"' : '\"บ้านเดี่ยว\", \"คอนโด\", \"ทาวน์เฮ้าส์\", \"ที่ดิน\", \"อพาร์ทเม้น\"'}"/>
<c:set var="min" value="${param.min_price != '' && param.min_price != null ? param.min_price : -2147483648}"/>
<c:set var="max" value="${param.max_price != '' && param.max_price != null ? param.max_price : 2147483648}"/>

<sql:query var="num_rows" dataSource="${dataSource}">
    SELECT province_id, amphur_id, district_id, announce_for, types, price
    FROM residential
    natural join details
    natural join location
    where
    (province_id in (${p_id}) or province_id is null or province_id = @province_id)
    and
    (amphur_id in (${a_id}) or amphur_id is null or amphur_id = @amphur_id)
    and
    (district_id in (${d_id}) or district_id is null or district_id = @district_id)
    and
    (announce_for in (${ann}) or announce_for is null or announce_for = @announce_for)
    and
    (types in (${type}) or types is null or types = @types)
    and
    (price between ${min} and ${max});
</sql:query>

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
                    <h4>ผลการค้นหา ${num_rows.getRowCount()} ประกาศ</h4>
                    <!-- search result -->

                    <div id="result">
                        <jsp:include page='<%= "result?" + request.getQueryString() + "&page=1"%>' />
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
        total: Math.ceil('${num_rows.getRowCount()}' / 10), // total pages
        page: 1,
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
