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
                <input type="submit" name="submit" class="btn btn-default" value="ข้อมูลทั่วไป">
                <span class="glyphicon glyphicon-menu-right"></span>
                <input type="submit" name="submit" class="btn btn-primary" value="รายละเอียดเพิ่มเติม">
                <span class="glyphicon glyphicon-menu-right"></span>
                <input type="submit" name="submit" class="btn btn-default" value="รูปภาพ">
                <span class="glyphicon glyphicon-menu-right"></span>
                <input type="submit" name="submit" class="btn btn-default" value="สรุป">
                <h6 class="pull-right" style="color: red">* ข้อมูลที่จำเป็นต้องใช้</h6>
            </div>

            <div class="panel-body">
                <div class="row">
                    <fieldset>

                        <legend>ข้อมูลเพิ่มเติม</legend>
                        <div class="form-group">
                            <label class="col-md-4 control-label">จำนวนชั้น : </label>
                            <div class="col-md-2">
                                <input class="form-control" type="number" min="0" name="floor" value="${ann.floor}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">ค่านำ้ : </label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <input class="form-control" type="number" min="0" name="water" value="${ann.water}">
                                    <span class="input-group-addon">บาท/ยูนิต</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">ค่าไฟ : </label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <input class="form-control" type="number" min="0" name="electricity" value="${ann.electricity}">
                                    <span class="input-group-addon">บาท/ยูนิต</span>
                                </div>
                            </div>
                        </div>
                        <div class="control-group">
                            <div class="form-group">
                                <label class="col-md-4 control-label">สิ่งอำนวยความสะดวก : </label>
                                <div class="col-md-4">
                                    <textarea class="form-control" name="facilities" >${ann.facilities}</textarea>
                                </div>
                            </div>
                        </div>
                        <br>

                    </fieldset>
                </div>
            </div>       

            <div class="panel-footer text-center">
                <input type="submit" name="submit" class="btn btn-default" value="กลับ" >
                <!-- <input type="button" class="btn btn-success value="บันทึก" > -->
                <input type="submit" name="submit" class="btn btn-primary" value="ถัดไป">

                <input type="hidden" name="process" class="btn btn-primary" value="detail">
                <input type="hidden" name="process_id" class="btn btn-primary" value="${param.process_id}">
            </div>  

        </div>
    </form>
</div>

<jsp:include page="/footer" />