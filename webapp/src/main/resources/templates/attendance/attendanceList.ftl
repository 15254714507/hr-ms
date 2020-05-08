<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>考勤列表</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" type="text/css" href="css/jquery.step.css"/>
    <link href="css/table.css" rel="stylesheet" type="text/css">
    <script src="js/jquery.js"></script>
    <script src="js/jquery.step.min.js"></script>
    <link rel="stylesheet" href="css/all.css">

    <style>
        html, body {
            height: 100%
        }

        button {
            display: inline-block;
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            text-align: center;
            cursor: pointer;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #fff;
            background-color: #5bc0de;
        }

        .main {
            width: 90%;
            margin: 100px auto;
        }

        #step {
            margin-bottom: 60px;
        }

        .btns {
            float: left;
        }

        .info {
            float: left;
            height: 34px;
            line-height: 34px;
            margin-left: 40px;
            font-size: 28px;
            font-weight: bold;
            color: #928787;
        }

        .info span {
            color: red;
        }
    </style>
</head>

<body style="background-color: #ecf0f5;font-family: 微软雅黑;color: #475059;min-width: 1000px;overflow: auto">
<div class="notice_main">
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>日常考勤列表</span></div>
    <div class="table-con">
        <table id="table" class="table-style"></table>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        table();

    })

    function table() {
        $('#table').bootstrapTable({
            method: "POST",
            striped: false,
            singleSelect: false,
            url: "/getAttendanceList.do",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search: true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            queryParams: null,
            sortName: "createTime",
            sortStable: true,
            sortOrder: "desc",
            columns: [
                {
                    title: "id",
                    field: 'id',
                    visible: false
                },
                {
                    title: "编号",
                    field: 'username',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '上班时间',
                    field: 'workTime',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (cellval, row) {
                        if (cellval != null && cellval !== '') {
                            return cellval.replace("T", " ");
                        }
                    }
                },
                {
                    title: '下班时间',
                    field: 'getOffWork',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (cellval, row) {
                        if (cellval != null && cellval !== '') {
                            return cellval.replace("T", " ");
                        }
                    }
                },
                {
                    title: '加班时长',
                    field: 'workOvertime',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '说明',
                    field: 'description',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: "createTime",
                    field: 'createTime',
                    visible: false
                },
            ]
        });
    }

    //打开请假申请的页面
    function updateSign(id) {
        $("#iframe").load("/gotoUpdateAttendanceException.do?id=" + id);
        $("#modal").modal('show')
    }

    //确认考勤异常
    function passSign(id) {
        $.ajax({
            url: "/deleteRequestVacation.do",
            type: "POST",
            cache: false,
            data: {
                id: id
            },
            success: function (result) {
                if (result.code === 1) {
                    location.reload();
                }
                alert(result.msg);
            }
            ,
            error: function () {
                alert("连接服务器异常，请刷新后重试")
            }
        });
    }

    //通过请假申请
    function passVacation(id) {
        $.ajax({
            url: "/passRequestVacation.do",
            type: "POST",
            cache: false,
            data: {
                id: id
            },
            success: function (result) {
                if (result.code === 1) {
                    location.reload();
                }
                alert(result.msg);
            }
            ,
            error: function () {
                alert("连接服务器异常，请刷新后重试")
            }
        });
    }
</script>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
</body>
</html>