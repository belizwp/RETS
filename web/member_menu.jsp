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
                <li ${param.tab == 'ads' ? 'class="active"' : ''}><a href="#ads" data-toggle="tab">ข่าวของฉัน</a></li>
            </ul>
        </div>
        <div class="panel-body">
            <div class="tab-content">

                <div class="tab-pane fade in active" id="announce">
                    <table id="announce-table" class="table table-bordred table-striped display" cellspacing="0" width="100%">
                        <thead>
                        <th>รหัส</th>
                        <th>ชื่อเรื่อง</th>
                        <th>วันที่แก้ไขล่าสุด</th>
                        <th>แก้ไข</th>
                        <th>ลบ</th>
                        </thead>
                        <tbody>
                            <tr>
                                <td>000000</td>
                                <td><a href="property.html">ที่สุดแห่งทำเลทอง ITKMITL</a></td>
                                <td>31/3/2017 23:59:59</td>
                                <td><p data-placement="top" title="Edit"><a href="edit_announce.html" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-pencil"></span></a></p></td>
                                <td><p data-placement="top" title="Delete"><a class="btn btn-danger btn-sm" data-toggle="modal" data-target=".item-delete"><span class="glyphicon glyphicon-trash"></span></a></p></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="tab-pane fade" id="contact">
                    <table id="contact-table" class="table table-bordred table-striped display" cellspacing="0" width="100%">
                        <thead>
                        <th>รหัสประกาศ</th>
                        <th>ชื่อประกาศ</th>
                        <th>วันที่ส่งมา</th>
                        <th>ชื่อ</th>
                        <th>เบอร์โทร</th>
                        <th>อีเมล์</th>
                        <th>ข้อความ</th>
                        <th>ลบ</th>
                        </thead>
                        <tbody>
                            <tr>
                                <td>000000</td>
                                <td><a href="property.html">ที่สุดแห่งทำเลทอง ITKMITL</a></td>
                                <td>31/3/2017 23:59:59</td>
                                <td>สมชาย ใจดี</td>
                                <td>089-9999-9999</td>
                                <td>somchai@somchai.com</td>
                                <td>I’m interested in this property. Please contact me, thanks!</td>
                                <td><p data-placement="top" title="Delete"><a class="btn btn-danger btn-sm" data-toggle="modal" data-target=".item-delete"><span class="glyphicon glyphicon-trash"></span></a></p></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="tab-pane fade" id="ads">
                    <table id="ads-table" class="table table-bordred table-striped display" cellspacing="0" width="100%">
                        <thead>
                        <th>รหัส</th>
                        <th>ชื่อเรื่อง</th>
                        <th>วันที่แก้ไขล่าสุด</th>
                        <th>นำเสนอ</th>
                        <th>ลบ</th>
                        </thead>
                        <tbody>
                            <tr>
                                <td>000000</td>
                                <td><a href="property.html">ที่สุดแห่งทำเลทอง ITKMITL</a></td>
                                <td>31/3/2017 23:59:59</td>
                                <td><p data-placement="top" title="show"><a class="btn btn-success btn-sm" data-toggle="modal" data-target=".item-show"><span class="glyphicon glyphicon-bullhorn"></span></a></p></td>
                                <td><p data-placement="top" title="Delete"><a class="btn btn-danger btn-sm" data-toggle="modal" data-target=".item-delete"><span class="glyphicon glyphicon-trash"></span></a></p></td>
                            </tr>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>

</div><!-- /container -->

<!-- modal delete confirmation -->
<div class="modal fade item-delete" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-body">
                คุณแน่ใจหรือ
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">ลบ</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">ยกเลิก</button>
            </div>
        </div>
    </div>
</div>

<script src="/RETS/assets/js/jquery.dataTables.min.js"></script>
<script src="/RETS/assets/js/dataTables.bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        $('#announce-table').DataTable();
        $('#contact-table').DataTable();
        $('#ads-table').DataTable();
    });
</script>

<jsp:include page="footer" />
