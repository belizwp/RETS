<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header" />

<c:set var="ann" value="${sessionScope[param.process_id]}"/>

<link rel="stylesheet" href="/RETS/assets/css/jquery.fileupload.css">
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h2>ลงประกาศใหม่</h2>
        </div>

        <div class="panel-body">
            <a type="button" class="btn btn-default">ข้อมูลทั่วไป</a>
            <span class="glyphicon glyphicon-menu-right"></span>
            <a type="button" class="btn btn-default">รายละเอียดเพิ่มเติม</a>
            <span class="glyphicon glyphicon-menu-right"></span>
            <a type="button" class="btn btn-primary">รูปภาพ</a>
            <span class="glyphicon glyphicon-menu-right"></span>
            <a type="button" class="btn btn-default">สรุป</a>
            <h6 class="pull-right" style="color: red">* ข้อมูลที่จำเป็นต้องใช้</h6>
        </div>

        <div class="panel-body">

            <legend>ใส่รูปภาพ</legend>

            <span class="btn btn-success fileinput-button">
                <i class="glyphicon glyphicon-plus"></i>
                <span> เลือกไฟล์</span>
                <!-- The file input field used as target for the file upload widget -->
                <input id="fileupload" name="files[]" type="file" accept="image/png, image/jpeg, image/gif" multiple/>
            </span>

            <br>
            <br>

            <!-- The global progress bar -->
            <div id="progress" class="progress">
                <div class="progress-bar progress-bar-success"></div>
            </div>

            <!-- img preview thumbnail -->
            <div class='list-group gallery' id='preview'>
                <c:if test="${ann.files.size() > 0}">
                    <c:forEach var="file" items="${ann.files}" varStatus="index">
                        <div class="thumbnail img-wrap col-sm-4 col-xs-6 col-md-3 col-lg-3">
                            <img id="preview-img" class="img-responsive" src="/RETS/image/?process_id=${param.process_id}&type=preview&index=${index.index}">
                            <button class="btn btn-danger remove" onclick="removePreview(${index.index}, this);">
                                <span class="glyphicon glyphicon-trash"></span>
                            </button>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
            
        </div>       

        <div class="panel-footer text-center">
            <form class="create-info form-horizontal" action="/RETS/NewAnnounce" method="POST">
                <input type="submit" name="submit" class="btn btn-default" value="กลับ" />
                <!-- <input type="button" class="btn btn-success value="บันทึก" /> -->
                <input type="submit" name="submit" class="btn btn-primary" value="ถัดไป"/>

                <input type="hidden" name="process" class="btn btn-primary" value="media"/>
                <input type="hidden" name="process_id" class="btn btn-primary" value="${param.process_id}"/>
            </form>
        </div>  

    </div>
</div>

<script src="/RETS/assets/js/vendor/jquery.ui.widget.js"></script>
<script src="/RETS/assets/js/load-image.all.min.js"></script>
<script src="/RETS/assets/js/canvas-to-blob.min.js"></script>
<script src="/RETS/assets/js/jquery.iframe-transport.js"></script>
<script src="/RETS/assets/js/jquery.fileupload.js"></script>
<script src="/RETS/assets/js/jquery.fileupload-process.js"></script>
<script src="/RETS/assets/js/jquery.fileupload-image.js"></script>

<script>
    $(function () {
        var url = "/RETS/upload?process_id=${param.process_id}&type=images";
        $('#fileupload').fileupload({
            url: url,
            dataType: 'json',
            disableImageResize: false,
            imageMaxWidth: 1000,
            imageMaxHeight: 1000,
            done: function (e, data) {
                $('.gallery').empty();
                $.each(data.result, function (index, file) {
                    addPreview(index, '/RETS/image/?process_id=${param.process_id}&type=preview&index=' + index);
                });
            },
            progressall: function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('#progress .progress-bar').css(
                        'width',
                        progress + '%'
                        );
            }
        }).prop('disabled', !$.support.fileInput)
                .parent().addClass($.support.fileInput ? undefined : 'disabled');
    });

    function addPreview(index, src) {
        var thumb = document.createElement('div');
        var img = document.createElement('img');
        var removebtn = document.createElement('button');

        thumb.className = 'thumbnail img-wrap col-sm-4 col-xs-6 col-md-3 col-lg-3';
        img.className = 'img-responsive';
        img.src = src; // src
        removebtn.className = 'btn btn-danger remove';
        removebtn.innerHTML = '<span class="glyphicon glyphicon-trash"></span>';
        removebtn.onclick = function () {
            removePreview(index, this);
        }

        thumb.appendChild(img);
        thumb.appendChild(removebtn);

        document.getElementById('preview').appendChild(thumb);
    }

    function removePreview(index, input) {
        document.getElementById('preview').removeChild(input.parentNode);
        
        $.ajax({
            type: "POST",
            url: "/RETS/upload",
            data: {process_id: '${param.process_id}', type: 'remove', index: index},
            success: function (data) {
                location.reload();
            }
        });
    }
</script>

<jsp:include page="/footer" />