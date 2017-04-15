<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header" />

<c:set var="ann" value="${sessionScope[param.process_id]}"/>

<link rel="stylesheet" href="/RETS/assets/css/jquery.fileupload.css">
<div class="container">
    <form class="create-info form-horizontal" action="/RETS/NewAnnounce" enctype="multipart/form-data" method="POST">
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
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="type">* ประกาศสำหรับ : </label>  
                            <div class="col-md-2">
                                <label class="radio-inline"><input type="radio" name="type" value="1" ${ann.type == 1 ? 'checked' : ''} required>ขาย</label>
                                <label class="radio-inline"><input type="radio" name="type" value="2" ${ann.type == 2 ? 'checked' : ''} required>เช่า</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">* ประเภท :</label>
                            <div class="col-md-3">
                                <select class="form-control" name="propType" required>
                                    <option value="1" ${ann.propType == 1 ? 'selected' : ''}>บ้านเดี่ยว</option>
                                    <option value="2" ${ann.propType == 2 ? 'selected' : ''}>คอนโด</option>
                                    <option value="3" ${ann.propType == 3 ? 'selected' : ''}>ทาวน์เฮ้าส์</option>
                                    <option value="4" ${ann.propType == 4 ? 'selected' : ''}>ที่ดิน</option>
                                    <option value="5" ${ann.propType == 5 ? 'selected' : ''}>อพาร์ทเม้น</option>
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
                            <label class="col-md-4 control-label">ชื่อโครงการ : </label>
                            <div class="col-md-4">
                                <input class="form-control" name="name" type="text" value="${ann.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">เลขที่ : </label>
                            <div class="col-md-1">
                                <input class="form-control" name="number" type="text" value="${ann.number}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">ถนน : </label>
                            <div class="col-md-4">
                                <input class="form-control" name="road" type="text" value="${ann.road}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">รหัสไปรษณีย์ : </label>
                            <div class="col-md-2">
                                <input class="form-control" name="postcode" type="text" value="${ann.postcode}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">รูปแผนที่ : </label>
                            <div class="col-md-2">
                                <span class="btn btn-default fileinput-button">
                                    <i class="glyphicon glyphicon-plus"></i>
                                    <span> เลือกไฟล์</span>
                                    <!-- The file input field used as target for the file upload widget -->
                                    <input id="fileupload" name="files" type="file" accept="image/png, image/jpeg, image/gif"/>
                                    <!-- The global progress bar -->
                                    <div id="progress" class="progress" style="padding: 0px; margin: 0px; height: 5px;">
                                        <div class="progress-bar progress-bar-success"></div>
                                    </div>
                                </span>
                            </div>
                        </div>

                        <label class="col-md-4 control-label"></label>
                        <div class="col-md-8">
                            <div id="map-preview" class="map-preview" >
                                <c:if test="${ann.mapImage != null}">
                                    <div class="img-wrap" id="img-wrap">
                                        <img class="img-responsive" src="/RETS/image/?type=map&process_id=${param.process_id}">
                                        <button class="btn btn-danger remove" onclick="removePreview();">
                                            <span class="glyphicon glyphicon-trash"></span>
                                        </button>
                                    </div>
                                </c:if>
                            </div>
                        </div>

                        <br>

                        <legend>ข้อมูลด้านราคา</legend>
                        <div class="form-group">
                            <label class="col-md-4 control-label">ราคา : </label>
                            <div class="col-md-3">
                                <div class="input-group">
                                    <input class="form-control" type="number" min="0" step="1" name="price" value="${ann.price}">
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
                                    <input class="form-control" type="number" min="0" name="area" value="${ann.area}">
                                    <span class="input-group-addon">ตารางเมตร</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">ขนาดพื้นที่ : </label>
                            <div class="col-md-4">
                                <div class="input-group">
                                    <input class="form-control" type="number" min="0" name="width" value="${ann.width}">
                                    <span class="input-group-addon">x</span>
                                    <input class="form-control" type="number" min="0" name="height" value="${ann.height}">
                                    <span class="input-group-addon">เมตร</span>
                                </div>
                            </div>
                        </div>
                        <br>

                    </fieldset>
                </div>
            </div>       

            <div class="panel-footer text-center">
                <input type="submit" name="submit" class="btn btn-primary" value="ถัดไป"/>
                <!-- <input type="button" class="btn btn-success value="บันทึก" /> -->

                <input type="hidden" name="process" class="btn btn-primary" value="basic"/>
                <input type="hidden" name="process_id" class="btn btn-primary" value="${param.process_id}"/>
            </div>  

        </div>
    </form>
</div>

<script src="/RETS/assets/js/vendor/jquery.ui.widget.js"></script>
<script src="/RETS/assets/js/jquery.iframe-transport.js"></script>
<script src="/RETS/assets/js/jquery.fileupload.js"></script>
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

    $(function () {
        var url = "/RETS/upload?process_id=${param.process_id}&type=map";
        $('#fileupload').fileupload({
            url: url,
            dataType: 'json',
            done: function (e, data) {
                $('#map-preview:has(div)').empty();
                addPreview('/RETS/image/?type=map&process_id=${param.process_id}');
            },
            progressall: function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('#progress .progress-bar').css('width', progress + '%');
            }
        }).prop('disabled', !$.support.fileInput)
                .parent().addClass($.support.fileInput ? undefined : 'disabled');
    });

    function addPreview(src) {
        var wrap = document.createElement('div');
        var img = document.createElement('img');
        var removebtn = document.createElement('button');

        wrap.className = 'img-wrap';
        img.className = 'img-responsive';
        img.src = src; // src
        removebtn.className = 'btn btn-danger remove';
        removebtn.innerHTML = '<span class="glyphicon glyphicon-trash"></span>';
        removebtn.onclick = function () {
            removePreview();
        }

        wrap.appendChild(img);
        wrap.appendChild(removebtn);

        document.getElementById('map-preview').appendChild(wrap);
    }

    function removePreview() {
        $('#map-preview:has(div)').empty();
        $('#progress .progress-bar').css(
                'width',
                0 + '%'
                );

        $.ajax({
            type: "POST",
            url: "/RETS/upload",
            data: {process_id: '${param.process_id}', type: 'remove_map'},
            success: function (data) {
            }
        });
    }
</script>

<jsp:include page="/footer" />