/**
 * Created by wn13 on 2016/2/16.
 */

$(document).ready(function(){
    var ename= $.cookie('ename');
    var etype= $.cookie('etype');
    if(ename==null||etype!='0'){
        window.location.href="/DessertHouse/index";
    }else{
        $('#navbar_ename').html(ename);
    }

    $('#logout_btn').click(function(){
        $.removeCookie('ename',{ path: '/' });
        $.removeCookie('etype',{ path: '/' });
        window.location.href="/DessertHouse/index";
    });
});