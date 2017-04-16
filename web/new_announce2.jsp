<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header" />

<c:set var="ann" value="${sessionScope[param.process_id]}"/>

<div class="container">
    <form class="create-info form-horizontal" action="/RETS/NewAnnounce" method="POST">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2>ลงประกาศใหม่</h2>
            </div>

            <div class="panel-body">
                <a type="button" class="btn btn-default">ข้อมูลทั่วไป</a>
                <span class="glyphicon glyphicon-menu-right"></span>
                <a type="button" class="btn btn-primary">รายละเอียดเพิ่มเติม</a>
                <span class="glyphicon glyphicon-menu-right"></span>
                <a type="button" class="btn btn-default">รูปภาพ</a>
                <span class="glyphicon glyphicon-menu-right"></span>
                <a type="button" class="btn btn-default">สรุป</a>
                <h6 class="pull-right" style="color: red">* ข้อมูลที่จำเป็นต้องใช้</h6>
            </div>

            <div class="panel-body">
                <div class="row">
                    <fieldset>

                        <legend>ข้อมูลเพิ่มเติม</legend>
                        <div class="form-group">
                            <label class="col-md-4 control-label">จำนวนชั้น : </label>
                            <div class="col-md-2">
                                <input class="form-control" type="number" name="floor" value="${ann.floor}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">ค่านำ้ : </label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <input class="form-control" type="number" name="water" value="${ann.water}">
                                    <span class="input-group-addon">บาท/ยูนิต</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">ค่าไฟ : </label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <input class="form-control" type="number" name="electricity" value="${ann.electricity}">
                                    <span class="input-group-addon">บาท/ยูนิต</span>
                                </div>
                            </div>
                        </div>
                        <br>

                        <legend>สิ่งอำนวยความสะดวก</legend>
                        <div class="control-group">
                            <div class="row">
                                <div class="col-sm-4">
                                    <label class="checkbox-inline"><input type="checkbox" name="internet" value="อินเตอร์เนต" ${ann.internet == 'อินเตอร์เนต' ? 'checked' : ''}>อินเตอร์เนต</label>
                                </div>
                                <div class="col-sm-4">
                                    <label class="checkbox-inline"><input type="checkbox" name="security" value="รักษาความปลอดภัย 24 ชั่วโมง" ${ann.security == 'รักษาความปลอดภัย 24 ชั่วโมง' ? 'checked' : ''}>รักษาความปลอดภัย 24 ชั่วโมง</label>
                                </div>
                                <div class="col-sm-4">
                                    <label class="checkbox-inline"><input type="checkbox" name="swimPool" value="สระว่ายน้ำ" ${ann.swimPool == 'สระว่ายน้ำ' ? 'checked' : ''}>สระว่ายน้ำ</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <label class="checkbox-inline"><input type="checkbox" name="laundry" value="ซัก อบ รีด" ${ann.laundry == 'ซัก อบ รีด' ? 'checked' : ''}>ซัก อบ รีด</label>
                                </div>
                                <div class="col-sm-4">
                                    <label class="checkbox-inline"><input type="checkbox" name="cam" value="กล้องวงจรปิด" ${ann.cam == 'กล้องวงจรปิด' ? 'checked' : ''}>กล้องวงจรปิด</label>
                                </div>
                                <div class="col-sm-4">
                                    <label class="checkbox-inline"><input type="checkbox" name="parklot" value="ลานจอดรถ" ${ann.parklot == 'ลานจอดรถ' ? 'checked' : ''}>ลานจอดรถ</label>
                                </div>
                            </div>
                        </div>
                        <br>

                    </fieldset>
                </div>
            </div>       

            <div class="panel-footer text-center">
                <input type="submit" name="submit" class="btn btn-default" value="กลับ" />
                <!-- <input type="button" class="btn btn-success value="บันทึก" /> -->
                <input type="submit" name="submit" class="btn btn-primary" value="ถัดไป"/>

                <input type="hidden" name="process" class="btn btn-primary" value="detail"/>
                <input type="hidden" name="process_id" class="btn btn-primary" value="${param.process_id}"/>
            </div>  

        </div>
    </form>
</div>

<jsp:include page="/footer" />