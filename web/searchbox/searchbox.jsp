<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link href="/RETS/assets/css/bootstrap-select.min.css" rel="stylesheet">
<script src="/RETS/assets/js/bootstrap-select.min.js"></script>

<div class="searchbox">
    <form name="search_form" action="/RETS/search" method="GET">
        <div class="row-fluid">
            <div class="input-group">
                <span class="input-group-btn">

                    <select name="province" id="province-list" onChange="getAmphur(this.value);" class="selectpicker col-md-*" data-live-search="true" title="จังหวัด" data-size="10">
                        <option value='0'>ทั้งหมด</option>
                        <sql:query var="province_rows" dataSource="${dataSource}">
                            SELECT * FROM province
                        </sql:query>
                        <c:forEach var="province_row" items="${province_rows.rows}">
                            <option value="${province_row.province_id}" >${province_row.province_name}</option>
                        </c:forEach>
                        <script>
                            $("#province-list").val(${param.province});
                        </script>
                    </select>

                    <select name="amphur" id="amphur-list" onChange="getDistrict(this.value);" class="selectpicker col-md-*" title="อำเภอ/เขต" data-size="10" disabled="true">
                        <c:if test="${param.amphur != null}">
                            <sql:query var="amphur_rows" dataSource="${dataSource}">
                                SELECT * FROM amphur WHERE province_id = ?
                                <sql:param value="${param.province}"/>
                            </sql:query>

                            <option value='0'>ทั้งหมด</option>

                            <c:forEach var="amphur_row" items="${amphur_rows.rows}">
                                <option value="${amphur_row.amphur_id}" >${amphur_row.amphur_name}</option>
                            </c:forEach>
                            <script>
                                $("#amphur-list").val(${param.amphur});
                                $("#amphur-list").attr("disabled", false);
                            </script>
                        </c:if>
                    </select>

                    <select name="district" id="district-list" class="selectpicker col-md-*" title="ตำบล/แขวง" data-size="10" disabled="true">
                        <c:if test="${param.district != null}">
                            <sql:query var="district_rows" dataSource="${dataSource}">
                                SELECT * FROM district WHERE amphur_id = ?
                                <sql:param value="${param.amphur}"/>
                            </sql:query>

                            <option value='0'>ทั้งหมด</option>

                            <c:forEach var="district_row" items="${district_rows.rows}">
                                <option value="${district_row.district_id}" >${district_row.district_name}</option>
                            </c:forEach>
                            <script>
                                $("#district-list").val(${param.district});
                                $("#district-list").attr("disabled", false);
                            </script>
                        </c:if>
                    </select>

                    <select name="announce_type" id="announce-type-list" class="selectpicker col-md-*" title="ประกาศสำหรับ">
                        <option value="0">ทั้งหมด</option>
                        <option value="1">ขาย</option>
                        <option value="2">เช่า</option>
                        <c:if test="${param.announce_type != null}">
                            <script>$("#announce-type-list").val(${param.announce_type});</script>
                        </c:if>
                    </select>

                    <select name="property_type" id="property-type-list" class="selectpicker col-md-*" title="ประเภท">
                        <option value="0">ทั้งหมด</option>
                        <option value="1">บ้านเดี่ยว</option>
                        <option value="2">คอนโด</option>
                        <option value="3">ทาวน์เฮ้าส์</option>
                        <option value="4">ที่ดิน</option>
                        <option value="5">อพาร์ทเม้น</option>
                        <c:if test="${param.property_type != null}">
                            <script>$("#property-type-list").val(${param.property_type});</script>
                        </c:if>
                    </select>

                    <div id="price_dropdown" class="dropdown btn-group col-md-*" style="padding: 0;">
                        <button id="price" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
                            ราคา <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" id="price_range" style="padding: 5px;" open>
                            <li><input name="min_price" id="min_price" class="form-control" type="number" min="0" placeholder="ราคาต่ำสุด" /></li>
                            <li><input name="max_price" id="max_price" class="form-control" type="number" min="0" placeholder="ราคาสูงสุด" /></li>
                        </ul>
                    </div>

                    <button type="submit" class="btn btn-info col-md-*">ค้นหา</button>
                </span>
            </div>
        </div>
    </form> <!--/search form-->
</div> <!--/search box-->

<script>
    function getAmphur(val) {
        if (val == 0) {
            $("#amphur-list").val('').attr("disabled", true);
            $("#district-list").val('').attr("disabled", true);
            $(".selectpicker").selectpicker('refresh');
        } else {
            $.ajax({
                type: "POST",
                url: "/RETS/searchbox/get_amphur.jsp",
                data: {province_id: val},
                success: function (data) {
                    $("#amphur-list").html(data).attr("disabled", false);
                    $("#district-list").val('').attr("disabled", true);
                    $(".selectpicker").selectpicker('refresh');
                }
            });
        }
    }

    function getDistrict(val) {
        if (val == 0) {
            $("#district-list").val('').attr("disabled", true);
            $(".selectpicker").selectpicker('refresh');
        } else {
            $.ajax({
                type: "POST",
                url: "/RETS/searchbox/get_district.jsp",
                data: {amphur_id: val},
                success: function (data) {
                    $("#district-list").html(data).attr("disabled", false);
                    $(".selectpicker").selectpicker('refresh');
                }
            });
        }
    }

    function addCommas(num) {
        return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

    function setPriceDesc(min, max) {
        if (!isNaN(min) && !isNaN(max)) {
            if (min <= max) {
                $("#price").text(addCommas(min) + ' ถึง ' + addCommas(max) + ' บาท');
            } else {
                $('#min_price').val(NaN);
                $('#max_price').val(NaN);
                $("#price").text('ไม่ระบุราคา');
            }
        } else if (!isNaN(min) && isNaN(max)) {
            $("#price").text('ตั้งแต่ ' + addCommas(min) + ' บาท');
        } else if (isNaN(min) && !isNaN(max)) {
            $("#price").text('สูงสุด ' + addCommas(max) + ' บาท');
        } else {
            $('#min_price').val(NaN);
            $('#max_price').val(NaN);
            $("#price").text('ไม่ระบุราคา');
        }
    }

    $(document).ready(function () {
        // default price
        $('#min_price').val(${param.min_price});
        $('#max_price').val(${param.max_price});
        setPriceDesc(parseInt(${param.min_price}), parseInt(${param.max_price}));

        // refresh price
        $("#price_dropdown").on("hide.bs.dropdown", function () {
            setPriceDesc(parseInt($('#min_price').val()), parseInt($('#max_price').val()));
        });
    });
</script>