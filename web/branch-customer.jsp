<%@ page import="service.UserService" %>
<%@ page import="dao.UserDao" %>
<%@ page import="factory.DaoFactory" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.User" %>
<%@ page import="beans.Order" %>
<%@ page import="dao.SaleDao" %>
<%@ page import="org.apache.struts2.json.JSONUtil" %><%--
  Created by IntelliJ IDEA.
  User: wn13
  Date: 2016/3/2
  Time: 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserDao userDao = DaoFactory.getUserDao();
    SaleDao saleDao=DaoFactory.getSaleDao();
    ArrayList<User> ulist = userDao.getAllValidUsers();
    ArrayList<Order> slist=saleDao.getAllOrdersWithoutUid();
    String userJsonStr=JSONUtil.serialize(ulist);
    String orderJsonStr=JSONUtil.serialize(slist);
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/navbar-branch.css" rel="stylesheet">

    <script>
        var ulist=<%=userJsonStr%>;
        var slist=<%=orderJsonStr%>;
    </script>
    <script src="../js/jquery-1.12.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>
    <script src="../js/navbar-branch.js"></script>
    <script src="../js/branch-customer.js"></script>


    <!--[if lt IE 9]>
    <script src="../js/html5shiv.min.js"></script>
    <script src="../js/respond.min.js"></script>
    <![endif]-->
    <title>客户信息 - Dessert House</title>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#!">Dessert House</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/DessertHouse/branch/trade">销售</a></li>
                <li class="active"><a href="/DessertHouse/branch/customer">客户信息</a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"><span id="navbar_ename">Dropdown</span> <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a id="logout_btn" href="#!">退出登录</a></li>
                    </ul>
                </li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<style>
    .container-fluid {
        margin-left: 15%;
        margin-right: 15%;
    }
    #search_div {
        margin-left: 15%;
        margin-right: 15%;
        margin-bottom:4%;
        text-align: center;
    }
</style>
<div class="container-fluid row">
    <div id="search_div"><input id="search_fld" type="search" class="form-control input-lg" placeholder="输入会员卡号"></div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">卡基本信息</h3>
        </div>
        <div class="panel-body row" id="card_content">
            无效卡
        </div>
    </div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>订单ID</th>
            <th>日期</th>
            <th>状态</th>
            <th>门店</th>
            <th>商品名</th>
            <th>单价</th>
            <th>数量</th>
            <th>总价</th>
        </tr>
        </thead>
        <tbody id="order_tbody">

        </tbody>
    </table>
</div>
</body>
</html>
