<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.Store" %>
<%@ page import="dao.StoreDao" %>
<%@ page import="factory.DaoFactory" %><%--
  Created by IntelliJ IDEA.
  User: wn13
  Date: 2016/2/14
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>在线预订 - Dessert House</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/navbar.css" rel="stylesheet">

    <script src="../js/jquery-1.12.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>
    <script src="../js/navbar.js"></script>
    <script src="../js/user-main.js"></script>


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
                <li class="active"><a href="/DessertHouse/user/main">在线预订</a></li>
                <li><a href="/DessertHouse/user/order">订单管理</a></li>
                <li><a href="/DessertHouse/user/vip">会员卡管理</a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span id="navbar_uname">Dropdown</span> <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/DessertHouse/user/profile">个人资料</a></li>
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
        margin-left: 15%;
        margin-right: 15%;
    }
    .discount{
        color:orangered;
        font-size: large;
    }
</style>
<%
    StoreDao storeDao= DaoFactory.getStoreDao();
    ArrayList<Store> slist=storeDao.getAllStores();
%>
<div class="container-fluid row">
    <div class="col-xs-12 col-md-12"><h2>在线预订</h2><br></div>
    <div class="list-group col-xs-12 col-md-3">
        <%
            for(int i=0;i<slist.size();i++){
                Store tmpStore=slist.get(i);
        %>
        <a href="#!" id="slist_item<%=i%>" class="list-group-item"><%=tmpStore.getSname()%></a>
        <%}%>
    </div>
</div>
</body>
</html>
