<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<sql:query var="province_rows" dataSource="${applicationScope.dataSource}">
    select * from province
</sql:query>

<link href="${SITE_URL}/assets/css/bootstrap-select.min.css" rel="stylesheet">
<script src="${SITE_URL}/assets/js/bootstrap-select.min.js"></script>

<div class="searchbox">
    <form name="search_form" action="#" method="POST">
        <div class="row">

            <select name="province" id="province-list" onChange="getAmphur(this.value);" class="selectpicker col-md-\*" data-live-search="true" title="จังหวัด" data-size="10">
                <option value='0'>ทั้งหมด</option>
                <c:forEach var="province_row" items="${province_rows.rows}">
                    <option value="${province_row.province_id}" >${province_row.province_name}</option>
                </c:forEach>
            </select>

            <select name="amphur" id="amphur-list" onChange="getDistrict(this.value);" class="selectpicker col-md-\*" title="อำเภอ/เขต" data-size="10" disabled="true">
            </select>

            <select name="district" id="district-list" class="selectpicker col-md-\*" title="ตำบล/แขวง" data-size="10" disabled="true">
            </select>

            <select class="selectpicker col-md-\*" title="ประกาศสำหรับ">
                <option value="0">ทั้งหมด</option>
                <option value="1">ขาย</option>
                <option value="2">เช่า</option>
            </select>

            <select class="selectpicker col-md-\*" title="ประเภท">
                <option value="0">ทั้งหมด</option>
                <option value="1">บ้านเดี่ยว</option>
                <option value="2">คอนโด</option>
                <option value="3">ทาวน์เฮ้าส์</option>
                <option value="4">ที่ดิน</option>
                <option value="5">อพาร์ทเม้น</option>
            </select>


            <select class="selectpicker col-md-\*" title="ราคา">
                <option>ฟรี</option>
            </select>

            <button type="submit" class="btn btn-info col-md-\*">ค้นหา</button>
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
                url: "${SITE_URL}/searchbox/get_amphur.jsp",
                data: 'province_id=' + val,
                success: function (data) {
                    $("#amphur-list").html(data);
                    $("#amphur-list").attr("disabled", false);
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
                url: "${SITE_URL}/searchbox/get_district.jsp",
                data: 'amphur_id=' + val,
                success: function (data) {
                    $("#district-list").html(data);
                    $("#district-list").attr("disabled", false);
                    $(".selectpicker").selectpicker('refresh');
                }
            });
        }
    }
</script>