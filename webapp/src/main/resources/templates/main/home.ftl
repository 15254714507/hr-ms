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
    <p class="home-p">xxxx,你好！</p>
    <p class="home-p">你有<a href="#" class="a-orgen">5条</a>待办信息,<a href="#" class="a-blue">2条</a>考勤异常,<a href="#"
                                                                                                        class="a-red">3条</a>
        未读通知</p>
    <div class="home-top">
        <a href="#"></a>
        <a href="#"></a>
        <a href="#"></a>
        <a href="#"></a>
    </div>
    <div class="home-box">
        <h4 class="h-style"><span class="note-span">通知<i class="note-i">3</i></span><span class="more-style"><a
                        href="#">更多+</a> </span></h4>
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
        <h4 class="h-style"><span class="note-span">待办事项</span></h4>
        <div class="wait-tab"><span class="tab-active">绩效考核</span><span>入职申请</span><span>离职申请</span></div>
        <div class="tab-all">
            <div class="tab-con" style="display: block">
                <table id="table1" class="table-style"></table>
            </div>
            <div class="tab-con">
                <table id="table2" class="table-style"></table>
            </div>
            <div class="tab-con">
                <table id="table3" class="table-style"></table>
            </div>

        </div>
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
        table1();
        table2();
        table3();
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

    function table1() {
        $('#table1').bootstrapTable({
            method: "get",
            striped: false,
            singleSelect: false,
            url: "json/table1.json",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search: false, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            queryParams: null,
            columns: [
                {
                    title: "编号",
                    field: 'id',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '考核项',
                    field: 'title',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '创建时间',
                    field: 'date',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '状态',
                    field: 'person',
                    align: 'center'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        var d = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="add(\'' + row.ID + '\')">处理</button> ';

                        return d;
                    }
                }
            ]
        });
    }

    function table2() {
        $('#table2').bootstrapTable({
            method: "get",
            striped: false,
            singleSelect: false,
            url: "json/table.json",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search: false, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            queryParams: null,
            columns: [
                {
                    title: "编号",
                    field: 'id',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '姓名',
                    field: 'title',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '提交时间',
                    field: 'type',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '状态',
                    field: 'person',
                    align: 'center'
                },
                {
                    title: '类型',
                    field: 'st',
                    align: 'center'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        var d = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="add(\'' + row.ID + '\')">处理</button> ';

                        return d;
                    }
                }
            ]
        });
    }

    function table3() {
        $('#table3').bootstrapTable({
            method: "get",
            striped: false,
            singleSelect: false,
            url: "json/table.json",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search: false, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            queryParams: null,
            columns: [
                {
                    title: "编号",
                    field: 'id',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '姓名',
                    field: 'title',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '提交时间',
                    field: 'type',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '状态',
                    field: 'person',
                    align: 'center'
                },
                {
                    title: '类型',
                    field: 'st',
                    align: 'center'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        var d = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="add(\'' + row.ID + '\')">处理</button> ';

                        return d;
                    }
                }
            ]
        });
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