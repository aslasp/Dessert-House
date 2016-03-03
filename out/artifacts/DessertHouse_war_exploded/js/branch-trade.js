/**
 * Created by wn13 on 2016/3/2.
 */
$(document).ready(function () {
    $('.uid_fld').blur(function () {
        var i=$(this).attr("index");
        $('#sellBtn'+i).attr("disabled",false);
        if($(this).val()==0){
            $("#ubspan"+i).html("请使用现金");
            $("#discount"+i).val(1.0);
        }else {
            var user;
            var found=false;
            for (var j = 0; j < ulist.length; j++) {
                if (ulist[j].uid == $(this).val() && ulist[j].ustatus != 1) {
                    alert("您的会员状态异常！");
                    $('#sellBtn' + i).attr("disabled", true);
                }
                else if (ulist[j].uid == $(this).val()) {
                    found=true;
                    user = ulist[j];
                }
            }
            if(!found){
                alert("会员卡不存在，请检查卡号是否正确。");
                $('#sellBtn' + i).attr("disabled", true);
            }
            $("#ubspan" + i).html(user.ubalance);
            $("#ubinput" + i).val(user.ubalance);
            var discount=1.0;
            switch (user.ulevel){
                case 1:discount=0.9;break;
                case 2:discount=0.8;break;
                case 3:discount=0.75;break;
            }
            $("#discount"+i).val(discount);
        }
    });

    $('.onum_fld').blur(function () {
        var i=$(this).attr("index");
        $('#sellBtn'+i).attr("disabled",false);
        if($(this).val()<=0){
            alert("数量应至少为1。")
            $('#sellBtn'+i).attr("disabled",true);
        }else {
            var price=$("#price"+i).val();
            var discount=$("#discount"+i).val();
            $("#btotal"+i).val(price*$(this).val());
            $("#ctotal"+i).val(price*$(this).val()*discount);
        }


    });
});