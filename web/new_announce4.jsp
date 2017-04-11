<%@taglib prefix="rets" tagdir="/WEB-INF/tags/"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/header" />

<div class="container">
    <form class="create-info form-horizontal" action="/RETS/NewAnnounce" method="POST">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2>ลงประกาศใหม่</h2>
            </div>

            <rets:tabnav/>

            <div class="panel-body">
                <div class="row">
                    <fieldset>

                        <legend>ตัวอย่างประกาศ</legend>
                        <div class="row">
                            <div class="list-group list-special result">
                                <a href="#" class="list-group-item row-fluid">
                                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 nopadding">
                                        <figure class="pull-left">
                                            <img class="img-responsive" src="http://placehold.it/200x200">
                                        </figure>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 ">
                                        <div class="caption">
                                            <h4>Residential</h4>
                                            <p>This is a short description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                        </div>
                                    </div>
                                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 ">
                                        <h4 class="pull-right"> 14,200,000 <small> บาท </small></h4>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <br>

                    </fieldset>
                </div>
            </div>       

            <div class="panel-footer text-center">
                <input type="submit" name="submit" class="btn btn-default" value="กลับ" />
                <!-- <input type="button" class="btn btn-success value="บันทึก" /> -->
                <input type="submit" name="submit" class="btn btn-danger" value="ลงประกาศ" />

                <input type="hidden" name="process" class="btn btn-primary" value="summary"/>
                <input type="hidden" name="process_id" class="btn btn-primary" value="${param.process_id}"/>
            </div>  

        </div>
    </form>
</div>

<jsp:include page="/footer" />