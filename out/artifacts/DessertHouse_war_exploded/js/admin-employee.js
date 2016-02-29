/**
 * Created by wn13 on 2016/2/17.
 */

$(document).ready(function(){
    $('.head_radio').click(function(){
        $('.head_option').attr("disabled",false);
        $('.sname_select').val("总店");
        $('.branch_option').attr("disabled",true);
    });
    $('.branch_radio').click(function(){
        $('.branch_option').attr("disabled",false);
        $('.sname_select').val("");
        $('.head_option').attr("disabled",true);
    });
});