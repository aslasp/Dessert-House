/**
 * Created by wn13 on 2016/3/3.
 */
$(document).ready(function () {
    $("#search_fld").keyup(function(){
        var user;
        var found=false;
        for(var i=0;i<ulist.length;i++){
            if($(this).val()==ulist[i].uid){
                found=true;
                user=ulist[i];
            }
        }
        if(!found){
            $("#card_content").html("无效卡");
            $("#order_tbody").html("");
        }else{
            var sex;
            if(user.usex==1){
                sex="男";
            }else{
                sex="女";
            }

            var cstr="<div class='col-md-12'>会员卡号："+user.uid+"</div>" +
                "<div class='col-md-12'>上次激活时间："+user.uactivate_time+"</div>"+
                    "<div class='col-md-12'>住址："+user.uaddr+"</div>" +
                    "<div class='col-md-12'>银行卡号："+user.ucard+"</div>" +
                "<div class='col-md-4'>持卡人姓名："+user.uname+"</div>" +
                "<div class='col-md-4'>年龄："+user.uage+"</div>" +
                "<div class='col-md-4'>性别："+sex+"</div>" +
                "<div class='col-md-4'>等级："+user.ulevel+"</div>" +
                "<div class='col-md-4'>积分："+user.ubonus+"</div>" +
                "<div class='col-md-4'>余额："+user.ubalance+"</div>";
            var ostr="";
            for(var i=0;i<slist.length;i++){
                if(slist[i].uid==user.uid){
                    var otype;
                    switch (slist[i].otype){
                        case 0:otype="订单执行中";break;
                        case 1:otype="购买完成";break;
                        case 2:otype="订单完成";break;
                        case 3:otype="订单取消";break;
                        default:otype="未知";
                    }
                    ostr+="<tr>" +
                        "<td>"+slist[i].oid+"</td>" +
                        "<td>"+slist[i].otime+"</td>" +
                        "<td>"+otype+"</td>" +
                        "<td>"+slist[i].sname+"</td>" +
                        "<td>"+slist[i].dname+"</td>" +
                        "<td>"+slist[i].oprice+"</td>" +
                        "<td>"+slist[i].onum+"</td>" +
                        "<td>"+slist[i].ototal+"</td>" +
                        "</tr>";
                }
            }

            $("#card_content").html(cstr);
            $("#order_tbody").html(ostr);
        }
    });
});