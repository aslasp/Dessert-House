<%@ page import="service.CookieService" %>
<%@ page import="service.serviceimpl.CookieServiceImpl" %>
<%@ page import="beans.User" %>
<%@ page import="service.UserService" %>
<%@ page import="service.serviceimpl.UserServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: wn13
  Date: 2016/2/24
  Time: 0:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/navbar.css" rel="stylesheet">

    <script src="../js/jquery-1.12.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>
    <script src="../js/navbar.js"></script>

    <!--[if lt IE 9]>
    <script src="../js/html5shiv.min.js"></script>
    <script src="../js/respond.min.js"></script>
    <![endif]-->

    <title>个人资料 - Dessert House</title>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Dessert House</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/DessertHouse/user/main">在线预订</a></li>
                <li><a href="/DessertHouse/user/order">订单管理</a></li>
                <li><a href="/DessertHouse/user/vip">会员卡管理</a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span id="navbar_uname">Dropdown</span> <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li class="active"><a href="/DessertHouse/user/profile">个人资料</a></li>
                        <li><a href="/DessertHouse/user/password">修改密码</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a id="logout_btn" href="#!">退出登录</a></li>
                    </ul>
                </li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>


<style>
    .container-fluid{
        margin-left: 30%;
        margin-right: 30%;
        text-align: center;
    }
</style>
<%
    CookieService service=new CookieServiceImpl();
    String uid=service.getCookie("uid");
    UserService userService=new UserServiceImpl();
    User tmpUser=userService.findUser("uid",uid).get(0);
%>
<div class="container-fluid">
    <div style="margin: auto"><h2 class="col-xs-12">修改个人资料</h2><br></div>
    <form style="margin: auto" action="/DessertHouse/user/changeProfile" method="post">
        <input readonly type="text" name="user.uid" class="form-control" value="<%=uid%>"><br>
        <input type="text" name="user.uname" class="form-control" placeholder="请输入姓名" value="<%=tmpUser.getUname()%>"><br>
        <input type="text" name="user.uage" class="form-control" placeholder="请输入年龄" value="<%=tmpUser.getUage()%>"><br>
        <input type="text" name="user.uaddr" class="form-control" placeholder="请输入地址" value="<%=tmpUser.getUaddr()%>"><br>
        <input type="text" name="user.ucard" class="form-control" placeholder="请输入银行卡号" value="<%=tmpUser.getUcard()%>"><br>
        <button type="button submit" class="btn btn-success">提交</button>
    </form>
</div>
</body>
</html>
