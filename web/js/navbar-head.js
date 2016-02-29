/**
 * Created by wn13 on 2016/2/17.
 */
$(document).ready(function(){
    var ename= $.cookie('ename');
    var etype= $.cookie('etype')
    if(ename==null||etype!='2'){
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