<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="templates/header.jsp" />

<div class="container">
    <form class="login form-horizontal" action="Login" method="POST">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2>เข้าสู่ระบบ</h2>
            </div>
            <div class="panel-body">
                <div class="row">
                    <fieldset>
                        <div class="form-group">
                            <div class="col-md-4 col-md-offset-4">
                                <input type="email" class="form-control" placeholder="อีเมล์" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4 col-md-offset-4">
                                <input type="password" class="form-control" placeholder="รหัสผ่าน" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4 col-md-offset-4">
                                <button type="submit" class="btn btn-success btn-block">เข้าสู่ระบบ</button>
                            </div>
                        </div>
                    </fieldset>
                </div>
            </div>
        </div>        
    </form>
</div>

<jsp:include page="templates/footer.jsp" />