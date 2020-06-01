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
        <a href="#" onclick="NoticeList()"></a>
        <a href="#" onclick="addNewNotice()"></a>
        <a href="#"></a>
    </div>
    <div class="home-box">
        <h4 class="h-style"><span class="note-span">通知</span><span class="more-style"></span></h4>
        <ul class="note-ul">
            <#list noticeList as notice>
                <li><a href="#"
                       onclick="seeNotice(${(notice.getId())!})">${(notice.title)!}</a><span>${(notice.deadline.toString()?date("yyyy-MM-dd"))!}</span>
                </li>
            </#list>
        </ul>
        <h4 class="h-style"><span class="note-span">信息统计</span></h4>
        <div class="home-static" id="chart02"></div>
    </div>

</main>
<div id="noticeModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
     aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div id="noticeIframe" class="modal-content">
        </div>
    </div>
</div>
<script src="js/jquery.js"></script>
<script src="js/echarts-all.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script>
    function seeNotice(id) {
        $("#noticeIframe").load("/gotoNoticeContent.do?id=" + id);
        $("#noticeModal").modal('show')
    }
    //添加新的通知
    function addNewNotice() {
        $("#noticeIframe").load("/gotoAddNotice.do");
        $("#noticeModal").modal('show')
    }
    //前往历史通知列表
    function NoticeList() {
        $("#noticeIframe").load("/gotoNoticeList.do");
        $("#noticeModal").modal('show')
    }
    $(function () {
        char2();
    })


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
                    data: [
                        '#{onboardingLeavingTrendList[11].month}月','#{onboardingLeavingTrendList[10].month}月','#{onboardingLeavingTrendList[9].month}月',
                        '#{onboardingLeavingTrendList[8].month}月','#{onboardingLeavingTrendList[7].month}月','#{onboardingLeavingTrendList[6].month}月',
                        '#{onboardingLeavingTrendList[5].month}月','#{onboardingLeavingTrendList[4].month}月','#{onboardingLeavingTrendList[3].month}月',
                        '#{onboardingLeavingTrendList[2].month}月','#{onboardingLeavingTrendList[1].month}月','#{onboardingLeavingTrendList[0].month}月'
                    ]

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
                    data: [
                        #{onboardingLeavingTrendList[11].numberOfEmployees}, #{onboardingLeavingTrendList[10].numberOfEmployees},
                        #{onboardingLeavingTrendList[9].numberOfEmployees}, #{onboardingLeavingTrendList[8].numberOfEmployees},
                        #{onboardingLeavingTrendList[7].numberOfEmployees}, #{onboardingLeavingTrendList[6].numberOfEmployees},
                        #{onboardingLeavingTrendList[5].numberOfEmployees}, #{onboardingLeavingTrendList[4].numberOfEmployees},
                        #{onboardingLeavingTrendList[3].numberOfEmployees}, #{onboardingLeavingTrendList[2].numberOfEmployees},
                        #{onboardingLeavingTrendList[1].numberOfEmployees}, #{onboardingLeavingTrendList[0].numberOfEmployees}
                    ]
                },
                {
                    name: '离职',
                    type: 'line',
                    data: [
                        #{onboardingLeavingTrendList[11].numberOfSeparations}, #{onboardingLeavingTrendList[10].numberOfSeparations},
                        #{onboardingLeavingTrendList[9].numberOfSeparations}, #{onboardingLeavingTrendList[8].numberOfSeparations},
                        #{onboardingLeavingTrendList[7].numberOfSeparations}, #{onboardingLeavingTrendList[6].numberOfSeparations},
                        #{onboardingLeavingTrendList[5].numberOfSeparations}, #{onboardingLeavingTrendList[4].numberOfSeparations},
                        #{onboardingLeavingTrendList[3].numberOfSeparations}, #{onboardingLeavingTrendList[2].numberOfSeparations},
                        #{onboardingLeavingTrendList[1].numberOfSeparations}, #{onboardingLeavingTrendList[0].numberOfSeparations}
                    ]
                }

            ]
        };
        myChart.setOption(option);
        window.addEventListener('resize', function () {
            myChart.resize();
        })

    }
</script>
</body>
</html>