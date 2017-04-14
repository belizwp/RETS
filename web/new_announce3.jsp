<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header" />

<c:set var="ann" value="${sessionScope[param.process_id]}"/>

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

            <!-- Standar Form -->
            <h4>Select files from your computer</h4>
            <!--            <form action="" method="post" enctype="multipart/form-data" id="js-upload-form">-->
            <div class="form-inline">
                <div class="form-group">
                    <input type="file" name="files[]" id="js-upload-files" data-url="/RETS/ImageUpload?process_id=${param.process_id}&type=images" multiple>
                </div>
                <button type="submit" class="btn btn-sm btn-primary" id="js-upload-submit">Upload files</button>
            </div>
            <!--            </form>-->

            <!-- Drop Zone -->
            <h4>Or drag and drop files below</h4>
            <div class="upload-drop-zone" id="drop-zone">
                Just drag and drop files here
            </div>

            <!-- Progress Bar -->
            <div class="progress">
                <div class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
                </div>
            </div>

            <!-- Upload Finished -->
            <div class="js-upload-finished">
                <h3>Processed files</h3>
                <div class="list-group">
                    <!-- <a href="#" class="list-group-item list-group-item-success" style="padding: 5px;"><span class="badge alert-success pull-right">Success</span>image-01.jpg</a> -->
                </div>
            </div>

            <table id="uploaded-files" class="table">
                <tr>
                    <th>File Name</th>
                    <th>File Size</th>
                    <th>File Type</th>
                </tr>
                <c:if test="${ann.files.size() > 0}">
                    <c:forEach var="file" items="${ann.files}">
                        <tr>
                            <td>
                                ${file.fileName}
                            </td>
                            <td>
                                ${file.fileSize}
                            </td>
                            <td>
                                ${feil.fileType}
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>

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

<!--<script src="/RETS/assets/js/upload-multi-file.js"></script>-->
<script src="/RETS/assets/js/vendor/jquery.ui.widget.js"></script>
<script src="/RETS/assets/js/jquery.iframe-transport.js"></script>
<script src="/RETS/assets/js/jquery.fileupload.js"></script>

<script>
    $(function () {
        $('#js-upload-files').fileupload({
            dataType: 'json',

            done: function (e, data) {
                $("tr:has(td)").remove();
                $.each(data.result, function (index, file) {

                    $("#uploaded-files").append(
                            $('<tr/>')
                            .append($('<td/>').text(file.fileName))
                            .append($('<td/>').text(file.fileSize))
                            .append($('<td/>').text(file.fileType))

                            )//end $("#uploaded-files").append()
                });
            },
            progressall: function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('.progress .progress-bar').css(
                        'width',
                        progress + '%'
                        );
            }
        });
    });
</script>

<jsp:include page="/footer" />