<%@ page import="service.HeadService" %>
<%@ page import="service.serviceimpl.HeadServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.Plan" %><%--
  Created by IntelliJ IDEA.
  User: wn13
  Date: 2016/2/19
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/navbar-head.css" rel="stylesheet">

    <script src="../js/jquery-1.12.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>
    <script src="../js/navbar-head.js"></script>

    <!--[if lt IE 9]>
    <script src="../js/html5shiv.min.js"></script>
    <script src="../js/respond.min.js"></script>
    <![endif]-->
    <title>过往计划 - Dessert House</title>
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
                <li><a href="/DessertHouse/head/plan">销售计划</a></li>
                <li class="active"><a href="/DessertHouse/head/history">过往计划</a></li>
                <li><a href="/DessertHouse/head/dessert">商品管理</a></li>
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
<%  HeadService service=new HeadServiceImpl();
    ArrayList<Plan> list=service.getAllHistoryPlans();
%>
<style>
    .container-fluid{
        margin-left: 15%;
        margin-right: 15%;
    }
</style>

<div class="container-fluid row">
    <h2 class="col-xs-12 col-md-8">历史计划</h2>
    <table class="table table-hover">
        <thead><tr>
            <th>ID</th>
            <th>起始日期</th>
            <th>商品名称</th>
            <th>门店名称</th>
            <th>单价</th>
            <th>每日限量</th>
            <th>状态</th>
        </tr></thead>
        <tbody>
            <% for(int i=0;i<list.size();i++){
            Plan tmpPlan=list.get(i);
        %>
        <tr>
            <td><%=tmpPlan.getPid()%></td>
            <td><%=tmpPlan.getPtime()%></td>
            <td><%=tmpPlan.getDname()%></td>
            <td><%=tmpPlan.getSname()%></td>
            <td><%=tmpPlan.getPrice()%></td>
            <td><%=tmpPlan.getNum_limit()%></td>
            <td><%  switch (tmpPlan.getPstatus()){
                case 0:out.print("待审批");break;
                case 1:out.print("未通过审批");break;
                case 2:out.print("通过审批");break;
                default:out.print("未知");
            }
            %></td>
        </tr>
            <%}%>
        </tbody>
    </table>
</div>

</body>
</html>
