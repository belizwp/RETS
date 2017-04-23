<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header" />

<style>
    .image-preview-input {
        position: relative;
        overflow: hidden;
        margin: 0px;    
        color: #333;
        background-color: #fff;
        border-color: #ccc;    
    }
    .image-preview-input input[type=file] {
        position: absolute;
        top: 0;
        right: 0;
        margin: 0;
        padding: 0;
        font-size: 20px;
        cursor: pointer;
        opacity: 0;
        filter: alpha(opacity=0);
    }
    .image-preview-input-title {
        margin-left:2px;
    }
</style>

<div class="container">
    <form class="register form-horizontal" action="/RETS/InsertAdvertise" enctype="multipart/form-data" method="POST">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2>สร้างข่าวประชาสัมพันธ์</h2>
            </div>
            <div class="panel-body">
                <div class="row">
                    <fieldset>

                        <legend>กรอกรายละเอียด<span><h6 class="pull-right" style="color: red">* ข้อมูลที่จำเป็นต้องใช้</h6></span></legend>

                        <div class="form-group">
                            <label class="col-md-4 control-label">รูปภาพ : </label>
                            <div class="col-md-4">
                                <!-- image-preview-filename input [CUT FROM HERE]-->
                                <div class="input-group image-preview">
                                    <input type="text" class="form-control image-preview-filename" disabled="disabled"> <!-- don't give a name === doesn't send on POST/GET -->
                                    <span class="input-group-btn">
                                        <!-- image-preview-clear button -->
                                        <button type="button" class="btn btn-default image-preview-clear" style="display:none;">
                                            <span class="glyphicon glyphicon-remove"></span> Clear
                                        </button>
                                        <!-- image-preview-input -->
                                        <div class="btn btn-default image-preview-input">
                                            <span class="glyphicon glyphicon-folder-open"></span>
                                            <span class="image-preview-input-title">Browse</span>
                                            <input type="file" accept="image/png, image/jpeg, image/gif" name="ads_img" required/> <!-- rename it -->
                                        </div>
                                    </span>
                                </div><!-- /input-group image-preview [TO HERE]--> 
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">* หัวเรื่อง : </label>
                            <div class="col-md-4">
                                <input name="title" id="title" class="form-control" type="text" maxlength="40" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">* รายละเอียด : </label>
                            <div class="col-md-4">
                                <input name="detail" id="detail" class="form-control" type="text" maxlength="40" required>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-md-4 control-label">* รหัสประกาศ : </label>
                            <div class="col-md-4">
                                <input name="res_id" id="res_id" class="form-control" type="text" required>
                            </div>
                        </div>

                    </fieldset>
                </div>
            </div>       

            <div class="panel-footer text-center">
                <input type="submit" class="btn btn-success" value="บันทึก" />
                <input type="submit" class="btn btn-danger" value="นำเสนอ" />
            </div>  

        </div>
    </form>
</div>

<script>
    $(document).on('click', '#close-preview', function () {
        $('.image-preview').popover('hide');
        // Hover befor close the preview
        $('.image-preview').hover(
                function () {
                    $('.image-preview').popover('show');
                },
                function () {
                    $('.image-preview').popover('hide');
                }
        );
    });

    $(function () {
        // Create the close button
        var closebtn = $('<button/>', {
            type: "button",
            text: 'x',
            id: 'close-preview',
            style: 'font-size: initial;',
        });
        closebtn.attr("class", "close pull-right");
        // Set the popover default content
        $('.image-preview').popover({
            trigger: 'manual',
            html: true,
            title: "<strong>Preview</strong>" + $(closebtn)[0].outerHTML,
            content: "There's no image",
            placement: 'bottom'
        });
        // Clear event
        $('.image-preview-clear').click(function () {
            $('.image-preview').attr("data-content", "").popover('hide');
            $('.image-preview-filename').val("");
            $('.image-preview-clear').hide();
            $('.image-preview-input input:file').val("");
            $(".image-preview-input-title").text("Browse");
        });
        // Create the preview image
        $(".image-preview-input input:file").change(function () {
            var img = $('<img/>', {
                id: 'dynamic',
                width: 250,
                height: 200
            });
            var file = this.files[0];
            var reader = new FileReader();
            // Set preview image into the popover data-content
            reader.onload = function (e) {
                $(".image-preview-input-title").text("Change");
                $(".image-preview-clear").show();
                $(".image-preview-filename").val(file.name);
                img.attr('src', e.target.result);
                $(".image-preview").attr("data-content", $(img)[0].outerHTML).popover("show");
            }
            reader.readAsDataURL(file);
        });
    });
</script>

<jsp:include page="footer" />
