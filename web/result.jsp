<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8");%>

<c:set var="p_id" value="${param.province != '' && param.province != null && param.province != 0 ? param.province : 'select province_id from province'}"/>
<c:set var="a_id" value="${param.amphur != '' && param.amphur != null && param.amphur != 0 ? param.amphur : 'select amphur_id from amphur'}"/>
<c:set var="d_id" value="${param.district != '' && param.district != null && param.district != 0 ? param.district : 'select district_id from district'}"/>
<c:set var="ann" value="${param.announce_type != '' && param.announce_type != 'ทั้งหมด' && param.announce_type != null ? '\"' += param.announce_type += '\"' : '\"ขาย\", \"เช่า\"'}"/>
<c:set var="type" value="${param.property_type != '' && param.property_type != 'ทั้งหมด' && param.property_type != null ? '\"' += param.property_type += '\"' : '\"บ้านเดี่ยว\", \"คอนโด\", \"ทาวน์เฮ้าส์\", \"ที่ดิน\", \"อพาร์ทเม้น\"'}"/>
<c:set var="min" value="${param.min_price != '' && param.min_price != null ? param.min_price : -2147483648}"/>
<c:set var="max" value="${param.max_price != '' && param.max_price != null ? param.max_price : 2147483648}"/>

<div class="row">
    <div class="list-group list-special result">

        <sql:query var="resultSet" dataSource="${dataSource}">
            SELECT Res_id, Res_name 
            FROM residential 
            natural join details
            natural join location
            where
            (province_id in (${p_id}) or province_id is null or province_id = @province_id)
            and
            (amphur_id in (${a_id}) or amphur_id is null or amphur_id = @amphur_id)
            and
            (district_id in (${d_id}) or district_id is null or district_id = @district_id)
            and
            (announce_for in (${ann}) or announce_for is null or announce_for = @announce_for)
            and
            (types in (${type}) or types is null or types = @types)
            and
            (price between ${min} and ${max})
            ORDER BY dt_modified DESC LIMIT ${ 10 * (param.page - 1)}, 10;
        </sql:query>
        <c:forEach var="res" items="${resultSet.rows}">
            <sql:query var="details" dataSource="${dataSource}">
                SELECT remark, price FROM details WHERE Res_id = ${res.Res_id};
            </sql:query>
            <c:forEach var="detail" items="${details.rows}">
                <a href="/RETS/residential?id=${res.Res_id}" class="list-group-item row-fluid">
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 nopadding">
                        <figure class="pull-left">
                            <img class="img-responsive" src="/RETS/image?type=thumbnail&id=${res.Res_id}">
                        </figure>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 ">
                        <div class="caption">
                            <h4>${res.Res_name}</h4>
                            <p>${detail.remark}</p>
                        </div>
                    </div>
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 ">
                        <h4 class="pull-right" >
                            <fmt:formatNumber value="${detail.price}"/>
                            <small> บาท </small>
                        </h4>
                    </div>
                </a>
            </c:forEach>
        </c:forEach>

    </div> <!-- /result list-->
</div> <!-- /row-->