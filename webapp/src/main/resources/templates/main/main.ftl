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
            <div><img src="${(employees.headShot)!}" class="per-img"></div>
            <div><h3>${(employees.name)!}</h3>
                <p>${(employees.departmentName)!}</p>
                <br/>
                <p>${(employees.jobName)!}</p></div>
        </div>
        <div class="clear"></div>
        <div class="aside-date">
            <p class="douline"><i class="i-date"></i> 日历</p>
            <div id="ca" class="date-con"></div>
        </div>
        <div class="clear"></div>
        <div class="aside-date">
            <p class="douline"><i class="i-list"></i>登录日志</p>
            <p class="mes-styel"><label>登录IP：</label>192.168.11.23</p>
            <p class="mes-styel"><label>登录时间：</label>2018-11-12 10:12:32</p>
        </div>
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
                <li><a href="shares.html" target="fraName">薪资管理</a></li>
                <li><a href="mes.html" target="fraName">信息管理</a></li>
                <li><a href="myself.html" target="fraName">个人中心</a></li>
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
        })
    })
</script>
</body>
</html>