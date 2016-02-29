<%@ page import="service.AdminService" %>
<%@ page import="service.serviceimpl.AdminServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.Store" %>
<%@ page import="beans.Employee" %><%--
  Created by IntelliJ IDEA.
  User: wn13
  Date: 2016/2/17
  Time: 14:07
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
    <script src="../js/admin-employee.js"></script>

    <!--[if lt IE 9]>
    <script src="../js/html5shiv.min.js"></script>
    <script src="../js/respond.min.js"></script>
    <![endif]-->

    <title>店员管理 - Dessert House</title>
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
                <li><a href="/DessertHouse/admin/store">店面管理</a></li>
                <li class="active"><a href="/DessertHouse/admin/employee">店员管理</a></li>

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
<%  AdminService service=new AdminServiceImpl();
    ArrayList<Store> storeList=service.getAllStores();
    ArrayList<Employee> employeeList=service.getAllEmployee();
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
    <h2 class="col-xs-12 col-md-8">店员管理</h2>
    <div id="add_btn_div" class="col-md-4"><button data-toggle="modal" data-target="#add_employee_modal" class="btn btn-primary">增加新店员</button></div>
    <div class="modal fade" id="add_employee_modal" tabindex="-1" role="dialog" aria-labelledby="增加新店员Modal">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title">增加新店员</h3>
                </div>
                <form action="/DessertHouse/admin/addEmployee" id="add_employee_form" method="post">
                    <div class="modal-body">
                        <input type="text" class="form-control" name="employee.ename" placeholder="请输入姓名"/><br/>
                        <input type="password" class="form-control" name="employee.epswd" placeholder="请输入密码"/><br/>
                        <div>类别：&nbsp;<input type="radio" name="employee.etype" value="2" class="head_radio" /> 总店服务员&nbsp;
                            <input type="radio" name="employee.etype" value="3" class="branch_radio" /> 分店服务员</div><br/>
                        <div class="form-group">
                            <label for="add_sname_select">所属门店</label>
                            <select form="add_employee_form" name="employee.sname" id="add_sname_select" class="form-control sname_select">
                                <option class="head_option" value="总店">总店</option>
                                <% for(int i=0;i<storeList.size();i++){%>
                                <option class="branch_option" value="<%=storeList.get(i).getSname()%>"><%=storeList.get(i).getSname()%></option>
                                <% }%>
                            </select>
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
            <th>姓名</th>
            <th>类型</th>
            <th>所属门店</th>
            <th>操作</th>
        </tr></thead>
        <tbody>
        <% for(int j=0;j<employeeList.size();j++){
        Employee tmpEmployee=employeeList.get(j);%>
        <tr>
            <td><%=tmpEmployee.getEname()%></td>
            <td><%int tmpEtype=tmpEmployee.getEtype();
                switch (tmpEtype){
                    case 0:out.print("系统管理员");break;
                    case 1:out.print("经理");break;
                    case 2:out.print("总店服务员");break;
                    case 3:out.print("分店服务员");break;
                    default:out.print("未知");
                }
            %></td>
            <td><%=tmpEmployee.getSname()%></td>
            <td>
                <% if(tmpEtype!=0 && tmpEtype!=1){%>
                <a href="#!" data-toggle="modal" data-target="#del_employee_modal<%=j%>">删除</a>
                <div class="modal fade" id="del_employee_modal<%=j%>" tabindex="-1" role="dialog" aria-labelledby="删除员工Modal">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="modal-title">删除确认</h3>
                            </div>
                            <div class="modal-body">
                                <p>确认要删除<span class="del_name"><%=tmpEmployee.getEname()%></span> 吗？</p>
                            </div>
                            <form action="/DessertHouse/admin/removeEmployee" method="post">
                                <input hidden name="employee.ename" value="<%=tmpEmployee.getEname()%>"/>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                                    <button type="button submit" class="del_store_btn btn btn-danger">删除</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <a href="#!" data-toggle="modal" data-target="#edit_employee_modal<%=j%>">编辑</a>
                <div class="modal fade" id="edit_employee_modal<%=j%>" tabindex="-1" role="dialog" aria-labelledby="编辑店员Modal">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="modal-title">修改店员信息</h3>
                            </div>
                            <form action="/DessertHouse/admin/editEmployee" id="edit_employee_form<%=j%>" method="post">
                                <div class="modal-body">
                                    <input readonly type="text" class="form-control" name="employee.ename" value="<%=tmpEmployee.getEname()%>"/><br/>
                                    <input type="password" class="form-control" name="employee.epswd" placeholder="请输入密码" value="<%=tmpEmployee.getEpswd()%>"/><br/>
                                    <div>类别：&nbsp;<input type="radio" name="employee.etype" value="2" class="head_radio" /> 总店服务员&nbsp;
                                        <input type="radio" name="employee.etype" value="3" class="branch_radio" /> 分店服务员</div><br/>
                                    <div class="form-group">
                                        <label for="edit_sname_select<%=j%>">所属门店</label>
                                        <select form="edit_employee_form<%=j%>" name="employee.sname" id="edit_sname_select<%=j%>" class="form-control sname_select">
                                            <option class="head_option" value="总店">总店</option>
                                            <% for(int i=0;i<storeList.size();i++){%>
                                            <option class="branch_option" value="<%=storeList.get(i).getSname()%>"><%=storeList.get(i).getSname()%></option>
                                            <% }%>
                                        </select>
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
                <%}%>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>


</body>
</html>
