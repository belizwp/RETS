<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header" />

<c:set var="ann" value="${sessionScope[param.process_id]}"/>

<div class="container">
    <form class="create-info form-horizontal" action="/RETS/NewAnnounce" enctype="multipart/form-data" method="POST">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2>ลงประกาศใหม่</h2>
            </div>

            <div class="panel-body">
                <input type="submit" name="submit" class="btn btn-primary" value="ข้อมูลทั่วไป">
                <span class="glyphicon glyphicon-menu-right"></span>
                <input type="submit" name="submit" class="btn btn-default" value="รายละเอียดเพิ่มเติม">
                <span class="glyphicon glyphicon-menu-right"></span>
                <input type="submit" name="submit" class="btn btn-default" value="รูปภาพ">
                <span class="glyphicon glyphicon-menu-right"></span>
                <input type="submit" name="submit" class="btn btn-default" value="สรุป">
                <h6 class="pull-right" style="color: red">* ข้อมูลที่จำเป็นต้องใช้</h6>
            </div>

            <div class="panel-body">
                <div class="row">
                    <fieldset>

                        <legend>ใส่ข้อมูลทั่วไป</legend>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="type">* ประกาศสำหรับ : </label>  
                            <div class="col-md-2">
                                <label class="radio-inline"><input type="radio" name="type" value="ขาย" ${ann.type == 'ขาย' ? 'checked' : ''} required>ขาย</label>
                                <label class="radio-inline"><input type="radio" name="type" value="เช่า" ${ann.type == 'เช่า' ? 'checked' : ''} required>เช่า</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">* ประเภท :</label>
                            <div class="col-md-3">
                                <select class="form-control" name="propType" required>
                                    <option value="1" ${ann.propType == 'บ้านเดี่ยว' ? 'selected' : ''}>บ้านเดี่ยว</option>
                                    <option value="2" ${ann.propType == 'คอนโด' ? 'selected' : ''}>คอนโด</option>
                                    <option value="3" ${ann.propType == 'ทาวน์เฮ้าส์' ? 'selected' : ''}>ทาวน์เฮ้าส์</option>
                                    <option value="4" ${ann.propType == 'ที่ดิน' ? 'selected' : ''}>ที่ดิน</option>
                                    <option value="5" ${ann.propType == 'อพาร์ทเม้น' ? 'selected' : ''}>อพาร์ทเม้น</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">* หัวข้อประกาศ : </label>
                            <div class="col-md-4">
                                <input class="form-control" type="text" name="title" value="${ann.title}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">* รายละเอียดประกาศ : </label>
                            <div class="col-md-4">
                                <textarea class="form-control" name="detail" required>${ann.detail}</textarea>
                            </div>
                        </div>
                        <br>

                        <legend>รายละเอียดที่ตั้ง</legend>
                        <div class="form-group">
                            <label class="col-md-4 control-label">* จังหวัด : </label>
                            <div class="col-md-3">
                                <select class="form-control" name="province" id="province-list" onChange="getAmphur(this.value);" required >
                                    <sql:query var="province_rows" dataSource="${dataSource}">
                                        SELECT * FROM province
                                    </sql:query>
                                    <c:forEach var="province_row" items="${province_rows.rows}">
                                        <option value="${province_row.province_id}" >${province_row.province_name}</option>
                                    </c:forEach>
                                    <script>
                                        $("#province-list").val(${ann.province});
                                    </script>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">* อำเภอ/เขต : </label>
                            <div class="col-md-3">
                                <select class="form-control" name="amphur" id="amphur-list" onChange="getDistrict(this.value);" required>
                                    <sql:query var="amphur_rows" dataSource="${dataSource}">
                                        SELECT * FROM amphur WHERE province_id = ?
                                        <sql:param value="${ann.province == null ? 1 : ann.province}"/>
                                    </sql:query>
                                    <c:forEach var="amphur_row" items="${amphur_rows.rows}">
                                        <option value="${amphur_row.amphur_id}" >${amphur_row.amphur_name}</option>
                                    </c:forEach>
                                    <script>
                                        $("#amphur-list").val(${ann.amphur});
                                    </script>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">* ตำบล/แขวง : </label>
                            <div class="col-md-3">
                                <select class="form-control" name="district" id="district-list" required>
                                    <sql:query var="district_rows" dataSource="${dataSource}">
                                        SELECT * FROM district WHERE amphur_id = ?
                                        <sql:param value="${ann.amphur == null ? 1 : ann.amphur}"/>
                                    </sql:query>
                                    <c:forEach var="district_row" items="${district_rows.rows}">
                                        <option value="${district_row.district_id}" >${district_row.district_name}</option>
                                    </c:forEach>
                                    <script>
                                        $("#district-list").val(${ann.district});
                                    </script>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">* ชื่อโครงการ : </label>
                            <div class="col-md-4">
                                <input class="form-control" name="name" type="text" value="${ann.name}" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">* ที่อยู่ : </label>
                            <div class="col-md-4">
                                <textarea class="form-control" name="address" type="text" required>${ann.address}</textarea>
                            </div>
                        </div>

                        <legend>ข้อมูลด้านราคา</legend>
                        <div class="form-group">
                            <label class="col-md-4 control-label">* ราคา : </label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <input class="form-control" type="number" min="0" step="1" name="price" value="${ann.price}" required>
                                    <span class="input-group-addon" id="price-unit">บาท</span>
                                </div>
                            </div>
                        </div>
                        <br>

                        <legend>ข้อมูลเจ้าของ</legend>
                        <div class="form-group">
                            <label class="col-md-4 control-label">* ชื่อ : </label>
                            <div class="col-md-4">
                                <input name="first_name" id="first_name" class="form-control" type="text" maxlength="40" required value="${ann.fname}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">* นามสกุล : </label>
                            <div class="col-md-4">
                                <input name="last_name" id="last_name" class="form-control" type="text" maxlength="40" required value="${ann.lname}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">* เบอร์โทรศัพท์ : </label>
                            <div class="col-md-4">
                                <input name="phone" id="phone" class="form-control" type="text" maxlength="10" required value="${ann.phone}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">อีเมล : </label>
                            <div class="col-md-4">
                                <input name="email" id="email" class="form-control" type="email" maxlength="25" value="${ann.email}">
                            </div>
                        </div>
                        <br>

                    </fieldset>
                </div>
            </div>       

            <div class="panel-footer text-center">
                <input type="submit" name="submit" class="btn btn-primary" value="ถัดไป">
                <!-- <input type="button" class="btn btn-success value="บันทึก" > -->

                <input type="hidden" name="process" class="btn btn-primary" value="basic">
                <input type="hidden" name="process_id" class="btn btn-primary" value="${param.process_id}">
            </div>  

        </div>
    </form>
</div>

<script>
    function getAmphur(val) {
        $.ajax({
            type: "POST",
            url: "/RETS/searchbox/get_amphur.jsp",
            data: {province_id: val, specific: true},
            success: function (data) {
                $("#amphur-list").html(data);
                getDistrict($("#amphur-list").val());
                $(".selectpicker").selectpicker('refresh');
            }
        });
    }

    function getDistrict(val) {
        $.ajax({
            type: "POST",
            url: "/RETS/searchbox/get_district.jsp",
            data: {amphur_id: val, specific: true},
            success: function (data) {
                $("#district-list").html(data);
                $(".selectpicker").selectpicker('refresh');
            }
        });
    }
</script>

<jsp:include page="/footer" />