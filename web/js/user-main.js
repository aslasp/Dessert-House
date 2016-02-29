/**
 * Created by wn13 on 2016/2/29.
 */

$(document).ready(function(){
    $('#slist_item0').attr('class',"list-group-item active");
    $('.list-group-item').click(function(){
        $('.list-group-item').attr('class','list-group-item');
        $(this).attr('class',"list-group-item active");
    });
});