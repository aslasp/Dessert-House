/**
 * Created by wn13 on 2016/2/14.
 */

$(document).ready(function(){
    var uname= $.cookie('uname');
    if(uname==null){
        window.location.href="/DessertHouse/index";
    }else{
        $('#navbar_uname').html(uname);
    }

    $('#logout_btn').click(function(){
        $.removeCookie('uname',{ path: '/' });
        $.removeCookie('uid',{ path: '/' });
        window.location.href="/DessertHouse/index";
    });
});
