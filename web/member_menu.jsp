<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header" />

<link href="/RETS/assets/css/dataTables.bootstrap.min.css" rel="stylesheet">

<style>
    .panel.with-nav-tabs .panel-heading{
        padding: 5px 5px 0 5px;
    }
    .panel.with-nav-tabs .nav-tabs{
        border-bottom: none;
    }
    .panel.with-nav-tabs .nav-justified{
        margin-bottom: -1px;
    }
</style>

<div class="container">
    <div class="panel panel-default"> 
        <div class="panel-body">
            <h4><small>สวัสดีคุณ</small> ${sessionScope.employee.fname} ${sessionScope.employee.lname}&nbsp;
                <span><small><a href="/RETS/edit_profile"> แก้ไขข้อมูลสมาชิก </a> | <a href="/RETS/Logout"> ออกจากระบบ </a></small></span>
            </h4>
        </div>
    </div>

    <div class="panel with-nav-tabs panel-default">
        <div class="panel-heading">
            <ul class="nav nav-tabs">
                <li ${param.tab == 'announce' || param.tab == null ? 'class="active"' : ''}><a href="#announce" data-toggle="tab">ประกาศของฉัน</a></li>
                <li ${param.tab == 'contact' ? 'class="active"' : ''}><a href="#contact" data-toggle="tab">รายชื่อผู้ติดต่อ</a></li>
                    <c:if test="${sessionScope.employee.role == 'admin' || sessionScope.employee.role == 'webmaster'}">
                    <li ${param.tab == 'ads' ? 'class="active"' : ''}><a href="#ads" data-toggle="tab">ข่าวของฉัน</a></li>
                    </c:if>
            </ul>
        </div>
        <div class="panel-body">
            <div class="tab-content">

                <div class="tab-pane fade ${param.tab == 'announce' || param.tab == null ? 'in active' : ''}" id="announce">
                    <table id="announce-table" class="table table-bordred table-striped display" cellspacing="0" width="100%">
                        <thead>
                        <th>รหัส</th>
                        <th>ชื่อเรื่อง</th>
                        <th>วันที่แก้ไขล่าสุด</th>
                        <th>แก้ไข</th>
                        <th>ลบ</th>
                        </thead>
                        <tbody>
                            <c:forEach var="res" items="${requestScope.myRes}">
                                <tr>
                                    <td>${res.id}</td>
                                    <td><a href="/RETS/residential?id=${res.id}">${res.name}</a></td>
                                    <td>${res.dt_time}</td>
                                    <td><p data-placement="top" title="Edit"><a href="/RETS/edit_announce?id=${res.id}" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-pencil"></span></a></p></td>
                                    <td><p data-placement="top" title="Delete"><a href="/RETS/DeleteAnnounce?id=${res.id}" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash"></span></a></p></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="tab-pane fade ${param.tab == 'contact' ? 'in active' : ''}" id="contact">
                    <table id="contact-table" class="table table-bordred table-striped display" cellspacing="0" width="100%">
                        <thead>
                        <th>วันที่ส่งมา</th>
                        <th>ชื่อ</th>
                        <th>เบอร์โทร</th>
                        <th>อีเมล์</th>
                        <th>ข้อความ</th>
                        <th>ลบ</th>
                        </thead>
                        <tbody>
                            <c:forEach var="cus" items="${requestScope.myCont}">
                                <tr>
                                    <td>${cus.date}</td>
                                    <td>${cus.fname} ${cus.lname}</td>
                                    <td>${cus.phone}</td>
                                    <td>${cus.email}</td>
                                    <td>${cus.desc}</td>
                                    <td><p data-placement="top" title="Delete"><a href="/RETS/DeleteContact?id=${cus.id}" class="btn btn-danger btn-sm" ><span class="glyphicon glyphicon-trash"></span></a></p></td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>

                <c:if test="${sessionScope.employee.role == 'admin' || sessionScope.employee.role == 'webmaster'}">
                    <div class="tab-pane fade ${param.tab == 'ads' ? 'in active' : ''}" id="ads">
                        <table id="ads-table" class="table table-bordred table-striped display" cellspacing="0" width="100%">
                            <thead>
                            <th>รหัส</th>
                            <th>ชื่อเรื่อง</th>
                            <th>วันที่แก้ไขล่าสุด</th>
                            <th>นำเสนอ</th>
                            <th>แก้ไข</th>
                            <th>ลบ</th>
                            </thead>
                            <tbody>
                                <c:forEach var="ads" items="${requestScope.myAds}">
                                    <tr>
                                        <td>${ads.ads_id}</td>
                                        <td><a href="/RETS/residential?id=${ads.res_id}">${ads.topic}</a></td>
                                        <td>${ads.date}</td>
                                        <td><p data-placement="top" title="show"><a href="/RETS/ShowAds?id=${ads.ads_id}" class="btn ${ads.status ? 'btn-success' : 'btn-default'} btn-sm" ><span class="glyphicon glyphicon-bullhorn"></span></a></p></td>
                                        <td><p data-placement="top" title="Edit"><a href="/RETS/EditAds?id=${ads.ads_id}" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-pencil"></span></a></p></td>
                                        <td><p data-placement="top" title="Delete"><a href="/RETS/DeleteAds?id=${ads.ads_id}" class="btn btn-danger btn-sm" ><span class="glyphicon glyphicon-trash"></span></a></p></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>

            </div>
        </div>
    </div>

</div><!-- /container -->

<script src="/RETS/assets/js/jquery.dataTables.min.js"></script>
<script src="/RETS/assets/js/dataTables.bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        $('#announce-table').DataTable({
            "order": [[2, "desc"]]
        });
        $('#contact-table').DataTable({
            "order": [[0, "desc"]]
        });
        $('#ads-table').DataTable({
            "order": [[2, "desc"]]
        });
    });
</script>

<jsp:include page="footer" />
