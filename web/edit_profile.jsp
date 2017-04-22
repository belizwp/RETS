<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header"/>

<div class="container">
    <form class="register form-horizontal" action="/RETS/EditProfile" method="POST">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2>แก้ไขบัญชี สมาชิก</h2>
            </div>
            <div class="panel-body">
                <div class="row">
                    <fieldset>

                        <legend>กรอกรายละเอียด<span><h6 class="pull-right" style="color: red">* ข้อมูลที่จำเป็นต้องใช้</h6></span></legend>
                        
                        <div class="form-group">
                            <label class="col-md-4 control-label">ชื่อ : </label>
                            <div class="col-md-4">
                                <input name="first_name" id="first_name" class="form-control" type="text" maxlength="40" value="${sessionScope.employee.fname}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">นามสกุล : </label>
                            <div class="col-md-4">
                                <input name="last_name" id="last_name" class="form-control" type="text" maxlength="40" value="${sessionScope.employee.lname}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">เบอร์โทรศัพท์ : </label>
                            <div class="col-md-4">
                                <input name="phone" id="phone" class="form-control" type="text" maxlength="10" value="${sessionScope.employee.phone}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">อีเมล : </label>
                            <div class="col-md-4">
                                <input name="email" id="email" class="form-control" type="email" maxlength="25" value="${sessionScope.employee.email}">
                            </div>
                        </div>
                            
                        <div class="form-group">
                            <label class="col-md-4 control-label">* รหัสผ่านเดิม : </label>
                            <div class="col-md-4">
                                <input name="password" id="password" class="form-control" type="password" maxlength="30" required>
                            </div>
                        </div>
                            
                        <div class="form-group">
                            <label class="col-md-4 control-label">รหัสผ่านใหม่ : </label>
                            <div class="col-md-4">
                                <input name="new_password" id="password" class="form-control" type="password" maxlength="30">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">ยืนยันระหัสผ่านใหม่ : </label>
                            <div class="col-md-4">
                                <input name="new_password_confirmation" id="password_confirmation" class="form-control" type="password" maxlength="30">
                            </div>
                        </div>

                    </fieldset>
                </div>
            </div>       

            <div class="panel-footer text-center">
                <input type="submit" class="btn btn-success" value="บันทึก" />
            </div>  

        </div>
    </form>
</div>

<jsp:include page="footer"/>
