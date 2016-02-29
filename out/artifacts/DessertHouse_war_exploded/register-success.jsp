<%--
  Created by IntelliJ IDEA.
  User: wn13
  Date: 2016/2/14
  Time: 0:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>注册成功</title>

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
                        <li class="dropdown-header">账户管理</li>
                        <li><a href="/DessertHouse/user/profile">个人资料</a></li>
                        <li><a href="/DessertHouse/user/password">修改密码</a></li>
                        <li><a href="/DessertHouse/user/creditcard">银行卡变更</a></li>
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
        margin-left: 15%;
        margin-right: 15%;
    }
    h3{
        text-align: center;
        margin-bottom: 40px;
    }
    p{
        margin-left: 20%;
        margin-right:20%;
        text-indent:2em
    }
    #uid_span{
        color:orangered;
        font-size: large;
    }
    #recharge_btn{
        margin-top: 40px;
        text-align: center;
    }
    #recharge_btn a{
        margin-left: auto;
        margin-right: auto;
    }
</style>
<div class="container-fluid">
    <h3>注册成功</h3>
    <p>亲爱的<s:property value="user.uname"></s:property>，恭喜您注册成功，请牢记您的会员卡号码和密码作为登录凭证。</p>
    <p>您的会员卡处于未激活状态，不能享受折扣优惠、购物积分等会员特权，请您充值200元（以上）激活会员卡。</p>
    <p>您的会员卡号是：<span id="uid_span"><s:property value="user.uid"></s:property></span></p>
    <div id="recharge_btn"><a href="/DessertHouse/user/vip" class="btn btn-primary">去充值</a></div>
</div>
</body>
</html>
