<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>人力资源管理平台</title>
    <link rel="stylesheet" href="css/all.css">
    <link rel="stylesheet" href="js/jQueryCalendar/calendar.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <style>
        .dropdown:hover ul {
            display: block;
        }
    </style>
</head>
<body>
<main class="all">
    <aside class="aside">
        <header class="aside-head"><img src="images/logo.jpg"></header>
        <div class="aside-per">
            <div style="text-align: center;"><img src="${(employees.headShot)!}" style="width: 100px;height: 100px"
                                                  class="per-img"/>
                <br>
                <br>
                <button id="btn1" class="btn btn-danger" onclick="saveSign()" style="display: none">上班签到</button>
                <button id="btn2" class="btn btn-success" style="display: none">已上班</button>
                <button id="btn3" class="btn btn-danger" onclick="updateSignGetOffWork()" style="display: none">下班签到
                </button>
                <button id="btn4" class="btn btn-success" onclick="updateSignGetOffWork()" style="display: none">已下班
                </button>
            </div>
            <div><p style="font-size: 24px;font-weight: 600">${(employees.name)!}</p>
                部门：<p style="font-weight: 700">${(employees.departmentName)!}</p>
                岗位：<p style="font-weight: 700">${(employees.jobName)!}</p></div>
        </div>
        <div class="clear"></div>
        <div class="aside-date">
            <p class="douline"><i class="i-date"></i> 日历</p>
            <div id="ca" class="date-con"></div>
        </div>
        <div class="clear"></div>
    </aside>
    <article class="artlce">
        <header class="aside-head">
            <ul class="nav-ul">
                <li class="active-li"><a href="home.html" target="fraName">首页</a></li>
                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">
                        入职管理 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class=""><a href="/gotoAddEmployees.do" target="fraName">入职登记</a></li>
                        <li class=""><a href="/gotoRegisterNewEmployeesList.do" target="fraName">入职列表</a></li>
                        <li class=""><a href="/gotoAuditEmployees.do" target="fraName">入职审核</a></li>
                    </ul>
                </li>
                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">
                        离职管理 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class=""><a href="/gotoDimissionApplication.do" target="fraName">离职申请</a></li>
                        <li class=""><a href="/gotoAuditDimission.do" target="fraName">审核离职申请</a></li>
                        <li class=""><a href="/gotoAuditDeleteUser.do" target="fraName">删除离职员工</a></li>
                        <li class=""><a href="/gotoDimissionUserList.do" target="fraName">离职员工列表</a></li>
                    </ul>
                </li>
                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">
                        绩效管理 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class=""><a href="/gotoAddAssessment.do" target="fraName">绩效考核项</a></li>
                        <li class=""><a href="/gotoPerformanceKpi.do" target="fraName">绩效认定</a></li>
                        <li class=""><a href="/gotoPerformanceList.do" target="fraName">绩效列表</a></li>
                    </ul>
                </li>
                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">
                        考勤管理 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class=""><a href="/gotoRequestVacationList.do" target="fraName">申请假期列表</a></li>
                        <li class=""><a href="/gotoAttendanceExceptionList.do" target="fraName">考勤异常列表</a></li>
                        <li class=""><a href="/gotoPerformanceKpi.do" target="fraName">考勤列表</a></li>
                    </ul>
                </li>
                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">
                        薪资管理 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class=""><a href="/gotoAddAssessment.do" target="fraName">调整薪资</a></li>
                        <li class=""><a href="/gotoPerformanceKpi.do" target="fraName">工资条列表</a></li>
                    </ul>
                </li>
                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">
                        岗位管理 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class=""><a href="/gotoAddAssessment.do" target="fraName">调整人员岗位</a></li>
                    </ul>
                </li>
                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">
                        招聘管理 <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class=""><a href="/gotoAddAssessment.do" target="fraName">招聘申请</a></li>
                    </ul>
                </li>
            </ul>
        </header>
        <section class="section">
            <iframe class="ifraem-style" frameborder="0" src="/home.do" name="fraName"></iframe>

        </section>

    </article>
</main>
<script src="js/jquery.js"></script>
<script src="js/jQueryCalendar/js/calendar.js"></script>
<script>
    $('#ca').calendar({
        width: 240,
        height: 280,
        data: [
            {
                date: '2015/12/24',
                value: 'Christmas Eve'
            },
            {
                date: '2015/12/25',
                value: 'Merry Christmas'
            },
            {
                date: '2016/01/01',
                value: 'Happy New Year'
            }
        ],
        onSelected: function (view, date, data) {
            console.log('view:' + view)
            alert('date:' + date)
            console.log('data:' + (data || 'None'));
        }
    });
    $(function () {
        $(".nav-ul li").click(function () {
            $(this).addClass("active-li").siblings().removeClass("active-li");
            $(this).find("ul").show();
            $(this).siblings().find('ul').hide();
        });
        //首先先确认是否签到了
        querySign();

    });

    //查询是否已签到
    function querySign() {
        $.ajax({
            url: "/querySign.do",
            type: "POST",
            cache: false,
            dataType: "json",
            success: function (result) {
                if (result.code === 1) {
                    if (result.object === 0) {
                        $("#btn1").show();
                        $("#btn2").hide();
                        $("#btn3").hide();
                        $("#btn4").hide();
                    }
                    if (result.object === 1) {
                        $("#btn1").hide();
                        $("#btn2").show();
                        $("#btn3").hide();
                        $("#btn4").hide();
                    }
                    if (result.object === 2) {
                        $("#btn1").hide();
                        $("#btn2").hide();
                        $("#btn3").show();
                        $("#btn4").hide();
                    }
                    if (result.object === 3) {
                        $("#btn1").hide();
                        $("#btn2").hide();
                        $("#btn3").hide();
                        $("#btn4").show();
                    }
                } else {
                    alert(result.msg);
                }

            },
            error: function () {
                alert("签到服务器异常，请刷新后重试")
            }
        });
    }

    //上班签到
    function saveSign() {
        $.ajax({
            url: "/saveSign.do",
            type: "POST",
            cache: false,
            dataType: "json",
            success: function (result) {
                if (result.code === 1) {
                    $("#btn1").hide();
                    $("#btn2").show();
                    $("#btn3").hide();
                    $("#btn4").hide();
                    alert(result.msg);
                } else {
                    alert(result.msg);
                }
            },
            error: function () {
                alert("连接服务器异常，请刷新后重试")
            }
        });
    }

    //下班签到
    function updateSignGetOffWork() {
        $.ajax({
            url: "/updateSignGetOffWork.do",
            type: "POST",
            cache: false,
            dataType: "json",
            success: function (result) {
                if (result.code === 1) {
                    $("#btn1").hide();
                    $("#btn2").hide();
                    $("#btn3").hide();
                    $("#btn4").show();
                    alert(result.msg);
                } else {
                    alert(result.msg);
                }
            },
            error: function () {
                alert("连接服务器异常，请刷新后重试")
            }
        });
    }
</script>
</body>
</html>