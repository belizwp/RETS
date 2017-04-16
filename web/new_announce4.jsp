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
                <a type="button" class="btn btn-default">รายละเอียดเพิ่มเติม</a>
                <span class="glyphicon glyphicon-menu-right"></span>
                <a type="button" class="btn btn-default">รูปภาพ</a>
                <span class="glyphicon glyphicon-menu-right"></span>
                <a type="button" class="btn btn-primary">สรุป</a>
                <h6 class="pull-right" style="color: red">* ข้อมูลที่จำเป็นต้องใช้</h6>
            </div>

            <div class="panel-body">
                <div class="row">
                    <fieldset>

                        <legend>ตัวอย่างประกาศ</legend>
                        <div class="row">
                            <div class="list-group list-special result">
                                <a href="#" class="list-group-item row-fluid">
                                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 nopadding">
                                        <figure class="pull-left">
                                            <c:choose>
                                                <c:when test="${ann.files.size() > 0}">
                                                    <img class="img-responsive" src="/RETS/image/?process_id=${param.process_id}&type=thumbnail">
                                                </c:when>
                                                <c:otherwise>
                                                    <img class="img-responsive" src="/RETS/assets/img/no-photo-placeholder.png">
                                                </c:otherwise>
                                            </c:choose>
                                        </figure>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 ">
                                        <div class="caption">
                                            <h4>${ann.title}</h4>
                                            <p>${ann.detail}</p>
                                        </div>
                                    </div>
                                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 ">
                                        <h4 class="pull-right" id="price">
                                            <c:choose>
                                                <c:when test="${ann.price != null}">
                                                    ${ann.priceFormat} <small> บาท </small>
                                                </c:when>
                                                <c:otherwise>
                                                    ไม่ระบุราคา
                                                </c:otherwise>
                                            </c:choose>
                                        </h4>
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