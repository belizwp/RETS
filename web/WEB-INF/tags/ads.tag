<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="type"%>

<%-- any content can be specified here e.g.: --%>
<h4><b>ประชาสัมพันธ์</b></h4>

<div class="row">

    <c:choose>
        <c:when test="${type == 'main'}">
            <c:forEach begin="1" end="4">
                
                <div class="col-xs-6 col-sm-6 col-md-3 col-lg-3">
                    <a class="thumbnail">
                        <img src="http://placehold.it/350x250" alt="ALT NAME">
                        <div class="caption">
                            <h4>Residential</h4>
                            <p>This is a short description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                        </div>
                    </a>
                </div>
                
            </c:forEach>
        </c:when>
        <c:when test="${type == 'sidebar'}">
            <c:forEach begin="1" end="2">
                
                <div class="col-xs-6 col-sm-6 col-md-12 col-lg-12">
                    <a class="thumbnail">
                        <img src="http://placehold.it/350x200" alt="ALT NAME">
                        <div class="caption">
                            <h4>Residential</h4>
                            <p>This is a short description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                        </div>
                    </a>
                </div>
                
            </c:forEach>
        </c:when>
    </c:choose>

</div>