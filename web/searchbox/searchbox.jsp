<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link href="/RETS/assets/css/bootstrap-select.min.css" rel="stylesheet">
<script src="/RETS/assets/js/bootstrap-select.min.js"></script>

<div class="searchbox">
    <form name="search_form" action="search" method="GET">
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
                    </select>

                    <select name="amphur" id="amphur-list" onChange="getDistrict(this.value);" class="selectpicker col-md-*" title="อำเภอ/เขต" data-size="10" disabled="true">
                    </select>

                    <select name="district" id="district-list" class="selectpicker col-md-*" title="ตำบล/แขวง" data-size="10" disabled="true">
                    </select>

                    <select name="announce_type" id="announce-type-list" class="selectpicker col-md-*" title="ประกาศสำหรับ">
                        <option value="0">ทั้งหมด</option>
                        <option value="1">ขาย</option>
                        <option value="2">เช่า</option>
                    </select>

                    <select name="property_type" id="property-type-list" class="selectpicker col-md-*" title="ประเภท">
                        <option value="0">ทั้งหมด</option>
                        <option value="1">บ้านเดี่ยว</option>
                        <option value="2">คอนโด</option>
                        <option value="3">ทาวน์เฮ้าส์</option>
                        <option value="4">ที่ดิน</option>
                        <option value="5">อพาร์ทเม้น</option>
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
                data: {province_id: val, amphur: '${param.amphur}'},
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
                data: {amphur_id: val, district: '${param.district}'},
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

    function fecthOption() {
        if (${param.province != null}) {
            getAmphur('${param.province}');
        }

        if (${param.amphur != null}) {
            getDistrict('${param.amphur}');
        }

    }

    $(document).ready(function () {
        fecthOption();

        // default selected
        $("#province-list").val(${param.province});
        $("#announce-type-list").val(${param.announce_type});
        $("#property-type-list").val(${param.property_type});

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