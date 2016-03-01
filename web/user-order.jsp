<%@ page import="service.SaleService" %>
<%@ page import="service.serviceimpl.SaleServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.Order" %>
<%@ page import="service.CookieService" %>
<%@ page import="service.serviceimpl.CookieServiceImpl" %>
<%@ page import="beans.User" %>
<%@ page import="service.serviceimpl.UserServiceImpl" %>
<%@ page import="service.UserService" %><%--
  Created by IntelliJ IDEA.
  User: wn13
  Date: 2016/2/24
  Time: 0:38
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

    <title>订单管理 - Dessert House</title>
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
            <a class="navbar-brand" href="#">Dessert House</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/DessertHouse/user/main">在线预订</a></li>
                <li class="active"><a href="/DessertHouse/user/order">订单管理</a></li>
                <li><a href="/DessertHouse/user/vip">会员卡管理</a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"><span id="navbar_uname">Dropdown</span> <span class="caret"></span></a>
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
<%
    CookieService service = new CookieServiceImpl();
    String uid = service.getCookie("uid");
    SaleService saleService = new SaleServiceImpl();
    ArrayList<Order> olist = saleService.getAllOrders(Integer.parseInt(uid));
    UserService userService=new UserServiceImpl();
    User tmpUser=userService.findUser("uid",uid).get(0);
%>
<style>
    .container-fluid {
        margin-left: 5%;
        margin-right: 5%;
    }

    .del_name {
        color: orangered;
        font-size: large;
    }
</style>
<div class="container-fluid row">
    <div class="col-xs-12 col-md-12"><h2>订单管理</h2><br></div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>订单ID</th>
            <th>日期</th>
            <th>状态</th>
            <th>门店</th>
            <th>商品</th>
            <th>单价</th>
            <th>数量</th>
            <th>折后总价</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <%
            for(int i=0;i<olist.size();i++){
                Order tmp=olist.get(i);
        %>
        <tr>
            <td><%=tmp.getOid()%></td>
            <td><%=tmp.getOtime()%></td>
            <td><%
                switch (tmp.getOtype()){
                    case 0:out.print("订单执行中");break;
                    case 1:out.print("购买完成");break;
                    case 2:out.print("订单完成");break;
                    case 3:out.print("订单取消");break;
                    default:out.print("unknown");
                }
            %></td>
            <td><%=tmp.getSname()%></td>
            <td><%=tmp.getDname()%></td>
            <td><%=tmp.getOprice()%></td>
            <td><%=tmp.getOnum()%></td>
            <td><%=tmp.getOtotal()%></td>
            <td><%
                if(tmp.getOtype()==0){%>
                <a href="#!" data-toggle="modal" data-target="#cancel_modal<%=i%>">取消订单</a>
                <div class="modal fade" id="cancel_modal<%=i%>" tabindex="-1" role="dialog" aria-labelledby="退订Modal">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="modal-title">退订确认</h3>
                            </div>
                            <div class="modal-body">
                                <p>确认要取消订单<span class="del_name"><%=tmp.getOid()%></span> 吗？</p>
                            </div>
                            <form action="/DessertHouse/user/cancelOrder" method="post">
                                <input hidden name="order.oid" value="<%=tmp.getOid()%>"/>
                                <input hidden name="order.ototal" value="<%=tmp.getOtotal()%>"/>
                                <input hidden name="order.uid" value="<%=tmp.getUid()%>"/>
                                <input hidden name="ubalance" value="<%=tmpUser.getUbalance()%>"/>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                                    <button type="button submit" class="btn btn-danger">退订</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <%}%></td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
</html>
