<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header" />

<c:set var="res" value="${requestScope.residential}" />
<c:set var="emp" value="${requestScope.emp_info}" />

<sql:query var="images" dataSource="${dataSource}">
    SELECT image_id from `image of detail` WHERE Res_id = ?;
    <sql:param value="${res.id}"/>
</sql:query>

<div class="container" style="margin-top: 20px;">
    <div class="row">
        <div class="col-md-9"> <!-- residential detail -->
            <div class="panel panel-default">
                <div class="panel-body" style="padding-top: 0px;">

                    <c:if test="${images.getRowCount() > 0}">
                        <div class="row">
                            <div data-ride="carousel" id="myCarousel" class="carousel slide">
                                <div class="carousel-outer">
                                    <div role="listbox" class="carousel-inner">
                                        <c:forEach var="image" items="${images.rows}" varStatus="count">
                                            <div class='item ${count.index == 0 ? "active" : ""}'>
                                                <img class="img-responsive center-block" src='/RETS/image?type=gallery&id=${image.image_id}'/>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <a data-slide="prev" role="button" class="left carousel-control" href="#myCarousel">
                                        <span aria-hidden="true" class="glyphicon glyphicon-chevron-left"></span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                    <a data-slide="next" role="button" class="right carousel-control" href="#myCarousel">
                                        <span aria-hidden="true" class="glyphicon glyphicon-chevron-right"></span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </div>
                                <!-- thumb navigation carousel -->
                                <ol id="slider-thumbs" class="carousel-indicators horizontal-scrollbar hidden-xs">
                                    <c:forEach var="image" items="${images.rows}" varStatus="count">
                                        <li data-target='#myCarousel' data-slide-to='${count.index}'  class="active">
                                            <img src='/RETS/image?type=nav&id=${image.image_id}' />
                                        </li>
                                    </c:forEach>
                                </ol>
                            </div> <!-- /carousel -->
                        </div> <!-- /row -->
                    </c:if>

                    <div id="property-info">
                        <h3> <fmt:formatNumber value="${res.price}"/> <small> บาท </small>
                            <span style="font-size: 12px; color: #BBBBBB; float: right;">แก้ไขล่าสุดเมื่อ ${res.dt_time}</span>
                        </h3>
                        ${res.provinceName}  ${res.amphurName}  ${res.districtName}  ${res.address}
                        <hr>
                        ประเภท : ${res.propType} ${res.type} <br>
                        รหัสประกาศ : ${res.id} <br>
                        <br>

                        <p>
                            <b>รายละเอียด</b><br>
                            ${res.detail}
                        </p>
                        <br>
                        <p>
                            <b>สิ่งอำนวยความสะดวก</b><br>
                            ${res.facilities}
                        </p>
                        <br>

                    </div><!-- /res-info -->


                </div> <!-- /body -->
            </div>  <!-- /panel -->

            <div class="panel panel-default hidden-md hidden-lg">
                <div class="panel-body">
                    <form action="/RETS/Contact" class="contact form-horizontal">
                        <input type="hidden" name="id" value="${res.id}">
                        <input type="hidden" name="emp_num" value="${emp.number}">
                        <h4>${emp.fname} ${emp.lname}</h4>
                        <p class="pull-right"><small>โทร.</small> ${emp.phone}</p>
                        <br>
                        <input name="fname" class="contact-fname form-control" type="text" placeholder="ชื่อ" required>
                        <input name="lname" class="contact-lname form-control" type="text" placeholder="นามสกุล" required>
                        <input name="phone" class="contact-tel form-control" type="text" maxlength="10" placeholder="หมายเลขโทรศัพท์" required>
                        <input name="email" class="contact-email form-control" type="email" placeholder="อีเมล" >
                        <textarea name="desc" class="contact-ms form-control">I’m interested in this property. Please contact me, thanks!</textarea>
                        <input type="submit" class="btn btn-danger form-control" value="ส่งข้อความถึงตัวแทน" >
                    </form>
                </div>
            </div>

        </div>  <!-- /col -->
        <div class="col-md-3"> <!-- contact form -->
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action="/RETS/Contact" class="contact form-horizontal">
                        <input type="hidden" name="id" value="${res.id}">
                        <input type="hidden" name="emp_num" value="${emp.number}">
                        <h4>${emp.fname} ${emp.lname}</h4>
                        <p class="pull-right"><small>โทร.</small> ${emp.phone}</p>
                        <br>
                        <input name="fname" class="contact-fname form-control" type="text" placeholder="ชื่อ" required>
                        <input name="lname" class="contact-lname form-control" type="text" placeholder="นามสกุล" required>
                        <input name="phone" class="contact-tel form-control" type="text" maxlength="10" placeholder="หมายเลขโทรศัพท์" required>
                        <input name="email" class="contact-email form-control" type="email" placeholder="อีเมล" >
                        <textarea name="desc" class="contact-ms form-control" required>I’m interested in this property. Please contact me, thanks!</textarea>
                        <input type="submit" class="btn btn-danger form-control" value="ส่งข้อความถึงตัวแทน" >
                    </form>
                </div>
            </div>
        </div>  <!-- /contact form -->
    </div>
</div> <!-- /content -->

<script>
    $(document).ready(function () {
        $(".contact-fname").keyup(function () {
            $(".contact-fname").val($(this).val());
        });
        $(".contact-lname").keyup(function () {
            $(".contact-lname").val($(this).val());
        });
        $(".contact-tel").keyup(function () {
            $(".contact-tel").val($(this).val());
        });
        $(".contact-email").keyup(function () {
            $(".contact-email").val($(this).val());
        });
        $(".contact-ms").keyup(function () {
            $(".contact-ms").val($(this).val());
        });
    });
</script>

<jsp:include page="footer" />
