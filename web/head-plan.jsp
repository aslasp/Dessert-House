<%@ page import="service.HeadService" %>
<%@ page import="service.serviceimpl.HeadServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.Plan" %>
<%@ page import="beans.Dessert" %>
<%@ page import="dao.StoreDao" %>
<%@ page import="factory.DaoFactory" %>
<%@ page import="beans.Store" %><%--
  Created by IntelliJ IDEA.
  User: wn13
  Date: 2016/2/17
  Time: 19:38
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
    <title>销售计划 - Dessert House</title>
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
                <li class="active"><a href="/DessertHouse/head/plan">销售计划</a></li>
                <li><a href="/DessertHouse/head/history">过往计划</a></li>
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
    StoreDao storeDao= DaoFactory.getStoreDao();
    ArrayList<Plan> list=service.getAllCurrentPlans();
    ArrayList<Dessert> dlist=service.getAllDesserts();
    ArrayList<Store> slist=storeDao.getAllStores();

%>
<style>
    .container-fluid{
        margin-left: 15%;
        margin-right: 15%;
    }
    #add_btn_div{
        text-align: right;
        margin-top: 20px;
    }
    .del_name{
        color:orangered;
        font-size: large;
    }
</style>
<div class="container-fluid row">
    <h2 class="col-xs-12 col-md-8">当前的计划</h2>
    <div id="add_btn_div" class="col-md-4"><button data-toggle="modal" data-target="#add_plan_modal" class="btn btn-primary">增加新计划</button></div>
    <div class="modal fade" id="add_plan_modal" tabindex="-1" role="dialog" aria-labelledby="增加新计划Modal">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title">增加新计划</h3>
                </div>
                <form id="add_plan_form" action="/DessertHouse/head/addPlan" method="post">
                    <div class="modal-body">
                        <input type="date" class="form-control" name="plan.ptime" placeholder="计划开始时间"/><br/>
                        <div class="form-group">
                            <label for="addPlan_dessert_select">商品</label>
                            <select form="add_plan_form" name="plan.dname" id="addPlan_dessert_select" class="form-control">
                                <% for(int i=0;i<dlist.size();i++){
                                    String tmpDname=dlist.get(i).getDname();
                                %>
                                <option value="<%=tmpDname%>"><%=tmpDname%></option>
                                <%}%>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="addPlan_store_select">门店</label>
                            <select form="add_plan_form" name="plan.sname" id="addPlan_store_select" class="form-control">
                                <% for(int i=0;i<slist.size();i++){
                                    String tmpSname=slist.get(i).getSname();
                                %>
                                <option value="<%=tmpSname%>"><%=tmpSname%></option>
                                <%}%>
                            </select>
                        </div>
                        <input type="text" class="form-control" name="plan.price" placeholder="请输入单价"/><br/>
                        <input type="text" class="form-control" name="plan.num_limit" placeholder="请输入每日限量"/><br/>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                        <button type="button submit" class="btn btn-primary">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <table class="table table-hover">
        <thead><tr>
            <th>ID</th>
            <th>起始日期</th>
            <th>商品名称</th>
            <th>门店名称</th>
            <th>单价</th>
            <th>每日限量</th>
            <th>状态</th>
            <th>操作</th>
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
            <td>
                <a href="#!" data-toggle="modal" data-target="#del_plan_modal<%=i%>">删除</a>
                <div class="modal fade" id="del_plan_modal<%=i%>" tabindex="-1" role="dialog" aria-labelledby="删除计划Modal">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="modal-title">删除确认</h3>
                            </div>
                            <div class="modal-body">
                                <p>确认要删除<span class="del_name">计划#<%=tmpPlan.getPid()%></span> 吗？</p>
                            </div>
                            <form action="/DessertHouse/head/removePlan" method="post">
                                <input hidden name="plan.pid" value="<%=tmpPlan.getPid()%>"/>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                                    <button type="button submit" class="btn btn-danger">删除</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <a href="#!" data-toggle="modal" data-target="#edit_plan_modal<%=i%>">修改</a>
                <div class="modal fade" id="edit_plan_modal<%=i%>" tabindex="-1" role="dialog" aria-labelledby="修改计划Modal">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="modal-title">修改计划</h3>
                            </div>
                            <form id="edit_plan_form<%=i%>" action="/DessertHouse/head/editPlan" method="post">
                                <div class="modal-body">
                                    <input readonly class="form-control" name="plan.pid" value="<%=tmpPlan.getPid()%>"><br>
                                    <input readonly class="form-control" name="plan.ptime" value="<%=tmpPlan.getPtime().substring(0,tmpPlan.getPtime().indexOf(' '))%>"/><br/>
                                    <input readonly class="form-control" value="<%=tmpPlan.getDname()%>" name="plan.dname"/><br>
                                    <input readonly class="form-control" value="<%=tmpPlan.getSname()%>" name="plan.sname"/><br>
                                    <input type="text" class="form-control" name="plan.price" placeholder="请输入单价" value="<%=tmpPlan.getPrice()%>"/><br/>
                                    <input type="text" class="form-control" name="plan.num_limit" placeholder="请输入每日限量" value="<%=tmpPlan.getNum_limit()%>"/><br/>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                                    <button type="button submit" class="btn btn-primary">提交</button>
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

</body>
</html>
