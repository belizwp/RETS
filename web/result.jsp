<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="list-group list-special result">

        <!-- 1. query for result set
             2. loop for show each result-->
        <c:forEach begin="1" end="${param.page}" varStatus="count">
            <a class="list-group-item row-fluid">
                <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 nopadding">
                    <figure class="pull-left">
                        <img class="img-responsive" src="http://placehold.it/200x200">
                    </figure>
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 ">
                    <div class="caption">
                        <h4>Residential Page ${count.index}</h4>
                        <p>This is a short description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                    </div>
                </div>
                <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 ">
                    <h4 class="pull-right"> 14,200,000 <small> บาท </small></h4>
                </div>
            </a>
        </c:forEach>

    </div> <!-- /result list-->
</div> <!-- /row-->