<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp" />

<!--content-->
<div class="container">
    <form class="create-info form-horizontal">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2>ลงประกาศใหม่</h2>
            </div>
            <div class="panel-body">
                <a type="button" class="btn btn-primary">ข้อมูลทั่วไป</a>
                <span class="glyphicon glyphicon-menu-right"></span>
                <a type="button" class="btn btn-default">รายละเอียดเพิ่มเติม</a>
                <span class="glyphicon glyphicon-menu-right"></span>
                <a type="button" class="btn btn-default">รูปภาพ</a>
                <span class="glyphicon glyphicon-menu-right"></span>
                <a type="button" class="btn btn-default">สรุป</a>
                <h6 class="pull-right" style="color: red">* ข้อมูลที่จำเป็นต้องใช้</h6>
            </div>

            <div class="panel-body">
                <div class="row">
                    <fieldset>

                        <legend>ใส่ข้อมูลทั่วไป</legend>
                        <div class="form-group" required>
                            <label class="col-md-4 control-label" for="title">* ประกาศสำหรับ : </label>  
                            <div class="col-md-2">
                                <label class="radio-inline"><input type="radio" name="announce_type">ขาย</label>
                                <label class="radio-inline"><input type="radio" name="announce_type">เช่า</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">* ประเภท :</label>
                            <div class="col-md-3">
                                <select class="form-control" name="propery_type" required>
                                    <option value="1">บ้านเดี่ยว</option>
                                    <option value="2">คอนโด</option>
                                    <option value="3">ทาวน์เฮ้าส์</option>
                                    <option value="4">ที่ดิน</option>
                                    <option value="5">อพาร์ทเม้น</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">* หัวข้อประกาศ : </label>
                            <div class="col-md-4">
                                <input class="form-control" type="text" name="announce_title" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">* รายละเอียดประกาศ : </label>
                            <div class="col-md-4">
                                <textarea class="form-control" name="announce_detail" required></textarea>
                            </div>
                        </div>
                        <br>

                        <legend>รายละเอียดที่ตั้ง</legend>
                        <div class="form-group">
                            <label class="col-md-4 control-label">* จังหวัด : </label>
                            <div class="col-md-3">
                                <select class="form-control" name="province" id="province-list" required>
                                    <option>[เลือก จังหวัด]</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">* อำเภอ/เขต : </label>
                            <div class="col-md-3">
                                <select class="form-control" name="amphur" id="amphur-list" required disabled>
                                    <option>[เลือก อำเภอ/เขต]</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">* ตำบล/แขวง : </label>
                            <div class="col-md-3">
                                <select class="form-control" name="district" id="district-list" required disabled>
                                    <option selected>[เลือก ตำบล/แขวง]</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">ชื่อโครงการ : </label>
                            <div class="col-md-4">
                                <input class="form-control" name="property_name" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">เลขที่ : </label>
                            <div class="col-md-1">
                                <input class="form-control" name="property_number" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">ถนน : </label>
                            <div class="col-md-4">
                                <input class="form-control" name="property_road" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">รหัสไปรษณีย์ : </label>
                            <div class="col-md-2">
                                <input class="form-control" name="property_postcode" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">รูปแผนที่ : </label>
                            <div class="col-md-4">
                                <!-- image-preview-filename input [CUT FROM HERE]-->
                                <div class="input-group image-preview">
                                    <input type="text" class="form-control image-preview-filename" disabled="disabled"> <!-- don't give a name === doesn't send on POST/GET -->
                                    <span class="input-group-btn">
                                        <!-- image-preview-clear button -->
                                        <button type="button" class="btn btn-default image-preview-clear" style="display:none; margin: 0px;">
                                            <span class="glyphicon glyphicon-remove"></span> Clear
                                        </button>
                                        <!-- image-preview-input -->
                                        <div class="btn btn-default image-preview-input">
                                            <span class="glyphicon glyphicon-folder-open"></span>
                                            <span class="image-preview-input-title">Browse</span>
                                            <input type="file" accept="image/png, image/jpeg, image/gif" name="property_map"/> <!-- rename it -->
                                        </div>
                                    </span>
                                </div><!-- /input-group image-preview [TO HERE]--> 
                            </div>
                        </div>
                        <br>

                        <legend>ข้อมูลด้านราคา</legend>
                        <div class="form-group">
                            <label class="col-md-4 control-label">ราคา : </label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <input class="form-control" type="number" min="0" step="1" name="property_price">
                                    <span class="input-group-addon" id="price-unit">บาท</span>
                                </div>
                            </div>
                        </div>
                        <br>

                        <legend>ขนาดเพิ่มเติม</legend>
                        <div class="form-group">
                            <label class="col-md-4 control-label">พื้นที่ใช้สอย : </label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input class="form-control" type="number" min="0" name="property_area">
                                    <span class="input-group-addon">ตารางเมตร</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">ขนาดพื้นที่ : </label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input class="form-control" type="number" min="0" name="property_sizew">
                                    <span class="input-group-addon">x</span>
                                    <input class="form-control" type="number" min="0" name="property_sizeh">
                                    <span class="input-group-addon">เมตร</span>
                                </div>
                            </div>
                        </div>
                        <br>

                    </fieldset>
                </div>
            </div>       

            <div class="panel-footer text-center">
                <button type="submit" class="btn btn-primary">ถัดไป</button>
            </div>  

        </div>
    </form>
</div>
<!-- /.container -->

<jsp:include page="templates/footer.jsp" />

<script src="/RETS/assets/js/upload-single-image.js"></script>