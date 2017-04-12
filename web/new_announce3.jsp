<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header" />

<div class="container">
    <form class="create-info form-horizontal" action="/RETS/NewAnnounce" method="POST">
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
                <form action="" method="post" enctype="multipart/form-data" id="js-upload-form">
                    <div class="form-inline">
                        <div class="form-group" style="padding: 15px;">
                            <input type="file" name="files[]" id="js-upload-files" multiple>
                        </div>
                        <button type="submit" class="btn btn-sm btn-primary" id="js-upload-submit">Upload files</button>
                    </div>
                </form>

                <!-- Drop Zone -->
                <h4>Or drag and drop files below</h4>
                <div class="upload-drop-zone" id="drop-zone">
                    Just drag and drop files here
                </div>

                <!-- Progress Bar -->
                <div class="progress">
                    <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                        <span class="sr-only">60% Complete</span>
                    </div>
                </div>

                <!-- Upload Finished -->
                <div class="js-upload-finished">
                    <h3>Processed files</h3>
                    <div class="list-group">
                        <a href="#" class="list-group-item list-group-item-success" style="padding: 5px;"><span class="badge alert-success pull-right">Success</span>image-01.jpg</a>
                        <a href="#" class="list-group-item list-group-item-success" style="padding: 5px;"><span class="badge alert-success pull-right">Success</span>image-02.jpg</a>
                    </div>
                </div>
                <br>

            </div>       

            <div class="panel-footer text-center">
                <input type="submit" name="submit" class="btn btn-default" value="กลับ" />
                <!-- <input type="button" class="btn btn-success value="บันทึก" /> -->
                <input type="submit" name="submit" class="btn btn-primary" value="ถัดไป"/>

                <input type="hidden" name="process" class="btn btn-primary" value="media"/>
                <input type="hidden" name="process_id" class="btn btn-primary" value="${param.process_id}"/>
            </div>  

        </div>
    </form>
</div>

<script src="/RETS/assets/js/upload-multi-file.js"></script>

<jsp:include page="/footer" />