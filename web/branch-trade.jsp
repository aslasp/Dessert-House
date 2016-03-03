<%@ page import="beans.Commodity" %>
<%@ page import="beans.Store" %>
<%@ page import="beans.User" %>
<%@ page import="dao.UserDao" %>
<%@ page import="factory.DaoFactory" %>
<%@ page import="service.CookieService" %>
<%@ page import="service.SaleService" %>
<%@ page import="service.serviceimpl.CookieServiceImpl" %>
<%@ page import="service.serviceimpl.SaleServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.struts2.json.JSONUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: wn13
  Date: 2016/3/2
  Time: 0:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CookieService cookieService = new CookieServiceImpl();
    String ename = cookieService.getCookie("ename");
    SaleService saleService = new SaleServiceImpl();
    Store store = saleService.findStoreOfBranch(ename);
    ArrayList<Commodity> clist = saleService.getCommoditiesInStore(store.getSname());
    UserDao userDao= DaoFactory.getUserDao();
    ArrayList<User> ulist=userDao.getAllValidUsers();
    String uJsonStr=JSONUtil.serialize(ulist);
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/navbar-branch.css" rel="stylesheet">

    <script src="../js/jquery-1.12.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>
    <script src="../js/navbar-branch.js"></script>
    <script>
        var ulist = <%=uJsonStr%>;
    </script>

    <!--[if lt IE 9]>
    <script src="../js/html5shiv.min.js"></script>
    <script src="../js/respond.min.js"></script>
    <![endif]-->
    <title>销售 - Dessert House</title>
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
                <li class="active"><a href="/DessertHouse/branch/trade">销售</a></li>
                <li><a href="/DessertHouse/branch/customer">客户信息</a></li>

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
</style>




<div class="container-fluid row">
    <div class="col-xs-12 col-md-12">
        <h2><%=store.getSname()%></h2>
        <h4><%=store.getSaddr()%></h4><br>
    </div>

    <table class='table table-hover'>
        <thead>
        <tr>
            <th>图片</th>
            <th>名称</th>
            <th>简介</th>
            <th>单价</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (int i=0;i<clist.size();i++){
                Commodity tmp=clist.get(i);
                String imgPath="/DessertHouse/dhImg/default.jpg";
                if(tmp.getHasImg()==1){
                    imgPath="/DessertHouse/dhImg/"+tmp.getDname()+".jpg";
                }
        %>
        <tr>
            <td><img src="<%=imgPath%>"></td>
            <td><%=tmp.getDname()%></td>
            <td><%=tmp.getdIntro()%></td>
            <td><%=tmp.getPrice()%></td>
            <td>
                <button data-toggle='modal' data-target='#sell_modal<%=i%>' class='btn btn-primary'>销售</button>
                <div class='modal fade' id='sell_modal<%=i%>' tabindex='-1' role='dialog' aria-labelledby='销售Modal'>
                    <div class='modal-dialog' role='document'>
                        <div class='modal-content'>
                            <div class='modal-header'>
                                <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>
                                <h3 class='modal-title'>销售</h3>
                            </div>
                            <form action='/DessertHouse/branch/sell' method='post'>
                                <div class='modal-body row'>
                                    <div class='form-group col-md-6'>会员卡号：<input type="text" index="<%=i%>" class='form-control uid_fld' name='order.uid' value='0'></div>
                                    <div class='form-group col-md-6'>商品名：<input readonly type="text" class='form-control' name='order.dname' value='<%=tmp.getDname()%>'></div>
                                    <input hidden name='order.sname' value='<%=tmp.getSname()%>'>
                                    <input hidden id="ubinput<%=i%>" name='ubalance'>
                                    <div class='form-group col-md-4'>单价：<input readonly id="price<%=i%>" type="text" class='form-control' name='order.oprice' value='<%=tmp.getPrice()%>'></div>
                                    <div class='form-group col-md-4'>数量（剩余<%=tmp.getRemainNum()%>个）：<input type="text" index="<%=i%>" class='form-control onum_fld' name='order.onum'></div>
                                    <div class='form-group col-md-4'>折扣系数：<input readonly id="discount<%=i%>" type="text" class='form-control'></div>
                                    <div class='form-group col-md-8'>折前总价：<input readonly id="btotal<%=i%>" type="text" class='form-control'></div>
                                    <div class='form-group col-md-8'>折后总价（您的余额：<span id="ubspan<%=i%>"></span>）：<input readonly id='ctotal<%=i%>' name='order.ototal' class='form-control'></div>
                                </div>
                                <div class='modal-footer'>
                                    <button type='button' class='btn btn-default' data-dismiss='modal'>返回</button>
                                    <button id="sellBtn<%=i%>" type='button submit' class='btn btn-success'>购买</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>


</div>
<script src="../js/branch-trade.js"></script>
</body>
</html>
