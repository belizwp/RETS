<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header" />

<div class="container">
    <form class="login form-horizontal" action="/RETS/Login" method="POST">
        <div class="panel panel-danger">
            <div class="panel-heading">
                <h2>เกิดข้อผิดพลาด!</h2>
            </div>
            <div class="panel-body">
                <div class="text-center">
                    กำลังดำการแก้ไข ขออภัยในความไม่สะดวก
                </div>
            </div>
        </div>        
    </form>
</div>

<jsp:include page="footer" />
