<%@ page import="service.HeadService" %>
<%@ page import="service.serviceimpl.HeadServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.Dessert" %><%--
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

    <title>商品管理 - Dessert House</title>

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
                <li><a href="/DessertHouse/head/history">过往计划</a></li>
                <li class="active"><a href="/DessertHouse/head/dessert">商品管理</a></li>
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
    ArrayList<Dessert> list=service.getAllDesserts();
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
    <h2 class="col-xs-12 col-md-8">商品管理</h2>
    <div id="add_btn_div" class="col-md-4"><button data-toggle="modal" data-target="#add_dessert_modal" class="btn btn-primary">添加新品</button></div>
    <div class="modal fade" id="add_dessert_modal" tabindex="-1" role="dialog" aria-labelledby="添加新品Modal">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title">添加新品</h3>
                </div>
                <form action="/DessertHouse/head/addDessert" method="post" enctype="multipart/form-data">
                    <div class="modal-body">
                        <input type="text" class="form-control" name="dessert.dname" placeholder="请输入商品名称"/><br/>
                        <input type="text" class="form-control" name="dessert.dintro" placeholder="请输入简介"/><br/>
                        <div>使用默认图片：&nbsp;<input type="radio" name="dessert.hasImg" value="0" /> 是&nbsp;
                            <input type="radio" name="dessert.hasImg" value="1" /> 否</div><br/>
                        <div>
                            <label for="myFile">选择图片</label>
                            <input id="myFile" type="file" name="img" />
                        </div>
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
            <th>图片</th>
            <th>名称</th>
            <th>简介</th>
            <th>操作</th>
        </tr></thead>
        <tbody>
        <% for(int i=0;i<list.size();i++){
        Dessert tmp=list.get(i);%>
        <tr>
            <td><img src="<%if(tmp.getHasImg()==1){
            out.print("/DessertHouse/dhImg/"+tmp.getDname()+".jpg");
            }else{
            out.print("/DessertHouse/dhImg/default.jpg");
            }
            %>" class="img-thumbnail"></td>
            <td><%=tmp.getDname()%></td>
            <td><%=tmp.getDintro()%></td>
            <td>
                <a href="#!" data-toggle="modal" data-target="#del_dessert_modal<%=i%>">删除</a>
                <div class="modal fade" id="del_dessert_modal<%=i%>" tabindex="-1" role="dialog" aria-labelledby="删除Modal">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="modal-title">删除确认</h3>
                            </div>
                            <div class="modal-body">
                                <p>确认要删除<span class="del_name"><%=tmp.getDname()%></span> 吗？删除时，会同时删除最近的销售计划。</p>
                            </div>
                            <form action="/DessertHouse/head/removeDessert" method="post">
                                <input hidden name="dessert.dname" value="<%=tmp.getDname()%>"/>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                                    <button type="button submit" class="del_dessert_btn btn btn-danger">删除</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <a href="#!" data-toggle="modal" data-target="#edit_dessert_modal<%=i%>">编辑</a>
                <div class="modal fade" id="edit_dessert_modal<%=i%>" tabindex="-1" role="dialog" aria-labelledby="修改信息Modal">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="modal-title">修改信息</h3>
                            </div>
                            <form action="/DessertHouse/head/editDessert" method="post" enctype="multipart/form-data">
                                <div class="modal-body">
                                    <input readonly type="text" class="form-control" name="dessert.dname" placeholder="请输入名称" value="<%=tmp.getDname()%>"/><br/>
                                    <input type="text" class="form-control" name="dessert.dintro" placeholder="请输入简介" value="<%=tmp.getDintro()%>"/><br/>
                                    <div>使用原来的图片：&nbsp;<input type="radio" name="newImgUploaded" value="0" /> 是&nbsp;
                                        <input type="radio" name="newImgUploaded" value="1" /> 否</div><br/>
                                    <div>
                                        <label for="editFile<%=i%>">选择图片</label>
                                        <input id="editFile<%=i%>" type="file" name="img" />
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                                    <input hidden name="dessert.hasImg" value="<%=tmp.getHasImg()%>">
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
