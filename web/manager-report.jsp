<%@ page import="service.ManagerService" %>
<%@ page import="service.serviceimpl.ManagerServiceImpl" %>
<%@ page import="org.apache.struts2.json.JSONUtil" %>
<%@ page import="java.util.Map" %>
<%@ page import="dao.StoreDao" %>
<%@ page import="factory.DaoFactory" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.Store" %>
<%@ page import="dao.DessertDao" %>
<%@ page import="beans.Dessert" %><%--
  Created by IntelliJ IDEA.
  User: wn13
  Date: 2016/2/23
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DessertDao dessertDao=DaoFactory.getDessertDao();
    String dlist=JSONUtil.serialize(dessertDao.getAllDesserts());
    StoreDao storeDao= DaoFactory.getStoreDao();
    ArrayList<Store> slist=storeDao.getAllStores();
    ManagerService managerService=new ManagerServiceImpl();
    Map<String,Object> map=managerService.getStatsMap();
    String statsJsonStr=JSONUtil.serialize(map);
    ArrayList<String> monthName=(ArrayList<String>)map.get("sixMonthName");
%>
<html>
<head>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/navbar-manager.css" rel="stylesheet">

    <script>
        var stats=<%=statsJsonStr%>;
        var dlist=<%=dlist%>;
    </script>
    <script src="../js/jquery-1.12.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>
    <script src="../js/navbar-manager.js"></script>
    <script src="../js/echarts.min.js"></script>
    <script src="../js/manager-report.js"></script>

    <!--[if lt IE 9]>
    <script src="../js/html5shiv.min.js"></script>
    <script src="../js/respond.min.js"></script>
    <![endif]-->

    <title>业务统计 - Dessert House</title>
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
            <a class="navbar-brand" href="#!">Dessert House</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/DessertHouse/manager/report">业务统计</a></li>
                <li><a href="/DessertHouse/manager/approval">计划审批</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span id="navbar_ename">Dropdown</span> <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a id="logout_btn" href="#!">退出登录</a></li>
                    </ul>
                </li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<style>
    .container-fluid{
        margin-left: 10%;
        margin-right: 10%;
    }
    .tab-content{
        margin-left: 5%;
        margin-right: 5%;
        text-align: center;
    }
</style>
<div class="container-fluid">
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="#vip_div" data-toggle="tab">会员情况</a></li>
        <li role="presentation"><a href="#store_div" data-toggle="tab">门店情况</a></li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane fade in active row" id="vip_div">
            <h4 style="margin-top: 15px;">当前会员总数：<%=map.get("userNumAll")%></h4>
            <div class="col-md-12" id="ageChart" style="width: 420px;height:250px;"></div>
            <div id="sexChart" class="col-md-12" style="width: 420px;height:250px;"></div>
            <div id="ustatusChart" class="col-md-12" style="width: 420px;height:250px;"></div>
            <div id="avgMoneyChart" class="col-md-12" style="width: 900px;height:400px;"></div>
        </div>
        <div class="tab-pane fade row" id="store_div">
            <div style="margin-bottom: 3%;margin-top: 3%;margin-left: 10%;margin-right: 10%"><select id="store_select" class="form-control input-lg">
                <option>请选择门店</option>
                <%
                    for (int i=0;i<slist.size();i++){
                %>
                <option><%=slist.get(i).getSname()%></option>
                <%}%>

            </select></div>
            <div id="storeChart" class="col-md-12" style="width: 900px;height:450px;"></div>
                <h3 class="col-md-12">热门商品排行榜</h3>
            <div class="col-md-2"><select id="top3_month_select" class="form-control">
                    <%
                        for(int i=0;i<monthName.size();i++){
                    %>
                    <option><%=monthName.get(i)%></option>
                    <%}%>
                </select></div>
                <table class="table table-hover col-md-12">
                    <thead><tr>
                        <th>图片</th>
                        <th>商品名</th>
                        <th>简介</th>
                    </tr></thead>
                    <tbody id="top3_table">

                    </tbody>
                </table>
        </div>
    </div>
</div>
</body>
</html>
