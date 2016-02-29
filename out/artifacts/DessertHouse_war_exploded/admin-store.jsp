<%@ page import="service.AdminService" %>
<%@ page import="service.serviceimpl.AdminServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.Store" %><%--
  Created by IntelliJ IDEA.
  User: wn13
  Date: 2016/2/15
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/navbar-admin.css" rel="stylesheet">

    <script src="../js/jquery-1.12.0.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/jquery.cookie.js"></script>
    <script src="../js/navbar-admin.js"></script>

    <!--[if lt IE 9]>
    <script src="../js/html5shiv.min.js"></script>
    <script src="../js/respond.min.js"></script>
    <![endif]-->
    <title>店面管理 - Dessert House</title>


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
                <li class="active"><a href="/DessertHouse/admin/store">店面管理</a></li>
                <li><a href="/DessertHouse/admin/employee">店员管理</a></li>

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

<% AdminService service=new AdminServiceImpl();
    ArrayList<Store> storeList=service.getAllStores();

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
    <h2 class="col-xs-12 col-md-8">店面管理</h2>
    <div id="add_btn_div" class="col-md-4"><button data-toggle="modal" data-target="#add_store_modal" class="btn btn-primary">增加新店</button></div>
    <div class="modal fade" id="add_store_modal" tabindex="-1" role="dialog" aria-labelledby="增加新店Modal">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title">增加新店</h3>
                </div>
                <form action="/DessertHouse/admin/addStore" method="post">
                    <div class="modal-body">
                        <input type="text" class="form-control" name="store.sname" placeholder="请输入门店名称"/><br/>
                        <input type="text" class="form-control" name="store.saddr" placeholder="请输入门店地址"/><br/>
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
            <th>门店名称</th>
            <th>门店地址</th>
            <th>操作</th>
        </tr></thead>
        <tbody>
        <% for(int i=0;i<storeList.size();i++){%>
        <tr>
            <td><%=storeList.get(i).getSname()%></td>
            <td><%=storeList.get(i).getSaddr()%></td>
            <td>
                <a href="#!" data-toggle="modal" data-target="#del_store_modal<%=i%>">删除</a>
                <div class="modal fade" id="del_store_modal<%=i%>" tabindex="-1" role="dialog" aria-labelledby="删除门店Modal">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="modal-title">删除确认</h3>
                            </div>
                                <div class="modal-body">
                                    <p>确认要删除<span class="del_name"><%=storeList.get(i).getSname()%></span> 吗？删除时，会同时删除此店最近的销售计划。</p>
                                </div>
                            <form action="/DessertHouse/admin/removeStore" method="post">
                                <input hidden name="store.sname" value="<%=storeList.get(i).getSname()%>"/>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                                    <button type="button submit" class="del_store_btn btn btn-danger">删除</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <a href="#!" data-toggle="modal" data-target="#edit_store_modal<%=i%>">编辑</a>
                <div class="modal fade" id="edit_store_modal<%=i%>" tabindex="-1" role="dialog" aria-labelledby="修改门店信息Modal">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="modal-title">修改门店信息</h3>
                            </div>
                            <form action="/DessertHouse/admin/editStore" method="post">
                                <div class="modal-body">
                                    <input readonly type="text" class="form-control" name="store.sname" placeholder="请输入门店名称" value="<%=storeList.get(i).getSname()%>"/><br/>
                                    <input type="text" class="form-control" name="store.saddr" placeholder="请输入门店地址" value="<%=storeList.get(i).getSaddr()%>"/><br/>
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
