/**
 * Created by wn13 on 2016/3/3.
 */

$(document).ready(function () {
    showAgeChart();
    showSexChart();
    showStatusChart();
    showAvgMoneyChart();
    $("#store_select").blur(function(){
        var sname=$(this).val();
        if(sname=="请选择门店"){
            return;
        }else{
            showStoreChart(sname);

        }
    });
    $("#top3_month_select").blur(function(){
        var sname=$("#store_select").val();
        var month=parseInt($(this).val().split("-")[1]);
        if(sname=="请选择门店"){
            alert("请先选择门店。");
        }else{
            showTop3(sname,month);
        }
    });
});
function showAgeChart(){
    var myChart = echarts.init(document.getElementById('ageChart'));
    var option = {
        title: {
            text: '会员年龄分布',
            left:'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:['20岁以下','20-30岁','30-40岁','40-50岁','50-70岁','70岁以上']
        },
        series: [
            {
                name:'年龄段',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '15',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:stats.userNumAgeS20, name:'20岁以下'},
                    {value:stats.userNumAgeS30, name:'20-30岁'},
                    {value:stats.userNumAgeS40, name:'30-40岁'},
                    {value:stats.userNumAgeS50, name:'40-50岁'},
                    {value:stats.userNumAgeS70, name:'50-70岁'},
                    {value:stats.userNumAgeB70, name:'70岁以上'}
                ]
            }
        ]
    };
    myChart.setOption(option);
}
function showSexChart(){
    var myChart = echarts.init(document.getElementById('sexChart'));
    var option = {
        title: {
            text: '会员性别分布',
            left:'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:['男','女']
        },
        series: [
            {
                name:'性别',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '15',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:stats.userNumMale, name:'男'},
                    {value:stats.userNumFemale, name:'女'}
                ]
            }
        ]
    };
    myChart.setOption(option);
}
function showStatusChart(){
    var myChart = echarts.init(document.getElementById('ustatusChart'));

    var option = {
        title: {
            text: '会员状态分布',
            left:'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:['未激活','正常','暂停','停止']
        },
        series: [
            {
                name:'状态',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '15',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:stats.inactivatedUserNum, name:'未激活'},
                    {value:stats.activatedUserNum, name:'正常'},
                    {value:stats.frozenUserNum, name:'暂停'},
                    {value:stats.invalidUserNum, name:'停止'},
                ]
            }
        ]
    };
    myChart.setOption(option);
}
function showAvgMoneyChart(){
    var myChart = echarts.init(document.getElementById('avgMoneyChart'));
    option = {
        title:{
            text: '近12个月消费情况',
            left:'center'
        },
        tooltip : {
            trigger: 'axis'
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        legend: {
            data:['总消费额','人均消费额'],
            x:'left'
        },
        xAxis : [
            {
                type : 'category',
                data : stats.sixMonthName
            }
        ],
        yAxis : [
            {
                type : 'value',
                name : '总金额',
                min: 0,
                max: 5000,
                axisLabel : {
                    formatter: '{value} 元'
                }
            },
            {
                type : 'value',
                name : '人均',
                min: 0,
                max: 1000,
                axisLabel : {
                    formatter: '{value} 元'
                }
            }
        ],
        series : [

            {
                name: '总消费额',
                type: 'bar',
                data: stats.sixMonthTotalMoney
            },
            {
                name:'人均消费额',
                type:'line',
                yAxisIndex: 1,
                data:stats.sixMonthAvgMoney
            }
        ]
    };
    myChart.setOption(option);
}
function getStoreStats(sname){
    var result=new Array();
    for(var i=0;i<stats.storeStats.length;i++){
        if(stats.storeStats[i].sname==sname){
            result.push(stats.storeStats[i]);
        }
    }
    return result;
}
function showStoreChart(sname){
    var orderNumArr=new Array();
    var sellNumArr=new Array();
    var orderMoneyArr=new Array();
    var sellMoneyArr=new Array();
    var storeStats=getStoreStats(sname);
    for(var i=0;i<storeStats.length;i++){
        orderNumArr.push(storeStats[i].orderNum);
        sellNumArr.push(storeStats[i].sellNum);
        orderMoneyArr.push(storeStats[i].orderMoney);
        sellMoneyArr.push(storeStats[i].sellMoney);
    }


    var myChart = echarts.init(document.getElementById('storeChart'));
    option = {
        title:{
            text: '近12个月运营状况',
            left:'center'
        },
        tooltip : {
            trigger: 'axis'
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        legend: {
            data:['订单量','零售量','订单收入','零售收入'],
            x:'left'
        },
        xAxis : [
            {
                type : 'category',
                data : stats.sixMonthName
            }
        ],
        yAxis : [
            {
                type : 'value',
                name : '交易量',
                min: 0,
                max: 200,
                axisLabel : {
                    formatter: '{value} 笔'
                }
            },
            {
                type : 'value',
                name : '交易收入',
                min: 0,
                max: 2000,
                axisLabel : {
                    formatter: '{value} 元'
                }
            }
        ],
        series : [

            {
                name: '订单量',
                type: 'bar',
                data:orderNumArr
            },{
                name: '零售量',
                type: 'bar',
                data:sellNumArr
            },{
                name: '订单收入',
                type: 'line',
                yAxisIndex: 1,
                data:orderMoneyArr
            },
            {
                name:'零售收入',
                type:'line',
                yAxisIndex: 1,
                data:sellMoneyArr
            }
        ]
    };
    myChart.setOption(option);
}
function showTop3(sname,month){
    var storeStats=getStoreStats(sname);
    for(var i=0;i<storeStats.length;i++){
        if(storeStats[i].month==month){
            var str=generateTop3HTML(storeStats[i].top3);
            $("#top3_table").html(str);
        }
    }
}

function generateTop3HTML(topNames){
    var str="";
    var toplist=new Array();
    for(var i=0;i<topNames.length;i++){
        for(var j=0;j<dlist.length;j++){
            if(topNames[i]==dlist[j].dname){

                toplist.push(dlist[j]);
            }
        }
    }
    for(var i=0;i<toplist.length;i++){
        str+="<tr>";
        if(toplist[i].hasImg==1){
            str+="<td><img src='/DessertHouse/dhImg/"+toplist[i].dname+".jpg'></td>";
        }else{
            str+="<td><img src='/DessertHouse/dhImg/default.jpg'></td>";
        }
        str+="<td>"+toplist[i].dname+"</td>";
        str+="<td>"+toplist[i].dintro+"</td>";
        str+="</tr>";
    }
    return str;
}