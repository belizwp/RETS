<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header" />

<div class="container">
    <div class="panel panel-default"> 
        <div class="panel-body">
            <h4>สวัสดีคุณ Nakarin Kakanumporn &nbsp;
                <span><small><a href="edit_account.html"> แก้ไขข้อมูลสมาชิก </a> | <a href="index.html"> ออกจากระบบ </a></small></span>
            </h4>
        </div>
    </div>

    <div class="panel panel-default"> 
        <div class="panel-body">

            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#announce" aria-controls="announce" role="tab" data-toggle="tab">ประกาศของฉัน</a></li>
                <li role="presentation"><a href="#contact" aria-controls="contact" role="tab" data-toggle="tab">รายชื่อผู้ติดต่อ</a></li>
                <li role="presentation"><a href="#ads" aria-controls="ads" role="tab" data-toggle="tab">ข่าวของฉัน</a></li>
            </ul>

            <div class="tab-content">

                <div role="tabpanel" class="tab-pane fade in active" id="announce">
                    <div class="table-responsive">
                        <table id="announce-table" class="table table-bordred table-striped">
                            <thead>
                            <th><input type="checkbox" id="checkall" /></th>
                            <th>รหัส</th>
                            <th>สถานะ</th>
                            <th>ชื่อเรื่อง</th>
                            <th>วันที่แก้ไขล่าสุด</th>
                            <th>จำนวนผู้ติดต่อ</th>
                            <th>แก้ไข</th>
                            <th>ลบ</th>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><input type="checkbox" class="checkthis" /></td>
                                    <td>000000</td>
                                    <td>ปกติ</td>
                                    <td><a href="property.html">ที่สุดแห่งทำเลทอง ITKMITL</a></td>
                                    <td>31/3/2017 23:59:59</td>
                                    <td>123</td>
                                    <td><p data-placement="top" title="Edit"><a href="edit_announce.html" class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-pencil"></span></a></p></td>
                                    <td><p data-placement="top" title="Delete"><a class="btn btn-danger btn-xs" data-toggle="modal" data-target=".item-delete"><span class="glyphicon glyphicon-trash"></span></a></p></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div role="tabpanel" class="tab-pane fade" id="contact">
                    <div class="table-responsive">
                        <table id="contact-table" class="table table-bordred table-striped">
                            <thead>
                            <th><input type="checkbox" id="checkall" /></th>
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
                                    <td><input type="checkbox" class="checkthis" /></td>
                                    <td>000000</td>
                                    <td><a href="property.html">ที่สุดแห่งทำเลทอง ITKMITL</a></td>
                                    <td>31/3/2017 23:59:59</td>
                                    <td>สมชาย ใจดี</td>
                                    <td>089-9999-9999</td>
                                    <td>somchai@somchai.com</td>
                                    <td>I’m interested in this property. Please contact me, thanks!</td>
                                    <td><p data-placement="top" title="Delete"><a class="btn btn-danger btn-xs" data-toggle="modal" data-target=".item-delete"><span class="glyphicon glyphicon-trash"></span></a></p></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div role="tabpanel" class="tab-pane fade" id="ads">
                    <div class="table-responsive">
                        <table id="ads-table" class="table table-bordred table-striped">
                            <thead>
                            <th><input type="checkbox" id="checkall" /></th>
                            <th>รหัส</th>
                            <th>สถานะ</th>
                            <th>ชื่อเรื่อง</th>
                            <th>วันที่แก้ไขล่าสุด</th>
                            <th>จำนวนผู้ติดต่อ</th>
                            <th>นำเสนอ</th>
                            <th>ลบ</th>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><input type="checkbox" class="checkthis" /></td>
                                    <td>000000</td>
                                    <td>แสดง</td>
                                    <td><a href="property.html">ที่สุดแห่งทำเลทอง ITKMITL</a></td>
                                    <td>31/3/2017 23:59:59</td>
                                    <td>123</td>
                                    <td><p data-placement="top" title="show"><a class="btn btn-success btn-xs" data-toggle="modal" data-target=".item-show"><span class="glyphicon glyphicon-bullhorn"></span></a></p></td>
                                    <td><p data-placement="top" title="Delete"><a class="btn btn-danger btn-xs" data-toggle="modal" data-target=".item-delete"><span class="glyphicon glyphicon-trash"></span></a></p></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div><!-- tab-content -->
        </div><!-- panel-body -->
    </div><!-- /panel -->
</div><!-- /container -->

<jsp:include page="footer" />
