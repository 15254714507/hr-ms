<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i>所在位置：<span>首页</span></div>
    <div class="home-top">
        <a href="#"></a>
        <a href="#"></a>
        <a href="#"></a>
        <a href="#"></a>
    </div>
    <div class="home-box">
        <h4 class="h-style"><span class="note-span">通知</span><span class="more-style"></span></h4>
        <ul class="note-ul">
            <li><a href="#">公司准备在星期一召开培训会议通知</a><span>6月12 10:12</span></li>
            <li><a href="#">2019年端午节放假通知</a><span>6月12 09:33</span></li>
            <li><a href="#">公司准备在星期一召开培训会议通知</a><span>6月12 10:12</span></li>
            <li><a href="#">2019年端午节放假通知</a><span>6月12 09:33</span></li>
            <li><a href="#">2019年端午节放假通知</a><span>6月12 09:33</span></li>
        </ul>
        <h4 class="h-style"><span class="note-span">信息统计</span></h4>
        <div class="home-static" id="chart01"></div>
        <div class="home-static" id="chart02"></div>
    </div>

</main>
<script src="js/jquery.js"></script>
<script src="js/echarts-all.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script>
    $(function () {
        char1();
        char2();
        change();

    })

    function char1() {

        var myChart = echarts.init($("#chart01")[0]);
//app.title = '堆叠柱状图';


        option = {
            title: {
                text: '人员薪酬占比'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                x: 'center',
                y: 'bottom',
                data: ['指标一', '指标二', '指标三']
            },

            calculable: false,
            series: [
                {
                    name: '指标一',
                    type: 'pie',
                    radius: '50%',
                    center: ['50%', '50%'],
                    data: [
                        {value: 335, name: '指标一'},
                        {value: 310, name: '指标二'},
                        {value: 234, name: '指标三'}

                    ]
                }


            ]
        };
        myChart.setOption(option);
        window.addEventListener('resize', function () {
            myChart.resize();
        })

    }

    function char2() {

        var myChart = echarts.init($("#chart02")[0]);
//app.title = '堆叠柱状图';


        option = {
            tooltip: {
                trigger: 'axis'
            },
            title: {
                text: '入职离职趋势'
            },
            legend: {
                data: ['入职', '离职']
            },

            calculable: true,
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']

                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '入职',
                    type: 'line',
                    stack: '总量',
                    data: [120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90]
                },
                {
                    name: '离职',
                    type: 'line',
                    stack: '总量',
                    data: [20, 12, 19, 34, 29, 30, 10, 20, 32, 10, 13, 9]
                }

            ]
        };
        myChart.setOption(option);
        window.addEventListener('resize', function () {
            myChart.resize();
        })

    }
    function change() {
        $(".wait-tab span").click(function () {
            var ins = $(this).index();
            $(this).addClass("tab-active").siblings().removeClass('tab-active');
            $(".tab-all .tab-con").eq(ins).show().siblings().hide();
        })
    }
</script>
</body>
</html>