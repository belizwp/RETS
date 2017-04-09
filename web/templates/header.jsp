<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>RETS | Real Estate Trading System</title>

        <link href="/RETS/assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="/RETS/assets/css/style.css" rel="stylesheet">

        <script src="/RETS/assets/js/jquery-3.2.0.min.js"></script>
        <script src="/RETS/assets/js/bootstrap.min.js"></script>
    </head>
    <body>

        <header>
            <nav class="navbar navbar-inverse navbar-fixed-top">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="/RETS">RETS</a>
                    </div>
                    <div id="navbar" class="collapse navbar-collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="/RETS">หน้าหลัก</a></li>
                            <li><a href="search?announce_type=1">ขาย</a></li>
                            <li><a href="search?announce_type=2">เช่า</a></li>
                            <li><a href="#">ลงประกาศ</a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="register">ลงทะเบียน</a></li>
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown">เข้าสู่ระบบ <b class="caret"></b></a>
                                <ul class="dropdown-menu" style="padding: 15px;min-width: 250px;">
                                    <li>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <form action="Login" class="form" role="form" method="post" action="login" id="login-nav">
                                                    <div class="form-group">
                                                        <input type="email" class="form-control" placeholder="อีเมล" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <input type="password" class="form-control" placeholder="รหัสผ่าน" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <button type="submit" class="btn btn-success btn-block">เข้าสู่ระบบ</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                        </ul> <!-- /nav-right -->
                    </div><!--/.nav-collapse -->
                </div>
            </nav>
        </header>
