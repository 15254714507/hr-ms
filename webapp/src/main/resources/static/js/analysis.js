/**
 * Created by Administrator on 2017/4/25.
 */
function myEcharts(){

    salesMyEcharts();

}

function salesMyEcharts(){
    var salesMyChart = echarts.init($("#container1")[0]);
    var option = {
        title: {
            text: '1至12月薪资人数走势图',
            x:'center',
            y:'bottom'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['2000以下','2000-3000','3000-4000','4000-5000','5000以上']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月',"12月"]
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'2000以下',
                type:'line',
                stack: '总额（万元）',
                data:[120, 132, 101, 134, 90, 230, 210, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'2000-3000',
                type:'line',
                stack: '总额（万元）',
                data:[220, 182, 191, 234, 290, 330, 310, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'3000-4000',
                type:'line',
                stack: '总额（万元）',
                data:[150, 232, 201, 154, 190, 330, 410, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'4000-5000',
                type:'line',
                stack: '总额（万元）',
                data:[320, 332, 301, 334, 390, 330, 320, 132, 101, 134, 90, 230, 210]
            },
            {
                name:'5000以上',
                type:'line',
                stack: '总额（万元）',
                data:[820, 932, 901, 934, 1290, 1330, 1320, 132, 101, 134, 90, 230, 210]
            }
        ]
    };
    salesMyChart.setOption(option);
}


$(function(){

    myEcharts()
})


