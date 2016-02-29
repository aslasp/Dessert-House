<%@ page import="service.CookieService" %>
<%@ page import="service.serviceimpl.CookieServiceImpl" %>
<%@ page import="beans.User" %>
<%@ page import="service.UserService" %>
<%@ page import="service.serviceimpl.UserServiceImpl" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.RechargeRecord" %>
<%@ page import="beans.BonusRecord" %><%--
  Created by IntelliJ IDEA.
  User: wn13
  Date: 2016/2/24
  Time: 0:40
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

    <script>
        $(document).ready(function(){
            $('#destoryBtn').click(function(){
                $.removeCookie('uid',{ path: '/' });
                $.removeCookie('uname',{ path: '/' });
            });
        });
    </script>

    <title>会员卡管理 - Dessert House</title>
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
                <li><a href="/DessertHouse/user/main">在线预订</a></li>
                <li><a href="/DessertHouse/user/order">订单管理</a></li>
                <li class="active"><a href="/DessertHouse/user/vip">会员卡管理</a></li>

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
</style>
<%
    CookieService cookieService=new CookieServiceImpl();
    UserService userService=new UserServiceImpl();
    String uid=cookieService.getCookie("uid");
    User user=userService.findUser("uid",uid).get(0);
    ArrayList<RechargeRecord> rlist=userService.getRechargeHistory(Integer.parseInt(uid));
    ArrayList<BonusRecord> blist=userService.getBonusHistory(Integer.parseInt(uid));
%>

<div class="container-fluid row">

    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">卡基本信息</h3>
        </div>
        <div class="panel-body">
            <div>卡号：<%=uid%></div>
            <div>等级：<%=user.getUlevel()%>级</div>
            <div>积分：<%=user.getUbonus()%>分</div>
            <div>余额：<%=user.getUbalance()%>元</div>
            <div>卡状态：<%
                switch(user.getUstatus()){
                    case 0:out.print("未激活");break;
                    case 1:out.print("正常");break;
                    case 2:out.print("暂停");break;
                    default:out.print("未知");
                }
            %></div>
            <div>最近一次激活时间：<%
                if(user.getUstatus()==0){
                    out.print("无");
                }else{
                    out.print(user.getUactivate_time());
                }
                %>
            </div>
            <div>
                <a href="#!" class="btn btn-sm btn-success" data-toggle="modal" data-target="#rechargeModal">充值</a>
                <div class="modal fade" id="rechargeModal" tabindex="-1" role="dialog" aria-labelledby="充值Modal">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="modal-title">充值</h3>
                            </div>
                            <form action="/DessertHouse/user/recharge" method="post">
                                <div class="modal-body">
                                    <input hidden name="user.uid" value="<%=user.getUid()%>"/>
                                    <input hidden name="user.ulevel" value="<%=user.getUlevel()%>"/>
                                    <input hidden name="user.ubalance" value="<%=user.getUbalance()%>"/>
                                    <input hidden name="user.utotal_recharge" value="<%=user.getUtotal_recharge()%>"/>
                                    <input hidden name="user.ubonus" value="<%=user.getUbonus()%>"/>
                                    <input hidden name="user.ustatus" value="<%=user.getUstatus()%>"/>
                                    <input type="text" class="form-control" name="money" placeholder="请输入金额"/><br/>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                                    <button type="button submit" class="btn btn-success">充值</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <a href="#!" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#bonusModal">积分兑换</a>
                <div class="modal fade" id="bonusModal" tabindex="-1" role="dialog" aria-labelledby="积分兑换Modal">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="modal-title">积分兑换</h3>
                            </div>
                            <form action="/DessertHouse/user/useBonus" method="post">
                                <div class="modal-body">
                                    <input hidden name="user.uid" value="<%=user.getUid()%>"/>
                                    <input hidden name="user.ubalance" value="<%=user.getUbalance()%>"/>
                                    <input hidden name="user.ubonus" value="<%=user.getUbonus()%>"/>
                                    <input type="text" class="form-control" name="useBonus" placeholder="请输入兑换量"/><br/>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                                    <button type="button submit" class="btn btn-success">兑换</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <form style="display:inline" action="/DessertHouse/user/destory" method="post">
                    <input hidden name="uid" value="<%=uid%>">
                    <button type="button submit" id="destoryBtn" class="btn-sm btn btn-danger">停用此卡</button>
                </form>
            </div>

        </div>
    </div>
    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="#recharge_tbl" data-toggle="tab">充值历史</a></li>
        <li role="presentation"><a href="#bonus_tbl" data-toggle="tab">积分兑换历史</a></li>
    </ul>
    <div class="tab-content">
        <table class="table table-hover tab-pane fade in active" id="recharge_tbl">
            <thead><tr>
                <th>日期</th>
                <th>卡号</th>
                <th>充值金额</th>
            </tr></thead>
            <tbody>
                <%for(int i=0;i<rlist.size();i++){
                    RechargeRecord tmp=rlist.get(i);
                %>
                <tr>
                    <td><%=tmp.getRtime()%></td>
                    <td><%=tmp.getUid()%></td>
                    <td><%=tmp.getRmoney()%></td>
                </tr>
            <%}%>
            </tbody>
        </table>
        <table class="table table-hover tab-pane fade" id="bonus_tbl">
            <thead><tr>
                <th>日期</th>
                <th>卡号</th>
                <th>兑换量</th>
            </tr></thead>
            <tbody>
            <%for(int i=0;i<blist.size();i++){
                BonusRecord tmp=blist.get(i);
            %>
            <tr>
                <td><%=tmp.getUseTime()%></td>
                <td><%=tmp.getUid()%></td>
                <td><%=tmp.getUse()%></td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
