/**
 * Created by wn13 on 2016/2/29.
 */
$(document).ready(function () {
    $('.list-group-item').click(function () {
        $('.list-group-item').attr('class', 'list-group-item');
        $(this).addClass("active");
        $.post("/DessertHouse/user/ajaxGetCommodity", {sname: $(this).html()}, function (data, status) {
            if (status == "success") {
                generateRightContent(data);
            }
        });
    });
});
function generateRightContent(data) {
    var htmlStr = "<table class='table table-hover'><thead><tr>\
            <th>图片</th>\
            <th>名称</th>\
            <th>简介</th>\
            <th>单价</th>\
            <th>操作</th></tr></thead><tbody>";
    var list = data.commodities;
    for (var i = 0; i < list.length; i++) {
        htmlStr += "<tr>";
        var imgPath;
        if (list[i].hasImg == 1)
            imgPath = "/DessertHouse/dhImg/" + list[i].dname + ".jpg";
        else
            imgPath = "/DessertHouse/dhImg/default.jpg";
        htmlStr += "<td><img src='" + imgPath + "'></td>";
        htmlStr += "<td>" + list[i].dname + "</td>";
        htmlStr += "<td>" + list[i].dIntro + "</td>";
        htmlStr += "<td>" + list[i].price + "</td>";
        htmlStr += "<td><button data-toggle='modal' data-target='#buy_modal" + i + "' class='btn btn-primary'>购买</button>&nbsp;" +
            "<button data-toggle='modal' data-target='#order_modal" + i + "' class='btn btn-primary'>预订</button></td>";
        htmlStr += "<div class='modal fade' id='buy_modal" + i+
        "' tabindex='-1' role='dialog' aria-labelledby='购买Modal'>\
                    <div class='modal-dialog' role='document'>\
                        <div class='modal-content'>\
                        <div class='modal-header'>\
                        <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>\
                    <h3 class='modal-title'>立刻购买</h3>\
                        </div>\
                        <form action='/DessertHouse/user/buyNow' method='post'>\
                        <div class='modal-body row'>\
                            <input hidden name='order.uid' value='"+uid+"'>\
                            <input hidden name='ubalance' value='"+ubalance+"'>\
                            <div class='form-group col-md-6'>商品：<input readonly class='form-control' name='order.dname' value='"+list[i].dname+"'></div>\
                            <div class='form-group col-md-6'>门店：<input readonly class='form-control' name='order.sname' value='"+list[i].sname+"'></div>\
                            <div class='form-group col-md-4'>单价：<input readonly class='form-control' name='order.oprice' value='"+list[i].price+"'></div>\
                            <div class='form-group col-md-4'>数量（剩余"+list[i].remainNum+"个）：<input onblur='calTotals($(this).val(),"+list[i].price+","+i+","+list[i].remainNum+")' id='num_"+i+"' class='form-control' type='text' name='order.onum' value='1'></div>\
                            <div class='form-group col-md-4'>折前总价：<input readonly id='btotal"+i+"' class='form-control'></div>\
                            <div class='form-group col-md-4'>折扣系数：<input readonly value='"+discount+"' class='form-control'></div>\
                            <div class='form-group col-md-8'>折后总价（您的余额："+ubalance+"）：<input readonly id='ctotal"+i+"' name='order.ototal' class='form-control'></div>\
                    </div>\
                        <div class='modal-footer'>\
                            <button type='button' class='btn btn-default' data-dismiss='modal'>返回</button>\
                            <button type='button submit' id='buy_btn"+i+"' class='btn btn-success'>购买</button>\
                            </div>\
                            </form>\
                            </div>\
                            </div>\
                            </div>";
        htmlStr += "<div class='modal fade' id='order_modal" + i+
            "' tabindex='-1' role='dialog' aria-labelledby='预订Modal'>\
                        <div class='modal-dialog' role='document'>\
                            <div class='modal-content'>\
                            <div class='modal-header'>\
                            <button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>\
                        <h3 class='modal-title'>预订商品</h3>\
                            </div>\
                            <form action='/DessertHouse/user/orderNow' method='post'>\
                            <div class='modal-body row'>\
                                <input hidden name='order.uid' value='"+uid+"'>\
                            <input hidden name='ubalance' value='"+ubalance+"'>\
                            <div class='form-group col-md-6'>商品：<input readonly class='form-control' name='order.dname' value='"+list[i].dname+"'></div>\
                            <div class='form-group col-md-6'>门店：<input readonly class='form-control' name='order.sname' value='"+list[i].sname+"'></div>\
                            <div class='form-group col-md-4'>单价：<input readonly class='form-control' name='order.oprice' value='"+list[i].price+"'></div>\
                            <div class='form-group col-md-4'>数量（剩余"+list[i].remainNum+"个）：<input onblur='calOTotals($(this).val(),"+list[i].price+","+i+","+list[i].remainNum+")' class='form-control' type='text' name='order.onum' value='1'></div>\
                            <div class='form-group col-md-4'>折前总价：<input readonly id='obtotal"+i+"' class='form-control'></div>\
                            <div class='form-group col-md-4'>折扣系数：<input readonly value='"+discount+"' class='form-control'></div>\
                            <div class='form-group col-md-8'>折后总价（您的余额："+ubalance+"）：<input readonly id='octotal"+i+"' name='order.ototal' class='form-control'></div>\
                            <div class='form-group col-md-8'>送货日期：<input type='date' name='order.otime' class='form-control' placeholder='请选择送货日期'></div>\
                    </div>\
                        <div class='modal-footer'>\
                            <button type='button' class='btn btn-default' data-dismiss='modal'>返回</button>\
                            <button type='button submit' id='obuy_btn"+i+"' class='btn btn-success'>购买</button>\
                            </div>\
                            </form>\
                            </div>\
                            </div>\
                            </div>";
        htmlStr += "</tr>";
    }
    htmlStr += "</tbody></table>";
    $("#right_content").html(htmlStr);
}

function calTotals(num,price,index,remain){
    $('#buy_btn'+index).attr("disabled",false);
    $('#buy_btn'+index).attr("class","btn btn-success");
    var bt=(num*price).toFixed(2);
    var ct=(num*price*discount).toFixed(2);
    $('#btotal'+index).val(bt);
    $('#ctotal'+index).val(ct);
    if(num>remain){
        alert("剩余数量不足！");
        $('#buy_btn'+index).attr("disabled",true);
        $('#buy_btn'+index).attr("class","btn btn-danger");
    }
    if(num*price*discount>ubalance){
        alert("您的余额不足！");
        $('#buy_btn'+index).attr("disabled",true);
        $('#buy_btn'+index).attr("class","btn btn-danger");
    }
}

function calOTotals(num,price,index,remain){
    $('#obuy_btn'+index).attr("disabled",false);
    $('#obuy_btn'+index).attr("class","btn btn-success");
    var bt=(num*price).toFixed(2);
    var ct=(num*price*discount).toFixed(2);
    $('#obtotal'+index).val(bt);
    $('#octotal'+index).val(ct);
    if(num>remain){
        alert("剩余数量不足！");
        $('#obuy_btn'+index).attr("disabled",true);
        $('#obuy_btn'+index).attr("class","btn btn-danger");
    }
    if(num*price*discount>ubalance){
        alert("您的余额不足！");
        $('#obuy_btn'+index).attr("disabled",true);
        $('#obuy_btn'+index).attr("class","btn btn-danger");
    }
}